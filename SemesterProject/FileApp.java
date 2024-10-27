import Files.*;
import UI.*;
import Exceptions.*;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.Comparator;
import java.util.List;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;

public class FileApp {
    FileExplorerComponents fileExplorerComponents;
    FileFactory fileFactory;
    String currSelected = "";

    private final String separator = FileSystems.getDefault().getSeparator();

    public static void main(String[] args) {
        new FileApp().setupFileApp();
    }

    public void setupFileApp() {
        checkIfSavedFileExists();
        CustomPanel.directory = System.getProperty("user.home") + separator + "Desktop";
        fileExplorerComponents = new FileExplorerComponents(new FileExplorerCallback() {
            @Override
            public void updateFiles() {
                FileApp.this.updateFiles(CustomPanel.directory);
            }

            @Override
            public void updateCurrentDirectory(String newDirectory) {
                currSelected = newDirectory;
            }

            @Override
            public String getCurrentDirectory() {
                return currSelected;
            }

            @Override
            public void addMouseListener(CustomJLabel jLabel, String fileName) {
                addFileMouseListener(jLabel, fileName);
            }

            @Override
            public void addMouseListener(CustomJLabelFolders customJLabelFolder, String directory, String folderName) {
                addFolderMouseListener(customJLabelFolder, directory, folderName);
            }
        }
        );

        fileFactory = new FileFactory();

        fileExplorerComponents.setupComponents();
    }

    public void updateFiles(String directory) {
        File f = new File(directory);
        if (!f.exists())
            return;

        fileExplorerComponents.filePanel.panel.removeAll();
        if (!FileManagement.fileList.isEmpty())
            FileManagement.fileList.clear();

        if (!FileManagement.folderList.isEmpty())
            FileManagement.folderList.clear();

        String[] list = f.list();
        int count = 0;

        if (list == null)
            return;

        for (String element : list) {
            File f1 = new File(directory + separator + element);
            if (!f1.isFile())
                continue;

            MyFile tmpFile = fileFactory.newFile(FileType.getFileType(f1.getName()), 0, directory, element);
            CustomJLabel tmp = new CustomJLabel(element, tmpFile.fileIcon.getIcon(), JLabel.CENTER, tmpFile);
            tmp.setVerticalTextPosition(JLabel.BOTTOM);
            tmp.setHorizontalTextPosition(JLabel.CENTER);
            FileManagement.fileList.add(tmp);
            fileExplorerComponents.filePanel.panel.add(tmp);
            addFileMouseListener(FileManagement.fileList.get(count), element);
            count++;
        }
        count = 0;

        for (String s : list) {
            File f1 = new File(directory + separator + s);
            if (!f1.isDirectory())
                continue;

            Icon temp = new Icon("./Icons/folder.png", 65, 65);
            CustomJLabelFolders tmp = new CustomJLabelFolders(s, temp.getIcon(), JLabel.CENTER);
            tmp.setVerticalTextPosition(JLabel.BOTTOM);
            tmp.setHorizontalTextPosition(JLabel.CENTER);
            FileManagement.folderList.add(tmp);
            fileExplorerComponents.filePanel.panel.add(tmp);
            addFolderMouseListener(FileManagement.folderList.get(count), directory, s);
            count++;
        }

        CustomLayout.revalidate(fileExplorerComponents.frame, fileExplorerComponents.leftMenu, fileExplorerComponents.filePanel, FileManagement.fileList, FileManagement.folderList);
        if (!FileManagement.fileList.isEmpty())
            FileManagement.fileList.sort(Comparator.comparing(object -> object.file.getFileName()));
    }

    private void checkIfSavedFileExists() {
        File savedLocation = new File(SaveData.getSaveLocation());
        if (!savedLocation.exists() || savedLocation.length() == 0)
            return;

        try (DataInputStream dataIn = new DataInputStream(new BufferedInputStream(new FileInputStream(SaveData.getSaveLocation())))) {
            int length = dataIn.readInt();
            byte[] data = new byte[length];
            dataIn.readFully(data);
            CustomPanel.directory = new String(data, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.println("Error reading saved location data: " + ex);
        }

        FileManagement.fileList = deserializeList(SaveData.getSaveFiles(), "file list");
        FileManagement.folderList = deserializeList(SaveData.getSaveFolder(), "folder list");
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> deserializeList(String filePath, String listType) {
        try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<T>) objectIn.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Error reading saved " + listType + ": " + ex.getMessage());
        }
        return null;
    }

    private void addFileMouseListener(CustomJLabel jLabel, String fileName) {
        jLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    try {
                        jLabel.file.openFile();
                    } catch (FileIsMissingException ex) {
                        System.out.println(ex);
                    }
                }
                if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1) {
                    currSelected = fileName;
                    fileExplorerComponents.rightFileMenu.show(jLabel, e.getX(), e.getY());
                }
            }
        });
    }

    private void addFolderMouseListener(CustomJLabelFolders customJLabelFolder, String directory, String folderName) {
        customJLabelFolder.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    int width = fileExplorerComponents.frame.getBounds().width;
                    int height = fileExplorerComponents.frame.getBounds().height + 1;
                    int x = fileExplorerComponents.frame.getLocation().x;
                    int y = fileExplorerComponents.frame.getLocation().y;
                    fileExplorerComponents.frame.setSize(width, height);
                    fileExplorerComponents.frame.setSize(width, height - 1);
                    fileExplorerComponents.frame.setLocation(x, y);
                    updateFiles(directory + separator + folderName);
                    CustomPanel.directory = directory + separator + folderName;
                }
                if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1) {
                    currSelected = folderName;
                    System.out.println("right click menu dir");
                    fileExplorerComponents.rightFolderMenu.show(customJLabelFolder, e.getX(), e.getY());
                }
            }
        });
    }
}
