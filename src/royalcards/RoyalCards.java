/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package royalcards;

import controller.Manager;
import java.util.ArrayList;
import model.Carta;
import model.Jugador;


/**
 *
 * @author usuario
 */
public class RoyalCards {
    
    public static Manager manager;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        manager = new Manager();
        manager.createPlayersCards();                   //inicialization of ArrayLists of predefined players and card
        int option;
        do {
            showMenu();
            option = inputMethods.askInt("Choose an option");
            switch (option) {
                case 1:
                    getNewCards();
                    break;
                case 2:
                    manager.createPlayersCards(); //restoration of card values
                    battles();
                    break;
                case 3:
                    rankings();
                    break;
                case 4:
                    if(manager.loadCardsPlayers()) System.out.println("Cards loaded");
                    break;
                case 5:
                    manager.loadTrophys();
                    System.out.println("Trophys loaded");
                    break;
                case 0:
                    System.out.println("See ya!");
                    break;
                default:
                    System.out.println("Option not allowed");
            }
        } while (option != 0);
    }
    
    
    /**
     * Shows the players ordered by number of trophys, from max to min.
     * 
     */
    private static void rankings(){
        System.out.println("*** Rankings ***");
        manager.orderPlayersByTrofs();
        for(Jugador player: manager.getPlayers()){
            System.out.println(player.toString());
        }
        
    }
    
    
    /**
     * Method initializes when battle option is selected.
     * 
     */
     private static void battles(){
        
         System.out.println("*** Battles ***");
        //login of the players
         System.out.println("Player 1 login:");
        Jugador player1 = login();
        boolean cardsOk = true;
        //warning if any of them got less than 3 cards. They will be redirected to getNewCards.
        if(player1.getCards().size() < 3){
            System.out.println("Sorry. Player " + player1.getName() + " has got less than 3 cards. Logout and redirection to add more cards");
            player1.setLogged(false);
            cardsOk = false;
            getNewCards();
        }
         System.out.println("Player 2 login:");
        Jugador player2 = login();
        if(player2.getCards().size() < 3){
            System.out.println("Sorry. Player " + player2.getName() + " has got less than 3 cards. Logout and redirection to add more cards");
            player2.setLogged(false);
            cardsOk = false;
            getNewCards();
        }
        if(cardsOk){
            System.out.println("Initial data of players:");
            System.out.println(player1.toString());
            System.out.println(player2.toString());
            
           //arraylists for cards selected for battle (three cards each) 
           ArrayList<Carta> cardsPlayer1 = selectCardsBattle(player1);
           ArrayList<Carta> cardsPlayer2 = selectCardsBattle(player2);
           
           //who starts attack
            System.out.println("Trophys: " + player1.getName() + ": "+ player1.getNumTrof() + ", " + player2.getName()+": "+ player2.getNumTrof());
            if(player1.getNumTrof() > player2.getNumTrof()){
                System.out.println(player1.getName()+ " got more. He starts attack");   
            }
            else if(player1.getNumTrof() < player2.getNumTrof()){
                Jugador temp = player1;
                player1 = player2;
                player2 = temp;
                System.out.println(player1.getName()+ " got more. He starts attack");   
            }
            else{
                System.out.println("They got equal trophys. Random.");
                int num = manager.randomGenerate(0, 1);
                if(num == 1){
                    Jugador temp = player1;
                    player1 = player2;
                    player2 = temp;
                    System.out.println(player1.getName()+ " starts attack");   
                }
                else{
                    System.out.println(player1.getName()+ " starts attack");   
                }
            }
            System.out.println("\nAttack of "+ player1.getName());
            for(Carta cardAttack: cardsPlayer1){
                for(Carta cardDefense: cardsPlayer2){
                    System.out.println("Card " + cardAttack.getCardName() + " attacks " + cardDefense.getCardName());
                    attackCards(cardAttack, cardDefense);
                }
            }
            System.out.println("\nAttack of "+ player2.getName());
            for(Carta cardAttack: cardsPlayer2){
                for(Carta cardDefense: cardsPlayer1){
                    System.out.println("Card " + cardAttack.getCardName() + " attacks " + cardDefense.getCardName());
                    attackCards(cardAttack, cardDefense);
                }
            }
            int lifeCardsP1 = 0;
            for(Carta card: cardsPlayer1){
                lifeCardsP1 += card.getLifeLevel();
            }
            int lifeCardsP2 = 0;
             for(Carta card: cardsPlayer2){
                lifeCardsP2 += card.getLifeLevel();
            }
            System.out.println("Total life of cards player "+ player1.getName()+" = "+ lifeCardsP1);
            System.out.println("Total life of cards player "+ player2.getName()+" = "+ lifeCardsP2);
            if(lifeCardsP1 > lifeCardsP2){
                player1.setNumTrof(player1.getNumTrof() + 5);
                player2.setNumTrof(player2.getNumTrof() + 1);
                System.out.println("Player "+ player1.getName() + " adds 5 trophys");
                System.out.println("Player "+ player2.getName() + " adds 1 trophy");
            }
            else if(lifeCardsP1 < lifeCardsP2){
                player2.setNumTrof(player2.getNumTrof() + 5);
                player1.setNumTrof(player1.getNumTrof() + 1);
                System.out.println("Player "+ player2.getName() + " adds 5 trophys");
                System.out.println("Player "+ player1.getName() + " adds 1 trophy");
            }
            else{
                System.out.println("Life is equal. No trophys for anybody");
            }
            System.out.println("Final data of players:");
            System.out.println(player1.toString());
            System.out.println(player2.toString());
            manager.createPlayersCards(); //restoration to initial values
        }    
        
    }
     
     /**
     * Method of cards attack, receives attacking card and attack receiver card and then calculates
     * the damage produced.
     * 
     * @param attack - Carta
     * @param defense - Carta
     */
     public static void attackCards(Carta attack, Carta defense ){
         
         int totalAttack = attack.totalAttackPoints();
         int totalDefense = defense.totalDefensePoints();
         int result = totalAttack - totalDefense;
         System.out.println("Attack:"+ totalAttack+". Defense: "+totalDefense+". The result is "+result);
         if(result < 0){
             System.out.println("Negative. No life discount. She got "+defense.getLifeLevel()+ " life Points");
         }
         else{
             defense.setLifeLevel(result);
             System.out.println(defense.getCardName()+ " loses " + result+ " points. Now she got "+defense.getLifeLevel()+" life points" );
           
         }
         
         
         
     }
    
    /**
     * Method for selection of the 3 cards that player will need to the battle.
     * 
     * @param player - Jugador
     * @return ArrayList Carta
     */
    public static ArrayList<Carta> selectCardsBattle(Jugador player){
            
            System.out.println("Player " + player.getName() + " select cards:");
            
            ArrayList<Carta> selectedCards = new ArrayList<>();
            Carta card = null;
            int elixirSum = 0;
            int i = 1;
            int j = 1;
            while(i < 4){
                int option = -1;
                boolean right = false;
                do{
                  for(j = 1; j <= player.getCards().size(); j++){
                     showCard(player.getCards().get(j-1), j);
                  }
                  option = inputMethods.askInt("Select card " + i + " of 3");
                  if(option < 1 || option > player.getCards().size()){
                      System.out.println("Option not allowed");
                  }
                  else{
                      card = player.getCards().get(option-1);
                      if(selectedCards.contains(card)){
                          System.out.println("The player already selected that card. Choose another");
                      }
                      else{
                          elixirSum += card.getCostElixir();
                          if(elixirSum > 10){
                              System.out.println("Sorry. Cost of elixir sum couldn't excess 10 points. Choose another.");
                              System.out.println("PANIC BUTTON: Press Return to try with another card, or ANY OTHER to logout and redirection to add new card");
                              if(!inputMethods.continueMethod()){
                                  player.setLogged(false);
                                  getNewCards();
                              }
                          }
                          else{
                              selectedCards.add(card);
                              System.out.println("Card added");
                              right = true;
                              i++;
                          }
                      }
                  }
                }while(!right);
            }
            return selectedCards;     
 }           
    
    /**
     * Method for get new cards selection
     */
    private static void getNewCards(){
        
        manager.unlogAll(); //unlogin of all players, because if there is some problem with elixirPoints in battles option, we offer the option to come here
                            //to add new cards, and one of the players cannot remain logged (it's a long story)
        System.out.println("*** Get new cards ***");
        
        if(manager.playersIsEmpty()){
            System.out.println("There is no players registered yet");
        }
        else{
            Jugador player = login();   //first, player makes login
            if(player != null){
                // after verification, we show cards and offer adding new cards
                 if(player.getCards().isEmpty()){
                     System.out.println("Player " + player.getName() + " has got no cards yet");
                 }
                 else{
                     System.out.println(player.getName() + " cards:");
                     int i = 1;
                     for(Carta index: player.getCards()){
                      showCard(index, i);
                      i++;
                     }
                 }
                //options for adding cards, maximum it's 6 cards for player
                boolean moreCards = false;
                do{
                    moreCards = false;
                    if(player.getCards().size() < 6){
                      System.out.println("Player owns " + player.getCards().size() + " cards. Press Return to add new card. Any other to leave");
                      if(inputMethods.continueMethod()){
                         newCard(player);
                         moreCards = true;
                      }
                      else{
                          player.setLogged(false);
                      }
                  }
                  else{
                     System.out.println("Player " + player.getName() + " gots 6 cards at this moment. No more cards allowed."
                             + "Showing cards and returning to menu");
                     int i = 1;
                     player.setLogged(false);
                     for(Carta index: player.getCards()){
                       showCard(index, i);
                       i++;  
                     }
                     moreCards = false;
                   }
                 }while(moreCards);   
           }
        }
    }
          
    /**
     * Method for verification of logins
     * @return Jugador
     */      
    public static Jugador login(){
            boolean exit = false;
            do{
                exit = false;
                String name = inputMethods.askString("Player name (Default: player1, player2, player3, player4)");
                //verification of name
                if(!manager.checkName(name)){
                    System.out.println("No players with name " + name);
                    System.out.println("Press Return to try again. Any other to leave");
                    if(!inputMethods.continueMethod()){
                       exit = true; 
                    }
                }
                else{
                   //verification of password. If its correct we receive a player object.
                   String password = inputMethods.askString("Password (Default: pass1, pass2, pass3, pass4");
                   Jugador player = manager.verifyAccess(name, password);
                   if(player == null){
                      System.out.println("The password isn't correct");
                      System.out.println("Press Return to try again. Any other to leave");
                         if(!inputMethods.continueMethod()){
                             exit = true; 
                         } 
                   }
                   else{
                       if(!player.isLogged()){ 
                           player.setLogged(true);
                           return player;
                       }
                       else{
                           System.out.println("Player "+ name + " is already logged. Try another");
                       }
                   }
                 }
            }while(!exit);
            return null;
    }
      
    /**
     * Method to show a specified card data
     * 
     * @param index - Carta
     * @param i - int
     */
    public static void showCard(Carta index, int i){
              
                System.out.println("\nCard " + i);
                System.out.println(index.toString());
               
    }
    
    /**
     * Method for selection of a new card
     * 
     * @param player - Jugador
     */
    public static void newCard(Jugador player){
            
            int option;
            boolean exit = false;
            do{
                int i = 1;
                for(Carta card: manager.getAvailableCards()){
                    showCard(card, i);
                    i++;
                }
                option = inputMethods.askInt("Select a card (number between 1 and "+manager.getAvailableCards().size()+")");
                if(option < 1 || option > manager.getAvailableCards().size()){
                    System.out.println("Option not allowed");
                    exit = false;
                }
                else if(player.getCards().contains(manager.getAvailableCards().get(option-1))){
                    System.out.println("The player already has got this card. Choose another one");
                    System.out.print("HINT for test. Player cards are " );
                    for(Carta playerCard: player.getCards()){
                        System.out.print(playerCard.getCardName() + " ");
                    }
                    exit = false;
                }
                else{
                  player.addCard(manager.getAvailableCards().get(option -1));
                  System.out.println("Congrats! New card added.");
                  exit= true;
                }
            }while(!exit);
    }           
    
    
    /**
     * generation of the main menu option
     */
    private static void showMenu() {
        manager.unlogAll();
        System.out.println("\n");
        System.out.println("*** STUCOM ROYAL. Cards game ***");
        System.out.println("1. Obtain new cards");
        System.out.println("2. Start a battle");
        System.out.println("3. Rankings");
        System.out.println("4. DIRECT LOADING OF 6 RANDOM CARDS (FROM AVAILABLE) FOR EACH PLAYER");
        System.out.println("5. DIRECT LOADING OF 4 PLAYERS WITH DIFFERENT NUMBER OF TROPHYS (P1: 4, P2: 6, P3: 5, P4: 7)");
        System.out.println("0. Exit");
    }
    
    
}
