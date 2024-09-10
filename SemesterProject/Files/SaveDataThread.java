package Files;

import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SaveDataThread implements Runnable {
    String saveDirectory;
    String fileLocation;

    public SaveDataThread(String saveDirectory, String fileLocation) {
        this.fileLocation = fileLocation;
        this.saveDirectory = saveDirectory;
    }

    public void run() {
        try {
            DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(fileLocation));
            byte[] data = saveDirectory.getBytes("UTF-8");
            System.out.println(saveDirectory);
            dataOut.writeInt(data.length);
            dataOut.write(data);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
