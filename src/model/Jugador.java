/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author alu2017454
 */
public class Jugador implements Comparable{
    
    private static int number = 1;              //static int for unique identification of the player name purposes, increase +1 on every new object created
    private final String player = "Player";
    private final String pass = "pass";
    private String name;
    private String password;
    private int numTrof;
    private boolean logged;
    private ArrayList<Carta> cards;

    public Jugador() {
        this.name = player + number;
        this.password = pass + number;
        this.numTrof = 0;
        this.cards = new ArrayList<>();
        this.logged = false;
        number++;
    }

    public static int getNumber() {
        return number;
    }


    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }


    public int getNumTrof() {
        return numTrof;
    }

    public void setNumTrof(int numTrof) {
        this.numTrof = numTrof;
    }

    public ArrayList<Carta> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Carta> cards) {
        this.cards = (ArrayList<Carta>) cards.clone();
    }
    
    public void deleteCards(){
        this.cards.clear();
    }
    public void addCard(Carta card){
        this.cards.add(card);
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
    
    @Override
    public int compareTo(Object comparePlayer) {
        int compareTrophys =((Jugador) comparePlayer).getNumTrof();
        return compareTrophys - this.numTrof;
    }

    @Override
    public String toString() {
        return "Player = " + name + ", Trophys =" + numTrof;
    }

    


   
    
    
    
}
