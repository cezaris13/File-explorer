import java.util.concurrent.TimeUnit;

import javax.swing.text.Document;

import java.io.IOException;



public class Test{//is didziosios raides
    public static void testImages(myFile tmp,Image a){
        a.println();
        System.out.println(tmp.toString());
        System.out.println("a1");
        System.out.println(a.toString());
        a.setFileDir("/home/pijus/Pictures/wallpapers");
        a.setFileName("bsod.png");
        System.out.println(a.toString());
        a.openProgram();
    }
    public static void testDocument(Docum a){
        a.println();
        System.out.println("a1");
        System.out.println(a.toString());
         // a.setFileDir("/home/pijus/Documents");
        // a.setFileName("RA.odt");
        a.setFileDir("/home/pijus/Documents/Latex/Testas");
        a.setFileName("testas.pdf");
        System.out.println(a.toString());
        a.openProgram();
    }
    public static void testMedia(Media a){
        a.println();
        System.out.println("a1");
        System.out.println(a.toString());
        a.setFileDir("/home/pijus/Documents/Recordings");
        a.setFileName("simplescreenrecorder-2021-03-04_13.51.54.mkv");
        System.out.println(a.toString());
        a.openProgram();
    }
    public static void main(String[] args){
        myFile tmp1 = new myFile();
        myFile tmp2 = new myFile(1024,"/home/pijus/Desktop","test.txt");
        myFile tmp3 = new myFile(512,"/home/pijus","sth.txt");
        System.out.println("tmp1");
        tmp1.println();
        System.out.println("tmp2");
        tmp2.println();
        System.out.println("tmp3");
        tmp3.println();

        tmp1.editFile(2048);
        System.out.println("tmp1");
        tmp1.println();

        tmp1.editFile(4096,"/home/pijus/Downloads");
        System.out.println("tmp1");
        tmp1.println();

        System.out.println("tmp3");
        tmp3.println();

        tmp3.setFileDir("/");
        System.out.println("tmp3");
        tmp3.println();

        System.out.println("tmp3 tests");
        System.out.println("file Size");
        System.out.println(tmp3.getFileSize());
        System.out.println("file dir");
        System.out.println(tmp3.getFileDir());
        System.out.println("file name");
        System.out.println(tmp3.getFileName());
        System.out.println("modification time");
        System.out.println(tmp3.getFileSize());
        System.out.println("creation time");
        System.out.println(tmp3.getCreationTime());
        // Image test = new Image();
        // testImages(tmp1, test);
        // Docum test2 = new Docum();
        // testDocument(test2);
        Media test3 = new Media();
        testMedia(test3);
    }
}
