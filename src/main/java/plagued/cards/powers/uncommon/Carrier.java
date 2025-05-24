package plagued.cards.powers.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.BirdOfPreyPower;
import plagued.powers.CarrierPower;
import plagued.util.CardStats;

public class Carrier extends BaseCard {
    public static final String ID = makeID("Carrier");
    public static final int DISEASE_AMOUNT = 1;
    public static final int DISEASE_AMOUNT_UPGRADED = 1;

    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public Carrier() {
        super(ID, info);
        setMagic(DISEASE_AMOUNT, DISEASE_AMOUNT_UPGRADED);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(
                new ApplyPowerAction(
                        p,
                        p,
                        new CarrierPower(p, magicNumber)
                )
        );
    }
}