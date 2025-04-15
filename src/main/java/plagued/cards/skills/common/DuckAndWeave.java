package plagued.cards.skills.common;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.actions.DiscountAction;
import plagued.actions.DiscountSelectAction;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.util.CardStats;

/**
 * 21:50:24.854 INFO basemod.BaseMod> publish on card use: theplagued:DuckAndWeave
 *
 * Exception in thread "LWJGL Application" com.badlogic.gdx.utils.GdxRuntimeException: java.lang.ExceptionInInitializerError
 */
public class DuckAndWeave extends BaseCard {
    public static final String ID = makeID("DuckAndWeave");
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    public DuckAndWeave() {
        super(ID, info);
        this.setBlock(4, 3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new DiscountSelectAction(DiscountAction.Discount_Type.PLAYED, true));
    }

}
