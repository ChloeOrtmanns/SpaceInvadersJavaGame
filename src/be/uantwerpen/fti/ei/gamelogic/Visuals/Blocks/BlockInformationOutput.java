package be.uantwerpen.fti.ei.gamelogic.Visuals.Blocks;

import be.uantwerpen.fti.ei.gamelogic.Entities.ScreenEntities.InformationOutputEntity;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

import java.awt.*;

public class BlockInformationOutput extends InformationOutputEntity {
    private BlockContext ctx;

    public BlockInformationOutput(BlockContext ctx,MovementComponent MC) {
        super(MC);
        this.ctx = ctx;
        this.level = 1;
        this.health1 = 0;
        this.health2 = 0;
    }

    @Override
    public void Visual() {
        double[] visualCoord = ctx.convert(movementComponent.getPosition(),movementComponent.getEntitySize());

        Graphics2D g2d = ctx.getG2dimage().createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));

        String levelText = "Level: " + level;
        String scoreText = "Score: " + getScoretotal();
        int x = (int)visualCoord[0];
        int y = (int)visualCoord[1];

        g2d.drawString(levelText, x, y);
        g2d.drawString(scoreText,x,y+24);

        String healthText = "Health1: " + "<3 ".repeat(health1);
        g2d.drawString(healthText, x + 150, y+24);
        if (health2 != 0) {
            String healthText2 = "Health2: " + "<3 ".repeat(health2);
            g2d.drawString(healthText2, x + 150 + 250, y+24);
        }
    }
}
