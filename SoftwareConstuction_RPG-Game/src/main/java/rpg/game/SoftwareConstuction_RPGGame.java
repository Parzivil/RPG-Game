/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package rpg.game;
import java.util.Scanner;
/**
 *
 * @author robin
 */

//// EVERYTHING IN HERE CAN BE MODIFYED, CURRENTLY FOR TESTING THINGS ////

public class SoftwareConstuction_RPGGame {
    static Scanner scan = new Scanner(System.in);
    static Player player;
    
    static Character skelly = new Character("Skeleton", new Location(0, 1), 15);

    public static void main(String[] args) {
        println("THIS IS A TEST, NOT THE GAME");
        
        print("What is your name? ");
        
        player = new Player(scan.nextLine(), new Location(0, 0), 55);
        
        println("Hello Knight " + player.name);
        
        Weapon sword = new Weapon("Sword", player.location, 1, 5, 5);
        
        player.GiveItem(sword);
        
        player.doDamageWith(sword);
        
    }
    
    //Print functions because I am sick of typing the system out thing
    public static void print(String str) {System.out.print(str);}
    public static void println(String str) {System.out.println(str);}
    

}
