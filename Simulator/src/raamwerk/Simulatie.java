package raamwerk;

import java.util.*;

/**
 *
 * @author Jasper De Vrient
 */
public abstract class Simulatie {

    private Map<UitvoerVelden, Object> entry = new HashMap<>();
    
    private Output output;
    
    void setOutput(Output output) {
       this.output = output;
    }
    
   protected void report() {
        output.writeEntry(entry);
        entry.clear();
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
    
  
    /**
     * 
     * @param properties 
     */
    public abstract List<UitvoerVelden> bereidVoor(Properties properties);
    
    public abstract void voerUit();
    
    Map<UitvoerVelden, Object> geefUitvoer() {
        return entry;
    }
    
    protected void set(UitvoerVelden uitvoerVeld, Object waarde) {
        entry.put(uitvoerVeld, waarde);
    }

}
