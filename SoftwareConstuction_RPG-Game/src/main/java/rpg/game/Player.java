package rpg.game;
import org.json.JSONObject;
import static rpg.game.Game.moves;
import static rpg.game.Location.Direction.EAST;
import static rpg.game.Location.Direction.NORTH;
import static rpg.game.Location.Direction.SOUTH;
import static rpg.game.Location.Direction.WEST;
/**
 *
 * @author robin, matthew
 */
public class Player extends Character{
   
    int score;

    public Player(String name, Location location, int health,int score){
        super(name,"Player",location, health);
        this.score = score;
    }

    @Override
    public String checkStats(){
        String stats = "";
        
        stats += "Score = " + this.getScore()+ "\n";
        stats += "Health = " + this.health;
        stats += "\nState = " + this.state.toString() + "\n";
        
        return stats;
    }
    
    
    public void attack(Character character, Item weapon){
        character.doDamageWith(weapon);
    }
    

    
    public int getScore()
    {
        return this.score;
    }  
    public void makeMove() {moves++;} //Make a move

    public void move(Location.Direction dir){
        switch(dir){
            case NORTH:
                //unless y = 15
                this.location.yPosition++;
            break;
            
            case SOUTH:
                //unless y = -15
                this.location.yPosition--;
            break;
            
            case EAST:
                //unless x = 15
                this.location.xPosition++;
            break;
            
            case WEST:
                //unless x = -15 - print out there is a wall in the way
                this.location.xPosition--;
            break;
        }
        this.makeMove();
    }

    public void move_back(){
        switch(this.location.heading){
            case NORTH:
                //unless y = -15
                this.location.yPosition--;
            break;
            
            case SOUTH:
                //unless y = 15
                this.location.yPosition++;
            break;
            
            case EAST:
                //unless y = -15
                this.location.xPosition--;
            break;
            
            case WEST:
                //unless y = 15
                this.location.xPosition++;
            break;
        }
        this.makeMove();
    }
    
    public void Turn_Left()
    {
        switch(this.location.heading){
            case NORTH:
                //unless y = -15
                this.location.setHeading(Location.Direction.WEST);
            break;
            case SOUTH:
                //unless y = 15
                this.location.setHeading(Location.Direction.EAST);
            break;
            case EAST:
                //unless y = -15
                this.location.setHeading(Location.Direction.NORTH);
            break;
            case WEST:
                //unless y = 15
                this.location.setHeading(Location.Direction.SOUTH);
            break;
        }
        this.makeMove();
    }

    public void Turn_Right()
    {
        switch(this.location.heading){
            case NORTH:
                //unless y = -15
                this.location.setHeading(Location.Direction.EAST);
            break;
            case SOUTH:
                //unless y = 15
                this.location.setHeading(Location.Direction.WEST);
            break;
            case EAST:
                //unless y = -15
                this.location.setHeading(Location.Direction.SOUTH);
            break;
            case WEST:
                //unless y = 15
                this.location.setHeading(Location.Direction.NORTH);
            break;
        }
        this.makeMove();
    }
    
    /*
        **********************************************
            **** Saving and Loading functions ****
        **********************************************
    */
    
    public JSONObject playerToJSON(){
        JSONObject jo = new JSONObject();
        jo.put("score", score);
        jo.put("character", this.characterToJSON());
        return jo;
    }
}
