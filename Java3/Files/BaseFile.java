package Files;
public interface BaseFile {
    String getFileDir();
    String getFileName();
    long getFileSize();
    void setFileDir(String fileDir);
    void setFileName(String fileName);
    void setFileSize(long fileSize);
}
