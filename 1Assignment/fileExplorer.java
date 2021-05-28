import java.io.File;
import java.io.BufferedReader; 
import java.io.InputStreamReader;
import java.io.IOException; 

public class fileExplorer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static void recursiveFiles(String dirpath,int count,String ex){
        String space = new String(new char[count]).replace('\0', '\t');
        File f = new File(dirpath); 
        if(f.exists()){
            String list[]=f.list();
            int n=list.length;
            for(int i=0;i<n;i++){
                File f1= new File(dirpath+"/"+list[i]);
                if(f1.isFile()){
                    String extension = "";
                    int j = f1.getName().lastIndexOf('.');
                    if (j >= 0) {
                        extension = f1.getName().substring(j+1);//something.txt -> txt
                    }
                    if(extension.equals(ex) || ex.equals("")){
                        System.out.print(space+list[i]);
                        System.out.println(ANSI_RED+":this is a file"+ANSI_RESET);
                    }                   
                }
                if(f1.isDirectory()){
                    System.out.print(space+dirpath+"/"+list[i]);
                    System.out.println(ANSI_PURPLE+":this is a directory"+ANSI_RESET);
                    recursiveFiles(dirpath+"/"+list[i],count+1,ex);
                }
            }
            if(n==0){
                System.out.println(space+"no entries in this directory");
            }
        }
        else{
            System.out.println("Directory not found");
        }
    }    
    public static void main(String[] args) throws IOException {
        String dirpath = new String();
        String ex= new String();
        if(args.length==1){
            dirpath=args[0];
            ex="";
        }
        else if( args.length>1){
            dirpath=args[0];
            ex=args[1];
        }
        else{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter dirpath:");
            dirpath = br.readLine();
            System.out.println("Enter filetype:");
            ex = br.readLine();
        }
        System.out.println(dirpath);
        recursiveFiles(dirpath,0,ex);
    }
}
