package Files;

import java.io.File;
import java.nio.file.FileSystems;

/**
 * Media extends MyFile, and it is used for Media(mp3,mp4) file types
 */
public class Media extends MyFile {
    /**
     * Media class has additional variables:
     * duration-media file type duration
     * mediaType- type of media(mp3,mp4)
     */
    private int duration;
    private String mediaType;

    private final String iconPath = "./Icons/media.png";

    /**
     * Constructor Media()
     * <p>
     * Creates empty Media object
     * changes media duration to 0
     * and media type to empty string
     */
    public Media() {
        super();
        duration = 0;
        mediaType = "";
    }

    /**
     * Constructor Media(String fileName)
     * <p>
     * Creates New empty Media with given file name
     * assigns file name value to fileName variable
     * assigns fileIcon new icon(media file icon)
     * file name-should not include fullPath to the file)
     */
    public Media(String fileName) {
        this(0, "", "");
        this.fileName = fileName;
        fileIcon = new Icon(iconPath, iconWidth, iconHeight);
    }

    /**
     * Constructor Media(int fileSize, String fileDir, String fileName)
     * <p>
     * Creates New Media with given file size,directory, name
     * assigns file name value to fileName variable(Same with fileDir,fileSize)
     * assigns fileIcon new icon(media file icon)
     * file name-should not include fullPath to the file)
     * changes execution program and media type variables
     * changes filesize variable
     */
    public Media(int fileSize, String fileDir, String fileName) {
        super(fileSize, fileDir, fileName);
        fileIcon = new Icon(iconPath, iconWidth, iconHeight);
        setMediaData(fileName);
    }

    /**
     * Method void setExProgram(String exProgram)
     * <p>
     * Changes execution program
     *
     * @param exProgram- execution program
     */
    public void setExProgram(String exProgram) {
        this.exProgram = exProgram;
    }

    /**
     * Method String setFileName(String fileName)
     * <p>
     * sets new fileName, and changes media type variable,
     * ads execution program and changes fileSize variable
     */
    public void setFileName(String fileName) {
        super.setFileName(fileName);
        setMediaData(fileName);
    }

    /**
     * Method toString()
     * returns a string consisting of all Media
     * variables description
     *
     * @return String of variables
     */
    @Override
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

    private void setMediaData(String fileName) {
        mediaType = getFileNameExtension(fileName);
        exProgram = "vlc";
        String separator = FileSystems.getDefault().getSeparator();
        File file = new File(getFileDir() + separator + fileName);
        setFileSize(file.length());
    }
}
