/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

/**
 *
 * @author x17167141
 */
public class Card {
    private int value;
    public Suit suit;
    public enum Suit {
        SPADE,
        CLUB,
        HEART,
        DIAMOND
    }

    public Card() {
    }

    public Card(int value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }
    
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return value + "" + suit;
    }
    
    
}
