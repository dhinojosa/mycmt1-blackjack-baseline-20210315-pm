package com.jitterted.ebp.blackjack;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WalletTest {

    //Zeroth, Simplest Case
    //1. New Wallet is Empty Done
    //2. Add Money And What is Total
    //3. New Wallet Add 15 Has Balance 15
    //4*  Add Money of Less Than Zero which throw an Exception
    @Test
    void testNewWalletIsEmpty() {
        Wallet wallet = new Wallet();
        assertThat(wallet.isEmpty()).isTrue();
    }

    @Test
    void testAdd15andBalance15() {
        Wallet wallet = new Wallet();
        wallet.addMoney(15);
        assertThat(wallet.getBalance())
            .isEqualTo(15);
    }

    @Test
    void testAdd15Add15andBalance30() {
        Wallet wallet = new Wallet();
        wallet.addMoney(15);
        wallet.addMoney(15);
        assertThat(wallet.getBalance())
            .isEqualTo(30);
    }

    @Test
    void testAdd30Add30andShouldNotBeEmpty() {
        Wallet wallet = new Wallet();
        wallet.addMoney(30);
        wallet.addMoney(30);
        assertThat(wallet.isEmpty())
            .isFalse();
    }

    @Test
    void testAddMoneyCannotAcceptNegative() {
        Wallet wallet = new Wallet();
        assertThatThrownBy(() -> wallet.addMoney(-2))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("amount must be positive");
    }
}
