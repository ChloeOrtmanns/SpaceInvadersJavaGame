package be.uantwerpen.fti.ei.gamelogic.Entities;

import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

public abstract class EnemyProjectileEntity extends Entity {
    /**
     * Constructor Entity EnemyProjectileEntity
     * assigns movementcomponent to the enemy bullet
     * @param MC : movementcomponent of entity
     */
    public EnemyProjectileEntity(MovementComponent MC) {
        this.movementComponent = MC;
    }
}
