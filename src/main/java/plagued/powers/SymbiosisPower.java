package plagued.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static plagued.ThePlagued.makeID;

public class SymbiosisPower extends BasePower implements OnReceivePowerPower {
    public static final String POWER_ID = makeID("SymbiosisPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public SymbiosisPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(target.isPlayer && power.type == PowerType.DEBUFF) {
            if(source == null || source.isPlayer) {
                AbstractMonster tempMonster = AbstractDungeon.getRandomMonster();
                this.addToBot(
                    new ApplyPowerAction(
                        tempMonster,
                        tempMonster,
                        power
                    )
                );
            } else {
                this.addToBot(
                    new ApplyPowerAction(
                        source,
                        source,
                        power
                    )
                );
            }


            this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
            return false;
        }
        return true;
    }
}
