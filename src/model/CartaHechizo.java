/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
"Alcance. RECIBIR ATAQUE: defensa = defensa + alcance. ATAQUE: ataque = ataque + alcance.";
 */
package model;

/**
 *
 * @author alu2017454
 */
public class CartaHechizo extends Carta {
    
    private static int numberCard = 1;
    private String cardName;
    private int specialLevelPoints;
    private final String specialPower = "Range. RECEIVING ATTACK: denfese = defense + range. ATTACK: attack = attack + range";
   
    
    public CartaHechizo(int attack, int defense, int costElixir, int lifeLevel, int specialLevelPoints){
        super(attack, defense, costElixir, lifeLevel);
        this.specialLevelPoints = specialLevelPoints;
        this.cardName = "Hechizo" + numberCard;
        numberCard++;
    }


    @Override
    public String getCardName() {
        return cardName;
    }


    @Override
    public int getSpecialLevelPoints() {
        return specialLevelPoints;
    }

  
    @Override
    public String getCARDTYPE() {
        return "Hechizo";
    }

    @Override
    public String getSpecialPower() {
        return specialPower;
    }
    
     @Override
    public int totalAttackPoints(){
        return super.getAttack() + specialLevelPoints;
    }
    
    
    @Override
    public int totalDefensePoints(){
        return super.getDefense() + specialLevelPoints;
    }
    
   
      
}
