/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group70.softwareconstuction_rpg.game;

/**
 *
 * @author Robin
 */
public class Armor extends Item{
    int protection; //The amount of HP points that the armor negates
    int condition; //How much damge the armor has sustained (0%-100% health)
    
    public Armor(String name, Location location, float weight, int protection, int condition){
        super(name, location, weight);
        this.protection = protection;
        this.condition = condition;
    }
}
