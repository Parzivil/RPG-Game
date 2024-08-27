/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;
import org.json.JSONObject;

/**
 *
 * @author robin, matthew
 */
public class Location {
    enum Direction{
        NORTH,
        EAST,
        SOUTH,
        WEST
    }
    
    int xPosition;
    int yPosition;
    private final int width = 30;
    private final int pos_width = 15;
    private String descriptor;

    protected Direction heading;
    
    
    public Location(int x, int y){
        this.xPosition = x;
        this.yPosition = y;
    }
    
    public Location(int x, int y, Direction heading){
        this.xPosition = x;
        this.yPosition = y;
        this.heading = heading;
    }

    /**
     *
     * @param direction
     */
    public void setHeading(Direction direction){
        this.heading = direction;
    }
    
    //Sets the description of the current location
    public void giveDescription(String description){
        this.descriptor = description;
    }
    
    //States the current location description
    public void describeLocation(){
        System.out.println(this.descriptor);
    }
    
    public void random_location() //setting a random location to the set character within the borders of the map.
    {
        int x = Game.random(width)-pos_width;
        int y = Game.random(width)-pos_width;
        this.xPosition = x;
        this.yPosition = y;
    }
    
    /*
        **********************************************
            **** Saving and Loading functions ****
        **********************************************
    */
    
    public JSONObject locationToJSON(){
        JSONObject jo = new JSONObject();
        jo.put("x", xPosition);
        jo.put("y", yPosition);
        jo.put("heading", heading);
        return jo;
    }
    
}
