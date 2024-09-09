package Files;

public interface Editable extends Viewable {
    void editFile(int fileSize);

    void editFile(int fileSize, String fileDir);

    void deleteFile() throws IncorrectFileNameException, FileIsMissingException;
}
