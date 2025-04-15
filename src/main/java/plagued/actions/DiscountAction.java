package plagued.actions;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DiscountAction extends AbstractGameAction {
    protected AbstractPlayer p;
    protected final DiscountSelectAction.Discount_Type discountType;
    protected boolean fullDiscount = false;
    protected int discountBy = 1;

    public DiscountAction(DiscountSelectAction.Discount_Type discountType, int discountBy) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;

        this.discountType = discountType;
        this.discountBy = discountBy;
    }

    public DiscountAction(DiscountSelectAction.Discount_Type discountType, boolean fullDiscount) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;

        this.discountType = discountType;
        this.fullDiscount = fullDiscount;
    }


    public enum Discount_Type {
        TURN,
        COMBAT,
        PLAYED
    }

    protected boolean canDiscount(AbstractCard c) {
        return c.cost > 0;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            boolean betterPossible = false;
            boolean possible = false;

            for(AbstractCard c : this.p.hand.group) {
                if (c.costForTurn > 0) {
                    betterPossible = true;
                } else if (c.cost > 0) {
                    possible = true;
                }
            }

            if (betterPossible || possible) {
                this.findAndModifyRandomCard(betterPossible);
            }
        }

        this.tickDuration();
    }

    private void findAndModifyRandomCard(boolean better) {
        AbstractCard c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
        if (better) {
            if (c.costForTurn > 0) {
                discount(c);
            } else {
                this.findAndModifyRandomCard(better);
            }
        } else if (c.cost > 0) {
            discount(c);
        } else {
            this.findAndModifyRandomCard(better);
        }
    }

    protected void discount(AbstractCard c) {
        int discountByTemp = this.fullDiscount ? c.cost : this.discountBy;
        if(this.discountType == DiscountSelectAction.Discount_Type.COMBAT)
            c.updateCost(-discountByTemp);
        else if(this.discountType == DiscountSelectAction.Discount_Type.TURN)
            c.setCostForTurn(-discountByTemp);
        else if (this.discountType == DiscountSelectAction.Discount_Type.PLAYED)
            c.freeToPlayOnce = true;

        c.isCostModified = true;
        c.superFlash(Color.GOLD.cpy());
    }
}
