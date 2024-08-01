/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group70.softwareconstuction_rpg.game;
import java.util.ArrayList;
/**
 *
 * @author robin
 */
public class Game{
    private int moves = 0; //Then number of moves the player has made
    
    
    enum Direction{
        NORTH,
        SOUTH,
        EAST,
        WEST
    }
    
    public class Location{
        int xPosition;
        int yPosition;
        
        Direction heading;
        
        public Location(int x, int y){
            this.xPosition = x;
            this.yPosition = y;
        }
        
        public void SetHeading(Direction direction){
            this.heading = direction;
        }
        
        public int[] getCoordinates(){
            int[] coord = {xPosition, yPosition};
            return coord;
        }
    }
    
    public void move() {this.moves++;} //Make a move
}
