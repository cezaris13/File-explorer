import UI.CustomPanel;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FileTree {
    public JTree fileTree;
    public DefaultMutableTreeNode head;

    public FileTree() {
        head = new DefaultMutableTreeNode(CustomPanel.directory);
        fileTree = new JTree(head);
        fileTree.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) fileTree.getLastSelectedPathComponent();
                        if (node == null)
                            return;
                    }
                }
            });
    }
}
