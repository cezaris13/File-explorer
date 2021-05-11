import Files.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
public class fileApp{
    String saveLocation="/home/pijus/Desktop/Programming_languages/Java/ThreadsFileExplorer/save.out";
    String saveFiles="/home/pijus/Desktop/Programming_languages/Java/ThreadsFileExplorer/object.out";
    CustomPanel            filePanel;
    CustomPanel            topPanel;
    CustomPanel            leftMenu=new CustomPanel();
    JFrame                 frame;
    JTree                  fileTree;
    String                 currSelected="";
    List<CustomJLabel>     fileList=new ArrayList<>();
    final JPopupMenu       rightMenu=new JPopupMenu("Edit");
    final JPopupMenu       rightFileMenu=new JPopupMenu("Edit file");
    final JPopupMenu       rightFolderMenu=new JPopupMenu("Edir dir");
    public FileFactory     fileFactory=new FileFactory();
    DefaultMutableTreeNode head;

    public fileApp(){//add folders
        frame=new JFrame("my file explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(600, 500));
        topPanel=new CustomPanel(0,0,600,35);
        topPanel.panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        CustomPanel.directory="/home/pijus/Desktop";
        File savedLocation=new File(saveLocation);
        if(savedLocation.exists()&&savedLocation.length()>0){
            try{
                DataInputStream dataIn =new DataInputStream(new BufferedInputStream(new FileInputStream(saveLocation)));
                int length=dataIn.readInt();
                byte[] data=new byte[length];
                dataIn.readFully(data);
                CustomPanel.directory=new String(data,"UTF-8");
                dataIn.close();
                ObjectInputStream objectIn= new ObjectInputStream(new FileInputStream(saveFiles));
                try{
                    fileList=(List<CustomJLabel>)objectIn.readObject();
                }
                catch(ClassNotFoundException ex){
                    System.out.println(ex);
                }
                objectIn.close();
            }
            catch(IOException ex){
                System.out.println(ex);
            }
            // try{
            //     Scanner lines=new Scanner(savedLocation);
            //     while(lines.hasNextLine()){
            //         CustomPanel.directory=lines.nextLine();
            //     }
            // }
            // catch(FileNotFoundException ex){
            //     System.out.println(ex);
            // }
        }

        head=new DefaultMutableTreeNode(CustomPanel.directory);
        JButton back=new JButton("back");
        back.addActionListener(e ->{
            int lastSlash=CustomPanel.directory.lastIndexOf('/');
            CustomPanel.directory=CustomPanel.directory.substring(0, lastSlash);//fix that it you have / stop
            if(CustomPanel.directory.equals("")){//cannot go back anymore
                CustomPanel.directory="/";
            }
            updateFiles(CustomPanel.directory);
        });
        JTextField textBox=new JTextField(CustomPanel.directory,50);
        JButton GoToDirectory=new JButton("go to directory");
        GoToDirectory.addActionListener(e ->{
            CustomPanel.directory=textBox.getText();
            updateFiles(CustomPanel.directory);

        });
        JButton saveDirectory=new JButton("save");
        saveDirectory.addActionListener(e ->{
                // (new Thread(new SaveDataThread(CustomPanel.directory,savedLocation))).start();
                SwingUtilities.invokeLater(new Runnable(){
                        public void run(){
                            try{
                                DataOutputStream dataOut =new DataOutputStream(new BufferedOutputStream(new FileOutputStream(saveLocation)));
                                byte[] data=CustomPanel.directory.getBytes("UTF-8");
                                dataOut.writeInt(data.length);
                                dataOut.write(data);
                                dataOut.close();
                                ObjectOutputStream objectOut= new ObjectOutputStream(new FileOutputStream(saveFiles));
                                objectOut.writeObject(fileList);
                                objectOut.flush();
                                objectOut.close();
                            }
                            catch(IOException ex){
                                System.out.println(ex);
                            }
                        }
                    });
                // try{
                //     DataOutputStream dataOut =new DataOutputStream(new FileOutputStream("/home/pijus/Desktop/Programming_languages/Java/ThreadsFileExplorer/save.txt"));
                //     byte[] data=CustomPanel.directory.getBytes("UTF-8");
                //     System.out.println(CustomPanel.directory);
                //     dataOut.writeInt(data.length);
                //     dataOut.write(data);
                // }
                // catch(IOException ex){
                //     System.out.println(ex);
                // }
                // try{
                //     if(savedLocation.createNewFile()){
                //         System.out.println("file created");
                //     }
                //     FileWriter writer=new FileWriter(savedLocation.getName());
                //     writer.write(CustomPanel.directory);
                //     writer.close();
                // }
                // catch(IOException ex){
                //     System.out.println(ex);
                // }
        });
        JMenuItem newF=new JMenuItem("New file");
        JMenuItem renameFile=new JMenuItem("rename file");
        JMenuItem renameDirectory=new JMenuItem("rename directory");
        JMenuItem deleteDir=new JMenuItem("delete directory");
        JMenuItem deleteFile=new JMenuItem("delete file");
        rightMenu.add(newF);
        rightFileMenu.add(renameFile);
        rightFileMenu.add(deleteFile);
        rightFolderMenu.add(renameDirectory);
        rightFolderMenu.add(deleteDir);
        frame.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e){
                    currSelected="";
                    if(e.getButton()==MouseEvent.BUTTON3 && e.getClickCount()==1){
                        rightMenu.show(frame , e.getX(), e.getY());
                    }
                }
            });
        newF.addActionListener(e ->{
            new Dialog(frame,"enter file name","new file",CustomPanel.directory);
            updateFiles(CustomPanel.directory);
        });
        renameDirectory.addActionListener(e ->{
            new Dialog(frame,"rename directory","rename directory",CustomPanel.directory,currSelected);
            updateFiles(CustomPanel.directory);
        });
        renameFile.addActionListener(e ->{
            new Dialog(frame,"rename file","rename file",CustomPanel.directory,currSelected);
            updateFiles(CustomPanel.directory);
        });
        deleteFile.addActionListener(e ->{
            new Dialog(frame,"delete file","delete file",CustomPanel.directory,currSelected);
            updateFiles(CustomPanel.directory);
        });
        deleteDir.addActionListener(e ->{
            new Dialog(frame,"delete directory","delete directory",CustomPanel.directory,currSelected);
            updateFiles(CustomPanel.directory);
        });
        frame.add(rightMenu);
        topPanel.panel.add(back);
        topPanel.panel.add(textBox);
        topPanel.panel.add(GoToDirectory);
        topPanel.panel.add(saveDirectory);
        recursiveFiles(CustomPanel.directory,"",head);
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
        CustomPanel leftMenu=new CustomPanel(0,topPanel.getYSize(),200,600,fileTree);
        filePanel=new CustomPanel(leftMenu.getXSize(),topPanel.getYSize(),420,500);
        if(fileList.size()==0){
            updateFiles(CustomPanel.directory);
        }
        else{
            filePanel.panel.removeAll();
            for(int i=0;i<fileList.size();i++){
                filePanel.panel.add(fileList.get(i));
                String name=fileList.get(i).file.getFileName();
                 final CustomJLabel tempButton=fileList.get(i);
                 fileList.get(i).addMouseListener(new MouseAdapter(){
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
                                currSelected=name;
                                rightFileMenu.show(tempButton,e.getX(),e.getY());
                            }
                        }
                        });
            }
            customLayout(fileList);
        }
        frame.add(rightFileMenu);
        frame.add(rightFolderMenu);
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
    public void updateFiles(String directory){
        File f=new File(directory);
        if(f.exists()){
            filePanel.panel.removeAll();
            if(fileList.size()>0){
                fileList.clear();
            }
            String[] list =f.list();
            int count=0;
            for(int i=0;i<list.length;i++){
                File f1=new File(directory+"/"+list[i]);
                final int tmpi=i;
                if(f1.isFile()){
                    MyFile tmpFile=new MyFile();
                    tmpFile=fileFactory.newFile(getFileType(f1.getName()),0,directory,list[i]);
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
    public void recursiveFiles(String directory,String ex,DefaultMutableTreeNode head){
        File f=new File(directory);
        if(f.exists()){
            String[] list =f.list();
            int n=list.length;
            for(int i=0;i<n;i++){
                File f1=new File(directory+"/"+list[i]);
                DefaultMutableTreeNode temp=new DefaultMutableTreeNode(directory+"/"+list[i]);
                head.add(temp);
                if(f1.isDirectory()){
                    recursiveFiles(directory+"/"+list[i],ex,temp);
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
    public String getTerminalProgram(String file){
        //fix with spaces
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
