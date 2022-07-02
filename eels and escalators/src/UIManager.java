/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emilyT
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UIManager extends JPanel{
    static Color darkGreen = new Color(0, 114, 0); // Color darkGreen
    GameController gc;
    int adjustedX = 0;
    int reverseAdjustedX = 550;
    int adjustedY = 500;
    int tileSize = 50;
    Color[] playerColorList = new Color[] {Color.BLUE, Color.orange, Color.green, Color.yellow, Color.MAGENTA, Color.lightGray, Color.pink, Color.cyan, Color.red, Color.BLACK, Color.DARK_GRAY, Color.ORANGE};
    String currentRoll = "Roll 0";
    
    
    /* an override of the JPanel paint method that paints the GUI
    * @param g the JPanel's Graphics reference 
    */
    @Override
    public void paint(Graphics g){
        Image image1 = Toolkit.getDefaultToolkit().getImage("eels_and_escalators_board2.jpg");
        while(!g.drawImage(image1, 50, 50, null)) { }
        
        ArrayList<Player> playerList = gc.getPlayerList();
        
        for(int i = 0; i < playerList.size(); i++) {
            Player selectedPlayer = playerList.get(i);
            g.setColor(playerColorList[i]);
            if(selectedPlayer.getYPosition() % 2 == 0) {
                if(selectedPlayer.getPosition() % 10 > 0) {
                    g.fillArc(adjustedX + (tileSize * selectedPlayer.getXPosition()), adjustedY - 50 * (selectedPlayer.getYPosition()), tileSize, tileSize, i * (360/playerList.size()), 360/playerList.size());
                } else {
                    g.fillArc(reverseAdjustedX - (tileSize * 10), adjustedY - 50 * (selectedPlayer.getYPosition() - 1), tileSize, tileSize, i * (360/playerList.size()), 360/playerList.size());
                }
            } else {
                if(selectedPlayer.getPosition() % 10 > 0) {
                    g.fillArc(reverseAdjustedX - (tileSize * selectedPlayer.getXPosition()), adjustedY - 50 * (selectedPlayer.getYPosition()), tileSize, tileSize, i * (360/playerList.size()), 360/playerList.size());
                } else {
                    g.fillArc(adjustedX + (tileSize * 10), adjustedY - 50 * (selectedPlayer.getYPosition() - 1), tileSize, tileSize, i * (360/playerList.size()), 360/playerList.size());
                }
            }
        }
        
        g.setColor(Color.black);
        g.clearRect(560, 0, 240, 1000);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        for(int i = 0; i < playerList.size(); i++) {
            Player selectedPlayer = playerList.get(i);
            g.drawString("Player " + (i + 1) + " Score: " + selectedPlayer.getScore() + "", 580, 100 + (i * 40));
        }
        
        
        g.clearRect(250, 555, 50, 50);
        setCurrentRoll();
        g.drawString(currentRoll, 250, 575);
    }
    
    /* sets the currentRoll string for the GUI to draw
    */
    private void setCurrentRoll() {
        currentRoll = "Roll " + gc.getLastRoll();
    }
    
    /* sets the GameController gc
    */
    public void setGC(GameController newGC) {
        gc = newGC;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        
        GameController gc = new GameController();
        JFrame frame = new JFrame();
        frame.setSize(800,800);
        UIManager m = new UIManager();
        m.setGC(gc);
        
        JButton myRollButton = new JButton("Roll");
        myRollButton.setSize(100, 40);
        myRollButton.setLocation(40, 650);
        
        myRollButton.addActionListener((ActionEvent e) -> {
            gc.takeTurn();
            m.paint(m.getGraphics());
        });
        frame.getContentPane().add(myRollButton);
        
        JButton mySaveButton = new JButton("Save");
        mySaveButton.setSize(100, 40);
        mySaveButton.setLocation(200, 650);
        
        mySaveButton.addActionListener((ActionEvent e) -> {
            try {
                gc.saveGame();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UIManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        frame.getContentPane().add(mySaveButton);
        
        JButton myLoadButton = new JButton("Load");
        myLoadButton.setSize(100, 40);
        myLoadButton.setLocation(360, 650);
        
        myLoadButton.addActionListener((ActionEvent e) -> {
            try {
                gc.loadGame();
                m.paint(m.getGraphics());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UIManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        frame.getContentPane().add(myLoadButton);
        
        frame.getContentPane().add(m);
        frame.setLocationRelativeTo(null);
        frame.setBackground(darkGreen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().setVisible(true);
        
        int i = 0;
        boolean goAhead = false;
        while(!goAhead) {
            String s = JOptionPane.showInputDialog("How many players?");
            try {
                i = Integer.parseInt(s);
                if(i < 13 && i > 0) {
                    goAhead = true;
                    gc.startNewGame(i, "input.txt");
                    m.paint(m.getGraphics());
                } else {
                    if(i <= 0) {
                        JOptionPane.showMessageDialog(frame, "ERROR It is no fun to play alone!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "ERROR Too many players. Max 12");
                    }
                }
            } catch(java.lang.NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "ERROR ONLY WHOLE NUMBERS!");
            }
        }
    }
}