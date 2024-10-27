package Files;

/**
 * FileFactory class implements method-factory design pattern
 */
public class FileFactory {
    /**
     * Method newfile(String type,int fileSize,String fileDir,String fileName)
     * <p>
     * takes given variables and returns one of the following classes:
     * Image
     * Docum
     * Media
     * SimpleFile
     *
     * @param type(String)- file type,
     * @return MyFile variable
     */
    public File newFile(FileType type, String fileDir, String fileName) {
        if (type == null)
            return null;

        return switch (type) {
            case Image -> new Image(fileDir, fileName);
            case Document -> new Document(fileDir, fileName);
            case Media -> new Media(fileDir, fileName);
            case Default -> new File(fileDir, fileName);
        };
    }
}
