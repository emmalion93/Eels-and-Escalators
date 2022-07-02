/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emilyT
 */
public class Player {
    private int position;
    private int turnPosition;
    private int score;

    /* Generates a new player at a position, turn position, and with a score
    * @param p the players current position on the board
    * @param tp the players current position in the turn order
    * @param s the players current score
    */
    public Player(int p, int tp, int s) {
        position = p;
        turnPosition = tp;
        score = s;
    }
    
    /* sets the players position to a variable
    * @param p the players new position on the board
    */
    public void setPosition(int p) {
        position = p;
    }
    
    /* gets the players current position on the board
    * @return the players current position on the board
    */
    public int getPosition() {
        return position;
    }
    
     /* gets the players current X position on the board
    * @return the players current X position on the board
    */
    public int getXPosition() {
        return position % 10;
    }
    
     /* gets the players current Y position on the board
    * @return the players current Y position on the board
    */
    public int getYPosition() {
        int tempPosition = (int) Math.floor(position / 10);
        return tempPosition % 10;
    }
    
     /* sets the players current turn position
    * @param tp the players new turn position
    */
    public void setTurnPosition(int tp) {
        turnPosition = tp;
    }
    
     /* gets the players current turn position
    * @return the players current turn position
    */
     public int getTurnPosition() {
        return turnPosition;
    }
     
    /* sets the players current score
    * @param s the players new score
    */
    public void setScore(int s) {
        score = s;
    }
    
    /* adds points to a players score
    * @param s point value to be added
    */
    public void addScore(int s) {
        score = score + s;
    }
    
    /* gets the players current score
    * @return the players  score
    */
    public int getScore() {
        return score;
    }
}
