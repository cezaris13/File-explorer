import Files.*;
public class Test{//is didziosios raides
    public static void testImages(MyFile a){
        System.out.println("Image____________________________");
        System.out.println(a.toString());
        a.setFileDir("/home/pijus/Pictures/wallpapers");
        a.setFileName("bsoda.png");
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
        try{
            a.deleteFile();
        }
        catch(IncorrectFileNameException ex){
            System.out.println("Failed to delete the file. \n\t"+ex);
        }
        catch(FileIsMissingException ex){
            System.out.println("Failed to delete the file. "+ex+"\n\tFile:"+ex.getFileName()+"\n\tAt: "+ex.getFileDir()+"\n");
        }
    }
    public static void testDocument(Docum a){
        System.out.println("Document_________________________");
        //  // a.setFileDir("/home/pijus/Documents");
        // // a.setFileName("RA.odt");
        a.setFileDir("/home/pijus/Documents/Latex/Testas");
        a.setFileName("testas.pdf");
        System.out.println(a.toString());
        // a.openFile();
    }
    public static void testMedia(Media a){
        System.out.println("Media____________________________");
        System.out.println(a.toString());
        a.setFileDir("/home/pijus/Documents/Recordings");
        a.setFileName("simplescreenrecorder-2021-03-04_13.51.54.mkv");
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
        Image test = new Image();
        testImages(test);
        Docum test2 = new Docum();
        testDocument(test2);
        Media test3 = new Media();
        testMedia(test3);
    }
}
