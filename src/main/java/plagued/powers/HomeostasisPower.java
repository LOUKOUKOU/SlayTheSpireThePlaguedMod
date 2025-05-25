package plagued.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import plagued.actions.DuplicateAction;
import plagued.cards.powers.rare.Homeostasis;

import java.util.Objects;

import static plagued.ThePlagued.makeID;

public class HomeostasisPower extends BasePower {
    public static final String POWER_ID = makeID("HomeostasisPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public HomeostasisPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        flash();
        for (int i = 0; i < AbstractDungeon.player.powers.size(); i++) {
            if (!Objects.equals(this.owner.powers.get(i).ID, this.ID)) {
                this.owner.powers.get(i).stackPower(this.amount);
            }
        }
    }
}
