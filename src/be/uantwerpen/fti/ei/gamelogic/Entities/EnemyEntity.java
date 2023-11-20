package be.uantwerpen.fti.ei.gamelogic.Entities;

import be.uantwerpen.fti.ei.gamelogic.Entities.Entity;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

public abstract class EnemyEntity extends Entity {
    private int currentState;
    // 1 = naar rechts
    // 2 = naar links

    /**
     * Constructor Entity EnemyEntity
     * assigns movementcomponent to the enemy
     * @param MC : movementcomponent of entity
     */
    public EnemyEntity(MovementComponent MC) {
        this.movementComponent = MC;
        this.currentState = 1;
    }

    /**
     * Getter int currentState
     * returns the int direction of EnemyEntity
     * @return the direction of the Entity
     */
    public int getCurrentState() { return currentState; }

    /**
     * Setter int currentState
     * Sets the int direction of EnemyEntity
     * @param newState: the score of the player
     */
    public void setCurrentState(int newState) { this.currentState = newState; }
}
