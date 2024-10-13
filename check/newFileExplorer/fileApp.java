import Files.*;
import Files.Panel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import com.formdev.flatlaf.FlatDarkLaf;
public class fileApp {
    Panel filePanel;
    Panel topPanel;
    Panel leftMenu = new Panel();
    JFrame frame;
    JTree fileTree;
    String currSelected = "";
    List<CustomJLabel> fileList = new ArrayList<>();
    // List<Folder> folderList = new ArrayList<Folder>();
    final JPopupMenu rightMenu = new JPopupMenu("Edit");
    final JPopupMenu rightFileMenu = new JPopupMenu("Edit file");
    final JPopupMenu rightDirMenu = new JPopupMenu("Edir dir");
    DefaultMutableTreeNode head;

    public fileApp() {
        // try {
        // UIManager.setLookAndFeel(new FlatDarkLaf());
        // }
        // catch(Exception ex){
        // System.err.println( "Failed to initialize LaF" );
        // }
        frame = new JFrame("my file explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(600, 500));
        topPanel = new Panel(0, 0, 600, 30);
        topPanel.panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        Panel.dirPath = "/home/pijus/Desktop";
        head = new DefaultMutableTreeNode(Panel.dirPath);
        JButton back = new JButton("go back");
        back.addActionListener(e -> {
            System.out.println("going back");// todo tree view
            int lastSlash = Panel.dirPath.lastIndexOf('/');
            Panel.dirPath = Panel.dirPath.substring(0, lastSlash);// fix that it you have / stop
            if (Panel.dirPath.equals("")) {// cannot go back anymore
                Panel.dirPath = "/";
            }
            updateFiles(Panel.dirPath);

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
                currSelected = "";
                if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1) {
                    rightMenu.show(frame, e.getX(), e.getY());
                }
            }
        });
        newF.addActionListener(e -> {
            // System.out.println("new file");
            new Dialog(frame, "enter file name", "new file", Panel.dirPath);
            updateFiles(Panel.dirPath);
        });
        newD.addActionListener(e -> {
            // System.out.println("new Directory");
            new Dialog(frame, "enter directory name", "new directory", Panel.dirPath);
            updateFiles(Panel.dirPath);
        });
        renameDirectory.addActionListener(e -> {
            // System.out.println("rename Directory");
            new Dialog(frame, "rename directory", "rename directory", Panel.dirPath, currSelected);
            updateFiles(Panel.dirPath);
        });
        renameFile.addActionListener(e -> {
            // System.out.println("rename file");
            new Dialog(frame, "rename file", "rename file", Panel.dirPath, currSelected);
            updateFiles(Panel.dirPath);
        });
        deleteFile.addActionListener(e -> {
            // System.out.println("delete file");
            new Dialog(frame, "delete file", "delete file", Panel.dirPath, currSelected);
            updateFiles(Panel.dirPath);
        });
        deleteDir.addActionListener(e -> {
            // System.out.println("delete directory");
            new Dialog(frame, "delete directory", "delete directory", Panel.dirPath, currSelected);
            updateFiles(Panel.dirPath);
        });
        JTextField textBox = new JTextField("enter file or directory");
        JButton newFile = new JButton("new file");
        fileManagement files = new fileManagement();
        newFile.addActionListener(e -> {
            // System.out.println("new File");//todo back
            files.createFile(textBox.getText(), Panel.dirPath);
            updateFiles(Panel.dirPath);
        });
        JButton delFile = new JButton("delete file");
        delFile.addActionListener(e -> {
            // System.out.println("delete file");//todo back
            files.deleteFile(textBox.getText(), Panel.dirPath);
            updateFiles(Panel.dirPath);
        });
        JButton newDir = new JButton("new dir");
        newDir.addActionListener(e -> {
            files.createDirectory(textBox.getText(), Panel.dirPath);
            // System.out.println("new Dir");//todo back
            updateFiles(Panel.dirPath);
        });
        JButton delDir = new JButton("delete dir");
        delDir.addActionListener(e -> {
            // System.out.println("delete dir");//todo back
            files.deleteDirectory(textBox.getText(), Panel.dirPath);
            updateFiles(Panel.dirPath);
        });
        frame.add(rightMenu);
        topPanel.panel.add(back);
        topPanel.panel.add(newFile);
        topPanel.panel.add(delFile);
        topPanel.panel.add(newDir);
        topPanel.panel.add(delDir);
        topPanel.panel.add(textBox);
        recursiveFiles(Panel.dirPath, "", head);
        fileTree = new JTree(head);
        fileTree.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // System.out.println("clicked");
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) fileTree.getLastSelectedPathComponent();
                    if (node == null) {
                        return;
                    }
                    if (node.isLeaf()) {
                        try {
                            String path = node.toString();
                            DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(node);
                            // getDir(node);
                            // //System.out.println(node.getPath().getParent().toString());
                            // while(!tempNode.toString().equals(dirPath)){
                            // for(int i=0;i<10;i++){
                            // DefaultMutableTreeNode tmp=new DefaultMutableTreeNode(tempNode.getParent());
                            // tempNode=tmp;
                            // //System.out.println(tmp.toString()+""+tempNode.toString());
                            // //System.out.println(path);
                            // }
                            // //System.out.println(node.getParent().toString());
                            Process proc = Runtime.getRuntime().exec(getEx2(path) + " " + path);
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }
        });
        Panel leftMenu = new Panel(0, topPanel.getYSize(), 200, 600, fileTree);
        filePanel = new Panel(leftMenu.getXSize(), topPanel.getYSize(), 420, 500);
        updateFiles(Panel.dirPath);
        frame.add(rightFileMenu);
        frame.add(rightDirMenu);
        frame.add(leftMenu.panel);
        frame.add(filePanel.panel);
        frame.add(topPanel.panel);
        filePanel.setSize(500, 500);
        // frame.getContentPane().add(filePanel.scrollbar);
        // filePanel.add(scrollbar);
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                int xx = componentEvent.getComponent().getSize().width;
                int yy = componentEvent.getComponent().getSize().height;
                customLayout(fileList);
                leftMenu.setSize(leftMenu.getXSize(), yy - 30);
                topPanel.setSize(xx, 30);
            }
        });
        frame.setSize(1050, 650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void updateFiles(String dirPath) {
        // filePanel.panel.setLayout(new GridLayout(1,2,3,4));
        File f = new File(dirPath);
        if (f.exists()) {
            filePanel.panel.removeAll();
            if (fileList.size() > 0) {
                fileList.clear();
            }
            String[] list = f.list();
            int count = 0;
            for (int i = 0; i < list.length; i++) {
                File f1 = new File(dirPath + "/" + list[i]);
                final int tmpi = i;
                if (f1.isFile()) {
                    CustomJLabel tmp = new CustomJLabel();
                    FileFactory fileFactory = new FileFactory();
                    tmp.file = fileFactory.newFile(getEx(f1.getName()), list[i]);
                    tmp = new CustomJLabel(list[i], tmp.file.fileIcon.getIcon(), JLabel.CENTER);
                    tmp.setVerticalTextPosition(JLabel.BOTTOM);
                    tmp.setHorizontalTextPosition(JLabel.CENTER);
                    fileList.add(tmp);
                    filePanel.panel.add(tmp);
                    System.out.println("");
                    final CustomJLabel tempButton = fileList.get(count);
                    fileList.get(count).addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                                try {
                                    String path = list[tmpi];
                                    Runtime.getRuntime().exec(getEx2(path) + " " + dirPath + "/" + path);
                                    System.out.println("this is file: " + list[tmpi]);
                                } catch (IOException ex) {
                                    System.out.println(ex);
                                }
                            }
                            if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1) {
                                currSelected = list[tmpi];
                                System.out.println("right click menu File");
                                rightFileMenu.show(tempButton, e.getX(), e.getY());
                            }
                        }
                    });
                    count++;
                }
                // else if(f1.isDirectory()){
                // JLabel temp=new JLabel(list[i],folderIcon.getIcon(),JLabel.CENTER);
                // temp.setVerticalTextPosition(JLabel.BOTTOM);
                // temp.setHorizontalTextPosition(JLabel.CENTER);
                // buttons.add(temp);
                // filePanel.panel.add(buttons.get(i));
                // final JLabel tempButton=buttons.get(i);
                // buttons.get(i).addMouseListener(new MouseAdapter(){
                // public void mouseClicked(MouseEvent e){
                // if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                // Rectangle r = frame.getBounds();
                // int x = r.width;
                // int y = r.height+1;
                // int cx = frame.getLocation().x;
                // int cy = frame.getLocation().y;
                // frame.setSize(x,y);
                // frame.setSize(x,y-1);
                // frame.setLocation(cx,cy);
                // System.out.println("this is directory "+list[tmpi]);
                // updateFiles(dirPath+"/"+list[tmpi]);
                // Panel.dirPath=dirPath+"/"+list[tmpi];
                // }
                // if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1) {
                // currSelected=list[tmpi];
                // System.out.println("right click menu dir");
                // rightDirMenu.show(tempButton , e.getX(), e.getY());
                // }
                // }
                // });
                // }
            }
            customLayout(fileList);
            filePanel.panel.revalidate();
            filePanel.panel.repaint();
            // if(buttons.size()>0){
            // Collections.sort(buttons,new Comparator<JLabel>(){
            // public int compare(final JLabel object1,final JLabel object2){
            // return object1.getName().compareTo(object2.getName());
            // }
            // });
            // }
        }
    }

    public static void main(String[] args) {
        new fileApp();
    }

    public void recursiveFiles(String dirPath, String ex, DefaultMutableTreeNode head) {
        File f = new File(dirPath);
        if (f.exists()) {
            String[] list = f.list();
            int n = list.length;
            for (int i = 0; i < n; i++) {
                File f1 = new File(dirPath + "/" + list[i]);
                DefaultMutableTreeNode temp = new DefaultMutableTreeNode(dirPath + "/" + list[i]);
                head.add(temp);
                if (f1.isDirectory()) {
                    recursiveFiles(dirPath + "/" + list[i], ex, temp);
                }
            }
        } else {
            System.out.println("Directory not found");
        }
    }

    public String getEx(String file) {
        // it does not work with spaces : (
        // add more extensions
        String extension = "";
        int j = file.lastIndexOf('.');
        if (j >= 0) {
            extension = file.substring(j + 1);// something.txt -> txt
        }
        if (extension.equals("txt")) {
            return "File";
        }
        if (extension.equals("png") || extension.equals("jpg")) {
            return "Image";
        }
        if (extension.equals("pdf") || extension.equals("tex") || extension.equals("doc")) {
            return "Document";
        }
        if (extension.equals("mp3") || extension.equals("mp4")) {
            return "Media";
        }
        return "File";
    }

    public String getEx2(String file) {
        // it does not work with spaces : (
        // add more extensions
        String extension = "";
        int j = file.lastIndexOf('.');
        if (j >= 0) {
            extension = file.substring(j + 1);// something.txt -> txt
        }
        if (extension.equals("txt")) {
            return "kate";
        }
        if (extension.equals("png") || extension.equals("jpg")) {
            return "gwenview";
        }
        if (extension.equals("pdf")) {
            return "okular";
        }
        if (extension.equals("tex")) {
            return "kile";
        }
        if (extension.equals("doc")) {
            return "libreoffice";
        }
        return "kate";
    }

    public void customLayout(List<CustomJLabel> fileList) {
        int space = 20;
        Rectangle r = frame.getBounds();
        int x = r.width / space;
        int xx = r.width;
        int yy = r.height;
        int y = 85;
        while (3 * x < 4 * 65 && space > 0) {// if space size + icon size is 1.33 times bigger than Files.Icon size
            space--;
            x = r.width / space;
        }
        int countx = 0;
        int county = 0;
        int initSpace = 25;
        filePanel.panel.setLayout(null);
        for (int i = 0; i < fileList.size(); i++) {
            if (x * (countx + 1) > x * (space - 3)) {
                county++;
                countx = 0;
            }
            fileList.get(i).setBounds(countx * x + initSpace, county * y + initSpace,
                    fileList.get(i).file.fileIcon.getWidth() + 20, fileList.get(i).file.fileIcon.getHeight() + 20);
            countx++;
        }
        // for(int i=0;i<folderList.size();i++){
        // if(x*(countx+1)>x*(space-3)){
        // county++;
        // countx=0;
        // }
        // File f1= new File(Panel.dirPath+"/"+buttons.get(i).getText());
        // System.out.println(fileIcon.getHeight());
        // if(f1.isFile()){
        // buttons.get(i).setBounds(countx*x+initSpace,county*y+initSpace,fileIcon.getWidth()+20,fileIcon.getHeight()+20);
        // }
        // else if(f1.isDirectory()){
        // buttons.get(i).setBounds(countx*x+initSpace,county*y+initSpace,folderIcon.getWidth()+20,folderIcon.getHeight()+20);
        // }
        // countx++;
        // }
        filePanel.panel.setSize(xx - leftMenu.getXSize() - 20, yy);
        filePanel.panel.revalidate();
        // filePanel.panel.validate();
        filePanel.panel.repaint();
    }
    // void getDir(DefaultMutableTreeNode temp){
    // if(temp.getParent()!=null){
    // fullPath="/"+temp.toString()+fullPath;
    // DefaultMutableTreeNode tmp=new DefaultMutableTreeNode(temp.getParent());

    // getDir(tmp);
    // }
    // else{
    // fullPath=temp.toString()+fullPath;
    // // fullPath=dir;
    // }
    // }
}
