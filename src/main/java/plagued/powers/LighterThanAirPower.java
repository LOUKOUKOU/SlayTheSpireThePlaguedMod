package plagued.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import plagued.actions.ApplyGeneralPowerAction;

import static plagued.ThePlagued.makeID;

public class LighterThanAirPower extends BasePower {
    public static final String POWER_ID = makeID("LighterThanAirPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public LighterThanAirPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            int tempAmount = this.amount;
            int cardCount = 0;
            for (int i = 0; i < AbstractDungeon.player.hand.size(); i++) {
                if(!AbstractDungeon.player.hand.group.get(i).isEthereal)
                    cardCount++;
            }
            int finalCardCount = cardCount;
            this.addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    this.addToBot(new AbstractGameAction() {
                        @Override
                        public void update() {
                            if(finalCardCount == 0)
                                this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(tempAmount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                            this.isDone = true;
                        }
                    });
                    this.isDone = true;
                }
            });
        }
    }
}
