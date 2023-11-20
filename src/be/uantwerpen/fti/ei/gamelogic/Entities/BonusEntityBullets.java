package be.uantwerpen.fti.ei.gamelogic.Entities;

import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

public abstract class BonusEntityBullets extends Entity {
    /**
     * Constructor Entity BonusEntityBullets
     * assigns movementcomponent to the bonus entity
     * @param MC : movementcomponent of entity
     */
    public BonusEntityBullets(MovementComponent MC)  {
        this.movementComponent = MC;
    }
}
