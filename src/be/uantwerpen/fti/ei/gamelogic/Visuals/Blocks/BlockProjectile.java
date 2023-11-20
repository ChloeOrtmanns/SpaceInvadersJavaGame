package be.uantwerpen.fti.ei.gamelogic.Visuals.Blocks;

import be.uantwerpen.fti.ei.gamelogic.Entities.EnemyProjectileEntity;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

import java.awt.*;

public class BlockProjectile extends EnemyProjectileEntity {
    BlockContext ctx;

    public BlockProjectile(BlockContext ctx,MovementComponent MC) {
        super(MC);
        this.ctx = ctx;
    }

    @Override
    public void Visual() {
        double[] visual = ctx.convert(movementComponent.getPosition(),movementComponent.getEntitySize());
        Graphics2D g2d = ctx.getG2dimage().createGraphics();
        g2d.setColor(Color.blue);
        g2d.fillRect((int)visual[0], (int)visual[1], (int)visual[2], (int)visual[3]);
    }
}
