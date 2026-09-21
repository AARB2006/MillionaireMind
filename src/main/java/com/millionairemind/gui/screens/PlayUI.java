package com.millionairemind.gui.screens;

import java.util.Optional;

import com.millionairemind.gui.GameUI;
import com.millionairemind.model.GameSession;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Placeholder gameplay screen.
 */
public final class PlayUI {

    private final GameUI gameUI;

    private final GameSession session;

    public PlayUI(
            GameUI gameUI,
            GameSession session
    ) {

        this.gameUI = gameUI;
        this.session = session;
    }

    public Node build() {

        /*
         * Screen title.
         */
        Label heading =
                new Label("PLAY");

        heading
                .getStyleClass()
                .add("screen-heading");

        /*
         * Quit game button.
         */
        Button quitGame =
                button("QUIT GAME");

        quitGame.setOnAction(
                event -> attemptQuitGame()
        );

        /*
         * Layout.
         */
        VBox content =
                new VBox(
                        18,
                        heading,
                        quitGame
                );

        content.setAlignment(
                Pos.CENTER
        );

        content.getStyleClass()
               .add("screen-root");

        /*
         * Responsive layout.
         */
        var scene =
                gameUI.getScene();

        quitGame
                .prefWidthProperty()
                .bind(
                        Bindings.createDoubleBinding(

                                () -> clamp(
                                        scene.getWidth()
                                                * 0.18,
                                        190,
                                        390
                                ),

                                scene.widthProperty()
                        )
                );

        heading
                .styleProperty()
                .bind(
                        Bindings.createStringBinding(

                                () ->
                                        "-fx-font-size: " +
                                        clamp(
                                                scene.getHeight()
                                                        * 0.055,
                                                24,
                                                58
                                        ) +
                                        "px;",

                                scene.heightProperty()
                        )
                );

        quitGame
                .styleProperty()
                .bind(
                        Bindings.createStringBinding(

                                () ->
                                        "-fx-font-size: " +
                                        clamp(
                                                scene.getHeight()
                                                        * 0.022,
                                                16,
                                                28
                                        ) +
                                        "px;",

                                scene.heightProperty()
                        )
                );

        return content;
    }

    /**
     * Creates a normal game button.
     */
    private Button button(String text) {

        Button button =
                new Button(text);

        button
                .getStyleClass()
                .add("menu-button");

        button.setFocusTraversable(false);

        return button;
    }

    /**
     * First quit confirmation.
     */
    private void attemptQuitGame() {

        Alert confirm =
                new Alert(
                        Alert.AlertType.CONFIRMATION,
                        "Are you sure you want to walk away?",
                        ButtonType.YES,
                        ButtonType.NO
                );

        confirm.setTitle(
                "Quit Game"
        );

        confirm.setHeaderText(
                "Quit Game"
        );

        confirm.initOwner(
                gameUI.getStage()
        );

        Optional<ButtonType> result =
                confirm.showAndWait();

        /*
         * Only continue if YES
         * was selected.
         */
        if (result.isPresent() &&
            result.get() == ButtonType.YES) {

            showCongratulations();
        }
    }

    /**
     * Second popup.
     */
    private void showCongratulations() {

        Alert congrats =
                new Alert(
                        Alert.AlertType.INFORMATION,
                        String.format(
                                "Congrats you won $%.2f.",
                                session
                                        .getWinnings()
                                        .doubleValue()
                        ),
                        ButtonType.OK
                );

        congrats.setTitle(
                "Congratulations"
        );

        congrats.setHeaderText(
                "Congratulations!"
        );

        congrats.initOwner(
                gameUI.getStage()
        );

        /*
         * Wait for the player to
         * acknowledge the popup.
         */
        congrats.showAndWait();

        /*
         * Reset game state.
         */
        session.reset();

        /*
         * Return to title screen.
         */
        gameUI.showTitle();
    }

    private static double clamp(
            double value,
            double min,
            double max
    ) {

        return Math.max(
                min,
                Math.min(max, value)
        );
    }
}