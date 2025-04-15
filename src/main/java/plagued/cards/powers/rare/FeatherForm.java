package plagued.cards.powers.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.actions.DiscountAction;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.FeatherFormPower;
import plagued.powers.PlungePower;
import plagued.util.CardStats;

public class FeatherForm extends BaseCard {
    public static final String ID = makeID("FeatherForm");
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );

    public FeatherForm() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(
            new ApplyPowerAction(
                p,
                p,
                new FeatherFormPower(p, this.upgraded ? DiscountAction.Discount_Type.COMBAT : DiscountAction.Discount_Type.PLAYED)
            )
        );
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
