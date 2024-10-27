import UI.*;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.JPopupMenu;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;

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
    private final FileTree fileTree = new FileTree();

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
        this.filePanel = new CustomPanel(leftMenu.getXSize(), topPanel.getYSize(), 420, 500);
        if (FileManagement.fileList.isEmpty()) {
            fileExplorerCallback.updateFiles();
            return;
        }

        filePanel.panel.removeAll();
        for (CustomJLabel customJLabel : FileManagement.fileList) {
            filePanel.panel.add(customJLabel);
            fileExplorerCallback.addMouseListener(customJLabel, customJLabel.file.getFileName());
        }
        for (CustomJLabelFolders customJLabelFolders : FileManagement.folderList) {
            filePanel.panel.add(customJLabelFolders);
            fileExplorerCallback.addMouseListener(customJLabelFolders, CustomPanel.directory, customJLabelFolders.getName());
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
        JButton saveDirectory = new JButton("save");
        saveDirectory.addActionListener(e -> {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
                        DataOutputStream dataOut = new DataOutputStream(
                                new BufferedOutputStream(new FileOutputStream(SaveData.getSaveLocation())));
                        byte[] data = CustomPanel.directory.getBytes(StandardCharsets.UTF_8);
                        dataOut.writeInt(data.length);
                        dataOut.write(data);
                        dataOut.close();
                        ObjectOutputStream filesOut = new ObjectOutputStream(new FileOutputStream(SaveData.getSaveFiles()));
                        filesOut.writeObject(FileManagement.fileList);
                        filesOut.flush();
                        filesOut.close();
                        ObjectOutputStream foldersOut = new ObjectOutputStream(new FileOutputStream(SaveData.getSaveFolder()));
                        foldersOut.writeObject(FileManagement.folderList);
                        foldersOut.flush();
                        foldersOut.close();
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            });
        });
        return saveDirectory;
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
}
