package plagued.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import java.util.ArrayList;

import static plagued.ThePlagued.makeID;


public class DiscountSelectAction extends DiscountAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final ArrayList<AbstractCard> cannotDiscount = new ArrayList();

    public DiscountSelectAction(DiscountAction.Discount_Type discountType, int discountBy) {
        super(discountType, discountBy);
    }

    public DiscountSelectAction(DiscountAction.Discount_Type discountType, boolean fullDiscount) {
        super(discountType, fullDiscount);
    }

    @Override
    public void update() {
        this.addToBot(new SelectCardsInHandAction(
                TEXT[0],
                this::canDiscount,
                (cards) -> {
                    for (AbstractCard c : cards) {
                        this.discount(c);
                    }
                }
        ));
        this.isDone = true;
    }

    private void returnCards() {
        for(AbstractCard c : this.cannotDiscount) {
            this.p.hand.addToTop(c);
        }

        this.p.hand.refreshHandLayout();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(makeID("DISCOUNT_ACTION"));
        TEXT = uiStrings.TEXT;
    }
}
