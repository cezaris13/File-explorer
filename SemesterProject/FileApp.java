import Exceptions.FileIsMissingException;
import Files.File;
import Files.FileType;
import UI.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FileApp {
    FileExplorerComponents fileExplorerComponents;
    String currentSelectedFile = "";
    List<CustomJLabel> fileList = new ArrayList<>();
    List<CustomJLabel> folderList = new ArrayList<>();
    String currentDirectory;

    private final String separator = FileSystems.getDefault().getSeparator();

    public static void main(String[] args) {
        new FileApp().setupFileApp();
    }

    public void setupFileApp() {
        currentDirectory = System.getProperty("user.home");
        checkIfSavedFileExists();
        fileExplorerComponents = new FileExplorerComponents(new FileExplorerCallback() {
            @Override
            public void updateFiles() {
                FileApp.this.updateFiles(currentDirectory);
            }

            @Override
            public void updateCurrentSelectedFile(String newSelectedFile) {
                currentSelectedFile = newSelectedFile;
            }

            @Override
            public String getCurrentSelectedFile() {
                return currentSelectedFile;
            }

            @Override
            public String getCurrentDirectory() {
                return currentDirectory;
            }

            @Override
            public void setCurrentDirectory(String newCurrentDirectory) {
                currentDirectory = newCurrentDirectory;
            }


            @Override
            public void addMouseListener(CustomJLabel jLabel) {
                addFileMouseListener(jLabel);
            }

            @Override
            public void addMouseListener(CustomJLabel jLabel, String directory) {
                addFolderMouseListener(jLabel, directory);
            }

            @Override
            public List<CustomJLabel> getFolderList() {
                return folderList;
            }

            @Override
            public List<CustomJLabel> getFileList() {
                return fileList;
            }
        }
        );

        fileExplorerComponents.setupComponents();
    }

    public void updateFiles(String directory) {
        java.io.File f = new java.io.File(directory);
        if (!f.exists())
            return;

        fileExplorerComponents.filePanel.panel.removeAll();
        if (!fileList.isEmpty())
            fileList.clear();

        if (!folderList.isEmpty())
            folderList.clear();

        String[] list = f.list();

        if (list == null)
            return;

        for (String element : list) {
            java.io.File file = new java.io.File(directory + separator + element);
            File fileCustom = new File(directory, element, file);
            CustomJLabel tmp = new CustomJLabel(element, fileCustom.icon, JLabel.CENTER, fileCustom);
            tmp.setVerticalTextPosition(JLabel.BOTTOM);
            tmp.setHorizontalTextPosition(JLabel.CENTER);
            if (fileCustom.fileType == FileType.Directory) {
                folderList.add(tmp);
                addFolderMouseListener(tmp, directory);
            } else {
                fileList.add(tmp);
                addFileMouseListener(tmp);
            }
            fileExplorerComponents.filePanel.panel.add(tmp);
        }

        if (!fileList.isEmpty())
            fileList.sort(Comparator.comparing(object -> object.file.getName()));

        CustomLayout.revalidate(fileExplorerComponents.filePanel, fileList, folderList, fileExplorerComponents.frame.getBounds().width, fileExplorerComponents.leftMenu.getWidth());
    }

    private void checkIfSavedFileExists() {
        java.io.File saveFolder = new java.io.File(SaveData.getSaveDirectory());
        if (!saveFolder.exists())
            saveFolder.mkdirs();

        java.io.File savedLocation = new java.io.File(SaveData.getSaveLocation());
        if (!savedLocation.exists() || savedLocation.length() == 0)
            return;

        try (DataInputStream dataIn = new DataInputStream(new BufferedInputStream(new FileInputStream(SaveData.getSaveLocation())))) {
            int length = dataIn.readInt();
            byte[] data = new byte[length];
            dataIn.readFully(data);
            currentDirectory = new String(data, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.println("Error reading saved location data: " + ex);
        }

        fileList = deserializeList(SaveData.getSaveFiles(), "file list");
        folderList = deserializeList(SaveData.getSaveFolder(), "folder list");
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> deserializeList(String filePath, String listType) {
        try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<T>) objectIn.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Error reading saved " + listType + ": " + ex.getMessage());
        }
        return new ArrayList<T>();
    }

    private void addFileMouseListener(CustomJLabel jLabel) {
        String fileName = jLabel.file.getName();
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
                    currentSelectedFile = fileName;
                    fileExplorerComponents.rightFileMenu.show(jLabel, e.getX(), e.getY());
                }
            }
        });
    }

    private void addFolderMouseListener(CustomJLabel customJLabel, String directory) {
        String folderName = customJLabel.file.getName();
        customJLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    updateFiles(directory + separator + folderName);
                    currentDirectory = directory + separator + folderName;
                }
                if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1) {
                    currentSelectedFile = folderName;
                    System.out.println("right click menu dir");
                    fileExplorerComponents.rightFolderMenu.show(customJLabel, e.getX(), e.getY());
                }
            }
        });
    }
}
