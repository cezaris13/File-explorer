package Files;

import java.io.File;

public class SimpleFile extends MyFile {
    private String fileType = "";

    public SimpleFile() {
        super();
        fileType = "";
    }

    public SimpleFile(int fileSize, String fileDir, String fileName) {
        super(fileSize, fileDir, fileName);
        int j = fileName.lastIndexOf('.');
        String extension = "";
        if (j >= 0) {
            extension = fileName.substring(j + 1);
        }
        fileType = extension;
        exProgram = "kate";
        File file = new File(getFileDir() + "/" + fileName);
        setFileSize(file.length());
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileName(String fileName) {
        super.setFileName(fileName);
        int j = fileName.lastIndexOf('.');
        String extension = "";
        if (j >= 0) {
            extension = fileName.substring(j + 1);
        }
        exProgram = "kate";
        fileType = extension;
        File file = new File(getFileDir() + "/" + fileName);
        setFileSize(file.length());
    }

    public String toString() {
        return "CreationTime:" + getCreationTime() + "\n"
                + "FileSize: " + getFileSize() + "\n"
                + "ModificationDate: " + getModificationTime() + "\n"
                + "Filedir: " + getFileDir() + "\n"
                + "Filename: " + getFileName() + "\n"
                + "exProgram: " + exProgram + "\n"
                + "fileType: " + fileType;
    }
}
