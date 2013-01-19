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
