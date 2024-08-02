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

    Direction heading;
    
    enum Direction{
        NORTH,
        SOUTH,
        EAST,
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

    /**
     *
     * @return
     */
    public int[] getCoordinates(){
        int[] coord = {xPosition, yPosition};
        return coord;
    }
    
}
