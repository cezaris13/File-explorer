package Files;

/**
 * Media extends MyFile, and it is used for Media(mp3,mp4) file types
 */
public class Media extends File {
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
    public Media(String fileDir, String fileName) {
        super(fileDir, fileName);
        String iconPath = "./Icons/media.png";
        icon = new Icon(iconPath, iconWidth, iconHeight);
        exProgram = "vlc";
    }
}
