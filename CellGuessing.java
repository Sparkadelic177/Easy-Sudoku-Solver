import java.util.ArrayList;
import java.util.HashMap;



//this class is going to use to stack to slove the sudoku.
public class CellGuessing{

    int[][] grid = new int[9][9];
    Grid candidateChecker = new Grid();
    HashMap<String, String>map = new HashMap<>();
    Boolean singles = false;
    StringBuilder stringManipulator;
    int column,
        row,
        value,
        singlesCells = 9;

    Boolean rowValid = false,
            columnValid = false,
            moreGimmes = true,
            boxValid = false;


    public CellGuessing(int[][]grid, HashMap<String,String>hash){
        mappingGrid(grid);
        mapHashMap(hash);
    }

    
    //mapping the grid to another grid.
    public void mappingGrid(int[][] grid){
        for(int i = 0; i < grid.length; i++ ){
            for(int j = 0; j < grid.length; j++ ){
                this.grid[i][j] = grid[i][j];
            }
        }
    }


    //mapping key value pairs to this classes hashmap
    public void mapHashMap(HashMap<String,String>hash){
        for(String key: hash.keySet()){
            map.put(key, hash.get(key));
        }
    }

    //this finds if there are still single values, if so then continue getting the gimmes
    public void findSingleValues(){
        for(String key: map.keySet()){
            if(map.get(key).length() == 1){
                singles = true;
                break;
            }else{
                singles = false;
            }
        }
    }

    //starts guessing loop for each empty cell
    public void guessing(){
        do{
            for(String key: map.keySet()){
                if(map.get(key).length() == 1) gimme(key);
            }
            findSingleValues();
        }while(singles);
    }


    //if the cell has only candidate then place it there.
    public void gimme(String cell){  
        //0 is the row, 1 is the column
        char[] rowColumn = new char[2];      
        rowColumn = cell.toCharArray();

        //extracting the value for cell
        row = Character.getNumericValue(rowColumn[0]);
        column = Character.getNumericValue(rowColumn[1]);
        value = Integer.parseInt(map.get(cell));

        //placing the value to the cell and emptying the hashmap candidates
        grid[row][column] = value;
        map.put(cell, "");

        //removing candidates in the same row. column, and box
        removeCandidates(row, column, value);
    }


    //if there aren't any more singles then guess what number can go there
    public void guess(String cell){
        char[] rowColumn;
        char[] candidates;                
        int counter = 0;
        
        candidates = map.get(cell).toCharArray();
        rowColumn = cell.toCharArray();
        row = Character.getNumericValue(rowColumn[0]);
        column = Character.getNumericValue(rowColumn[1]);

        int candidateLength = candidates.length;

        //check to see which candidate is valid to place in this spot
        while(counter < candidateLength){       
            value = Character.getNumericValue(candidates[counter]);
            rowValid = candidateChecker.rowCheck(row, grid, value);
            columnValid = candidateChecker.colummnCheck(column, grid, value);
            boxValid = candidateChecker.boxCheck(row, column, grid, value);
            if(rowValid && columnValid && boxValid){
                grid[row][column] = value;
                map.put(cell, "");
                return;
            }else{
                System.out.println("this could not work: " + row+""+column);
                counter++;
                return;
            }
        }
        //once the candidate is selected that the rest of the candidates in that cell should be deleted
        removeCandidates(row,column,value);
    }

    //removes the candidates in the same row, column, and box, after a number has been choosen
    public void removeCandidates(int row, int column, int value){
        String valueToRemove = Integer.toString(value);
        for(int i = 0; i < grid.length; i++){
            //if its a empty spot
            if(grid[row][i] == 0){
                //gets the candidates in the same row
                String candidates = map.get(row+""+i);                
                if(candidates.contains(valueToRemove)){
                    StringBuilder builder = new StringBuilder(candidates);
                    builder.deleteCharAt(candidates.indexOf(valueToRemove));
                    map.put(row+""+i, builder.toString());
                }
            }
        }

        for(int i = 0; i < grid.length; i++){
            if(grid[i][column] == 0){
                String candidates = map.get(i+""+column);                
                if(candidates.contains(valueToRemove)){
                    StringBuilder builder = new StringBuilder(candidates);
                    builder.deleteCharAt(candidates.indexOf(valueToRemove));
                    map.put(i+""+column, builder.toString());
                }
            }
        }

        row = (row / 3) * 3;
        column = (column / 3) * 3;
        for(int i = row; i < row + 3; i++){
            for(int j = column; j < column +3; j++){
                if(grid[i][j] == 0){
                    String candidates = map.get(i+""+j);                
                    if(candidates.contains(valueToRemove)){
                        StringBuilder builder = new StringBuilder(candidates);
                        builder.deleteCharAt(candidates.indexOf(valueToRemove));
                        map.put(i+""+j, builder.toString());
                    } 
                } 
            }
        }


    }

    public void toPrint(){
        System.out.println("\n\n");
        for(int i = 0; i < grid.length; i++){
            System.out.print("\n");
            for(int j = 0; j < grid.length; j++){
                System.out.print(grid[i][j] + " ");
            }
        }
    }

}