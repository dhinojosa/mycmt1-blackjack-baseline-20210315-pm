package com.jitterted.ebp.blackjack;

public class Wallet {
    private int balance;

    public boolean isEmpty() {
        return balance == 0;
    }

    public void addMoney(int amount) {
        assertThatAmountIsPositive(amount);
        balance += amount;
    }

    private void assertThatAmountIsPositive(int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("amount must be positive");
    }

    public int getBalance() {
        return balance;
    }
}

//CMD+SHIFT+T
