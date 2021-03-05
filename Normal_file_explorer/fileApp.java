import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import java.awt.Rectangle;
import java.util.*;
import java.awt.Component;
import com.formdev.flatlaf.FlatDarkLaf;
public class fileApp {
    List<JLabel> buttons    = new ArrayList<JLabel>();
    Icon         fileIcon   = new Icon("/home/pijus/Desktop/Java/Normal_file_explorer/file.png",65,65);//folder + directory icons
    Icon         folderIcon = new Icon("/home/pijus/Desktop/Java/Normal_file_explorer/folder.png",65,65);
    Panel        filePanel;
    Panel        topPanel;
    JFrame       frame;
    JTree        fileTree;
    Panel        leftMenu=new Panel();
    int          maxWidth = (folderIcon.width>fileIcon.width?folderIcon.width:fileIcon.width);
    String curSelected="";
    DefaultMutableTreeNode head;
    final JPopupMenu popupMenu = new JPopupMenu("Edit");
    final JPopupMenu rightFileMenu = new JPopupMenu("Edit file");
    final JPopupMenu rightDirMenu = new JPopupMenu("Edir dir");
    public fileApp(){
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        frame = new JFrame("my file explorer");//frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(600, 500));
        topPanel=new Panel(0,0,600,30);
        topPanel.panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        Panel.dirpath="/home/pijus/Desktop";
        head=new DefaultMutableTreeNode(Panel.dirpath);
        JButton back=new JButton("go back");
        // back.setBounds(0,0,100,topPanel.ySize);
        back.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    System.out.println("back back back");//todo back
                    int lastIndxDot = Panel.dirpath.lastIndexOf('/');
                    Panel.dirpath=Panel.dirpath.substring(0, lastIndxDot);//fix that it you have / stop
                    if(Panel.dirpath.equals("")){
                        Panel.dirpath="/";
                    }
                    updateFiles(Panel.dirpath);
                    // recursiveFiles(Panel.dirpath,"",head);
                    // if(leftMenu!=null){
                    //     leftMenu.panel.removeAll();
                    //     leftMenu.scrollbar=new JScrollPane(fileTree);
                    //     leftMenu.panel.add(leftMenu.scrollbar);
                    // }
                }
            });

        JMenuItem newF = new JMenuItem("New file");
        JMenuItem newD = new JMenuItem("New directory");
        JMenuItem renameFile = new JMenuItem("rename file");
        JMenuItem renameDirectory = new JMenuItem("rename directory");
        JMenuItem deleteDir = new JMenuItem("delete directory");
        JMenuItem deleteFile = new JMenuItem("delete file");

        popupMenu.add(newF);
        popupMenu.add(newD);
        rightFileMenu.add(renameFile);
        rightFileMenu.add(deleteFile);
        rightDirMenu.add(renameDirectory);
        rightDirMenu.add(deleteDir);
        frame.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    curSelected="";
                    if (e.getModifiers() == MouseEvent.BUTTON3_MASK && e.getClickCount() == 1) {
                        popupMenu.show(frame , e.getX(), e.getY());
                    }
                }
            });
        newF.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    System.out.println("new file");
                    new Dialog(frame,"enter file name","new file",Panel.dirpath);
                    updateFiles(Panel.dirpath);
                }
            });
        newD.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    System.out.println("new Directory");
                    new Dialog(frame,"enter directory name","new directory",Panel.dirpath);
                    updateFiles(Panel.dirpath);
                }
            });
        renameDirectory.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    System.out.println("rename Directory");
                    new Dialog(frame,"rename directory","rename directory",Panel.dirpath,curSelected);
                    updateFiles(Panel.dirpath);
                }
            });
        renameFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    System.out.println("rename file");
                    new Dialog(frame,"rename file","rename file",Panel.dirpath,curSelected);
                    updateFiles(Panel.dirpath);
                }
            });
        deleteFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    System.out.println("delete file");
                    new Dialog(frame,"delete file","delete file",Panel.dirpath,curSelected);
                    updateFiles(Panel.dirpath);
                }
            });
        deleteDir.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    System.out.println("delete directory");
                    new Dialog(frame,"delete directory","delete directory",Panel.dirpath,curSelected);
                    updateFiles(Panel.dirpath);
                }
            });
        JTextField textBox= new JTextField("enter file or directory");
        // textBox.setBounds(500,0,300,topPanel.ySize);
        JButton newFile=new JButton("new file");
        fileManagement files=new fileManagement();
        // newFile.setBounds(100,0,100,topPanel.ySize);
        newFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    System.out.println("new File");//todo back
                    files.createFile(textBox.getText(),Panel.dirpath);
                    updateFiles(Panel.dirpath);
                }
            });
        JButton delFile=new JButton("delete file");
        // delFile.setBounds(200,0,100,topPanel.ySize);
        delFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    System.out.println("delete file");//todo back
                    files.deleteFile(textBox.getText(),Panel.dirpath);
                    updateFiles(Panel.dirpath);
                }
            });
        JButton newDir=new JButton("new dir");
        // newDir.setBounds(300,0,100,topPanel.ySize);
        newDir.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    files.createDirectory(textBox.getText(),Panel.dirpath);
                    System.out.println("new Dir");//todo back
                    updateFiles(Panel.dirpath);
                }
            });
        JButton delDir=new JButton("delete dir");
        // delDir.setBounds(400,0,100,topPanel.ySize);
        delDir.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    System.out.println("delete dir");//todo back
                    files.deleteDirectory(textBox.getText(),Panel.dirpath);
                    updateFiles(Panel.dirpath);
                }
            });
        frame.add(popupMenu);
        topPanel.panel.add(back);
        topPanel.panel.add(newFile);
        topPanel.panel.add(delFile);
        topPanel.panel.add(newDir);
        topPanel.panel.add(delDir);
        topPanel.panel.add(textBox);
        recursiveFiles(Panel.dirpath,"",head);
        fileTree=new JTree(head);
        fileTree.addMouseListener(new MouseAdapter() {
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
        updateFiles(Panel.dirpath);
        frame.add(rightFileMenu);
        frame.add(rightDirMenu);
        // JScrollPane scrollbar = new JScrollPane(filePanel);//add scrollbar
        // scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(leftMenu.panel);
        frame.add(filePanel.panel);//add panels
        frame.add(topPanel.panel);
        filePanel.setSize(500,500);
        // frame.getContentPane().add(filePanel.scrollbar);
        // filePanel.add(scrollbar);
        frame.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent componentEvent) {
                    int xx=componentEvent.getComponent().getSize().width;
                    int yy=componentEvent.getComponent().getSize().height;
                    customLayout(buttons);
                    leftMenu.setSize(leftMenu.xSize,yy-30);
                    topPanel.setSize(xx, 30);
                }
            });
        frame.setSize(1050,650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void updateFiles(String dirpath){
        // filePanel.panel.setLayout(new GridLayout(1,2,3,4));
        File f = new File(dirpath);
        if(f.exists()){
            filePanel.panel.removeAll();
            buttons.clear();
            String list[]=f.list();
            for(int i=0;i<list.length;i++){
                File f1= new File(dirpath+"/"+list[i]);
                final int tmpi = i;
                if(f1.isFile()){
                    JLabel temp=new JLabel(list[i],fileIcon.icon,JLabel.CENTER);
                    temp.setVerticalTextPosition(JLabel.BOTTOM);
                    temp.setHorizontalTextPosition(JLabel.CENTER);
                    buttons.add(temp);
                    filePanel.panel.add(temp);
                    final JLabel tempButton=buttons.get(i);
                    buttons.get(i).addMouseListener(new MouseAdapter(){
                            public void mouseClicked(MouseEvent e){
                                if (e.getModifiers() == MouseEvent.BUTTON1_MASK && e.getClickCount() == 2) {
                                    try{
                                        String path=list[tmpi];
                                        Process proc = Runtime.getRuntime().exec(getEx(path)+" "+dirpath+"/"+path);
                                        System.out.println("this is file. Nothing to do for now file: "+list[tmpi]);
                                    }
                                    catch(IOException ex){
                                        System.out.println("something went wrong");
                                    }
                                }
                                if (e.getModifiers() == MouseEvent.BUTTON3_MASK && e.getClickCount() == 1) {
                                    curSelected=list[tmpi];
                                    System.out.println("right click menu File");
                                    rightFileMenu.show(tempButton , e.getX(), e.getY());
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
                    final JLabel tempButton=buttons.get(i);
                    buttons.get(i).addMouseListener(new MouseAdapter(){
                            public void mouseClicked(MouseEvent e){
                                if (e.getModifiers() == MouseEvent.BUTTON1_MASK && e.getClickCount() == 2) {
                                    Rectangle r = frame.getBounds();
                                    int x = r.width;
                                    int y = r.height+1;
                                    int cx=frame.getLocation().x;
                                    int cy=frame.getLocation().y;
                                    frame.setSize(x,y);
                                    frame.setSize(x,y-1);
                                    frame.setLocation(cx,cy);
                                    System.out.println("this is directory "+list[tmpi]);
                                    updateFiles(dirpath+"/"+list[tmpi]);
                                    Panel.dirpath=dirpath+"/"+list[tmpi];
                                }
                                if (e.getModifiers() == MouseEvent.BUTTON3_MASK && e.getClickCount() == 1) {
                                    curSelected=list[tmpi];
                                    System.out.println("right click menu ");
                                    System.out.println(e.getX()+" "+e.getY());
                                    rightDirMenu.show(tempButton , e.getX(), e.getY());
                                }
                            }
                        });
                }
            }
            customLayout(buttons);
            filePanel.panel.revalidate();
            filePanel.panel.repaint();
        }
    }
    public static void main(String[] args) {
        fileApp GUI= new fileApp();
    }
    public void recursiveFiles(String dirpath,String ex,DefaultMutableTreeNode head){
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
    public String getEx(String file){
        // it does not work with spaces : (
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
    public void customLayout(List<JLabel> buttons){
        int space=20;
        Rectangle r = frame.getBounds();
        int x = r.width/space;
        int xx = r.width;
        int yy = r.height;
        int y=85;
        while(3*x<4*maxWidth && space>0){//if space size + icon size is 1.5 times bigger than Icon size
            space--;
            x=r.width/space;
        }
        int countx=0;
        int county=0;
        int initSpace=25;
        filePanel.panel.setLayout(null);
        for(int i=0;i<buttons.size();i++){
            if(x*(countx+1)>x*(space-3)){
                county++;
                countx=0;
            }
            File f1= new File(Panel.dirpath+"/"+buttons.get(i).getText());
            if(f1.isFile()){
                buttons.get(i).setBounds(countx*x+initSpace,county*y+initSpace,fileIcon.width+20,fileIcon.height+20);
            }
            else if(f1.isDirectory()){
                buttons.get(i).setBounds(countx*x+initSpace,county*y+initSpace,folderIcon.width+20,folderIcon.height+20);
            }
            countx++;
        }
        // int newY=county*(folderIcon.height>fileIcon.height?folderIcon.height:fileIcon.height);
        // filePanel.panel.setSize(xx-leftMenu.xSize-20,(newY>yy?newY:yy));
        filePanel.panel.setSize(xx-leftMenu.xSize-20,yy);
        filePanel.panel.revalidate();
        // filePanel.panel.validate();
        filePanel.panel.repaint();
    }
}
