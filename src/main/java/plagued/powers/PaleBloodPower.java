package plagued.powers;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import plagued.relics.mutation.PaleBlood;

import static plagued.ThePlagued.makeID;

public class PaleBloodPower extends BasePower {
    public static final String POWER_ID = makeID("PaleBloodPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private int healThisTurn = 0;
    private int HEAL_PER_ATTACK = 3;

    public PaleBloodPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, 0);
    }

    private int relicStack() {
        AbstractRelic r = AbstractDungeon.player.getRelic(PaleBlood.ID);
        if(r == null)
            return 0;
        return ((PaleBlood)r).relicStack;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        this.healThisTurn += damageAmount;
        this.healThisTurn = Math.min(this.healThisTurn, this.relicStack() * 3);
        this.amount = this.healThisTurn;
        return super.onAttacked(info, damageAmount);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        int tempHeal = Math.min(this.healThisTurn, HEAL_PER_ATTACK);
        this.healThisTurn -= tempHeal;
        this.amount -= tempHeal;
        this.addToTop(new HealAction(this.owner, this.owner, tempHeal, 0.0F));
        super.onAttack(info, damageAmount, target);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.healThisTurn = 0;
        this.amount = 0;
        super.atEndOfTurn(isPlayer);
    }

    @Override
    public void onVictory() {
        this.healThisTurn = 0;
        this.amount = 0;
        super.onVictory();
    }
}
