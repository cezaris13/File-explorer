public class Image extends File{
    int PictureXSize=0;
    int PictureYSize=0;

    public Image(){
        super();
        PictureXSize=112;
        PictureYSize=112;
        exProgram="gwenview";
    }
    public Image(int fileSize,String fileDir, String fileName){
        super(fileSize,fileDir,fileName);
        PictureXSize=112;
        PictureYSize=112;
        exProgram="gwenview";
    }

    public void println(){
        super.println();
        System.out.println("picture size:"+ PictureXSize);
    }
    public String getExProgram(){
        return exProgram;
    }
    public void setExProgram(String exProgram){
        this.exProgram=exProgram;
    }
    public String toString(){
        return getCreationTime()+" "+getFileSize()+" "+getModificationTime()+" "+getFileDir()+" "+getFileName()+" "+PictureXSize+" "+PictureYSize+" "+exProgram;
    }
}
