package com.sample;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * 
 * @author cjrequena
 *
 */
public class ResourcesLocator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private File file;
	private URL url;

	public ResourcesLocator() {

	}

	public ResourcesLocator(String name) throws IOException {
		this.name = name;
		SecurityException exception = null;

		try {
			// Search using the CLASSPATH. If found, "file" is set and the call
			// returns true. A SecurityException might bubble up.
			if (tryClasspath(name)) {
				return;
			}
		} catch (SecurityException e) {
			exception = e; // Save for later.
		}

		try {
			// Search using the classloader getResource( ). If found as a file,
			// "file" is set; if found as a URL, "url" is set.
			if (tryLoader(name)) {
				return;
			}
		} catch (SecurityException e) {
			exception = e; // Save for later.
		}

		// If you get here, something went wrong. Report the exception.
		String msg = "";
		if (exception != null) {
			msg = ": " + exception;
		}

		throw new IOException("ResourceLocator '" + name + "' could not be found in " + "the CLASSPATH (" + System.getProperty("java.class.path")
				+ "), nor could it be located by the classloader responsible for the " + "web application (WEB-INF/classes)" + msg);
	}

	/**
	 * Returns the resource name, as passed to the constructor
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the directory containing the resource, or null if the resource isn't directly available on the filesystem. This value can be used to locate the configuration file on disk, or to write
	 * files in the same directory.
	 */
	public String getDirectory() {
		if (file != null) {
			return file.getParent();
		} else if (url != null) {
			return null;
		}
		return null;
	}

	/**
	 * Returns the absolute path containing the resource, or null if the resource isn't directly available on the filesystem. This value can be used to locate the configuration file on disk, or to
	 * write files in the same directory.
	 */
	public String getAbsolutePath() {
		if (file != null) {
			return file.getAbsolutePath();
		} else if (url != null) {
			return null;
		}
		return null;
	}

	/**
	 * Returns an input stream to read the resource contents
	 */
	public InputStream getInputStream() throws IOException {
		if (file != null) {
			return new BufferedInputStream(new FileInputStream(file));
		} else if (url != null) {
			return new BufferedInputStream(url.openStream());
		}
		return null;
	}

	/**
	 * Returns when the resource was last modified. If the resource was found using a URL, this method will work only if the URL connection supports last modified information. If there's no support,
	 * Long.MAX_VALUE is returned. Perhaps this should return -1, but you should return MAX_VALUE on the assumption that if you can't determine the time, it's maximally new.
	 */
	public long lastModified() {
		if (file != null) {
			return file.lastModified();
		} else if (url != null) {
			try {
				return url.openConnection().getLastModified(); // Hail Mary
			} catch (IOException e) {
				return Long.MAX_VALUE;
			}
		}
		return 0; // can't happen
	}

	public static File urlToFile(URL res) {
		String externalForm = res.toExternalForm();
		if (externalForm.startsWith("file:")) {
			return new File(externalForm.substring(5));
		}
		return null;
	}

	public boolean tryClasspath(String filename) {
		String classpath = System.getProperty("java.class.path");
		String[] paths = split(classpath, File.pathSeparator);
		file = searchDirectories(paths, filename);
		return (file != null);
	}

	public static File searchDirectories(String[] paths, String filename) {
		SecurityException exception = null;
		for (int i = 0; i < paths.length; i++) {
			try {
				File file = new File(paths[i], filename);
				if (file.exists() && !file.isDirectory()) {
					return file;
				}
			} catch (SecurityException e) {
				// Security exceptions can usually be ignored, but if all
				// attempts
				// to find the file fail, report the (last) security exception.
				exception = e;
			}
		}
		// Couldn't find any match
		if (exception != null) {
			throw exception;
		} else {
			return null;
		}
	}

	private static String[] split(String str, String delim) {
		// Use a Vector to hold the split strings.
		Vector<String> v = new Vector<String>();

		// Use a StringTokenizer to do the splitting.
		StringTokenizer tokenizer = new StringTokenizer(str, delim);
		while (tokenizer.hasMoreTokens()) {
			v.addElement(tokenizer.nextToken());
		}

		String[] ret = new String[v.size()];
		v.copyInto(ret);
		return ret;
	}

	private boolean tryLoader(String name) {
		name = "/" + name;
		URL res = ResourcesLocator.class.getResource(name);
		if (res == null) {
			return false;
		}

		// Try converting from a URL to a File.
		File resFile = urlToFile(res);
		if (resFile != null) {
			file = resFile;
		} else {
			url = res;
		}
		return true;
	}

	public static Collection<String> getResources(final Pattern pattern) {
		final ArrayList<String> retval = new ArrayList<String>();
		final String classPath = System.getProperty("java.class.path", ".");
		final String[] classPathElements = classPath.split(System.getProperty("path.separator"));
		for (final String element : classPathElements) {
			retval.addAll(getResources(element, pattern));
		}
		return retval;
	}

	private static Collection<String> getResources(final String element, final Pattern pattern) {
		final ArrayList<String> retval = new ArrayList<String>();
		final File file = new File(element);
		if (file.isDirectory()) {
			retval.addAll(getResourcesFromDirectory(file, pattern));
		} else {
			retval.addAll(getResourcesFromJarFile(file, pattern));
		}
		return retval;
	}

	private static Collection<String> getResourcesFromJarFile(final File file, final Pattern pattern) {
		final ArrayList<String> retval = new ArrayList<String>();
		ZipFile zf;
		try {
			zf = new ZipFile(file);
		} catch (final ZipException e) {
			throw new Error(e);
		} catch (final IOException e) {
			throw new Error(e);
		}
		final Enumeration<?> e = zf.entries();
		while (e.hasMoreElements()) {
			final ZipEntry ze = (ZipEntry) e.nextElement();
			final String fileName = ze.getName();
			final boolean accept = pattern.matcher(fileName).matches();
			if (accept) {
				retval.add(fileName);
			}
		}
		try {
			zf.close();
		} catch (final IOException e1) {
			throw new Error(e1);
		}
		return retval;
	}

	private static Collection<String> getResourcesFromDirectory(final File directory, final Pattern pattern) {
		final ArrayList<String> retval = new ArrayList<String>();
		final File[] fileList = directory.listFiles();
		for (final File file : fileList) {
			if (file.isDirectory()) {
				retval.addAll(getResourcesFromDirectory(file, pattern));
			} else {
				try {
					final String fileName = file.getCanonicalPath();
					final boolean accept = pattern.matcher(fileName).matches();
					if (accept) {
						retval.add(fileName);
					}
				} catch (final IOException e) {
					throw new Error(e);
				}
			}
		}
		return retval;
	}

	public String toString() {
		return "[ResourceLocator: File: " + file + " URL: " + url + "]";
	}
}