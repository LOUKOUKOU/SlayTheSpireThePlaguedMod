package plagued.cards.skills.rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.actions.ApplyGeneralPowerAction;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.util.CardStats;

public class Doom extends BaseCard {
    public static final String ID = makeID("Doom");
    private static final int MAGIC_NUMBER = 7;
    private static final int UPGRADE_MAGIC_NUMBER = 3;

    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ALL,
            2
    );

    public Doom() {
        super(ID, info);
        this.setMagic(this.MAGIC_NUMBER, this.UPGRADE_MAGIC_NUMBER);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyGeneralPowerAction(p,null, this.magicNumber));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new ApplyGeneralPowerAction(p,mo, this.magicNumber));
        }
    }
}
