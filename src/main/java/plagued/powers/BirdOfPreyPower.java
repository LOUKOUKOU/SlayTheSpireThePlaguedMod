package plagued.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import plagued.actions.DiscountAction;
import plagued.actions.DuplicateAction;

import static plagued.ThePlagued.makeID;

public class BirdOfPreyPower extends BasePower {
    public static final String POWER_ID = makeID("BirdOfPreyPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static final int NUMBER_OF_COPIES = 1;

    public BirdOfPreyPower(AbstractCreature owner, int amount) {
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
    public void atStartOfTurn() {
        flash();
        this.addToBot(new DuplicateAction(this.amount, NUMBER_OF_COPIES, DuplicateAction.DUPLICATE_LOCATION.DRAW_RANDOM));
    }
}
