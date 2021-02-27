import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
public class fileApp {

    static int n=1;
    static String list[];
    static JButton[] buttons;
    public static void main(String[] args) {
        JFrame frame = new JFrame("my file explorer");//frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(200, 500));

        JPanel fileContainer=new JPanel();//file panel
        fileContainer.setBounds(80,0,420,500);
        fileContainer.setLayout(null);
        fileContainer.setBackground(Color.gray);
        JPanel leftMenu=new JPanel();//left panel (todo later)
        leftMenu.setBounds(0,0,80,600);
        leftMenu.setLayout(null);
        leftMenu.setBackground(Color.yellow);

        ImageIcon fileIcon = new ImageIcon("/home/pijus/Desktop/Java/Normal_file_explorer/file.png");//folder + directory icons
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

        String dirpath= "/home/pijus/Desktop";//buttons
        File f = new File(dirpath);
        if(f.exists()){
            list=f.list();
            n=list.length;
            buttons = new JButton[n];
            for(int i=0;i<n;i++){
                File f1= new File(dirpath+"/"+list[i]);
                if(f1.isFile()){
                    buttons[i]=new JButton(fileIcon);

                    fileContainer.add(buttons[i]);
                }
                else if(f1.isDirectory()){
                    buttons[i]=new JButton(folderIcon);
                    fileContainer.add(buttons[i]);
                }
            }
        }

        JScrollPane scrollbar = new JScrollPane(fileContainer);//add scrollbar
        scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.add(fileContainer);//add panels
        frame.add(leftMenu);

        frame.getContentPane().add(scrollbar);

        frame.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent componentEvent) {
                    int space=20;
                    int x = componentEvent.getComponent().getSize().width/space;//tarpo ir aukscio konstantas kazkokias reik
                    int xx=componentEvent.getComponent().getSize().width;
                    int yy=componentEvent.getComponent().getSize().height;
                    leftMenu.setSize(80,yy);
                    int y=75;
                    while(2*x<3*maxWidth && space>0){//if space size + icon size is 1.5 times bigger than Icon size
                        space--;
                        x=componentEvent.getComponent().getSize().width/space;
                    }
                    int countx=0;
                    int county=0;
                    for(int i=0;i<n;i++){
                        if(x*countx>x*(space-2)){
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
                    int newY=county*(folHeight>filHeight?folHeight:filHeight);
                    fileContainer.setSize(xx-80-20,(newY>yy?newY:yy));
                }
            });
        frame.setSize(500,500);
        frame.setVisible(true);
    }
}
