/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.game;

/**
 *
 * @author robin
 */

//Used for when encountering an enemy, can have multiple enounters going at once
public abstract class Combat extends Game implements Runnable{
    Player player;
    Character enemy;
    
    public Combat(Player player, Character enemy){
       this.player = player;
       this.enemy = enemy;
    }
    
    @Override
    public void run(){
        while(enemy.state.equals(Character.State.ALIVE)){
            player.doDamageWith(enemy.main_hand);
            
            try{
                Thread.sleep(enemy.main_hand.attackRate); //Wait before next attack
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
