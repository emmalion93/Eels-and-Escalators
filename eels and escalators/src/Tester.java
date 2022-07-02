
import java.io.File;
import java.io.FileNotFoundException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emilyT
 */
public class Tester {
    public static void main(String[] args) throws FileNotFoundException {
        //Player class
        Player p1 = new Player(1, 0, 0);
        
        System.out.println("Player 1:");
        System.out.println("Original position = " + p1.getPosition());
        System.out.println("Original X position = " + p1.getXPosition());
        System.out.println("Original Y position = " + p1.getYPosition());
        System.out.println("Original turn position = " + p1.getTurnPosition());
        System.out.println("Original score = " + p1.getScore());
        
        p1.setPosition(13);
        p1.setTurnPosition(4);
        p1.setScore(40);
        p1.addScore(11);
        
        System.out.println("new position = " + p1.getPosition());
        System.out.println("new turn position = " + p1.getTurnPosition());
        System.out.println("new score = " + p1.getScore());
        
        //BoardCreator class
        String inputFile = "input.txt";
        BoardCreator bc = new BoardCreator(inputFile);
        
        System.out.println("The tile at index 1 leads to " + bc.getTile(1));
        System.out.println("Current Board = " + bc.getBoard());
        
        //GameController class
        GameController gc = new GameController();
        
        gc.startNewGame(2, "input.txt");
        
        System.out.println("Player 1 original position is " + gc.getPlayerList().get(0).getPosition());
        
        //player 1's turn
        gc.takeTurn();
        
        System.out.println("Player 1 rolled a " + gc.getLastRoll());
        System.out.println("Player 1 new position is " + gc.getPlayerList().get(0).getPosition());
        
        //player 2's turn
        gc.takeTurn();
        
        System.out.println("SAVING");
        gc.saveGame();
        
        //player 1's turn
        gc.takeTurn();
        
        System.out.println("Player 1 rolled a " + gc.getLastRoll());
        System.out.println("Player 1 new position is " + gc.getPlayerList().get(0).getPosition());
        
        System.out.println("LOADING");
        gc.loadGame();
        
        System.out.println("Player 1 position is " + gc.getPlayerList().get(0).getPosition());
        
    }
}
