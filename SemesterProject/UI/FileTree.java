package UI;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileTree {
    public JTree fileTree;
    public DefaultMutableTreeNode head;

    public FileTree() {
        head = new DefaultMutableTreeNode(CustomPanel.directory);
        fileTree = new JTree(head);
    }
}