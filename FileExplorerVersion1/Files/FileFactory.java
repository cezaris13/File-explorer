package Files;

public class FileFactory {
    public MyFile newFile(String type) {
        if (type == null) {
            return null;
        } else if (type == "Image") {
            return new Image();
        } else if (type == "Document") {
            return new Docum();
        } else if (type == "Media") {
            return new Media();
        } else if (type == "File") {
            return new SimpleFile();
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
