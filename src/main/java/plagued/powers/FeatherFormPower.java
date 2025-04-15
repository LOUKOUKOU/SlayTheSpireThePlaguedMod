package plagued.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import plagued.actions.DiscountAction;

import static plagued.ThePlagued.makeID;

public class FeatherFormPower extends BasePower {
    public static final String POWER_ID = makeID("FeatherFormPower");
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final DiscountAction.Discount_Type discountType;

    public FeatherFormPower(AbstractCreature owner, DiscountAction.Discount_Type discountType) {
        super(POWER_ID, TYPE, TURN_BASED, owner, 1);
        this.discountType = discountType;
        updateDescription();
    }

    public void updateDescription() {
        this.description = this.discountType == DiscountAction.Discount_Type.PLAYED ? DESCRIPTIONS[0] : DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        flash();
        for (int i = 0; i < amount; i++) {
            this.addToBot(
                    new AbstractGameAction() {
                        public void update() {
                            this.addToBot(new DiscountAction(discountType, true));
                            this.isDone = true;
                        }
                    }
            );
        }
    }
}
