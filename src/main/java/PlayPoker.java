import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
* This class handles the player through and functionality of one round of the
* game
*/
public class PlayPoker {
  private static ArrayList<Player> players;
  private static boolean flop_bool = false;
  private static boolean turn_bool = false;
  private static boolean river_bool = false;
  private static boolean flag;
  //private static int currentMinimumBet = 20;

  /**
  * This method is the actual structure of the game, and will iteratre through
  * all betting stages of a round when called
  */
  public void play() {
    players = TexasHoldem.getPlayers(); // Initialize players
    Logger log = new Logger(); // Initialize logger instance
    HandRank hands = new HandRank(players); // Initialize hand rank instance

    log.preFlop(); // Mark preflop in logger
    TexasHoldem.setBlinds(players.size()-1, players.size()-2); // Assign blinds
    hands.rankHands(); // Rank player hands for pre-flop

    takeTurn(); // Take the 1stturn

    flop(); // Add three center cards
    TexasHoldem.update(); // Refresh the board
    pause();

    takeTurn(); // Take the 2nd turn
    log.flop(); // Mark flop in logger
    hands.rankHands(); // Rank player hands for flop

    turn(); // Add 4th center card
    TexasHoldem.update(); // Refresh the board
    pause();

    takeTurn(); // Take the 3rd turn
    log.turn(); // Mark turn in logger
    hands.rankHands(); // Rank player hands for turn

    river(); // Add 5th center card
    TexasHoldem.update(); // Refresh the board
    pause();

    takeTurn(); // Take the 4th turn
    log.river(); // Mark river in logger
    hands.rankHands(); // Rank player hands for river

    log.finish(); // Close logger and send changes to log.txt
  }

  /**
  * Method to check the number of community cards on the table
  * @return boolean flop flag
  */
  public static boolean isFlop() {
    return flop_bool;
  }

  /**
  * Method to check the number of community cards on the table
  * @return boolean turn flag
  */
  public static boolean isTurn() {
    return turn_bool;
  }

  /**
  * Method to check the number of community cards on the table
  * @return boolean river flag
  */
  public static boolean isRiver() {
    return river_bool;
  }

  public void takeTurn() {
    flag = false;

    for (int i = 0; i < players.size(); i++) {
      unHighlight();

      if (players.get(i).getAction().equals("FOLD")) {
        continue;
      } else {
        highlight(i);

        if (i == 0) { // It's the user's turn
          TexasHoldem.toggleButtons();
          while (flag == false) {
            try {
              Thread.sleep(200);
            } catch(InterruptedException e) {
              // Do nothing
            }
          }
          TexasHoldem.toggleButtons();
        } else { // It's an AI's turn
          String action = AI.getAction(players.get(i).getHandScore());
          players.get(i).setAction(action);
          TexasHoldem.refreshPlayer(i, TexasHoldem.showPlayer(players.get(i)));
          takeAction(i);
        }
      }

      pause();
      TexasHoldem.update();
    }
  }

  public static void takeAction(int i) {
    if (players.get(i).getAction().equals("FOLD")) {
      players.get(i).getCard_1().fold();
      players.get(i).getCard_2().fold();
      TexasHoldem.refreshPlayer(i, TexasHoldem.showPlayer(players.get(i)));
      Logger.logAction(i, "folded");
    }
    if (players.get(i).getAction().equals("CALL")) {
      players.get(i).setBet(TexasHoldem.call);
      players.get(i).subFromStack(TexasHoldem.call);
      TexasHoldem.the_pot.addToPot(TexasHoldem.call);
      //TexasHoldem.the_pot.getPotJPanel().revalidate();
      //TexasHoldem.the_pot.getPotJPanel().repaint();
      TexasHoldem.refreshPlayer(i, TexasHoldem.showPlayer(players.get(i)));
      Logger.logAction(i, "called");
    }

  }  

  public static void toggleFlag() {
    flag = true;
  }

  /**
  * This method provides delay for iteration in the AI player moves
  */
  public void pause() {
    long time_0 = System.currentTimeMillis();
    long time_1;
    long run_time = 0;

    while(run_time < 1000) {
      time_1 = System.currentTimeMillis();
      run_time = time_1 - time_0;
    }
  }

  /**
  * Method to flip over the 3 center cards once the flop begins
  */
  public void flop() {
    FlopTurnRiver ftr = TexasHoldem.getFTR();
    ftr.getCard_1().flip();
    ftr.getCard_2().flip();
    ftr.getCard_3().flip();

    JPanel the_panel = ftr.getCardsJPanel();
    TexasHoldem.updateCenterCards(the_panel);

    flop_bool = true;
  }

  /**
  * Method to add a 4th card to the 3 flop cards once the turn begins
  */
  public void turn() {
    Deck deck = TexasHoldem.getDeck();
    FlopTurnRiver ftr = TexasHoldem.getFTR();
    ftr.addCard(deck.draw());
    ftr.getCard_4().flip();

    TexasHoldem.updateDeck(deck);

    JPanel the_panel = ftr.getCardsJPanel();
    TexasHoldem.updateCenterCards(the_panel);

    turn_bool = true;
  }

  /**
  * Method to add a 5th card to the 4 turn cards once the river begins
  */
  public void river() {
    Deck deck = TexasHoldem.getDeck();
    FlopTurnRiver ftr = TexasHoldem.getFTR();
    ftr.addCard(deck.draw());
    ftr.getCard_5().flip();

    TexasHoldem.updateDeck(deck);

    JPanel the_panel = ftr.getCardsJPanel();
    TexasHoldem.updateCenterCards(the_panel);

    river_bool = true;
  }

  /**
  * This method indicates which player is currently taking a turn by changing
  * the border color to white
  */
  public void highlight(int index) {
    JPanel the_panel = TexasHoldem.getPlayerPanel(index);
    the_panel.setBorder(BorderFactory.createLineBorder(Color.white));

    TexasHoldem.updatePlayer((index + 1), the_panel);
  }

  /**
  * This method reverts player borders back to black
  */
  public void unHighlight() {
    for (int i = 0; i < players.size(); i++) {
      JPanel the_panel = TexasHoldem.getPlayerPanel(i);
      the_panel.setBorder(BorderFactory.createLineBorder(Color.black));

      TexasHoldem.updatePlayer((i + 1), the_panel);
    }
  }

  /**
  * This method gets the index of the previous player, if the current player
  * is at the last index of the players ArrayList, it avoids an out of bounds
  * exception
  */
  public int getPrevIndex(int index) {
    if (index == 0) {
      index = players.size() - 1;
    } else {
      index = index - 1;
    }

    return index;
  }
}
