package be.uantwerpen.fti.ei.gamelogic.Visuals.Blocks;

import be.uantwerpen.fti.ei.gamelogic.Entities.EnemyEntity;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

import java.awt.*;

public class BlockEnemy extends EnemyEntity {
    BlockContext ctx;

    public BlockEnemy(BlockContext ctx, MovementComponent MC) {
        super(MC);
        this.ctx = ctx;
    }

    @Override
    public void Visual() {
        double[] visual = ctx.convert(movementComponent.getPosition(),movementComponent.getEntitySize());
        Graphics2D g2d = ctx.getG2dimage().createGraphics();
        g2d.setColor(new Color(102,255,255));
        g2d.fillRect((int)visual[0], (int)visual[1], (int)visual[2], (int)visual[3]);
    }
}
