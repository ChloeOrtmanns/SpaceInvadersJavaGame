package be.uantwerpen.fti.ei.gamelogic.Visuals.Blocks;

import be.uantwerpen.fti.ei.gamelogic.Entities.ScreenEntities.StartScreenEntity;
import be.uantwerpen.fti.ei.gamelogic.Inputs.AbstractInput;
import be.uantwerpen.fti.ei.gamelogic.Movement.MovementComponent;
import be.uantwerpen.fti.ei.gamelogic.Visuals.J2D.J2DContext;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BlockStartScreen extends StartScreenEntity {
    BlockContext ctx;

    public BlockStartScreen(BlockContext ctx, AbstractInput inputs, MovementComponent MC) {
        super(MC);
        this.ctx = ctx;
        this.inputs = inputs;
    }

    @Override
    public void Visual() {
        double[] visualCoord = ctx.convert(movementComponent.getPosition(),movementComponent.getEntitySize());

        Graphics2D g2d = ctx.getG2dimage().createGraphics();

        BufferedImage image = new BufferedImage((int) visualCoord[2], (int) visualCoord[3], BufferedImage.TYPE_INT_ARGB);
        //Graphics2D g2d = image.createGraphics();


        g2d.clearRect((int) visualCoord[0], (int) visualCoord[1], (int) visualCoord[2], (int) visualCoord[3]);

        // Set the background color
        g2d.setColor(new Color(247,128,144));
        g2d.fillRect((int) visualCoord[0], (int) visualCoord[1], (int) visualCoord[2], (int) visualCoord[3]);

        // Set the font and color for the "Game Over" text
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 48));

        // Draw the "Game Over" text in the center of the screen
        String text1 = "One Player = press 1";
        String text2 = "Two players = press 2";
        int stringWidth1 = g2d.getFontMetrics().stringWidth(text1);
        int x = ((int) visualCoord[2] - stringWidth1) / 2;
        int y = (int) visualCoord[3] / 2 - 48;
        g2d.drawString(text1, x, y);
        int stringWidth2 = g2d.getFontMetrics().stringWidth(text2);
        int x2 = ((int) visualCoord[2] - stringWidth2) / 2;
        int y2 = (int) visualCoord[3] / 2 + 48;
        g2d.drawString(text2,x2,y2);

        g2d.dispose();

        JFrame frame = ctx.getFrame();

        frame.getContentPane().add(new JLabel(new ImageIcon(image)));
        frame.setVisible(true);
    }
}
