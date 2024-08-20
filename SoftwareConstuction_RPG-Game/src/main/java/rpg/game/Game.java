/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;
import java.util.*;
/**
 *
 * @author robin
 */
public class Game{

    public Random rand = new Random();
    
    public final static String OPTIONS = "OPTIONS";
    public final static String ATTACK = "ATTACK";
    public final static String INTERACT = "INTERACT";
    public final static String MOVE_FORWARD = "MOVE FORWARD";
    public final static String MOVE_BACKWARDS = "MOVE BACKWARDS";
    public final static String TURN_LEFT = "TURN LEFT";
    public final static String TURN_RIGHT = "TURN RIGHT";
    public final static String SHOW_INVENTORY = "SHOW INVENTORY";
    public final static String SET_MAIN_HAND = "SET MAIN HAND";
        
    public final static int EASY = 1;
    public final static int MEDIUM = 2;
    public final static int HARD = 3;
    
    /**
     *
     */
    protected int moves = 0; //Then number of moves the player has made
    
    //Functions for generating random numbers
    public int random(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    public static int random(int max) {
        return (int) (Math.random() * max);
    }
    
    public String randomString(String strings[]){
        rand.setSeed(System.currentTimeMillis()); //Reset the seed
        return strings[rand.nextInt(strings.length)];
    }
    
     //Print functions because I am sick of typing the system out thing
    public static void print(String str) {System.out.print(str);}
    public static void println(String str) {System.out.println(str);}
    
    public static String ask(String question, Scanner scan){
        System.out.print(question);
        return scan.nextLine();
    } 
    
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
