package rpg.game;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author robin, matthew
 */
public class Character extends Game {
    String name;
    Location location;
    String race;
    ArrayList<Item> inventory = new ArrayList<>(); //Stores players items
    public Item main_hand;
    public State state;
    int health;
    
    
    private final int unconsiousLimit = 3;
    
    //Options for the character to say when damaged (Partially AI genereated) 
    public String deathEmotes[] = {"Collapses dramatically...", "Gone too soon...", "The end is nigh...", "Fallen...", "Fade to black..."};
    public String damageEmotes[] = {
        "Ow, that hurt!", "Seriously?", "That’s gonna leave a mark!", 
        "I felt that one!", "Not cool, bro!", "Ouch!", 
        "Why me?", "That was uncalled for!", "You hit like a truck!", 
        "Really?", "Could you not?", "That’s gonna sting later.", 
        "You’re asking for it!", "Hey, watch it!", "Come on, I just healed!"
    };    
    public String imortalEmotes[] = {"You can't defeat me!", "Is that your best?", "Still standing!", "Invincible, remember?", "Nice try, but I'm still here!"};
    public String unconsciousEmotes[] = {"*is out cold*", "Down for the count...", "Lights out...", "Fainted...", "*is knocked out*"};

    /**
     * Stores the current life state of the character
     */
    enum State{
        ALIVE,
        DEAD,
        UNCONSIOUS,
        IMMORTAL
    }

    /**
     *  Creates a new character
     * @param name
     * @param race
     * @param location
     * @param health 
     */
    public Character(String name,String race, Location location, int health){
        this.name = name;
        this.race = race;
        this.location = location;
        this.health = health;
        
        //Depending on the health given in the constructor sets the state
        if(health == 0) state = State.DEAD;
        else if(health == Integer.MAX_VALUE) state = State.IMMORTAL;
        else if(health < 0) state = State.UNCONSIOUS;
        else state = State.ALIVE;
    }
    
    
    /**
     *Set an entire new emote set
     * Array order:
     *  1. Death
     *  2. Damage
     *  3. Immortal
     *  4. Unconscious 
     * @param emotes
     */
    public void setEmotes(String emotes[][]){
        this.deathEmotes = emotes[0];
        this.damageEmotes = emotes[1];
        this.imortalEmotes = emotes[2];
        this.unconsciousEmotes = emotes[3];
    }
    
    /**
     * Deals the character damage using a weapon
     * @param weapon 
     */
    public void doDamageWith(Item weapon){
        
        //Condition if they stab themselves
        if(this.inventory.contains(weapon)){
            System.out.println("\n\n"+ this.race +" - "+ this.name + " attacks themselves with " + weapon.name + " for " + weapon.damage + " damage!");
        }
        else System.out.println("\n\n"+ this.race +" - "+ this.name + " is attacked with " + weapon.name + " for " + weapon.damage + " damage!");
        this.doDamage(weapon.damage);
    }
    
    /**
     * Private function of character which deals the damage to the player
     * and determines if the character state needs to change
     * @param damage 
     */
    private void doDamage(int damage){
        switch(state){
            
            //Alive
            case ALIVE:
                health -= damage; //Change the damage done to the character
        
                if(health <= 0) this.Kill();
                else if(health < unconsiousLimit) {
                    state = State.UNCONSIOUS;
                    this.Say(randomString(unconsciousEmotes));
                    System.out.println(this.name + " goes unconsious");
                }
                else{
                    this.Say(randomString(damageEmotes));
                }
            break;
            
            //Dead
            case DEAD:
                this.Say("I am already dead...");
            break;
            
            //Unconsious 
            case UNCONSIOUS:
                health -= damage; //Change the damage done to the character
        
                if(health <= 0) this.Kill();
                else{
                    this.Say(randomString(unconsciousEmotes));
                }
            break;
            
            //Imortal
            case IMMORTAL:
                this.Say(randomString(imortalEmotes));
            break;
        } 
     }

    /**
     * Kills the character, then says one of the death emotes
     */
    public void Kill(){
        state = State.DEAD;
        this.Say(randomString(deathEmotes));
    }
    
    /**
     * Gives the character an item and speaks in third person about it being given
     * @param item 
     */
    public void GiveItem(Item item){
        System.out.println("\n" + this.name + " is given " + item.name);
        inventory.add(item);
        item.location = this.location;
    }
    
    /**
    *Gives an item to the character silently
     * @param item
    */
    public void GiveItem_silent(Item item){
        inventory.add(item);
        item.location = this.location;
    }
    
    /**
     * Overwrites the characters entire inventory to the value of the array
     * Used for quickly setting the characters inventory
     * @param inventory 
     */
    public void setInventory(ArrayList<Item> inventory){
        this.inventory = inventory;
    }
    
    /**
     * Removes an item from the characters inventory
     * @param item 
     */
    public void RemoveItem(Item item){
        inventory.remove(item);
        item.location = this.location;
    }
    
    /**
     * Speaks a message from the character
     * @param message 
     */
    public void Say(String message){
        System.out.println(this.name + ": " + message);
    }
    
    /**
     * Returns a string listing all the items in the characters inventory
     * @return 
     */
    public String Show_Inventory()
    {
        int i = 0;
        StringBuilder line = (new StringBuilder());
        for(Item I : inventory)
        {
            line.append(i).append(", \"").append(I.name).append("\" weight = ").append(I.weight).append("\n");
            i++;
        }
        return line.toString();
    }

    /**
     * Makes an item the main weapon/item in the characters hand
     * @param itm 
     */
    public void equip(Item itm)
    {
        this.main_hand = itm;
    }
    
    /**
     * Returns the item which is currently set to the characters main item
     * @return 
     */
    public Item getMainHandItem()
    {
        return this.main_hand;
    }
    
    /**
     * Returns a string that lists the characters current stats
     * @return 
     */
    public String checkStats(){
        String stats = "";
        stats += "Name = " + this.name;
        stats += "\nHealth = " + this.health;
        stats += "\nState = " + this.state.toString() + "\n";
        return stats;
    }
    
    
    /*
    **********************************************
        **** Saving and Loading functions ****
    **********************************************
    */
    
    
    /**
     * Converts the character object to a JSON object for file saving
     * @return 
     */
    public JSONObject characterToJSON(){
        JSONObject jo = new JSONObject();
        jo.put("name", name);
        jo.put("health", health);
        jo.put("location", location.locationToJSON());
        jo.put("inventory", inventoryToJSONArray());
        jo.put("race", race);
        jo.put("state", state.toString());
        return jo;
    }

    /**
     * Takes the inventory array and converts it to a JSON array
     * to be saved 
     * @return 
     */
    private JSONArray inventoryToJSONArray(){
        JSONArray ja = new JSONArray();
        
        for(Item item : this.inventory){
            ja.put(item.itemToJSON());
        }
        return ja;
    }
}
