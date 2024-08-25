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
    
    int xPosition;
    int yPosition;
    int width = 30;
    int pos_width = 15;
    private String descriptor;

    Direction heading;
    
    enum Direction{
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    /**
     *
     * @param x
     * @param y
     */
    public Location(int x, int y){
        this.xPosition = x;
        this.yPosition = y;
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

    /**
     *
     * @return
     */
    public int[] getCoordinates(){
        int[] coord = {xPosition, yPosition};
        return coord;
    }
    @Override
    public String toString()
    {
        return (" x = "+xPosition+" y = "+yPosition+" ");
    }
    public void random_location() //setting a random location to the set character within the borders of the map.
    {
        int x = Game.random(width)-pos_width;
        int y = Game.random(width)-pos_width;
        this.xPosition = x;
        this.yPosition = y;
    }
    
    public JSONObject locationToJSON(){
        JSONObject jo = new JSONObject();
        jo.put("x", xPosition);
        jo.put("y", yPosition);
        jo.put("heading", heading);
        return jo;
    }
    
}
