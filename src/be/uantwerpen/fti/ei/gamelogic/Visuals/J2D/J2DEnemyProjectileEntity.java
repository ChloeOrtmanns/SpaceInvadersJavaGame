package be.uantwerpen.fti.ei.gamelogic.Visuals.J2D;

import be.uantwerpen.fti.ei.gamelogic.Entities.EnemyProjectileEntity;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

import java.awt.*;
import java.awt.image.BufferedImage;

public class J2DEnemyProjectileEntity extends EnemyProjectileEntity {
    private J2DContext ctx;

    public J2DEnemyProjectileEntity(J2DContext ctx, MovementComponent MC) {
        super(MC);
        this.ctx = ctx;
    }

    @Override
    public void Visual() {
        Graphics2D g2d = ctx.getG2dimage().createGraphics();
        double[] visual = ctx.convert(movementComponent.getPosition(),movementComponent.getEntitySize());
        g2d.setColor(Color.GREEN);
        g2d.fillRect((int) visual[0], (int) visual[1], (int) visual[2], (int) visual[3]);
    }
}
