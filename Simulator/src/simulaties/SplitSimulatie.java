package simulaties;

import com.philihp.bj.Card;
import com.philihp.bj.DealerPlayer;
import com.philihp.bj.DealerPlayerChangedStop;
import com.philihp.bj.Deck;
import com.philihp.bj.Hand;
import com.philihp.bj.Player;
import com.philihp.bj.Response;
import com.philihp.bj.ZeroMemoryPlayer;
import java.util.ArrayList;
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
public class SplitSimulatie extends Simulatie {

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
        return Arrays.asList(UitvoerVelden.SPELERHAND, UitvoerVelden.SPLIT, UitvoerVelden.WINNAAR);
    }

    @Override
    public void voerUit() {
        ZeroMemoryPlayer player = new ZeroMemoryPlayer();
        table.setPlayer(player);
        table.setDealer(new DealerPlayer());
        
        for (Card c: Card.values()) {
            String spelerhand = c.toString() + "-" + c.toString();
            ResultaatHand res = table.speelSpelerSplit(c, c);
            set(UitvoerVelden.SPELERHAND, spelerhand);
            set(UitvoerVelden.SPLIT, 'J');
            set(UitvoerVelden.WINNAAR, res.getWinnaar());
            report();
            
            res = table.speelSpelerGeenSplit(c, c);
                  set(UitvoerVelden.SPELERHAND, spelerhand);
            set(UitvoerVelden.SPLIT, 'N');
            set(UitvoerVelden.WINNAAR, res.getWinnaar());
            report();
        }
      
    }

}
