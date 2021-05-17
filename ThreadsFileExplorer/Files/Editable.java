package Files;
/**
 * editable interface extends Viewable interface by giving the access to edit file(delete, edit)
 *
 * @throws IncorrectFileNameException, FileIsMissingException
 *  */
public interface Editable extends Viewable{
    void editFile(int fileSize);
    void editFile(int fileSize,String fileDir);
    void deleteFile() throws IncorrectFileNameException,FileIsMissingException;
}
