/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
"Escudo. RECIBIR ATAQUE: defensa = defensa X escudo. Ataque especial: NO.";
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author alu2017454
 */
public class CartaEstructura extends Carta {
    
    private static int numberCard = 1;
    private String cardName;
    private int specialLevelPoints;
    private final String specialPower = "Shield. In RECEIVING ATTACK: defense = defense X shield. Special attack: NO";
   
    
    public CartaEstructura(int attack, int defense, int costElixir, int lifeLevel, int specialLevelPoints){
        super(attack, defense, costElixir, lifeLevel);
        this.specialLevelPoints = specialLevelPoints;
        this.cardName = "Estructura" + numberCard;
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
        return "Estructura";
    }

    @Override
    public String getSpecialPower() {
        return specialPower;
    }
    
    @Override
    public int totalAttackPoints(){
        return super.getAttack();
    }
    
    
    @Override
    public int totalDefensePoints(){
        return super.getDefense() * specialLevelPoints;
    }
    
}
  
