package Files;

import Exceptions.*;

/**
 * editable interface extends Viewable interface by giving the access to edit
 * file(delete, edit)
 *
 */
public interface IEditable extends IViewable {
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
