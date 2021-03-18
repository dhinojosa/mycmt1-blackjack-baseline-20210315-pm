package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {

    //SUT = Subject Under Test
    @Test
    public void handWithOneAceTwoCardsIsValuedAt11() throws Exception {
        List<Card> cards = List.of(new Card("whocares", "A"),
            new Card("whocares", "5"));

        Hand playerHand = new Hand(cards);
        assertThat(playerHand.valueIsEqualTo(11 + 5)).isTrue();
    }

    @Test
    public void handWithOneAceAndOtherCardsEqualTo11IsValuedAt1() throws Exception {
        List<Card> cards = List.of(new Card("whocares", "A"),
            new Card("whocares", "8"),
            new Card("whocares", "3"));
        Hand playerHand = new Hand(cards);
        assertThat(playerHand.valueIsEqualTo(1 + 8 + 3)).isTrue();
    }

}
