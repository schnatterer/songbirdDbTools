/* Copyright (C) 2013 Johannes Schnatterer
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

package info.schnatterer.songbirddbapi4j.domain;

import info.schnatterer.songbirddbapi4j.domain.util.MemberMediaItemComparator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A Playlist object.
 * 
 * @author schnatterer
 * 
 */
public class SimpleMediaList {

	/** The Media item representing the play list. */
	private MediaItem list;

	/** The MediaItems representing the member. */
	private List<MemberMediaItem> members = new LinkedList<MemberMediaItem>();

	/**
	 * @return the list
	 */
	public MediaItem getList() {
		return list;
	}

	/**
	 * @param newList
	 *            the list to set
	 */
	public void setList(final MediaItem newList) {
		this.list = newList;
	}

	/**
	 * @return the members
	 */
	public List<MemberMediaItem> getMembers() {
		return members;
	}

	/**
	 * @param newMembers
	 *            the members to set
	 */
	public void setMembers(final List<MemberMediaItem> newMembers) {
		this.members = newMembers;
	}

	/**
	 * @param descending
	 *            <code>true</code> sorts descendingly.
	 */
	public void sortMembers(final boolean descending) {
		Collections.sort(members, new MemberMediaItemComparator(false));
	}
}
