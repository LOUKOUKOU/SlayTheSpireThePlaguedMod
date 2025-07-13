package plagued.cards.skills.Parked;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.actions.ApplyGeneralPowerAction;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.util.CardStats;

public class Flap extends BaseCard {
    public static final String ID = makeID("Flap");
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_MAGIC_NUMBER = 1;
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            1
    );

    public Flap() {
        super(ID, info);
        this.setMagic(MAGIC_NUMBER, UPGRADE_MAGIC_NUMBER);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyGeneralPowerAction(p, m, magicNumber));
    }
}
