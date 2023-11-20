package be.uantwerpen.fti.ei;

import be.uantwerpen.fti.ei.gamelogic.Visuals.AFact;
import be.uantwerpen.fti.ei.gamelogic.*;
import be.uantwerpen.fti.ei.gamelogic.Visuals.Blocks.BlockFact;
import be.uantwerpen.fti.ei.gamelogic.Visuals.J2D.*;
//import be.uantwerpen.fti.ei.gamelogic.Visuals.TXT.TXTFact;

public class Main {
    public static void main(String[] args) throws Exception {
        AFact f = new J2DFact();
        Game g = new Game(f);
        g.Start();
    } 
}