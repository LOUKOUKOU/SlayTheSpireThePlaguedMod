package plagued.cards.powers.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.SymbiosisPower;
import plagued.util.CardStats;

public class Symbiosis extends BaseCard {
    public static final String ID = makeID("Symbiosis");
    public static final int BLOCK_DEBUFF = 1;
    public static final int BLOCK_DEBUFF_UPGRADE = 1;

    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    public Symbiosis() {
        super(ID, info);
        this.setMagic(BLOCK_DEBUFF, BLOCK_DEBUFF_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(
                new ApplyPowerAction(
                        p,
                        p,
                        new SymbiosisPower(p, magicNumber)
                )
        );
    }
}
