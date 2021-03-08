import java.awt.Dimension;

public class test{

    public static void main(String[] args){
        Panel temp = new Panel();
        Panel temp2 = new Panel();
        System.out.println("temp");
        temp.println();
        System.out.println("temp2");
        temp2.println();
        temp.dirPath="/home";
        System.out.println("temp");
        temp.println();
        System.out.println("temp2");
        temp2.println();
        temp.setSize(100,200);
        System.out.println("temp");
        temp.println();
        temp.setX(temp.getX()+10);
        // temp.setX(temp.getX()+10);
        temp.setY(100);
        System.out.println("temp");
        temp.println();
        Panel temp3 = new Panel(1,2,3,4);
        System.out.println("temp3");
        temp3.println();
        temp3.setSize(new Dimension(423,567));
        System.out.println("temp3");
        temp3.println();
    }

}
