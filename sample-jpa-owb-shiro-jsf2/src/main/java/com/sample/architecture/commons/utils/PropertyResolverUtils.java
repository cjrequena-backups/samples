package com.sample.architecture.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import com.sample.architecture.commons.constants.Constants;




/**
 * <p>
 * Reads all valid property files within the classpath and prepare them to be fetched.
 * </p>
 * 
 * <p>
 * This class <strong>can</strong> be accessed concurrently by multiple clients. The inner representation of properties <strong>should not</strong> be leaked out; if this is absolutely required, use
 * unmodifiable collection.
 * </p>
 * 
 * <p>
 * This resolver <strong>doesn't pay attention</strong> to multiple properties defined with the same name in different files. It's impossible to determine which one will take precedence, so the
 * responsibility for name-clash is a deployer concern.
 * </p>
 * 
 * @author cjrequena
 * 
 */
@Singleton
public class PropertyResolverUtils {

	Map<String, Object> properties = new HashMap<String, Object>();


	/**
	 * Initializes the properties by reading and uniforming them.
	 * 
	 * This method is called by the container only. It's not supposed to be invoked by the client directly.
	 * 
	 * @throws IOException
	 *             in case of any property file access problem
	 * @throws URISyntaxException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostConstruct
	private void init() {
		try {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();

			List<File> propertyFiles;
			try {
				propertyFiles = getPropertyFiles(cl);
			} catch (Exception e1) {
				propertyFiles = new ArrayList<File>();
			}

			try {
				// BUSCA EN EL CLASSPATH
				ResourcesLocator rl = new ResourcesLocator(Constants.CONFIG_FILE);
				if (rl.getAbsolutePath() != null) {
					File file = new File(rl.getAbsolutePath());
					propertyFiles.add(file);
				} else {
					// BUSCA EN UN PATH ESPECIFICO
					File file = new File(Constants.CONFIG_FILE);
					propertyFiles.add(file);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			for (File file : propertyFiles) {
				Properties p = new Properties();
				p.load(new FileInputStream(file));

				properties.putAll(new HashMap<String, Object>((Map) p));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Gets flat-file properties files accessible from the root of the given classloader.
	 * 
	 * @param cl
	 *            classpath to be used when scanning for files.
	 * 
	 * @return found property files.
	 * 
	 * @throws IOException
	 *             if there was a problem while accessing resources using the <code>cl</code>.
	 */
	List<File> getPropertyFiles(ClassLoader cl) throws IOException {
		List<File> result = new ArrayList<File>();

		Enumeration<URL> resources = cl.getResources("");

		while (resources.hasMoreElements()) {
			File resource = getFileFromURL(resources.nextElement());

			File[] files = resource.listFiles(new PropertyFileFilterUtils());
			result.addAll(Arrays.asList(files));
		}

		return result;
	}

	/**
	 * Converts URL resource to a File. Makes sure that invalid URL characters (e.g. whitespaces) won't prevent us from accessing the valid file location.
	 * 
	 * @param url
	 *            URL to be transformed
	 * 
	 * @return File pointing to the given <code>url</code>.
	 */
	File getFileFromURL(URL url) {

		System.out.println(url.getPath());
		File result;

		try {
			result = new File(url.toURI());
		} catch (URISyntaxException e) {
			result = new File(url.getPath());
		}

		return result;
	}

	/**
	 * Returns property held under specified <code>key</code>. If the value is supposed to be of any other type than {@link String}, it's up to the client to do appropriate casting.
	 * 
	 * @param key
	 * @return value for specified <code>key</code> or null if not defined.
	 */
	public String getValue(String key) {
		Object value = properties.get(key);

		return (value != null) ? String.valueOf(value) : null;
	}
}
