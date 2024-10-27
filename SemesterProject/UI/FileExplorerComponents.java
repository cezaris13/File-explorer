package UI;

import Files.FileManagement;

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

public class FileExplorerComponents {
    public JFrame frame = new JFrame("my file explorer");
    public CustomPanel filePanel;
    public JPopupMenu rightFileMenu = new JPopupMenu("Edit file");
    public JPopupMenu rightFolderMenu = new JPopupMenu("Edit folder");
    public CustomPanel leftMenu;
    public JRightMenu jRightMenu;

    private JButton backButton;
    private CustomPanel topPanel;
    private final FileExplorerCallback fileExplorerCallback;
    private JPopupMenu rightMenu;
    private final CustomPanel.FileTree fileTree = new CustomPanel.FileTree();

    public FileExplorerComponents(FileExplorerCallback fileExplorerCallback) {
        this.fileExplorerCallback = fileExplorerCallback;

    }

    public void setupComponents() {
        frame.setMinimumSize(new Dimension(600, 500));
        this.jRightMenu = new JRightMenu(frame, fileExplorerCallback);

        rightFileMenu = jRightMenu.createFileRightMenu();
        rightFolderMenu = jRightMenu.createFolderRightMenu();
        rightMenu = jRightMenu.createRightMenu();

        backButton = createBackButton();
        topPanel = createTopPanel();
        leftMenu = new CustomPanel(0, topPanel.getYSize(), 200, 600, fileTree.fileTree);
        createFilePanel();

        FileManagement.recursiveFiles(CustomPanel.directory, "", fileTree.head);

        createJFrame();
    }

    private JButton createBackButton() {
        JButton back = new JButton("back");
        String separator = FileSystems.getDefault().getSeparator();
        back.addActionListener(e -> {
            int lastSlash = CustomPanel.directory.lastIndexOf(separator);
            CustomPanel.directory = CustomPanel.directory.substring(0, lastSlash);// fix that it you have / stop
            if (CustomPanel.directory.isEmpty()) // cannot go back anymore
                CustomPanel.directory = separator;

            fileExplorerCallback.updateFiles();
        });
        return back;
    }

    private void createFilePanel() {
        // Initialize the filePanel with dimensions based on the leftMenu and topPanel sizes
        this.filePanel = new CustomPanel(leftMenu.getXSize(), topPanel.getYSize(), 420, 500);

        // Check if the file list is empty; if so, update files and exit early
        if (FileManagement.fileList.isEmpty()) {
            fileExplorerCallback.updateFiles();
            return;
        }

        // Clear existing components from the panel
        filePanel.panel.removeAll();

        // Add files to the filePanel
        addComponentsToPanel(FileManagement.fileList, false);

        // Add folders to the filePanel
        addComponentsToPanel(FileManagement.folderList, true);
    }

    private void addComponentsToPanel(List<CustomJLabel> labels, boolean isDirectory) {
        for (CustomJLabel customJLabel : labels) {
            filePanel.panel.add(customJLabel);
            // Add mouse listeners based on whether the label represents a file or a directory
            if (isDirectory) {
                fileExplorerCallback.addMouseListener(customJLabel, CustomPanel.directory, customJLabel.file.getName());
            } else {
                fileExplorerCallback.addMouseListener(customJLabel, customJLabel.file.getName());
            }
        }
    }

    private CustomPanel createTopPanel() {
        CustomPanel topPanel = new CustomPanel(0, 0, 600, 35);
        topPanel.panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTextField textBox = createDirectoryTextBox();
        topPanel.panel.add(backButton);
        topPanel.panel.add(textBox);
        topPanel.panel.add(createGoToDirectoryButton(textBox));
        topPanel.panel.add(createSaveDirectoryButton());

        return topPanel;
    }

    private JTextField createDirectoryTextBox() {
        return new JTextField(CustomPanel.directory, 50);
    }

    private JButton createSaveDirectoryButton() {
        JButton saveDirectory = new JButton("Save");
        saveDirectory.addActionListener(e -> SwingUtilities.invokeLater(() -> saveData()));
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
            byte[] data = CustomPanel.directory.getBytes(StandardCharsets.UTF_8);
            dataOut.writeInt(data.length);
            dataOut.write(data);
        }
    }

    private void saveFileData() throws IOException {
        try (ObjectOutputStream filesOut = new ObjectOutputStream(new FileOutputStream(SaveData.getSaveFiles()))) {
            filesOut.writeObject(FileManagement.fileList);
        }
    }

    private void saveFolderData() throws IOException {
        try (ObjectOutputStream foldersOut = new ObjectOutputStream(new FileOutputStream(SaveData.getSaveFolder()))) {
            foldersOut.writeObject(FileManagement.folderList);
        }
    }

    private JButton createGoToDirectoryButton(JTextField textBox) {
        JButton goToDirectoryButton = new JButton("go to directory");
        goToDirectoryButton.addActionListener(e -> {
            CustomPanel.directory = textBox.getText();
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
                fileExplorerCallback.updateCurrentDirectory("");
                if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1)
                    rightMenu.show(frame, e.getX(), e.getY());
            }
        });
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                int width = componentEvent.getComponent().getSize().width;
                int height = componentEvent.getComponent().getSize().height;
                CustomLayout.revalidate(frame, leftMenu, filePanel, FileManagement.fileList, FileManagement.folderList);
                leftMenu.setSize(leftMenu.getXSize(), height - 30);
                topPanel.setSize(width, 30);
            }
        });

        filePanel.setSize(500, 500);
        frame.setSize(1050, 650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static class JRightMenu {
        private final FileExplorerCallback fileExplorerCallback;
        private final JFrame frame;

        public JRightMenu(JFrame frame, FileExplorerCallback fileExplorerCallback) {
            this.frame = frame;
            this.fileExplorerCallback = fileExplorerCallback;
        }

        public JPopupMenu createFolderRightMenu() {
            JPopupMenu rightMenu = new JPopupMenu();
            JMenuItem renameDirectoryMenuItem = new JMenuItem("rename directory");
            JMenuItem deleteDirectoryMenuItem = new JMenuItem("delete directory");
            rightMenu.add(renameDirectoryMenuItem);
            rightMenu.add(deleteDirectoryMenuItem);
            renameDirectoryMenuItem.addActionListener(e -> {
                new Dialog(frame, "rename directory", DialogType.RenameDirectory, CustomPanel.directory, fileExplorerCallback.getCurrentDirectory());
                fileExplorerCallback.updateFiles();
            });
            deleteDirectoryMenuItem.addActionListener(e -> {
                new Dialog(frame, "delete directory", DialogType.DeleteDirectory, CustomPanel.directory, fileExplorerCallback.getCurrentDirectory());
                fileExplorerCallback.updateFiles();
            });
            return rightMenu;
        }

        public JPopupMenu createFileRightMenu() {
            JPopupMenu rightMenu = new JPopupMenu();
            JMenuItem renameFileMenuItem = new JMenuItem("rename file");
            JMenuItem deleteFileMenuItem = new JMenuItem("delete file");
            rightMenu.add(renameFileMenuItem);
            rightMenu.add(deleteFileMenuItem);

            renameFileMenuItem.addActionListener(e -> {
                new Dialog(frame, "rename file", DialogType.RenameFile, CustomPanel.directory, fileExplorerCallback.getCurrentDirectory());
                fileExplorerCallback.updateFiles();
            });
            deleteFileMenuItem.addActionListener(e -> {
                new Dialog(frame, "delete file", DialogType.DeleteFile, CustomPanel.directory, fileExplorerCallback.getCurrentDirectory());
                fileExplorerCallback.updateFiles();
            });
            return rightMenu;
        }

        public JPopupMenu createRightMenu() {
            JPopupMenu rightMenu = new JPopupMenu();
            JMenuItem newFileMenuItem = new JMenuItem("New file");
            JMenuItem newDirectoryMenuItem = new JMenuItem("New folder");
            rightMenu.add(newFileMenuItem);
            rightMenu.add(newDirectoryMenuItem);
            newFileMenuItem.addActionListener(e -> {
                new Dialog(frame, "enter file name", DialogType.NewFile, CustomPanel.directory);
                fileExplorerCallback.updateFiles();
            });
            newFileMenuItem.addActionListener(e -> {
                new Dialog(frame, "enter directory name", DialogType.NewDirectory, CustomPanel.directory);
                fileExplorerCallback.updateFiles();
            });
            return rightMenu;
        }
    }

    /**
     * UI.FileExplorerComponents.Dialog class consists of JDialog variable
     * <p>
     * it is used as a additional menu
     * possible options:
     * new File/folder menu
     * rename File/folder menu
     * delete File/folder menu
     */
    public static class Dialog {
        private final JDialog jDialog;
        private final int width = 300;
        private final int height = 150;
        private final int buttonWidth = 100;
        private final int buttonHeight = 20;
        private final int buttonSpacing = 10;
        private final int buttonPadding = 40;

        /**
         * Constructor UI.FileExplorerComponents.Dialog(JFrame frame, String title, String dialogTitle, String
         * directory ,String name)
         * <p>
         * This constructor creates new JDialog and adds to given JFrame object
         * <p>
         * this is used to as a (Rename/create)File/folder dialog window
         */
        Dialog(JFrame frame, String title, DialogType dialogType, String directory, String name) {
            jDialog = new JDialog(frame, dialogType.name(), true);
            JTextField textBox = getTextBox();
            JButton okButton = getOkButton(dialogType, name, directory, textBox);
            JButton cancelButton = getCancelButton();
            jDialog.setLayout(null);
            jDialog.add(getDialogTitle(title));
            jDialog.add(okButton);
            jDialog.add(cancelButton);
            jDialog.setSize(width, height);
            jDialog.setResizable(false);
            jDialog.setLocationRelativeTo(frame);
            if (dialogType != DialogType.RenameFile && dialogType != DialogType.RenameDirectory)
                jDialog.add(textBox);

            jDialog.setVisible(true);
        }

        /**
         * Constructor UI.FileExplorerComponents.Dialog(JFrame frame, String title, String dialogTitle, String
         * directory)
         * <p>
         * This constructor creates new JDialog and adds to given JFrame object
         * <p>
         * this is used to as a deleteFile/folder dialog window
         */
        Dialog(JFrame frame, String title, DialogType dialogType, String directory) {
            this(frame, title, dialogType, directory, "");
        }

        private JButton getOkButton(DialogType dialogType, String name, String directory, JTextField textBox) {
            JButton okButton = new JButton("OK");
            okButton.setBounds(buttonPadding, 70, buttonWidth, buttonHeight);
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    switch (dialogType) {
                        case DialogType.NewFile -> FileManagement.createFile(textBox.getText(), directory);
                        case DialogType.NewDirectory -> FileManagement.createDirectory(textBox.getText(), directory);
                        case DialogType.RenameFile -> FileManagement.renameFile(name, textBox.getText(), directory);
                        case DialogType.RenameDirectory ->
                                FileManagement.renameDirectory(name, textBox.getText(), directory);
                        case DialogType.DeleteFile -> FileManagement.deleteFile(name, directory);
                        case DialogType.DeleteDirectory -> FileManagement.deleteDirectory(name, directory);
                    }

                    jDialog.setVisible(false);
                }
            });
            return okButton;
        }

        private JButton getCancelButton() {
            JButton cancelButton = new JButton("Cancel");
            cancelButton.setBounds(buttonPadding + buttonWidth + buttonSpacing, 70, buttonWidth, buttonHeight);
            cancelButton.addActionListener(e -> jDialog.setVisible(false));
            return cancelButton;
        }

        private JLabel getDialogTitle(String title) {
            JLabel dialogTitle = new JLabel(title, SwingConstants.CENTER);
            dialogTitle.setBounds(0, 10, 300, 20);
            return dialogTitle;
        }

        private JTextField getTextBox() {
            JTextField textBox = new JTextField("");
            textBox.setBounds(50, 40, 200, 20);
            return textBox;
        }
    }
}
