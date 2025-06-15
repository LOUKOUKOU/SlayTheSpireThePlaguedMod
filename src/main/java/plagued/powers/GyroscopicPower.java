package plagued.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.actions.DuplicateAction;

import static plagued.ThePlagued.makeID;

public class GyroscopicPower extends BasePower {
    public static final String POWER_ID = makeID("GyroscopicPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public GyroscopicPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    public void updateDescription() {
        if(this.amount > 1)
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        else
            this.description = DESCRIPTIONS[0];
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        this.addToBot(new DrawCardAction(1));
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));

        super.onPlayCard(card, m);
    }
}
