package Files;
import java.io.File;
public class Docum extends myFile{
    private int wordCount;
    private int pages;
    private String docType;
    public Docum(){
        super();
        wordCount=0;
        pages=0;
        exProgram="";
    }
    public Docum(int fileSize,String fileDir, String fileName){
        super(fileSize,fileDir,fileName);
        int j = fileName.lastIndexOf('.');
        String extension="";
        if (j >= 0) {
            extension = fileName.substring(j+1);
        }
        docType=extension;
        if(extension.equals("pdf")){
            exProgram="okular";
        }
        else{
            exProgram="libreoffice";
        }
        wordCount=0;//change that later
        pages=0;
        File file = new File(getFileDir()+"/"+fileName);
        setFileSize(file.length());
    }
    public String getExProgram(){
        return exProgram;
    }
    public String getDocType(){
        return docType;
    }
    public int getPageCount(){
        return pages;
    }
    public int getWordCount(){
        return wordCount;
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
        docType=extension;
        if(extension.equals("pdf")){
            exProgram="okular";
        }
        else{
            exProgram="libreoffice";
        }
        File file = new File(getFileDir()+"/"+fileName);
        setFileSize(file.length());
        wordCount=0;
        pages=0;
    }
    public String toString(){
        return "CreationTime:"+getCreationTime()+"\n"
            +"FileSize: "+getFileSize()+"\n"
            +"ModificationDate: "+getModificationTime()+"\n"
            +"Filedir: "+getFileDir()+"\n"
            +"Filename: "+getFileName()+"\n"
            +"wordcount:"+wordCount+"\n"
            +"pages:"+pages+"\n"
            +"exProgram: "+exProgram+"\n"
            +"docType: "+docType;
    }
}
