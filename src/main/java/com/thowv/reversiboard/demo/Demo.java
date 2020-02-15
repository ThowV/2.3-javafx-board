package com.thowv.reversiboard.demo;

import com.thowv.reversiboard.BoardTile;
import com.thowv.reversiboard.events.BoardTileActivatedEvent;
import com.thowv.reversiboard.ReversiBoard;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Demo extends Application {

    @Override
    public void start(Stage primaryStage) {
        ReversiBoard reversiBoard = new ReversiBoard();

        primaryStage.setScene(new Scene(reversiBoard));
        primaryStage.setTitle("JavaFX Board Project");

        primaryStage.show();

        // Showcasing behaviour
        reversiBoard.start();

        PauseTransition pauseTransition = new PauseTransition(Duration.millis(100));
        pauseTransition.setOnFinished(e -> {
            reversiBoard.addEventHandler(BoardTileActivatedEvent.TILE_ACTIVATED,
                    e1 -> System.out.println("Tile x: " + e1.getXCord() + "\tTile y: " + e1.getYCord()));

            System.out.println(reversiBoard.getSize());
            System.out.println(reversiBoard.getTile(0, 0));

            reversiBoard.setTilePieceType(0, 0, BoardTile.TilePieceType.WHITE);
            reversiBoard.setTilePieceType(1, 1, BoardTile.TilePieceType.BLACK);
            reversiBoard.setTilePieceType(2, 2);
            reversiBoard.setTilePieceType(3, 3);
        });
        pauseTransition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
