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

package info.schnatterer.songbirddbapi4j.domain;

/**
 * A member object of a playlist ({@link SimpleMediaList}), contains a {@link MediaItem} as well as the ordinal within
 * the playlist.
 * 
 * @author schnatterer
 * 
 */
public class MemberMediaItem {

	/**
	 * Ordinal, that is the position of the member within the playlist. Looks something like:
	 * 72.31.0.20.2.0.-1.-1.1.-1.0.
	 * 
	 * Can be sorted using {@link info.schnatterer.songbirddbapi4j.domain.util.MemberMediaItemComparator}.
	 */
	private String oridnal;
	/** The actual member object. */
	private MediaItem member;

	/**
	 * @return the position of the member within the playlist
	 */
	public String getOridnal() {
		return oridnal;
	}

	/**
	 * @return the actual member object.
	 */
	public MediaItem getMember() {
		return member;
	}

	/**
	 * @param newOrdinal
	 *            the oridnal to set
	 */
	public void setOridnal(final String newOrdinal) {
		this.oridnal = newOrdinal;
	}

	/**
	 * @param newMember
	 *            the member to set
	 */
	public void setMember(final MediaItem newMember) {
		this.member = newMember;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MemberMediaItem [oridnal=" + oridnal + ", member=" + member + "]";
	}

}
