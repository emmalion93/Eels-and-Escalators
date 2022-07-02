
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emilyT
 */
public class BoardCreator {
    private ArrayList<Integer> board = new ArrayList<>();
    private String myInputFile;
    
    /* creates a new BoardCretor object, generates the new board from an inputFile, and sets the myGameController object
    * @param s the name of the file that contains the new board
    * @param gc the current GameController object
    */
    public BoardCreator(String s) throws FileNotFoundException {
        myInputFile = s;
        File inputFile = new File(s); 
        Scanner in = new Scanner(inputFile);
        
        double rowCount = 1;
        ArrayList<Integer> tempBoard = new ArrayList<>();
        while (in.hasNextInt()) 
        {   
            
            String value = in.nextLine();   
            String[] splitValues = value.split(" ");
            if(rowCount % 2 == 0) {
                for(int i = 9; i > -1; i--) {
                    tempBoard.add(Integer.parseInt(splitValues[i]));
                }
            } else {
                for(int i = 0; i < 10; i++) {
                    tempBoard.add(Integer.parseInt(splitValues[i]));
                }
            }
            rowCount += 1;
        }
        in.close();
        
        for(int i = tempBoard.size() - 1; i > -1; i--) {
            board.add(tempBoard.get(i));
        }
    }
    
    /* returns the value of a tile at an index
    * @param the tile index
    * @return the tile value
    */
    public int getTile(int n) {
        return board.get(n);
    }
    
    /* returns the entire ArrayList of tiles for the board
    * @return the ArrayList of tiles for the board
    */
    public ArrayList<Integer> getBoard() {
        return board;
    }
    
    /* returns the current boards name
    * @return the name of the file used to generate the current board
    */
    public String getInputFile() {
        return myInputFile;
    }
}
