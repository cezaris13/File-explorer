package Files;

import java.io.File;

/**
 * Media extends MyFile and it is used for Media(mp3,mp4) file types
 */
public class Media extends MyFile {
    /**
     * Media class has additional variables:
     * duration- media file type duration
     * mediaType- type of media(mp3,mp4)
     */
    private int duration;
    private String mediaType;

    private String iconPath = "./Icons/media.png";

    /**
     * Constructor Media()
     *
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
     *
     * Creates New empty Media with given file name
     * assigns file name value to fileName variable
     * assigns fileIcon new icon(media file icon)
     * file name- should not include fullPath to the file)
     *
     * @param fileName(String)
     */
    public Media(String fileName) {
        this(0, "", "");
        this.fileName = fileName;
        fileIcon = new Icon(iconPath, iconWidth, iconHeight);
    }

    /**
     * Constructor Media(int fileSize, String fileDir, String fileName)
     *
     * Creates New Media with given file size,directory, name
     * assigns file name value to fileName variable(Same with fileDir,fileSize)
     * assigns fileIcon new icon(media file icon)
     * file name-should not include fullPath to the file)
     * changes execution program and media type variables
     * changes filesize variable
     *
     * @param fileSize(int)
     * @param fileDir(String)
     * @param fileName(String)
     */
    public Media(int fileSize, String fileDir, String fileName) {
        super(fileSize, fileDir, fileName);
        fileIcon = new Icon(iconPath, iconWidth, iconHeight);
        setMediaData(fileName);
    }

    /**
     * Method int getDuration()
     *
     * returns duration time
     *
     * @return creation time(Date)
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Method String getMediaType()
     *
     * returns Media type
     *
     * @return media type(String)
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * Method void setExProgram(String exProgram)
     *
     * Changes execution program
     *
     * @param exProgram- execution program
     */
    public void setExProgram(String exProgram) {
        this.exProgram = exProgram;
    }

    /**
     * Method String setFileName(String fileName)
     *
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
        mediaType = getFileNameExtension(fileName);:with
        exProgram = "vlc";
        File file = new File(getFileDir() + "/" + fileName);
        setFileSize(file.length());
    }
}
