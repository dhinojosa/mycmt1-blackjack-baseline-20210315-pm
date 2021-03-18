package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameBettingTest {
    @Test
    void testAtTheStartGameBalanceIsZero() {
        Game game = new Game();
        int balance = game.playerBalance();
        assertThat(balance).isZero();
    }

    @Test
    void testPlayerWith100BalanceBets75ThenBalanceIs25() {
        Game game = new Game();
        game.playerDeposits(100);
        game.playerBets(75);
        assertThat(game.playerBalance()).isEqualTo(25);
    }

    @Test
    void testPlayerWith100Bets50AndWinsThenBalanceIs150() {
        Game game = new Game();
        game.playerDeposits(100);
        game.playerBets(50);
        game.playerWins();
        assertThat(game.playerBalance()).isEqualTo(100 - 50 + (50 * 2));
    }

    @Test
    void testPlayerWith2000Bets100AndWinsThenBalanceIs150() {
        Game game = new Game();
        game.playerDeposits(2000);
        game.playerBets(100);
        game.playerWins();
        assertThat(game.playerBalance()).isEqualTo(2000 - 100 + (100 * 2));
    }

    @Test
    void testPlayerWith100WinsButForgotToMakeABet() {
        Game game = new Game();
        game.playerDeposits(100);
        game.playerWins();
        assertThat(game.playerBalance()).isEqualTo(100);
    }

    @Test
    void testPlayerWith100WinsBets50AndWins50() {
        Game game = new Game();
        game.playerDeposits(100);
        game.playerBets(50);
        game.playerWins();
        game.playerWins();
        assertThat(game.playerBalance()).isEqualTo(100 - 50 + (50 * 2));
    }

    @Test
    void playWith100Bets50AndWinsThenBalanceIs150() {
        Game game = new Game();
        game.playerDeposits(100);
        game.playerBets(50);

        game.playerWins();
        assertThat(game.playerBalance()).isEqualTo(100 - 50 + 100);
    }

    @Test
    void testPlayWith50Bets25AndLosesThenBalanceIs25() {
        Game game = new Game();
        game.playerDeposits(50);
        game.playerBets(25);
        game.playerLoses();
        assertThat(game.playerBalance()).isEqualTo(25);
    }

    @Test
    void testPlayerWith100WinsBets50AndLosesTwice() {
        Game game = new Game();
        game.playerDeposits(100);
        game.playerBets(50);
        game.playerLoses();
        game.playerWins();
        assertThat(game.playerBalance()).isEqualTo(100 - 50);
    }

    @Test
    void testPlayerWith75Bets50AndPushesThenBalanceIs75() {
        Game game = new Game();
        game.playerDeposits(75);
        game.playerBets(50);
        game.playerPushes();
        assertThat(game.playerBalance()).isEqualTo(75);
    }


    @Test
    void testPlayerWith75Bets50AndPushesThenWinsThenBalanceIs75() {
        Game game = new Game();
        game.playerDeposits(75);
        game.playerBets(50);
        game.playerPushes();
        game.playerWins();
        assertThat(game.playerBalance()).isEqualTo(75);
    }

    @Test
    void testPlayerWith200Bets100AndWinsBlackjackThenBalance350() {
        Game game = new Game();
        game.playerDeposits(200);
        game.playerBets(100);
        game.playerWinsBlackjack();
        assertThat(game.playerBalance()).isEqualTo(200 - 100 + ((int)(100 * 2.5)));
    }

    @Test
    void testPlayerWith200Bets100AndWinsBlackjackThenWinsThenBalance350() {
        Game game = new Game();
        game.playerDeposits(200);
        game.playerBets(100);
        game.playerWinsBlackjack();
        game.playerWins();
        assertThat(game.playerBalance()).isEqualTo(200 - 100 + ((int)(100 * 2.5)));
    }


}
