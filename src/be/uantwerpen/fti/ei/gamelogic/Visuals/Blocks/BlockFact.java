package be.uantwerpen.fti.ei.gamelogic.Visuals.Blocks;

import be.uantwerpen.fti.ei.gamelogic.Entities.*;
import be.uantwerpen.fti.ei.gamelogic.Entities.ScreenEntities.GameOverScreenEntity;
import be.uantwerpen.fti.ei.gamelogic.Entities.ScreenEntities.InformationOutputEntity;
import be.uantwerpen.fti.ei.gamelogic.Entities.ScreenEntities.StartScreenEntity;
import be.uantwerpen.fti.ei.gamelogic.Inputs.AbstractInput;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;
import be.uantwerpen.fti.ei.gamelogic.Visuals.AFact;

public class BlockFact extends AFact {
    BlockContext ctx;

    public BlockFact() {
        this.ctx = new BlockContext();
        super.frame = ctx.getFrame();
    }

    @Override
    public PlayerEntity createPlayer(AbstractInput inputs, MovementComponent MC) {
        return new BlockPlayer(this.ctx, inputs, MC);
    }

    @Override
    public PlayerBulletEntity createPlayerBullet(MovementComponent MC) {
        return new BlockBullet(this.ctx, MC);
    }

    @Override
    public EnemyEntity createEnemy(MovementComponent MC) {
        return new BlockEnemy(this.ctx, MC);
    }

    @Override
    public EnemyProjectileEntity createEnemyProjectile(MovementComponent MC) {
        return new BlockProjectile(this.ctx, MC);
    }

    @Override
    public BonusEntityBullets createBonusBullet(MovementComponent MC) {
        return new BlockBonusBullet(this.ctx, MC);
    }

    @Override
    public GameOverScreenEntity createGameOverScreen(MovementComponent MC) {
        return new BlockGameOverScreen(this.ctx, MC);
    }

    @Override
    public InformationOutputEntity createScoreOutput(MovementComponent MC) {
        return new BlockInformationOutput(this.ctx, MC);
    }

    @Override
    public StartScreenEntity createStartScreen(AbstractInput inputs, MovementComponent MC) {
        return new BlockStartScreen(this.ctx, inputs, MC);
    }

    @Override
    public void setGameDimensions(double GameCellsX, double GameCellsY) {
        ctx.setSizeX(ctx.getScreenWidth() / GameCellsX);
        ctx.setSizeY(ctx.getScreenHeight()/ GameCellsY);
    }

    @Override
    public void render() {
        ctx.render();
    }
}
