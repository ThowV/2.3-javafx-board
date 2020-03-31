package com.thowv.javafxgridgameboard.premades;

import com.thowv.javafxgridgameboard.AbstractGameInstance;
import com.thowv.javafxgridgameboard.AbstractTurnEntity;
import com.thowv.javafxgridgameboard.events.GameBoardTilePressedEvent;

public abstract class AbstractTurnEntityPlayer extends AbstractTurnEntity {
    public AbstractTurnEntityPlayer() {
        super(EntityType.PLAYER);
    }

    @Override
    public abstract void takeTurn(AbstractGameInstance gameInstance);

    @Override
    public void onTilePressed(AbstractGameInstance gameInstance, GameBoardTilePressedEvent e) {
        gameInstance.doTurn(e.getXCord(), e.getYCord());
    }
}
