package plagued.powers.services;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.*;

public class ApplyPowerService {
    public enum POWER_TYPE {
        WEAK,
        FRAIL,
        VULNERABLE,
        LOOSE_DEX,
        LOOSE_STR
    }

    public static AbstractPower getPower(POWER_TYPE type, AbstractCreature owner, int amount, boolean isSourceMonster) {
        switch (type) {
            case WEAK:
            case FRAIL:
            case VULNERABLE:
                switch (type) {
                    case WEAK:
                        return new WeakPower(owner, amount, isSourceMonster);
                    case FRAIL:
                        return new FrailPower(owner, amount, isSourceMonster);
                    case VULNERABLE:
                        return new VulnerablePower(owner, amount, isSourceMonster);
                    default:
                        return null;
                }
            default:
                return null;
        }

    }
    public static AbstractPower getPower(POWER_TYPE type, AbstractCreature owner, int amount) {
        switch (type) {
            case LOOSE_DEX:
            case LOOSE_STR:
            case VULNERABLE:
                switch (type) {
                    case LOOSE_DEX:
                        return new LoseDexterityPower(owner, amount);
                    case LOOSE_STR:
                        return new LoseStrengthPower(owner, amount);
                    default:
                        return null;
                }
            default:
                return null;
        }
    }

    public static AbstractPower.PowerType getPowerType(POWER_TYPE type) {
        switch (type) {
            case WEAK:
            case FRAIL:
            case VULNERABLE:
            case LOOSE_DEX:
            case LOOSE_STR:
            default:
                return AbstractPower.PowerType.DEBUFF;
        }
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
            default:
                description = "";
        }
        return description.replace("!amount!", amount + "");
    }
}
