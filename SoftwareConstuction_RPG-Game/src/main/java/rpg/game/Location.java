/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;

/**
 *
 * @author Robin
 */
public class Location {
    
    int xPosition;
    int yPosition;
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
        int x = Game.random(30)-16;
        int y = Game.random(30)-16;
        this.xPosition = x;
        this.yPosition = y;
    }
    
}
