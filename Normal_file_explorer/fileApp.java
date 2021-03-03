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
import java.util.*;
import com.formdev.flatlaf.FlatLightLaf;
public class fileApp {

    static List<JLabel> buttons    = new ArrayList<JLabel>();
    static Icon         fileIcon   = new Icon("/home/pijus/Desktop/Java/Normal_file_explorer/file.png",50,50);//folder + directory icons
    static Icon         folderIcon = new Icon("/home/pijus/Desktop/Java/Normal_file_explorer/folder.png",50,50);
    static Panel        filePanel;
    static Panel        topPanel;
    static JFrame       frame;
    static JTree        fileTree;
    static Panel        leftMenu;
    DefaultMutableTreeNode head;
    public fileApp(){
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        frame = new JFrame("my file explorer");//frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(600, 500));
        topPanel=new Panel(0,0,600,30);
        topPanel.dirpath="/home/pijus/Desktop";
        head=new DefaultMutableTreeNode(topPanel.dirpath);
        JButton back=new JButton("go back");
        back.setBounds(0,0,100,topPanel.ySize);
        back.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    System.out.println("back back back");//todo back
                    int lastIndxDot = topPanel.dirpath.lastIndexOf('/');
                    topPanel.dirpath=topPanel.dirpath.substring(0, lastIndxDot);
                    updateFiles(filePanel.dirpath);
                    recursiveFiles(topPanel.dirpath,"",head);
                    if(leftMenu!=null){
                        leftMenu.panel.removeAll();
                        leftMenu.scrollbar=new JScrollPane(fileTree);
                        leftMenu.panel.add(leftMenu.scrollbar);
                    }
                }
            });
        topPanel.panel.add(back);
        recursiveFiles(topPanel.dirpath,"",head);
        fileTree=new JTree(head);
        fileTree.addMouseListener(new MouseAdapter() {//double click on node(todo)
                public void mouseClicked(MouseEvent e){
                    if (e.getClickCount() == 2) {
                        System.out.println("paspaude");
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                            fileTree.getLastSelectedPathComponent();
                        if (node == null){
                            return;
                        }
                        if(node.isLeaf()){
                            try{//todo png images etc
                                String path=node.toString();
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
                                Process proc = Runtime.getRuntime().exec(getEx(path)+" "+path);
                            }
                            catch(IOException ex){
                                System.out.println("something went wrong");
                            }
                        }
                    }
                }
            });
        Panel leftMenu=new Panel(0,topPanel.ySize,200,600,fileTree);//change later
        filePanel = new Panel(leftMenu.xSize,topPanel.ySize,new Dimension(420,500));
        int    maxWidth   = (folderIcon.width>fileIcon.width?folderIcon.width:fileIcon.width);
        updateFiles(filePanel.dirpath);

        // JScrollPane scrollbar = new JScrollPane(filePanel);//add scrollbar
        // scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(leftMenu.panel);

        frame.add(filePanel.panel);//add panels
        frame.add(topPanel.panel);

        filePanel.setSize(500,500);
        frame.getContentPane().add(filePanel.scrollbar);
        // filePanel.add(scrollbar);
        frame.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent componentEvent) {
                    int space=20;
                    System.out.println(filePanel.dirpath);
                    int x =componentEvent.getComponent().getSize().width/space;//tarpo ir aukscio konstantas kazkokias reik
                    int xx=componentEvent.getComponent().getSize().width;
                    int yy=componentEvent.getComponent().getSize().height;
                    leftMenu.setSize(leftMenu.xSize,yy-30);
                    topPanel.setSize(xx, 30);
                    int y=75;
                    while(3*x<4*maxWidth && space>0){//if space size + icon size is 1.5 times bigger than Icon size
                        space--;
                        x=componentEvent.getComponent().getSize().width/space;
                    }
                    int countx=0;
                    int county=0;
                    int initSpace=10;
                    // filePanel.panel.setLayout(new GridLayout(5,5,20,20));
                    // filePanel.panel.setLayout(null);
                    for(int i=0;i<buttons.size();i++){// change to list length
                        if(x*(countx+1)>x*(space-3)){
                            county++;
                            countx=0;
                        }
                        File f1= new File(filePanel.dirpath+"/"+buttons.get(i).getText());
                        if(f1.isFile()){
                            buttons.get(i).setBounds(countx*x+initSpace,county*y+initSpace,fileIcon.width+20,fileIcon.height+20);
                        }
                        else if(f1.isDirectory()){
                            buttons.get(i).setBounds(countx*x+initSpace,county*y+initSpace,folderIcon.width+20,folderIcon.height+20);
                        }
                        countx++;
                    }
                    int newY=county*(folderIcon.height>fileIcon.height?folderIcon.height:fileIcon.height);
                    filePanel.panel.setSize(xx-leftMenu.xSize-20,(newY>yy?newY:yy));
                    // filePanel.panel.setPreferredSize(new Dimension(xx-80-20,(newY>yy?newY:yy)));
                    // filePanel.panel.repaint();
                    // filePanel.panel.revalidate();
                    // scrollbar.revalidate();
                    // scrollbar.repaint();
                }
            });
        frame.setSize(1050,650);
        frame.setVisible(true);
    }
    public static void updateFiles(String dirpath){
        filePanel.panel.removeAll();
        buttons.clear();
        File f = new File(dirpath);
        if(f.exists()){
            String list[]=f.list();
            System.out.println("n is:"+list.length);//cia
            for(int i=0;i<list.length;i++){
                File f1= new File(dirpath+"/"+list[i]);
                final Integer innerMi = new Integer(i);
                if(f1.isFile()){
                    JLabel temp=new JLabel(list[i],fileIcon.icon,JLabel.CENTER);
                    temp.setVerticalTextPosition(JLabel.BOTTOM);
                    temp.setHorizontalTextPosition(JLabel.CENTER);
                    buttons.add(temp);
                    filePanel.panel.add(temp);
                    buttons.get(i).addMouseListener(new MouseAdapter(){
                            public void mouseClicked(MouseEvent e){
                                try{
                                    String path=list[innerMi];
                                    Process proc = Runtime.getRuntime().exec(getEx(path)+" "+dirpath+"/"+path);
                                    System.out.println("this is file. Nothing to do for now file: "+list[innerMi]);
                                }
                                catch(IOException ex){
                                    System.out.println("something went wrong");
                                }
                            }
                        });
                }
                else if(f1.isDirectory()){
                    JLabel temp=new JLabel(list[i],folderIcon.icon,JLabel.CENTER);
                    temp.setVerticalTextPosition(JLabel.BOTTOM);
                    temp.setHorizontalTextPosition(JLabel.CENTER);
                    buttons.add(temp);
                    filePanel.panel.add(buttons.get(i));
                    buttons.get(i).addMouseListener(new MouseAdapter(){
                            public void mouseClicked(MouseEvent e){
                                System.out.println("this is directory "+list[innerMi]);
                                updateFiles(dirpath+"/"+list[innerMi]);
                                filePanel.dirpath=dirpath+"/"+list[innerMi];
                                // DefaultMutableTreeNode temp = new DefaultMutableTreeNode(dirpath+"/"+list[innerMi]);
                                // recursiveFiles(dirpath+"/"+list[innerMi],"",temp);
                                // JTree tempTree = new JTree(temp);
                                // fileTree=tempTree;
                            }
                        });
                }
            }
            //add refresh to layout
        }
        // if(leftMenu!=null){
        //     leftMenu.panel.revalidate();
        //     leftMenu.panel.repaint();
        // }
        filePanel.panel.revalidate();
        filePanel.panel.repaint();
        // frame.add(filePanel.panel);//add panels
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
                if(f1.isDirectory()){
                    recursiveFiles(dirpath+"/"+list1[i],ex,temp);
                }
            }
        }
        else{
            System.out.println("Directory not found");
        }
    }
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
    public static String getEx(String file){// it does not work with spaces : (
                                            // add more extensions
        String extension = "";
        int j = file.lastIndexOf('.');
        if (j >= 0) {
            extension = file.substring(j+1);//something.txt -> txt
        }
        if(extension.equals("txt")){
            return "kate";
        }
        if(extension.equals("png")||extension.equals("jpg")){
            return "gwenview";
        }
        if(extension.equals("pdf")){
            return "okular";
        }
        if(extension.equals("tex")){
            return "kile";
        }
        return "kate";
    }

}
