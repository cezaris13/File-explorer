import java.nio.file.FileSystems;

public class SaveData {
    private static String getSaveDirectory() {
        String sep = FileSystems.getDefault().getSeparator();
        return System.getProperty("user.home") + sep + ".cache" + sep + "FileExplorer" + sep;
    }

    public static String getSaveLocation() {
        String directoryFile = "directory.out";
        return getSaveDirectory() + directoryFile;
    }

    public static String getSaveFiles() {
        String filesFile = "files.out";
        return getSaveDirectory() + filesFile;
    }

    public static String getSaveFolder() {
        String folderFile = "folder.out";
        return getSaveDirectory() + folderFile;
    }
}
