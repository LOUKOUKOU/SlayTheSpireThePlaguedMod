package plagued.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import plagued.powers.services.ApplyPowerService;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static plagued.ThePlagued.makeID;

public class CrippledPower extends BasePower implements NonStackablePower {
    public static final String POWER_ID = makeID("CrippledPower");
    private static final boolean TURN_BASED = true;
    ;
    private final ApplyPowerService.POWER_TYPE powerApplied;

    public CrippledPower(AbstractCreature owner, String name, ApplyPowerService.POWER_TYPE powerApplied, int amount) {
        super(POWER_ID, ApplyPowerService.getPowerType(powerApplied), TURN_BASED, owner, amount);
        this.name = name;
        this.powerApplied = powerApplied;
        this.updateDescription(powerApplied, amount);
    }

    public void updateDescription(ApplyPowerService.POWER_TYPE powerApplied, int amount) {
        this.description = DESCRIPTIONS[0] + ApplyPowerService.getDescription(powerApplied, amount);
    }

    public static AbstractGameAction getPower(ApplyPowerService.POWER_TYPE powerApplied, AbstractCreature owner, int amount) {
        if (powerApplied == ApplyPowerService.POWER_TYPE.DEATH_PLUNGE) {
            return new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        } else {
            return new ApplyPowerAction(AbstractDungeon.player,
                    AbstractDungeon.player,
                    ApplyPowerService.getPower(powerApplied, owner, amount, false)
            );
        }
    }

    public void atStartOfTurn() {
        this.flash();
        this.addToBot(getPower(this.powerApplied, this.owner, this.amount));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
}
