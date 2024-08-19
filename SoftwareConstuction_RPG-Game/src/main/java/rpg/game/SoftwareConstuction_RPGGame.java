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

public class SoftwareConstuction_RPGGame {
    static Player player;
    
    static Saver save = new Saver("C:\\Users\\robin\\downloads");
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        boolean game_on = true; //Current Game state
        int difficulty=0;
        player = new Player(
                Game.ask("Please enter a name for your character. ", scan), 
                new Location(0, 0), 55,0);
        player.location.setHeading(Location.Direction.NORTH); //Set default player location
        ArrayList<Character> enemies = new ArrayList<>();
        Item bob = new Weapon("Bob",new Location(0,0), 2,2,2);
        player.GiveItem(bob);
        while(difficulty <= 0 || difficulty >= 4)
        {
            difficulty = Game.askNum("Hello "+player.name+"\nPlease enter a difficulty \n1) Easy\n2) Medium\n3) Hard\n",scan);
        }
        
        switch(difficulty) //this is the setup for the while game loop
        {
            case Game.EASY: //easy difficulty is a find the door with 3 enemies
            {
                
                enemies.add(new Character("Skeleton", new Location(0,0), 15,false));
                enemies.add(new Character("Skeleton", new Location(0,0), 15,false));
                enemies.add(new Character("Skeleton", new Location(0,0), 15,false));
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
                    Game.println("Your options are: \nAttack\nInteract\nMove Forward\nMove Backwards\nTurn Left\nTurn Right");
                case Game.ATTACK:
                    Attack(enemies,bob);
                    break;
                case Game.INTERACT:
                    
                case Game.MOVE_FORWARD:
                    player.move(player.location.heading);
                    break;
                case Game.MOVE_BACKWARDS:
                    player.move_back();
                    break;
                case Game.TURN_LEFT:
                    TURN_LEFT();
                    break;
                case Game.TURN_RIGHT:
                    
                    break;
            } 
        } 
        
    } 
    public static void Attack(ArrayList<Character> enemies, Weapon bob)
    {
        if(Enemy_CHECK(enemies) != null)
        {
            player.attack(Enemy_CHECK(enemies),bob);;
        }
    }
    public static void TURN_LEFT()
    {
        if(player.location.heading.equals(Location.Direction.NORTH)){player.location.setHeading(Location.Direction.WEST);}
        if(player.location.heading.equals(Location.Direction.SOUTH)){player.location.setHeading(Location.Direction.EAST);}
        if(player.location.heading.equals(Location.Direction.EAST)){player.location.setHeading(Location.Direction.NORTH);}
        if(player.location.heading.equals(Location.Direction.WEST)){player.location.setHeading(Location.Direction.SOUTH);}
    }
    public static void TURN_RIGHT()
    {
        if(player.location.heading.equals(Location.Direction.NORTH)){player.location.setHeading(Location.Direction.EAST);}
        if(player.location.heading.equals(Location.Direction.SOUTH)){player.location.setHeading(Location.Direction.WEST);}
        if(player.location.heading.equals(Location.Direction.EAST)){player.location.setHeading(Location.Direction.SOUTH);}
        if(player.location.heading.equals(Location.Direction.WEST)){player.location.setHeading(Location.Direction.NORTH);}
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

    public static boolean check()
    {

        
        return false;
    }
    
}
