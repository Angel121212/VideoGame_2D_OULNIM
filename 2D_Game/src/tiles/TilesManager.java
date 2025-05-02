/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanelSection;

/**
 *
 * @author emili
 */
public class TilesManager {
    GamePanelSection gps;
    Tiles[] tiles;
    int worldMapTilesNum[][];
    
    
    public TilesManager(GamePanelSection gps){
        this.gps = gps;
        tiles = new Tiles[10];
        worldMapTilesNum = new int[gps.maxScreenCol][gps.maxScreenRow];
        
        
        getTilesImage();
        loadWorldMap("/worldmap/Worldmap.txt");
    }
    
    public void getTilesImage(){
        try{
            tiles[0] = new Tiles();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tilesblocks/ground_green.png"));
            
            tiles[1] = new Tiles();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tilesblocks/ground_block.png"));
            
            tiles[2] = new Tiles();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tilesblocks/ground_water.png"));
            
        }catch(IOException e){
            e.printStackTrace();
            
        }
    }
    
    public void loadWorldMap(String filePath){
        try{
           InputStream is = getClass().getResourceAsStream(filePath);
           BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
           int col = 0;
           int row = 0;
           
           while(col < gps.maxScreenCol && row < gps.maxScreenRow){
               String line = br.readLine(); //will read each single line
               
               while(col < gps.maxScreenCol){
                   String numbers[] = line.split(" ");
                   int num = Integer.parseInt(numbers[col]);
                   
                   worldMapTilesNum[col][row] = num;
                   col++;
                }if(col == gps.maxScreenCol){
                    col = 0;
                    row++;

               }
            }
           br.close();
           
        }catch (Exception e){
            
        }
    }
    
    
    public void draw(Graphics2D g2){
        
       // g2.drawImage(tiles[0].image, 0, 0, gps.tileSize, gps.tileSize,null);
       // g2.drawImage(tiles[1].image, 48, 0, gps.tileSize, gps.tileSize,null);
       // g2.drawImage(tiles[2].image, 96, 0, gps.tileSize, gps.tileSize,null);
       // the code above is not efficient
       
       int col = 0;
       int row = 0;
       int x = 0;
       int y = 0;
       
       while(col < gps.maxScreenCol && row < gps.maxScreenRow){
           
           int tilesNum = worldMapTilesNum[col][row];
           
            g2.drawImage( tiles[tilesNum].image, x, y, gps.tileSize, gps.tileSize,null);
            col++;
            x += gps.tileSize;
            
            if(col == gps.maxScreenCol){
                col = 0;
                x =0;
                row++;
                y += gps.tileSize;
            }
       }
              
    
    }
}
