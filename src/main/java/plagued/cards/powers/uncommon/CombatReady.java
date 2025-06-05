package plagued.cards.powers.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.CarrierPower;
import plagued.powers.CombatReadyPower;
import plagued.util.CardStats;

public class CombatReady extends BaseCard {
    public static final String ID = makeID("CombatReady");

    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public CombatReady() {
        super(ID, info);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster monster) {
        this.addToBot(
            new ApplyPowerAction(
                p,
                p,
                new CombatReadyPower(p, 1)
            )
        );
    }
}
