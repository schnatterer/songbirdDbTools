/**
 * Copyright (C) 2015 Johannes Schnatterer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package info.schnatterer.java.util.jar;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 * Basic abstraction from the jar file the code is contained in.
 * 
 * @author schnatterer
 *
 */
public class Jar {

	/**
	 * Tries to read an attribute <code>build</code> from the manifest of the
	 * jar.
	 * 
	 * @return the value of the <code>build</code> attribute or
	 *         <code>null</code> if not found or not even in a jar file
	 * 
	 * @throws IOException
	 *             if an I/O error has occurred reading the manifest
	 */
	public static String getBuildNumberFromManifest() throws IOException {
		InputStream manifestStream = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("META-INF/MANIFEST.MF");
		if (manifestStream != null) {
			Manifest manifest = new Manifest(manifestStream);
			Attributes attributes = manifest.getMainAttributes();
			return attributes.getValue("build");
		}
		return null;
	}
}
