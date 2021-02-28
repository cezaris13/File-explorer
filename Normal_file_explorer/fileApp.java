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
import javax.swing.tree.DefaultMutableTreeNode;  
import java.awt.BorderLayout;
import java.awt.AWTEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
public class fileApp {

    static int n=1;
    static String[] list;
    static JButton[] buttons;
    static ImageIcon fileIcon = new ImageIcon("/home/pijus/Desktop/Java/Normal_file_explorer/file.png");//folder + directory icons
    static ImageIcon folderIcon = new ImageIcon("/home/pijus/Desktop/Java/Normal_file_explorer/folder.png");
    static JPanel fileContainer;
    static JFrame frame;
    DefaultMutableTreeNode head;
    String fullPath="";
    public fileApp(){
        int leftMenux=200;
        int leftMenuy=600;
        frame = new JFrame("my file explorer");//frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(600, 500));

        fileContainer=new JPanel();//file panel
        // fileContainer.setBounds(80,0,420,500);
        fileContainer.setLocation(leftMenux,0);
        fileContainer.setPreferredSize(new Dimension(420,500));
        fileContainer.setLayout(null);
        fileContainer.setBackground(Color.gray);
       
      
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
        String dirpath="/home/pijus/Desktop";
        updateFiles(dirpath);


        head=new DefaultMutableTreeNode(dirpath);
        recursiveFiles(dirpath,"",head);
        JTree fileTree=new JTree(head);
        JPanel leftMenu=new JPanel();//left panel (todo later)
        leftMenu.setBounds(0,0,leftMenux,leftMenuy);
        leftMenu.setLayout(new BorderLayout());
        fileTree.addMouseListener(new MouseAdapter() {//double click on node(todo)
                public void mouseClicked(MouseEvent e){
                    if (e.getClickCount() == 2) {
                        System.out.println("paspaude");
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                            fileTree.getLastSelectedPathComponent();
                        if (node == null) return;
                        if(node.isLeaf()){
                           try{//todo png images etc
                                String path=node.toString();
                                fullPath="";
                                DefaultMutableTreeNode tempNode=new DefaultMutableTreeNode(node);

                                // getDir(node);
                                // System.out.println(node.getPath().getParent().toString());
                                // while(!tempNode.toString().equals(dirpath)){
                                // for(int i=0;i<10;i++){
                                //     y
                                //     DefaultMutableTreeNode tmp=new DefaultMutableTreeNode(tempNode.getParent());
                                //     tempNode=tmp;
                                //     System.out.println(tmp.toString()+""+tempNode.toString());
                                //     System.out.println(path);
                                // }
                                // System.out.println(node.getParent().toString());
                                Process proc = Runtime.getRuntime().exec("kate "+path);
                            }
                            catch(IOException ex){
                                System.out.println("something went wrong");
                            }
                        }
                        Object nodeInfo = node.getUserObject();
                        // Cast nodeInfo to your object and do whatever you want
                    }
                }
            });
        JScrollPane sp = new JScrollPane(fileTree);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollPane scrollbar = new JScrollPane(fileContainer);//add scrollbar
        scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        leftMenu.add(sp);
        frame.add(fileContainer);//add panels
        frame.add(leftMenu);

        fileContainer.setSize(420,500);
        frame.getContentPane().add(scrollbar);
        frame.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent componentEvent) {
                    int space=20;
                    int x = componentEvent.getComponent().getSize().width/space;//tarpo ir aukscio konstantas kazkokias reik
                    int xx=componentEvent.getComponent().getSize().width;
                    int yy=componentEvent.getComponent().getSize().height;
                    leftMenu.setSize(leftMenux,yy);
                    int y=75;
                    while(3*x<4*maxWidth && space>0){//if space size + icon size is 1.5 times bigger than Icon size
                        space--;
                        x=componentEvent.getComponent().getSize().width/space;
                    }
                    int countx=0;
                    int county=0;
                    int initSpace=10;
                    for(int i=0;i<n;i++){
                        if(x*(countx+1)>x*(space-3)){
                            county++;
                            countx=0;
                        }
                        File f1= new File(dirpath+"/"+list[i]);
                        if(f1.isFile()){
                            buttons[i].setBounds(countx*x+initSpace,county*y+initSpace,filWidth,filHeight);
                        }
                        else if(f1.isDirectory()){
                            buttons[i].setBounds(countx*x+initSpace,county*y+initSpace,folWidth,folHeight);
                        }
                        countx++;
                    }
                    int newY=county*(folHeight>filHeight?folHeight:filHeight);
                    fileContainer.setSize(xx-leftMenux,(newY>yy?newY:yy));
                    // fileContainer.setPreferredSize(new Dimension(xx-80-20,(newY>yy?newY:yy)));
                    // fileContainer.repaint();
                    // fileContainer.revalidate();
                    // scrollbar.revalidate();
                    // scrollbar.repaint();
                }
            });
        frame.setSize(500,500);
        frame.setVisible(true);
    }
    public static void updateFiles(String dirpath){
        // String dirpath= "/home/pijus/Desktop";//buttons
        File f = new File(dirpath);
        if(f.exists()){
            list=f.list();
            n=list.length;
            buttons = new JButton[n];
            int i;
            for(i=0;i<n;i++){
                File f1= new File(dirpath+"/"+list[i]);
                final Integer innerMi = new Integer(i);
                if(f1.isFile()){
                    buttons[i]=new JButton(fileIcon);
                    fileContainer.add(buttons[i]);
                    buttons[i].addActionListener(new ActionListener(){  
                        public void actionPerformed(ActionEvent e){
                            try{
                                String path=list[innerMi];
                                Process proc = Runtime.getRuntime().exec("kate "+dirpath+"/"+path);
                            }
                            catch(IOException ex){
                                System.out.println("something went wrong");
                            }
                            System.out.println("this is file. Nothing to do for now file: "+list[innerMi]);
                        }  
                        });
                }
                else if(f1.isDirectory()){
                    buttons[i]=new JButton(folderIcon);
                    fileContainer.add(buttons[i]);
                    buttons[i].addActionListener(new ActionListener(){  
                        public void actionPerformed(ActionEvent e){  
                                System.out.println("this is directory "+list[innerMi]); 
                                // updateFiles(dirpath+"/"+list[innerMi]);
                        }  
                        });
                }
            }
        }
        // frame.add(fileContainer);//add panels
    }
    public static void main(String[] args) {
     fileApp GUI= new fileApp();
    }
    public static void recursiveFiles(String dirpath,String ex,DefaultMutableTreeNode head){
        File f = new File(dirpath);
        if(f.exists()){
            String list1[]=f.list();
            int n=list1.length;
            for(int i=0;i<n;i++){
                File f1= new File(dirpath+"/"+list1[i]);
                DefaultMutableTreeNode temp=new DefaultMutableTreeNode(dirpath+"/"+list1[i]);
                head.add(temp);
                // if(f1.isFile()){
                //     String extension = "";
                //     int j = f1.getName().lastIndexOf('.');
                //     if (j >= 0) {
                //         extension = f1.getName().substring(j+1);//something.txt -> txt
                //     }
                //     if(extension.equals(ex) || ex.equals("")){
                //         System.out.print(space+list[i]);
                //         System.out.println(ANSI_RED+":this is a file"+ANSI_RESET);
                //     }
                // }
                if(f1.isDirectory()){
                    // System.out.print(space+dirpath+"/"+list[i]);
                    // System.out.println(ANSI_PURPLE+":this is a directory"+ANSI_RESET);
                    recursiveFiles(dirpath+"/"+list1[i],ex,temp);
                }
            }
            // if(n==0){
            //     System.out.println(space+"no entries in this directory");
            // }
        }
        else{
            System.out.println("Directory not found");
        }
    }
    // @Override
    // public void actionPerformed(ActionEvent arg0) {
    //     // TODO Auto-generated method stub

    // }

    //Add my recursive file search
    // void getDir(DefaultMutableTreeNode temp){
    //     if(temp.getParent()!=null){
    //         fullPath="/"+temp.toString()+fullPath;
    //         DefaultMutableTreeNode tmp=new DefaultMutableTreeNode(temp.getParent());

    //         getDir(tmp);
    //     }
    //     else{
    //         fullPath=temp.toString()+fullPath;
    //         // fullPath=dir;
    //     }
    // }
}
