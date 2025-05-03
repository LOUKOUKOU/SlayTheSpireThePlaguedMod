package plagued.cards.skills.common;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.actions.PerchFollowUpAction;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.util.CardStats;

public class Perch extends BaseCard {
    public static final String ID = makeID("Perch");
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK = 3;
    private static final int DRAW = 2;
    private static final int DRAW_UPGRADE = 1;
    public Perch() {
        super(ID, info);
        this.setBlock(BLOCK);
        setMagic(DRAW, DRAW_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(this.magicNumber, new PerchFollowUpAction(this.block, p)));
    }
}
