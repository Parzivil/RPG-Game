/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;
import java.util.Scanner;
import java.util.*;

/**
 *
 * @author robin
 */
public class Player extends Character{
   
    int score;
    /**
     *
     * @param name
     * @param location
     * @param health
     */
    public Player(String name, Location location, int health,int score){
        super(name, location, health, false);
        this.score = score;
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
    
    public Map toJSON(){
        Map<String, String> map = new HashMap<>();
        map.put("name", this.name);
        map.put("health", Integer.toString(this.health));
        map.put("location", this.location.toString());        
        return map;
    }
    public int getscore()
    {
        return this.score;
    }
    
    
    
    
}
