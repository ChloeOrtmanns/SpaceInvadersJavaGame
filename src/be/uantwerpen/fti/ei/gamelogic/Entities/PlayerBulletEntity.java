package be.uantwerpen.fti.ei.gamelogic.Entities;

import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

public abstract class PlayerBulletEntity extends Entity {
    /**
     * Constructor Entity PlayerBulletEntity
     * assigns movementcomponent to the player buller
     * @param MC : movementcomponent of entity
     */
    public PlayerBulletEntity(MovementComponent MC) {
        this.movementComponent = MC;
    }
}
