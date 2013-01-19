package info.schnatterer.songbirdDbTools.backend.domain;

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
	 * Can be sorted using {@link info.schnatterer.songbirdDbTools.backend.domain.util.MemberMediaItemComparator}.
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
