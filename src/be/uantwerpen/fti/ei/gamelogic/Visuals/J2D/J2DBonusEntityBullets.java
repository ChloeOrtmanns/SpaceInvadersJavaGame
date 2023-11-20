package be.uantwerpen.fti.ei.gamelogic.Visuals.J2D;

import be.uantwerpen.fti.ei.gamelogic.Entities.BonusEntityBullets;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

import java.awt.*;

public class J2DBonusEntityBullets extends BonusEntityBullets {
    J2DContext ctx;

    public J2DBonusEntityBullets(J2DContext ctx, MovementComponent MC) {
        super(MC);
        this.ctx = ctx;
    }

    @Override
    public void Visual() {
        double[] visual = ctx.convert(movementComponent.getPosition(),movementComponent.getEntitySize());

        Graphics2D g2d = ctx.getG2dimage().createGraphics();
        g2d.setColor(new Color(102,0,204));
        g2d.fillRect((int)visual[0],(int)visual[1],(int)visual[2],(int)visual[3]);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.BOLD, (int)visual[2]));
        g2d.drawString("B",(int) (visual[0]+visual[2]/6),(int) (visual[1]+visual[3]*5/6));

    }
}
