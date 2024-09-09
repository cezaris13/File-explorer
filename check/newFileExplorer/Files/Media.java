package Files;

import java.io.File;

public class Media extends MyFile {
    private int duration;
    private String mediaType;

    public Media() {
        super();
        duration = 0;
        mediaType = "";
    }

    public Media(String fileName) {
        this(0, "", "");
        fileIcon = new Icon("/home/pijus/Desktop/Programming_languages/Java/ThreadsFileExplorer/Files/media.png", 65,
                65);
    }

    public Media(int fileSize, String fileDir, String fileName) {
        super(fileSize, fileDir, fileName);
        fileIcon = new Icon("/home/pijus/Desktop/Programming_languages/Java/ThreadsFileExplorer/Files/media.png", 65,
                65);
        int j = fileName.lastIndexOf('.');
        String extension = "";
        if (j >= 0) {
            extension = fileName.substring(j + 1);
        }
        mediaType = extension;
        exProgram = "vlc";
        File file = new File(getFileDir() + "/" + fileName);
        setFileSize(file.length());
    }

    public int getDuration() {
        return duration;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setExProgram(String exProgram) {
        this.exProgram = exProgram;
    }

    public void setFileName(String fileName) {
        super.setFileName(fileName);
        int j = fileName.lastIndexOf('.');
        String extension = "";
        if (j >= 0) {
            extension = fileName.substring(j + 1);
        }
        exProgram = "vlc";
        mediaType = extension;
        File file = new File(getFileDir() + "/" + fileName);
        setFileSize(file.length());
    }

    public String toString() {
        return "CreationTime:" + getCreationTime() + "\n"
                + "FileSize: " + getFileSize() + "\n"
                + "ModificationDate: " + getModificationTime() + "\n"
                + "Filedir: " + getFileDir() + "\n"
                + "Filename: " + getFileName() + "\n"
                + "duration:" + duration + "\n"
                + "exProgram: " + exProgram + "\n"
                + "mediaType: " + mediaType;
    }
}
