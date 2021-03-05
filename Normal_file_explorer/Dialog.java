import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Dialog {
    private static JDialog d;
    Dialog(JFrame frame,String title,String DialogTitle,String dir) {
        JFrame f= frame;
        d = new JDialog(f , DialogTitle, true);//Change title
        // d.setLayout( new FlowLayout() );
        d.setLayout(null);
        JButton ok = new JButton ("OK");
        ok.setBounds(40,70,100,20);
        JButton cancel = new JButton ("Cancel");
        cancel.setBounds(150,70,100,20);
        JTextField textBox=new JTextField("");
        textBox.setBounds(50,40,200,20);
        cancel.addActionListener ( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                Dialog.d.setVisible(false);
            }
        });
        ok.addActionListener ( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                fileManagement tmp=new fileManagement();
                if(DialogTitle=="new file"){
                    tmp.createFile(textBox.getText(),dir);
                }
                if(DialogTitle=="new directory"){
                    tmp.createDirectory(textBox.getText(),dir);
                }
                // if(DialogTitle=="delete file"){
                //     tmp.createFile(textBox.getText(),dir);
                // }
                // if(DialogTitle=="delete directory"){ m
                //     tmp.createDirectory(textBox.getText(),dir);
                // }
                Dialog.d.setVisible(false);
            }
        });

        JLabel Jtitle=new JLabel(title,SwingConstants.CENTER);
        Jtitle.setBounds(0,10,300,20);
        d.add(Jtitle);
        d.add(cancel);
        d.add(textBox);
        d.add(ok);
        d.setSize(300,100);
        d.setResizable(false);
        d.setLocationRelativeTo(f);
        d.setVisible(true);
    }
}
