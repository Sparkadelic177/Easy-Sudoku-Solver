import java.util.HashMap;

public class Main{
    static HashMap <String, String> recursionMapping = new HashMap<>();    
    private static CellGuessing guessing;
    
    public static void main(String[] args){
        //read the file and pass the file information to the grid class
        ReaderCSV reader = new ReaderCSV();
        WriterCSV writer = new WriterCSV();

        //after reading the file, check for possbile candidated for the squares
        //the grid class would hold the orginal grid
        Grid grid = new Grid(reader.reader("easy1.csv"));

        grid.toPrint();
        //passing empty cells through a function and returning a set of candidates
        grid.checkCandidates(reader.getEmptyCells());

        //the guessing class would hold a copy of the grid and guess spots
        guessing = new CellGuessing(grid.grid, grid.candidates);
       

        //guessing for empty cells
        guessing.guessing();

        //printing the file grid
        guessing.toPrint();

        //write the output file
        writer.write(guessing.grid);

    }   
}