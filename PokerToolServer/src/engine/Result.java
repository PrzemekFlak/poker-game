/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

/**
 *
 * @author kanip
 */
public class Result {
    private int handValue;
    private String hand;
    private int hand1;
    private int hand2;
    
    public Result() {
    }

    public Result(int handValue) {
        this.handValue = handValue;
    }

    public Result(String hand) {
        this.hand = hand;
    }
    
    public Result(int handValue, String hand) {
        this.handValue = handValue;
        this.hand = hand;
    }
    
    public int getHandValue() {
        return handValue;
    }

    public void setHandValue(int handValue) {
        this.handValue = handValue;
    }

    public String getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public int getHand1() {
        return hand1;
    }

    public void setHand1(int hand1) {
        this.hand1 = hand1;
    }

    public int getHand2() {
        return hand2;
    }

    public void setHand2(int hand2) {
        this.hand2 = hand2;
    }
    
    
}
