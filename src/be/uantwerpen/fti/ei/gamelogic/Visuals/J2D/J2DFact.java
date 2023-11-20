package be.uantwerpen.fti.ei.gamelogic.Visuals.J2D;

import be.uantwerpen.fti.ei.gamelogic.Entities.*;
import be.uantwerpen.fti.ei.gamelogic.Entities.ScreenEntities.GameOverScreenEntity;
import be.uantwerpen.fti.ei.gamelogic.Entities.ScreenEntities.InformationOutputEntity;
import be.uantwerpen.fti.ei.gamelogic.Entities.ScreenEntities.StartScreenEntity;
import be.uantwerpen.fti.ei.gamelogic.Inputs.AbstractInput;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;
import be.uantwerpen.fti.ei.gamelogic.Visuals.AFact;

import java.io.IOException;

public class J2DFact extends AFact {
    J2DContext ctx;

    public J2DFact() throws IOException {
        this.ctx = new J2DContext();
        super.frame = ctx.getFrame();
    }

    public PlayerEntity createPlayer(AbstractInput inputs, MovementComponent MC) throws IOException {
        return new J2DPlayerEntity(this.ctx, inputs, MC);
    }

    public PlayerBulletEntity createPlayerBullet(MovementComponent MC) {
        return new J2DPlayerBulletEntity(this.ctx, MC);
    }

    public EnemyEntity createEnemy(MovementComponent MC) throws IOException {
        return new J2DEnemyEntity(this.ctx, MC);
    }

    public EnemyProjectileEntity createEnemyProjectile(MovementComponent MC) {
        return new J2DEnemyProjectileEntity(this.ctx, MC);
    }

    public BonusEntityBullets createBonusBullet(MovementComponent MC) {
        return new J2DBonusEntityBullets(this.ctx, MC);
    }

    public GameOverScreenEntity createGameOverScreen(MovementComponent MC) {
        return new J2DGameOverScreenEntity(this.ctx, MC);
    }

    public InformationOutputEntity createScoreOutput(MovementComponent MC) {
        return new J2DInformationOutput(this.ctx, MC);
    }

    public StartScreenEntity createStartScreen(AbstractInput inputs, MovementComponent MC) {
        return new J2DStartScreenEntity(this.ctx, inputs, MC);
    }

    public void setGameDimensions(double GameCellsX, double GameCellsY) {
        ctx.setSizeX(ctx.getScreenWidth() / GameCellsX);
        ctx.setSizeY(ctx.getScreenHeight()/ GameCellsY);
    }

    public void render() {
        ctx.render();
    }
}
