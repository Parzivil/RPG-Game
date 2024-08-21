/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package rpg.game;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author robin, matthew
 */

/*
********** Dear Matthew:
********** - COMMENT THE CODE YOU WRITE!!
********** - Also if it can go in a class it should go there (keep this code minimal and abstract)

***OKAY...xoxo ;)
*/

public class SoftwareConstuction_RPGGame {
    static Player player;
    
    //This decription was generated using chat GPT
    private final static String dungeonDescription = 
        "You find yourself in a dimly lit dungeon,\n"
      + "where the air is thick with mildew and the walls are cold and damp.\n"
      + "Flickering torches cast long shadows, and the floor is uneven, making each step treacherous.\n"
      + "The silence is broken only by distant dripping water and the skittering of unseen creatures.\n"
      + "\n"
      + "As you venture deeper, the corridors twist and turn,\n"
      + "leading to dead ends or eerie, abandoned chambers.\n"
      + "Yet, amid the oppressive darkness, you sense a faint draft; an indication that somewhere nearby,\n"
      + "a hidden door awaits discovery...\n\n\n";
    
    private final static String doorDescription = 
            "As you carefully trace the cold, damp wall with your hand, \n"
            + "you suddenly feel a subtle shift—a stone that gives way under your touch. \n"
            + "A low rumble echoes through the corridor as a hidden door swings open, \n"
            + "revealing a narrow passageway beyond. \n"
            + "The draft grows stronger, carrying with it a musty smell, \n"
            + "and the faintest hint of something unknown lurking in the darkness ahead... \n\n\n";
             

    static Saver save = new Saver("path.path"); //Object to save
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); //Scanner object to take in inputs
        
        boolean game_on = true; //Current Game state
        int difficulty = 0;
        
        player = new Player(
                Game.ask("Please enter a name for your character. ", scan), 
                new Location(0, 0), 55,0);
        
        
        player.location.setHeading(Location.Direction.NORTH); //Set default player location
        
        
        ArrayList<Object> others = new ArrayList<>(); //The arraylist of everything thats not a character.
        
        Weapon Start_Weapon = new Weapon("Wooden Sword of weakness",new Location(20,20), 2,2,2);
        
        
        while(difficulty <= 0 || difficulty >= 4) //finding out what difficulty they want.
        {
            difficulty = Game.askNum("Hello "+player.name+"\nPlease enter a difficulty \n1) Easy\n2) Medium\n3) Hard\n",scan);
        }
        
        switch(difficulty) //this is the setup for the while game loop
        {
            case Game.EASY: //easy difficulty is a find the door with 3 enemies
            {
                //Add the enemies to the game
                Game.enemies.add(new Character("Gob - Skeleton", new Location(0,1), 10,false));
                Game.enemies.add(new Character("Job - Skeleton", new Location(0,0), 10,false));
                Game.enemies.add(new Character("Bob - Skeleton", new Location(0,0), 10,false));
                
                //Create a location with a description
                Location dungeon = new Location(0, 0);
                dungeon.giveDescription(dungeonDescription);
                player.location = dungeon;
                
                Location doorLocation = new Location(15, 15);
                doorLocation.giveDescription(doorDescription);
                
                //for(Character e : enemies) //For loop giving random locations within a set area.
                //{
                    //e.location.random_location();
                //}
                Object Door = new Object("Door", doorLocation,true);
                player.GiveItem(Start_Weapon);
                player.equip(Start_Weapon);
                player.location.heading = Location.Direction.NORTH; //Set heading
                
                dungeon.describeLocation();
                
                System.out.println("** For all the options type 'options' **\n");
                
                //BUG!
                //This loop runs twice on every action causing the direction to only be north and south
                while(game_on)
                {
                    //Why does this print twice?
                    Game.print("You are at: "+player.location.toString()); //reoccuring message informing player of location.
                    String input = Game.ask("What would you like to do? \n",scan).toUpperCase().strip();

                    switch(input)
                    {
                        case Game.OPTIONS: //prints out a list of options for things they can do
                            Game.println("Your options are: "
                                    + "\nAttack"
                                    + "\nInteract"
                                    + "\nMove Forward"
                                    + "\nMove Backwards"
                                    + "\nTurn Left"
                                    + "\nTurn Right"
                                    + "\nShow Inventory"
                                    + "\nSet main hand");
                        case Game.ATTACK: //Attacks the square infront of them.
                            Attack(Game.enemies,(Weapon)player.get_main_hand());
                            break;
                        case Game.INTERACT: //Will Scan infront and interact with it

                            break;
                        case Game.MOVE_FORWARD:
                            player.Say("Moved forward");
                            player.move(player.location.heading);
                            break;
                        case Game.MOVE_BACKWARDS:
                            player.Say("Moved backward");
                            player.move_back(); //Do we need this? Could the player just turn 180?
                            break;
                        case Game.TURN_LEFT:
                            player.Say("Turned Left and if facing : " + player.location.heading.toString());
                            player.TURN_LEFT();
                            break;
                        case Game.TURN_RIGHT:
                            player.Say("Turned Right and if facing : " + player.location.heading.toString());
                            player.TURN_RIGHT();
                            break;
                        case Game.SHOW_INVENTORY:
                            Game.print(player.Show_Inventory());
                            break;
                        case Game.SET_MAIN_HAND:
                            int in = Game.askNum("What would you like to be in your hand?\n(Using the number)\n"+ player.Show_Inventory(),scan);
                            player.equip(player.inventory.get(in));
                            Game.print(player.get_main_hand().name+" is now in your hand.");
                            break;
                        case Game.LOOK:    

                            break;
                    } 

                    //Check if the door has been found
                    if(player.location.xPosition == doorLocation.xPosition && player.location.yPosition == doorLocation.yPosition){
                        doorLocation.describeLocation(); //Describe the door
                    }
                }   
            break;
                
            }
            case Game.MEDIUM: //medium is find the treasure with 5 enemies
            {
                break;
                
            }
            case Game.HARD: //defeat the boss and his 6 henchmen
            {
                break;
            }
        }
        
        
    } 
    public static void Attack(ArrayList<Character> enemies, Weapon Wep) //Checks for enemy and attacks if there is one. Should also have a object check and a wall check.
    {
        if(Enemy_CHECK(enemies) != null)
        {
            player.attack(Enemy_CHECK(enemies),Wep);
        }
        else
        {
            Game.print("It does nothing.\n");
        }
    }

        
    public static Character Enemy_CHECK(ArrayList<Character> enemies) //looks for an enemy infront of them.
    {
        int x_char = 0;
        int y_char = 0;
        switch(player.location.heading) //sets the location of the square that is being checked
        {
            case NORTH:
                y_char = player.location.yPosition + 1;
                x_char = player.location.xPosition;
                break;
            case SOUTH:
                y_char = player.location.yPosition - 1;
                x_char = player.location.xPosition;
                break;
            case EAST:
                x_char = player.location.xPosition + 1;
                y_char = player.location.yPosition;
                break;
            case WEST:
                x_char =  player.location.xPosition - 1;
                y_char = player.location.yPosition;
                break;
        }
        for(Character e : enemies) //checking through all enemy positions
        {
            int x = e.location.xPosition;
            int y = e.location.yPosition;
            if((y_char == y) && (x_char == x))
            {
                return e;
            }
        }
        return null;
    }
    
}
