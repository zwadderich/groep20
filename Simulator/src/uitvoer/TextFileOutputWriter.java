package uitvoer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import raamwerk.OutputWriter;
import raamwerk.Simulatie;
import raamwerk.UitvoerVelden;

/**
 *
 * @author Jasper De Vrient
 */
public class TextFileOutputWriter implements OutputWriter {

    private OutputStream stream;
    private Formatter formatter;
    private List<UitvoerVelden> order;
    
    @Override
    public void setOutput(File file) {
        try {
            this.stream =new FileOutputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextFileOutputWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }

    @Override
    public String extention() {
        return ".txt"; //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public void openOutput(Simulatie simulatie, List<UitvoerVelden> velden) {
      formatter = new Formatter(stream);
      order = velden;
    }

    @Override
    public void writeEntry(Map<UitvoerVelden, Object> entry) {
     for (UitvoerVelden veld : order)
           writeColumn(veld,  entry.get(veld).toString());
       
       formatter.format("\n");
        try {
            formatter.flush();
        } catch (Exception ex) {
            Logger.getLogger(TextFileOutputWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void writeColumn(UitvoerVelden veld, String value) {
        switch (veld) {
            case DATUM:
                formatter.format("%-40s", value);
                break;
            case DEALERSTOPOP:
                  formatter.format("%-2s", value);
                break;
            case DEALERSTRATEGIE:
                  formatter.format("%-50s", value);
                break;
            case OPBRENGST:
                  formatter.format("%-8s", value);
                break;
            case SPELERSTOPOP:
                  formatter.format("%-2s", value);
                break;
            case SPELERSTRATEGIE:
                  formatter.format("%-50s", value);
                break;
            case WINNAAR:
                  formatter.format("%-1s", value);
                break;
        }
    }

    @Override
    public void closeOutput(Simulatie simulatie) {
        try {
            formatter.flush();
            formatter.close();
        } catch (Exception ex) {
          throw new IllegalArgumentException("",ex);
        }
    }



}
