package be.uantwerpen.fti.ei.gamelogic.Inputs;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import be.uantwerpen.fti.ei.gamelogic.Helpers.Constants;


public class KeyboardInputs1 extends AbstractInput {

    private final int keyEventLeft = KeyEvent.VK_LEFT;
    private final int keyEventRight = KeyEvent.VK_RIGHT;
    private final int keyEventShoot = KeyEvent.VK_SPACE;
    private final int keyEventPause = KeyEvent.VK_P;
    private final int keyEventOnePlayer = KeyEvent.VK_1;
    private final int keyEventTwoPlayer = KeyEvent.VK_2;

    /**
     * constructor AbstractInput KeyboardInputs1
     * creates the keyboardinputs for player1
     * @param frame: jframe that is assigned to the keybindings
     */
    public KeyboardInputs1(JFrame frame) {
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

        KeyListener AmountOfPlayers = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == keyEventOnePlayer) {
                    isOnePlayer = true;
                    isTwoPlayer = false;
                }
                if (e.getKeyCode() == keyEventTwoPlayer) {
                    isTwoPlayer = true;
                    isOnePlayer = false;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };


        frame.addKeyListener(LeftListener);
        frame.addKeyListener(RightListener);
        frame.addKeyListener(ShootListener);
        frame.addKeyListener(PauseListener);
        frame.addKeyListener(AmountOfPlayers);
    }

}
