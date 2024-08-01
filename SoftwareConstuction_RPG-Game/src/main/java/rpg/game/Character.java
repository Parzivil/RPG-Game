package rpg.game;
import java.util.ArrayList;
/**
 * Character class for the game
 * @author Robin
 */
public class Character extends Game{
    String name;
    Location location;
    private ArrayList<Item> inventory = new ArrayList<Item>(); //Stores players items
    private State state;
    int health;
    
    private int unconsiousLimit = 3;
    
    //Options for the character to say when damaged

    /**
     *
     */
    public String deathEmotes[] = {"Oh man"};

    /**
     *
     */
    public String damageEmotes[] = {"Ouch", "Far out that hurt", "Man"};

    /**
     *
     */
    public String imortalEmotes[] = {"Hahaha nice try", "Really trying to attack an imortal?"};

    /**
     *
     */
    public String uncontiousEmotes[] = {"Ooof"};
    
    enum State{
        ALIVE,
        DEAD,
        UNCONSIOUS,
        IMPORTAL
    }
    
    //Character Contstructor

    /**
     *
     * @param name
     * @param location
     * @param health
     */
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
    
    //Move one place in any direction

    /**
     *
     * @param dir
     */
    public void move(Location.Direction dir){
        switch(dir){
            case dir.NORTH:
                this.location.yPosition++;
            break;
            
            case dir.SOUTH:
                this.location.yPosition--;
            break;
            
            case dir.EAST:
                this.location.xPosition++;
            break;
            
            case dir.WEST:
                this.location.xPosition--;
            break;
        }
    }
    
    //Change the default limit

    /**
     * Set the value which bellow the character becomes Unconsious
     * @param limit
     */
    public void setUnconsiousLimit(int limit){
        this.unconsiousLimit = limit;
    }


    /**
     *Set an entire new emote set
     * Array order:
     *  1. Death
     *  2. Damage
     *  3. Imortal
     *  4. Unconsious 
     * @param emotes
     */
    public void setEmotes(String emotes[][]){
        this.deathEmotes = emotes[0];
        this.damageEmotes = emotes[1];
        this.imortalEmotes = emotes[2];
        this.uncontiousEmotes = emotes[3];
    }
    
    /**
     * Returns the current state of the character
     * @return
     */
    public State getState() { return this.state;}
    

    /**
     *Do damage using a weapon
     * @param weapon
     */
    public void doDamageWith(Weapon weapon){
        
        //Condition if they stab themselves
        if(this.inventory.contains(weapon)){
            System.out.println("\n"+ this.name + " attacks themselves with " + weapon.name + " for " + weapon.damage + " damage!");
        }
        else System.out.println("\n"+ this.name + " is attacked with " + weapon.name + " for " + weapon.damage + " damage!");
        this.doDamage(weapon.damage);
    }
    

    /**
     *Affects health and says a message
     * @param damage
     */
    public void doDamage(int damage){
        switch(state){
            
            //Alive
            case State.ALIVE:
                health -= damage; //Change the damage done to the character
        
                if(health <= 0) this.Kill();
                else if(health < unconsiousLimit) {
                    state = State.UNCONSIOUS;
                    this.Say(randomString(uncontiousEmotes));
                    System.out.println(this.name + " goes unconsious");
                }
                else{
                    this.Say(randomString(damageEmotes));
                }
            break;
            
            //Dead
            case State.DEAD:
                this.Say("I am already dead...");
            break;
            
            //Unconsious 
            case State.UNCONSIOUS:
                health -= damage; //Change the damage done to the character
        
                if(health <= 0) this.Kill();
                else{
                    this.Say(randomString(uncontiousEmotes));
                }
            break;
            
            //Imortal
            case State.IMPORTAL:
                this.Say(randomString(imortalEmotes));
            break;
        } 
     }

    /**
     * Kill the character
     */
    public void Kill(){
        state = State.DEAD;
        this.Say(randomString(deathEmotes));
    }
    
    /**
     * Gives an item to the character and adds it to their inventory
     * @param item
     */
    public void GiveItem(Item item){
        System.out.println("\n" + this.name + " is given " + item.name);
        inventory.add(item);
    }
    
    /**
     * Removes an item from the characters inventory
     * @param item
     */
    public void RemoveItem(Item item){
        inventory.remove(item);
    }
    
    /**
     * Speaks a message as the character
     * @param message
     */
    public void Say(String message){
        System.out.print(this.name + ": ");
        System.out.println(message);
    }
}
