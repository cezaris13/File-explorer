package Files;

import java.util.Date;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public abstract class EditableFile implements Editable {
    String exProgram = "";
    String fileDir;
    String fileName;
    long fileSize = 0;// kb
    Date modificationTime;

    public void editFile(int fileSize) {
        this.fileSize = fileSize;
        modificationTime = new Date();
    }

    public void editFile(int fileSize, String fileDir) {
        this.fileSize = fileSize;
        this.fileDir = fileDir;
        modificationTime = new Date();
    }

    public void openFile()
            throws FileIsMissingException {// abu
        try {
            File tmpFile = new File(fileDir + "/" + fileName);
            if (!isCorrectFileName(fileName)) {
                throw new IncorrectFileNameException("Incorrect filename : " + fileName);
            }
            if (!tmpFile.exists()) {
                throw new FileIsMissingException("File not Found", fileName);
            }
            Process p = Runtime.getRuntime().exec(exProgram + " " + fileDir + "/" + fileName);
            try {
                p.waitFor();
            } catch (InterruptedException ex) {
                System.out.println("interruption error");
            }
        } catch (IOException ex) {
        }
    }

    public void deleteFile()
            throws FileIsMissingException {// add stuff
        File file = new File(fileDir + "/" + fileName);
        if (!isCorrectFileName(fileName)) {
            throw new IncorrectFileNameException("Incorrect filename : " + fileName);
        }
        if (!file.exists()) {
            throw new FileIsMissingException("File not Found", fileName, fileDir);
        }
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    public boolean isCorrectFileName(String fileName) {
        String illegalCaracters = "@$%&\\/:*?\"'<>|~`#^+={}[];!";
        for (int i = 0; i < fileName.length(); i++) {
            for (int j = 0; j < illegalCaracters.length(); j++) {
                if (fileName.charAt(i) == illegalCaracters.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
