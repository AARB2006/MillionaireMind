package com.millionairemind.gui.screens;

import com.millionairemind.gui.GameUI;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Main title/menu screen.
 */
public final class TitleUI {

    private final GameUI gameUI;

    public TitleUI(GameUI gameUI) {

        this.gameUI = gameUI;
    }

    public Node build() {

        /*
         * MILLIONAIRE
         */
        Label millionaire =
                new Label("MILLIONAIRE");

        millionaire
                .getStyleClass()
                .add("title-small");

        /*
         * MIND
         */
        Label mind =
                new Label("MIND");

        mind
                .getStyleClass()
                .add("title-big");

        /*
         * Buttons
         */
        Button play =
                button("PLAY");

        play.setOnAction(
                event -> gameUI.showPlay()
        );

        Button instructions =
                button("INSTRUCTIONS");

        instructions.setOnAction(
                event -> gameUI.showInstructions()
        );

        Button quit =
                button("QUIT");

        quit.setOnAction(
                event -> gameUI.quitApplication()
        );

        /*
         * Main vertical layout.
         */
        VBox menu =
                new VBox(
                        10,
                        millionaire,
                        mind,
                        play,
                        instructions,
                        quit
                );

        menu.setAlignment(
                Pos.CENTER
        );

        menu.getStyleClass()
            .add("screen-root");

        /*
         * Make elements responsive.
         */
        bindResponsive(
                menu,
                play,
                instructions,
                quit,
                millionaire,
                mind
        );

        return menu;
    }

    /**
     * Creates a common menu button.
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
     * Responsive sizing.
     */
    private void bindResponsive(
            VBox menu,
            Button play,
            Button instructions,
            Button quit,
            Label millionaire,
            Label mind
    ) {

        var scene =
                gameUI.getScene();

        /*
         * Button width.
         */
        var buttonWidth =
                Bindings.createDoubleBinding(

                        () -> clamp(
                                scene.getWidth() * 0.18,
                                180,
                                380
                        ),

                        scene.widthProperty()
                );

        play.prefWidthProperty()
                .bind(buttonWidth);

        instructions.prefWidthProperty()
                .bind(buttonWidth);

        quit.prefWidthProperty()
                .bind(buttonWidth);

        /*
         * MILLIONAIRE font size.
         */
        var smallFont =
                Bindings.createDoubleBinding(

                        () -> clamp(
                                scene.getHeight() * 0.055,
                                22,
                                52
                        ),

                        scene.heightProperty()
                );

        /*
         * MIND font size.
         */
        var bigFont =
                Bindings.createDoubleBinding(

                        () -> clamp(
                                scene.getHeight() * 0.13,
                                56,
                                132
                        ),

                        scene.heightProperty()
                );

        millionaire
                .styleProperty()
                .bind(
                        Bindings.createStringBinding(

                                () ->
                                        "-fx-font-size: " +
                                        smallFont.get() +
                                        "px;",

                                smallFont
                        )
                );

        mind
                .styleProperty()
                .bind(
                        Bindings.createStringBinding(

                                () ->
                                        "-fx-font-size: " +
                                        bigFont.get() +
                                        "px;",

                                bigFont
                        )
                );

        /*
         * Responsive spacing.
         */
        menu.spacingProperty()
                .bind(
                        Bindings.createDoubleBinding(

                                () -> clamp(
                                        scene.getHeight()
                                                * 0.018,
                                        8,
                                        18
                                ),

                                scene.heightProperty()
                        )
                );
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