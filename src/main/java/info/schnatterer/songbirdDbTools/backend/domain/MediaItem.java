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

import java.util.HashMap;
import java.util.Map;

/**
 * A generic media item containing {@link Property}s.
 * 
 * This is the underlying data structure for either playlist (modeled by {@link SimpleMediaList}) or a members (modeled
 * by {@link MemberMediaItem}).
 * 
 * @author schnatterer
 * 
 */
public class MediaItem {

	/** ID of this MediaItem within the Songbird database. */
	private Integer id;

	/** Content URL of this MediaItems. This can be the URL to an mp3 file. */
	private String contentUrl;
	/** All properties of the MediaItem. See {@link Property} for available properties. */
	private Map<Integer, String> properties = new HashMap<Integer, String>();
	/** The list type of this MediaItem. See {@link MediaListTypes}. */
	private int listType;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the contentUrl
	 */
	public String getContentUrl() {
		return contentUrl;
	}

	/**
	 * @param property
	 *            the property to be retrieved. See {@link Property} for available properties.
	 * @return the property, of <code>null</code> if no such property.
	 */
	public String getProperty(final String property) {
		if (property == null) {
			return null;
		}
		return properties.get(Property.property2Id(property));
	}

	/**
	 * @param newId
	 *            the id to set
	 */
	public void setId(final Integer newId) {
		this.id = newId;
	}

	/**
	 * @param newContentUrl
	 *            the contentUrl to set
	 */
	public void setContentUrl(final String newContentUrl) {
		this.contentUrl = newContentUrl;
	}

	/**
	 * @param newProperties
	 *            the properties to set
	 */
	public void setProperties(final Map<Integer, String> newProperties) {
		this.properties = newProperties;
	}

	/**
	 * @return the listType
	 */
	public String getListType() {
		return MediaListTypes.id2ListType(listType);
	}

	/**
	 * @param newListType
	 *            the listType to set
	 */
	public void setListType(final String newListType) {
		this.listType = MediaListTypes.listType2Id(newListType);
	}

	/**
	 * @param newListType
	 *            the listType to set
	 */
	public void setListType(final int newListType) {
		this.listType = newListType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MediaItem [id=" + id + ", contentUrl=" + contentUrl + ", properties=" + properties + ", listType="
				+ listType + "]";
	}

	/**
	 * @return the properties
	 */
	public Map<Integer, String> getProperties() {
		return properties;
	}

}
