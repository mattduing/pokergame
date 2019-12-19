import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import java.util.Date;
import java.util.ArrayList;

public class Logger {
  private static PrintWriter pw;
  private FileWriter fw;
  private BufferedWriter bw;

  private static ArrayList<Player> players = new ArrayList<Player>();

  public Logger() {
    players = TexasHoldem.getPlayers();
    File temp = new File("src/main/resources/log.txt");
    boolean exists = temp.exists();

    if (exists) {
      try {
        fw = new FileWriter("src/main/resources/log.txt", true);
      } catch (IOException e) {

      }

      BufferedWriter bw = new BufferedWriter(fw);

      pw = new PrintWriter(bw);
      start();
    } else {
      try {
        pw = new PrintWriter("src/main/resources/log.txt", "UTF-8");
      } catch (FileNotFoundException | UnsupportedEncodingException e) {

      }

      start();
    }
  }

  public void start() {
    // Date
    Date date = new Date();
    pw.println("================================================================================");
    pw.println("================================================================================");
    pw.println("GAME STARTED - " + date.toString() + "\n");

    // Player 1
    pw.println("PLAYER NAME:\n\t" + players.get(0).getName());

    // AI Players
    String ai_names = "";
    for (int i = 1; i < players.size(); i++) {
      ai_names += players.get(i).getName();

      if (i < (players.size() - 1)) {
        ai_names += ", ";
      }
    }
    pw.println("\nAI PLAYERS:\n\t" + ai_names + "\n");

    // Cards dealt
    pw.println("CARDS DEALT: ");
    for (int i = 0; i < players.size(); i++) {
      String temp = "\t";
      temp += players.get(i).getName();
      temp += ": --> ";
      temp += players.get(i).getCard_1().toString();
      temp += " ";
      temp += players.get(i).getCard_2().toString();
      pw.println(temp);
    }
  }

  public static void logRank(int index, String rank) {
    pw.println("\n" + players.get(index).getName() + "'s best hand:");
    pw.println("\t" + rank);
  }

  public static void preFlop() {
    pw.println("------PRE-FLOP----PRE-FLOP----PRE-FLOP----PRE-FLOP----PRE-FLOP----PRE-FLOP------");
  }

  public static void flop() {
    pw.println("------FLOP----FLOP----FLOP----FLOP----FLOP----FLOP----FLOP----FLOP----FLOP------");
  }

  public static void turn() {
    pw.println("------TURN----TURN----TURN----TURN----TURN----TURN----TURN----TURN----TURN------");
  }

  public static void river() {
    pw.println("------RIVER----RIVER----RIVER----RIVER----RIVER----RIVER----RIVER----RIVER------");
  }

  public void finish() {
    pw.close();
  }
}