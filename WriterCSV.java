import java.io.*;  
public class WriterCSV {  
    
    public void write(int[][] grid) {  
        String line = "";
        
        try {  
            Writer w = new FileWriter("output.txt"); 
            for(int i = 0; i < 9; i++){
                line = "";
                for(int j = 0; j < 9; j++){
                    line += grid[i][j] + ",";
                }
                w.write(line + '\n');                  
            } 
            w.close();  
            System.out.println("Done");  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}  