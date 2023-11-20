package be.uantwerpen.fti.ei.gamelogic.Visuals.J2D;

import be.uantwerpen.fti.ei.gamelogic.Entities.EnemyEntity;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class J2DEnemyEntity extends EnemyEntity {
    J2DContext ctx;
    BufferedImage playerImage;

    public J2DEnemyEntity(J2DContext ctx, MovementComponent MC) throws IOException {
        super(MC);
        this.ctx = ctx;
        this.playerImage = ImageIO.read(new File("src/resources/411562079b1a858.png"));
        }

    public void Visual() {
        double[] visual = ctx.convert(movementComponent.getPosition(),movementComponent.getEntitySize());

        Graphics2D g2d = ctx.getG2dimage().createGraphics();
        g2d.drawImage(playerImage, (int) visual[0], (int) visual[1],
                (int) visual[2], (int) visual[3], null);
    }
}
