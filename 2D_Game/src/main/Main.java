/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import javax.swing.JFrame;

/**
 *
 * @author emili
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("OUMOK");
        
        GamePanelSection gamepanelsection = new GamePanelSection();
        window.add(gamepanelsection);
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        
         gamepanelsection.startGameThread();
    }
    
}
