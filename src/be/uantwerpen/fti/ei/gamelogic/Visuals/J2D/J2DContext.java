package be.uantwerpen.fti.ei.gamelogic.Visuals.J2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.round;

public class J2DContext {
    private double ScreenWidth;
    private double ScreenHeight;
    private JFrame frame;
    private JPanel gamePanel;
    private BufferedImage g2dimage;     // used for drawing
    private Graphics2D g2d;             // always draw in this one
    private double sizeX;               // cel size
    private double sizeY;
    private BufferedImage backgroundImg;

    public void setSizeX(double sizeX) {
        this.sizeX = sizeX;
    }
    public void setSizeY(double sizeY) {
        this.sizeY = sizeY;
    }
    public double getScreenWidth() {
        return ScreenWidth;
    }
    public double getScreenHeight() {
        return ScreenHeight;
    }

    public BufferedImage getG2dimage() {
        return g2dimage;
    }
    public Graphics2D getG2d() {
        return g2d;
    }
    public JFrame getFrame() {
        return frame;
    }
    public JPanel getPanel() {
        return gamePanel;
    }

    public J2DContext() throws IOException {
        ScreenWidth = 720;
        ScreenHeight = 720;
        frame = new JFrame();
        gamePanel = new JPanel(true) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                doDrawing(g);
            }
        };

        frame.setFocusable(true);
        frame.add(gamePanel);
        frame.setTitle("SPACE INVADERS");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize((int)ScreenWidth, (int)ScreenHeight);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        this.g2dimage = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        this.g2d = g2dimage.createGraphics();
        //this.g2d.setBackground(new Color(0,0,50));
        //this.g2d.clearRect(0,0, frame.getWidth(), frame.getHeight());
        this.backgroundImg = ImageIO.read(new File("src/resources/pinkGalaxy.jpg"));

    }


    public double[] convert(double[] position, double[] size) {
        return new double[] {position[0] * this.sizeX, position[1] * this.sizeY, size[0] * this.sizeX, size[1] * this.sizeY};
    }

    public void render() {
        gamePanel.repaint();
    }

    private void doDrawing(Graphics g)
    {
        Graphics2D graph2d = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();
        graph2d.drawImage(g2dimage,0,0,null);
        graph2d.dispose();
        if (this.g2d != null) {
            this.g2d.drawImage(this.backgroundImg, 0, 0,(int) this.ScreenWidth, (int) this.getScreenHeight(), null);
        }
    }

}
