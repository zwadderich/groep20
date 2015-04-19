package invoer;

import com.philihp.bj.Response;
import java.util.Properties;
import raamwerk.InputFile;

/**
 *
 * @author Jasper De Vrient
 */
public class StandaartInvoerParameters extends InputFile {

    public StandaartInvoerParameters(String filename) {
        super(filename);
    }

    @Override
    protected void createDefault(Properties properties) {
      properties.setProperty("decks", "6");
      properties.setProperty("soft17", Response.H.toString());
      properties.setProperty("payout", Float.toString(3F / 2F));
      properties.setProperty("doubleaftersplit", Boolean.toString(true));
      properties.setProperty("cutcardpenetration", Double.toString(0.666666666666666D));
      properties.setProperty("minbet", Integer.toString(10));
      properties.setProperty("maxsplits", Integer.toString(4));
      properties.setProperty("van", Integer.toString(2));
      properties.setProperty("tot", Integer.toString(21));
      
      
    }
    
    

}
