
package rpg.game;

/**
 *
 * @author robin, matthew
 */

//Used for when encountering an enemy, can have multiple enounters going at once
public class Combat extends Game implements Runnable{
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
        if(enemy.state == Character.State.DEAD)
        {
            Game.enemies.remove(enemy);
            Game.player.score++;
        }
    }
}
