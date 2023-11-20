package be.uantwerpen.fti.ei.gamelogic.Visuals.Blocks;

import be.uantwerpen.fti.ei.gamelogic.Entities.PlayerBulletEntity;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

import java.awt.*;

public class BlockBullet extends PlayerBulletEntity {
    BlockContext ctx;

    public BlockBullet(BlockContext ctx, MovementComponent MC) {
        super(MC);
        this.ctx = ctx;
    }

    @Override
    public void Visual() {
        double[] visual = ctx.convert(movementComponent.getPosition(),movementComponent.getEntitySize());
        Graphics2D g2d = ctx.getG2dimage().createGraphics();
        g2d.setColor(new Color(255,204,134));
        g2d.fillRect((int)visual[0], (int)visual[1], (int)visual[2], (int)visual[3]);
    }
}
