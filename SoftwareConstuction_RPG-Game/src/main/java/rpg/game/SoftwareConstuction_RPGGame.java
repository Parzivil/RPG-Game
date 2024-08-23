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

public class SoftwareConstuction_RPGGame {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); //Scanner object to take in inputs
        
        
        
        
        Game.player = new Player(
                Game.ask("Please enter a name for your character. ", scan), 
                new Location(0, 0), 55,0);
        Game.player.location.setHeading(Location.Direction.NORTH); //Set default player location
        
        
        
        
        while(Game.difficulty <= 0 || Game.difficulty >= 4) //finding out what difficulty they want.
        {
            Game.difficulty = Game.askNum("Hello "+Game.player.name+"\nPlease enter a difficulty \n1) Easy\n2) Medium\n3) Hard\n",scan);
        }
        
        while(Game.playing)
        {
            switch(Game.difficulty) //this is the setup for the while game loop
            {
                case Game.EASY: //easy difficulty is a find the door with 3 enemies
                {
                    Game.Easy_Set_Up();  //Sets up easy difficulty
                    //BUG!
                    //This loop runs twice on every action causing the direction to only be north and south
                    //If it ran twice on every action then you would move forward twice a turn therefore the turn thingy is the problem.
                    //Sorted. I switched it from a if statement to a case.
                    while(Game.easy_game_on)
                    {
                        Game.Game_play(); //So we dont have to copy
                        Game.Check_Easy_Completion();//Check if the door has been found
                    }   
                    break;
                }
                case Game.MEDIUM: //medium is find the treasure with 5 enemies
                {
                    while(Game.medium_game_on)
                    {
                        Game.print("Good job");
                        Game.player.location.xPosition = 0;
                        Game.player.location.yPosition = 0;
                    }   
                    break;
                }
                case Game.HARD: //defeat the boss and his 6 henchmen
                {
                    break;
                }
            }
        }
        
    } 
    
}
