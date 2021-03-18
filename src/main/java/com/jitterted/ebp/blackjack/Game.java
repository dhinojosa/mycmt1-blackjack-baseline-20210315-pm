package com.jitterted.ebp.blackjack;

import org.fusesource.jansi.Ansi;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Game {

    private final Deck deck;

    final Hand playerHand = new Hand();
    private final Hand dealerHand = new Hand();
    private int playerBalance;
    private int playerBetAmount;

    public static void main(String[] args) {
        Game game = new Game();

        System.out.println(ansi()
            .bgBright(Ansi.Color.WHITE)
            .eraseScreen()
            .cursor(1, 1)
            .fgGreen().a("Welcome to")
            .fgRed().a(" Jitterted's")
            .fgBlack().a(" BlackJack"));


        game.initialDeal();
        game.play();

        System.out.println(ansi().reset());
    }

    public Game() {
        deck = new Deck();
    }

    public void initialDeal() {
        dealRound();
        dealRound();
    }

    private void dealRound() {
        // deal next round of cards
        playerHand.drawFrom(deck);
        dealerHand.drawFrom(deck);
    }

    public void play() {
        // get Player's decision: hit until they stand, then they're done (or
        // they go bust)
        boolean playerBusted = false;
        while (!playerBusted) {
            displayGameState();
            String playerChoice = inputFromPlayer().toLowerCase();
            if (playerChoice.startsWith("s")) {
                break;
            }
            if (playerChoice.startsWith("h")) {
                playerHand.drawFrom(deck);
                if (playerHand.isBust()) {
                    playerBusted = true;
                }
            } else {
                System.out.println("You need to [H]it or [S]tand");
            }
        }

        // Dealer makes its choice automatically based on a simple heuristic
        // (<=16, hit, 17>=stand)
        if (!playerBusted) {
            while (dealerHand.canContinue()) {
                dealerHand.drawFrom(deck);
            }
        }

        displayFinalGameState();

        displayOutcome(playerBusted);
    }

    private void displayOutcome(boolean playerBusted) {
        if (playerBusted) {
            System.out.println("You Busted, so you lose.  💸");
            playerLoses();
        } else if (dealerHand.isBust()) {
            System.out.println("Dealer went BUST, Player wins! Yay for you!! " +
                "💵");
            playerWins();
        } else if (playerHand.beats(dealerHand)) {
            System.out.println("You beat the Dealer! 💵");
            playerWins();
        } else if (dealerHand.isPushedWith(playerHand)) {
            System.out.println("Push: The house wins, you Lose. 💸");
            playerPushes();
        } else {
            System.out.println("You lost to the Dealer. 💸");
            playerLoses();
        }
    }

    private String inputFromPlayer() {
        System.out.println("[H]it or [S]tand?");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void displayGameState() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        System.out.println(dealerHand.showFirstCard().display()); //
        // first card is Face Up

        // second card is the hole card, which is hidden
        displayBackOfCard();

        System.out.println();
        System.out.println("Player has: ");
        playerHand.displayHand();
        playerHand.displayHandValue();
    }

    private void displayBackOfCard() {
        System.out.print(
            ansi()
                .cursorUp(7)
                .cursorRight(12)
                .a("┌─────────┐").cursorDown(1).cursorLeft(11)
                .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                .a("│░ J I T ░│").cursorDown(1).cursorLeft(11)
                .a("│░ T E R ░│").cursorDown(1).cursorLeft(11)
                .a("│░ T E D ░│").cursorDown(1).cursorLeft(11)
                .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                .a("└─────────┘"));
    }

    private void displayFinalGameState() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        dealerHand.displayHand();
        playerHand.displayHandValue();

        System.out.println();
        System.out.println("Player has: ");
        playerHand.displayHand();
        playerHand.displayHandValue();
    }

    public int playerBalance() {
        return playerBalance;
    }

    public void playerDeposits(int i) {
        this.playerBalance += i;
    }

    public void playerBets(int i) {
        this.playerBalance -= i;
        this.playerBetAmount = i;

    }

    public void playerWins() {
        this.playerBalance = playerBalance + (playerBetAmount * 2);
        resetBetAmount();
    }

    private void resetBetAmount() {
        this.playerBetAmount = 0;
    }

    public void playerLoses() {
        resetBetAmount();
    }

    public void playerPushes() {
        this.playerBalance = playerBalance + playerBetAmount;
        resetBetAmount();
    }

    public void playerWinsBlackjack() {
        this.playerBalance = playerBalance + ((int) (playerBetAmount * 2.5));
        resetBetAmount();
    }
}
