/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group70.softwareconstuction_rpg.game;

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

    public Location(int x, int y){
        this.xPosition = x;
        this.yPosition = y;
    }

    public void setHeading(Direction direction){
        this.heading = direction;
    }

    public int[] getCoordinates(){
        int[] coord = {xPosition, yPosition};
        return coord;
    }
    
}
