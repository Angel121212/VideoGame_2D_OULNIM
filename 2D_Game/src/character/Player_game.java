/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package character;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanelSection;
import main.Keyboardcontrols;

/**
 *
 * @author emili
 */
public class Player_game extends Character{
    GamePanelSection gps;
    Keyboardcontrols keyH;
    
    
    public final int screenX;
    public final int screenY;
    
    public Player_game(GamePanelSection gps, Keyboardcontrols keyH){
        this.gps = gps;
        this.keyH = keyH;
        
        screenX = gps.screenWidth/2;
        screenY = gps.screenHeight/2;
        
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gps.tileSize * 23;
        worldY = gps.tileSize * 21;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        
        try{
            up = ImageIO.read(getClass().getResourceAsStream("/player/up_img.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2_img.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/player/down_img.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2_img.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/player/left_img.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2_img.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/player/right_img.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2_img.png"));
        }catch(IOException e){
 
        }
    }
    
    public void updateMovements(){
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true ){
        
            if(keyH.upPressed == true){
                direction = "up";
                worldY -= speed; // the same to playerY = playerY - playerSpeed; 
            }else if(keyH.downPressed == true){
                direction = "down";
                worldY += speed; // the same to playerY = playerY + playerSpeed; 
            }else if(keyH.leftPressed == true){
                direction = "left";
                worldX -= speed; // the same to playerX = playerX - playerSpeed; 
            }else if(keyH.rightPressed == true){
                direction = "right";
                worldX += speed; // the same to playerX = playerX + playerSpeed; 
            }

            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2){
        // g2.setColor(Color.white);
         // g2.fillRect(x, y, gps.tileSize, gps.tileSize);
        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up;
                }
               if(spriteNum == 2){
                    image = up2;
               } //second image for the movement
                break;
            case "down":
                if(spriteNum == 1){
                    image = down;
                }
                if(spriteNum == 2){
                    image = down2;
                } //second image for the movement
                break;
            case "left":
                if(spriteNum == 1){
                    image = left;
                }
                if(spriteNum == 2){
                    image = left2;
                } //second image for the movement
                break;
            case "right":
                
                if(spriteNum == 1){
                    image = right;
                }
                 if(spriteNum == 2){
                    image = right2;
                } //second image for the movement
                break;
        }
        g2.drawImage(image, screenX, screenY, gps.tileSize,gps.tileSize, null );
        
    }
}
