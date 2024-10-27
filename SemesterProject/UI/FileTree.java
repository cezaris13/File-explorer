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

    public void expandAllNodes(int startingIndex, int rowCount) {
        for (int i = startingIndex; i < rowCount; i++)
            fileTree.expandRow(i);

        if (fileTree.getRowCount() != rowCount)
            expandAllNodes(rowCount, fileTree.getRowCount());
    }
}
