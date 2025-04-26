package plagued.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import plagued.powers.services.ApplyPowerService;
import plagued.relics.rare.HemlockMoth;

import static plagued.ThePlagued.makeID;

public class DiseasePower extends BasePower {
    public static final String POWER_ID = makeID("DiseasePower");
    private static final float WEAK_EFFECTIVENESS = 0.01F;
    private static final float VULN_EFFECTIVENESS = 0.02F;
    private static final AbstractPower.PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    private float getWeak(int amount) {
        float reduceBy = this.amount * WEAK_EFFECTIVENESS;
        return Math.min(reduceBy, 0.1F);
    }

    private float getVuln(int amount) {
        return this.amount * VULN_EFFECTIVENESS;
    }

    private String formatForDescription(float value) {
        return String.format("%.0f", Math.ceil(value * 100));
    }

    public DiseasePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription(amount);
    }

    public void updateDescription(int amount) {
        float increaseBy = (this.amount * VULN_EFFECTIVENESS);
        if (AbstractDungeon.player.hasRelic(HemlockMoth.ID)) {
            float reduceBy = (this.amount * WEAK_EFFECTIVENESS);
            this.description = DESCRIPTIONS[0]
                    + formatForDescription(increaseBy)
                    + DESCRIPTIONS[1]
                    + DESCRIPTIONS[2]
                    + formatForDescription(reduceBy)
                    + DESCRIPTIONS[3]
                    + DESCRIPTIONS[4];
        } else {
            this.description = DESCRIPTIONS[0]
                    + formatForDescription(increaseBy)
                    + DESCRIPTIONS[1]
                    + DESCRIPTIONS[4];
        }
    }

    public void atEndOfRound() {
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, this, 1));
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL && !this.owner.isPlayer) {
            return damage * (1 + this.getVuln(this.amount));
        }
        return damage;
    }

    public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL && !this.owner.isPlayer && AbstractDungeon.player.hasRelic(HemlockMoth.ID)) {
            return damage * (1 - this.getWeak(this.amount));
        }
        return damage;
    }
}
