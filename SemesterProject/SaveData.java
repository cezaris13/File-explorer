public class SaveData {
    private static String directoryFile = "directory.out";
    private static String folderFile = "folder.out";
    private static String filesFile = "files.out";

    private static String getSaveDirectory() {
        String sep = System.getProperty("file.separator");
        return System.getProperty("user.home") + sep + ".cache" + sep + "FileExplorer" + sep;
    }

    public static String getSaveLocation() {
        return getSaveDirectory() + directoryFile;
    }

    public static String getSaveFiles() {
        return getSaveDirectory() + filesFile;
    }

    public static String getSaveFolder() {
        return getSaveDirectory() + folderFile;
    }
}
