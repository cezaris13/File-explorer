package Files;

/**
 * Image extends MyFile, and it is used for image(jpg,png) file types
 */
public class Image extends File {
    /**
     * Constructor Image(int fileSize, String fileDir, String fileName)
     * <p>
     * Creates new Image with given file size,directory, name
     * assigns file name value to fileName variable(Same with fileDir,fileSize)
     * assigns fileIcon new icon(image file icon)
     * file name-should not include fullPath to the file)
     * changes image type variables
     * changes filesize variable
     *
     * @param fileDir-file  directory,
     * @param fileName-file name
     */
    public Image(String fileDir, String fileName) {
        super(fileDir, fileName);
        String iconPath = "./Icons/image.png";
        icon = new Icon(iconPath, iconWidth, iconHeight);
        exProgram = "gwenview";
    }
}
