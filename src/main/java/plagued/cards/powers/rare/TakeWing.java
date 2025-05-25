package plagued.cards.powers.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.HomeostasisPower;
import plagued.powers.TakeWingPower;
import plagued.util.CardStats;

public class TakeWing extends BaseCard {
    public static final String ID = makeID("TakeWing");
    private static final int WING_STACKS = 4;
    private static final int WING_STACKS_UPGRADED = 2;

    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );

    public TakeWing() {
        super(ID, info);
        this.setMagic(WING_STACKS, WING_STACKS_UPGRADED);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(
            new ApplyPowerAction(
                p,
                p,
                new TakeWingPower(p, this.magicNumber)
            )
        );
    }
}
