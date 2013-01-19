/**
* Copyright (C) 2013 Johannes Schnatterer
* See the NOTICE file distributed with this work for additional
* information regarding copyright ownership.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package info.schnatterer.songbirdDbTools.Utils;

/**
 * Contains utility methods for {@link String} operations.
 * 
 * @author schnatterer
 * 
 */
public final class StringUtils {
	/** Don't instantiate utility classes! */
	private StringUtils() {
	}

	/**
	 * Returns a substring before a certain index, excluding the character at
	 * the index.
	 * 
	 * @param str
	 *            string to find substring in
	 * @param index
	 *            position of the first character that is excluded.
	 * @return a substring before a certain index, excluding the character at
	 *         the index.
	 */
	public static String substringBefore(final String str, final int index) {
		return str.substring(0, index);
	}

	/**
	 * Returns a substring after a certain index, excluding the character at the
	 * index.
	 * 
	 * @param str
	 *            string to find substring in
	 * @param substring
	 *            the returned value begins after this substring.
	 * @return a substring after a certain index, excluding the character at the
	 *         index.
	 */
	public static String substringAfter(final String str, final String substring) {
		return str.substring(substring.length() + 1);
	}
}
