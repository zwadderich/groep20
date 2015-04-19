package simulaties;

import com.philihp.bj.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import raamwerk.ResultaatHand;
import raamwerk.Simulatie;
import raamwerk.Table;
import raamwerk.UitvoerVelden;

/**
 *
 * @author Jasper De Vrient
 */
public class TestSimulatie extends Simulatie {

    private int decks;
    private Response soft17;
    private float payout;
    private boolean doubleAfterSplit;
    private double cutcardpenetration;
    private int minbed;
    private int maxsplits;
    private List<Player> spelers;
    private List<Player> dealers;
    private Table table;

    @Override
    public List<UitvoerVelden> bereidVoor(Properties properties) {
        decks = Integer.parseInt(properties.getProperty("decks"));
        soft17 = Response.valueOf(properties.getProperty("soft17"));
        payout = Float.parseFloat(properties.getProperty("payout"));
        doubleAfterSplit = Boolean.parseBoolean(properties.getProperty("doubleaftersplit"));
        cutcardpenetration = Double.parseDouble(properties.getProperty("cutcardpenetration"));
        minbed = Integer.parseInt(properties.getProperty("minbet"));
        maxsplits = Integer.parseInt(properties.getProperty("maxsplits"));

        table = new Table();
        table.setCutcardpenetration(cutcardpenetration);
        table.setDecks(decks);
        table.setDoubleAfterSplit(doubleAfterSplit);
        table.setMaxsplits(maxsplits);
        table.setMinbed(minbed);
        table.setPayout(payout);
        table.setSoft17(soft17);

        spelers = new LinkedList<>();
        spelers.add(new HiLoPlayer());
        spelers.add(new REKOPlayer());
        spelers.add(new ZeroMemoryPlayer());
        dealers = new LinkedList<>();

        for (int i = 11; i <= 21; ++i) {
            spelers.add(new DealerPlayerChangedStop(i));
            dealers.add(new DealerPlayerChangedStop(i));
        }

        return Arrays.asList(UitvoerVelden.DEALERSTRATEGIE, UitvoerVelden.DEALERSTOPOP,
                UitvoerVelden.SPELERSTRATEGIE, UitvoerVelden.SPELERSTOPOP, UitvoerVelden.OPBRENGST,
                UitvoerVelden.WINNAAR
        );
    }

    @Override
    public void voerUit() {
        
        for (Player speler : spelers) {
            table.setPlayer(speler);
            
            for (Player dealer : dealers) {
                table.setDealer(dealer);
                
                for (ResultaatHand resultaat : table.speel()) {
                    set(UitvoerVelden.DEALERSTRATEGIE, resultaat.getDealer().toString());
                    if (resultaat.getDealer() instanceof DealerPlayerChangedStop) {
                        DealerPlayerChangedStop d =  (DealerPlayerChangedStop)resultaat.getDealer();
                        set(UitvoerVelden.DEALERSTOPOP, d.getStopAt());
                        
                    } else
                        set(UitvoerVelden.DEALERSTOPOP, 0);
                    
                    set(UitvoerVelden.SPELERSTRATEGIE, resultaat.getSpeler().toString());
                    if (resultaat.getSpeler() instanceof DealerPlayerChangedStop) {
                        DealerPlayerChangedStop d =  (DealerPlayerChangedStop)resultaat.getSpeler();
                        set(UitvoerVelden.SPELERSTOPOP, d.getStopAt());
                        
                    } else
                        set(UitvoerVelden.SPELERSTOPOP, 0);
                    set(UitvoerVelden.OPBRENGST, resultaat.getWinst());
                    set(UitvoerVelden.WINNAAR, resultaat.getWinnaar());
                    report();
                }
            }
        }

    }
}
