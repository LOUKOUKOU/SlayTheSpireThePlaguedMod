package plagued.relics.starter;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import plagued.character.ThePlaguedCharacter;
import plagued.relics.BaseRelic;

import static plagued.ThePlagued.makeID;

public class ArtificialWing extends BaseRelic {
    private static final String NAME = "ArtificialWing";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private boolean usedThisTurn = false;

    public ArtificialWing() {
        super(ID, NAME, ThePlaguedCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return String.format(DESCRIPTIONS[0]);
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        this.usedThisTurn = false;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (!this.usedThisTurn && targetCard.type == AbstractCard.CardType.ATTACK) {
            super.onUseCard(targetCard, useCardAction);
            if (targetCard.costForTurn == 0 || (targetCard.freeToPlayOnce && targetCard.cost != -1)) {
                this.addToBot(new DrawCardAction(AbstractDungeon.player, 1));
                this.usedThisTurn = true;
            }
        }
    }
}
