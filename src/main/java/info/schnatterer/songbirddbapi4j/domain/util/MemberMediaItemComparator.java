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

package info.schnatterer.songbirddbapi4j.domain.util;

import info.schnatterer.songbirdDbTools.Utils.StringUtils;
import info.schnatterer.songbirddbapi4j.domain.MemberMediaItem;

import java.util.Comparator;

/**
 * {@link Comparator}, that allows for comparing two {@link MemberMediaItem}s.
 * This is useful for ordering them by their
 * {@link MemberMediaItem#getOridnal()}, which is a String containing numbers
 * separated by dots.
 * 
 * @author schnatterer
 * 
 */
public class MemberMediaItemComparator implements Comparator<MemberMediaItem> {

	/** The character that separates the individual numbers within the ordinal. */
	private static final char SEPARATOR = '.';
	/** Sort descending? */
	private boolean desc = false;

	/**
	 * Constructs a new {@link MemberMediaItem}, which sorts the list in a
	 * descending/ascending order.
	 * 
	 * @param descending
	 *            if <code>true</code>, the list is sorted descendingly,
	 *            otherwise ascendingly.
	 */
	public MemberMediaItemComparator(final boolean descending) {
		desc = descending;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(final MemberMediaItem member1, final MemberMediaItem member2) {
		return compare(member1.getOridnal(), member2.getOridnal());
	}

	/**
	 * Actual String comparator that compares two ordinals like "168.199" and
	 * "72.26.0.1.1.1.1.1".
	 * 
	 * @param str1
	 *            the first ordinal to be compared
	 * @param str2
	 *            the second ordinal to be compared
	 * @return < 0 when the first argument is less than the second. Return 0
	 *         when the first argument equals the second. Return > 0 when the
	 *         first argument is greater than the second. Or the opposite, if
	 *         #get
	 */
	public int compare(final String str1, final String str2) {
		// Return < 0 when the first argument is less than the second.
		// Return 0 when the first argument equals the second.
		// Return > 0 when the first argument is greater than the second.

		String substr1 = getSubstringToDot(str1);
		String substr2 = getSubstringToDot(str2);

		int compare = Integer.parseInt(substr1) - Integer.parseInt(substr2);

		// Equal and more dots -> Continue comparing
		if (compare == 0) {
			if ((substr1.length() != str1.length())
					&& (substr2.length() != str2.length())) {
				// Keep comparing
				return compare(StringUtils.substringAfter(str1, substr1),
						StringUtils.substringAfter(str2, substr2));
			} else if (str1.length() != str2.length()) {
				// The shorter string is less
				compare = str1.length() - str2.length();
			}
		}
		// Else: They really are equal: No more dots same value and same length

		if (desc) {
			compare = -compare;
		}
		return compare;
	}

	/**
	 * Returns all characters to the first dot ('.')that is found.
	 * 
	 * @param str
	 *            string to look for dots
	 * @return a substring containing all chars until (but excluding) the first
	 *         dot.
	 */
	public String getSubstringToDot(final String str) {
		int dot = str.indexOf(SEPARATOR);
		if (dot > 0) {
			return StringUtils.substringBefore(str, dot);
		}
		return str;
	}

	/**
	 * @return <code>true</code>, the list is sorted descendingly, otherwise
	 *         ascendingly.
	 */
	protected boolean isDescending() {
		return desc;
	}

}