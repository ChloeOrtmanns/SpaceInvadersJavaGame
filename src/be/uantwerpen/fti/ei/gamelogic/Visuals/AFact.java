package be.uantwerpen.fti.ei.gamelogic.Visuals;

import be.uantwerpen.fti.ei.gamelogic.Entities.*;
import be.uantwerpen.fti.ei.gamelogic.Entities.ScreenEntities.GameOverScreenEntity;
import be.uantwerpen.fti.ei.gamelogic.Entities.ScreenEntities.InformationOutputEntity;
import be.uantwerpen.fti.ei.gamelogic.Entities.ScreenEntities.StartScreenEntity;
import be.uantwerpen.fti.ei.gamelogic.Inputs.AbstractInput;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

import javax.swing.*;
import java.io.IOException;

public abstract class AFact {
    public JFrame frame;

    /**
     * create the abstract player
     * @param inputs: inputs used by the player
     * @param MC: movement component used by the player
     * @return a player
     * @throws IOException if IO cannot be read
     */
    public abstract PlayerEntity createPlayer(AbstractInput inputs, MovementComponent MC) throws IOException;

    /**
     * create the abstract bullet
     * @param MC:  movement component used by the bullet
     * @return a bullet entity
     */
    public abstract PlayerBulletEntity createPlayerBullet(MovementComponent MC);

    /**
     * create the abstract enemy
     * @param MC:  movement component used by the enemy
     * @return an enemy
     * @throws IOException if IO cannot be read
     */
    public abstract EnemyEntity createEnemy(MovementComponent MC) throws IOException;

    /**
     * create the abstract projectile
     * @param MC:  movement component used by the projectile
     * @return a projectile entity
     */
    public abstract EnemyProjectileEntity createEnemyProjectile(MovementComponent MC);

    /**
     * create the abstract bonus entity
     * @param MC:  movement component used by the bonus entity
     * @return a bonus entity
     */
    public abstract BonusEntityBullets createBonusBullet(MovementComponent MC);

    /**
     * create the abstract game over screen
     * @param MC:  movement component used by the game over screen
     * @return a game over screen
     */
    public abstract GameOverScreenEntity createGameOverScreen(MovementComponent MC);

    /**
     * create the abstract information overlay
     * @param MC:  movement component used by the information overlay
     * @return an information overlay
     */
    public abstract InformationOutputEntity createScoreOutput(MovementComponent MC);

    /**
     * create the start screen
     * @param inputs: the inputs to activate the startscreen
     * @param MC: movement component used by the startcreen
     * @return a startscreen
     */
    public abstract StartScreenEntity createStartScreen(AbstractInput inputs, MovementComponent MC);

    /**
     * sets the gamedimensions
     * @param GameCellsX: widht of gamescreen
     * @param GameCellsY: height of gamescreen
     */
    public abstract void setGameDimensions(double GameCellsX, double GameCellsY);

    /**
     * renders the game
     */
    public abstract void render();
}
