/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Carta;
import model.CartaEstructura;
import model.CartaHechizo;
import model.CartaTropa;
import model.Jugador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author usuario
 */
public class Manager {
    
    private ArrayList<Jugador> players;
    private ArrayList<Carta> availableCards;
    
    /**
     * constructor from class Manager
     */
    public Manager(){
        players = new ArrayList<>();
        availableCards = new ArrayList<>();
    }
    
    /**
     * Adds three cards for each of the four players. It creates also four objects of class Jugador
     */
    public void createPlayersCards (){
        availableCards.clear();
        //creation of 3 cards each type
        //int ataque, int defensa, int costeElixir (1 a 5), int nivelVida (1 a 100), int poderEspecial){
        availableCards.add(new CartaTropa(10, 5, 2, 100, 2));
        availableCards.add(new CartaTropa(12, 8, 3, 100, 5));
        availableCards.add(new CartaTropa(15, 7, 3, 100, 3));
        availableCards.add(new CartaEstructura(9, 2, 2,100,2));
        availableCards.add(new CartaEstructura(8, 3, 1,100,5));
        availableCards.add(new CartaEstructura(7, 1, 5,100,3));
        availableCards.add(new CartaHechizo(9, 8, 4, 100, 2));
        availableCards.add(new CartaHechizo(8, 8, 3, 100, 5));
        availableCards.add(new CartaHechizo(7, 8, 2, 100, 3));
        
        //creacion of 4 players. The constructor initializes each player with default name and password(i.e: player1, 1)
        for(int i = 0; i < 4; i++){
            players.add(new Jugador());
        }
          
    }
    
    /**
     * For testing purposes on option Rankings, adds different number of trophys for each player
     */
    public void loadTrophys(){  
        players.get(0).setNumTrof(4);
        players.get(1).setNumTrof(6);
        players.get(2).setNumTrof(5);
        players.get(3).setNumTrof(7);
    }
    
    /**
     * Receives a minimum and a maximum integers and generates a random number between them, inclusive
     * @param min - int
     * @param max - int
     * @return int
     */
    public int randomGenerate(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
    
    /**
     * orders players by trofs, from max  to min
     */
    public void orderPlayersByTrofs(){
        Collections.sort(players);
    }
      
    /**
     * For testing purposes on battle option, loads 6 random cards on every player
     * @return boolean
     */
    public boolean loadCardsPlayers(){
        deleteCardsPlayers();
        ArrayList<Carta> randomCards = new ArrayList<>();
        Carta card = null;
        int i = 0;
        for(Jugador player: players){   
            while(i < 6){
              boolean exist = false;
              do{
                exist = false;
                int num = randomGenerate(0,8);
                card = availableCards.get(num);
                if(randomCards.contains(card)) exist = true;
              }while(exist);
              randomCards.add(card);
              i++;
             }
            player.setCards(randomCards);
            randomCards.clear();
            card = null;
            i = 0;
        }
        return true;
    }
    
    /**
     * Delete all the cards from the players
     */
    public void deleteCardsPlayers(){
        for(Jugador player: players){
            player.deleteCards();
        }
    }
    
    
    /**
     * In login, receives name and password and verificates that there is a player with such login data
     * @param name - String
     * @param password - String
     * @return Jugador
     */
    public Jugador verifyAccess(String name, String password){
        for(Jugador index: players){
            if(index.getName().equalsIgnoreCase(name) && index.getPassword().equals(password)){
                return index;
            }
        }
        return null;
    }
    
    /** 
     * Evaluates if players arraylist is empty
     * @return boolean
     */
    public boolean playersIsEmpty(){ 
        if(players.isEmpty()){
            return true;
        }
        return false;
    }
    
    /**
     * For login process, first we search the name introduced, if it doesn't exists the process doesn't continue
     * @param name - String
     * @return boolean
     */
    public boolean checkName(String name){
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Sets all players to state unlogged
     */
    public void unlogAll(){
        for(Jugador player: players){
            player.setLogged(false);
        }
    }
    
    /**
     * Returns an arraylist with all the players
     * @return ArrayList Jugador
     */
    public ArrayList<Jugador> getPlayers() {
        return players;
    }
    
    /**
     * Adds players received from the arraylist param and use this.players to store it
     * @param players - ArrayList Jugador
     */
    public void setPlayers(ArrayList<Jugador> players) {
        this.players = players;
    }

    /**
     * returns all available cards for the game
     * @return ArrayList Carta
     */
    public ArrayList<Carta> getAvailableCards() {
        return availableCards;
    }

    /**
     * Adds cards received from the arraylist param and use this.availableCards to store it
     * @param availableCards - ArrayList Carta
     */
    public void setAvailableCards(ArrayList<Carta> availableCards) {
        this.availableCards = availableCards;
    }
    
    
    
}
