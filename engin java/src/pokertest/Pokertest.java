/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokertest;

/**
 *
 * @author kanip
 */
public class Pokertest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Poker_E pe = new Poker_E();
        pe.dealHand();
        double[] a = pe.getP1hand();
        double[] b = pe.getP2hand();
        double[] c = pe.getFlop();
        double d = pe.getTurn();
        double e = pe.getRiver();
        double[] f = pe.getP1full();
        double[] g = pe.getP2full();
        System.out.println(a[0]+"  "+b[0]);
        System.out.println(a[1]+"  "+b[1]+"\n");
        System.out.println(c[0]);
        System.out.println(c[1]);
        System.out.println(c[2]);
        System.out.println(d);
        System.out.println(e+"\n");
        System.out.println(f[0]+"  "+g[0]);
        System.out.println(f[1]+"  "+g[1]);
        System.out.println(f[2]+"  "+g[2]);
        System.out.println(f[3]+"  "+g[3]);
        System.out.println(f[4]+"  "+g[4]);
        System.out.println(f[5]+"  "+g[5]);
        System.out.println(f[6]+"  "+g[6]);
                
    }
    
}
