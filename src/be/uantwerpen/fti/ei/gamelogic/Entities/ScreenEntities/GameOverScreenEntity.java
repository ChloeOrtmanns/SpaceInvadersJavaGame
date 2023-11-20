package be.uantwerpen.fti.ei.gamelogic.Entities.ScreenEntities;

import be.uantwerpen.fti.ei.gamelogic.Entities.Entity;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

public abstract class GameOverScreenEntity extends Entity {

    /**
     * Constructor Entity GameOverScreenEntity
     * assigns movementcomponent to the game over screen entity
     * @param MC : movementcomponent of entity
     */
    public GameOverScreenEntity(MovementComponent MC) {
        this.movementComponent = MC;
    }
}
