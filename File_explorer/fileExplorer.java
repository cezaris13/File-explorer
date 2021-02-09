import java.io.File;
import java.io.BufferedReader; 
import java.io.InputStreamReader;
import java.io.IOException; 

public class fileExplorer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static void recursiveFiles(String dirpath,int count){
    String space = new String(new char[count]).replace('\0', '\t');
    File f = new File(dirpath, ""); 
        if(f.exists()){
            System.out.println(ANSI_BLUE+dirpath+ANSI_RESET);
            String list[]= f.list();
            int n=list.length;
            for(int i=0;i<n;i++){
                System.out.print(space+list[i]);
                File f1=new File(dirpath+"/"+list[i]);
                if(f1.isFile()){
                    System.out.println(ANSI_RED+":this is file"+ANSI_RESET);
                }
                if(f1.isDirectory()){
                    System.out.println(ANSI_PURPLE+":this is directory"+ANSI_RESET);
                    recursiveFiles(dirpath+"/"+list[i],count+1);
                }
            }
            if(n==0){
                System.out.println("no entries in this directory");
            }
        }
        else{
            System.out.println("Directory not found");
        }
    }    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
  
        System.out.println("Enter dirpath:"); 
        String dirpath=br.readLine(); 
        recursiveFiles(dirpath,0);
    }
}