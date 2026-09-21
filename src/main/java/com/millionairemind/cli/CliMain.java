package com.millionairemind.cli;

import com.millionairemind.model.GameSession;

/**
 * Text-based version of the current screen flow.
 */
public final class CliMain {

    private final ConsoleUI ui = new ConsoleUI();

    private final GameSession session = new GameSession();

    private CliMain() {
    }

    public static void main(String[] args) {
        new CliMain().run();
    }

    public void run() {
        boolean running = true;
        while (running) {
            ui.clearScreen();
            ui.titleLogo();
            ui.option(1, "Play");
            ui.option(2, "Instructions");
            ui.option(3, "Quit");
            switch (ui.readOption(1, 3)) {
                case 1 -> showPlay();
                case 2 -> showInstructions();
                case 3 -> running = false;
                default -> throw new IllegalStateException("Unexpected option.");
            }
        }
        ui.exit();
    }

    private void showPlay() {
        boolean inPlay = true;
        while (inPlay) {
            ui.clearScreen();
            ui.heading("PLAY");
            ui.option(1, "Quit game");
            ui.option(2, "Back to menu");
            switch (ui.readOption(1, 2)) {
                case 1 -> {
                    if (confirmQuitGame()) {
                        showCongratulations();
                        inPlay = false;
                    }
                }
                case 2 -> inPlay = false;
                default -> throw new IllegalStateException("Unexpected option.");
            }
        }
    }

    private boolean confirmQuitGame() {
        ui.clearScreen();
        ui.heading("QUIT GAME");
        return ui.confirm("Are you sure you want to walk away?");
    }

    private void showCongratulations() {
        ui.clearScreen();
        ui.heading("CONGRATULATIONS");
        System.out.printf("You won $%.2f.%n", session.getWinnings().doubleValue());
        ui.pause();
        session.reset();
    }

    private void showInstructions() {
        boolean inInstructions = true;
        while (inInstructions) {
            ui.clearScreen();
            ui.heading("INSTRUCTIONS");
            System.out.println("Instructions will go here later.");
            System.out.println();
            ui.option(1, "Back to menu");
            if (ui.readOption(1, 1) == 1) {
                inInstructions = false;
            }
        }
    }
}