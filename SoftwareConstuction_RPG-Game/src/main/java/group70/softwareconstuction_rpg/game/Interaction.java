/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group70.softwareconstuction_rpg.game;

/**
 *
 * @author Robin
 */
public class Interaction extends Game{
    Location location; 
    String conversation[]; 
    Character firstCharacter;
    Character secondCharacter;
    
    int step = 0;
    
    public Interaction(String conversation[], Location location, Character firstCharacter, Character secondCharacter){
        this.conversation = conversation;
        this.location = location;
        this.firstCharacter = firstCharacter;
        this.secondCharacter = secondCharacter;
    }
    
    public void next(){
        if(step < conversation.length){
            if(step % 2 == 1){
                firstCharacter.Say(conversation[step]); //First character speaks
            }
            else{
                secondCharacter.Say(conversation[step]); //Second character speaks
            }
            step++;
        }
        
        step = 0; //Reset to the begining if the conversation is over
    }
}
