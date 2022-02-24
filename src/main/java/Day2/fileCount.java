package Day2;
import java.io.File;
import java.util.Scanner;

public class fileCount {
    static int files;
    static int folders;
    public static void main(String[] args) {
        // variables
        Criteria crit = new Criteria();
        Scanner scan = new Scanner(System.in);

        //set criteria information
        System.out.println("Enter folder name or complete folder path");
        crit.setFolder(scan.nextLine());
        System.out.println("Enter t or f for include subfolder");
        crit.setIncludeSubFolder((scan.nextLine().charAt(0) == 't')? true:false);
        System.out.println("Enter File Extention to count. Examples * for all, .*, .java, or .txt. One Extention only");
        crit.setExtention(scan.nextLine());

        //time and execute count
        long begin = System.currentTimeMillis();

        count(crit);
        long end = System.currentTimeMillis();
        long time = end-begin;
        System.out.println("Time Milli Seconds : " +time);
    }

    public static void count(Criteria crit)
    {
        //couts the total number of files/folders that meet the criteria

        //if file/folder exists then continue
        File file = new File(crit.getFolder());
        if (file.exists())
        {
            recursiveCount(crit, file);
            System.out.println("There are "+files+" file(s) and "+folders+" folder(s) inside folder "+crit.getFolder()+" with extension "+crit.getExtention());
        }
        else
            System.out.println("File/Folder does not exist." );
    }


    public static void recursiveCount(Criteria crit, File file) {
        //does the counting recursively
        if (!file.exists())
            return;
        // check if directory exists
        if (!file.isDirectory()) {
            //count file
            if (isSuffix(file.getName(), crit.getExtention())) {
                files +=1;
            }
            return;
        }
        else{
            //count folder
            if (file.getName().contains(crit.getExtention())) {
                folders +=1;
            }

            //Enables recursion
            if (crit.isIncludeSubFolder())
                try {
                    for (File f : file.listFiles()) {
                        recursiveCount(crit,f);
                    }}
                catch(Exception e) {

                }
        }
    }
    public static boolean isSuffix(String s, String suffix) {
        if (s.length() < suffix.length())
            return false;
        //System.out.println(s.substring(s.length() - suffix.length()) + " "+ suffix);
        if (s.substring(s.length() - suffix.length()).equals(suffix))
            return true;
        return false;
    }
}
class Criteria{
    private String folder ;
    private boolean includeSubFolder;
    private String extention;
    public Criteria(String folder, boolean includeSubFolder, String extention) {
        super();
        this.folder = folder;
        this.includeSubFolder = includeSubFolder;
        this.extention = extention;
    }
    public Criteria() {
        // TODO Auto-generated constructor stub
        folder = "/";
        includeSubFolder = true;
        extention = ".java";
    }
    public String getFolder() {
        return folder;
    }
    public void setFolder(String folder) {
        this.folder = folder;
    }
    public boolean isIncludeSubFolder() {
        return includeSubFolder;
    }
    public void setIncludeSubFolder(boolean includeSubFolder) {
        this.includeSubFolder = includeSubFolder;
    }
    public String getExtention() {
        return extention;
    }
    public void setExtention(String extention) {
        this.extention = extention;
    }

}