package be.uantwerpen.fti.ei.gamelogic.Entities.ScreenEntities;

import be.uantwerpen.fti.ei.gamelogic.Entities.Entity;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

public abstract class InformationOutputEntity extends Entity {
    protected int score1 = 0;
    protected int score2 = 0;
    protected int level;
    protected int health1;
    protected int health2;

    /**
     * Constructor Entity InformationOutputEntity
     * assigns movementcomponent to the InformationOutput entity
     * @param MC : movementcomponent of entity
     */
    public InformationOutputEntity(MovementComponent MC) {
        this.movementComponent = MC;
    }

    /**
     * Setter int score1
     * Sets the int score1 of InformationOutput entity
     * @param score1: the score of the player1
     */
    public void setScore1(int score1) {
        this.score1 = score1;
    }

    /**
     * Setter int score2
     * Sets the int score2 of InformationOutput entity
     * @param score2: the score of the player2
     */
    public void setScore2(int score2) {
        this.score2 = score2;
    }

    /**
     * Getter int total scotr
     * returns the score of player 1 and player 2 added up
     * @return the score of player 1 and player 2 added up
     */
    public int getScoretotal() {
        return score1+score2;
    }

    /**
     * Setter int level
     * Sets the int level of InformationOutput entity
     * @param level: the current level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Setter int health1
     * Sets the int health1 of InformationOutput entity
     * @param health1: the health of the player1
     */
    public void setHealth1(int health1) {
        this.health1 = health1;
    }

    /**
     * Setter int health2
     * Sets the int health2 of InformationOutput entity
     * @param health2: the health of the player2
     */
    public void setHealth2(int health2) {
        this.health2 = health2;
    }
}
