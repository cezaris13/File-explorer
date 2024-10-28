package UI;

import Files.FileManagement;
import Files.FileType;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.List;
import java.util.stream.Stream;

public class FileExplorerComponents {
    public JFrame frame = new JFrame("my file explorer");
    public CustomPanel filePanel;
    public JPopupMenu rightFileMenu = new JPopupMenu("Edit file");
    public JPopupMenu rightFolderMenu = new JPopupMenu("Edit folder");
    public CustomPanel leftMenu;
    public JRightMenu jRightMenu;

    private JButton backButton;
    private CustomPanel topPanel;
    private JPopupMenu rightMenu;
    private final FileExplorerCallback fileExplorerCallback;

    public FileExplorerComponents(FileExplorerCallback fileExplorerCallback) {
        this.fileExplorerCallback = fileExplorerCallback;
    }

    public void setupComponents() {
        FileTree fileTree = new FileTree(fileExplorerCallback.getCurrentDirectory());
        frame.setMinimumSize(new Dimension(600, 500));
        this.jRightMenu = new JRightMenu(frame, fileExplorerCallback);

        rightFileMenu = jRightMenu.createFileRightMenu();
        rightFolderMenu = jRightMenu.createFolderRightMenu();
        rightMenu = jRightMenu.createRightMenu();

        backButton = createBackButton();
        topPanel = createTopPanel();
        leftMenu = new CustomPanel(0, topPanel.getHeight(), 200, 600, fileTree.fileTree);
        createFilePanel();

        FileManagement.recursiveFiles(fileExplorerCallback.getCurrentDirectory(), fileTree.head);

        createJFrame();

        fileTree.expandAllNodes(0, fileTree.fileTree.getRowCount());
    }

    private JButton createBackButton() {
        JButton back = new JButton("back");
        String separator = FileSystems.getDefault().getSeparator();
        back.addActionListener(e -> {
            String currentDirectory = fileExplorerCallback.getCurrentDirectory();
            int lastSlash = currentDirectory.lastIndexOf(separator);
            currentDirectory = currentDirectory.substring(0, lastSlash);
            if (currentDirectory.isEmpty()) // cannot go back anymore
                currentDirectory = separator;

            fileExplorerCallback.setCurrentDirectory(currentDirectory);
            fileExplorerCallback.updateFiles();
        });
        return back;
    }

    private void createFilePanel() {
        // Initialize the filePanel with dimensions based on the leftMenu and topPanel sizes
        this.filePanel = new CustomPanel(leftMenu.getWidth(), topPanel.getHeight(), 420, 500);

        // Check if the file list is empty; if so, update files and exit early
        if (fileExplorerCallback.getFileList().isEmpty()) {
            fileExplorerCallback.updateFiles();
            return;
        }

        // Clear existing components from the panel
        filePanel.panel.removeAll();

        List<CustomJLabel> combinedJLabelList = Stream.concat(fileExplorerCallback.getFolderList().stream(), fileExplorerCallback.getFileList().stream()).toList();

        for (CustomJLabel customJLabel : combinedJLabelList) {
            filePanel.panel.add(customJLabel);
            // Add mouse listeners based on whether the label represents a file or a directory
            if (customJLabel.file.fileType == FileType.Directory)
                fileExplorerCallback.addMouseListener(customJLabel, fileExplorerCallback.getCurrentDirectory());
            else
                fileExplorerCallback.addMouseListener(customJLabel);
        }
    }

    private CustomPanel createTopPanel() {
        CustomPanel topPanel = new CustomPanel(0, 0, 600, 35);
        topPanel.panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTextField textBox = new JTextField(fileExplorerCallback.getCurrentDirectory(), 50);
        topPanel.panel.add(backButton);
        topPanel.panel.add(textBox);
        topPanel.panel.add(createGoToDirectoryButton(textBox));
        topPanel.panel.add(createSaveDirectoryButton());

        return topPanel;
    }

    private JButton createSaveDirectoryButton() {
        JButton saveDirectory = new JButton("Save");
        saveDirectory.addActionListener(e -> SwingUtilities.invokeLater(this::saveData));
        return saveDirectory;
    }

    private void saveData() {
        try {
            saveDirectoryData();
            saveFileData();
            saveFolderData();
        } catch (IOException ex) {
            System.err.println("Error saving data: " + ex.getMessage());
        }
    }

    private void saveDirectoryData() throws IOException {
        try (DataOutputStream dataOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(SaveData.getSaveLocation())))) {
            byte[] data = fileExplorerCallback.getCurrentDirectory().getBytes(StandardCharsets.UTF_8);
            dataOut.writeInt(data.length);
            dataOut.write(data);
        }
    }

    private void saveFileData() throws IOException {
        try (ObjectOutputStream filesOut = new ObjectOutputStream(new FileOutputStream(SaveData.getSaveFiles()))) {
            filesOut.writeObject(fileExplorerCallback.getFileList());
        }
    }

    private void saveFolderData() throws IOException {
        try (ObjectOutputStream foldersOut = new ObjectOutputStream(new FileOutputStream(SaveData.getSaveFolder()))) {
            foldersOut.writeObject(fileExplorerCallback.getFolderList());
        }
    }

    private JButton createGoToDirectoryButton(JTextField textBox) {
        JButton goToDirectoryButton = new JButton("go to directory");
        goToDirectoryButton.addActionListener(e -> {
            fileExplorerCallback.setCurrentDirectory(textBox.getText());
            fileExplorerCallback.updateFiles();
        });

        return goToDirectoryButton;
    }

    private void createJFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(rightMenu);
        frame.add(rightFileMenu);
        frame.add(rightFolderMenu);
        frame.add(leftMenu.panel);
        frame.add(filePanel.panel);
        frame.add(topPanel.panel);

        frame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                fileExplorerCallback.updateCurrentSelectedFile("");
                if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1)
                    rightMenu.show(frame, e.getX(), e.getY());
            }
        });
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                int width = componentEvent.getComponent().getSize().width;
                int height = componentEvent.getComponent().getSize().height;
                CustomLayout.revalidate(filePanel, fileExplorerCallback.getFileList(), fileExplorerCallback.getFolderList(), frame.getBounds().width, leftMenu.getWidth());

                topPanel.setWidth(width);
                leftMenu.setHeight(height - 30);
            }
        });

        filePanel.setWidth(500);
        frame.setSize(1050, 650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
