package raamwerk;

import com.philihp.bj.*;
import java.util.*;

/**
 *
 * @author Jasper De Vrient
 */
public class Table {

    private Player player;
    private float money;
    private Deck deck;
    private Player dealer;
    private Random r = new Random();

    private int decks;
    private Response soft17;
    private float payout;
    private boolean doubleAfterSplit;
    private double cutcardpenetration;
    private int minbed;
    private int maxsplits;

    public void setDecks(int decks) {
        this.decks = decks;
    }

    public void setSoft17(Response soft17) {
        this.soft17 = soft17;
    }

    public void setPayout(float payout) {
        this.payout = payout;
    }

    public void setDoubleAfterSplit(boolean doubleAfterSplit) {
        this.doubleAfterSplit = doubleAfterSplit;
    }

    public void setCutcardpenetration(double cutcardpenetration) {
        this.cutcardpenetration = cutcardpenetration;
    }

    public void setMinbed(int minbed) {
        this.minbed = minbed;
    }

    public void setMaxsplits(int maxsplits) {
        this.maxsplits = maxsplits;
    }

    public Collection<ResultaatHand> speel() {
        List<ResultaatHand> resultaten = new LinkedList<>();
        deck = new Deck(decks, player);
        deck.shuffle(r);
        player.resetCount(decks);
        while ((float) deck.size() / deck.getInitialSize() > cutcardpenetration) {
            float prevMoney = money;
            List<Hand> playerHands = new ArrayList<Hand>(1);
            playerHands.add(new Hand(player.bet(), deck.draw(), deck.draw(), false));
            Hand dealerHand = new Hand(deck.draw(), deck.draw());
            money -= playerHands.get(0).getBet();

            if (dealerHand.getValue() == 21)
                //dealer has blackjack. do not play out hands, just leave money on
                //table and start over.
                continue;

            money += playoutPlayer(player, deck, playerHands, dealerHand);

            playoutDealer(deck, dealer, dealerHand);

            for (Hand playerHand : playerHands)
                money += payout(playerHand, dealerHand);

            float winst = money - prevMoney;
            ResultaatHand res = new ResultaatHand(player, dealer, winst <= 0 ? 'D' : 'S', winst, money);
            resultaten.add(res);

        }

        return resultaten;

    }

    private float playoutPlayer(Player player, Deck deck,
            List<Hand> playerHands, Hand dealerHand) {
        int i = 0;
        float money = 0;
        do {
            Hand playerHand = playerHands.get(i);

            for (;;) {
                Response response = player.prompt(playerHand, dealerHand, playerHands.size() < maxsplits);
                if (response == Response.RH)
                    if (playerHand.size() == 2 && playerHand.isSplit() == false) {
                        playerHand.surrender();
                        break;
                    } else
                        response = Response.H;
                if (response == Response.DH)
                    if (playerHand.canDoubleDown()) {
                        int bet = playerHand.getBet();
                        money -= bet;
                        playerHand.addBet(bet);
                        playerHand.add(deck.draw());
                        break;
                    } else
                        response = Response.H;
                if (response == Response.H) {
                    playerHand.add(deck.draw());
                    if (playerHand.getValue() > 21) // bust!

                        break;
                    else
                        continue;
                }
                if (response == Response.DS)
                    if (playerHand.canDoubleDown()) {
                        int bet = playerHand.getBet();
                        money -= bet;
                        playerHand.addBet(bet);
                        response = Response.S;
                    } else
                        response = Response.S;
                if (response == Response.S)
                    break;
                if (response == Response.P) {
                    money -= playerHand.getBet();
                    Hand left = new Hand(playerHand.getBet(), playerHand.get(0), deck.draw(), true);
                    Hand right = new Hand(playerHand.getBet(), playerHand.get(1), deck.draw(), true);
                    playerHands.set(i, left);
                    playerHands.add(right);
                    playerHand = left;
                    continue;
                }
            }
        } while (++i < playerHands.size());
        return money;
    }

    private void playoutDealer(Deck deck, Player dealer, Hand dealerHand) {
        do {
            Response response = dealer.prompt(null, dealerHand, false);
            if (response == Response.H)
                dealerHand.add(deck.draw());
            else if (response == Response.S)
                break;
            else
                throw new RuntimeException("Dealer should never do anything but Hit or Stay. " + dealerHand);
        } while (dealerHand.getValue() <= 21); // bust
    }

    private int payout(Hand playerHand,
            Hand dealerHand) {
        if (playerHand.isSurrendered())
            return playerHand.getBet() / 2;
        else if (playerHand.isBlackjack() && dealerHand.isBlackjack())
            //System.out.println("--- \tPush, both blackjacks");
            return playerHand.getBet();
        else if (playerHand.isBlackjack() && dealerHand.isBlackjack() == false)
            //System.out.println("Win \tPlayer BJ "+playerHand);
            return (int) (playerHand.getBet() * (1 + payout));
        else if (playerHand.isBlackjack() == false && dealerHand.isBlackjack())
            //System.out.println("Lose \tDealer BJ "+dealerHand);
            return 0;
        else if (playerHand.getValue() > 21)
            //System.out.println("Lose \tPlayer Bust "+playerHand.getValue());
            return 0;
        else if (dealerHand.getValue() > 21)
            //System.out.println("Win \tDealer Bust "+dealerHand.getValue());
            return playerHand.getBet() * 2;
        else if (playerHand.getValue() > dealerHand.getValue())
            //System.out.println("Win \tplayer="+playerHand.getValue()+", dealer="+dealerHand.getValue());
            return playerHand.getBet() * 2;
        else if (playerHand.getValue() < dealerHand.getValue())
            //System.out.println("Lose \tplayer="+playerHand.getValue()+", dealer="+dealerHand.getValue());
            return 0;
        else if (playerHand.getValue() == dealerHand.getValue())
            //System.out.println("--- \tPush @ " + dealerHand.getValue());
            return playerHand.getBet();
        else
            throw new RuntimeException("Player: " + playerHand + ", Dealer: " + dealerHand);
    }

    public void setDealer(Player player) {
        dealer = player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        money = 0;
    }

    public ResultaatHand speelHand(int spelerHard) {
        if (deck == null)
            deck = new Deck(decks, player);
        if ((float) deck.size() / deck.getInitialSize() < cutcardpenetration)
            deck = new Deck(decks, player);
        Hand dealerHand = new Hand(deck.draw(), deck.draw());
        playoutDealer(deck, dealer, dealerHand);
        char winner = 'S';
        int dealerscore = dealerHand.getValue();
        if (dealerscore != 21 && spelerHard < dealerscore)
            winner = 'D';
        return new ResultaatHand(winner, spelerHard);
    }

    public ResultaatHand speelSpelerSplit(Card c1, Card c2) {

        int money = 0;
        if (deck == null)
            deck = new Deck(decks, player);
        if ((float) deck.size() / deck.getInitialSize() < cutcardpenetration)
            deck = new Deck(decks, player);
        float prevMoney = money;
        List<Hand> playerHands = new ArrayList<Hand>(1);
        playerHands.add(new Hand(player.bet(), c1, deck.draw(), true));
        playerHands.add(new Hand(player.bet(), c2, deck.draw(), true));
        deck.remove(c1);
        deck.remove(c2);
        Hand dealerHand = new Hand(deck.draw(), deck.draw());
        money -= (2 * playerHands.get(0).getBet());

        if (dealerHand.getValue() == 21) {
            deck.add(c1);
            deck.add(c2);
            return speelSpelerSplit(c1, c2);
        }

        money += playoutPlayer(player, deck, playerHands, dealerHand);

        playoutDealer(deck, dealer, dealerHand);

        for (Hand playerHand : playerHands)
            money += payout(playerHand, dealerHand);

        float winst = money - prevMoney;
        deck.add(c1);
        deck.add(c2);
        deck.shuffle(new Random());
        return new ResultaatHand(player, dealer, bepaalWinnaar(playerHands, dealerHand), winst, money);

    }

  private char bepaalWinnaar(List<Hand> spelers, Hand dealer) {
      if (dealer.getValue() > 21)
          return 'S';
        for (Hand speler : spelers)
            if (bepaalWinnaarHand(speler, dealer) == 'S')
                return 'S';
        return 'D';
    }
    
    private char bepaalWinnaarHand(Hand speler, Hand dealer) {
        int s = speler.getValue();
        int d = dealer.getValue();
        if (s > 21)
            return 'D';
        if (d > 21)
            return 'S';
        if ((s <= 21 && s >= d) || d > 21)
            return 'S';
        
        return 'D';
    }
    
    public ResultaatHand speelSpelerGeenSplit(Card c1, Card c2) {

        int money = 0;
        if (deck == null)
            deck = new Deck(decks, player);
        if ((float) deck.size() / deck.getInitialSize() < cutcardpenetration)
            deck = new Deck(decks, player);
        float prevMoney = money;
        List<Hand> playerHands = new ArrayList<Hand>(1);
        playerHands.add(new Hand(player.bet(), c1, c2, false));
       // deck.remove(c1);
        //  deck.remove(c2);
        Hand dealerHand = new Hand(deck.draw(), deck.draw());
        money -= (2 * playerHands.get(0).getBet());

        if (dealerHand.getValue() == 21) {
            deck.add(c1);
            deck.add(c2);
            return speelSpelerSplit(c1, c2);
        }

        money += playoutPlayer(player, deck, playerHands, dealerHand);

        playoutDealer(deck, dealer, dealerHand);

        for (Hand playerHand : playerHands)
            money += payout(playerHand, dealerHand);

        float winst = money - prevMoney;
         deck.add(c1);
        deck.add(c2);
        deck.shuffle(new Random());
        return new ResultaatHand(player, dealer, bepaalWinnaar(playerHands, dealerHand), winst, money);

    }

}
