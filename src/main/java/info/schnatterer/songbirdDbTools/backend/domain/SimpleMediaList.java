package info.schnatterer.songbirdDbTools.backend.domain;

import info.schnatterer.songbirdDbTools.backend.domain.util.MemberMediaItemComparator;

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
