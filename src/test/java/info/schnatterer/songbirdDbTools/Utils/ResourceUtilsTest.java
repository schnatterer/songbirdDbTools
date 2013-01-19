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

package info.schnatterer.songbirdDbTools.Utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import info.schnatterer.songbirdDbTools.Utils.ResourceUtils.PathResolutionException;

public class ResourceUtilsTest {
	@Test
	public void testGetRelativePathsUnix() {
		assertEquals("stuff/xyz.dat", ResourceUtils.getRelativePath(
				"/var/data/", "/var/data/stuff/xyz.dat", "/"));
		assertEquals("../../b/c",
				ResourceUtils.getRelativePath("/a/x/y/", "/a/b/c", "/"));
		assertEquals("../../b/c", ResourceUtils.getRelativePath(
				"/m/n/o/a/x/y/", "/m/n/o/a/b/c", "/"));
	}

	@Test
	public void testGetRelativePathFileToFile() {
		String target = "C:\\Windows\\Boot\\Fonts\\chs_boot.ttf";
		String base = "C:\\Windows\\Speech\\Common\\sapisvr.exe";

		String relPath = ResourceUtils.getRelativePath(base, target, "\\");
		assertEquals("..\\..\\Boot\\Fonts\\chs_boot.ttf", relPath);
	}

	@Test
	public void testGetRelativePathDirectoryToFile() {
		String target = "C:\\Windows\\Boot\\Fonts\\chs_boot.ttf";
		String base = "C:\\Windows\\Speech\\Common\\";

		String relPath = ResourceUtils.getRelativePath(base, target, "\\");
		assertEquals("..\\..\\Boot\\Fonts\\chs_boot.ttf", relPath);
	}

	@Test
	public void testGetRelativePathFileToDirectory() {
		String target = "C:\\Windows\\Boot\\Fonts";
		String base = "C:\\Windows\\Speech\\Common\\foo.txt";

		String relPath = ResourceUtils.getRelativePath(base, target, "\\");
		assertEquals("..\\..\\Boot\\Fonts", relPath);
	}

	@Test
	public void testGetRelativePathDirectoryToDirectory() {
		String target = "C:\\Windows\\Boot\\";
		String base = "C:\\Windows\\Speech\\Common\\";
		String expected = "..\\..\\Boot";

		String relPath = ResourceUtils.getRelativePath(base, target, "\\");
		assertEquals(expected, relPath);
	}

	@Test
	public void testGetRelativePathDifferentDriveLetters() {
		String target = "D:\\sources\\recovery\\RecEnv.exe";
		String base = "C:\\Java\\workspace\\AcceptanceTests\\Standard test data\\geo\\";

		try {
			ResourceUtils.getRelativePath(base, target, "\\");
			fail();

		} catch (PathResolutionException ex) {
			// expected exception
		}
	}
}
