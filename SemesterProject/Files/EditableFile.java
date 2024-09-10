package Files;

import java.util.Date;
import java.io.IOException;
import java.io.File;

/**
 * EditableFile abstract class implements Editable interface
 */
public abstract class EditableFile implements Editable {
    String exProgram;
    String fileDir;
    String fileName = "";
    long fileSize = 0;// kb
    Date modificationTime;

    /**
     * Method editFile(int fileSize)
     *
     * this method changes fileSize and modification time
     *
     * @param fileSize(int)
     */
    public void editFile(int fileSize) {
        this.fileSize = fileSize;
        modificationTime = new Date();
    }

    /**
     * Method editFile(int fileSize,String fileDir)
     *
     * this method changes fileSize, file directory and modification time
     *
     * @param fileSize(int). fileDir(String)
     */
    public void editFile(int fileSize, String fileDir) {
        this.fileSize = fileSize;
        this.fileDir = fileDir;
        modificationTime = new Date();
    }

    /**
     * Method openFile(String exProgram, String fileName)
     *
     *
     * this method tries to open file using given execution program and its fileName
     * checks if correct file name and if file exists
     * if yes- tries to open
     * else throws exception
     *
     * @param exProgram(String) fileName(String)
     */
    public void openFile(String exProgram, String fileName)
            throws FileIsMissingException {
        try {
            System.out.println(fileName);
            String replaceSpaces = fileName;
            System.out.println(replaceSpaces);
            File tmpFile = new File(fileDir + "/" + replaceSpaces);
            if (!isCorrectFileName(fileName)) {
                throw new IncorrectFileNameException("Incorrect filename : " + fileName);
            }
            if (!tmpFile.exists()) {
                throw new FileIsMissingException("File not Found", fileName);
            }
            Runtime.getRuntime().exec(exProgram + " " + fileDir + "/" + replaceSpaces);
        } catch (IOException ex) {
        }
    }

    /**
     * Method deleteFile()
     *
     *
     * this method tries to delete file
     * checks if correct file name and if file exists
     * if yes- tries to delete
     * else throws exception
     *
     */
    public void deleteFile()
            throws FileIsMissingException {
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

    /**
     * Method isCorrectFileName(String fileName)
     *
     * this method checks if given file name is valid
     * Invalid characters: @$%\\/:*?\"'>|~`#^+={}[];!
     * if yes- returns true
     * else returns false
     *
     * @param fileName - file name
     * @return Boolean
     */
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
