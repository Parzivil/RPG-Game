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
    
    final static int EASY = 1;
    final static int MEDIUM = 2;
    final static int HARD = 3;
    static Event events = new Event();
    final static String OPTIONS = "OPTIONS";
    final static String ATTACK = "ATTACK";
    final static String INTERACT = "INTERACT";
    final static String MOVE_FORWARD = "MOVE FORWARD";
    final static String MOVE_BACKWARDS = "MOVE BACKWARDS";
    final static String TURN_LEFT = "TURN LEFT";
    final static String TURN_RIGHT = "TURN RIGHT";
    
    Game game = new Game();
    
    static Player player;
    
    //static Character skelly = new Character("Skeleton", new Location(0, 1), 15);

    //static Enemy skel = new Enemy(skelly, new String[] {"Hello there", "g"});
    //static Chest chst = new Chest();
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        /*println("THIS IS A TEST, NOT THE GAME");
        
        String name = ask("What is your name? ", scan);
        
        player = new Player(name, new Location(0, 0), 55);
        
        println("Hello Knight " + player.name);
        
        Weapon sword = new Weapon("Sword", player.location, 1, 5, 5);
        
        player.GiveItem(sword);
        
        System.out.println(chst.location.xPosition);
        
        player.doDamageWith(sword);
        
        player.attack(skelly, sword); */
        boolean game_on = true;
        String name;
        int difficulty=0;
        name = ask("Please enter a name for your character. ",scan);
        player = new Player(name, new Location(0, 0), 55);
        player.location.setHeading(Location.Direction.NORTH);
        while(difficulty <= 0 || difficulty >= 4)
        {
            difficulty = askNum("Hello "+player.name+"\nPlease enter a difficulty \n1) Easy\n2) Medium\n3) Hard\n",scan);
        }
        
        
        switch(difficulty) //this is the setup for the while game loop
        {
            case EASY: //easy difficulty is a find the door with 3 enemies
            {
                Character skelly_1 = new Character("Skeleton", new Location(0, 1), 15);
                Character skelly_2 = new Character("Skeleton", new Location(0, 1), 15);
                Character skelly_3 = new Character("Skeleton", new Location(4, 5), 15);
                break;
            }
            case MEDIUM: //medium is find the treasure with 5 enemies
            {
                
                break;
            }
            case HARD: //defeat the boss and his 6 henchmen
            {
                
                break;
            }
        }
        while(game_on)
        {
            String input;
            Location.Direction heading = player.location.heading;
            print("you are at"+player.location.toString());
            input = ask("What would you like to do? \nFor all the options type 'options'\n",scan);
            String inputUpperCase = input.toUpperCase();
            switch(inputUpperCase)
            {
                case OPTIONS:
                    println("Your options are: \nAttack\nInteract\nMove Forward\nMove Backwards\nTurn Left\nTurn Right");
                case ATTACK:
                    
                case INTERACT:
                    
                case MOVE_FORWARD:
                    if(heading.equals(Location.Direction.NORTH)){player.location.yPosition++;}
                    if(heading.equals(Location.Direction.SOUTH)){player.location.yPosition--;}
                    if(heading.equals(Location.Direction.EAST)){player.location.xPosition++;}
                    if(heading.equals(Location.Direction.WEST)){player.location.xPosition--;}
                    break;
                case MOVE_BACKWARDS:
                    if(heading.equals(Location.Direction.NORTH)){player.location.yPosition--;}
                    if(heading.equals(Location.Direction.SOUTH)){player.location.yPosition++;}
                    if(heading.equals(Location.Direction.EAST)){player.location.xPosition--;}
                    if(heading.equals(Location.Direction.WEST)){player.location.xPosition++;}
                    break;
                case TURN_LEFT:
                    if(heading.equals(Location.Direction.NORTH)){player.location.setHeading(Location.Direction.WEST);}
                    if(heading.equals(Location.Direction.SOUTH)){player.location.setHeading(Location.Direction.EAST);}
                    if(heading.equals(Location.Direction.EAST)){player.location.setHeading(Location.Direction.NORTH);}
                    if(heading.equals(Location.Direction.WEST)){player.location.setHeading(Location.Direction.SOUTH);}
                    break;
                case TURN_RIGHT:
                    if(heading.equals(Location.Direction.NORTH)){player.location.setHeading(Location.Direction.EAST);}
                    if(heading.equals(Location.Direction.SOUTH)){player.location.setHeading(Location.Direction.WEST);}
                    if(heading.equals(Location.Direction.EAST)){player.location.setHeading(Location.Direction.SOUTH);}
                    if(heading.equals(Location.Direction.WEST)){player.location.setHeading(Location.Direction.NORTH);}
                    break;
            }
            
        }
        
        
    }
    
    
    
    
    //Print functions because I am sick of typing the system out thing

    /**
     *
     * @param str
     */
    public static void print(String str) {System.out.print(str);}

    /**
     *
     * @param str
     */
    public static void println(String str) {System.out.println(str);}
    
            /**
     *
     * @param question
     * @param scan
     * @return
     */
    public static String ask(String question, Scanner scan){
        System.out.print(question);
        return scan.nextLine();
    } 
    
    /**
     *
     * @param question
     * @param scan
     * @return
     */
    public static int askNum(String question, Scanner scan){ 
        do{
            System.out.print(question);
        
            try{
                return scan.nextInt(); //Break out of the loop
            }

            catch(Exception e){
                System.out.println("\n Input error, try again");         
            }
        } while(true);
    }
}
