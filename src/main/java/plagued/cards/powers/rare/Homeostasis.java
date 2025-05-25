package plagued.cards.powers.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.actions.DiscountAction;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.FeatherFormPower;
import plagued.powers.HomeostasisPower;
import plagued.util.CardStats;

public class Homeostasis extends BaseCard {
    public static final String ID = makeID("Homeostasis");
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    public Homeostasis() {
        super(ID, info);
        this.setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(
            new ApplyPowerAction(
                p,
                p,
                new HomeostasisPower(p, 1)
            )
        );
    }
}
