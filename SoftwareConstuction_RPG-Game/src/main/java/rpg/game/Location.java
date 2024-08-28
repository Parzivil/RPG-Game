/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;
import java.util.ArrayList;
import org.json.JSONObject;
import static rpg.game.Game.print;

/**
 *
 * @author robin, matthew
 */
public class Location {

    
    enum Direction{
        NORTH,
        EAST,
        SOUTH,
        WEST
    }
    
    int xPosition;
    int yPosition;
    private final int width = 30;
    private final int pos_width = 15;
    private String descriptor;
    public static ArrayList<String> descriptions_v2 = new ArrayList<>(); //The arraylist of everything thats not a character.

    protected Direction heading;
    
    /**
     * Creates a new random location
     */
    public Location(){
        this.randomizeLocation();
    }
    
    /**
     * Creates a new location with set x and y positions
     * @param x
     * @param y 
     */
    public Location(int x, int y){
        this.xPosition = x;
        this.yPosition = y;
    }
    
    /**
     * Creates a new location with set x, y positions and heading
     * @param x
     * @param y
     * @param heading 
     */
    public Location(int x, int y, Direction heading){
        this.xPosition = x;
        this.yPosition = y;
        this.heading = heading;
    }
    
    @Override
    public String toString(){
        return "x: " + this.xPosition + ", y: " + this.yPosition + "\n";
    }

    /**
     *
     * @param direction
     */
    public void setHeading(Direction direction){
        this.heading = direction;
    }
    
    //Sets the description of the current location
    public void giveDescription(String description){
        this.descriptor = description;
    }
    
    
    //States the current location description
    public void describeLocation(){
        System.out.println(this.descriptor);
    }
    
    protected void randomizeLocation() //setting a random location to the set character within the borders of the map.
    {
        int x = Game.random(width)-pos_width;
        int y = Game.random(width)-pos_width;
        this.xPosition = x;
        this.yPosition = y;
    }
    
    void Add_Text_Description(String[] lines)
    {
        for(int i = 0;  lines.length > i; i++)
        {
            descriptions_v2.add(lines[i]);
        }
    }
    
    void Read_Text_Description()
    {
       
        for(int i = 0;i < descriptions_v2.size(); i++)
        {
            print(descriptions_v2.get(i));
            try{Thread.sleep(2000);}catch(Exception er){}

        }
    }
    
    /*
        **********************************************
            **** Saving and Loading functions ****
        **********************************************
    */
    
    public JSONObject locationToJSON(){
        JSONObject jo = new JSONObject();
        jo.put("x", xPosition);
        jo.put("y", yPosition);
        jo.put("heading", heading);
        return jo;
    }
    

    //These decription was generated using chat GPT    
    public final static String[] DUNGEON_DESCRIPTION_1 = 
    {"You find yourself in a dimly lit dungeon,\n",
    "where the air is thick with mildew and the walls are cold and damp.\n",
    "Flickering torches cast long shadows, and the floor is uneven, making each step treacherous.\n",
    "The silence is broken only by distant dripping water and the skittering of unseen creatures.\n",
    "\n",
    "As you venture deeper, the corridors twist and turn,\n",
    "leading to dead ends or eerie, abandoned chambers.\n",
    "Yet, amid the oppressive darkness, you sense a faint draft; an indication that somewhere nearby,\n",
    "a hidden door awaits discovery...\n\n"};

    public final static String[] DUNGEON_DESCRIPTION_2 = 
    {"The dungeon looms in oppressive silence, its air thick with the stench of damp stone and decay. \n",
    "Flickering torches cast long, jittery shadows that dance across the uneven, bloodstained walls.\n",
    "The floor is a treacherous maze of cracked flagstones, slick with a sheen of stagnant water. \n",
    "Low, guttural whispers echo from unseen corners, while the occasional skittering noise hints at creatures lurking in the darkness. \n",
    "The narrow corridors twist and turn in a labyrinthine fashion, each bend revealing more of the same oppressive gloom. \n",
    "The feeling of being watched is almost palpable, a constant, unsettling presence that gnaws at the edges of sanity.\n\n"};
    public final static String[] DUNGEON_DESCRIPTION_3 = 
    {
    "The dungeon stretches into an almost unbearable darkness, its passages cold and unwelcoming. \n",
    "Shadows cling to the jagged stone walls, where ancient runes seem to pulse faintly with a malevolent light. \n",
    "A low, droning hum fills the air, punctuated by the occasional drip of unseen water from a cracked ceiling. \n",
    "The floor is littered with debris and skeletal remains, evidence of long-forgotten prisoners. \n",
    "Iron-barred cells line the corridors, their doors rusted and creaking on their hinges. \n",
    "An unsettling chill seeps through the air, and the faint scent of mildew and decay lingers, suggesting that the dungeon has been undisturbed for far too long. \n",
    "The deeper you venture, the more the darkness seems to press in, as if the very walls are alive and watching. \n\n"};
    
}
