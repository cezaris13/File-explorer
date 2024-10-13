package Files;

/**
 * FileFactory class implements method-factory design pattern
 */
public class FileFactory {
    /**
     * Method newfile(String type,intfileSize,String fileDir,String fileName)
     *
     * takes given variables and returns one of the following classes:
     * Image
     * Docum
     * Media
     * SimpleFile
     *
     * @param type(String)-    file type,
     * @param fileSize(int)
     * @param fileDir(String),
     * @param fileName(String)
     * @return MyFile variable
     */
    public MyFile newFile(String type, int fileSize, String fileDir, String fileName) {
        if (type == null)
            return null;

        return switch (type) {
            case "Image" -> new Image(fileSize, fileDir, fileName);
            case "Document" -> new Docum(fileSize, fileDir, fileName);
            case "Media" -> new Media(fileSize, fileDir, fileName);
            case "File" -> new SimpleFile(fileSize, fileDir, fileName);
            default -> null;
        };
    }
}
