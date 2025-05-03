package plagued.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import plagued.relics.rare.HemlockMoth;

import static plagued.ThePlagued.makeID;

public class DudPower extends BasePower {
    public static final String POWER_ID = makeID("DudPower");
    public DudPower(AbstractCreature owner) {
        super(POWER_ID, PowerType.DEBUFF, true, owner, 0);
    }
}
