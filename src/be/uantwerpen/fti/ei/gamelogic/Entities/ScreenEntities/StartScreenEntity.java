package be.uantwerpen.fti.ei.gamelogic.Entities.ScreenEntities;

import be.uantwerpen.fti.ei.gamelogic.Entities.Entity;
import be.uantwerpen.fti.ei.gamelogic.Inputs.AbstractInput;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

public abstract class StartScreenEntity extends Entity {
    protected AbstractInput inputs;

    public StartScreenEntity(MovementComponent MC) {
        this.movementComponent = MC;
    }

    /**
     * Getter AbstractInput inputs
     * returns the AbstractInput inputs of StartScreenEntity
     * @return the inputs of the StartScreenEntity
     */
    public AbstractInput getInputs() {
        return inputs;
    }

    /**
     * Setter AbstractInput inputs
     * sets the AbstractInput inputs of StartScreenEntity
     * @param inputs: the inputs assigned to the StartScreenEntity
     */
    public void setInputs(AbstractInput inputs) {
        this.inputs = inputs;
    }
}
