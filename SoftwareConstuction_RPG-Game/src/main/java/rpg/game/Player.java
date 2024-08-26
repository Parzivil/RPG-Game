package rpg.game;
import org.json.JSONObject;
/**
 *
 * @author robin, matthew
 */
public class Player extends Character{
   
    int score;

    public Player(String name, Location location, int health,int score){
        super(name,"player",location, health, false);
        this.score = score;
    }

    
    
    public void attack(Character character, Item weapon){
        character.doDamageWith(weapon);
    }
    
    public JSONObject playerToJSON(){
        JSONObject jo = new JSONObject();
        jo.put("score", score);
        jo.put("character", this.characterToJSON());
        return jo;
    }
    
    public int getscore()
    {
        return this.score;
    }
    
    
}
