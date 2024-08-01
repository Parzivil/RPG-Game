/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group70.softwareconstuction_rpg.game;
import java.util.ArrayList;
/**
 *
 * @author Robin
 */
public class Character extends Game{
    String name;
    private ArrayList<Item> inventory = new ArrayList<Item>(); //Stores players items
    Direction heading;
    State state;
    int health;
    
    enum State{
        ALIVE,
        DEAD,
        UNCONSIOUS,
        IMPORTAL
    }
    
    public void GiveItem(Item item){
        inventory.add(item);
    }
    
    public void RemoveItem(Item item){
        inventory.remove(item);
    }
    
    public void Say(String message){
        System.out.print(this.name + ": ");
        System.out.println(message);
    }
}
