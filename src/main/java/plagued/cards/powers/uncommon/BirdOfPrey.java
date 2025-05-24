package plagued.cards.powers.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.BirdOfPreyPower;
import plagued.powers.SymbioticPower;
import plagued.util.CardStats;

public class BirdOfPrey extends BaseCard {
    public static final String ID = makeID("BirdOfPrey");
    private static final int CARDS_TO_DUPE = 1;
    private static final int CARDS_TO_DUPE_UPGRADED = 1;

    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public BirdOfPrey() {
        super(ID, info);
        setMagic(CARDS_TO_DUPE, CARDS_TO_DUPE_UPGRADED);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(
                new ApplyPowerAction(
                        p,
                        p,
                        new BirdOfPreyPower(p, this.magicNumber)
                )
        );
    }
}
