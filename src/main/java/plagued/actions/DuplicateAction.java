package plagued.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;

import static plagued.ThePlagued.makeID;

public class DuplicateAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String TEXT;
    public static final String TEXT_PLURAL;
    protected AbstractPlayer p;
    private final int copies;
    private final ArrayList<AbstractCard> cannotDuplicate = new ArrayList();
    private int amount = 1;

    public enum DUPLICATE_LOCATION {
        DISCARD,
        DRAW,
        HAND,
        DRAW_RANDOM
    }

    private final DUPLICATE_LOCATION location;

    public DuplicateAction(int copies, DUPLICATE_LOCATION location) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.copies = copies;
        this.location = location;
        this.duration = Settings.ACTION_DUR_FAST;
        this.p = AbstractDungeon.player;
    }

    public DuplicateAction(int amount, int copies, DUPLICATE_LOCATION location) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.copies = copies;
        this.amount = amount;
        this.location = location;
        this.duration = Settings.ACTION_DUR_FAST;
        this.p = AbstractDungeon.player;
    }


    @Override
    public void update() {
        this.addToBot(new SelectCardsInHandAction(
                this.amount,
                this.copies > 1 ? TEXT_PLURAL : TEXT,
                this::canDuplicate,
                (cards) -> {
                    for (AbstractCard c : cards) {
                        for (int i = 0; i < copies; i++) {
                            addToTop(duplicate(c, this.location, this.copies));
                        }
                    }
                }
        ));
        this.isDone = true;
    }

    public static AbstractGameAction duplicate(AbstractCard c, DUPLICATE_LOCATION location, int copies) {
        AbstractCard copy = c.makeStatEquivalentCopy();
        switch (location) {
            case DISCARD:
                return new MakeTempCardInDiscardAction(copy, copies);
            case DRAW:
                return new MakeTempCardInDrawPileAction(copy, copies, false, false, true);
            case DRAW_RANDOM:
                return new MakeTempCardInDrawPileAction(copy, copies, true, false, false);
            case HAND:
            default:
                return new MakeTempCardInHandAction(copy, copies);

        }
    }


    private boolean canDuplicate(AbstractCard card) {
        return card.type == AbstractCard.CardType.ATTACK || card.type == AbstractCard.CardType.SKILL;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(makeID("DUPLICATE_ACTION"));
        TEXT = uiStrings.TEXT[0];
        TEXT_PLURAL = uiStrings.TEXT[1];
    }
}
