package Files;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.nio.file.FileSystems;

/**
 * Image extends MyFile and it is used for image(jpg,png) file types
 *
 */
public class Image extends MyFile {
    /**
     * Image class has additional variables:
     * PictureXSize and PictureYSize - image x and y size
     * orientation- image orientation
     * imageType- type of image(png,jpg)
     */
    int PictureXSize;
    int PictureYSize;
    int orientation;
    private String imageType;

    private final String iconPath = "./Icons/image.png";

    /**
     * Constructor Image()
     *
     * Creates empty Image object
     * changes picture x and y size to 0
     * changes image orientation to 0
     * changes execution program to "gwenview"
     * and image type to empty string
     */
    public Image() {
        super();
        imageType = "";
        PictureXSize = 0;
        PictureYSize = 0;
        exProgram = "gwenview";
        orientation = 0;
    }

    /**
     * Constructor Image(String fileName)
     *
     * Creates New empty Image with given file name
     * assigns file name value to fileName variable
     * assigns fileIcon new icon(image file icon)
     * file name- should not include fullPath to the file)
     *
     */
    public Image(String fileName) {
        this(0, "", "");
        this.fileName = fileName;
        fileIcon = new Icon(iconPath, iconWidth, iconHeight);
    }

    /**
     * Constructor Image(int fileSize, String fileDir, String fileName)
     *
     * Creates new Image with given file size,directory, name
     * assigns file name value to fileName variable(Same with fileDir,fileSize)
     * assigns fileIcon new icon(image file icon)
     * file name-should not include fullPath to the file)
     * changes image type variables
     * changes filesize variable
     *
     * @param fileSize-file size
     * @param fileDir-      file directory,
     * @param fileName-file name
     */
    public Image(int fileSize, String fileDir, String fileName) {
        super(fileSize, fileDir, fileName);
        fileIcon = new Icon(iconPath, iconWidth, iconHeight);
        exProgram = "gwenview";
        setImageData(fileName);
    }

    /**
     * Method String setFileName(String fileName)
     *
     * sets new fileName, and changes Image type variable,
     * ads execution program and changes fileSize variable
     * orientation is set to 0
     */
    public void setFileName(String fileName) {
        super.setFileName(fileName);
        setImageData(fileName);
    }


    private void setImageData(String fileName) {
        imageType = getFileNameExtension(fileName);
        String separator = FileSystems.getDefault().getSeparator();
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(getFileDir() + separator + fileName));
            PictureXSize = bufferedImage.getWidth();
            PictureYSize = bufferedImage.getHeight();
        } catch (IOException ex) {
            System.out.println("\t" + ex);
        }
        File file = new File(getFileDir() + separator + fileName);
        setFileSize(file.length());
        orientation = 0;
    }
    /**
     * Method String getImageType()
     *
     * returns image type
     *
     * @return Image type(String)
     */
    public String getImageType() {
        return imageType;
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
     * Method toString()
     * returns a string consisting of all Image
     * variables description
     *
     * @return String of variables
     */
    public String toString() {
        return "CreationTime:" + getCreationTime() + "\n"
                + "FileSize: " + getFileSize() + "\n"
                + "ModificationDate: " + getModificationTime() + "\n"
                + "Filedir: " + getFileDir() + "\n"
                + "Filename: " + getFileName() + "\n"
                + "PictureXSize: " + PictureXSize + "\n"
                + "PictureySize: " + PictureYSize + "\n"
                + "Orientation: " + orientation + "\n"
                + "exProgram: " + exProgram + "\n"
                + "imageType: " + getImageType();
    }
}
