/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;
import java.util.ArrayList;
/**
 *
 * @author robin
 */
public class Game{
    protected int moves = 0; //Then number of moves the player has made
    
    

    
    
    

    
    
    //Functions for generating random numbers
    public int random(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    public int random(int max) {
        return (int) (Math.random() * max);
    }
    
    public String randomString(String strings[]){
        return strings[random(strings.length-1)];
    }
}
