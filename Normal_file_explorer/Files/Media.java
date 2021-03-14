public class Media extends myFile{
    private int duration;
    private String mediaType;
    public Media(){
        super();
        duration=0;
    }
    public Media(int fileSize,String fileDir, String fileName){
        super(fileSize,fileDir,fileName);
        int j = fileName.lastIndexOf('.');
        String extension="";
        if (j >= 0) {
            extension = fileName.substring(j+1);
        }
        mediaType=extension;
        exProgram="vlc";
    }
    public int getDuration(){
        return duration;
    }
    public String getExProgram(){
        return exProgram;
    }
    public String getMediaType(){
        return mediaType;
    }
    public void println(){
        super.println();
        System.out.println("duration: "+ duration);
        System.out.println("Mediatype: "+ mediaType);
        System.out.println("exProgram: "+ getExProgram());
    }
    public void setExProgram(String exProgram){
        this.exProgram=exProgram;
    }
    public void setFileName(String fileName){
        super.setFileName(fileName);
        int j = fileName.lastIndexOf('.');
        String extension="";
        if (j >= 0) {
            extension = fileName.substring(j+1);
        }
        exProgram="vlc";
        mediaType=extension;
    }
    public String toString(){
        return "CreationTime:"+getCreationTime()+"\n"
            +"FileSize: "+getFileSize()+"\n"
            +"ModificationDate: "+getModificationTime()+"\n"
            +"Filedir: "+getFileDir()+"\n"
            +"Filename: "+getFileName()+"\n"
            +"duration:"+duration+"\n"
            +"exProgram: "+exProgram+"\n"
            +"mediaType: "+mediaType;
    }
}
