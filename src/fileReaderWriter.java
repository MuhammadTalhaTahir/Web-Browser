
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Vector;

public class fileReaderWriter {
    public Vector<String> readFile(String filePath){
        Vector<String> data = new Vector<>();
        try{
            BufferedReader inFile = new BufferedReader(new FileReader(new File(filePath)));
            String line = inFile.readLine();
            while(line != null){
                data.add(line);
                line = inFile.readLine();
            }
            inFile.close();
            return data;
        }
        catch(Exception e){
            System.out.println("[ERROR] issue in reading file.");
            e.printStackTrace();
            return null;
        }
    }
    public void writeFile(String Data, String filePath){
        try{
            PrintWriter outFile = new PrintWriter(new FileWriter(new File(filePath),true));
            outFile.println(Data);
            outFile.close();
        }
        catch(Exception e){
            System.out.println("[ERROR] issue in writting file.");
            e.printStackTrace();
        }
    }
    public void writeFile(String Data, String filePath, boolean append){
        try{
            PrintWriter outFile = new PrintWriter(new FileWriter(new File(filePath),append));
            outFile.println(Data);
            outFile.close();
        }
        catch(Exception e){
            System.out.println("[ERROR] issue in writting file.");
            e.printStackTrace();
        }
    }
    public void removeAline(String line, String filePath){
        try{
            Vector<String> temp = this.readFile(filePath);
            PrintWriter outFile = new PrintWriter(new FileWriter(new File(filePath)));
            outFile.print("");
            outFile.close();
            for(int i=0;i<temp.size();i++){
                if(!line.equals(temp.elementAt(i))){
                    this.writeFile(temp.elementAt(i), filePath);
                }
            }
        }
        catch(Exception e){
            System.out.println("[ERROR] issue in deleting line from file.");
            e.printStackTrace();
        }
    }
}
