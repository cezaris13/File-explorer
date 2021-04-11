import Files.*;
// add plain file class
public class Test{//is didziosios raides
    public static void testImages(MyFile tmp,MyFile a){
        System.out.println("Image____________________________");
        // a.println();
        System.out.println(tmp.toString());
        System.out.println("a1");
        System.out.println(a.toString());
        a.setFileDir("/home/pijus/Pictures/wallpapers");
        a.setFileName("bsoda.png");
        System.out.println(a.toString());
        a.openFile();
        a.deleteFile();
    }
    public static void testDocument(Docum a){
        System.out.println("Document_________________________");
        // a.println();
        // System.out.println("a1");
        // System.out.println(a.toString());
        //  // a.setFileDir("/home/pijus/Documents");
        // // a.setFileName("RA.odt");
        a.setFileDir("/home/pijus/Documents/Latex/Testas");
        a.setFileName("testas.pdf");
        System.out.println(a.toString());
        // a.openFile();
    }
    public static void testMedia(Media a){
        System.out.println("Media____________________________");
        // a.println();
        System.out.println("a1");
        System.out.println(a.toString());
        a.setFileDir("/home/pijus/Documents/Recordings");
        a.setFileName("simplescreenrecorder-2021-03-04_13.51.54.mkv");
        System.out.println(a.toString());
        // a.openFile();
    }
    public static void main(String[] args){
        MyFile tmp1 = new MyFile();
        // {
        // MyFile tmp2 = new MyFile(1024,"/home/pijus/Desktop","test.txt");
        // MyFile tmp3 = new MyFile(512,"/home/pijus","sth.txt");
        // System.out.println("tmp1");
        // // tmp1.println();
        // System.out.println("tmp2");
        // // tmp2.println();
        // System.out.println("tmp3");
        // // tmp3.println()/* ; */

        // tmp1.editFile(2048);
        // System.out.println("tmp1");
        // // tmp1.println();

        // tmp1.editFile(4096,"/home/pijus/Downloads");
        // System.out.println("tmp1");
        // // tmp1.println();

        // System.out.println("tmp3");
        // // tmp3.println();

        // tmp3.setFileDir("/");
        // System.out.println("tmp3");
        // // tmp3.println();

        // System.out.println("tmp3 tests");
        // System.out.println("file Size");
        // System.out.println(tmp3.getFileSize());
        // System.out.println("file dir");
        // System.out.println(tmp3.getFileDir());
        // System.out.println("file name");
        // System.out.println(tmp3.getFileName());
        // System.out.println("modification time");
        // System.out.println(tmp3.getFileSize());
        // System.out.println("creation time");
        // System.out.println(tmp3.getCreationTime());
        // }
        // MyFile test4 = new Image();

        // polimorfizmas
        Image test = new Image();
        // MyFile test4;//MyFile -> MyFile
        // MyFile obj = new MyFile();
        // System.out.println(obj.toString());
        // test4=test;
        // System.out.println("Test_______________________________________");
        // System.out.println(test4.toString());// uzklotas toString() metodas
        // System.out.println("");
        // System.out.println(tmp1.toString());// originali bazine klase
        // System.out.println("");
        // System.out.println("End of Test_______________________________________");
        //baigiasi
        testImages(tmp1, test);
        // Docum test2 = new Docum();
        // testDocument(test2);
        // Media test3 = new Media();
        // testMedia(test3);
        // test.rotateImage();
        // test.rotateImage();
        // test.rotateImage();
        // System.out.println("Rotation___________________________");
        // System.out.println(test.toString());
    }
}
