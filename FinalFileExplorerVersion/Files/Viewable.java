package Files;

/**
 * Viewable is used to give user permission to view file
 */
public interface Viewable {
    /**
     * openfile method takes execution program, and file name
     * and opens given file
     *
     * @param exProgram(String)
     * @param fileName(String)
     * @throws FileIsMissingException - file is missing
     */
    void openFile(String exProgram, String fileName) throws FileIsMissingException;
}
