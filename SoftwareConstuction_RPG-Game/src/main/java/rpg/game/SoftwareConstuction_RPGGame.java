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
    
    static Event events = new Event();
    
    Game game = new Game();
    
    static Player player;
    
    static Character skelly = new Character("Skeleton", new Location(0, 1), 15);

    
   
    static Enemy skel = new Enemy(skelly, new String[] {"Hello there", "g"});
    
    
    static Chest chst = new Chest();
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        println("THIS IS A TEST, NOT THE GAME");
        
        String name = ask("What is your name? ", scan);
        
        player = new Player(name, new Location(0, 0), 55);
        
        println("Hello Knight " + player.name);
        
        Weapon sword = new Weapon("Sword", player.location, 1, 5, 5);
        
        player.GiveItem(sword);
        
        System.out.println(chst.location.xPosition);
        
        player.doDamageWith(sword);
        
        player.attack(skelly, sword);
        
    }
    
    //Print functions because I am sick of typing the system out thing

    /**
     *
     * @param str
     */
    public static void print(String str) {System.out.print(str);}

    /**
     *
     * @param str
     */
    public static void println(String str) {System.out.println(str);}
    
            /**
     *
     * @param question
     * @param scan
     * @return
     */
    public static String ask(String question, Scanner scan){
        System.out.print(question);
        return scan.nextLine();
    } 
    
    /**
     *
     * @param question
     * @param scan
     * @return
     */
    public static int askNum(String question, Scanner scan){ 
        do{
            System.out.print(question);
        
            try{
                return scan.nextInt(); //Break out of the loop
            }

            catch(Exception e){
                System.out.println("\n Input error, try again");         
            }
        } while(true);
    }
}
