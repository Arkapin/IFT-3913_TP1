import java.io.File;

/**
 * Object to hold path related info
 * */
public class PathInfo {
	
	private String path;
	private String name; // file or directory name
	private boolean isDirectory;

	/**
	 * @return {@link String} of the path of the file/directory
	 * */
	public String getPath() {
		return path;
	}

	/**
	 * @return {@link String} of the name of the file/directory
	 * */
	public String getName() {
		return name;
	}

	/**
	 * @return {@link Boolean True} if this PathInfo represents a directory
	 * */
	public boolean isDirectory() {
		return isDirectory;
	}

	/**
	 * @return {@link Boolean True} if this PathInfo represents a file
	 * */
	public boolean isFile() {
		return !isDirectory;
	}

	/**
	 * Constructor
	 * @param f {@link File} from which to construct this PathInfo
	 * */
	public PathInfo(File f) {
		path = f.getAbsolutePath();
		isDirectory = f.isDirectory();
		name = f.getName();
	}
}
