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
    private Writer write;
    
    public Saver(String path){        
        try{
            write = new FileWriter(path);
        }
        catch(Exception e){
            System.out.println("FAILED TO OPEN FILE: " + path);
        }
    }
    
    //Saves the current game state to a JSON file
    public void SaveGame(JSONObject jo){                
        try{
            write.write(jo.toString(3));
            write.close();
            System.out.println(jo.toString(3));
            
        }   
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void LoadGame(){
        
    }
}
