/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;

import org.json.JSONObject;

/**
*
* @author mjbak
**/
public class Object extends Game{
    String name;
    Location location;
    String conversation[];
    Boolean aim;
    /*
    *Object constructor
    *
    *name, location, boolean aim (Default is false but set to true if interacting completes the level.)
    */
    public Object(String name, Location location,Boolean aim)
    {
        this.name = name;
        this.location = location;
        this.aim = aim;
    }        
    
    @Override
    public String toString()
    {
        return ("It is a "+name);
    }
    
    public JSONObject objectToJSON(){
        JSONObject jo = new JSONObject();
        jo.put("name", name);
        jo.put("location", this.location.locationToJSON());
        jo.put("conversation", conversation);
        jo.put("aim", aim);
        return jo;
    }
    
}
