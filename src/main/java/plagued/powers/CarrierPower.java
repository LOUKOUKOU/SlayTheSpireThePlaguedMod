package plagued.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import plagued.actions.DuplicateAction;

import static plagued.ThePlagued.makeID;

public class CarrierPower extends BasePower {
    public static final String POWER_ID = makeID("CarrierPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public CarrierPower(AbstractCreature owner, int block) {
        super(POWER_ID, TYPE, TURN_BASED, owner, 1);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            this.flash();
            this.addToBot(new ApplyPowerAction(info.owner, this.owner, new DiseasePower(info.owner, this.amount), this.amount, true, AbstractGameAction.AttackEffect.POISON));
        }

        return damageAmount;
    }
}
