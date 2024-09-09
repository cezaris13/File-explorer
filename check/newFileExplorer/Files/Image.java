package Files;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

public class Image extends MyFile {
    int PictureXSize;
    int PictureYSize;
    int orientation;// 0,1,2,3-rotating clock wise
    // prideti icon(myFile.java)
    private String imageType;

    public Image() {
        super();
        imageType = "";
        PictureXSize = 0;
        PictureYSize = 0;
        exProgram = "gwenview";
        orientation = 0;
    }

    public Image(String fileName) {
        this(0, "", "");
        fileIcon = new Icon("/home/pijus/Desktop/Programming_languages/Java/ThreadsFileExplorer/Files/image.png", 65,
                65);
    }

    public Image(int fileSize, String fileDir, String fileName) {
        super(fileSize, fileDir, fileName);
        fileIcon = new Icon("/home/pijus/Desktop/Programming_languages/Java/ThreadsFileExplorer/Files/image.png", 65,
                65);
        exProgram = "gwenview";
        int j = fileName.lastIndexOf('.');
        String extension = "";
        if (j >= 0) {
            extension = fileName.substring(j + 1);// something.txt -> txt
        }
        imageType = extension;
        try {
            BufferedImage bimg = ImageIO.read(new File(getFileDir() + "/" + fileName));
            PictureXSize = bimg.getWidth();
            PictureYSize = bimg.getHeight();
        } catch (IOException ex) {
            System.out.println("\t" + ex);
        }
        File file = new File(fileDir + "/" + fileName);
        setFileSize(file.length());
        orientation = 0;
    }

    public void setFileName(String fileName) {
        super.setFileName(fileName);
        int j = fileName.lastIndexOf('.');
        String extension = "";
        if (j >= 0) {
            extension = fileName.substring(j + 1);// something.txt -> txt
        }
        imageType = extension;
        try {
            BufferedImage bimg = ImageIO.read(new File(getFileDir() + "/" + fileName));
            PictureXSize = bimg.getWidth();
            PictureYSize = bimg.getHeight();
        } catch (IOException ex) {
            System.out.println("\t" + ex);
        }
        File file = new File(getFileDir() + "/" + fileName);
        setFileSize(file.length());
        orientation = 0;
    }

    public String getImageType() {
        return imageType;
    }

    public void rotateImage() {
        int tmp = PictureXSize;
        PictureXSize = PictureYSize;
        PictureYSize = tmp;
        orientation = (orientation + 1 == 4 ? 0 : orientation + 1);
        // add actual image orientation
    }

    public void setExProgram(String exProgram) {
        this.exProgram = exProgram;
    }

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
