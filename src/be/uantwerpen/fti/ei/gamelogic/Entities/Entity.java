package be.uantwerpen.fti.ei.gamelogic.Entities;

import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

public abstract class Entity {
    protected MovementComponent movementComponent;

    /**
     * getter MovementComponent movementComponent
     * @return the entity's movementcomponent
     */
    public MovementComponent getMovementComponent() {
        return movementComponent;
    }

    /**
     * setter MovementComponent movementComponent
     * @param movementComponent: the movementcomponent of the entity
     */
    public void setMovementComponent(MovementComponent movementComponent) {
        this.movementComponent = movementComponent;
    }

    /**
     * abstract function that can visualize the entities extending from this entity
     */
    public abstract void Visual();

}

