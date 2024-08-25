/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;
import org.json.*;

/**
 *
 * @author robin, matthew
 */
public class Item extends Game {
    float weight;
    String name;
    String type; //Sword, mace, axe
    String Quality; //Bad, Decent, Good, Amazing
    String Material; //Wooden, Stone, Iron, Steel
    
    int damage;
    int attackRate; //Rate between attacks in ms
    
    Location location;
    
    public Item(String type,String Quality,String Material, Location location, float weight, int damage){
        this.name = Quality+" "+Material+" "+type;
        this.location = location;
        this.weight = weight;
        this.damage = damage;
        
        //Attack rate is a function of weight
        attackRate = (int)weight * 2000; //May need adjusting
    }
    

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
    
    public JSONObject itemToJSON(){
        JSONObject jo = new JSONObject();
        jo.put("name", name);
        jo.put("location", this.location.locationToJSON());
        jo.put("weight", weight);
        return jo;
    }
}
