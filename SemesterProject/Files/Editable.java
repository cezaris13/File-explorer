package Files;

/**
 * editable interface extends Viewable interface by giving the access to edit
 * file(delete, edit)
 *
 */
public interface Editable extends Viewable {
    /**
     *
     * @param fileSize - file size
     */
    void editFile(int fileSize);

    /**
     *
     * @param fileSize - file Size
     * @param fileDir- file directory
     */
    void editFile(int fileSize, String fileDir);

    /**
     *
     * @throws IncorrectFileNameException - incorrect name
     * @throws FileIsMissingException     - no file found
     */
    void deleteFile() throws IncorrectFileNameException, FileIsMissingException;
}
