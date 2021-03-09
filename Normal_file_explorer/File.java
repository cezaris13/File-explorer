public class File{// prideti papildomy funkciju: fileType etc.
    //vartotojas static
    private final long creationTime;// Date
    private int fileSize=0;//kb
    private long modificationTime;// dir
    private String fileDir;
    private String fileName;
    public File(){
        fileSize=0;
        fileDir="";
        fileName="";
        creationTime=System.currentTimeMillis();
        modificationTime=System.currentTimeMillis();
    }
    public File(int fileSize,String fileDir,String fileName){//this()
        this.fileSize=fileSize;
        this.fileDir=fileDir;
        this.fileName=fileName;
        creationTime=System.currentTimeMillis();
        modificationTime=System.currentTimeMillis();
    }
    public void editFile(int fileSize){
        this.fileSize=fileSize;
        modificationTime=System.currentTimeMillis();
    }
    public void editFile(int fileSize, String fileDir){
        this.fileSize=fileSize;
        this.fileDir=fileDir;
        modificationTime=System.currentTimeMillis();
    }
    public int getFileSize(){
        return fileSize;
    }
    public long getCreationTime(){
        return creationTime;
    }
    public long getModificationTime(){
        return modificationTime;
    }
    public String getFileDir(){
        return fileDir;
    }
    public void setFileDir(String fileDir){
        this.fileDir=fileDir;
    }
    public String getFileName(){
        return fileName;
    }
    public void setFileName(String fileName){
        this.fileName=fileName;
    }
    public void println(){
        System.out.println("fileSize: "+fileSize);
        System.out.println("modification time: "+modificationTime);
        System.out.println("directory: "+fileDir);
        System.out.println("File name: "+fileName);
        System.out.println("creation time: "+creationTime);
        System.out.println("");
    }
}
