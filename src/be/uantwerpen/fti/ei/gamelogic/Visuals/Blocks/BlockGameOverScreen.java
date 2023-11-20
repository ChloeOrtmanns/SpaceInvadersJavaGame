package be.uantwerpen.fti.ei.gamelogic.Visuals.Blocks;

import be.uantwerpen.fti.ei.gamelogic.Entities.ScreenEntities.GameOverScreenEntity;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.round;

public class BlockGameOverScreen extends GameOverScreenEntity {
    BlockContext ctx;

    public BlockGameOverScreen(BlockContext ctx,MovementComponent MC) {
        super(MC);
        this.ctx = ctx;
    }

    @Override
    public void Visual() {
        double[] visualCoord = ctx.convert(movementComponent.getPosition(),movementComponent.getEntitySize());
        Graphics2D g2d = ctx.getG2d();

        // Set the background color
        g2d.setColor(Color.BLACK);
        g2d.fillRect((int) visualCoord[0], (int) visualCoord[1], (int) visualCoord[2], (int) visualCoord[3]);

        // Set the font and color for the "Game Over" text
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 48));

        // Draw the "Game Over" text in the center of the screen
        String gameOverText = "Game Over";
        int stringWidth = g2d.getFontMetrics().stringWidth(gameOverText);
        int x = ((int) visualCoord[2] - stringWidth) / 2;
        int y = (int) visualCoord[3] / 2;
        g2d.drawString(gameOverText, x, y);

        g2d.dispose();

        BufferedImage image = new BufferedImage((int) visualCoord[2], (int) visualCoord[3], BufferedImage.TYPE_INT_ARGB);
        JFrame frame = ctx.getFrame();
        frame.setSize((int) round(ctx.getScreenWidth()), (int) round(ctx.getScreenHeight()));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.getContentPane().add(new JLabel(new ImageIcon(image)));
        frame.setVisible(true);
    }
}
