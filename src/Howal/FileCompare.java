package Howal;
/**
 * <dl>
 * <dd> <code>FileCompare.java</code> TODO: Class TO Compare two Project Folders and check the files which have changed and Copy them to another folder to perform complexity.
 * </dd>
 * </dl>
 * @author ashish.howal,Shivam Mishra DL
 * @version 1.0.0
 */
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class FileCompare
{

    public static void main(String[] args) throws IOException
    {

        // Basline path.
        String root1 = "C:\\Ashish\\FIPO\\GetNfDetails\\src\\main";

        // path of the latest project folder which you want to compare.
        String root2 = "C:\\Ashish\\FIPO\\GetNfDetails_2\\src\\main";

        // add paths to a file
        File file1 = new File(root1);
        File file2 = new File(root2);

        // check if the path is a directory or not.
        if (file1.isDirectory())
        {

            if (file2.isDirectory())
            {

                File[] listOFile1 = getFileList(file1);
                File[] listOfFile2 = getFileList(file2);

                CompareFile(listOFile1, listOfFile2);
            }
        }

    }

    private static void CompareFile(File[] listOFile1, File[] listOfFile2) throws IOException
    {
        // TODO Auto-generated method stub

        List<String> pathList = new ArrayList<String>();

        // iterate through the list of files from the path.
        for (File file1 : listOFile1)
        {
            for (File file2 : listOfFile2)
            {

                // String a1 =
                // (file1.getAbsolutePath().substring(file1.getAbsolutePath().lastIndexOf("\\")
                // + 1));
                // String a2 =
                // (file2.getAbsolutePath().substring(file2.getAbsolutePath().lastIndexOf("\\")
                // + 1));

                String a1 = file1.getName();
                String a2 = file2.getName();

                if (a1.equals(a2))
                {

                    if (file1.isDirectory() && file2.isDirectory())
                    {
                        File[] child1 = getFileList(file1);
                        File[] child2 = getFileList(file2);

                        CompareFile(child1, child2);

                    }
                    else
                    {

                        if (file1.length() != file2.length())
                        {

                            pathList.add(file2.getAbsolutePath());

                            // System.err.println(file2.getAbsolutePath());

                        }
                    }

                }

            }
        }

        copyFile(pathList);

    }

    private static void copyFile(List<String> pathList) throws IOException
    {

        // iterator to iterate through the source files.
        java.util.Iterator<String> itr = pathList.iterator();

        while (itr.hasNext())
        {

            // Gets the source path
            String source = itr.next();

            // path where you want to copy the files
            String target = "C:/Compare/";

            // copy the source path into a file.
            File sourcefile = new File(source);

            // copy the name of the file from the path.
            String name = sourcefile.getName();

            // copy the target path + source name to the target file.
            File targetFile = new File(target + name);

            System.out.println("Copying file : " + sourcefile.getName());

            // Copy files from source path to target path with the same name.
            FileUtils.copyFile(sourcefile, targetFile);

        }
        

    }

    // Method to fetch the files from the path provided.
    private static File[] getFileList(File file)
    {
        File[] listFiles = file.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                return pathname.isDirectory() || pathname.getName().endsWith(".java");
            }
        });
        return listFiles;
    }
}
