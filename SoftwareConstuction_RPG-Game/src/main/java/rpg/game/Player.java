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
        super(name, location, health, false);
        this.score = score;
    }

    public void move() {moves++;} //Make a move
    
    public void attack(Character character, Weapon weapon){
        character.doDamageWith(weapon);
    }
    
    public void TURN_LEFT()
    {
        if(this.location.heading.equals(Location.Direction.NORTH)){this.location.setHeading(Location.Direction.WEST);}
        if(this.location.heading.equals(Location.Direction.SOUTH)){this.location.setHeading(Location.Direction.EAST);}
        if(this.location.heading.equals(Location.Direction.EAST)){this.location.setHeading(Location.Direction.NORTH);}
        if(this.location.heading.equals(Location.Direction.WEST)){this.location.setHeading(Location.Direction.SOUTH);}
        this.move();
    }
    public void TURN_RIGHT()
    {
        if(this.location.heading.equals(Location.Direction.NORTH)){this.location.setHeading(Location.Direction.EAST);}
        if(this.location.heading.equals(Location.Direction.SOUTH)){this.location.setHeading(Location.Direction.WEST);}
        if(this.location.heading.equals(Location.Direction.EAST)){this.location.setHeading(Location.Direction.SOUTH);}
        if(this.location.heading.equals(Location.Direction.WEST)){this.location.setHeading(Location.Direction.NORTH);}
        this.move();
    }
    
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
