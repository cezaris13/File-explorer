package Files;

public class FileFactory {
    public MyFile newFile(String type, String fileName) {
        if (type == null) {
            return null;
        } else if (type == "Image") {
            return new Image(fileName);
        } else if (type == "Document") {
            return new Docum(fileName);
        } else if (type == "Media") {
            return new Media(fileName);
        } else if (type == "File") {
            return new SimpleFile(fileName);
        }
        return null;
    }

    public MyFile newFile(String type, int fileSize, String fileDir, String fileName) {
        if (type == null) {
            return null;
        } else if (type == "Image") {
            return new Image(fileSize, fileDir, fileName);
        } else if (type == "Document") {
            return new Docum(fileSize, fileDir, fileName);
        } else if (type == "Media") {
            return new Media(fileSize, fileDir, fileName);
        } else if (type == "File") {
            return new SimpleFile(fileSize, fileDir, fileName);
        }
        return null;
    }
}
