/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;

/**
 *
 * @author robin
 */
public class Item extends Game implements Comparable{
    float weight;
    String name;
    
    Location location;
    
    /**
     *
     * @param name
     * @param location
     * @param weight
     */
    public Item(String name, Location location, float weight){
        this.name = name;
        this.location = location;
        this.weight = weight;
    }
    
    /**
     *
     * @param object
     * @return
     */
    @Override
    public int compareTo(Object object){
        return object.toString().compareTo(this.name); //Compare the names of objects
    }
}
