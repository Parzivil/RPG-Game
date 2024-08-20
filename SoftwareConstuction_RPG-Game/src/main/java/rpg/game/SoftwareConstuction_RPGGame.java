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

//// EVERYTHING IN HERE CAN BE MODIFYED, CURRENTLY FOR TESTING THINGS ////

/*
********** Dear Matthew:
********** - COMMENT THE CODE YOU WRITE!!
********** - Also if it can go in a class it should go there (keep this code minimal and abstract)

***OKAY...
*/

public class SoftwareConstuction_RPGGame {
    static Player player;

    static Saver save = new Saver("C:\\Users\\robin\\downloads"); //Object to save
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        boolean game_on = true; //Current Game state
        int difficulty=0;
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
                
                Game.enemies.add(new Character("Skeleton", new Location(0,1), 10,false));
                Game.enemies.add(new Character("Skeleton", new Location(0,0), 10,false));
                Game.enemies.add(new Character("Skeleton", new Location(0,0), 10,false));
                //for(Character e : enemies) //For loop giving random locations within a set area.
                //{
                    //e.location.random_location();
                //}
                Object Door = new Object("Door",new Location(15,15),true);
                player.GiveItem(Start_Weapon);
                player.equip(Start_Weapon);
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
        
        while(game_on)
        {
            String input;
            Game.print("you are at"+player.location.toString()); //reoccuring message informing player of location.
            input = Game.ask("What would you like to do? \nFor all the options type 'options'\n",scan).toUpperCase().strip();
            
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
                    player.move(player.location.heading);
                    break;
                case Game.MOVE_BACKWARDS:
                    player.move_back();
                    break;
                case Game.TURN_LEFT:
                    player.TURN_LEFT();
                    break;
                case Game.TURN_RIGHT:
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
