package UI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileTree {
    public JTree fileTree;
    public DefaultMutableTreeNode head;

    public FileTree(String currentDirectory) {
        head = new DefaultMutableTreeNode(currentDirectory);
        fileTree = new JTree(head);
    }

    public void expandAllNodes(int startingIndex, int rowCount) {
        for (int i = startingIndex; i < rowCount; i++)
            fileTree.expandRow(i);

        if (fileTree.getRowCount() != rowCount)
            expandAllNodes(rowCount, fileTree.getRowCount());
    }
}
