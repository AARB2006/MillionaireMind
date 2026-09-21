package com.millionairemind.gui;

import com.millionairemind.gui.screens.InstructionsUI;
import com.millionairemind.gui.screens.PlayUI;
import com.millionairemind.gui.screens.TitleUI;
import com.millionairemind.model.GameSession;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Main JavaFX application and screen navigator.
 */
public final class GameUI extends Application {

    private static final String STYLESHEET =
            "/com/millionairemind/gui/game.css";

    /**
     * Shared game state.
     */
    private final GameSession session =
            new GameSession();

    /**
     * Root container.
     *
     * Only one screen is displayed at a time.
     */
    private final StackPane root =
            new StackPane();

    private Stage stage;

    private Scene scene;

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;

        scene = new Scene(
                root,
                1280,
                720
        );

        /*
         * Load CSS.
         */
        var css =
                getClass().getResource(STYLESHEET);

        if (css == null) {

            throw new IllegalStateException(
                    "Missing stylesheet: " +
                    STYLESHEET
            );
        }

        scene.getStylesheets()
             .add(css.toExternalForm());

        /*
         * Window configuration.
         */
        stage.setTitle(
                "Millionaire Mind"
        );

        stage.setScene(scene);

        stage.setMinWidth(640);
        stage.setMinHeight(480);

        stage.setOnCloseRequest(
                event -> Platform.exit()
        );

        /*
         * Fullscreen configuration.
         */
        stage.setFullScreenExitHint("");

        stage.setFullScreenExitKeyCombination(
                KeyCombination.NO_MATCH
        );

        /*
         * Keyboard controls.
         *
         * F11:
         *     Toggle fullscreen
         *
         * ESC:
         *     Exit fullscreen
         */
        scene.setOnKeyPressed(event -> {

            switch (event.getCode()) {

                case F11 ->
                        stage.setFullScreen(
                                !stage.isFullScreen()
                        );

                case ESCAPE -> {

                    if (stage.isFullScreen()) {

                        stage.setFullScreen(false);
                    }
                }

                default -> {
                    // Ignore unrelated keys.
                }
            }
        });

        /*
         * Start on title screen.
         */
        showTitle();

        stage.show();

        /*
         * Enter fullscreen after
         * the stage has been shown.
         */
        stage.setFullScreen(true);
    }

    /**
     * Show title screen.
     */
    public void showTitle() {

        root.getChildren().setAll(
                new TitleUI(this).build()
        );
    }

    /**
     * Show play screen.
     */
    public void showPlay() {

        root.getChildren().setAll(
                new PlayUI(
                        this,
                        session
                ).build()
        );
    }

    /**
     * Show instructions screen.
     */
    public void showInstructions() {

        root.getChildren().setAll(
                new InstructionsUI(this).build()
        );
    }

    /**
     * Completely close application.
     */
    public void quitApplication() {

        Platform.exit();
    }

    public Scene getScene() {

        return scene;
    }

    public Stage getStage() {

        return stage;
    }
}