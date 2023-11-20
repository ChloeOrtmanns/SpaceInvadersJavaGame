package be.uantwerpen.fti.ei.gamelogic.Inputs;

import be.uantwerpen.fti.ei.gamelogic.Helpers.Constants;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardInputs2 extends AbstractInput {

    private final int keyEventLeft = KeyEvent.VK_Q;
    private final int keyEventRight = KeyEvent.VK_D;
    private final int keyEventShoot = KeyEvent.VK_S;
    private final int keyEventPause = KeyEvent.VK_P;

    /**
     * constructor AbstractInput KeyboardInputs2
     * creates the keyboardinputs for player2
     * @param frame: jframe that is assigned to the keybindings
     */
    public KeyboardInputs2(JFrame frame) {
        Init(frame);
    }

    /**
     * initializes all the right keyboardinputs
     * @param frame: jframe that is assigned to the keybindings
     */
    public void Init(JFrame frame) {
        KeyListener LeftListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == keyEventLeft)
                    speedLeft = Double.parseDouble(Constants.getConstants().getProperty("PLAYER_SPEED"));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == keyEventLeft)
                    speedLeft = 0;
            }
        };

        KeyListener RightListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == keyEventRight)
                    speedRight = Double.parseDouble(Constants.getConstants().getProperty("PLAYER_SPEED"));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == keyEventRight)
                    speedRight = 0;
            }
        };

        KeyListener ShootListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == keyEventShoot)
                    isShoot = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == keyEventShoot)
                    isShoot = false;
            }
        };

        KeyListener PauseListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == keyEventPause)
                    isPaused = ! isPaused;
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };



        frame.addKeyListener(LeftListener);
        frame.addKeyListener(RightListener);
        frame.addKeyListener(ShootListener);
        frame.addKeyListener(PauseListener);
    }

}
