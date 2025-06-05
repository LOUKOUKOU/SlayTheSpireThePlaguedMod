package plagued.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Random;

import static plagued.ThePlagued.makeID;

public class CombatReadyPower extends BasePower {
    public static final String POWER_ID = makeID("CombatReadyPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private AbstractCard.CardRarity getRandomRarity() {
        AbstractCard.CardRarity[] rarityArr = {
                AbstractCard.CardRarity.COMMON,
                AbstractCard.CardRarity.UNCOMMON,
                AbstractCard.CardRarity.RARE
        };
        Random rn = new Random();
        int answer = rn.nextInt(3);
        return rarityArr[answer];
    }

    public CombatReadyPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    public void updateDescription() {
        if(this.amount > 1)
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        else
            this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurn() {
        for(int i = 0; i < this.amount; ++i) {
            AbstractCard card = AbstractDungeon.getCardFromPool(getRandomRarity(), AbstractCard.CardType.ATTACK, true).makeCopy();
            this.addToBot(new MakeTempCardInHandAction(card, 1, false));
        }
    }
}
