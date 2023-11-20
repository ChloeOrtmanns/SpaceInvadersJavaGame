package be.uantwerpen.fti.ei.gamelogic.Visuals.Blocks;

import be.uantwerpen.fti.ei.gamelogic.Entities.PlayerEntity;
import be.uantwerpen.fti.ei.gamelogic.Helpers.Constants;
import be.uantwerpen.fti.ei.gamelogic.Inputs.*;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

import java.awt.*;

public class BlockPlayer extends PlayerEntity {
    private BlockContext ctx;

    public BlockPlayer(BlockContext ctx, AbstractInput inputs, MovementComponent MC) {
        super(MC);
        this.ctx = ctx;
        this.inputs = inputs;
    }

    @Override
    public void Visual() {
        double[] visual = ctx.convert(movementComponent.getPosition(), movementComponent.getEntitySize());
        Graphics2D g2d = ctx.getG2dimage().createGraphics();

        if (inputs instanceof KeyboardInputs2) {
            g2d.setColor(new Color(178,102,255));
        } else {
            g2d.setColor(new Color(255,102,102));
        }

        g2d.fillRect((int) visual[0], (int) visual[1], (int) visual[2], (int) visual[3]);
    }
}
