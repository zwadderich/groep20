/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack01;

import invoer.StandaartInvoerParameters;
import raamwerk.Output;
import raamwerk.SimulatieRunner;
import simulaties.SplitSimulatie;
import simulaties.TestSimulatie;
import simulaties.VasteSpelerWillekeurigeDealerSimulatie;
import uitvoer.CsvOutputWriter;
import uitvoer.SpssOutputWriter;
import uitvoer.TextFileOutputWriter;

/**
 *
 * @author Jasper De Vrient
 */
public class Blackjack01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SimulatieRunner sr = new SimulatieRunner("uitvoer");
        
        Output output = sr.getOutput();
        output.addOutputWriter(new SpssOutputWriter());
            
        sr.offer(new VasteSpelerWillekeurigeDealerSimulatie(), 10000, new StandaartInvoerParameters("simulatie1.txt"));
        sr.offer(new SplitSimulatie(), 10000, new StandaartInvoerParameters("simulatie2.txt"));
        sr.run();
    }
    
}
