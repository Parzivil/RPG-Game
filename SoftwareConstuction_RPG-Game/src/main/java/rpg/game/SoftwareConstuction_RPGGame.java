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
        
        Game.player = new Player(
                Game.ask("Please enter a name for your character. ", Game.scan), 
                new Location(0, 0), 55,0);
        Scanner scan = new Scanner(System.in); //Scanner object to take in inputs
        while(Game.currentDifficultyState <= 0 || Game.currentDifficultyState > 4) //finding out what difficulty they want.
        {
            Game.currentDifficultyState = Game.askNum("Hello "+Game.player.name+"\nPlease enter a difficulty \n1) Easy\n2) Medium\n3) Hard\n4) Load from previous save\n",scan);
        }
        while(Game.playing)
        {   
            switch(Game.currentDifficultyState) //this is the setup for the while game loop
            {
                case Game.EASY_KEYCODE: //easy difficulty is a find the door with 3 enemies
                {
                    Game.Easy_Set_Up();  //Sets up easy difficulty
                    break;
                }
                case Game.MEDIUM_KEYCODE: //medium is find the treasure with 5 enemies
                {
                    Game.Medium_Set_Up();
                    break;
                }
                case Game.HARD_KEYCODE: //defeat the boss and his 6 henchmen
                {  
                    Game.Hard_Set_Up();
                    break;
                }
                case Game.LOAD_KEYCODE:
                {
                    Game.userSave.LoadGame();
                    break;
                }
            }
            while(Game.level_completed != true){
                Game.Game_play(scan);
            }
        }  
        Game.scan.close();
        Game.print("Why");
    } 
}





