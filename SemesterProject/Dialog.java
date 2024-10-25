import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

/**
 * Dialog class consists of JDialog variable
 *
 * it is used as a additional menu
 * possible options:
 * new File/folder menu
 * rename File/folder menu
 * delete File/folder menu
 *
 */
public class Dialog {
    private static JDialog jDialog;
    private int width = 300;
    private int height = 100;

    /**
     * Constructor Dialog(JFrame frame, String title, String dialogTitle, String
     * directory ,String name)
     *
     * This constructor creates new JDialog and adds to given JFrame object
     *
     * this is used to as a (Rename/create)File/folder dialog window
     */
    Dialog(JFrame frame, String title, DialogType dialogType, String directory, String name) {
        jDialog = new JDialog(frame, dialogType.name(), true);
        JTextField textBox = getTextBox();
        JButton okButton =  getOkButton(dialogType, name, directory, textBox);
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
     * Constructor Dialog(JFrame frame, String title, String dialogTitle, String
     * directory)
     *
     * This constructor creates new JDialog and adds to given JFrame object
     *
     * this is used to as a deleteFile/folder dialog window
     */
    Dialog(JFrame frame, String title, DialogType dialogType, String directory) {
        this(frame, title, dialogType, directory, "");
    }

    private JButton getOkButton(DialogType dialogType, String name, String directory, JTextField textBox){
        JButton okButton = new JButton("OK");
        okButton.setBounds(40, 70, 100, 20);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileManagement tmp = new FileManagement();
                switch(dialogType){
                    case DialogType.NewFile -> tmp.createFile(textBox.getText(), directory);
                    case DialogType.NewDirectory -> tmp.createDirectory(textBox.getText(), directory);
                    case DialogType.RenameFile -> tmp.renameFile(name, textBox.getText(), directory);
                    case DialogType.RenameDirectory -> tmp.renameDirectory(name, textBox.getText(), directory);
                    case DialogType.DeleteFile -> tmp.deleteFile(name, directory);
                    case DialogType.DeleteDirectory -> tmp.deleteDirectory(name, directory);
                }

                jDialog.setVisible(false);
            }
        });
        return okButton;
    }

    private JButton getCancelButton() {
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(150, 70, 100, 20);
        cancelButton.addActionListener(e -> Dialog.jDialog.setVisible(false));
        return cancelButton;
    }

    private JLabel getDialogTitle(String title){
        JLabel dialogTitle = new JLabel(title, SwingConstants.CENTER);
        dialogTitle.setBounds(0, 10, 300, 20);
        return dialogTitle;
    }

    private JTextField getTextBox() {
        JTextField textBox = new JTextField("");
        textBox.setBounds(50, 40, 200, 20); // TODO - figure out the constants
        return textBox;
    }
}
