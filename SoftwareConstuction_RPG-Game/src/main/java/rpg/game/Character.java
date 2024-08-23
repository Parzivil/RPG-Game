package rpg.game;
import java.util.ArrayList;
import org.json.JSONObject;

public class Character extends Game {
    String name;
    Location location;
    String race;
    ArrayList<Item> inventory = new ArrayList<>(); //Stores players items
    public Item main_hand;
    public State state;
    int health;
    boolean boss;
    
    
    private int unconsiousLimit = 3;
    
    //Options for the character to say when damaged
    public String deathEmotes[] = {"Dies of embarisment..."};
    public String damageEmotes[] = {"Ouch", "Far out that hurt", "Man"};
    public String imortalEmotes[] = {"Hahaha nice try", "Really trying to attack an imortal?"};
    public String unconsciousEmotes[] = {"Ooof"};
    
    enum State{
        ALIVE,
        DEAD,
        UNCONSIOUS,
        IMMORTAL
    }

    public Character(String name,String race, Location location, int health, boolean boss){
        this.name = name;
        this.race = race;
        this.location = location;
        this.health = health;
        this.boss = boss;
        
        //Depending on the health given in the constructor sets the state
        if(health == 0) state = State.DEAD;
        else if(health == Integer.MAX_VALUE) state = State.IMMORTAL;
        else if(health < 0) state = State.UNCONSIOUS;
        else state = State.ALIVE;
    }
    public void makeMove() {moves++;} //Make a move

    public void move(Location.Direction dir){
        switch(dir){
            case NORTH:
                //unless y = 15
                this.location.yPosition++;
            break;
            
            case SOUTH:
                //unless y = -15
                this.location.yPosition--;
            break;
            
            case EAST:
                //unless x = 15
                this.location.xPosition++;
            break;
            
            case WEST:
                //unless x = -15 - print out there is a wall in the way
                this.location.xPosition--;
            break;
        }
        this.makeMove();
    }

    public void move_back(){
        switch(this.location.heading){
            case NORTH:
                //unless y = -15
                this.location.yPosition--;
            break;
            
            case SOUTH:
                //unless y = 15
                this.location.yPosition++;
            break;
            
            case EAST:
                //unless y = -15
                this.location.xPosition--;
            break;
            
            case WEST:
                //unless y = 15
                this.location.xPosition++;
            break;
        }
        this.makeMove();
    }
    
    public void Turn_Left()
    {
        switch(this.location.heading){
            case NORTH:
                //unless y = -15
                this.location.setHeading(Location.Direction.WEST);
            break;
            case SOUTH:
                //unless y = 15
                this.location.setHeading(Location.Direction.EAST);
            break;
            case EAST:
                //unless y = -15
                this.location.setHeading(Location.Direction.NORTH);
            break;
            case WEST:
                //unless y = 15
                this.location.setHeading(Location.Direction.SOUTH);
            break;
        }
        this.makeMove();
    }

    public void Turn_Right()
    {
        switch(this.location.heading){
            case NORTH:
                //unless y = -15
                this.location.setHeading(Location.Direction.EAST);
            break;
            case SOUTH:
                //unless y = 15
                this.location.setHeading(Location.Direction.WEST);
            break;
            case EAST:
                //unless y = -15
                this.location.setHeading(Location.Direction.SOUTH);
            break;
            case WEST:
                //unless y = 15
                this.location.setHeading(Location.Direction.NORTH);
            break;
        }
        this.makeMove();
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
    
    public State getState() { return this.state;}
    
    public void doDamageWith(Item weapon){
        
        //Condition if they stab themselves
        if(this.inventory.contains(weapon)){
            System.out.println("\n"+ this.name + " attacks themselves with " + weapon.name + " for " + weapon.damage + " damage!");
        }
        else System.out.println("\n"+ this.name + " is attacked with " + weapon.name + " for " + weapon.damage + " damage!");
        this.doDamage(weapon.damage);
    }
    

    public void doDamage(int damage){
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

    public void Kill(){
        state = State.DEAD;
        this.Say(randomString(deathEmotes));
    }
    
    public void GiveItem(Item item){
        System.out.println("\n" + this.name + " is given " + item.name);
        inventory.add(item);
    }
    
    //Gives an item silently
    public void GiveItem_silent(Item item){
        inventory.add(item);
    }
    
    public void setInventory(ArrayList<Item> inventory){
        this.inventory = inventory;
    }
    
    public void RemoveItem(Item item){
        inventory.remove(item);
    }
    
    public void Say(String message){
        System.out.print(this.name + ": ");
        System.out.println(message);
    }
    
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

    public void equip(Item itm)
    {
        this.main_hand = itm;
    }
    public Item get_main_hand()
    {
        return this.main_hand;
    }
    
    public JSONObject characterToJSON(){
        JSONObject jo = new JSONObject();
        jo.put("name", name);
        jo.put("health", health);
        jo.put("location", location.locationToJSON());
        jo.put("inventory", inventoryToJSON());
        jo.put("race", race);
        jo.put("state", state.toString());
        return jo;
    }
    
    private JSONObject inventoryToJSON(){
        JSONObject jo = new JSONObject();
        
        for(Item item : this.inventory){
            jo.put("item", item.itemToJSON());
        }
        return jo;
    }
    
    public String checkStats(){
        String stats = "";
        
        stats += "Health = " + this.health;
        stats += "\nState = " + this.state.toString() + "\n";
        
        return stats;
    }
}
