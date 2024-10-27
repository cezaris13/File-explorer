package Files;

import java.io.File;
import java.nio.file.FileSystems;

/**
 * Document extends MyFile and it is used for document(odt,docx) file types
 */
public class Document extends MyFile {
    /**
     * Document class has additional variables:
     * wordCount
     * pages
     * docType - document type(docx, odt)
     */
    private int wordCount;
    private int pages;
    private String docType;

    private final String iconPath = "./Icons/document.png";

    /**
     * Constructor Document()
     * <p>
     * Creates empty Document object
     * changes word count and pages count to 0
     * changes execution program to empty string
     */
    public Document() {
        super();
        wordCount = 0;
        pages = 0;
        exProgram = "";
    }

    /**
     * Constructor Document(String fileName)
     * <p>
     * Creates New empty Document with given file name
     * assigns file name value to fileName variable
     * assigns fileIcon new icon(image file icon)
     * file name-should not include fullPath to the file)
     *
     */
    public Document(String fileName) {
        this(0, "", "");
        this.fileName = fileName;
        fileIcon = new Icon(iconPath, iconWidth, iconHeight);
    }

    /**
     * Constructor Document(int fileSize, String fileDir, String fileName)
     * <p>
     * Creates new Document with given file size,directory, name
     * assigns file name value to fileName variable(Same with fileDir,fileSize)
     * assigns fileIcon new icon(Document file icon)
     * file name-should not include fullPath to the file)
     * changes document type variable
     * changes file size variable
     *
     * @param fileSize-file size
     * @param fileDir-file  directory
     * @param fileName      - file name
     */
    public Document(int fileSize, String fileDir, String fileName) {
        super(fileSize, fileDir, fileName);
        fileIcon = new Icon(iconPath, iconWidth, iconHeight);
        setExtensionProgramAndSize(fileName);
    }

    /**
     * Method void setExProgram(String exProgram)
     * <p>
     * Changes execution program
     *
     * @param exProgram - execution program
     */
    public void setExProgram(String exProgram) {
        this.exProgram = exProgram;
    }

    /**
     * Method String setFileName(String fileName)
     * <p>
     * sets new fileName, and changes Document type variable,
     * ads execution program and changes fileSize variable
     * word count and page count is set to 0
     */
    public void setFileName(String fileName) {
        super.setFileName(fileName);
        setExtensionProgramAndSize(fileName);
    }

    private void setExtensionProgramAndSize(String fileName) {
        docType = getFileNameExtension(fileName);

        if (docType.equals("pdf")) {
            exProgram = "okular";
        } else {
            exProgram = "libreoffice";
        }
        wordCount = 0;
        pages = 0;
        String separator = FileSystems.getDefault().getSeparator();
        File file = new File(getFileDir() + separator + fileName);
        setFileSize(file.length());
    }

    /**
     * Method toString()
     * returns a string consisting of all Document
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
