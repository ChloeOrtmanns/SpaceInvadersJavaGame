package be.uantwerpen.fti.ei.gamelogic.Visuals.J2D;

import be.uantwerpen.fti.ei.gamelogic.Entities.PlayerEntity;
import be.uantwerpen.fti.ei.gamelogic.Inputs.*;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class J2DPlayerEntity extends PlayerEntity {
    private final J2DContext ctx;
    BufferedImage playerImage1;
    BufferedImage playerImage2;

    public J2DPlayerEntity(J2DContext ctx, AbstractInput inputs, MovementComponent MC) throws IOException {
        super(MC);
        this.ctx = ctx;
        this.inputs = inputs;
        this.playerImage1 = ImageIO.read(new File("src/resources/Cute-Sticker-PNG-File.png"));
        this.playerImage2 = ImageIO.read(new File("src/resources/Afbeelding1.png"));
    }

    public void Visual() {
        double[] visual = ctx.convert(movementComponent.getPosition(),movementComponent.getEntitySize());
        Graphics2D g2d = ctx.getG2dimage().createGraphics();
        if (inputs instanceof KeyboardInputs1) {
            g2d.drawImage(playerImage1, (int) visual[0], (int) visual[1], (int) visual[2], (int) visual[3], null);
        } else {
            g2d.drawImage(playerImage2, (int) visual[0], (int) visual[1], (int) visual[2], (int) visual[3], null);
        }
    }


}
