/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se7ensekop;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author undeed
 */
public class Game {

    private ArrayList<Card> cards;
    private ArrayList<Card>[] played;
    private boolean closed[];
    private boolean start;
    private int position;
    private String[][] tbl;

    public Game() {
        start = false;
        cards = new ArrayList();
        int index_1, index_2;
        Random generator = new Random();
        Card temp;
        tbl = new String[14][4];
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 4; j++) {
                tbl[i][j] = "";
            }
        }

        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 13; b++) {
                cards.add(new Card(a, b));
            }
        }

        for (int i = 0; i < 100; i++) {
            index_1 = generator.nextInt(cards.size() - 1);
            index_2 = generator.nextInt(cards.size() - 1);

            temp = cards.get(index_2);
            cards.set(index_2, cards.get(index_1));
            cards.set(index_1, temp);
        }

        played = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            played[i] = new ArrayList<>();
        }

        closed = new boolean[4];
        position = 0;
    }

    public void setPosition(int i) {
        position = i;
    }

    public int getPosition() {
        return position;
    }

    public void setClosed(int i) {
        closed[i] = true;
    }

    public boolean getClosed(int i) {
        return closed[i];
    }

    public void setStart() {
        start = true;
    }

    public boolean getStarted() {
        return start;
    }

    public void Play(Player p1, Player p2, Player p3, Player p4) {

        p1.initiateGame(this);
        p2.initiateGame(this);
        p3.initiateGame(this);
        p4.initiateGame(this);
        System.out.println("");
        int i = 0;
        try {
            while (p1.getNumCard() > 0 || p2.getNumCard() > 0 || p3.getNumCard() > 0 || p4.getNumCard() > 0) {
                System.out.println("play : " + (i + 1));

                p1.play(this);
                p2.play(this);
                p3.play(this);
                p4.play(this);
                i++;
            }
            System.out.println("turn end");
        } catch (Exception e) {
            if (p1.getNumCard() + p2.getNumCard() + p3.getNumCard() + p4.getNumCard() == 0) {
                System.out.println("turn end");
            } else {
                System.out.println("err at play " + (i + 1));
                e.printStackTrace();
            }
        }
        System.out.println("p1 = " + p1.countLose(this));
        System.out.println("p2 = " + p2.countLose(this));
        System.out.println("p3 = " + p3.countLose(this));
        System.out.println("p4 = " + p4.countLose(this));

        if (getPosition() == 1) {
            System.out.println("atas");
        } else if (getPosition() == -1) {
            System.out.println("bawah");
        } else {
            System.out.println("not closed");
        }

        p1.displayClosed();
        p2.displayClosed();
        p3.displayClosed();
        p4.displayClosed();

    }

    public void viewTable() {
//        int mx = Math.max(Math.max(played[0].size(), played[1].size()),
//                Math.max(played[2].size(), played[3].size()));

        System.out.println("hearts|\tspades|\tdiamonds|\tclubs");
//        for (int j = 0; j < 14; j++) {
//            for (int k = 0; k < 4; k++) {
//                System.out.print(j + " ");
//                if (j < played[k].size()) {
//                    System.out.print(played[k].get(j).toString() + "|\t");
//                    
//                } else {
//                    System.out.print("\t|\t");
//                }
//            }
//            System.out.println();
//        }
        for (int i = 0; i < 14; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 4; j++) {
                System.out.print(tbl[i][j] + "|\t");
            }
            System.out.println("");
        }
    }

    public void addToPlayed(int i, Card c) {

        if (c.getRank() == 0) {
            setClosed(c.getSuit());
        }

        if ((played[i].size() > 0) && (played[i].get(0).getRank() > c.getRank())) {
            played[i].add(0, c);
            tbl[c.getRank()][i] = "x";
        } else {
            played[i].add(c);
            tbl[c.getRank()][i] = "x";
        }
    }

    public ArrayList<Card>[] getPlayed() {
        return played;
    }

    public Card drawFromDeck() {
        return cards.remove(0);
    }

    public int getTotalCards() {
        return cards.size();   //we could use this method when making a complete poker game to see if we needed a new deck
    }
}
