/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;

import org.json.JSONObject;

/**
 *
 * @author robin
 */
public class Weapon extends Item {
    int range;
    int damage;
    
    public Weapon(String name, Location location, float weight, int range, int damage){
        super(name, location, weight);
        this.range = range;
        this.damage = damage;
    }
    
    public JSONObject weaponToJSON(){
        JSONObject jo = new JSONObject();
        jo.put("name", name);
        jo.put("location", this.location.locationToJSON());
        jo.put("weight", Float.toString(weight));
        jo.put("range", Integer.toString(range));
        jo.put("damage", Integer.toString(damage));
        return jo;
    }
    
}
