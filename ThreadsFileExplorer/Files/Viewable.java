package Files;
/**
 * Viewable is used to give user permission to view file
 *  */
public interface Viewable {
    /**
     * openfile method takes execution program, and file name
     * and opens given file
     * @param Execution program(String), file name(String)
     * */

    void openFile(String exProgram,String fileName) throws IncorrectFileNameException,FileIsMissingException;
}
