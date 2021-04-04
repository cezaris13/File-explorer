package Files;
import java.util.Date;
public interface ExtendedFile extends BaseFile{
    Date getCreationTime();
    Date getModificationTime();
    // void editFile();
}
