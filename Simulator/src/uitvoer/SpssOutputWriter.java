package uitvoer;

import com.pmstation.spss.SPSSWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import raamwerk.OutputWriter;
import raamwerk.Simulatie;
import raamwerk.UitvoerVelden;

/**
 *
 * @author Jasper De Vrient
 */
public class SpssOutputWriter implements OutputWriter {

    private OutputStream out;
    private SPSSWriter writer;
    private List<UitvoerVelden> order;

    @Override
    public void setOutput(File file) {
        try {
            out = new FileOutputStream(file);
            writer = new SPSSWriter(out, "utf-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SpssOutputWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(SpssOutputWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SpssOutputWriter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void openOutput(Simulatie simulatie, List<UitvoerVelden> velden) {
        try {
            order = velden;
            writer.setCalculateNumberOfCases(false);
            writer.addDictionarySection(-1);

            for (UitvoerVelden veld : velden) {
                WriteColumnHeader(veld);
            }
            writer.addDataSection();

        } catch (Exception ex) {
        }

    }

    private void WriteColumnHeader(UitvoerVelden veld) throws IOException {
        switch (veld) {
            case DATUM:
                writer.addDateVar("Datum", 255, "Datumveld");
                break;
            case DEALERSTOPOP:
                writer.addNumericVar("DealerStoptOp", 8, 0, "Dealer stopt op");
                break;
            case DEALERSTRATEGIE:
                writer.addStringVar("DealerStrategie", 255, "Dealer strategie");
                break;
            case OPBRENGST:
                writer.addCommaVar("Opbrengst", 8, 2, "Opbrengst van de hand");
                break;
            case SPELERSTOPOP:
                writer.addNumericVar("SpelerStoptOp", 8, 0, "Speler stopt op");
                break;
            case SPELERSTRATEGIE:
                writer.addStringVar("SpelerStrategie", 255, "Speler strategie");
                break;
            case WINNAAR:
                writer.addStringVar("Winnaar", 1, "De winnaar");
                break;
            case SPELERHAND:
                writer.addStringVar("SpelerHand",255,"Hand dat de speler meekreeg.");
                break;
            case SPLIT:
                writer.addStringVar("Split", 1, "Speler gesplit");
                break;
        }
    }

    @Override
    public void writeEntry(Map<UitvoerVelden, Object> entry) {
        try {
            for (UitvoerVelden veld : order) {
                writeColumn(veld, entry.get(veld));
            }
        } catch (IOException ex) {
            Logger.getLogger(SpssOutputWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeColumn(UitvoerVelden veld, Object entry) throws IOException {
        switch (veld) {
            case DATUM:
                writer.addData((Date) entry);
                break;
            case DEALERSTOPOP:

                writer.addData(Long.parseLong(entry.toString()));
                break;
            case DEALERSTRATEGIE:
                writer.addData(entry.toString());
                break;
            case OPBRENGST:
                writer.addData(Double.parseDouble(entry.toString()));
                break;
            case SPELERSTOPOP:
                writer.addData(Long.parseLong(entry.toString()));
                break;
            case SPELERSTRATEGIE:
                writer.addData(entry.toString());
                break;
            case WINNAAR:
                writer.addData(entry.toString());
                break;
            case SPELERHAND:
                writer.addData(entry.toString());
                break;
            case SPLIT:
                writer.addData(entry.toString());
                break;
        }
    }

    @Override
    public void closeOutput(Simulatie simulatie) {
        try {
            writer.addFinishSection();
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(SpssOutputWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String extention() {
        return ".sav"; //To change body of generated methods, choose Tools | Templates.
    }

}
