package raamwerk;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/**
 *
 * @author Jasper De Vrient
 */
public class Output implements OutputWriter {

    private List<OutputWriter> writers = new LinkedList<>();

    public List<OutputWriter> getWriters() {
        return writers;
    }
    
    public void addOutputWriter(OutputWriter writer) {
        writers.add(writer);
    }
    
    @Override
    public void openOutput(Simulatie simulatie, List<UitvoerVelden> velden) {
        writers.stream().forEach((OutputWriter o) -> o.openOutput(simulatie,velden));
    }

    @Override
   public  void writeEntry(Map<UitvoerVelden, Object> entry) {
        writers.stream().forEach((OutputWriter o) -> o.writeEntry(entry));
    }

    @Override
 public    void closeOutput(Simulatie simulatie) {
      writers.stream().forEach((OutputWriter o) -> o.closeOutput(simulatie));
    }

    @Override
 public    void setOutput(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
