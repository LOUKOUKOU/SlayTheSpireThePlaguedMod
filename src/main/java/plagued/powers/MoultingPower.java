package plagued.powers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.actions.ApplyGeneralPowerAction;

import static plagued.ThePlagued.makeID;

public class MoultingPower extends BasePower {
    public static final String POWER_ID = makeID("MoultingPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public MoultingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    public void updateDescription() {
        if(this.amount > 0)
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        else
            this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();

            for(AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (!m.isDead && !m.isDying) {
                    this.addToBot(new ApplyGeneralPowerAction(AbstractDungeon.player, m, this.amount));
                }
            }
        }

    }
}
