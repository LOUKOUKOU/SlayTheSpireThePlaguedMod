package plagued.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import plagued.powers.DiseasePower;
import plagued.powers.DudPower;

import java.util.Random;

public class ApplyPowerToAll extends AbstractGameAction {
    private int amount = 1;
    private AbstractPlayer player;
    private AbstractMonster monster = null;

    public enum DEBUFF_TYPE {
        WEAK,
        VULNERABLE,
        LOOSE_STR,
        POISON,
        SLOW,
        CHOKE,
        CONSTRICTED,
        DISEASE
    }

    public enum BUFF_TYPE {
//        CORPSE_EXPLOSION,
        STRENGTH,
        ARTIFACT,
        BARRICADE,
        BUFFER,
        INTANGIBLE,
        METALLICIZE,
        PLATED_ARMOR,
        REGENERATE,
        RITUAL,
        THORNS,
        VIGOR,
        ANGRY,
        MALLEABLE,
    }

    public ApplyPowerToAll(AbstractPlayer player) {
        this.amount = 1;
        this.player = player;
    }

    public ApplyPowerToAll(AbstractPlayer player, AbstractMonster monster) {
        this.amount = 1;
        this.monster = monster;
        this.player = player;
    }

    public ApplyPowerToAll(AbstractPlayer player, int amount) {
        this.amount = amount;
        this.player = player;
    }

    public ApplyPowerToAll(AbstractPlayer player, AbstractMonster monster, int amount) {
        this.amount = amount;
        this.monster = monster;
        this.player = player;
    }

    private static AbstractPower getDebuff(DEBUFF_TYPE type, AbstractCreature owner, AbstractPlayer player, int amount) {
        switch (type) {
            case WEAK:
                return new WeakPower(owner, amount, false);
            case VULNERABLE:
                return new VulnerablePower(owner, amount, false);
            case LOOSE_STR:
                return new StrengthPower(owner, -amount);
            case POISON:
                return new PoisonPower(owner, player, amount);
            case SLOW:
                return new SlowPower(owner, amount);
            case CHOKE:
                return new ChokePower(owner, amount);
            case CONSTRICTED:
                return new ConstrictedPower(owner, player, amount);
            case DISEASE:
                return new DiseasePower(owner, amount);
            default:
                return new DudPower(owner);
        }
    }

    private DEBUFF_TYPE getRandomDebuffType() {
        int pick = new Random().nextInt(DEBUFF_TYPE.values().length);
        return DEBUFF_TYPE.values()[pick];
    }

    @Override
    public void update() {
        if (this.monster != null) {
            for (int i = 0; i < this.amount; i++) {
                this.addToBot(new ApplyPowerAction(this.monster, this.monster, getDebuff(getRandomDebuffType(), this.monster, this.player, 1)));
            }
        } else
        if (this.player != null) {
            for (int i = 0; i < this.amount; i++) {
                this.addToBot(new ApplyPowerAction(this.player, this.player, getDebuff(getRandomDebuffType(), this.player, this.player, 1)));
            }
        }
        this.isDone = true;
    }
}
