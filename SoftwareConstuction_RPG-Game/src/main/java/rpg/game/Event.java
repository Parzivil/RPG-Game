/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;

/**
 *
 * @author robin
 */
public class Event extends Game{
    String eventType;
    public Location location;
    String activationResponse; //Spoken when the character steps onto 
    Event linkedEvent;
    
    
}

class Wall extends Event{
        int ID = 0xFFFFFF;
        

    public Wall(Location location, String activationResponse) {
        eventType = "Wall";
        linkedEvent = this;
    }
        
    }
    
    class Empty extends Event{
        int ID = 0x020000;
        String description;
    }
    
    class Enemy extends Event{
        int ID = 0x030000;
        Character enemy;
        String dialog[];
        
        public Enemy(Character enemy, String dialog[]){
            this.enemy = enemy;
            this.dialog = dialog;  
        }
        
        public Enemy(){}
        
        //Pick up the loot from a killed enemy
        public void pickUpLoot(){
            
        }
    }
    
    class Neutral extends Event{
        int ID = 0x040000;
        Character character;
        String dialog[];
    }
    
    class Trap extends Event{
        int ID = 0x050000;
        Weapon trap[];    
    }
    
    class Loot extends Event{
        int ID = 0x060000;
        Item items[];
        String description;
    }
    
    class Chest  extends Event{
        int ID = 0x070000;
        Item items[];
        String description;
    }
    
    class Door extends Event{
        int ID = 0x080000;
        
    }
    
    class Friend extends Event{
        int ID = 0x090000;
    }
    
    class Sign extends Event{
        int ID = 0x0A0000;
    }
