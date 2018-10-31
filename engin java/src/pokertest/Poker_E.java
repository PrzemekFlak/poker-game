/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokertest;

import java.util.Random;

/**
 *
 * @author kanip
 */
public class Poker_E {
    private final double[] deck = {2.1, 2.2, 2.3, 2.4, 3.1, 3.2, 3.3, 3.4, 4.1, 4.2, 4.3, 4.4, 5.1, 5.2, 5.3, 5.4, 6.1, 6.2, 6.3, 6.4, 7.1, 7.2, 7.3, 7.4, 8.1, 8.2, 8.3, 8.4, 9.1, 9.2, 9.3, 9.4, 10.1, 10.2, 10.3, 10.4, 11.1, 11.2, 11.3, 11.4, 12.1, 12.2, 12.3, 12.4, 13.1, 13.2, 13.3, 13.4, 14.1, 14.2, 14.3, 14.4};
    private double[] p1hand = new double[2];
    private double[] p2hand = new double[2];
    private double[] flop = new double[3];
    private double turn;
    private double river;
    private double[] p1full = new double[7];
    private double[] p2full = new double[7];
    
    private double[] p1res = new double[7];
    private double[] p2res = new double[7];
    private int winner;
    private double[] winHand = new double[5];

    void dealHand(){
        
        Random rnd = new Random();
        int a1,a2,b1,b2,f1,f2,f3,t,r;
        
        a1 = rnd.nextInt(51);
        p1hand[0] = deck[a1];
        
        do {
            b1 = rnd.nextInt(51);
        } while (a1==b1);
        p2hand[0] = deck[b1];
        
        do {
            a2 = rnd.nextInt(51);
        } while (a2==a1 || a2==b1);
        p1hand[1] = deck[a2];
        
        do {
            b2 = rnd.nextInt(51);
        } while (b2==a1 || b2==b1 || b2==a2);
        p2hand[1] = deck[b2];
        
        do {
            f1 = rnd.nextInt(51);
        } while (f1==a1 || f1==b1 || f1==a2 || f1==b2);
        flop[0] = deck[f1];
        
        do {
            f2 = rnd.nextInt(51);
        } while (f2==a1 || f2==b1 || f2==a2 || f2==b2 || f2==f1);
        flop[1] = deck[f2];
        
        do {
            f3 = rnd.nextInt(51);
        } while (f3==a1 || f3==b1 || f3==a2 || f3==b2 || f3==f1 || f3==f2);
        flop[2] = deck[f3];
        
        do {
            t = rnd.nextInt(51);
        } while (t==a1 || t==b1 || t==a2 || t==b2 || t==f1 || t==f2 || t==f3);
        turn = deck[t];
        
        do {
            r = rnd.nextInt(51);
        } while (r==a1 || r==b1 || r==a2 || r==b2 || r==f1 || r==f2 || r==f3 || r==t);
        river = deck[r];
        
        p1full[0] = p1hand[0];
        p1full[1] = p1hand[1];
        p1full[2] = flop[0];
        p1full[3] = flop[1];
        p1full[4] = flop[2];
        p1full[5] = turn;
        p1full[6] = river;
        
        p2full[0] = p2hand[0];
        p2full[1] = p2hand[1];
        p2full[2] = flop[0];
        p2full[3] = flop[1];
        p2full[4] = flop[2];
        p2full[5] = turn;
        p2full[6] = river;
        
        revBubbleSort(p1full);
        revBubbleSort(p2full);
    
    }
    
    static void revBubbleSort(double[] arr) {  
        int n = arr.length;  
        double temp = 0;  
         for(int i=0; i < n; i++){  
                 for(int j=1; j < (n-i); j++){  
                          if(arr[j-1] < arr[j]){  
                                 //swap elements  
                                 temp = arr[j-1];  
                                 arr[j-1] = arr[j];  
                                 arr[j] = temp;  
                         }  
                          
                 }  
         }  
  
    } 
    void checkResult(double[]arr1, double[] arr2){
        int n = arr1.length;
        if ((int)arr1[0] > (int)arr2[0]){
            winner = 1;}
            
        }
        
    
        
    
    /*    double[] result = new double[7];
        
        checkFlush(p1full, p1res);
        
        checkFlush(p2full, p2res);
        whoWin(p1res, p2res);
        highLights(winner, winHand);
    */    
    
    
/*    static void checkFlush(double[] cards, double[] result){
        for (int i=0; i<3;i++){
            if (cards[i]-cards[i+1]==1){
                if (cards[i+1]-cards[i+2]==1){
                    if (cards[i+2]-cards[i+3]==1){
                        if (cards[i+3]-cards[i+4]==1){
                            if (cards[i+4]-cards[i+5]==1){
                                result[0] = 1;
                                result[1] = cards[i];
                                result[2] = 0;
                                result[3] = 0;
                                result[4] = 0;
                                result[5] = 0;
                                result[6] = 0;
                                result[7] = 0;
                            }
                        }
                    }
                }
            }
        }
            
    }
    static void whoWin(double[] res1, double[] res2){
        if (res1[0] < res2[0]){
            ;
        }
    }
    
    static void highLights(int winner, double[] winHand){
        
    }
*/
    
          
    public double[] getP1hand() {
        return p1hand;
    }

    public double[] getP2hand() {
        return p2hand;
    }

    public double[] getFlop() {
        return flop;
    }

    public double getTurn() {
        return turn;
    }

    public double getRiver() {
        return river;
    }

    public int getWinner() {
        return winner;
    }

    public double[] getP1full() {
        return p1full;
    }

    public double[] getP2full() {
        return p2full;
    }
    
}

