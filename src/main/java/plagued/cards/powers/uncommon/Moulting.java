package plagued.cards.powers.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.CarrierPower;
import plagued.powers.MoultingPower;
import plagued.util.CardStats;

public class Moulting extends BaseCard {
    public static final String ID = makeID("Moulting");

    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public Moulting() {
        super(ID, info);
        this.setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(
                new ApplyPowerAction(
                        p,
                        p,
                        new MoultingPower(p, 1)
                )
        );
    }
}