package com.thowv.javafxgridgameboard.premades.reversi;

import com.thowv.javafxgridgameboard.*;
import com.thowv.javafxgridgameboard.events.GameBoardTilePressedEvent;

public class ReversiGameInstance extends AbstractGameInstance {
    private byte passAmount = 0;

    // region Constructors
    public ReversiGameInstance(GameBoard gameBoard, AbstractTurnEntity entityOne, AbstractTurnEntity entityTwo) {
        this(gameBoard, entityOne, entityTwo, 0);
    }

    public ReversiGameInstance(GameBoard gameBoard, AbstractTurnEntity entityOne, AbstractTurnEntity entityTwo,
                               int currentTurnEntity) {
        super(gameBoard, entityOne, entityTwo,
                ReversiGameInstance.class.getResource("/reversi-style.css").toExternalForm(),
                currentTurnEntity);
    }
    // endregion

    public void startGame() {
        int halfSize = (int)Math.floor((double)super.getGameBoard().getSize() / 2);

        // Set the starting tiles
        super.getGameBoard().setTileType(halfSize - 1, halfSize - 1, GameBoardTileType.PLAYER_1);
        super.getGameBoard().setTileType(halfSize, halfSize - 1, GameBoardTileType.PLAYER_2);
        super.getGameBoard().setTileType(halfSize, halfSize, GameBoardTileType.PLAYER_1);
        super.getGameBoard().setTileType(halfSize - 1, halfSize, GameBoardTileType.PLAYER_2);

        super.startGame(this);
    }

    @Override
    public void doTurn(int x, int y) {
        super.doTurn(x, y);

        // Flip all possible game board tile types
        ReversiAlgorithms.flipTilesFromOrigin(super.getGameBoard(),
                super.getCurrentTurnEntity().getGameBoardTileType(), x, y);

        if (super.getGameBoard().getTilesByType(GameBoardTileType.HIDDEN).size() != 0)
            super.switchTurn(this);
        else
            endGame();
    }

    public void passTurn() {
        passAmount += 1;

        if (passAmount == 2)
            endGame();
        else
            switchTurn();
    }

    public void endGame() {
        int entityOneTileAmount = super.getGameBoard().getTilesByType(GameBoardTileType.PLAYER_1).size();
        int entityTwoTileAmount = super.getGameBoard().getTilesByType(GameBoardTileType.PLAYER_2).size();

        // Standard is a tie, unless calculated otherwise
        GameBoardTileType[] winningTileType = new GameBoardTileType[] {
                GameBoardTileType.PLAYER_1, GameBoardTileType.PLAYER_2
        };

        // Determine the winner
        if (entityOneTileAmount > entityTwoTileAmount)
            winningTileType = new GameBoardTileType[] { super.getEntityOne().getGameBoardTileType() };
        else if (entityTwoTileAmount > entityOneTileAmount)
            winningTileType = new GameBoardTileType[] { super.getEntityTwo().getGameBoardTileType() };

        super.endGame(winningTileType);
    }

    @Override
    protected void onTilePressed(GameBoardTilePressedEvent e) {
        if (super.getCurrentTurnEntity().getEntityType() == AbstractTurnEntity.EntityType.PLAYER)
            super.getCurrentTurnEntity().onTilePressed(this, e);
    }
}
