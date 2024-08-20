/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;
import java.io.FileWriter;
import java.io.Writer;
import org.json.*;
/**
 *
 * @author robin
 */

////WORK IN PROGRESS, NEEDS MORE WORK
public class Saver extends Game{
    private String path;
    private Writer write;
    
    public Saver(String path){
        this.path = path;
        
        try{
            write = new FileWriter("saveData.json");
        }
        catch(Exception e){
            
        }
    }
    
    //Saves the current game state to a txt file
    public void SaveGame(Player player){
        JSONObject jo = new JSONObject(player.toJSON());
                
        try{
            write.write(jo.toString(1));
        }   
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    //Returns if there is a save file available
    public Boolean hasSave(){
        return false; //PLACE HOLDER!!
    }
}
