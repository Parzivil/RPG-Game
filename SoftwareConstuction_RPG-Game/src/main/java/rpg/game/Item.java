/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;

/**
 *
 * @author robin
 */
//probable eeds to be abstract
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
    //It didnt like the override function
    public int compareTo(Object object){
        return object.toString().compareTo(this.name); //Compare the names of objects
    }
    
    @Override
    public String toString(){
        String combo = "name:" + name + "weight:" + Float.toString(weight);
        combo += "location:" + location.toString() + "; \n";
        
        return combo;
    }
}
