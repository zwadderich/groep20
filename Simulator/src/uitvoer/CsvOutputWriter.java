package uitvoer;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
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
public class CsvOutputWriter implements OutputWriter {

    private CSVWriter writer;
    private List<UitvoerVelden> order;
    
    @Override
    public void setOutput(File file) {
        try {
            writer = new CSVWriter(new FileWriter(file),';');
        } catch (IOException ex) {
            Logger.getLogger(CsvOutputWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    

    @Override
    public void openOutput(Simulatie simulatie, List<UitvoerVelden> velden) {
     order = velden;
    }

    @Override
    public void writeEntry(Map<UitvoerVelden, Object> entry) {
       String[] s = new String[order.size()];
       int i = 0;
    for (UitvoerVelden veld : order) 
        s[i++] = entry.get(veld).toString();
    
        writer.writeNext(s);
      
        try {
          writer.flush();
        } catch (Exception ex) {
            Logger.getLogger(CsvOutputWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String extention() {
        return ".csv";
    }
  
    @Override
    public void closeOutput(Simulatie simulatie) {
        try {
           writer.flush();
           writer.close();
        } catch (Exception ex) {
          throw new IllegalArgumentException("",ex);
        }
    }



}
