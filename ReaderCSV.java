import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;



public class ReaderCSV{
   
    HashMap<String, String>emptySpots = new HashMap<>();    

    
    public int[][] reader(String fileName){

        String line;                
        int rowCounter = 0;
        int[][] cells = new int[9][9];
        BufferedReader fileReader = null;
        
        
    
        //get the csv file path
        try{
            fileReader = new BufferedReader(new FileReader(fileName));
            
            //read each line and contate them in a array
            while((line = fileReader.readLine()) != null){
                String[] numbers = (line.replaceAll("\\s", "")).split(",");
                //reinit / declare the inputs due to missing commas and indexes in the input file
                int [] inputs = new int[9];
                //mapping the file into an array;
                for(int i = 0; i < numbers.length; i++){              
                    if(numbers[i].equals("")){ //if empty place zero
                        inputs[i] = 0;
                    }else{ //else place the number
                        inputs[i] = Integer.parseInt(numbers[i]);
                    }
                }

                //mapping the array to a 2d array and locating all the empty spots
                System.out.print("\n");
                for(int i = 0; i < inputs.length; i++){
                    //saving all the empty spots 
                    if(inputs[i] == 0){
                        emptySpots.put(rowCounter+""+i, "");
                    }
                     cells[rowCounter][i] = inputs[i];
                }
                rowCounter++;
            }
             
        }catch(FileNotFoundException e){
            System.out.println("The file was not found: "  + e.getMessage());
            // return cells;
        }catch(Exception e){
            System.out.println("something went wrong: "  + e.getMessage());
            // return cells;
        }
        return  cells;
    }

    public HashMap<String, String> getEmptyCells(){
        return emptySpots;
    }

}