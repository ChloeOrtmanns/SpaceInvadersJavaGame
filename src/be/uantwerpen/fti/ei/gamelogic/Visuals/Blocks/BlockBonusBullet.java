package be.uantwerpen.fti.ei.gamelogic.Visuals.Blocks;

import be.uantwerpen.fti.ei.gamelogic.Entities.BonusEntityBullets;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

import java.awt.*;

public class BlockBonusBullet extends BonusEntityBullets {
    BlockContext ctx;

    public BlockBonusBullet(BlockContext ctx,MovementComponent MC) {
        super(MC);
        this.ctx = ctx;
    }

    @Override
    public void Visual() {
        double[] visual = ctx.convert(movementComponent.getPosition(),movementComponent.getEntitySize());
        Graphics2D g2d = ctx.getG2dimage().createGraphics();
        g2d.setColor(Color.yellow);
        g2d.fillRect((int)visual[0], (int)visual[1], (int)visual[2], (int)visual[3]);
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Arial", Font.BOLD, (int)visual[2]));
        g2d.drawString("B",(int) (visual[0]+visual[2]/6),(int) (visual[1]+visual[3]*5/6));
    }
}
