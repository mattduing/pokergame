import java.io.*;
import java.util.*;

public class Player {
  private String name = "";
  private Card card_1, card_2;
  private int stack;
  private int x_pos, y_pos;
  private boolean folded;
  private int betAmt = 0;
   private int hand_big_rank;
  private int hand_sub_rank; // This rank will only matter in case of big rank ties
  private int hand_score;

  

  /**
  * This method constructs a player object
  * @param String for player name
  * @param Card for player's first card
  * @param Card for player's second card
  * @param int for player's stack
  */
  public Player(String string, Card one, Card two, int val) {
    this.folded = false;
    this.setName(string);
    this.setCard_1(one);
    this.setCard_2(two);
    this.setStack(val);
    this.setXPos(0);
    this.setYPos(0);
  }

  /**
  * Set method for a player's name
  * @param String player name
  */
  public void setName(String string) {
    name = string;
  }

  /**
  * Get method for a player's name
  * @return String player name
  */
  public String getName() {
    return name;
  }

  /**
  * Set method for a player's first card
  * @param Card object
  */
  public void setCard_1(Card one) {
    card_1 = one;
  }

  /**
  * Get method for a player's first card
  * @return Card object
  */
  public Card getCard_1() {
    return card_1;
  }

  /**
  * Set method for a player's second card
  * @param Card object
  */
  public void setCard_2(Card two) {
    card_2 = two;
  }

  /**
  * Get method for a player's second card
  * @return Card object
  */
  public Card getCard_2() {
    return card_2;
  }

  /**
  * Set method for a player's stack
  * @param int stack (should be 1000)
  */
  public void setStack(int val) {
    stack = val;
  }

  /**
  * A method to add to a player's stack (player wins money)
  * @param int money player has won
  */
  public void addToStack(int val) {
    stack = stack + val;
  }

   ////////////////////////////////////////////////////////////////////////////////
  /**
  * A method to subtract from a player's stack for a bet/ ante
  * @param int money player is losing from stack (betting)
  */
  public int betFromStack(int val) {
      Scanner keyboard = new Scanner(System.in); //initialize scanner
      System.out.println("HOW MUCH WOULD YOU LIKE TO BET?: "); //ask user how much they'd like to bet
      int betAmt = keyboard.nextInt(); //set betAmt to keyboard input
//Error checking
      /*
      if(betAmt <= 0) {
            System.out.println("YOU CAN NOT BET $0 OR LESS. THIS GAME IS FOR HIGH ROLLERS ONLY");
            betFromStack();
      }
      else if(betAmt > val) {
            System.out.println("WOAH THERE!! YOU CAN'T BET MORE MONEY THAN YOU HAVE");
            betFromStack();
      }
      else {
            System.out.println("YOU HAVE BET: " + betAmt);
            val = val - betAmt;
      }
      */
       System.out.println("YOU HAVE BET: " + betAmt);
            val = val - betAmt;

      return val;
  }


  /**
  * A method to subtract from a player's stack (player loses money)
  * @param int money player has lost
  */
  public void subFromStack(int val) {
    stack = stack - val;
  }

  /**
  * Set method for a player's stack
  * @param int stack (should be 1000)
  */
  public int getStack() {
    return stack;
  }

  /**
  * Set method for a player's x position
  * @param int xpos (0-4)
  */
  public void setXPos(int val) {
    x_pos = val;
  }

  /**
  * Get method for a player's x position
  * @return int xpos (0-4)
  */
  public int getXPos() {
    return x_pos;
  }

  /**
  * Set method for a player's y position
  * @param int ypos (0-4)
  */
  public void setYPos(int val) {
    y_pos = val;
  }

  /**
  * Get method for a player's y position
  * @return int ypos (0-4)
  */
  public int getYPos() {
    return y_pos;
  }

  public boolean getFolded() {
    return this.folded;
  }

  public void setFolded(boolean trueOrFalse) {
    this.folded = trueOrFalse;
  }

  /**
  * Get method for a player's minBet
  * @param int betAmt (must be <=1000)
  */
  public int getBet() {
        return betAmt;
  }

  public void setBet(int val) {
        betAmt = val;
  }

  public void setHandScore(int val) {
    hand_score = val;
  }

  public int getHandScore() {
    return hand_score;
  }

  public void setBigRank(int val) {
    hand_big_rank = val;
  }

  public int getBigRank() {
    return hand_big_rank;
  }

  public void setSubRank(int val) {
    hand_sub_rank = val;
  }

  public int getSubRank() {
    return hand_sub_rank;
  }

}
