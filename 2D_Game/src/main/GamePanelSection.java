/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author emili
 */

import character.Player_game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JPanel;
import tiles.TilesManager;

public class GamePanelSection extends JPanel implements Runnable{
    final int originalTileSize =16; //Default size
    final int scale = 3;
    
    public final int tileSize = originalTileSize*scale; // total of the spriteframe 48 x 48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;  // the size of the background of all the game _ 4x3
    //Screen size 768*576 pxls
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    
    int fps = 60; //FPS 
    TilesManager tileM = new TilesManager(this);
    
    Keyboardcontrols keyH = new Keyboardcontrols();
    Thread gameThread;
    Player_game player = new Player_game(this,keyH);
    
    
  
    
    public GamePanelSection(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    
    @Override
    /*
     public void run(){ //create a game loop for the core´s game
        double drawIntervalGame = 1000000000/fps;
        double nextDrawTime = System.nanoTime() + drawIntervalGame;
            
        while(gameThread != null){
            // 1. update information
            updateMovements();
            // 2. draw the screen background
            repaint();
            
            
           try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawIntervalGame;
                
           } catch(InterruptedException e){
               e.printStackTrace();
           }
        }
        
    }*/
    
    public void run(){
        double drawIntervalGame = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawIntervalGame;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            
            if(delta >= 1){
                updateMovements();
                repaint();
                delta--;
                drawCount++;
            }
            
            if(timer >= 1000000000){
                System.out.println("FPS: "+ drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    
    
    public void updateMovements(){
        player.updateMovements();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        
        player.draw(g2);
        g2.dispose();
    }
}
