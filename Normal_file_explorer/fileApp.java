// import java.awt.event.*;
// import javax.swing.*;

// public class fileApp{
//         static int currx=500;
//         static int curry=600;
//         public static void main(String[] args) {
//                 JFrame f=new JFrame("Button Example");
//                 final JTextField tf=new JTextField();
//                 tf.setBounds(50,50, 150,20);
//                 JButton b=new JButton("Click Here");
//                 b.setBounds(50,100,95,30);
//                 b.addActionListener(new ActionListener(){
//                                 public void actionPerformed(ActionEvent e){
//                                         tf.setText("Welcome to Javatpoint.");
//                                         b.setBounds(900,100,95,30);
//                                 }
//                         });
//                 b.addComponentListener(new ComponentAdapter() {
//                                 @Override
//                                 public void componentResized(ComponentEvent e) {
//                                         System.out.println("Resized to " + e.getComponent().getSize());
//                                 }
//                                 @Override
//                                 public void componentMoved(ComponentEvent e) {
//                                         System.out.println("Moved to " + e.getComponent().getLocation());
//                                 }
//                         });
//                 f.add(b);f.add(tf);
//                 f.setSize(currx,curry);
//                 f.setLayout(null);
//                 f.setVisible(true);
//         }
// }
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.*;

public class fileApp {
    static int n=1;
    static String list[]=new String[100];
    static JButton[] buttons=new JButton[100];
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(100, 100));
        ImageIcon fileIcon = new ImageIcon("/home/pijus/Desktop/Java/Normal_file_explorer/file.png");
        ImageIcon folderIcon = new ImageIcon("/home/pijus/Desktop/Java/Normal_file_explorer/folder.png");
        Image image = fileIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        fileIcon= new ImageIcon(newimg);
        image = folderIcon.getImage(); // transform it
        newimg = image.getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        folderIcon = new ImageIcon(newimg);
        int folWidth  = folderIcon.getImage().getWidth(null);
        int folHeight = folderIcon.getImage().getHeight(null);
        int filWidth  = fileIcon.getImage().getWidth(null);
        int filHeight = fileIcon.getImage().getHeight(null);
        int maxWidth= (folWidth>filWidth?folWidth:filWidth);
        // File f = new File(args[0]);
        String dirpath= "/home/pijus";
        File f = new File(dirpath);
        if(f.exists()){
            list=f.list();
            n=list.length;
            buttons = new JButton[n];
            for(int i=0;i<n;i++){
                File f1= new File(dirpath+"/"+list[i]);
                if(f1.isFile()){
                    buttons[i]=new JButton(fileIcon);

                    frame.add(buttons[i]);
                }
                else if(f1.isDirectory()){
                    buttons[i]=new JButton(folderIcon);
                    frame.add(buttons[i]);
                }
            }
        }
        frame.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent componentEvent) {
                    int space=10;
                    // b.setBounds(componentEvent.getComponent().getSize().width/2,componentEvent.getComponent().getSize().height/2,95,30);
                    int x = componentEvent.getComponent().getSize().width/space;//tarpo ir aukscio konstantas kazkokias reik
                    // int y = componentEvent.getComponent().getSize().height/space;
                    int y=75;
                    while(2*x<3*maxWidth && space>0){//if space size + icon size is 1.5 times bigger than Icon size
                        space--;
                        x=componentEvent.getComponent().getSize().width/space;
                    }
                    int countx=0;
                    int county=0;
                    for(int i=0;i<n;i++){
                        if(x*countx>x*(space-1)){
                            county++;
                            countx=0;
                        }
                        File f1= new File(dirpath+"/"+list[i]);
                        if(f1.isFile()){
                            buttons[i].setBounds(countx*x,county*y,filWidth,filHeight);
                        }
                        else if(f1.isDirectory()){
                            buttons[i].setBounds(countx*x,county*y,folWidth,folHeight);
                        }
                        countx++;
                    }
                }
            });
        frame.setSize(500,500);
        frame.setVisible(true);
    }
}
