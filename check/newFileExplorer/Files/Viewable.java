package Files;

public interface Viewable {
    void openFile() throws IncorrectFileNameException, FileIsMissingException;
}
