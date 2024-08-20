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
        
        ArrayList<Character> enemies = new ArrayList<>();
        
        Weapon bob = new Weapon("Wooden Sword of weakness",new Location(20,20), 2,2,2);
        player.GiveItem(bob);
        
        
        while(difficulty <= 0 || difficulty >= 4)
        {
            difficulty = Game.askNum("Hello "+player.name+"\nPlease enter a difficulty \n1) Easy\n2) Medium\n3) Hard\n",scan);
        }
        
        switch(difficulty) //this is the setup for the while game loop
        {
            case Game.EASY: //easy difficulty is a find the door with 3 enemies
            {
                
                enemies.add(new Character("Skeleton", new Location(0,0), 10,false));
                enemies.add(new Character("Skeleton", new Location(0,0), 10,false));
                enemies.add(new Character("Skeleton", new Location(0,0), 10,false));
                for(Character e : enemies)
                {
                    e.location.random_location();
                }
                Object Door = new Object("Door",new Location(15,15),true);
                
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
            Game.print("you are at"+player.location.toString());
            input = Game.ask("What would you like to do? \nFor all the options type 'options'\n",scan);
            String inputUpperCase = input.toUpperCase();
            
            switch(inputUpperCase)
            {
                case Game.OPTIONS:
                    Game.println("Your options are: "
                            + "\nAttack"
                            + "\nInteract"
                            + "\nMove Forward"
                            + "\nMove Backwards"
                            + "\nTurn Left"
                            + "\nTurn Right"
                            + "\nShow Inventory"
                            + "\nSet main hand");
                case Game.ATTACK:
                    Attack(enemies,(Weapon)player.get_main_hand());
                    break;
                case Game.INTERACT:
                    
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
                    String in = Game.ask("What would you like to be in your hand?\n(Using the number)\n"+ player.Show_Inventory(),scan);
                    int h = Integer.valueOf(in);
                    player.equip(player.inventory.get(h));
                   
            } 
        }   
    } 
    public static void Attack(ArrayList<Character> enemies, Weapon Wep)
    {
        if(Enemy_CHECK(enemies) != null)
        {
            player.attack(Enemy_CHECK(enemies),Wep);
        }
    }

        
    public static Character Enemy_CHECK(ArrayList<Character> enemies)
    {
        
        int x_char = 0;
        int y_char = 0;
        switch(player.location.heading)
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
        for(Character e : enemies)
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
