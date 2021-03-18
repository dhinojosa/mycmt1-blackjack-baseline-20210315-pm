package com.jitterted.ebp.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.ansi;

public class Hand {
    private List<Card> cardsList = new ArrayList<Card>();

    public Hand() {
    }

    public Hand(List<Card> cards) {
        this.cardsList = cards;
    }

    void drawFrom(Deck deck) {
        cardsList.add(deck.draw());
    }

    private int handValueOf() {
        int handValue = cardsList
            .stream()
            .mapToInt(Card::rankValue)
            .sum();

        // does the hand contain at least 1 Ace?
        boolean hasAce = cardsList
            .stream()
            .anyMatch(card -> card.rankValue() == 1);

        // if the total hand value <= 11, then count the Ace as 11 by adding 10
        if (hasAce && handValue < 11) {
            handValue += 10;
        }

        return handValue;
    }

    Card showFirstCard() {
        return cardsList.get(0);
    }

    //UI should be separate from business class
    void displayHand() {
        System.out.println(cardsList.stream()
                                    .map(Card::display)
                                    .collect(Collectors.joining(
                                        ansi().cursorUp(6).cursorRight(1).toString())));
    }

    boolean isBust() {
        return handValueOf() > 21;
    }

    boolean canContinue() {
        return handValueOf() <= 16;
    }

    void displayHandValue() {
        System.out.println(" (" + handValueOf() + ")");
    }

    boolean isPushedWith(Hand otherHand) {
        return handValueOf() == otherHand.handValueOf();
    }

    boolean beats(Hand otherHand) {
        return otherHand.handValueOf() < handValueOf();
    }

    public boolean valueIsEqualTo(int i) {
        return handValueOf() == i;
    }
}
