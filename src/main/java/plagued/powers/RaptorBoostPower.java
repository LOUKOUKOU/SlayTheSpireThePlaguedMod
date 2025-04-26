package plagued.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import plagued.powers.services.ApplyPowerService;

import static plagued.ThePlagued.makeID;

public class RaptorBoostPower extends BasePower {
    public static final String POWER_ID = makeID("RaptorBoostPower");
    private static final int DAMAGE = 4;

    public RaptorBoostPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.updateDescription(amount);
    }

    public void updateDescription(int amount) {
        this.description = DESCRIPTIONS[0] + DAMAGE * amount + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2] + amount + DESCRIPTIONS[3];
    }

    public void atEndOfRound() {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? (damage + DAMAGE * (float)this.amount) : damage;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.flash();
            AbstractCreature p = AbstractDungeon.player;
            if (card.multiDamage != null) {
                for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    this.addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, this.amount, false), this.amount, true, AbstractGameAction.AttackEffect.NONE));
                    this.addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, this.amount, false), this.amount, true, AbstractGameAction.AttackEffect.NONE));
                }
            } else {
                AbstractCreature mo = action.target;
                this.addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, this.amount, false), this.amount, true, AbstractGameAction.AttackEffect.NONE));
                this.addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, this.amount, false), this.amount, true, AbstractGameAction.AttackEffect.NONE));
            }

            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
    }
}
