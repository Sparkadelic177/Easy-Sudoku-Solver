import java.util.HashMap;
import java.util.Random;

//this class is going to hold the grid and some possible canidates int / char
//you can always find one (or more) position(s) that only a unique value would work 
//(also known as a “gimme”)

/**
 * 
 * The grid is class is going to have a  9 X 9 array
 * The HashMap is going to be used to hold the empty sqaures and their candidates
 * For the HashMap the key is going to be the empty sqaure's index ex. 00 01 02  for the first threw columns int the first row
 * The value for the HashMap is going to be the possibles values that the square can hold in the
 * 
 */
public class Grid{

    static boolean valid = false;
    int[][]grid = new int[9][9]; //grid
    HashMap<String, String>candidates = new HashMap<>(); //empty cells candidates
    
    public Grid(){}

    public Grid(int[][] grid){
        mapGrid(grid);
    }


    public void mapGrid(int[][] tempCopy){
        for(int i = 0; i < tempCopy.length; i++){
            for(int j = 0; j < tempCopy.length; j++){
                grid[i][j] = tempCopy[i][j];
            }
        }
    }

     /**
     * Check for empty spaces then calls boxCheck, columnCheck, and rowCheck for pssibles candidates candidates
     **/
    
    public void checkCandidates(HashMap<String, String> map){
        String keyExtrator = "";
        boolean numberExist = false;

        //mapping all the empty cells to a string 
        //even index are rows odd index are columns 
        for(String key: map.keySet()){ 
            keyExtrator += key;
        }
        
        for(int i = 0; i < keyExtrator.length(); i += 2){  
            String possibles = "123456789";            
            int row = Character.getNumericValue(keyExtrator.charAt(i));
            int column = Character.getNumericValue(keyExtrator.charAt(i + 1));

            //check for duplicates in row
            for(int j = 1; j < grid.length + 1; j++){
                numberExist = rowCheck(row, grid, j);
                if(numberExist) possibles = possibles.replace(possibles.charAt(j - 1), ' ');
            }
            //check for duplicates in coulmn
            for(int j = 1; j < grid.length + 1; j++){
                numberExist = colummnCheck(column, grid, j);
                if(numberExist) possibles = possibles.replace(possibles.charAt(j - 1), ' ');
            }
            //check for duplicates in box
            for(int j = 1; j < grid.length + 1; j++){
                numberExist = boxCheck(row, column, grid, j);
                if(numberExist) possibles = possibles.replace(possibles.charAt(j - 1), ' ');
            }

            //added what are the possible candidates and addded it to the hashmap value for that cell
            String newPossibles = "";

            //format the new string
            for(int j = 0; j < possibles.length(); j++){
                if(possibles.charAt(j) != ' ') newPossibles += possibles.charAt(j);
            }

            //each key is a row column pair
            String key = row +""+ column;
            candidates.put(key, newPossibles);
        }
        
    }

    /**
     * The next thre function checks to see if the value that is passes is a valid 
     */
    public boolean rowCheck(int row, int[][]grid, int value){
        for(int i = 0; i < grid.length; i++){
            if(value == grid[row][i]) return true;
        }   
        return false;
    }


    public boolean colummnCheck(int column, int[][]grid, int value){
        for(int i = 0; i < grid.length; i++){
            if(value == grid[i][column]) return true;
        }
        return false;
    }

   
    public boolean boxCheck(int row, int column, int[][]grid, int value){
        row = (row / 3) * 3;
        column = (column / 3) * 3;
        for(int i = row; i < row + 3; i++){
            for(int j = column; j < column +3; j++){
                if(value == grid[i][j]) return true;
            }
        }
        return false;
    }

    public void toPrint(){
        for(int i = 0; i < grid.length; i++){
            System.out.print("\n");
            for(int j = 0; j < grid.length; j++){
                System.out.print(grid[i][j] + " ");
            }
        }
    }
    
}

