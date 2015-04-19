package com.philihp.bj;

public class DealerPlayerChangedStop implements Player {
    private int stopAt;
    
	public DealerPlayerChangedStop(int stopAt) {
            this.stopAt = stopAt;
	}

	public int bet() {
		return Blackjack.MIN_BET*1;
	}
	
	public Response prompt(Hand playerHand, Hand dealerHand, boolean canSplit) {
		if(dealerHand.getValue() < stopAt) return Response.H;
		else if(dealerHand.getValue() > stopAt) return Response.S;
		else if(dealerHand.isSoft()) return Blackjack.SOFT17;
		else return Response.S;
	}
	
	public void notify(Card card) {
	}
	
	public void resetCount(int decks) {
	}

    public int getStopAt() {
        return stopAt;
    }

        
        
    @Override
    public String toString() {
        return String.format("Speler trekt kaarten tot %d.", stopAt);
    }
        
        
}
