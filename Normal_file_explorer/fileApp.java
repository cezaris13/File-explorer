import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;

import com.formdev.flatlaf.FlatDarkLaf;
public class fileApp {
    Icon                   fileIcon   = new Icon("/home/pijus/Desktop/Java/Normal_file_explorer/file.png",65,65);//folder + directory icons
    Icon                   folderIcon = new Icon("/home/pijus/Desktop/Java/Normal_file_explorer/folder.png",65,65);
    Panel                  filePanel;
    Panel                  topPanel;
    Panel                  leftMenu=new Panel();
    JFrame                 frame;
    JTree                  fileTree;
    int                    maxWidth = (folderIcon.getWidth()>fileIcon.getWidth()?folderIcon.getWidth():fileIcon.getWidth());//delete later, because icon size is equal now
    String                 currSelected="";
    List<JLabel>           buttons    = new ArrayList<JLabel>();
    final JPopupMenu       rightMenu = new JPopupMenu("Edit");
    final JPopupMenu       rightFileMenu = new JPopupMenu("Edit file");
    final JPopupMenu       rightDirMenu = new JPopupMenu("Edir dir");
    DefaultMutableTreeNode head;
    public fileApp(){
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        frame = new JFrame("my file explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(600, 500));
        topPanel = new Panel(0,0,600,30);
        topPanel.panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        Panel.dirPath = "/home/pijus/Desktop";
        head = new DefaultMutableTreeNode(Panel.dirPath);
        JButton back = new JButton("go back");
        back.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    System.out.println("going back");//todo tree view
                    int lastSlash = Panel.dirPath.lastIndexOf('/');
                    Panel.dirPath = Panel.dirPath.substring(0, lastSlash);//fix that it you have / stop
                    if(Panel.dirPath.equals("")){//cannot go back anymore
                        Panel.dirPath = "/";
                    }
                    updateFiles(Panel.dirPath);
                    // recursiveFiles(Panel.dirPath,"",head);
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
        rightMenu.add(newF);
        rightMenu.add(newD);
        rightFileMenu.add(renameFile);
        rightFileMenu.add(deleteFile);
        rightDirMenu.add(renameDirectory);
        rightDirMenu.add(deleteDir);
        frame.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    currSelected="";
                    if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1) {
                        rightMenu.show(frame , e.getX(), e.getY());
                    }
                }
            });
        newF.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("new file");
                    new Dialog(frame,"enter file name","new file",Panel.dirPath);
                    updateFiles(Panel.dirPath);
                }
            });
        newD.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("new Directory");
                    new Dialog(frame,"enter directory name","new directory",Panel.dirPath);
                    updateFiles(Panel.dirPath);
                }
            });
        renameDirectory.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("rename Directory");
                    new Dialog(frame,"rename directory","rename directory",Panel.dirPath,currSelected);
                    updateFiles(Panel.dirPath);
                }
            });
        renameFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("rename file");
                    new Dialog(frame,"rename file","rename file",Panel.dirPath,currSelected);
                    updateFiles(Panel.dirPath);
                }
            });
        deleteFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("delete file");
                    new Dialog(frame,"delete file","delete file",Panel.dirPath,currSelected);
                    updateFiles(Panel.dirPath);
                }
            });
        deleteDir.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("delete directory");
                    new Dialog(frame,"delete directory","delete directory",Panel.dirPath,currSelected);
                    updateFiles(Panel.dirPath);
                }
            });
        JTextField textBox= new JTextField("enter file or directory");
        JButton newFile=new JButton("new file");
        fileManagement files=new fileManagement();
        newFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    //System.out.println("new File");//todo back
                    files.createFile(textBox.getText(),Panel.dirPath);
                    updateFiles(Panel.dirPath);
                }
            });
        JButton delFile=new JButton("delete file");
        delFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    //System.out.println("delete file");//todo back
                    files.deleteFile(textBox.getText(),Panel.dirPath);
                    updateFiles(Panel.dirPath);
                }
            });
        JButton newDir=new JButton("new dir");
        newDir.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    files.createDirectory(textBox.getText(),Panel.dirPath);
                    //System.out.println("new Dir");//todo back
                    updateFiles(Panel.dirPath);
                }
            });
        JButton delDir=new JButton("delete dir");
        delDir.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    //System.out.println("delete dir");//todo back
                    files.deleteDirectory(textBox.getText(),Panel.dirPath);
                    updateFiles(Panel.dirPath);
                }
            });
        frame.add(rightMenu);
        topPanel.panel.add(back);
        topPanel.panel.add(newFile);
        topPanel.panel.add(delFile);
        topPanel.panel.add(newDir);
        topPanel.panel.add(delDir);
        topPanel.panel.add(textBox);
        recursiveFiles(Panel.dirPath,"",head);
        fileTree=new JTree(head);
        fileTree.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    if (e.getClickCount() == 2) {
                        //System.out.println("clicked");
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                            fileTree.getLastSelectedPathComponent();
                        if (node == null){
                            return;
                        }
                        if(node.isLeaf()){
                            try{
                                String path=node.toString();
                                DefaultMutableTreeNode tempNode=new DefaultMutableTreeNode(node);
                                // getDir(node);
                                // //System.out.println(node.getPath().getParent().toString());
                                // while(!tempNode.toString().equals(dirPath)){
                                // for(int i=0;i<10;i++){
                                //     DefaultMutableTreeNode tmp=new DefaultMutableTreeNode(tempNode.getParent());
                                //     tempNode=tmp;
                                //     //System.out.println(tmp.toString()+""+tempNode.toString());
                                //     //System.out.println(path);
                                // }
                                // //System.out.println(node.getParent().toString());
                                Process proc = Runtime.getRuntime().exec(getEx(path)+" "+path);
                            }
                            catch(IOException ex){
                                System.out.println("something went wrong");
                            }
                        }
                    }
                }
            });
        Panel leftMenu=new Panel(0,topPanel.getYSize(),200,600,fileTree);
        filePanel = new Panel(leftMenu.getXSize(),topPanel.getYSize(),420,500);
        updateFiles(Panel.dirPath);
        frame.add(rightFileMenu);
        frame.add(rightDirMenu);
        // JScrollPane scrollbar = new JScrollPane(filePanel);//add scrollbar
        // scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(leftMenu.panel);
        frame.add(filePanel.panel);
        frame.add(topPanel.panel);
        filePanel.setSize(500,500);
        // frame.getContentPane().add(filePanel.scrollbar);
        // filePanel.add(scrollbar);
        frame.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent componentEvent) {
                    int xx=componentEvent.getComponent().getSize().width;
                    int yy=componentEvent.getComponent().getSize().height;
                    customLayout(buttons);
                    leftMenu.setSize(leftMenu.getXSize(),yy-30);
                    topPanel.setSize(xx, 30);
                }
            });
        frame.setSize(1050,650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void updateFiles(String dirPath){
        // filePanel.panel.setLayout(new GridLayout(1,2,3,4));
        File f = new File(dirPath);
        if(f.exists()){
            filePanel.panel.removeAll();
            buttons.clear();
            String list[]=f.list();
            for(int i=0;i<list.length;i++){
                File f1= new File(dirPath+"/"+list[i]);
                final int tmpi = i;
                if(f1.isFile()){
                    JLabel temp=new JLabel(list[i],fileIcon.getIcon(),JLabel.CENTER);
                    temp.setVerticalTextPosition(JLabel.BOTTOM);
                    temp.setHorizontalTextPosition(JLabel.CENTER);
                    buttons.add(temp);
                    filePanel.panel.add(buttons.get(i));
                    final JLabel tempButton=buttons.get(i);
                    buttons.get(i).addMouseListener(new MouseAdapter(){
                            public void mouseClicked(MouseEvent e){
                                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                                    try{
                                        String path = list[tmpi];
                                        Runtime.getRuntime().exec(getEx(path)+" "+dirPath+"/"+path);
                                        System.out.println("this is file: "+list[tmpi]);
                                    }
                                    catch(IOException ex){
                                        System.out.println("something went wrong");
                                    }
                                }
                                if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1) {
                                    currSelected=list[tmpi];
                                    System.out.println("right click menu File");
                                    rightFileMenu.show(tempButton , e.getX(), e.getY());
                                }
                            }
                        });
                }
                else if(f1.isDirectory()){
                    JLabel temp=new JLabel(list[i],folderIcon.getIcon(),JLabel.CENTER);
                    temp.setVerticalTextPosition(JLabel.BOTTOM);
                    temp.setHorizontalTextPosition(JLabel.CENTER);
                    buttons.add(temp);
                    filePanel.panel.add(buttons.get(i));
                    final JLabel tempButton=buttons.get(i);
                    buttons.get(i).addMouseListener(new MouseAdapter(){
                            public void mouseClicked(MouseEvent e){
                                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                                    Rectangle r = frame.getBounds();
                                    int x = r.width;
                                    int y = r.height+1;
                                    int cx = frame.getLocation().x;
                                    int cy = frame.getLocation().y;
                                    frame.setSize(x,y);
                                    frame.setSize(x,y-1);
                                    frame.setLocation(cx,cy);
                                    System.out.println("this is directory "+list[tmpi]);
                                    updateFiles(dirPath+"/"+list[tmpi]);
                                    Panel.dirPath=dirPath+"/"+list[tmpi];
                                }
                                if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1) {
                                    currSelected=list[tmpi];
                                    System.out.println("right click menu dir");
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
        new fileApp();
    }
    public void recursiveFiles(String dirPath,String ex,DefaultMutableTreeNode head){
        File f = new File(dirPath);
        if(f.exists()){
            String list[]=f.list();
            int n=list.length;
            for(int i=0;i<n;i++){
                File f1= new File(dirPath+"/"+list[i]);
                DefaultMutableTreeNode temp=new DefaultMutableTreeNode(dirPath+"/"+list[i]);
                head.add(temp);
                if(f1.isDirectory()){
                    recursiveFiles(dirPath+"/"+list[i],ex,temp);
                }
            }
        }
        else{
            System.out.println("Directory not found");
        }
    }
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
        int y = 85;
        while(3*x<4*maxWidth && space>0){//if space size + icon size is 1.33 times bigger than Icon size
            space--;
            x = r.width/space;
        }
        int countx = 0;
        int county = 0;
        int initSpace = 25;
        filePanel.panel.setLayout(null);
        for(int i=0;i<buttons.size();i++){
            if(x*(countx+1)>x*(space-3)){
                county++;
                countx=0;
            }
            File f1= new File(Panel.dirPath+"/"+buttons.get(i).getText());
            if(f1.isFile()){
                buttons.get(i).setBounds(countx*x+initSpace,county*y+initSpace,fileIcon.getWidth()+20,fileIcon.getHeight()+20);
            }
            else if(f1.isDirectory()){
                buttons.get(i).setBounds(countx*x+initSpace,county*y+initSpace,folderIcon.getWidth()+20,folderIcon.getHeight()+20);
            }
            countx++;
        }
        filePanel.panel.setSize(xx-leftMenu.getXSize()-20,yy);
        filePanel.panel.revalidate();
        // filePanel.panel.validate();
        filePanel.panel.repaint();
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
}
