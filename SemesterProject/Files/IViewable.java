package Files;

import Exceptions.*;

/**
 * Viewable is used to give user permission to view file
 */
public interface IViewable {
    /**
     * openfile method takes execution program, and file name
     * and opens given file
     *
     * @throws FileIsMissingException - file is missing
     */
    void openFile() throws FileIsMissingException;
}
