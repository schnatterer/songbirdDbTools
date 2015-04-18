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

import info.schnatterer.songbirddbapi4.SongbirdDbConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * The property types of a {@link MemberMediaItem}.
 * 
 * @author schnatterer
 * 
 */
public final class Property {
	/** Don't instantiate utility classes! */
	private Property() {
	}

	/** Gets all available properties. */
	public static final String QUERY_PROPERTIES = "select * from properties";

	/** Property constant for mediaListName. */
	public static final String PROP_MEDIA_LIST_NAME = "http://songbirdnest.com/data/1.0#mediaListName";
	/** Property constant for trackName. */
	public static final String PROP_TRACK_NAME = "http://songbirdnest.com/data/1.0#trackName";
	/** Property constant for albumName. */
	public static final String PROP_ALBUM_NAME = "http://songbirdnest.com/data/1.0#albumName";
	/** Property constant for artistName. */
	public static final String PROP_ARTIST_NAME = "http://songbirdnest.com/data/1.0#artistName";
	/** Property constant for rating. */
	public static final String PROP_RATING = "http://songbirdnest.com/data/1.0#rating";
	/** Property constant for customType. */
	public static final String PROP_CUSTOM_TYPE = "http://songbirdnest.com/data/1.0#customType";

	/**
	 * TODO Insert remaining properties
	 * 
	 * <pre>
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#duration";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#genre";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#trackNumber";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#year";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#discNumber";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#totalDiscs";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#totalTracks";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#lastPlayTime";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#playCount";
	 * 	;
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#isSortable";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#ordinal";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#defaultColumnSpec";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#isSubscription";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#outerGUID";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#originURL";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#enableAutoDownload";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#comment";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#bitRate";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#sampleRate";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#softwareVendor";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#primaryImageURL";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#albumArtistName";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#composerName";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#recordLabelName";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#language";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#lyrics";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#bpm";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#copyright";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#originPage";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#subtitle";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#key";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#columnSpec";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#defaultMediaPageURL";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#storageGUID";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#isReadOnly";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#isContentReadOnly";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#smartMediaListState";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#onlyCustomMediaPages";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#isPartOfCompilation";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#producerName";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#conductorName";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#lyricistName";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#lastSkipTime";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#skipCount";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#copyrightURL";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#metadataUUID";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#originLibraryGuid";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#originItemGuid";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#destination";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#downloadStatusTarget";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#artistDetailUrl";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#albumDetailUrl";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#originPageTitle";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#downloadButton";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#downloadDetails";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#originPageImage";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#artistDetailImage";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#albumDetailImage";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#rapiScopeURL";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#rapiSiteID";
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#disableDownload"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#excludeFromHistory"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#transferPolicy"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#availability"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#deviceId"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#playCount_AtLastSync"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#skipCount_AtLastSync"
	 * 	public static final String PROP_ = "http://songbirdnest.com/dummy/smartmedialists/1.0#playlist"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#subscriptionURL"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#subscriptionInterval"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#subscriptionNextRun"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#iTunesGUID"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#artistOnTour"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#artistOnTourUrl"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#streamName"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#favesSPSNodeCreated"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#listenerCount"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#streamFave"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#streamId"
	 * 	public static final String PROP_ = "*"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#hasLyrics"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#channels"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#downloadMediaListGUID"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#createdFirstRunSmartPlaylists"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#isDRMProtected"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#cdRipStatus"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#deviceLibraryGuid"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#shouldRip"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#dontWriteMetadata"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#playlistURL"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#attemptedRemoteArtFetch"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#uiLimitType"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#columnSpec+(audio)"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#defaultColumnSpec+(audio)"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#lastPlayPosition"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#cdDiscHash"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#keywords"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#description"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#showName"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#episodeNumber"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#seasonNumber"
	 * 	public static final String PROP_ = "http://songbirdnest.com/device/1.0#capacity"
	 * 	public static final String PROP_ = "http://songbirdnest.com/device/1.0#freeSpace"
	 * 	public static final String PROP_ = "http://songbirdnest.com/device/1.0#totalUsedSpace"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#columnSpec+(video)"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#defaultColumnSpec+(video)"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#libraryItemControllerLastSeenTypes"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#trackType"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#playQueueMediaListGUID"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#artistNewRelease"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#artistNewReleaseUrl"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#hasLRCfile"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#mlyricsScrollCorrArray"
	 * 	public static final String PROP_ = "Location"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#translatedLyrics"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#originIsInMainLibrary"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#importType"
	 * 	public static final String PROP_ = "http://songbirdnest.com/data/1.0#lastSyncTime"
	 * 	public static final String PROP_ = "http://songbirdnest.com/device/1.0#musicItemCount"
	 * 	public static final String PROP_ = "http://songbirdnest.com/device/1.0#musicUsedSpace"
	 * 	public static final String PROP_ = "http://songbirdnest.com/device/1.0#musicTotalPlayTime"
	 * 	public static final String PROP_ = "http://songbirdnest.com/device/1.0#videoItemCount"
	 * 	public static final String PROP_ = "http://songbirdnest.com/device/1.0#videoUsedSpace"
	 * 	public static final String PROP_ = "http://songbirdnest.com/device/1.0#videoTotalPlayTime"
	 * 	public static final String PROP_ = "http://songbirdnest.com/device/1.0#imageItemCount"
	 * 	public static final String PROP_ = "http://songbirdnest.com/device/1.0#imageUsedSpace"
	 * </pre>
	 */

	/** Mapping from numerical IDs to string constants (database enums). */
	private static Map<Integer, String> id2PropertyMap;
	/** Mapping from string constants (database enums) to numerical IDs. */
	private static Map<String, Integer> property2IdMap;

	/**
	 * Initializes the mappings from string constants (database enums) to numerical IDs and the other way round from the
	 * database.
	 * 
	 * To avoid doing this again and again, use {@link #isInitialized()}.
	 * 
	 * @throws SQLException
	 *             in case an error occurs during the db query.
	 */
	public static void populateResourceMap(SongbirdDbConnection connection) throws SQLException {
		property2IdMap = new HashMap<String, Integer>();
		id2PropertyMap = new HashMap<Integer, String>();
		ResultSet rs = connection.executeQuery(QUERY_PROPERTIES);
		while (rs.next()) {
			// read the result set
			Integer id = rs.getInt(1);
			String name = rs.getString(2);
			property2IdMap.put(name, id);
			id2PropertyMap.put(id, name);
		}
	}

	/**
	 * @param id
	 *            a numerical ID to be mapped to the corresponding string
	 * @return the string representation of a numerical ID.
	 */
	public static String id2Property(final int id) {
		return id2PropertyMap.get(id);
	}

	/**
	 * @param property
	 *            a string representation to be mapped to the corresponding numerical ID.
	 * @return the numerical ID of a string representation.
	 */
	public static int property2Id(final String property) {
		return property2IdMap.get(property);
	}

	/**
	 * Checks if maps have been initialized from database.
	 * 
	 * @return
	 */
	public static boolean isInitialized() {
		if (property2IdMap == null || property2IdMap.size() == 0 || id2PropertyMap == null
				|| id2PropertyMap.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
}
