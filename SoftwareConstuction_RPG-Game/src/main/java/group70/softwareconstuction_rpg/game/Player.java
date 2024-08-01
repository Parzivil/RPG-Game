/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group70.softwareconstuction_rpg.game;

/**
 *
 * @author robin
 */
public class Player extends Character{
   
    public Player(String name, Location location, int health){
        super(name, location, health);
    }
        
    public void move() {moves++;} //Make a move
}
