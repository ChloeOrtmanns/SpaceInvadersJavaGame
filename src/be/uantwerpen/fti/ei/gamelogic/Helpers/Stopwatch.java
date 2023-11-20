package be.uantwerpen.fti.ei.gamelogic.Helpers;

import java.util.Arrays;

public class Stopwatch {
    private long currentTime;
    private long previousTime;
    private double delta;

    /**
     * Standard Constructor Stopwatch
     * initializes it with default values
     */
    public Stopwatch() {
        this.currentTime = 0;
        this.previousTime = 0;
        this.delta = 0;
    }

    /**
     * sets the times
     */
    public void setTime() {
        this.currentTime = System.currentTimeMillis();
        this.previousTime = System.currentTimeMillis();
    }

    /**
     * updates the times
     */
    public void updateTime() {
        this.delta = (System.currentTimeMillis() - this.previousTime)/1000.0;
        this.previousTime = System.currentTimeMillis();
    }

    /**
     * getter double delta
     * @return the delta
     */
    public double getDeltaTime() {
        return this.delta;
    }

}
