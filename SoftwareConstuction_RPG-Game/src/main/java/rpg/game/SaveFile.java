/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import org.json.*;
/**
 *
 * @author robin
 */

public class SaveFile extends Game{
    private Writer write;
    private String path;
    
    public SaveFile(String path){        
        this.path = path;
    }
    
    //Saves the current game state to a JSON file
    public void SaveGame(){  
        try{
            write = new FileWriter(this.path);
        }
        catch(Exception e){
            System.out.println("FAILED TO OPEN FILE: " + path);
        }
        
        JSONObject jo = Game.gameToJSON();
        try{
            write.write(jo.toString(3));
            write.close();
            
        }   
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    //Loads the current game from the save path    
    public void LoadGame(){
        try{
            // Load the JSON file
            FileInputStream fis = new FileInputStream(this.path);
            JSONTokener tokener = new JSONTokener(fis);
            JSONObject jo = new JSONObject(tokener);
            
            Game.player = loadPlayer(jo); //Load the player
            
            
            // *** Load Enemies *** ///
            Game.enemies.clear(); //Empty the enemy array
            JSONArray enemiesJSON = jo.getJSONArray("enemies");
            for(int i = 0; i < enemiesJSON.length(); i++){
                Game.enemies.add(loadCharacter(enemiesJSON.getJSONObject(i))); //Add enemies
            }
            
            // *** Load Objects *** ///
            Game.objects.clear(); //Empty the object array
            JSONArray objectsJSONArray = jo.getJSONArray("objects");
            for(int i = 0; i < objectsJSONArray.length(); i++){
                Game.objects.add(loadObject(objectsJSONArray.getJSONObject(i))); //Add enemies
            }
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    

    private Location loadLocation(JSONObject jo){
        JSONObject locationObj = jo.getJSONObject("location");
        
        int x = locationObj.getInt("x");
        int y = locationObj.getInt("y");

        // Create a Location object
        Location location = new Location(x, y);

        // Handle heading if it exists
        if (locationObj.has("heading")) {
            String headingStr = locationObj.getString("heading");
            Location.Direction heading = Location.Direction.valueOf(headingStr); // Assuming your enum has matching values
            location.setHeading(heading);
        }
        
        return location;
    }
    
    private Player loadPlayer(JSONObject jo){
        JSONObject playerObj = jo.getJSONObject("player");
        JSONObject playerCharacterObj = playerObj.getJSONObject("character");
        
        Location playerLocation = loadLocation(playerCharacterObj);
        Player player = new Player(playerCharacterObj.getString("name"), playerLocation, playerCharacterObj.getInt("health"), playerObj.getInt("score"));
        
        player.inventory = loadInventory(playerCharacterObj);
        
        return player;
    }
    
    private ArrayList<Item> loadInventory(JSONObject jo){
            JSONArray inventoryJSON = jo.getJSONArray("inventory");
            ArrayList<Item> inventoryArray = new ArrayList<>();
 
            for(int i = 0; i < inventoryJSON.length(); i++){
                JSONObject itemJSON = inventoryJSON.getJSONObject(i);
                inventoryArray.add(loadItem(itemJSON)); //Add enemies
            }
            
            return inventoryArray;
    }
    
    private Item loadItem(JSONObject itemObj){
        String type = itemObj.getString("type");
        String Quality = itemObj.getString("quality");
        String Material = itemObj.getString("material");
        Location location = loadLocation(itemObj);
        float weight = itemObj.getFloat("weight");
        int damage = itemObj.getInt("damage");
        
        return new Item(type, Quality, Material, location, weight, damage);
    }
    
    private Character loadCharacter(JSONObject characterObj){
        String name = characterObj.getString("name"); 
        String race = characterObj.getString("race");
        Location characterLocation = loadLocation(characterObj);
        int health = characterObj.getInt("health"); 
        boolean boss = characterObj.optBoolean("boss", false);
        
        Character character = new Character(name, race, characterLocation, health, boss);
        character.inventory = loadInventory(characterObj);
        
        return character;   
    }
    
     private ArrayList<Object> loadObjects(JSONObject jo){
            JSONArray objectJSON = jo.getJSONArray("objects");
            ArrayList<Object> objectArray = new ArrayList<>();
 
            for(int i = 0; i < objectJSON.length(); i++){
                objectArray.add(loadObject(objectJSON.getJSONObject(i))); //Add enemies
            }
            
            return objectArray;
    }
     
     private Object loadObject(JSONObject objectObj){
        String name = objectObj.getString("name");
        Location location = loadLocation(objectObj);
        boolean goal = objectObj.getBoolean("goal");
        return new Object(name, location, goal);
     }
}
