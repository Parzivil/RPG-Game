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
    private final int attackRatePerKG = 2000; //The time to attack per kg
    
    float weight;
    String name;
    String type; //Sword, mace, axe
    String Quality; //Bad, Decent, Good, Amazing
    String Material; //Wooden, Stone, Iron, Steel
    
    int damage;
    int attackRate; //Rate between attacks in ms
    
    Location location;
    
    public Item(String type,String Quality,String Material, Location location, float weight, int damage){
        this.Quality = Quality;
        this.type = type;
        this.Material = Material;
        this.location = location;
        this.weight = weight;
        this.damage = damage;
        
        this.name = Quality + " " + Material + " " + type;
        //Attack rate is a function of weight
        attackRate = (int)weight * attackRatePerKG; 
    }
    
    /*
        **********************************************
            **** Saving and Loading functions ****
        **********************************************
    */
    
    public JSONObject itemToJSON(){
        JSONObject jo = new JSONObject();
        jo.put("type", this.type);
        jo.put("quality", this.Quality);
        jo.put("material", this.Material);
        jo.put("location", this.location.locationToJSON());
        jo.put("weight",this. weight);
        jo.put("damage", this.damage);
        return jo;
    }
}
