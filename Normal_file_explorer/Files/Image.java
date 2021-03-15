import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Image extends myFile{
    int PictureXSize;
    int PictureYSize;
    //prideti icon(myFile.java)
    private String imageType;
    public Image(){
        super();
        imageType="";
        PictureXSize=0;
        PictureYSize=0;
        exProgram="gwenview";
    }
    public Image(int fileSize,String fileDir, String fileName){
        super(fileSize,fileDir,fileName);
        exProgram="gwenview";
        int j = fileName.lastIndexOf('.');
        String extension="";
        if (j >= 0) {
            extension = fileName.substring(j+1);//something.txt -> txt
        }
        imageType=extension;
        try{
            BufferedImage bimg = ImageIO.read(new File(getFileDir()+"/"+fileName));
            PictureXSize = bimg.getWidth();
            PictureYSize = bimg.getHeight();
        }
        catch(IOException e){
            System.out.println("something failed");
        }
        File file = new File(fileDir+"/"+fileName);
        setFileSize(file.length());
    }
    public void setFileName(String fileName){
        super.setFileName(fileName);
        int j = fileName.lastIndexOf('.');
        String extension="";
        if (j >= 0) {
            extension = fileName.substring(j+1);//something.txt -> txt
        }
        imageType=extension;
        try{
            BufferedImage bimg = ImageIO.read(new File(getFileDir()+"/"+fileName));
            PictureXSize = bimg.getWidth();
            PictureYSize = bimg.getHeight();
        }
        catch(IOException e){
            System.out.println("something failed");
        }
        File file = new File(getFileDir()+"/"+fileName);
        setFileSize(file.length());
    }
    public void println(){
        super.println();
        System.out.println("pictureXSize: "+ PictureXSize);
        System.out.println("pictureYSize: "+ PictureYSize);
        System.out.println("exProgram: "+ getExProgram());
        System.out.println("imageType: "+ imageType);
    }
    public String getExProgram(){
        return exProgram;
    }
    public String getImageType(){
        return imageType;
    }
    public void setExProgram(String exProgram){
        this.exProgram=exProgram;
    }
    public String toString(){
        return "CreationTime:"+getCreationTime()+"\n"
            +"FileSize: "+getFileSize()+"\n"
            +"ModificationDate: "+getModificationTime()+"\n"
            +"Filedir: "+getFileDir()+"\n"
            +"Filename: "+getFileName()+"\n"
            +"PictureXSize: "+PictureXSize+"\n"
            +"PictureySize: "+PictureYSize+"\n"
            +"exProgram: "+exProgram+"\n"
            +"imageType: "+getImageType();
    }
}
