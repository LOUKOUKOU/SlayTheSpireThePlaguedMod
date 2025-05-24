package plagued.cards.powers.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.LighterThanAirPower;
import plagued.powers.MoultingPower;
import plagued.util.CardStats;

public class LighterThanAir extends BaseCard {
    public static final String ID = makeID("LighterThanAir");
    public static final int DAMAGE_AMOUNT = 10;
    public static final int DAMAGE_AMOUNT_UPGRADED = 5;

    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public LighterThanAir() {
        super(ID, info);
        setMagic(DAMAGE_AMOUNT, DAMAGE_AMOUNT_UPGRADED);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(
                new ApplyPowerAction(
                        p,
                        p,
                        new LighterThanAirPower(p, magicNumber)
                )
        );
    }
}