package plagued.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static plagued.ThePlagued.makeID;

public class SymbioticPower extends BasePower {
    public static final String POWER_ID = makeID("SymbioticPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final int block;

    public SymbioticPower(AbstractCreature owner, int block) {
        super(POWER_ID, TYPE, TURN_BASED, owner, 1);
        this.updateDescription();
        this.block = block;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (this.amount * this.block) + DESCRIPTIONS[1];
    }

    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer) {
            for (int i = 0; i < AbstractDungeon.player.powers.size(); i++) {
                AbstractPower tempPower = AbstractDungeon.player.powers.get(i);
                if(tempPower.type == PowerType.DEBUFF) {
                    this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, amount * this.block, true));
                }
            }
        }
    }
}
