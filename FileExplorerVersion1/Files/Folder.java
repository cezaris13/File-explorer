package Files;

import java.util.ArrayList;

public class Folder implements Cloneable {
    public ArrayList<MyFile> files = new ArrayList<>();
    int filecount;
    String folderName;

    public Object clone() throws CloneNotSupportedException {
        Folder folderCopy = (Folder) super.clone();
        ArrayList<MyFile> tmpFiles = new ArrayList<>();
        for (int i = 0; i < folderCopy.files.size(); i++) {
            tmpFiles.add((MyFile) folderCopy.files.get(i).clone());
        }
        folderCopy.files = tmpFiles;
        return folderCopy;
        // return super.clone();
    }

}
