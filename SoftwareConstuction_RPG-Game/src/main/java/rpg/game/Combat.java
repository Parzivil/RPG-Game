/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;

/**
 *
 * @author robin, matthew
 */

//Used for when encountering an enemy, can have multiple enounters going at once
public class Combat extends Game implements Runnable{
    Player player;
    Character enemy;
    
    /**
     * Creates a new combat encounter
     * @param player
     * @param enemy 
     */
    public Combat(Player player, Character enemy){
       this.player = player;
       this.enemy = enemy;
    }
    
    /**
     * Begins a new thread where the enemy attacks the player at a fixed rate
     */
    @Override
    public void run(){
        while(enemy.state.equals(Character.State.ALIVE)){
            player.doDamageWith(enemy.main_hand); //Attack the player
            
            try{
                Thread.sleep(enemy.main_hand.attackRate); //Wait before next attack
            }
            catch(Exception e){
                System.out.println(e.getMessage()); 
            }
        }
        //Check if enemy is dead
        if(enemy.state == Character.State.DEAD) {
            Game.enemies.remove(enemy); //Remove the enemy from the game
            Game.player.score += 10; //Award then player points
        }
    }
}
