package Files;

import java.io.File;

/**
 * SimpleFile extends MyFile and it is used as a unknown file type
 */
public class SimpleFile extends MyFile {
    /**
     * fileType saves extension of the file
     */
    private String fileType = "";

    /**
     * Constructor SimpleFile()
     *
     * Creates empty SimpleFile opbject where file extension is empty string
     */
    public SimpleFile() {
        super();
        fileType = "";
    }

    /**
     * Constructor SimpleFile(String fileName)
     *
     * Creates New empty SimpleFile with given file name
     * assigns file name value to fileName variable(from MyFile class)
     * assigns fileIcon new icon(txt file icon)
     * file name- should not include fullPath to the file)
     *
     * @param fileName(String)
     */
    public SimpleFile(String fileName) {
        this(0, "", "");
        this.fileName = fileName;
        fileIcon = new Icon("/home/pijus/Desktop/Programming_languages/Java/FinalFileExplorerVersion/Files/file.png", // absolute
                                                                                                                      // path
                                                                                                                      // here
                65, 65);// folder + directory icons
    }

    /**
     * Constructor SimpleFile(int fileSize, String fileDir, String fileName)
     *
     * Creates New SimpleFile with given file size,directory, name
     * assigns file name value to fileName variable(from MyFile class)
     * assigns fileIcon new icon(txt file icon)
     * file name- should not include fullPath to the file)
     * finds extension of file
     *
     * @param fileSize-     file size
     * @param fileDir-      file directory,
     * @param fileName-file name
     */
    public SimpleFile(int fileSize, String fileDir, String fileName) {
        super(fileSize, fileDir, fileName);
        fileIcon = new Icon(
                "/**//home/pijus/Desktop/Programming_languages/Java/FinalFileExplorerVersion/Files/file.png", 65, 65);// folder
                                                                                                                      // +
                                                                                                                      // directory
                                                                                                                      // icons
                                                                                                                      // absolute
                                                                                                                      // path
                                                                                                                      // here
        int j = fileName.lastIndexOf('.');
        String extension = "";
        if (j >= 0) {
            extension = fileName.substring(j + 1);
        }
        fileType = extension;
        exProgram = "kate";
        File file = new File(getFileDir() + "/" + fileName);
        setFileSize(file.length());
    }

    /**
     * Method String getFileType()
     *
     * @return fileType(String)
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Method setFileName(String fileName)
     *
     * Changes File name and sets extension, file size, execution program acordingly
     *
     * @param fileName(String)- file name
     */
    public void setFileName(String fileName) {
        super.setFileName(fileName);
        int j = fileName.lastIndexOf('.');
        String extension = "";
        if (j >= 0) {
            extension = fileName.substring(j + 1);
        }
        exProgram = "kate";
        fileType = extension;
        File file = new File(getFileDir() + "/" + fileName);
        setFileSize(file.length());
    }

    /**
     * Method toString()
     * returns a string consisting of all SimpleFile
     * variables description
     *
     * @return String of variables
     */
    @Override
    public String toString() {
        return "CreationTime:" + getCreationTime() + "\n"
                + "FileSize: " + getFileSize() + "\n"
                + "ModificationDate: " + getModificationTime() + "\n"
                + "Filedir: " + getFileDir() + "\n"
                + "Filename: " + getFileName() + "\n"
                + "exProgram: " + exProgram + "\n"
                + "fileType: " + fileType;
    }
}
