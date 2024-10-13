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
    private static JDialog d;

    /**
     * Constructor Dialog(JFrame frame,String title,String DialogTitle,String
     * directory)
     *
     * This constructor creates new JDialog and adds to given JFrame object
     *
     * this is used to as a deleteFile/folder dialog window
     */
    Dialog(JFrame frame, String title, String DialogTitle, String directory) {
        d = new JDialog(frame, DialogTitle, true);
        d.setLayout(null);
        JButton ok = new JButton("OK");
        ok.setBounds(40, 70, 100, 20);
        JButton cancel = new JButton("Cancel");
        cancel.setBounds(150, 70, 100, 20);
        JTextField textBox = new JTextField("");
        textBox.setBounds(50, 40, 200, 20);
        cancel.addActionListener(e -> Dialog.d.setVisible(false));
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileManagement tmp = new FileManagement();
                if (Objects.equals(DialogTitle, "new file"))
                    tmp.createFile(textBox.getText(), directory);

                if (Objects.equals(DialogTitle, "new directory"))
                    tmp.createDirectory(textBox.getText(), directory);

                Dialog.d.setVisible(false);
            }
        });
        JLabel Jtitle = new JLabel(title, SwingConstants.CENTER);
        Jtitle.setBounds(0, 10, 300, 20);
        d.add(Jtitle);
        d.add(cancel);
        d.add(textBox);
        d.add(ok);
        d.setSize(300, 100);
        d.setResizable(false);
        d.setLocationRelativeTo(frame);
        d.setVisible(true);
    }

    /**
     * Constructor Dialog(JFrame frame,String title,String DialogTitle,String
     * directory,String name)
     *
     * This constructor creates new JDialog and adds to given JFrame object
     *
     * this is used to as a (Rename/create)File/folder dialog window
     */
    Dialog(JFrame frame, String title, String DialogTitle, String directory, String name) {
        JFrame f = frame;
        d = new JDialog(f, DialogTitle, true);
        d.setLayout(null);
        JButton ok = new JButton("OK");
        ok.setBounds(40, 70, 100, 20);
        JButton cancel = new JButton("Cancel");
        cancel.setBounds(150, 70, 100, 20);
        JTextField textBox = new JTextField("");
        textBox.setBounds(50, 40, 200, 20);
        cancel.addActionListener(e -> Dialog.d.setVisible(false));
        ok.addActionListener(e -> {
            FileManagement tmp = new FileManagement();
            if (Objects.equals(DialogTitle, "rename file"))
                tmp.renameFile(name, textBox.getText(), directory);

            if (Objects.equals(DialogTitle, "rename directory"))
                tmp.renameDirectory(name, textBox.getText(), directory);

            if (Objects.equals(DialogTitle, "delete file"))
                tmp.deleteFile(name, directory);

            if (Objects.equals(DialogTitle, "delete directory"))
                tmp.deleteDirectory(name, directory);

            Dialog.d.setVisible(false);
        });
        JLabel Jtitle = new JLabel(title, SwingConstants.CENTER);
        Jtitle.setBounds(0, 10, 300, 20);
        d.add(Jtitle);
        d.add(cancel);
        if (Objects.equals(DialogTitle, "rename file") || Objects.equals(DialogTitle, "rename directory"))
            d.add(textBox);

        d.add(ok);
        d.setSize(300, 100);
        d.setResizable(false);
        d.setLocationRelativeTo(f);
        d.setVisible(true);
    }
}
