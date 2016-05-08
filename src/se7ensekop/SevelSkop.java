/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se7ensekop;

import java.util.ArrayList;

/**
 *
 * @author undeed
 */
public class SevelSkop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

//        String a = "abcdefghij";
//        String b = a.substring(0, 6);
//        System.out.println("a = " + a);
//        System.out.println("b = " + b);
//        
//        String c = a.substring(3);
//        System.out.println(c);
        
        Game g = new Game();
        Player Player1 = new PlayerHuman();
        Player Player2 = new PlayerHuman();
//        Player Player2 = new Player("p2");
        Player Player3 = new Player("p3");
        Player Player4 = new Player("p4");
        g.Play(Player1, Player2, Player3, Player4);

    }
}
