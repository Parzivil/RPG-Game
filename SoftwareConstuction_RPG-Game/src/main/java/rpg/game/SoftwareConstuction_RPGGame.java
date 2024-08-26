/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package rpg.game;
import java.util.Scanner;
/**
 *
 * @author robin, matthew
 */

/*
********** Dear Matthew:
********** - COMMENT THE CODE YOU WRITE!!
********** - Also if it can go in a class it should go there (keep this code minimal and abstract)

***OKAY...xoxo ;)
*/

public class SoftwareConstuction_RPGGame 
{    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); //Scanner object to take in inputs
        Game.player = new Player(
                Game.ask("Please enter a name for your character. ", scan), 
                new Location(0, 0), 55,0);
        Game.Difficulty_selector();
        while(Game.playing)
        {   
            switch(Game.difficulty) //this is the setup for the while game loop
            {
                case Game.EASY: //easy difficulty is a find the door with 3 enemies
                {
                    Game.Easy_Set_Up();  //Sets up easy difficulty
                    break;
                }
                case Game.MEDIUM: //medium is find the treasure with 5 enemies
                {
                    Game.Medium_Set_Up();
                    break;
                }
                case Game.HARD: //defeat the boss and his 6 henchmen
                {  
                    break;
                }
                case Game.LOAD:
                {
                    Game.userSave.LoadGame();
                    break;
                }
            }
            while(Game.level_completed != true)
            {
                Game.Game_play();
                if(Game.difficulty == Game.EASY){Game.Check_Easy_Completion();Game.level_completed = true;}
                if(Game.difficulty == Game.MEDIUM){Game.Check_Medium_Completion();Game.level_completed = true;}
            }  
            break;
        }  
    } 
}
