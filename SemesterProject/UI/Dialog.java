package UI;

import Files.FileManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * UI.FileExplorerComponents.Dialog class consists of JDialog variable
 * <p>
 * it is used as an additional menu
 * possible options:
 * new File/folder menu
 * rename File/folder menu
 * delete File/folder menu
 */
public class Dialog {
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
    Dialog(JFrame frame, String title, DialogType dialogType, String name, String directory) {
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
        System.out.println(dialogType);
        if (dialogType != DialogType.DeleteFile && dialogType != DialogType.DeleteDirectory)
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
        this(frame, title, dialogType, "", directory);
    }

    private JButton getOkButton(DialogType dialogType, String name, String directory, JTextField textBox) {
        JButton okButton = new JButton("OK");
        okButton.setBounds(buttonPadding, 70, buttonWidth, buttonHeight);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (dialogType) {
                    case NewFile -> FileManagement.createFile(textBox.getText(), directory);
                    case NewDirectory -> FileManagement.createDirectory(textBox.getText(), directory);
                    case RenameFile -> FileManagement.renameFile(name, textBox.getText(), directory);
                    case RenameDirectory -> FileManagement.renameDirectory(name, textBox.getText(), directory);
                    case DeleteFile -> FileManagement.deleteFile(name, directory);
                    case DeleteDirectory -> FileManagement.deleteDirectory(name, directory);
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
