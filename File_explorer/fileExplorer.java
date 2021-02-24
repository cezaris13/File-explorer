import java.io.File;
import java.io.BufferedReader; 
import java.io.InputStreamReader;
import java.io.IOException; 

public class fileExplorer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static void recursiveFiles(String dirpath,int count,String ex){
    String space = new String(new char[count]).replace('\0', '\t');
    File f = new File(dirpath, ""); 
        if(f.exists()){
            // System.out.println(ANSI_BLUE+dirpath+ANSI_RESET);
            String list[]= f.list();
            int n=list.length;
            for(int i=0;i<n;i++){
                File f1=new File(dirpath+"/"+list[i]);
                if(f1.isFile()){
                    String extension = "";
                    int j = f1.getName().lastIndexOf('.');
                    if (j >= 0) {
                         extension = f1.getName().substring(j+1);
                    }
                    // System.out.println(extension);
                    if(extension.equals(ex) || ex.equals("")){
                        System.out.print(space+list[i]);
                        System.out.println(ANSI_RED+":this is file"+ANSI_RESET);
                    }                   
                }
                if(f1.isDirectory()){
                    System.out.print(space+dirpath+"/"+list[i]);
                    System.out.println(ANSI_PURPLE+":this is directory"+ANSI_RESET);
                    recursiveFiles(dirpath+"/"+list[i],count+1,ex);
                }
             }
             //inkapsuliacija- veri important()
             //polimorfizmas- sukuri klase ir doggo paveldi animal klase o kate irgi po to prisideda kazkas nuo ju
             //abstraktumas - kartu su polimorfizmu
             //
            if(n==0){//vienas ciklas O(n+n) negalimas; metodas kuris skaiciuoja negali spausdinti and vice versa
                System.out.println(space+"no entries in this directory");//pats geriausias metodas, kuris grazina viena dalyka
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
        System.out.println("Enter filetype"); 
        String ex=br.readLine();
        recursiveFiles(dirpath,0,ex);
    }
}
