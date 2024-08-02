/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;
import java.util.Scanner;

/**
 *
 * @author robin
 */
public class Player extends Character{
   
    /**
     *
     * @param name
     * @param location
     * @param health
     */
    public Player(String name, Location location, int health){
        super(name, location, health);
    }
        
    /**
     *
     */
    public void move() {moves++;} //Make a move
    
    /**
     *
     * @param character
     * @param weapon
     */
    public void attack(Character character, Weapon weapon){
        character.doDamageWith(weapon);
    }
    

}
