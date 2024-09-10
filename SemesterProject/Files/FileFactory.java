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
        if (type == null) {
            return null;
        } else if (type == "Image") {
            return new Image(fileSize, fileDir, fileName);
        } else if (type == "Document") {
            return new Docum(fileSize, fileDir, fileName);
        } else if (type == "Media") {
            return new Media(fileSize, fileDir, fileName);
        } else if (type == "File") {
            return new SimpleFile(fileSize, fileDir, fileName);
        }
        return null;
    }
}
