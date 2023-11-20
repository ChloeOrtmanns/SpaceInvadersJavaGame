package be.uantwerpen.fti.ei.gamelogic.Entities;

import be.uantwerpen.fti.ei.gamelogic.Entities.Entity;
import be.uantwerpen.fti.ei.gamelogic.Helpers.Constants;
import be.uantwerpen.fti.ei.gamelogic.Inputs.AbstractInput;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

public abstract class PlayerEntity extends Entity {
    protected AbstractInput inputs;
    protected double cooldown;
    protected int health;
    protected int score;

    /**
     * Constructor Entity PlayerEntity
     * assigns movementcomponent, cooldown, health and score to the player
     * @param MC : movementcomponent of entity
     */
    public PlayerEntity(MovementComponent MC) {
        this.movementComponent = MC;
        this.cooldown = Double.parseDouble(Constants.getConstants().getProperty("PLAYER_COOLDOWN"));
        this.health = Integer.parseInt(Constants.getConstants().getProperty("PLAYER_HEALTH"));
        this.score = 0;
    }

    /**
     * Getter AbstractInput inputs
     * returns the AbstractInput inputs of PlayerEntity
     * @return the inputs of the player
     */
    public AbstractInput getInputs() {
        return inputs;
    }

    /**
     * Setter AbstractInput inputs
     * sets the AbstractInput inputs of PlayerEntity
     * @param inputs: the inputs assigned to the player
     */
    public void setInputs(AbstractInput inputs) {
        this.inputs = inputs;
    }

    /**
     * Getter double cooldown
     * returns the double cooldown of PlayerEntity
     * @return the inputs of the player
     */
    public double getCooldown() {
        return cooldown;
    }

    /**
     * Setter double cooldown
     * Sets the double cooldown of PlayerEntity
     * @param cooldown: the score of the player
     */
    public void setCooldown(double cooldown) {
        this.cooldown = cooldown;
    }

    /**
     * Getter int health
     * returns the int health of PlayerEntity
     * @return the health of the player
     */
    public int getHealth() {
        return health;
    }

    /**
     * Setter int health
     * Sets the int health of PlayerEntity
     * @param health: the score of the player
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Getter int score
     * returns the int score of PlayerEntity
     * @return the score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter int score
     * Sets the int score of PlayerEntity
     * @param score: the score of the player
     */
    public void setScore(int score) {
        this.score = score;
    }

}



