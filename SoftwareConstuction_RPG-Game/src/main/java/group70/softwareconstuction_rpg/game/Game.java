/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group70.softwareconstuction_rpg.game;

/**
 *
 * @author robin
 */
public class Game{
    class Location{
        int xPosition;
        int yPosition;
        int rotation;
        
        public Location(int x, int y, int rot){
            this.xPosition = x;
            this.yPosition = y;
            this.rotation = rot;
        }
        
        public int[] getCoordinates(){
            int[] coord = {xPosition, yPosition};
            return coord;
        }
    }
}
