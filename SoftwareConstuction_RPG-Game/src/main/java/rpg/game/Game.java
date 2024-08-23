/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;
import java.util.*;
/**
 *
 * @author robin
 */
public class Game{

    public Random rand = new Random();
    
    public final static String OPTIONS = "OPTIONS";
    public final static String ATTACK = "ATTACK";
    public final static String INTERACT = "INTERACT";
    public final static String MOVE_FORWARD = "MOVE FORWARD";
    public final static String MOVE_BACKWARDS = "MOVE BACKWARDS";
    public final static String TURN_LEFT = "TURN LEFT";
    public final static String TURN_RIGHT = "TURN RIGHT";
    public final static String SHOW_INVENTORY = "SHOW INVENTORY";
    public final static String SET_MAIN_HAND = "SET MAIN HAND";
    public final static String LOOK = "LOOK";
    static Player player;
    public static Weapon Start_Weapon = new Weapon("Wooden Sword of weakness",new Location(20,20), 2,2,2);
    public static int difficulty = 0;
    public static Location doorLocation = new Location(15, 15);
    public final static int EASY = 1;
    public final static int MEDIUM = 2;
    public final static int HARD = 3;
    
    
    public static boolean easy_game_on = true; //Current Game state
    public static boolean medium_game_on = false; //Current Game state
    
    public static boolean playing = true; //Current Game state
    public static ArrayList<Character> enemies = new ArrayList<>(); // The array list of enemies 
    public static ArrayList<Object> others = new ArrayList<>(); //The arraylist of everything thats not a character.
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
             
    private final static String pathDescription_1 = 
            "You are standing on cobblestone path"
            + " \nThere is nothing in front of you";
    private final static String pathDescription_2 =
            "You can feel a smooth stone beneath you"
            + "Cold to the touch";
    
    /**
     *
     */
    protected int moves = 0; //Then number of moves the player has made
    public static String get_dungeonDescription(){return dungeonDescription;}
    public static String get_doorDescription(){return doorDescription;}
    public static String get_pathDescription_1(){return pathDescription_1;}
    public static String get_pathDescription_2(){return pathDescription_2;}

    //Functions for generating random numbers
    public int random(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    public static int random(int max) {
        return (int) (Math.random() * max);
    }
    
    public String randomString(String strings[]){
        rand.setSeed(System.currentTimeMillis()); //Reset the seed
        return strings[rand.nextInt(strings.length)];
    }
    
     //Print functions because I am sick of typing the system out thing
    public static void print(String str) {System.out.print(str);}
    public static void println(String str) {System.out.println(str);}
    
    public static String ask(String question, Scanner scan){
        System.out.print(question);
        return scan.nextLine();
    } 
    
    public static int askNum(String question, Scanner scan)
    { 
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
    
    
    
    
    public static void Game_play()
   {
       Scanner scan = new Scanner(System.in); //Scanner object to take in inputs
        //Why does this print twice?
        Game.print("You are at: "+player.location.toString()+"\nFacing "+player.location.heading.toString()+"\n"); //reoccuring message informing player of location.
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
                break;
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
                player.move_back(); //Do we need this? Could the player just turn 180? // Sort of but thats not much easier.
                break;
            case Game.TURN_LEFT:
                player.Turn_Left();
                player.Say("You turned Left");
                break;
            case Game.TURN_RIGHT:
                player.Turn_Right();
                player.Say("You turned Right");
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
            case "TP PLAYER": //One of the admin commands
                player.location.xPosition = askNum("x = ",scan);
                player.location.yPosition = askNum("y = ",scan);
                break;
            case "LOCATE ENEMIES": //One of the admin commands
                for(Character e : enemies) //For loop giving random locations within a set area.
                {
                    print(e.name.toString()+" = "+e.location.toString()+"\n");
                }
                break;
        
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

    
    public static void Check_Easy_Completion()
    {
        Scanner scan = new Scanner(System.in); //Scanner object to take in inputs
        if(player.location.xPosition == doorLocation.xPosition && player.location.yPosition == doorLocation.yPosition)
        {
            doorLocation.describeLocation();//Describe the door
            String confirmation =  Game.ask("Would you like to move to the next level? \n ******YES or NO******\n", scan).toUpperCase();
            if(confirmation.equals("YES"))
            {
                easy_game_on = false;
                difficulty = MEDIUM;  
            }
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
    
    public static void Easy_Set_Up() //Sets up the easy difficulty
    {
        //Add the enemies to the game
        enemies.add(new Character("Gob","Skeleton",new Location(0,0), 10,false));
        enemies.add(new Character("Job","Skeleton", new Location(0,0), 10,false));
        enemies.add(new Character("Bob","Skeleton", new Location(0,0), 10,false));

        //Create a location with a description
        Location dungeon = new Location(0, 0);
        dungeon.giveDescription(Game.get_dungeonDescription());
        player.location = dungeon;

        
        doorLocation.giveDescription(Game.get_doorDescription());


        for(Character e : Game.enemies) //For loop giving random locations within a set area.
        {
            e.location.random_location();
        }
        Object Door = new Object("Door", doorLocation,true);
        player.GiveItem(Start_Weapon);
        player.equip(Start_Weapon);
        player.location.heading = Location.Direction.NORTH; //Set heading

        dungeon.describeLocation();

        System.out.println("** For all the options type 'options' **\n");
                
    }
   
}
