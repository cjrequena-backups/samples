package com.sample.math.solutions;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author cjrequena
 *
 */
public class Solution {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static void main(String[] args) {
		new Solution().execute();
	}

	private void execute() {
		try {

			File folder = new File("/Users/cjrequena/Development/temporal/");
			File[] listOfFiles = folder.listFiles();

			for (File file : listOfFiles) {
				if (file.isFile()) {
					if (file.getName().contains(".xml")) {
						List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()), Charset.defaultCharset());

						for (Iterator<String> iterator = lines.iterator(); iterator.hasNext();) {
							String string = (String) iterator.next();
							List<String> matches = getAllMatches(EMAIL_PATTERN, string, Pattern.CASE_INSENSITIVE);
							if (matches != null && matches.size() > 0) {
								System.out.println("The File: " + file.getName() + " | Contains emails addresses");
							}

						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<String> getAllMatches(String pattern, String input, int flags) {
		Pattern p = Pattern.compile(pattern, flags);
		CharSequence i = input;
		List<String> matches = new ArrayList<String>();
		for (MatchResult match : getAllMatches(p, i)) {
			matches.add(match.group());
		}
		return matches;
	}

	private static Iterable<MatchResult> getAllMatches(final Pattern p, final CharSequence input) {
		return new Iterable<MatchResult>() {
			public Iterator<MatchResult> iterator() {
				return new Iterator<MatchResult>() {
					// Use a matcher internally.
					final Matcher matcher = p.matcher(input);
					// Keep a match around that supports any interleaving of
					// hasNext/next calls.
					MatchResult pending;

					public boolean hasNext() {
						// Lazily fill pending, and avoid calling find()
						// multiple times if the
						// clients call hasNext() repeatedly before sampling via
						// next().
						if (pending == null && matcher.find()) {
							pending = matcher.toMatchResult();
						}
						return pending != null;
					}

					public MatchResult next() {
						// Fill pending if necessary (as when clients call
						// next() without
						// checking hasNext()), throw if not possible.
						if (!hasNext()) {
							throw new NoSuchElementException();
						}
						// Consume pending so next call to hasNext() does a
						// find().
						MatchResult next = pending;
						pending = null;
						return next;
					}

					/** Required to satisfy the interface, but unsupported. */
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}
}