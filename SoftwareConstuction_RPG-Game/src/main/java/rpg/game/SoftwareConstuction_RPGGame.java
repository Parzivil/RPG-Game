/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package rpg.game;
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
        
        while(difficulty <= 0 || difficulty >= 4)
        {
            difficulty = Game.askNum("Hello "+player.name+"\nPlease enter a difficulty \n1) Easy\n2) Medium\n3) Hard\n",scan);
        }
        
        switch(difficulty) //this is the setup for the while game loop
        {
            case Game.EASY: //easy difficulty is a find the door with 3 enemies
            {
                Character skelly_1 = new Character("Skeleton", new Location(0,7), 15,false);
                Character skelly_2 = new Character("Skeleton", new Location(0, 1), 15,false);
                Character skelly_3 = new Character("Skeleton", new Location(4, 5), 15,false);
                
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
                    Attack();
                    break;
                case Game.INTERACT:
                    
                case Game.MOVE_FORWARD:
                    MOVE_FORWARD();
                    break;
                case Game.MOVE_BACKWARDS:
                    MOVE_BACKWARDS();
                    break;
                case Game.TURN_LEFT:
                    TURN_LEFT();
                    break;
                case Game.TURN_RIGHT:
                    
                    break;
            } 
        } 
        
    } 
    public static void Attack()
    {
        if(player.location.heading.equals(Location.Direction.NORTH)){int y = player.location.yPosition;}
        if(player.location.heading.equals(Location.Direction.SOUTH)){player.location.yPosition--;}
        if(player.location.heading.equals(Location.Direction.EAST)){player.location.xPosition++;}
        if(player.location.heading.equals(Location.Direction.WEST)){player.location.xPosition--;}
    }
    public static void MOVE_FORWARD()
    {
        if(player.location.heading.equals(Location.Direction.NORTH)){player.location.yPosition++;}
        if(player.location.heading.equals(Location.Direction.SOUTH)){player.location.yPosition--;}
        if(player.location.heading.equals(Location.Direction.EAST)){player.location.xPosition++;}
        if(player.location.heading.equals(Location.Direction.WEST)){player.location.xPosition--;}
    }
    public static void MOVE_BACKWARDS()
    {
        if(player.location.heading.equals(Location.Direction.NORTH)){player.location.yPosition--;}
        if(player.location.heading.equals(Location.Direction.SOUTH)){player.location.yPosition++;}
        if(player.location.heading.equals(Location.Direction.EAST)){player.location.xPosition--;}
        if(player.location.heading.equals(Location.Direction.WEST)){player.location.xPosition++;}
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
}
