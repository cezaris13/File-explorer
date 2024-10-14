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

    /**
     * Constructor Dialog(JFrame frame,String title,String DialogTitle,String
     * directory)
     *
     * This constructor creates new JDialog and adds to given JFrame object
     *
     * this is used to as a deleteFile/folder dialog window
     */
    Dialog(JFrame frame, String title, String dialogTitle, String directory) {
        JTextField textBox = getTextBox();
        jDialog = getDialog(frame, dialogTitle, "", title, directory, textBox);
        jDialog.add(textBox);
        jDialog.setVisible(true);
    }

    /**
     * Constructor Dialog(JFrame frame,String title,String DialogTitle,String
     * directory,String name)
     *
     * This constructor creates new JDialog and adds to given JFrame object
     *
     * this is used to as a (Rename/create)File/folder dialog window
     */
    Dialog(JFrame frame, String title, String dialogTitle, String directory, String name) {
        JTextField textBox = getTextBox();
        jDialog = getDialog(frame, dialogTitle, name, title, directory, textBox);

        if (Objects.equals(dialogTitle, "rename file") || Objects.equals(dialogTitle, "rename directory"))
            jDialog.add(textBox);

        jDialog.setVisible(true);
    }

    private JButton getOkButton(String dialogTitle, String name, String directory, JTextField textBox){
        JButton okButton = new JButton("OK");
        okButton.setBounds(40, 70, 100, 20);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileManagement tmp = new FileManagement();
                switch(dialogTitle){
                    case "new file" -> tmp.createFile(textBox.getText(), directory);
                    case "new directory" -> tmp.createDirectory(textBox.getText(), directory);
                    case "rename file" -> tmp.renameFile(name, textBox.getText(), directory);
                    case "rename directory" -> tmp.renameDirectory(name, textBox.getText(), directory);
                    case "delete file" -> tmp.deleteFile(name, directory);
                    case "delete directory" -> tmp.deleteDirectory(name, directory);
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
        textBox.setBounds(50, 40, 200, 20);
        return textBox;
    }

    private JDialog getDialog(JFrame frame, String dialogTitle, String name, String title, String directory, JTextField textBox) {
        JDialog jDialog = new JDialog(frame, dialogTitle, true);
        JButton okButton =  getOkButton(dialogTitle, name, directory, textBox);
        JButton cancelButton = getCancelButton();
        jDialog.setLayout(null);
        jDialog.add(getDialogTitle(title));
        jDialog.add(okButton);
        jDialog.add(cancelButton);
        jDialog.setSize(300, 100);
        jDialog.setResizable(false);
        jDialog.setLocationRelativeTo(frame);
        return jDialog;
    }
}
