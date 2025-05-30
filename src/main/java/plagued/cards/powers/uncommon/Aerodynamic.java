package plagued.cards.powers.uncommon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.AerodynamicPower;
import plagued.util.CardStats;

public class Aerodynamic extends BaseCard {
    public static final String ID = makeID("Aerodynamic");
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public Aerodynamic() {
        super(ID, info);
        setMagic(1, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(
                new ApplyPowerAction(
                        p,
                        p,
                        new AerodynamicPower(p, magicNumber)
                )
        );
    }
}
