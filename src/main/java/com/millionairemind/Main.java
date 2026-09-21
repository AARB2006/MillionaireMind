package com.millionairemind;

import com.millionairemind.cli.CliMain;

/**
 * Convenience launcher.
 *
 * Usage:
 *
 * java com.millionairemind.Main gui
 * java com.millionairemind.Main cli
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) {

        String mode = args.length == 0
                ? "gui"
                : args[0].toLowerCase();

        switch (mode) {

            case "cli" -> CliMain.main(new String[0]);

            case "gui" -> com.millionairemind.gui.Main.main(new String[0]);

            default -> {

                System.out.println(
                        "Usage: java com.millionairemind.Main [gui|cli]"
                );

                System.out.println(
                        "Maven GUI: mvn javafx:run"
                );

                System.out.println(
                        "Maven CLI: mvn exec:java -Dexec.mainClass=com.millionairemind.cli.CliMain"
                );
            }
        }
    }
}