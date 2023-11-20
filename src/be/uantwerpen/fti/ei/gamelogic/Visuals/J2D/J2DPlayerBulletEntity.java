package be.uantwerpen.fti.ei.gamelogic.Visuals.J2D;

import be.uantwerpen.fti.ei.gamelogic.Entities.PlayerBulletEntity;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.round;

public class J2DPlayerBulletEntity extends PlayerBulletEntity {
    private J2DContext ctx;

    public J2DPlayerBulletEntity(J2DContext ctx, MovementComponent MC) {
        super(MC);
        this.ctx = ctx;
    }

    @Override
    public void Visual() {
        double[] visual = ctx.convert(movementComponent.getPosition(),movementComponent.getEntitySize());

        // gebruikt de g2d van het game panel => als ik de kleur verander in ctx veranderd die hier ook
        //     Graphics2D g2d = ctx.getG2d();
        // maakt een volledig nieuwe g2d aan => er wordt niets op mn pannel getoont
        //     BufferedImage g2dimage = new BufferedImage((int) visual[2], (int) visual[3],BufferedImage.TYPE_4BYTE_ABGR_PRE);
        //     Graphics2D g2d = g2dimage.createGraphics();

        // gebruikt de g2dImage van het game panel
        Graphics2D g2d = ctx.getG2dimage().createGraphics();
        g2d.setColor(new Color(255,203,112));
        g2d.fillRect((int) visual[0], (int) visual[1], (int) visual[2], (int) visual[3]);
    }
}
