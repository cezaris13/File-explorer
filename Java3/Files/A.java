package Files;
import java.util.Date;
public interface A {
    abstract String getFileDir();
    abstract String getFileName();
    abstract long getFileSize();
    abstract void setFileDir(String fileDir);
    abstract void setFileName(String fileName);
    abstract void setFileSize(long fileSize);
}
