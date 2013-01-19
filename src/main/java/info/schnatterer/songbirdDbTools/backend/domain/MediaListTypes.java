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

package info.schnatterer.songbirdDbTools.backend.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import info.schnatterer.songbirdDbTools.backend.connection.SongbirdDatabaseConnection;

/**
 * The type of a {@link MediaItem}.
 * 
 * @author schnatterer
 * 
 */
public final class MediaListTypes {
	/** Don't instantiate utility classes! */
	private MediaListTypes() {
	}

	/** Gets all available list types. */
	public static final String QUERY_LIST_TYPES = "select * from media_list_types";

	static {
		listTyep2IdMap = new HashMap<String, Integer>();
		id2ListTypeMap = new HashMap<Integer, String>();
		try {
			populatelistTypeMap();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * Why not use enum, which would be easier to use and more efficient? Software must be adapted to DB changes, i.e.
	 * map is more flexible
	 */
	/** Mapping from string constants (database enums) to numerical IDs. */
	private static Map<String, Integer> listTyep2IdMap;
	/** Mapping from numerical IDs to string constants (database enums). */
	private static Map<Integer, String> id2ListTypeMap;

	/**
	 * Initializes the mappings from string constants (database enums) to numerical IDs and the other way round from the
	 * database.
	 * 
	 * @throws SQLException
	 *             in case an error occurs during the db query.
	 */
	private static void populatelistTypeMap() throws SQLException {
		ResultSet rs = SongbirdDatabaseConnection.executeQuery(QUERY_LIST_TYPES);
		while (rs.next()) {
			// read the result set
			Integer id = rs.getInt(1);
			String type = rs.getString(2);
			id2ListTypeMap.put(id, type);
			listTyep2IdMap.put(type, id);
			// logger.debug("id=" + id + "; type=" + type );
		}
	}

	/**
	 * @param id
	 *            a numerical ID to be mapped to the corresponding string
	 * @return the string representation of a numerical ID.
	 */
	public static String id2ListType(final int id) {
		return id2ListTypeMap.get(id);
	}

	/**
	 * @param listType
	 *            a string representation to be mapped to the corresponding numerical ID.
	 * @return the numerical ID of a string representation.
	 */
	public static int listType2Id(final String listType) {
		return listTyep2IdMap.get(listType);
	}

}
