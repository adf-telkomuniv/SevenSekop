/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se7ensekop;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author undeed
 */
public class PlayerHuman extends Player {

    Scanner s;
    ArrayList<Card> tempHand;

    public PlayerHuman() {
        s = new Scanner(System.in);
        System.out.println("input nama : ");
        name = s.nextLine();
    }
    
    @Override
    public void initiateGame(Game g) {
        super.initiateGame(g);
        tempHand = (ArrayList<Card>) hand.clone();
        displayAll();
    }

    @Override
    void displayCloseable() {
        System.out.println("----closeable-----");
        for (int i = 0; i < closeable.size(); i++) {
            Card card = closeable.get(i);
            System.out.println(i + " " + card + " " + card.getClosedP());
        }
    }

    @Override
    void displayPlayable() {
        System.out.println("----playable-----");
        for (int i = 0; i < playable.size(); i++) {
            Card card = playable.get(i);
            System.out.println(i + " " + card);
        }
    }
    
    @Override
    public void displayAll(){
        System.out.println("===========xx=======");
        System.out.println("player : " + name);
        for (Card card : tempHand) {
            System.out.println(card);
        }
    }

    @Override
    public void play(Game g) {
        displayAll();
        g.viewTable();
        System.out.println("player = " + name);

        getPlayable(g);
        displayPlayable();
        sortCloseable();
        displayCloseable();
        int pil;

        Card played;
        if (playable.size() > 0) {
            g.setStart();

            s = new Scanner(System.in);
            do {
                System.out.println("input choice : ");
                pil = s.nextInt();
            } while (pil < 0 || pil >= playable.size());

            played = playable.remove(pil);
            tempHand.remove(played);

            // ask if 2 and king both present but position not yet determined
            ArrayList<Card> temp = g.getPlayed()[played.getSuit()];
            if (played.getRank() == 0 && temp.get(0).getRank() == 1 && temp.get(temp.size() - 1).getRank() == 12) {
                do {
                    System.out.println("input choice\n1. above\n2. below");
                    pil = s.nextInt();
                } while (pil != 1 && pil != 2);
                if (pil == 1) {
                    played.setRank(13);
                } else {
                    played.setRank(0);
                }
            }

            // default = closed at below
            if (played.getRank() == 0) {
                g.setPosition(-1);
                System.out.println("bawah");
            } else if (played.getRank() == 13) {
                g.setPosition(1);
                System.out.println("atas");
            }

            System.out.println(name + " played " + played);
            g.addToPlayed(played.getSuit(), played);
        } else if (g.getStarted()) {
            s = new Scanner(System.in);
            do {
                System.out.println("input choice : ");
                pil = s.nextInt();
            } while (pil < 0 || pil >= closeable.size());
            System.out.println(name + " closed " + closeable.get(pil));
            tempHand.remove(closeable.get(pil));
            closed.add(closeable.remove(pil));
        }
        System.out.println("============================");
    }
}
