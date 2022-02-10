import java.io.File;

/** Object to hold path related info */
    public class PathInfo {
    	
    	private String path;
    	private String name; // file or directory name
    	private boolean isDirectory;
    	
    	public String getPath() {
    		return path;
    	}

    	public String getName() {
    		return name;
    	}
    	
    	public boolean isDirectory() {
    		return isDirectory;
		}
    	
    	public boolean isFile() {
    		return !isDirectory;
		}
    	
    	public PathInfo(File f) {
    		path = f.getAbsolutePath();
    		isDirectory = f.isDirectory();
			name = f.getName();
    	}
    }
