import java.awt.Dimension;

public class Test{//is didziosios raides
    public static void main(String[] args){
        File tmp1 = new File();
        File tmp2 = new File(1024,"/home/pijus/Desktop","test.txt");
        File tmp3 = new File(512,"/home/pijus","sth.txt");
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
    }
}
