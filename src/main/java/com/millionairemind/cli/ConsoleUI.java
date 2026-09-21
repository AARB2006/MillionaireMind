package com.millionairemind.cli;

import java.util.Scanner;

/**
 * Simple presentation helpers for the terminal version.
 */
public final class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);

    public void clearScreen() {
        System.out.println();
    }

    public void titleLogo() {
        System.out.println("MILLIONAIRE MIND");
        System.out.println();
    }

    public void heading(String text) {
        System.out.println(text.toUpperCase());
        System.out.println();
    }

    public void option(int number, String label) {
        System.out.printf("%d. %s%n", number, label);
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int readOption(
            int min,
            int max
    ) {

        while (true) {

            String input = readLine("Choose an option: ");
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Show the same validation message for invalid text and numbers.
            }

            System.out.printf("Please enter a number from %d to %d.%n", min, max);
        }
    }

    public boolean confirm(String question) {
        while (true) {
            String answer = readLine(question + " (Y/N): ").toLowerCase();
            if (answer.equals("y") || answer.equals("yes")) {
                return true;
            }
            if (answer.equals("n") || answer.equals("no")) {
                return false;
            }
            System.out.println("Please enter Y or N.");
        }
    }

    public void pause() {
        readLine("Press ENTER to continue...");
    }

    public void exit() {
        System.out.println("Goodbye.");
    }
}