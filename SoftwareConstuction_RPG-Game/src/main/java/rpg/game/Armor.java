/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;

import org.json.JSONObject;

/**
 *
 * @author Robin
 */
public class Armor extends Item{
    int protection; //The amount of HP points that the armor negates
    int condition; //How much damge the armor has sustained (0%-100% health)
    
    /**
     *
     * @param name
     * @param location
     * @param weight
     * @param protection
     * @param condition
     */
    public Armor(String name, Location location, float weight, int protection, int condition){
        super(name, location, weight, 0);
        this.protection = protection;
        this.condition = condition;
    }
    
    public JSONObject armorToJSON(){
        JSONObject jo = new JSONObject();
        jo.put("item", this.itemToJSON());
        jo.put("protection", protection);
        jo.put("condition", condition);
        return jo;
    }
}
