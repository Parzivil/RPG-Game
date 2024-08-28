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
    
    //List of commands that the user can activate
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
    public static Scanner scan = new Scanner(System.in); //Scanner object to take in inputs
    
    public Random rand = new Random();
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
    public static Location doorLocation = new Location(15, 15);
    
    
    public static int currentDifficultyState = 0;
    
    public final static int EASY_KEYCODE = 1;
    public final static int MEDIUM_KEYCODE = 2;
    public final static int HARD_KEYCODE = 3;
    public final static int LOAD_KEYCODE = 4;
    public static String input_move;
    
    public static boolean easy_game_on = false; //Current Game state
    public static boolean medium_game_on = false; //Current Game state
    public static boolean hard_game_on = false; //Current Game state
    
    public static boolean playing = true; //Current Game state
    public static boolean level_completed = false; //Current Game state
    
    public static ArrayList<Character> enemies = new ArrayList<>(); // The array list of enemies 
    public static ArrayList<Object> objects = new ArrayList<>(); //The arraylist of everything thats not a character.

    
    
    
    private final static String DOOR_DESCRIPTION = 
    """
    As you carefully trace the cold, damp wall with your hand, 
    you suddenly feel a subtle shift\u2014a stone that gives way under your touch. 
    A low rumble echoes through the corridor as a hidden door swings open, 
    revealing a narrow passageway beyond. 
    The draft grows stronger, carrying with it a musty smell, 
    and the faintest hint of something unknown lurking in the darkness ahead... 
    
    
    """;
             //   17 different path descriptions
   static String[] path = 
    {"""
     You are standing on cobblestone path 
     There is nothing in front of you
     """, """
          You can feel a smooth stone beneath you
          Cold to the touch
          """, //GPT from here
    "In the dark, the stone path feels like a labyrinth, leading deeper into the unknown.",
    "The cold, smooth stones of the path are barely discernible as night swallows their form.",
    "The dim glow of lanterns reveals just enough of the stone path to hint at its winding course.",
    "The stone path stretches out into impenetrable darkness, disappearing into the void.",
    "The path beneath feels cold and rough, but its surroundings remain obscured by utter blackness.",
    "With no visible landmarks, the stone path is a solitary route through an all-encompassing gloom.",
    "The texture of the stone path is the only hint of direction in the pitch-black cave.",
    "The silence and darkness make the stone path seem like a thin thread connecting you to the unknown.",
    "The path’s surface glistens with moisture, hinting at the hidden wonders within the cave.",
    "Damp and uneven, the path in the dungeon echoes with every cautious step.",
    "The stone path in the dungeon is cold and uneven, lined with rusting metal and decaying remnants.",
    "Flickering torches cast eerie shadows along the damp, grimy stone path of the dungeon.",
    "The stone path is flanked by darkness that seems to close in as you walk.",
    "The oppressive silence of the dungeon makes each step on the stone path echo ominously.",
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

    
    protected static int moves = 0; //Then number of moves the player has made
    
    /**
     * Main game-play loop function
     * @Return
     */
    public static void Game_play(Scanner scan) {

        input_move = Game.ask("\nWhat would you like to do? \n", Game.scan).toUpperCase().trim();
        Game.print("--------------------------------------------------\n");
        switch(input_move)
        {
            case Game.SAVE:
                userSave.SaveGame(); //Save the game
                break;

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
                        + "\nSave"
                        + "\nStats");
                break;

            case Game.ATTACK: //Attacks the square infront of them.
                Attack(Game.enemies, player.getMainHandItem());
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
                Game.print("You are at: "+player.location.toString()+"    Facing "+player.location.heading.toString()+"\n"); //reoccuring message informing player of location.

                print("\n\""+path[random(16)] + "\"");
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
                Game.print("You are at: "+player.location.toString()+"    Facing "+player.location.heading.toString()+"\n"); //reoccuring message informing player of location.

                print("\n\""+path[random(16)] + "\"");
                break;

            case Game.TURN_LEFT:
                player.Turn_Left();
                player.Say("You turned Left\n");
                Game.print("You are facing "+player.location.heading.toString()+"\n"); //reoccuring message informing player of location.
                break;

            case Game.TURN_RIGHT:
                player.Turn_Right();
                player.Say("You turned Right\n");
                Game.print("You are facing "+player.location.heading.toString()+"\n"); //reoccuring message informing player of location.
                break;

            case Game.SHOW_INVENTORY:
                Game.print(player.Show_Inventory());
                break;

            case Game.SET_MAIN_HAND:
                int in = Game.askNum("What would you like to be in your hand?\n(Using the number)\n"+ player.Show_Inventory(),scan);
                player.equip(player.inventory.get(in));
                Game.print(player.getMainHandItem().name+" is now in your hand.\n");
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


            //********** ADMIN COMMANDS **************//
            case "TP PLAYER": //One of the admin commands
                player.location.xPosition = askNum("x = ",scan);
                player.location.yPosition = askNum("y = ",scan);
                break;

            case "LOCATE ENEMIES": //One of the admin commands
                println("|--------------------|");
                for(Character e : enemies) //For loop giving random locations within a set area.
                {
                    println("|"+e.name+" = "+e.location.toString()+"|");
                }
                println("|--------------------|");
                break;

            case "LOCATE OBJECTS": //One of the admin commands
                for(Object o : objects) //For loop giving random locations within a set area.
                {
                    print(o.name+" is at "+o.location.toString()+"\n");
                }
                break;
        }
        
        if(Game.currentDifficultyState == Game.EASY_KEYCODE)Game.Check_Easy_Completion();
        if(Game.currentDifficultyState == Game.MEDIUM_KEYCODE)Game.Check_Medium_Completion();
        if(Game.currentDifficultyState == Game.HARD_KEYCODE)Game.Check_Hard_Completion();
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
        //Objective
        print("\n***The objective is to find the door***\n\n");
        //Removing previous stuff
        enemies.clear();
        objects.clear();
        
        //Add the enemies to the game
        enemies.add(new Character("Gob","Skeleton",new Location(), 10));
        enemies.add(new Character("Job","Skeleton", new Location(), 10));
        enemies.add(new Character("Bob","Skeleton", new Location(), 10));
        for(Character enemy : Game.enemies) //For loop giving random locations within a set area.
        {
            enemy.GiveItem_silent(Skeleweapon_1);
            enemy.equip(Skeleweapon_1);
        }
        
        
        //OBJECTS
        for(int i = 0; i <= 5; i++) {
            objects.add(new Object("column",new Location(),false)); //creating 5 different objects for the level.
        }
        
        Game.player.location = new Location(0, 0, Location.Direction.NORTH);        

        //Create a location with a description
        Location dungeon = new Location(0, 0);

        dungeon.Add_Text_Description(Location.DUNGEON_DESCRIPTION_1);
        player.location = dungeon;

        
        doorLocation.giveDescription(DOOR_DESCRIPTION);
        
        Object Door = new Object("Door", doorLocation,true);
        player.GiveItem(Easy_Weapon);
        player.equip(Easy_Weapon);
        if(player.main_hand == null)
        {
            player.equip(Easy_Weapon);
        }
        
        player.location.heading = Location.Direction.NORTH; //Set heading

        dungeon.Read_Text_Description();

        System.out.println("** For all the options type 'options' **\n");
                
        easySave.SaveGame(); //Save the easy save state
        level_completed = false;
    }
    
    public static void Medium_Set_Up() //Sets up the medium difficulty
    {
        //Objective
        print("\n***The objective is to find the chest***\n\n");
        //Removing previous stuff
        enemies.clear();
        objects.clear();
        //Add the enemies to the game
        enemies.add(new Character("Gob","Skeleton",new Location(), 20));
        enemies.add(new Character("Job","Skeleton", new Location(), 20));
        enemies.add(new Character("Bob","Skeleton", new Location(), 20));
        enemies.add(new Character("Lob","Skeleton",new Location(), 20));
        enemies.add(new Character("Mob","Skeleton", new Location(), 20));
        
        //OBJECTS
        for(int i = 0; i < 5; i++)
        {
            objects.add(new Object("column",new Location(),false)); //creating 5 different objects for the level.
        }
        objects.add(new Object("Chest",new Location(),true));

        //Set the player location
        Game.player.location.xPosition=0;        
        Game.player.location.yPosition=0; 
        Game.player.location.heading=Location.Direction.NORTH;
        
        player.GiveItem_silent(Medium_Weapon); //medium starting weapon
        if(player.main_hand == null)
        {
            player.equip(Medium_Weapon);
        }
        
        //Create a location with a description
        Location dungeon = new Location(0, 0);
        dungeon.Add_Text_Description(Location.DUNGEON_DESCRIPTION_2);
        player.location = dungeon;
        player.GiveItem(Medium_Weapon);
        player.equip(Medium_Weapon);
        player.location.heading = Location.Direction.NORTH; //Set heading

        dungeon.Read_Text_Description();

        System.out.println("** For all the options type 'options' **\n");
            
        mediumSave.SaveGame(); //Set the medium save setup
        level_completed = false;
    }
   
    public static void Hard_Set_Up() //Sets up the hard difficulty
    {
        //Objective
        print("\n***The objective is clear kill all the mobs and the boss***\n\n");
        //Removing previous stuff
        enemies.clear();
        objects.clear();
        //Add the enemies to the game
        enemies.add(new Character("Gob","Skeleton",new Location(), 30));
        enemies.add(new Character("Job","Skeleton", new Location(), 30));
        enemies.add(new Character("Bob","Skeleton", new Location(), 30));
        enemies.add(new Character("Lob","Skeleton",new Location(), 30));
        enemies.add(new Character("Mob","Skeleton", new Location(), 30));
        enemies.add(new Character("Steve Job","Skeleton", new Location(), 30));
        
        //OBJECTS
        for(int i = 0; i < 7; i++)
        {
            objects.add(new Object("column",new Location(),false)); //creating 5 different objects for the level.
        }
        
        Game.player.location = new Location(0, 0, Location.Direction.NORTH);        
        player.GiveItem(Hard_Weapon); //Hard starting weapon
        if(player.main_hand == null)
        {
            player.equip(Medium_Weapon);
        }
        
        level_completed = false;
        
        //Create a location with a description
        Location dungeon = new Location(0, 0);
        dungeon.Add_Text_Description(Location.DUNGEON_DESCRIPTION_3);
        player.location = dungeon;
        player.location.heading = Location.Direction.NORTH; //Set heading
        dungeon.Read_Text_Description();

        System.out.println("** For all the options type 'options' **\n");
            
        hardSave.SaveGame(); //Set the hard save setup
        level_completed = false;
    }
    
    /*
    Completions of the different levels
    */
    public final static void Check_Easy_Completion()
    {
        if(player.location.xPosition == doorLocation.xPosition && player.location.yPosition == doorLocation.yPosition)
        {
            doorLocation.describeLocation();//Describe the door
            Check_Next_Level(scan);
        } 
    }
    public final static void Check_Medium_Completion()
    {
        if(player.location.xPosition == objects.get(5).location.xPosition && player.location.yPosition == objects.get(5).location.yPosition)
        {
            Check_Next_Level(scan);
        }
    }
    
    public final static boolean Check_Hard_Completion()
    { 
        return enemies.isEmpty();
    }
    
    public final static void Check_Next_Level(Scanner scan)
    {
        String confirmation =  Game.ask("Would you like to move to the next level? \n ******YES or NO******\n", scan).toUpperCase();
            if((currentDifficultyState != 3) && confirmation.equals("YES"))
            {
                currentDifficultyState++; 
                level_completed = true;
            }
            if(confirmation.equals("NO"))
            {
                userSave.SaveGame(); //Save the game
                playing = false;
            }
    }
    
    /**
     * Generates a random number between given values
     * @param min
     * @param max
     * @return 
     */
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
    
    public static void print(String str) {System.out.print(str);}
    public static void println(String str) {System.out.println(str);}
    
    public static String ask(String question, Scanner scan){
        System.out.print(question);
        String bob =  scan.nextLine();
        return bob;
    } 
    
    public static int askNum(String question, Scanner scan)
    { 
        do{
            System.out.print(question);
            try{
                return scan.nextInt(); //Break out of the loop
            }

            catch(Exception e){
                System.out.println("\n **Input error, try again!**\n");   
                scan.next();
            }
        } while(true);
    }
    
    /*
        **********************************************
            **** Saving and Loading functions ****
        **********************************************
    */
    /**
     * Converts enemy array to a JSONArray
     * @return 
     */
    private static JSONArray enemiesToJSON(){
        JSONArray ja = new JSONArray();
        
        for(Character enemy : enemies){
            ja.put(enemy.characterToJSON());
        }
        return ja;
    }
    
    /**
     * Converts game objects to JSON array
     * @return 
     */
    private static JSONArray objectsToJSON(){
        JSONArray ja = new JSONArray();
        
        for(Object object : objects){
            ja.put(object.objectToJSON());
        }
        return ja;
    }
    
    /**
     * Saves the game as a JSON file
     * @return 
     */
    protected static JSONObject gameToJSON(){
        JSONObject jo = new JSONObject();
        jo.put("playing", playing);
        jo.put("moves", moves);
        jo.put("difficulty", currentDifficultyState);
        jo.put("player", player.playerToJSON()); //Add player to JSON
        //Add all the enemies to the JSON
        
        jo.put("enemies", enemiesToJSON());
        jo.put("objects", objectsToJSON());
        return jo;
    }
    
    
}
