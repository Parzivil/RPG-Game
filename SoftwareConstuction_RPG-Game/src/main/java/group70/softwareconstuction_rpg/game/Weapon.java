/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group70.softwareconstuction_rpg.game;

/**
 *
 * @author robin
 */
public class Weapon extends Item {
    int range;
    int damage;
    
    public Weapon(String name, Location location, float weight, int range, int damage){
        super(name, location, weight);
        this.range = range;
        this.damage = damage;
    }
    
}
