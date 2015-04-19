package raamwerk;

import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author Jasper De Vrient
 */
public class SimulatieWrapper implements Comparable<SimulatieWrapper> {

    private Simulatie simulatie;
    private int aantalKeer;
    private Output output;
    private Properties properties = new Properties();

    void setOutput(Output output) {
        this.output = output;
    }

    public SimulatieWrapper(int aantalKeer, Simulatie simulatie) {
        this.simulatie = simulatie;
        this.aantalKeer = aantalKeer;
    }

    public SimulatieWrapper( int aantalKeer, Simulatie simulatie, Properties properties) {
        this.simulatie = simulatie;
        this.aantalKeer = aantalKeer;
        this.properties = properties;
    } 
    
    String fileName() {
        return simulatie.toString();
    }

    void voerUit() {
     List<UitvoerVelden> velden=    simulatie.bereidVoor(properties);
        output.openOutput(simulatie, velden);
        simulatie.setOutput(output);
        while (aantalKeer > 0) {
            simulatie.voerUit();
            aantalKeer--;
           
        }
        
        output.closeOutput(simulatie);
       
    }

    @Override
    public int compareTo(SimulatieWrapper o) {
        return aantalKeer - o.aantalKeer;
    }

    String getAantal() {
        return Integer.toString(aantalKeer);
    }

}
