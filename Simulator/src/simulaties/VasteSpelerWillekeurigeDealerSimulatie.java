package simulaties;

import com.philihp.bj.DealerPlayer;
import com.philihp.bj.DealerPlayerChangedStop;
import com.philihp.bj.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import raamwerk.ResultaatHand;
import raamwerk.Simulatie;
import raamwerk.Table;
import raamwerk.UitvoerVelden;

/**
 *
 * @author Jasper De Vrient
 */
public class VasteSpelerWillekeurigeDealerSimulatie extends Simulatie {

    private Table table;
    private int decks;
    private Response soft17;
    private float payout;
    private boolean doubleAfterSplit;
    private double cutcardpenetration;
    private int minbed;
    private int maxsplits;
    private int van;
    private int tot;

    @Override
    public List<UitvoerVelden> bereidVoor(Properties properties) {
        decks = Integer.parseInt(properties.getProperty("decks"));
        soft17 = Response.valueOf(properties.getProperty("soft17"));
        payout = Float.parseFloat(properties.getProperty("payout"));
        doubleAfterSplit = Boolean.parseBoolean(properties.getProperty("doubleaftersplit"));
        cutcardpenetration = Double.parseDouble(properties.getProperty("cutcardpenetration"));
        minbed = Integer.parseInt(properties.getProperty("minbet"));
        maxsplits = Integer.parseInt(properties.getProperty("maxsplits"));
        van = Integer.parseInt(properties.getProperty("van"));
        tot = Integer.parseInt(properties.getProperty("tot"));

        table = new Table();
        table.setCutcardpenetration(cutcardpenetration);
        table.setDecks(decks);
        table.setDoubleAfterSplit(doubleAfterSplit);
        table.setMaxsplits(maxsplits);
        table.setMinbed(minbed);
        table.setPayout(payout);
        table.setSoft17(soft17);
        return Arrays.asList(UitvoerVelden.SPELERSTOPOP, UitvoerVelden.WINNAAR);
    }

    @Override
    public void voerUit() {
        table.setDealer(new DealerPlayer());
        table.setPlayer(new DealerPlayerChangedStop(21));

        for (int spelerHard = van; spelerHard <= tot; ++spelerHard) {
            ResultaatHand res = table.speelHand(spelerHard);

            set(UitvoerVelden.SPELERSTOPOP, spelerHard);
            set(UitvoerVelden.WINNAAR, res.getWinnaar());

            report();

        }

    }
}
