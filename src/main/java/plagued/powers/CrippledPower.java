package plagued.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import plagued.powers.services.ApplyPowerService;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static plagued.ThePlagued.makeID;

public class CrippledPower extends BasePower {
    public static final String POWER_ID = makeID("CrippledPower");
    private static final boolean TURN_BASED  = true;;
    private final AbstractPower powerApplied;

    public CrippledPower(AbstractCreature owner, String name, ApplyPowerService.POWER_TYPE powerApplied, int amount) {
        super(POWER_ID, ApplyPowerService.getPowerType(powerApplied), TURN_BASED, owner, 1);
        this.name = name;
        this.powerApplied =  ApplyPowerService.getPower(powerApplied, owner, amount, false) ;
        this.updateDescription(powerApplied, amount);
    }

    public void updateDescription(ApplyPowerService.POWER_TYPE powerApplied, int amount) {
        this.description = DESCRIPTIONS[0] + ApplyPowerService.getDescription(powerApplied, amount);
    }

    public void atStartOfTurn() {
        this.flash();
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, this.powerApplied));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
}
