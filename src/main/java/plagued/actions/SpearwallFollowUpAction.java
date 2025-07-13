package plagued.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SpearwallFollowUpAction extends AbstractGameAction {
    private final int amount;
    private final AbstractPlayer player;
    public SpearwallFollowUpAction(int amount, AbstractPlayer player) {
        this.duration = 0.001F;
        this.amount = amount;
        this.player = player;
    }

    public void update() {
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.4F));
        this.tickDuration();
        if (this.isDone) {
            for(AbstractCard c : DrawCardAction.drawnCards) {
                if(c.type == AbstractCard.CardType.ATTACK) {
                    this.addToBot(new GainBlockAction(this.player, this.player, this.amount));
                }
            }
        }

    }
}
