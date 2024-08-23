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

    public Player(String name, Location location, int health,int score){
        super(name,"player",location, health, false);
        this.score = score;
    }

    
    
    public void attack(Character character, Weapon weapon){
        character.doDamageWith(weapon);
    }
    
    //Is there a more efficent way of doing this?
    
    
    public Map toJSON(){
        Map<String, String> map = new HashMap<>();
        map.putAll(this.toJSON()); //Add character stats to the map
        map.put("score", Integer.toString(this.score)); //Add the player score to the stats
        return map;
    }
    public int getscore()
    {
        return this.score;
    }
}
