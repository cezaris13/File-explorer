package Files;
public interface Viewable {
    void openFile(String exProgram) throws IncorrectFileNameException,FileIsMissingException;
}
