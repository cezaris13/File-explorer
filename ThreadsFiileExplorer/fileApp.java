import Files.*;

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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class fileApp{
    Panel                  filePanel;
    Panel                  topPanel;
    Panel                  leftMenu=new Panel();
    JFrame                 frame;
    JTree                  fileTree;
    String                 currSelected="";
    List<CustomJLabel>     fileList= new ArrayList<>();
    final JPopupMenu       rightMenu=new JPopupMenu("Edit");
    final JPopupMenu       rightFileMenu=new JPopupMenu("Edit file");
    final JPopupMenu       rightDirMenu=new JPopupMenu("Edir dir");
    public FileFactory     fileFactory=new FileFactory();
    DefaultMutableTreeNode head;
    public fileApp(){//add folders
        frame=new JFrame("my file explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(600, 500));
        topPanel=new Panel(0,0,600,30);
        topPanel.panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        Panel.dirPath="/home/pijus/Desktop";
        head=new DefaultMutableTreeNode(Panel.dirPath);
        JButton back=new JButton("go back");
        back.addActionListener(e ->{
            int lastSlash=Panel.dirPath.lastIndexOf('/');
            Panel.dirPath=Panel.dirPath.substring(0, lastSlash);//fix that it you have / stop
            if(Panel.dirPath.equals("")){//cannot go back anymore
                Panel.dirPath="/";
            }
            updateFiles(Panel.dirPath);
        });
        JTextField textBox= new JTextField("directory");
        JButton goToDir=new JButton("go to dir");
        goToDir.addActionListener(e ->{
            Panel.dirPath=textBox.getText();
            updateFiles(Panel.dirPath);

        });
        JMenuItem newF=new JMenuItem("New file");
        JMenuItem renameFile=new JMenuItem("rename file");
        JMenuItem renameDirectory=new JMenuItem("rename directory");
        JMenuItem deleteDir=new JMenuItem("delete directory");
        JMenuItem deleteFile=new JMenuItem("delete file");
        rightMenu.add(newF);
        rightFileMenu.add(renameFile);
        rightFileMenu.add(deleteFile);
        rightDirMenu.add(renameDirectory);
        rightDirMenu.add(deleteDir);
        frame.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e){
                    currSelected="";
                    if(e.getButton()==MouseEvent.BUTTON3 && e.getClickCount()==1){
                        rightMenu.show(frame , e.getX(), e.getY());
                    }
                }
            });
        newF.addActionListener(e ->{
            new Dialog(frame,"enter file name","new file",Panel.dirPath);
            updateFiles(Panel.dirPath);
        });
        renameDirectory.addActionListener(e ->{
            new Dialog(frame,"rename directory","rename directory",Panel.dirPath,currSelected);
            updateFiles(Panel.dirPath);
        });
        renameFile.addActionListener(e ->{
            new Dialog(frame,"rename file","rename file",Panel.dirPath,currSelected);
            updateFiles(Panel.dirPath);
        });
        deleteFile.addActionListener(e ->{
            new Dialog(frame,"delete file","delete file",Panel.dirPath,currSelected);
            updateFiles(Panel.dirPath);
        });
        deleteDir.addActionListener(e ->{
            new Dialog(frame,"delete directory","delete directory",Panel.dirPath,currSelected);
            updateFiles(Panel.dirPath);
        });
        frame.add(rightMenu);
        topPanel.panel.add(back);
        topPanel.panel.add(textBox);
        topPanel.panel.add(goToDir);
        recursiveFiles(Panel.dirPath,"",head);
        fileTree=new JTree(head);
        fileTree.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e){
                    if(e.getClickCount()==2){
                        DefaultMutableTreeNode node =(DefaultMutableTreeNode)
                            fileTree.getLastSelectedPathComponent();
                        if(node==null){
                            return;
                        }
                        if(node.isLeaf()){
                            try{
                                String path=node.toString();
                                DefaultMutableTreeNode tempNode=new DefaultMutableTreeNode(node);
                                Process proc=Runtime.getRuntime().exec(getTerminalProgram(path)+" "+path);
                            }
                            catch(IOException ex){
                                System.out.println(ex);
                            }
                        }
                    }
                }
            });
        Panel leftMenu=new Panel(0,topPanel.getYSize(),200,600,fileTree);
        filePanel=new Panel(leftMenu.getXSize(),topPanel.getYSize(),420,500);
        updateFiles(Panel.dirPath);
        frame.add(rightFileMenu);
        frame.add(rightDirMenu);
        frame.add(leftMenu.panel);
        frame.add(filePanel.panel);
        frame.add(topPanel.panel);
        filePanel.setSize(500,500);
        frame.addComponentListener(new ComponentAdapter(){
                public void componentResized(ComponentEvent componentEvent){
                    int xx=componentEvent.getComponent().getSize().width;
                    int yy=componentEvent.getComponent().getSize().height;
                    customLayout(fileList);
                    leftMenu.setSize(leftMenu.getXSize(),yy-30);
                    topPanel.setSize(xx,30);
                }
            });
        frame.setSize(1050,650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void updateFiles(String dirPath){
        File f=new File(dirPath);
        if(f.exists()){
            filePanel.panel.removeAll();
            if(fileList.size()>0){
                fileList.clear();
            }
            String[] list =f.list();
            int count=0;
            for(int i=0;i<list.length;i++){
                File f1=new File(dirPath+"/"+list[i]);
                final int tmpi=i;
                if(f1.isFile()){
                    MyFile tmpFile=new MyFile();
                    tmpFile=fileFactory.newFile(getFileType(f1.getName()),0,dirPath,list[i]);
                    CustomJLabel tmp=new CustomJLabel(list[i],tmpFile.fileIcon.getIcon(), JLabel.CENTER,tmpFile);
                    tmp.setVerticalTextPosition(JLabel.BOTTOM);
                    tmp.setHorizontalTextPosition(JLabel.CENTER);
                    fileList.add(tmp);
                    filePanel.panel.add(tmp);
                    final CustomJLabel tempButton=fileList.get(count);
                    fileList.get(count).addMouseListener(new MouseAdapter(){
                        public void mouseClicked(MouseEvent e){
                            if(e.getButton()==MouseEvent.BUTTON1&&e.getClickCount()==2){
                                try{
                                    tempButton.file.openFile(tempButton.file.exProgram);
                                }
                                catch(FileIsMissingException ex){
                                    System.out.println(ex);
                                }
                            }
                            if(e.getButton()==MouseEvent.BUTTON3&&e.getClickCount()==1){
                                currSelected=list[tmpi];
                                rightFileMenu.show(tempButton,e.getX(),e.getY());
                            }
                        }
                        });
                    count++;
                }
            }
            customLayout(fileList);
            filePanel.panel.revalidate();
            filePanel.panel.repaint();
            if(fileList.size()>0){
                Collections.sort(fileList,new Comparator<CustomJLabel>(){
                        public int compare(final CustomJLabel object1,final CustomJLabel object2){
                            return object1.file.getFileName().compareTo(object2.file.getFileName());
                        }
                    });
            }
        }
    }
    public static void main(String[] args){
        new fileApp();
    }
    public void recursiveFiles(String dirPath,String ex,DefaultMutableTreeNode head){
        File f=new File(dirPath);
        if(f.exists()){
            String[] list =f.list();
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
    public String getFileType(String file){
        String extension="";
        int j=file.lastIndexOf('.');
        if(j>=0){
            extension=file.substring(j+1);//something.txt -> txt
        }
        if(extension.equals("txt")){
            return "File";
        }
        if(extension.equals("png")||extension.equals("jpg")){
            return "Image";
        }
        if(extension.equals("pdf")||extension.equals("tex")||extension.equals("doc")){
            return "Document";
        }
        if(extension.equals("mp3")||extension.equals("mp4")||extension.equals("mkv")){
            return "Media";
        }
        return "File";
    }
    public String getTerminalProgram(String file){//fix with spaces
        String extension="";
        int j=file.lastIndexOf('.');
        if(j>=0){
            extension=file.substring(j+1);//something.txt -> txt
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
        if(extension.equals("doc")){
            return "libreoffice";
        }
        if(extension.equals("mp4")||extension.equals("mp3")){
            return "vlc";
        }
        return "kate";
    }
    public void customLayout(List<CustomJLabel> fileList){
        int space=20;
        Rectangle r=frame.getBounds();
        int x=r.width/space;
        int xx=r.width;
        int yy=r.height;
        int y=85;
        while(3*x<4*65&&space>0){//if space size + icon size is 1.33 times bigger than Files.Icon size
            space--;
            x=r.width/space;
        }
        int countx=0;
        int county=0;
        int initSpace=25;
        filePanel.panel.setLayout(null);
        for(int i=0;i<fileList.size();i++){
            if(x*(countx+1)>x*(space-3)){
                county++;
                countx=0;
            }
            fileList.get(i).setBounds(countx*x+initSpace,county*y+initSpace,fileList.get(i).file.fileIcon.getWidth()+20,fileList.get(i).file.fileIcon.getHeight()+20);
            countx++;
        }
        filePanel.panel.setSize(xx-leftMenu.getXSize()-20,yy);
        filePanel.panel.revalidate();
        filePanel.panel.repaint();
    }
}
