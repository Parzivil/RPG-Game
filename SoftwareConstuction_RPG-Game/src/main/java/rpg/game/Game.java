/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;
import java.util.*;
/**
 *
 * @author robin
 */
public class Game{

    public Random rand = new Random();
    /**
     *
     */
    protected int moves = 0; //Then number of moves the player has made
    
    //Functions for generating random numbers

    /**
     *
     * @param min
     * @param max
     * @return
     */
    public int random(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    /**
     *
     * @param max
     * @return
     */
    public int random(int max) {
        return (int) (Math.random() * max);
    }
    
    /**
     *
     * @param strings
     * @return
     */
    public String randomString(String strings[]){
        rand.setSeed(System.currentTimeMillis()); //Reset the seed
        return strings[rand.nextInt(strings.length)];
    }
}
