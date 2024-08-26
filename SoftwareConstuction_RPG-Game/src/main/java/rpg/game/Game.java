package rpg.game;
import java.util.*;
import org.json.*;
/**
 *
 * @author robin, matthew
 */
public class Game{

    //Game Saving and loading objects
    static SaveFile userSave = new SaveFile("src\\main\\java\\rpg\\game\\saveFile.json"); //Object to save
    static SaveFile easySave = new SaveFile("src\\main\\java\\rpg\\game\\Level_1_Save.json"); //Object to save
    static SaveFile mediumSave = new SaveFile("src\\main\\java\\rpg\\game\\Level_2_Save.json"); //Object to save
    static SaveFile hardSave = new SaveFile("src\\main\\java\\rpg\\game\\Level_3_Save.json"); //Object to save
    
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
    public final static String STATS = "STATS";
    public final static String SAVE = "SAVE";
    static Player player;
    
    //Starting weapons
    public static Item Easy_Weapon = new Item("Sword","Bad","Wooden", new Location(20,20), 2, 2);
    public static Item Medium_Weapon = new Item("Sword","Decent","Wooden", new Location(20,20), 2, 4);
    public static Item Hard_Weapon = new Item("Sword","Good","Wooden", new Location(20,20), 2, 6);
    //Completion weapons
    public static Item Easy_completion_weapon = new Item("Sword","Bad","Stone", new Location(20,20), 1, 5);
    public static Item Medium_completion_weapon = new Item("Sword","Decent","Stone", new Location(20,20), 2, 7);
    public static Item Hard_completion_weapon = new Item("Sword","Good","Stone", new Location(20,20), 1, 9);
    //Enemy weapon
    public static Item Skeleweapon_1 = new Item("Club","Bad","Bone", new Location(20,20), 1, 2);
    public static Item Skeleweapon_2 = new Item("Club","Okay","Bone", new Location(20,20), 1, 3);
    public static Item Skeleweapon_3 = new Item("Club","Strong","Bone", new Location(20,20), 1, 5);
    public static Item Boss_Weapon = new Item("Mace","Massive","Bone", new Location(20,20), 2, 15);
    
    public static int difficulty = 0;
    public static Location doorLocation = new Location(15, 15);
    public final static int EASY = 1;
    public final static int MEDIUM = 2;
    public final static int HARD = 3;
    public final static int LOAD = 4;
    
    
    public static boolean easy_game_on = false; //Current Game state
    public static boolean medium_game_on = false; //Current Game state
    public static boolean hard_game_on = false; //Current Game state
    
    public static boolean playing = true; //Current Game state
    public static boolean level_completed = false; //Current Game state
    
    public static ArrayList<Character> enemies = new ArrayList<>(); // The array list of enemies 
    public static ArrayList<Object> objects = new ArrayList<>(); //The arraylist of everything thats not a character.

    
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
             //   17 different path descriptions
   static String[] path = 
    {"You are standing on cobblestone path"
    + " \nThere is nothing in front of you\n",
    "You can feel a smooth stone beneath you\n"
    + "Cold to the touch\n", //GPT from here
    "In the dark, the stone path feels like a labyrinth, leading deeper into the unknown.\n",
    "The cold, smooth stones of the path are barely discernible as night swallows their form.\n",
    "The dim glow of lanterns reveals just enough of the stone path to hint at its winding course.\n",
    "The stone path stretches out into impenetrable darkness, disappearing into the void.\n",
    "The path beneath feels cold and rough, but its surroundings remain obscured by utter blackness.\n",
    "With no visible landmarks, the stone path is a solitary route through an all-encompassing gloom.\n",
    "The texture of the stone path is the only hint of direction in the pitch-black cave.\n",
    "The silence and darkness make the stone path seem like a thin thread connecting you to the unknown.\n",
    "The path’s surface glistens with moisture, hinting at the hidden wonders within the cave.\n",
    "Damp and uneven, the path in the dungeon echoes with every cautious step.\n",
    "The stone path in the dungeon is cold and uneven, lined with rusting metal and decaying remnants.\n",
    "Flickering torches cast eerie shadows along the damp, grimy stone path of the dungeon.\n",
    "The stone path is flanked by darkness that seems to close in as you walk.\n",
    "The oppressive silence of the dungeon makes each step on the stone path echo ominously.\n",
    "The path winds through the dungeon's dark corridors, illuminated only by the occasional flicker of a torch.\n"};
   
    static String[] wall = //all gpt
    {"The stone wall of the dungeon is rough-hewn and damp, covered in a dark patina of age and neglect.",
    "Cracks spiderweb through the cold, gray stones, hinting at the dungeon’s long-forgotten history.",
    "Moss and mildew cling to the uneven surface of the dungeon’s stone wall, adding to its eerie, neglected aura.",
    "Iron shackles and faded bloodstains mar the surface of the stone wall, whispering tales of past torment.",
    "The wall’s jagged stones are irregular and grimy, casting dark shadows in the flickering torchlight.",
    "The stone wall feels cold and unyielding to the touch, its rough surface scraping against your fingertips.",
    "The dampness of the stone wall seeps into your skin, making it feel clammy and unsettling.",
    "Uneven and jagged, the stone wall is uncomfortable to lean against, its surface rough and abrasive.",
    "The chill from the stone wall penetrates through your clothing, intensifying the dungeon’s oppressive atmosphere.",
    "The coarse texture of the stone wall is both harsh and unwelcoming, adding to the sense of confinement and desolation."};
    /**
     *
     */
    protected static int moves = 0; //Then number of moves the player has made
    public static String get_dungeonDescription(){return dungeonDescription;}
    public static String get_doorDescription(){return doorDescription;}

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
    
    
    public static void Difficulty_selector()
    {
        Scanner scan = new Scanner(System.in); //Scanner object to take in inputs
        while(Game.difficulty <= 0 || Game.difficulty >= 4) //finding out what difficulty they want.
        {
            Game.difficulty = Game.askNum("Hello "+Game.player.name+"\nPlease enter a difficulty \n1) Easy\n2) Medium\n3) Hard\n4) Load from previous save\n",scan);
            switch(Game.difficulty)
            {
                    ///IMPLEMENTED FOR TESTING, NEEDS PROPER IMPLMENTATION
                case Game.LOAD:
                    userSave.LoadGame();
                    break;
            }
        }
    }
    
    
    
    public static void Game_play()
   {
        Scanner scan = new Scanner(System.in); //Scanner object to take in inputs
        Game.print("You are at: "+player.location.toString()+"\nFacing "+player.location.heading.toString()+"\n"); //reoccuring message informing player of location.
        String input = Game.ask("What would you like to do? \n",scan).toUpperCase().strip();
        switch(input)
        {
            //JUST USED FOR TESTING
            //******
            case Game.SAVE:
                userSave.SaveGame(); //Save the game
            break;
            //****** Needs propper implementation (just copy the above function
            
            case Game.OPTIONS: //prints out a list of options for things they can do
                Game.println("Your options are: "
                        + "\nAttack"
                        + "\nLook"
                        + "\nMove Forward"
                        + "\nMove Backwards"
                        + "\nTurn Left"
                        + "\nTurn Right"
                        + "\nShow Inventory"
                        + "\nSet main hand"
                        + "\nStats");
                break;
                
            case Game.ATTACK: //Attacks the square infront of them.
                Attack(Game.enemies, player.get_main_hand());
                break;
                
            case Game.MOVE_FORWARD: //Checks for collision with enemy,object and wall/border before moving forward.
                if(Enemy_CHECK() != null)
                {
                    print(Enemy_CHECK().name +" the "+Enemy_CHECK().race+" is in front of you\n");
                    break;
                }
                if(Look() != null)
                {
                    print("There is a "+Look().name+" in front of you\n");
                    break;
                }
                if(Wall_CHECK() == true)
                {
                    print(wall[random(8)]);
                    break;
                }
                player.Say("Moved forward\n");
                player.move(player.location.heading);
                print(path[random(16)]);
                break;
                
            case Game.MOVE_BACKWARDS:
                if(Behind_Enemy_CHECK() != null)
                {
                    print(Behind_Enemy_CHECK().name +" the "+Behind_Enemy_CHECK().race+" is in behind of you\n"
                            + "turn and fight\n");
                    break;
                }
                if(Behind_Wall_CHECK() == true)
                {
                    print(wall[random(8)]);
                    break;
                }
                if(Look_Behind() != null)
                {
                    print("There is a "+Look_Behind().name+" behind you");
                    break;
                }
                player.Say("Moved backward\n");
                player.move_back(); 
                print(path[random(16)]);
                break;
                
            case Game.TURN_LEFT:
                player.Turn_Left();
                player.Say("You turned Left\n");
                break;
                
            case Game.TURN_RIGHT:
                player.Turn_Right();
                player.Say("You turned Right\n");
                break;
                
            case Game.SHOW_INVENTORY:
                Game.print(player.Show_Inventory());
                break;
                
            case Game.SET_MAIN_HAND:
                int in = Game.askNum("What would you like to be in your hand?\n(Using the number)\n"+ player.Show_Inventory(),scan);
                player.equip(player.inventory.get(in));
                Game.print(player.get_main_hand().name+" is now in your hand.\n");
                break;
                
            case Game.LOOK: //describes the block in front of them.
                if(Look() != null)
                {
                    print("There is a "+Look().name+" in front of you\n");
                    break;
                }
                if(Enemy_CHECK() != null)
                {
                    print(Enemy_CHECK().name +" the "+Enemy_CHECK().race+" is in front of you "+Enemy_CHECK().state+"\n");
                    break;
                }
                if(Wall_CHECK() == true)
                {
                    print(wall[random(8)]);
                    break;
                }
                print("There is nothing but darkness\n");
                break;
                
                
            case Game.STATS:
                print(player.checkStats());
                break;
                
            case "TP PLAYER": //One of the admin commands
                player.location.xPosition = askNum("x = ",scan);
                player.location.yPosition = askNum("y = ",scan);
                break;
                
            case "LOCATE ENEMIES": //One of the admin commands
                for(Character e : enemies) //For loop giving random locations within a set area.
                {
                    print(e.name+" = "+e.location.toString()+"\n");
                }
                break;
                
            case "LOCATE OBJECTS": //One of the admin commands
                for(Object o : objects) //For loop giving random locations within a set area.
                {
                    print(o.name+" is at "+o.location.toString()+"\n");
                }
                break;
        }
    }
    
    public static void Attack(ArrayList<Character> enemies, Item Wep) //Checks for enemy and attacks if there is one. Should also have a object check and a wall check.
    {
        if(Enemy_CHECK() != null)
        {
            player.attack(Enemy_CHECK(),Wep);
            Combat encounter = new Combat(player,Enemy_CHECK());
            Thread th = new Thread(encounter);
            th.start();
        }
        else
        {
            Game.print("It does nothing.\n");
        }
    }

    
    /*
    Different types of check,
    Wall, object, enemy
    */
    public static boolean Wall_CHECK()
    {
        int x_char = 0;
        int y_char = 0;
        switch(player.location.heading) //sets the location of the square that is being checked
        {
            case NORTH:
                y_char = player.location.yPosition + 1;
                if(y_char == 16)
                {
                    return true;
                }
                break;
            case SOUTH:
                y_char = player.location.yPosition - 1;
                if(y_char == -16)
                {
                    return true;
                }
                break;
            case EAST:
                x_char = player.location.xPosition + 1;
                if(y_char == 16)
                {
                    return true;
                }
                break;
            case WEST:
                x_char =  player.location.xPosition - 1;
                if(y_char == -16)
                {
                    return true;
                }
                break;
        }
        return false;
    }
    
    public static boolean Behind_Wall_CHECK()
    {
        int x_char = 0;
        int y_char = 0;
        switch(player.location.heading) //sets the location of the square that is being checked
        {
            case NORTH:
                y_char = player.location.yPosition - 1;
                if(y_char == 16)
                {
                    return true;
                }
                break;
            case SOUTH:
                y_char = player.location.yPosition + 1;
                if(y_char == -16)
                {
                    return true;
                }
                break;
            case EAST:
                x_char = player.location.xPosition - 1;
                if(y_char == 16)
                {
                    return true;
                }
                break;
            case WEST:
                x_char =  player.location.xPosition + 1;
                if(y_char == -16)
                {
                    return true;
                }
                break;
        }
        return false;
    }

    
    public static Object Look()
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
        for(Object o : objects) //checking through object positions
        {
            int x = o.location.xPosition;
            int y = o.location.yPosition;
            if((y_char == y) && (x_char == x))
            {
                return o;
            }
        }
        return null;
    }
    
        public static Object Look_Behind()
    {
        int x_char = 0;
        int y_char = 0;
        switch(player.location.heading) //sets the location of the square that is being checked
        {
            case NORTH:
                y_char = player.location.yPosition - 1;
                x_char = player.location.xPosition;
                break;
            case SOUTH:
                y_char = player.location.yPosition + 1;
                x_char = player.location.xPosition;
                break;
            case EAST:
                x_char = player.location.xPosition - 1;
                y_char = player.location.yPosition;
                break;
            case WEST:
                x_char =  player.location.xPosition + 1;
                y_char = player.location.yPosition;
                break;
        }
        for(Object o : objects) //checking through object positions
        {
            int x = o.location.xPosition;
            int y = o.location.yPosition;
            if((y_char == y) && (x_char == x))
            {
                return o;
            }
        }
        return null;
    }

    
    public static Character Enemy_CHECK() //looks for an enemy infront of them.
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
    
    
    public static Character Behind_Enemy_CHECK() //looks for an enemy infront of them.
    {
        int x_char = 0;
        int y_char = 0;
        switch(player.location.heading) //sets the location of the square that is being checked
        {
            case NORTH:
                y_char = player.location.yPosition - 1;
                x_char = player.location.xPosition;
                break;
            case SOUTH:
                y_char = player.location.yPosition + 1;
                x_char = player.location.xPosition;
                break;
            case EAST:
                x_char = player.location.xPosition - 1;
                y_char = player.location.yPosition;
                break;
            case WEST:
                x_char =  player.location.xPosition + 1;
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
    
    
    
    /*
    Setting up all the different levels
    */
    
    public static void Easy_Set_Up() //Sets up the easy difficulty
    {
        //Removing previous stuff
        enemies.clear();
        objects.clear();
        //Add the enemies to the game
        enemies.add(new Character("Gob","Skeleton",new Location(0,0), 10,false));
        enemies.add(new Character("Job","Skeleton", new Location(0,0), 10,false));
        enemies.add(new Character("Bob","Skeleton", new Location(0,0), 10,false));
        for(Character e : Game.enemies) //For loop giving random locations within a set area.
        {
            e.GiveItem_silent(Skeleweapon_1);
            e.equip(Skeleweapon_1);
            e.location.random_location();
        }
        //OBJECTS
        for(int i = 0; i <= 5; i++)
        {
            objects.add(new Object("column",new Location(0,0),false)); //creating 5 different objects for the level.
        }
        for(Object o : Game.objects) //For loop giving random locations within a set area.
        {
            o.location.random_location();
        }
        
        Game.player.location.setHeading(Location.Direction.NORTH); //Set default player location
        Game.player.location.xPosition = 0; //Setting x position to 0
        Game.player.location.yPosition = 0; //Setting y position to 0
        

        //Create a location with a description
        Location dungeon = new Location(0, 0);
        dungeon.giveDescription(Game.get_dungeonDescription());
        player.location = dungeon;

        
        doorLocation.giveDescription(Game.get_doorDescription());


        
        Object Door = new Object("Door", doorLocation,true);
        player.GiveItem(Easy_Weapon);
        player.equip(Easy_Weapon);
        if(player.main_hand == null)
        {
            player.equip(Easy_Weapon);
        }
        
        player.location.heading = Location.Direction.NORTH; //Set heading

        dungeon.describeLocation();

        System.out.println("** For all the options type 'options' **\n");
                
        easySave.SaveGame(); //Save the easy save state
    }
    
    public static void Medium_Set_Up() //Sets up the medium difficulty
    {
        //Removing previous stuff
        enemies.clear();
        objects.clear();
        //Add the enemies to the game
        enemies.add(new Character("Gob","Skeleton",new Location(0,0), 20,false));
        enemies.add(new Character("Job","Skeleton", new Location(0,0), 20,false));
        enemies.add(new Character("Bob","Skeleton", new Location(0,0), 20,false));
        enemies.add(new Character("Lob","Skeleton",new Location(0,0), 20,false));
        enemies.add(new Character("Mob","Skeleton", new Location(0,0), 20,false));
        for(Character e : Game.enemies) //For loop giving random locations within a set area.
        {
            e.location.random_location();
        }
        //OBJECTS
        for(int i = 0; i < 5; i++)
        {
            objects.add(new Object("column",new Location(0,0),false)); //creating 5 different objects for the level.
        }
        objects.add(new Object("Chest",new Location(0,0),true));
        for(Object o : Game.objects) //For loop giving random locations within a set area.
        {
            o.location.random_location();
        }

        
        Game.player.location.setHeading(Location.Direction.NORTH); //Set default player location
        Game.player.location.xPosition = 0; //Setting x position to 0
        Game.player.location.yPosition = 0; //Setting y position to 0
        player.GiveItem(Medium_Weapon); //medium starting weapon
        if(player.main_hand == null)
        {
            player.equip(Medium_Weapon);
        }
        
        //Create a location with a description
        Location dungeon = new Location(0, 0);
        dungeon.giveDescription(Game.get_dungeonDescription());
        player.location = dungeon;
        player.GiveItem(Medium_Weapon);
        player.equip(Medium_Weapon);
        player.location.heading = Location.Direction.NORTH; //Set heading

        dungeon.describeLocation();

        System.out.println("** For all the options type 'options' **\n");
            
        
        mediumSave.SaveGame(); //Set the medium save setup
    }
   
    /*
    Completions of the different levels
    */
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
    public static void Check_Medium_Completion()
    {
        Scanner scan = new Scanner(System.in); //Scanner object to take in inputs
        if(player.location.xPosition == objects.get(5).location.xPosition && player.location.yPosition == objects.get(5).location.yPosition)
        {
            String confirmation =  Game.ask("Would you like to move to the next level? \n ******YES or NO******\n", scan).toUpperCase();
            if(confirmation.equals("YES"))
            {
                easy_game_on = false;
                difficulty = HARD;  
            }
        } 
    }
    
    //Converts enemy array to a JSONArray
    private static JSONArray enemiesToJSON(){
        JSONArray ja = new JSONArray();
        
        for(Character enemy : enemies){
            ja.put(enemy.characterToJSON());
        }
        return ja;
    }
    
    //Convets game objects to JSON array
    private static JSONArray objectsToJSON(){
        JSONArray ja = new JSONArray();
        
        for(Object object : objects){
            ja.put(object.objectToJSON());
        }
        return ja;
    }
    
    //Saves the game as a JSON file
    protected static JSONObject gameToJSON(){
        JSONObject jo = new JSONObject();
        jo.put("playing", playing);
        jo.put("moves", moves);
        jo.put("difficulty", difficulty);
        jo.put("player", player.playerToJSON()); //Add player to JSON
        //Add all the enemies to the JSON
        
        jo.put("enemies", enemiesToJSON());
        jo.put("objects", objectsToJSON());
        return jo;
    }
    
}
