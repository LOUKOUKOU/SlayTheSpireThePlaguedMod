package plagued.cards.powers.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.AcclimatizePower;
import plagued.powers.SymbioticPower;
import plagued.util.CardStats;

public class Acclimatize extends BaseCard {
    public static final String ID = makeID("Acclimatize");
    public static final int DRAW_PER_DEBUFF = 1;

    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    public Acclimatize() {
        super(ID, info);
        this.setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(
            new ApplyPowerAction(
                p,
                p,
                new AcclimatizePower(p, DRAW_PER_DEBUFF)
            )
        );
    }
}
