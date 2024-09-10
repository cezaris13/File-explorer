package UI;

import javax.swing.*;
import java.awt.event.*;

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
        JFrame f = frame;
        d = new JDialog(f, DialogTitle, true);
        d.setLayout(null);
        JButton ok = new JButton("OK");
        ok.setBounds(40, 70, 100, 20);
        JButton cancel = new JButton("Cancel");
        cancel.setBounds(150, 70, 100, 20);
        JTextField textBox = new JTextField("");
        textBox.setBounds(50, 40, 200, 20);
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Dialog.d.setVisible(false);
            }
        });
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileManagement tmp = new fileManagement();
                if (DialogTitle == "new file") {
                    tmp.createFile(textBox.getText(), directory);
                }
                if (DialogTitle == "new directory") {
                    tmp.createDirectory(textBox.getText(), directory);
                }
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
        d.setLocationRelativeTo(f);
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
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Dialog.d.setVisible(false);
            }
        });
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileManagement tmp = new fileManagement();
                if (DialogTitle == "rename file") {
                    tmp.renameFile(name, textBox.getText(), directory);
                }
                if (DialogTitle == "rename directory") {
                    tmp.renameDirectory(name, textBox.getText(), directory);
                }
                if (DialogTitle == "delete file") {
                    tmp.deleteFile(name, directory);
                }
                if (DialogTitle == "delete directory") {
                    tmp.deleteDirectory(name, directory);
                }
                Dialog.d.setVisible(false);
            }
        });
        JLabel Jtitle = new JLabel(title, SwingConstants.CENTER);
        Jtitle.setBounds(0, 10, 300, 20);
        d.add(Jtitle);
        d.add(cancel);
        if (DialogTitle == "rename file" || DialogTitle == "rename directory") {
            d.add(textBox);
        }
        d.add(ok);
        d.setSize(300, 100);
        d.setResizable(false);
        d.setLocationRelativeTo(f);
        d.setVisible(true);
    }
}
