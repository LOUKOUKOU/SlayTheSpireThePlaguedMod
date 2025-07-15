package plagued.powers.services;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import plagued.actions.ApplyGeneralPowerAction;
import plagued.powers.DudPower;

import java.util.Random;

public class ApplyPowerService {
    public enum POWER_TYPE {
        WEAK,
        FRAIL,
        VULNERABLE,
        LOOSE_DEX,
        LOOSE_STR,
        DEATH_PLUNGE,
        DRAW_DOWN
    }

    public static AbstractPower getPower(POWER_TYPE type, AbstractCreature owner, int amount, boolean isSourceMonster) {
        switch (type) {
            case WEAK:
                return new WeakPower(owner, amount, isSourceMonster);
            case FRAIL:
                return new FrailPower(owner, amount, isSourceMonster);
            case VULNERABLE:
                return new VulnerablePower(owner, amount, isSourceMonster);
            case LOOSE_DEX:
                return new DexterityPower(owner, -amount);
            case LOOSE_STR:
                return new StrengthPower(owner, -amount);
            case DRAW_DOWN:
                return new DrawReductionPower(owner, amount);
            default:
                return new DudPower(owner);
        }

    }
    public static AbstractPower getPower(POWER_TYPE type, AbstractCreature owner, int amount) {
        switch (type) {
            case LOOSE_DEX:
                return new LoseDexterityPower(owner, amount);
            case LOOSE_STR:
                return new LoseStrengthPower(owner, amount);
            default:
                return new DudPower(owner);
        }
    }

    public static AbstractPower.PowerType getPowerType(POWER_TYPE type) {
        switch (type) {
            case WEAK:
            case FRAIL:
            case VULNERABLE:
            case LOOSE_DEX:
            case LOOSE_STR:
            case DRAW_DOWN:
            default:
                return AbstractPower.PowerType.DEBUFF;
        }
    }

    static public POWER_TYPE getRandomDebuffType() {
        int pick = new Random().nextInt(POWER_TYPE.values().length);
        return POWER_TYPE.values()[pick];
    }

    public static String getDescription(POWER_TYPE type, int amount) {
        String description = "";
        switch (type) {
            case LOOSE_DEX:
                description = "Loose !amount! #bdexterity";
                break;
            case LOOSE_STR:
                description = "Loose !amount! #bstrength";
                break;
            case WEAK:
                description = "Suffer !amount! #bWeak";
                break;
            case FRAIL:
                description = "Suffer !amount! #bFrail";
                break;
            case VULNERABLE:
                description = "Suffer !amount! #bVulnerable";
                break;
            case DEATH_PLUNGE:
                description = "Loose !amount! life next turn.";
                break;
            default:
                description = "";
        }
        return description.replace("!amount!", amount + "");
    }
}
