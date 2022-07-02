
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
public class GameController {
    private ArrayList<Player> playerList = new ArrayList<>();
    private int currentTurn = 0;
    private int playerCount;
    private BoardCreator myBoard;
    private int lastRoll = 0;
    
    /* starts a new game of snakes and ladders by generating players, setting the board, and calling 
    * the ui manager to draw the game.
    * @param n an integer used to determine new amount of players
    * @param s the name of the input file that contains the board
    */ 
    public void startNewGame(int n, String s) throws FileNotFoundException {
        generatePlayers(n);
        myBoard = new BoardCreator(s);
    }
    
    /* sets the current turn to 0, updates the player scores, and sets the player position back to 0
    */ 
    private void restartGame() {
        currentTurn = 0;
        for(int i = 0; i < playerList.size(); i++) {
            Player selectedPlayer = playerList.get(i);
            selectedPlayer.addScore(selectedPlayer.getPosition());
            selectedPlayer.setPosition(1);
        }
    }
    
    /* saves the current game to an output file, with the current turn and player position, 
    * turn position, current score, and the name of the file used to generate the board
    */
    public void saveGame() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("output.txt");
        out.println(myBoard.getInputFile());
        out.println(currentTurn);
        for(int i = 0; i < playerCount; i++) {
            Player selectedPlayer = playerList.get(i);
            out.println(selectedPlayer.getPosition() + " " + selectedPlayer.getTurnPosition() + " " + selectedPlayer.getScore());
        }
        out.close();
    }
    
    /* loads a previous save game by reading from a save file, clearing the current game, and generating a 
    * new game based off the save file
    */
    public void loadGame() throws FileNotFoundException {
        File loadFile = new File("output.txt");
        Scanner in = new Scanner(loadFile);
        String InputFileName = in.nextLine();   
        
        myBoard = new BoardCreator(InputFileName);
        playerList.clear();
        playerCount = 0;
        
        boolean setTurnNumber = false;
        while (in.hasNextInt()) 
        {   
            String value = in.nextLine();   
            String[] splitValues = value.split(" ");
            
            if(!setTurnNumber) {
                currentTurn = Integer.parseInt(splitValues[0]);
                setTurnNumber = true;
            } else {
                playerCount += 1;
                Player p = new Player(Integer.parseInt(splitValues[0]), Integer.parseInt(splitValues[1]), Integer.parseInt(splitValues[2]));
                playerList.add(p);
            }
        }
        
    }
    
    /* Generates a number of players and adds them to the player list
    * @param n an integer used to determine new amount of players
    */
    private void generatePlayers(int n) {
        playerCount = n;
        for(int i = 0; i < n; i++) {
            Player p = new Player(1, i, 0);
            playerList.add(p);
        }
    }
    /* returns the current list of players for this game
    * @return n the list of player objects for the current game
    */
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
    
    /* Calls for the current player to take a new turn and the adjust their position. 
    * If they land on a snake or ladder use myBoard.getTile(int) to determine the end location and set the position.
    * If the player lands on the final tile, restart the game.
    */ 
    public void takeTurn() {
        Player currentPlayer = playerList.get(currentTurn);
        int tempPosition = currentPlayer.getPosition() + roll();
        if(tempPosition < 100) {
            if(myBoard.getTile(tempPosition - 1) != 0) {
                currentPlayer.setPosition(myBoard.getTile(tempPosition - 1));
            } else {
                currentPlayer.setPosition(tempPosition);
            }
            currentTurn = nextTurn();
        } else if(tempPosition == 100) {
            currentPlayer.setPosition(100);
            restartGame();
        } else {
            currentTurn = nextTurn();
        }
    }
    
    /* Incremints the current turn based on total number of players
    * @return the new current turn value
    */ 
    private int nextTurn() {
        int tempTurn = currentTurn;
        if(tempTurn + 1 < playerCount) {
            tempTurn += 1;
        } else {
            tempTurn = 0;
        }
        return tempTurn;
    }
    
    /* generates a number between 1 and 6 to emulate an average dice roll
    * @return the dice roll result
    */ 
    private int roll() {
        int random = (int)(Math.random() * 6 + 1);
        lastRoll = random;
        return random;
    }
    
    /* returns the last roll made
    * @return the last roll made
    */ 
    public int getLastRoll() {
        return lastRoll;
    }
}
