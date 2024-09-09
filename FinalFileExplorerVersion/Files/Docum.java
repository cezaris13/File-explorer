package Files;

import java.io.File;

/**
 * Docum extends MyFile and it is used for document(odt,docx) file types
 */
public class Docum extends MyFile {
    /**
     * Docum class has additionl varibles:
     * wordCount
     * pages
     * docType - document type(docx, odt)
     */
    private int wordCount;
    private int pages;
    private String docType;

    /**
     * Constructor Docum()
     *
     * Creates empty Docum object
     * changes word count and pages count to 0
     * changes execution program to empty sring
     */
    public Docum() {
        super();
        wordCount = 0;
        pages = 0;
        exProgram = "";
    }

    /**
     * Constructor Docum(String fileName)
     *
     * Creates New empty Docum with given file name
     * assigns file name value to fileName variable
     * assigns fileIcon new icon(image file icon)
     * file name- should not include fullPath to the file)
     *
     * @param fileName(String)
     */
    public Docum(String fileName) {
        this(0, "", "");
        this.fileName = fileName;
        fileIcon = new Icon(
                "/home/pijus/Desktop/Programming_languages/Java/FinalFileExplorerVersion/Files/document.png", 65, 65);
    }

    /**
     * Constructor Docum(int fileSize, String fileDir, String fileName)
     *
     * Creates new Docum with given file size,directory, name
     * assigns file name value to fileName variable(Same with fileDir,fileSize)
     * assigns fileIcon new icon(Document file icon)
     * file name-should not include fullPath to the file)
     * changes document type variable
     * changes filesize variable
     *
     * @param fileSize-file size
     * @param fileDir-file  directory
     * @param fileName      - file name
     */
    public Docum(int fileSize, String fileDir, String fileName) {
        super(fileSize, fileDir, fileName);
        fileIcon = new Icon(
                "/home/pijus/Desktop/Programming_languages/Java/FinalFileExplorerVersion/Files/document.png", 65, 65);
        int j = fileName.lastIndexOf('.');
        String extension = "";
        if (j >= 0) {
            extension = fileName.substring(j + 1);
        }
        docType = extension;
        if (extension.equals("pdf")) {
            exProgram = "okular";
        } else {
            exProgram = "libreoffice";
        }
        wordCount = 0;
        pages = 0;
        File file = new File(getFileDir() + "/" + fileName);
        setFileSize(file.length());
    }

    /**
     * Method void setExProgram(String exProgram)
     *
     * Changes execution program
     *
     * @param exProgram - execution program
     */
    public void setExProgram(String exProgram) {
        this.exProgram = exProgram;
    }

    /**
     * Method String setFileName(String fileName)
     *
     * sets new fileName, and changes Docum type variable,
     * ads execution program and changes fileSize variable
     * word count and page count is set to 0
     */
    public void setFileName(String fileName) {
        super.setFileName(fileName);
        int j = fileName.lastIndexOf('.');
        String extension = "";
        if (j >= 0) {
            extension = fileName.substring(j + 1);
        }
        docType = extension;
        if (extension.equals("pdf")) {
            exProgram = "okular";
        } else {
            exProgram = "libreoffice";
        }
        File file = new File(getFileDir() + "/" + fileName);
        setFileSize(file.length());
        wordCount = 0;
        pages = 0;
    }

    /**
     * Method toString()
     * returns a string consisting of all Docum
     * variables description
     *
     * @return String of variables
     */
    public String toString() {
        return "CreationTime:" + getCreationTime() + "\n"
                + "FileSize: " + getFileSize() + "\n"
                + "ModificationDate: " + getModificationTime() + "\n"
                + "Filedir: " + getFileDir() + "\n"
                + "Filename: " + getFileName() + "\n"
                + "wordcount:" + wordCount + "\n"
                + "pages:" + pages + "\n"
                + "exProgram: " + exProgram + "\n"
                + "docType: " + docType;
    }
}
