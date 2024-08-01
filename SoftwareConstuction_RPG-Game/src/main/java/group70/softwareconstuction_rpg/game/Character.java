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
    Location location;
    private ArrayList<Item> inventory = new ArrayList<Item>(); //Stores players items
    private State state;
    int health;
    
    //Options for the character to say when damaged
    public String deathEmotes[] = {"Oh man"};
    public String damageEmotes[] = {"Ouch"};
    public String imortalEmotes[] = {"Hahaha nice try"};
    public String uncontiousEmotes[] = {"Ooof"};
    
    enum State{
        ALIVE,
        DEAD,
        UNCONSIOUS,
        IMPORTAL
    }
    
    public Character(String name, Location location, int health){
        this.name = name;
        this.location = location;
        this.health = health;
        
        
        //Depending on the health given in the constructor sets the state
        if(health == 0) state = State.DEAD;
        else if(health == Integer.MAX_VALUE) state = State.IMPORTAL;
        else if(health < 0) state = State.UNCONSIOUS;
        else state = State.ALIVE;
    }

    public State getState() { return this.state;}
    
    //Affects health and says a message
    public void doDamage(int damage){
        
        switch(state){
            case State.ALIVE:
                health -= damage; //Change the damage done to the character
        
                if(health <= 0) this.Kill();
                else{
                    this.Say(randomString(damageEmotes));
                }
            break;
            
            
        } 
     }
    
    public void doDamageWith(Weapon weapon){
        
        //Condition if they stab themselves
        if(this.inventory.contains(weapon)){
            System.out.println("\n"+ this.name + " attacks themselves with " + weapon.name + " for " + weapon.damage + " damage!");
        }
        else System.out.println("\n"+ this.name + " is attacked with " + weapon.name + " for " + weapon.damage + " damage!");
        this.doDamage(weapon.damage);
    }
    
    public void Kill(){
        state = State.DEAD;
        this.Say(randomString(deathEmotes));
    }
    
    public void GiveItem(Item item){
        System.out.println("\n" + this.name + " is given " + item.name);
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
