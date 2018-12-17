/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import engine.Result;
import java.util.ArrayList;
import java.util.Random;



/**
 *
 * @author kanip
 */
public class Poker_E {
    private ArrayList<Card> deck = new ArrayList(52);
    private ArrayList<Card> p1hand = new ArrayList(2);
    private ArrayList<Card> p2hand = new ArrayList(2);
    private ArrayList<Card> flop = new ArrayList(3);
    private Card turn;
    private Card river;
    private ArrayList<Card> p1full = new ArrayList(7);
    private ArrayList<Card> p2full = new ArrayList(7);
    private String allCards1, allCards2;
    
    private int[] p1res = new int[7];
    private int[] p2res = new int[7];
    private int winner;
    private ArrayList<Card> winHand = new ArrayList(5);
    private Result p1result = new Result(0, "");
    private Result p2result = new Result(0, "");
    
    private String p1c1, p1c2, p2c1, p2c2, f1c, f2c, f3c, tc, rc, hand;
    
    private   ArrayList<Card> test = new ArrayList(7);
    private  Card c1 = new Card();
    private  Card c2 = new Card();
    private    Card c3 = new Card();
    private    Card c4 = new Card();
    private    Card c5 = new Card();
    private    Card c6 = new Card();
    private    Card c7 = new Card();
    
    void setTest(){
        c1.setValue(14);
        c2.setValue(13);
        c3.setValue(4);
        c4.setValue(4);
        c5.setValue(4);
        c6.setValue(4);
        c7.setValue(3);
        c1.setSuit(Card.Suit.HEART);
        c2.setSuit(Card.Suit.SPADE);
        c3.setSuit(Card.Suit.SPADE);
        c4.setSuit(Card.Suit.SPADE);
        c5.setSuit(Card.Suit.CLUB);
        c6.setSuit(Card.Suit.CLUB);
        c7.setSuit(Card.Suit.SPADE);
              
        test.add(c1);
        test.add(c2);
        test.add(c3);
        test.add(c4);
        test.add(c5);
        test.add(c6);
        test.add(c7);
        
        for (int i = 0; i< test.size(); i++){
            System.out.println(test.get(i).toString());
        }
    }
    
    void setDeck(){
        for (int i=0; i<13; i++){
            for (int j=1; j<=4; j++){
                Card card = new Card();
                switch(j) {
                    case 1: card.setSuit(Card.Suit.SPADE);
                    break;
                    case 2: card.setSuit(Card.Suit.CLUB);
                    break;
                    case 3: card.setSuit(Card.Suit.HEART);
                    break;
                    case 4: card.setSuit(Card.Suit.DIAMOND);
                    break;
                }
                if (i==12) {
                    card.setValue(14);
                } else {
                    card.setValue(i+2);
                }
                deck.add(card);
            }
        }
    }
    void dealHand(){
        
        Random rnd = new Random();
        int a1,a2,b1,b2,f1,f2,f3,t,r;
        
        a1 = rnd.nextInt(51);
        p1hand.add(deck.get(a1));
        
        do {
            b1 = rnd.nextInt(51);
        } while (a1==b1);
        p2hand.add(deck.get(b1));
        
        do {
            a2 = rnd.nextInt(51);
        } while (a2==a1 || a2==b1);
        p1hand.add(deck.get(a2));
        
        do {
            b2 = rnd.nextInt(51);
        } while (b2==a1 || b2==b1 || b2==a2);
        p2hand.add(deck.get(b2));
        
        do {
            f1 = rnd.nextInt(51);
        } while (f1==a1 || f1==b1 || f1==a2 || f1==b2);
        flop.add(deck.get(f1));
        
        do {
            f2 = rnd.nextInt(51);
        } while (f2==a1 || f2==b1 || f2==a2 || f2==b2 || f2==f1);
        flop.add(deck.get(f2)); 
        
        do {
            f3 = rnd.nextInt(51);
        } while (f3==a1 || f3==b1 || f3==a2 || f3==b2 || f3==f1 || f3==f2);
        flop.add(deck.get(f3)); 
        
        do {
            t = rnd.nextInt(51);
        } while (t==a1 || t==b1 || t==a2 || t==b2 || t==f1 || t==f2 || t==f3);
        turn = deck.get(t);
        
        do {
            r = rnd.nextInt(51);
        } while (r==a1 || r==b1 || r==a2 || r==b2 || r==f1 || r==f2 || r==f3 || r==t);
        river = deck.get(r);
        
        p1full.add(p1hand.get(0));
        p1full.add(p1hand.get(1)); 
        p1full.add(flop.get(0));
        p1full.add(flop.get(1));
        p1full.add(flop.get(2));
        p1full.add(turn);
        p1full.add(river);
        
        p2full.add(p2hand.get(0));
        p2full.add(p2hand.get(1)); 
        p2full.add(flop.get(0));
        p2full.add(flop.get(1));
        p2full.add(flop.get(2));
        p2full.add(turn);
        p2full.add(river);
        
        p1c1 = p1hand.get(0).toString();
        p1c2 = p1hand.get(1).toString();
        p2c1 = p2hand.get(0).toString();
        p2c2 = p2hand.get(1).toString();
        f1c = flop.get(0).toString();
        f2c = flop.get(1).toString();
        f3c = flop.get(2).toString();
        tc = turn.toString();
        rc = river.toString();
                
        /*for (int l=0; l<2; l++){
            System.out.println("player 1 card "+(l+1)+": "+p1hand.get(l).toString()+"\n");
            
        }
        for (int l=0; l<2; l++){
        System.out.println("player 2 card "+(l+1)+": "+p2hand.get(l).toString()+"\n");
        }
        
        for (int l=0; l<3; l++){
            System.out.println("flop card "+(l)+": "+flop.get(l).toString()+"\n");
        }
        
        System.out.println("turn card: "+turn.toString()+"\n");
        System.out.println("rivet card: "+river.toString()+"\n");*/
                
        revBubbleSort(p1full);
        revBubbleSort(p2full);
        /*
        for (int l=0; l<7; l++){
            System.out.println("player 1 fullhand card "+(l+1)+": "+p1full.get(l).toString()+"\n");
        }
        for (int l=0; l<7; l++){
        System.out.println("player 2 fullhand card "+(l+1)+": "+p2full.get(l).toString()+"\n");
        }
        */
    } 
    
    static void revBubbleSort(ArrayList<Card> arr) {  
        int n = arr.size();  
        Card temp = null;  
        Card v1 = null;
        Card v2 = null;
        for(int i=0; i < n; i++){  
                 for(int j=1; j < (n-i); j++){ 
                     v1 = arr.get(j-1);
                     v2 = arr.get(j);
                          if(v1.getValue() < v2.getValue()){  
                                 //swap elements  
                                 temp = v1;  
                                 arr.set(j-1, v2);  
                                 arr.set(j, temp);
                                 
                         }  
                          
                 }  
         }   
  
    }
    
    void checkResults(){
       
        checkStraightFlush(p1full, p1result);
        checkStraightFlush(p2full, p2result);
        //System.out.println("Value of hand: " + p1result.getHandValue());
        //System.out.println(p1result.getHand());
        //System.out.println("Value of hand: " + p2result.getHandValue());
        //System.out.println(p2result.getHand());
        whoWin(p1result, p2result);
        //System.out.println(winner);
        //System.out.println(p1result.getHand());
        //System.out.println(p2result.getHand());
    }


    void whoWin(Result result1, Result result2){
        if (result1.getHandValue() > result2.getHandValue()) winner = 1; 
        else if (result1.getHandValue() < result2.getHandValue()) winner = 2; 
        else { 
            switch(result1.getHandValue()){
                case 8: if (result1.getHand1() > result2.getHand1()) {winner =1; p1result.setHand(p1result.getHand() + " win by high card"); break;}
                        else if (result1.getHand1() < result2.getHand1()) {winner =2; p2result.setHand(p2result.getHand() + " win by high card"); break;}
                
                case 7: if (result1.getHand1() > result2.getHand1()) {winner = 1; p1result.setHand(p1result.getHand() + " win by high card"); break;}
                        else if (result1.getHand1() < result2.getHand1()) {winner = 2; p2result.setHand(p2result.getHand() + " win by high card"); break;}
                        else {
                            for (int i=0; i<5; i++){
                                if (p1full.get(i).getValue() > p2full.get(i).getValue()) {winner =1; p1result.setHand(p1result.getHand() + " win by high card"); break;}
                                else if (p1full.get(i).getValue() < p2full.get(i).getValue()) {winner =2; p2result.setHand(p2result.getHand() + " win by high card"); break;}
                            }
                        }
                
                case 6: if (result1.getHand1() > result2.getHand1()) {winner = 1; p1result.setHand(p2result.getHand() + " win by high card"); break;}
                        else if (result1.getHand1() < result2.getHand1()) {winner = 2; p2result.setHand(p2result.getHand() + " win by high card"); break;}
                        else if (result1.getHand2() > result2.getHand2()) {winner = 1; p1result.setHand(p1result.getHand() + " win by high card"); break;}
                        else if (result1.getHand2() < result2.getHand2()) {winner = 2; p2result.setHand(p2result.getHand() + " win by high card"); break;}
                
                case 5: for (int i=0; i<5; i++){
                            if (p1full.get(i).getValue() > p2full.get(i).getValue()) {winner =1; p1result.setHand(p1result.getHand() + " win by high card"); break;}
                            else if (p1full.get(i).getValue() < p2full.get(i).getValue()) {winner =2; p2result.setHand(p2result.getHand() + " win by high card"); break;}
                }
                
                case 4: if (result1.getHand1() > result2.getHand1()) {winner =1; p1result.setHand(p1result.getHand() + " win by high card"); break;}
                        else if (result1.getHand1() < result2.getHand1()) {winner =2; p2result.setHand(p2result.getHand() + " win by high card"); break;}
                
                case 3: if (result1.getHand1() > result2.getHand1()) {winner =1; p1result.setHand(p1result.getHand() + " win by high card"); break;}
                        else if (result1.getHand1() < result2.getHand1()) {winner =2; p2result.setHand(p2result.getHand() + " win by high card"); break;}
                        else {
                            for (int i=0; i<5; i++){
                                if (p1full.get(i).getValue() > p2full.get(i).getValue()) {winner =1; p1result.setHand(p1result.getHand() + " win by high card"); break;}
                                else if (p1full.get(i).getValue() < p2full.get(i).getValue()) {winner =2; p2result.setHand(p2result.getHand() + " win by high card"); break;}
                            }
                        }
                
                case 2: if (result1.getHand1() > result2.getHand1()) {winner =1; p1result.setHand(p1result.getHand() + " win by high card"); break;}
                        else if (result1.getHand1() < result2.getHand1()) {winner =2; p2result.setHand(p2result.getHand() + " win by high card"); break;}
                        else if (result1.getHand2() > result2.getHand2()) {winner = 1; p1result.setHand(p1result.getHand() + " win by high card"); break;}
                        else if (result1.getHand2() < result2.getHand2()) {winner = 2; p2result.setHand(p2result.getHand() + " win by high card"); break;}
                        else {
                            for (int i=0; i<5; i++){
                                if (p1full.get(i).getValue() > p2full.get(i).getValue()) {winner =1; p1result.setHand(p1result.getHand() + " win by high card"); break;}
                                else if (p1full.get(i).getValue() < p2full.get(i).getValue()) {winner =2; p2result.setHand(p2result.getHand() + " win by high card"); break;}
                            }
                        }
                
                case 1: if (result1.getHand1() > result2.getHand1()) {winner =1; p1result.setHand(p1result.getHand() + " win by high card"); break;}
                        else if (result1.getHand1() < result2.getHand1()) {winner =2; p2result.setHand(p2result.getHand() + " win by high card"); break;}
                        else {
                            for (int i=0; i<5; i++){
                                if (p1full.get(i).getValue() > p2full.get(i).getValue()) {winner =1; p1result.setHand(p1result.getHand() + " win by high card"); break;}
                                else if (p1full.get(i).getValue() < p2full.get(i).getValue()) {winner =2; p2result.setHand(p2result.getHand() + " win by high card"); break;}
                            }
                        }
                
                case 0: for (int i=0; i<5; i++){
                            if (p1full.get(i).getValue() > p2full.get(i).getValue()) {winner =1; p1result.setHand(p1result.getHand() + " win by high card"); break;}
                            else if (p1full.get(i).getValue() < p2full.get(i).getValue()) {winner =2; p2result.setHand(p2result.getHand() + " win by high card"); break;}
                        }
            
            }
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }
        
    void checkStraightFlush(ArrayList<Card> arr, Result result){
        
        for (int i=0; i<(arr.size()-4); i++){
            if ((arr.get(i).getValue() - arr.get(i+1).getValue() == 1) & (arr.get(i+1).getValue() - arr.get(i+2).getValue() == 1) & (arr.get(i+2).getValue() - arr.get(i+3).getValue() == 1) & (arr.get(i+3).getValue() - arr.get(i+4).getValue() == 1) & ((arr.get(i).getSuit()== arr.get(i+1).getSuit()) & (arr.get(i).getSuit()== arr.get(i+2).getSuit()) & (arr.get(i).getSuit()== arr.get(i+3).getSuit()) & (arr.get(i).getSuit()== arr.get(i+4).getSuit()))){
            result.setHandValue(8);
            result.setHand("Straight flush! ");
            result.setHand1(arr.get(i).getValue());
            break;
            }
        }
                
        if (result.getHandValue() !=8) {checkQuads(arr, result);}
    }
    
    void checkQuads(ArrayList<Card> arr, Result result){

        for (int i=0; i<3; i++){
            if ((arr.get(i).getValue() - arr.get(i+1).getValue() == 0) & (arr.get(i+1).getValue() - arr.get(i+2).getValue() == 0) & (arr.get(i+2).getValue() - arr.get(i+3).getValue() == 0)){
                result.setHandValue(7);
                result.setHand("Quads ");
                result.setHand1(arr.get(i).getValue());
                break;
            }
        }
        if (result.getHandValue() !=7) {checkFullHouseThree(arr, result);}
    }
    void checkFullHouseThree(ArrayList<Card> arr, Result result){
        ArrayList<Card> house = new ArrayList();
        int three =0;
        for (int k=0; k<7; k++){
            house.add(arr.get(k));
        }
        for (int i=0; i<5; i++){
            if ((house.get(i).getValue() - house.get(i+1).getValue()== 0) & (house.get(i+1).getValue() - house.get(i+2).getValue() == 0)){
                three = house.get(i).getValue();
                house.remove(i+2);
                house.remove(i+1);
                house.remove(i);
                checkFullHouseTwo(arr, house, three, result);
                break;
            }
        }
        if (result.getHandValue() !=6) checkFlush(arr, result);
    }
    void checkFullHouseTwo(ArrayList<Card> arr, ArrayList<Card> house, int three, Result result){
        int two;
        for (int j=0; j<3; j++){
            if (house.get(j).getValue()- house.get(j+1).getValue() == 0){
                two = house.get(j).getValue();
                result.setHandValue(6);
                result.setHand("Full house: " + three + " over " + two);
                result.setHand1(three);
                result.setHand2(two);
                break;
                }
            }
        result.setHandValue(0);
        if (result.getHandValue() !=6) checkFlush(arr, result);
    }
    
    void checkFlush(ArrayList<Card> arr, Result result){
        ArrayList<Card> spade = new ArrayList();
        ArrayList<Card> heart = new ArrayList();
        ArrayList<Card> club = new ArrayList();
        ArrayList<Card> diamond = new ArrayList();
        for (int i=0; i<7; i++){ 
            if (arr.get(i).getSuit().toString()=="SPADE") {spade.add(arr.get(i));}
            if (arr.get(i).getSuit().toString()=="HEART") {heart.add(arr.get(i));}
            if (arr.get(i).getSuit().toString()=="CLUB") {club.add(arr.get(i));}
            if (arr.get(i).getSuit().toString()=="DIAMOND") {diamond.add(arr.get(i));}
           
        }
        if ((spade.size() >=5) || (heart.size() >=5) || (club.size() >= 5) || (diamond.size()>=5)){
            result.setHandValue(5);
            result.setHand("Flush ");
            
        }
        if (result.getHandValue() !=5){checkStraight(arr, result);}
    }
    
    void checkStraight(ArrayList<Card> arr, Result result){
        
        for (int i=0; i<2; i++){
            if ((arr.get(i).getValue() - arr.get(i+1).getValue() == 1) & (arr.get(i+1).getValue() - arr.get(i+2).getValue() == 1) & (arr.get(i+2).getValue() - arr.get(i+3).getValue() == 1) & (arr.get(i+3).getValue() - arr.get(i+4).getValue() == 1)){
                result.setHandValue(4);
                result.setHand("Straight ");
                result.setHand1(arr.get(i).getValue());
                break;
            }
        }
        if (result.getHandValue() !=4) checkSet(arr, result);
    }
            
    void checkSet(ArrayList<Card> arr, Result result){
                
        for (int i=0; i<5; i++){
            if ((arr.get(i).getValue() - arr.get(i+1).getValue()== 0) & (arr.get(i+1).getValue() - arr.get(i+2).getValue() == 0)){
                    result.setHandValue(3);
                    result.setHand("Three of kind "+arr.get(i).getValue());
                    result.setHand1(arr.get(i).getValue());
                    break;
            }
        }
        if (result.getHandValue() !=3) checkTwoPairOne(arr, result);
    }
    void checkTwoPairOne(ArrayList<Card> arr, Result result){
        ArrayList<Card> twopair = new ArrayList();
        int one =0;
        for (int k=0; k<7; k++){
            twopair.add(arr.get(k));
        }
        for (int i=0; i<5; i++){
            if (twopair.get(i).getValue() - twopair.get(i+1).getValue()== 0){
                one = twopair.get(i).getValue();
                twopair.remove(i+1);
                twopair.remove(i);
                checkTwoPairTwo(arr, twopair, one, result);
                break;
            }
        }
        if (result.getHandValue() !=2) checkOnePair(arr, result);
    }
    
    void checkTwoPairTwo(ArrayList<Card> arr, ArrayList<Card> twopair, int one, Result result){
        int two;
        for (int j=0; j<4; j++){
            if (twopair.get(j).getValue()- twopair.get(j+1).getValue() == 0){
                two = twopair.get(j).getValue();
                result.setHandValue(2);
                result.setHand("Two pair: " + one + " over " + two);
                result.setHand1(one);
                result.setHand2(two);
                break;
                }
            } 
        result.setHandValue(0);
        if (result.getHandValue() !=2) checkOnePair(arr, result);
    }
   
    void checkOnePair(ArrayList<Card> arr, Result result) {
        
        for (int i=0; i<6; i++){
            if (arr.get(i).getValue()==arr.get(i+1).getValue()){
               result.setHandValue(1);
               result.setHand("One pair of "+arr.get(i).getValue()); 
               result.setHand1(arr.get(i).getValue());
               break;
            } 
        }
        if (result.getHandValue() !=1) checkHighCard(arr, result);
    }
    void checkHighCard(ArrayList<Card> arr, Result result){
                        
        result.setHandValue(0);
        result.setHand("Card High");
            
        
    }
    
    public void play() {
        
        setDeck();
        dealHand();
        checkResults();
       
    
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getP1c1() {
        return p1c1;
    }

    public String getP1c2() {
        return p1c2;
    }

    public String getP2c1() {
        return p2c1;
    }

    public String getP2c2() {
        return p2c2;
    }

    public String getF1c() {
        return f1c;
    }

    public String getF2c() {
        return f2c;
    }

    public String getF3c() {
        return f3c;
    }

    public String getTc() {
        return tc;
    }

    public String getRc() {
        return rc;
    }
    
    public int getWinner() {
        return winner;
    }

    public String getHand() {
        return hand;
    }
    
    
    
}      