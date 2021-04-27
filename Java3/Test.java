import java.util.ArrayList;
import java.util.Date;
import Files.*;

public class Test{//is didziosios raides
    public static void testImages(MyFile a){
        System.out.println("Image____________________________");
        System.out.println(a.toString());
        // a.setFileDir("/home/pijus/Pictures/wallpapers");
        // a.setFileName("bsod.png");
        // System.out.printlnn(a.toString());
        //
        // try{
        //     a.openFile();
        // }
        // catch(IncorrectFileNameException ex){
        //     System.out.printlnn("\t"+ex+"\n\t"+ex.getMessage()+"\n");
        // }
        // catch(FileIsMissingException ex){
        //     System.out.printlnn("Failed to open file. "+ex+"\n\tFile: "+ex.getFileName()+"\n");
        // }
        //
        // try{
        //     a.deleteFile();
        // }
        // catch(IncorrectFileNameException ex){
        //     System.out.printlnn("Failed to delete the file. \n\t"+ex);
        // }
        // catch(FileIsMissingException ex){
        //     System.out.printlnn("Failed to delete the file. "+ex+"\n\tFile:"+ex.getFileName()+"\n\tAt: "+ex.getFileDir()+"\n");
        // }
    }
    public static void testDocument(Docum a){
        System.out.println("Document_________________________");
         // a.setFileDir("/home/pijus/Documents");
        // a.setFileName("RA.odt");
        a.setFileDir("/home/pijus/Documents/Latex/Testas");
        a.setFileName("testas.pdf");
        System.out.println(a.toString());
        try{
            a.openFile();
        }
        catch(IncorrectFileNameException ex){
            System.out.println("\t"+ex+"\n\t"+ex.getMessage()+"\n");
        }
        catch(FileIsMissingException ex){
            System.out.println("Failed to open file. "+ex+"\n\tFile: "+ex.getFileName()+"\n");
        }
    }
    public static void testMedia(Media a){
        System.out.println("Media____________________________");
        System.out.println(a.toString());
        a.setFileDir("/home/pijus/Documents");
        a.setFileName("simplescreenrecorder-2021-04-16_15.53.50.mkv");
        System.out.println(a.toString());
        try{
            a.openFile();
        }
        catch(IncorrectFileNameException ex){
            System.out.println("\t"+ex+"\n\t"+ex.getMessage()+"\n");
        }
        catch(FileIsMissingException ex){
            System.out.println("Failed to open file. "+ex+"\n\tFile: "+ex.getFileName()+"\n");
        }
    }
    public static void main(String[] args){
        FileFactory fileFactory=new FileFactory();
        // Image test = new Image();
        // test.setFileDir("/home/pijus/Pictures/wallpapers");
        // test.setFileName("bsod.png");
        // testImages(test);
        Folder tmp=new Folder();
        Folder tmpCloned=new Folder();

        MyFile testA = new MyFile();
        testA.setFileDir("/home/pijus/Pictures/wallpapers");
        testA.setFileName("bsod.png");
        tmp.files.add(testA);
        // MyFile testCloned=new MyFile();
        try{
            // testCloned=(MyFile)testA.clone();
            tmpCloned=(Folder)tmp.clone();
        }
        catch(CloneNotSupportedException ex){
            System.out.println(ex);
        }

        tmpCloned.files.get(0).exProgram="kate";
        tmpCloned.files.get(0).setFileSize(1234);
        System.out.println("cloned");
        for(int i=0;i<tmpCloned.files.size();i++){
            System.out.println(tmpCloned.files.get(i).toString());
        }
        System.out.println("original");
        for(int i=0;i<tmp.files.size();i++){
            System.out.println(tmp.files.get(i).toString());
        }
    }
}
