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

package info.schnatterer.songbirdDbTools.backend.domain.util;

import static org.junit.Assert.*;
import info.schnatterer.songbirddbapi4j.domain.MemberMediaItem;
import info.schnatterer.songbirddbapi4j.domain.util.MemberMediaItemComparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class MemberMediaItemComparatorTest {

	public static final String TEST_0 = "72.31.0.20.2.0.-1.-1.1.-1.0";
	public static final String TEST_1 = "72.31.0.20.2.0.-1.-1.1";
	public static final String TEST_2 = "72.31.0.20.2.0.-1.-1.0";
	public static final String TEST_3 = "72.31.0.20.2.0.1.1.8";
	public static final String TEST_4 = "72.31.0.20.2.0.1.1.7";
	public static final String TEST_5 = "100";
	public static final String TEST_6 = "71.0";
	public static final String TEST_7 = "168.813.45.0.238.0.2.13.466.140.14.1.52.298.1.277.117.0.40.0.68.13.115.40.0.1.1.56.9";
	public static final String TEST_8 = "168.813.45.0.238.0.2.13.466.140.14.1.52.298.1.277.117.0.40.0.68.13.115.40.0.1.1.56.8";

	@Test
	public void testSortingNoDots() {
		boolean descending = false;
		MemberMediaItemComparator comp = new MemberMediaItemComparator(
				descending);
		assertTrue("First arg must be greater than second",
				comp.compare(TEST_5, TEST_6) > 0);
	}

	@Test
	public void testSortingNoDots2() {
		MemberMediaItemComparator comp = new MemberMediaItemComparator(false);
		assertTrue("First arg must be less than second",
				comp.compare(TEST_5, TEST_8) < 0);
	}
	
	@Test
	public void testDifferentLength() {
		MemberMediaItemComparator comp = new MemberMediaItemComparator(false);
		// 1 shorter than 0
		assertTrue("First arg must be less than second",
				comp.compare(TEST_1, TEST_0) < 0);
	}
	
	@Test
	public void testNegative() {
		MemberMediaItemComparator comp = new MemberMediaItemComparator(false);
		// 1 shorter than 0
		assertTrue("First arg must be less than second",
				comp.compare(TEST_2, TEST_3) < 0);
	}

	@Test
	public void testSorting() {
		List<MemberMediaItem> list = new LinkedList<MemberMediaItem>();
		addMember(list, TEST_0); // 0
		addMember(list, TEST_1); // 1
		addMember(list, TEST_2); // 2
		addMember(list, TEST_3); // 3
		addMember(list, TEST_4); // 4
		addMember(list, TEST_5); // 5
		addMember(list, TEST_6); // 6
		addMember(list, TEST_7); // 7
		addMember(list, TEST_8); // 8

		/* Compare: Expected order: 7, 4, 3, 2, 1, 0, 6, 9, 8 */
		Collections.sort(list, new MemberMediaItemComparator(false));
		List<String> actual = new LinkedList<String>();
		for (MemberMediaItem memberMediaItem : list) {
			actual.add(memberMediaItem.getOridnal());
		}

		List<String> expected = Arrays.asList(TEST_6, TEST_2, TEST_1, TEST_0,
				TEST_4, TEST_3, TEST_5, TEST_8, TEST_7);
		assertEquals(expected, actual);

	}

	private void addMember(List<MemberMediaItem> list, String ordinal) {
		MemberMediaItem member = new MemberMediaItem();
		member.setOridnal(ordinal);
		// MediaItem mediaItem = new MediaItem();
		// mediaItem.setId(list.size());
		// member.setMember(mediaItem);
		list.add(member);
	}

}
