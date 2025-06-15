package plagued.cards.skills.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.actions.ApplyGeneralPowerAction;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.GyroscopicPower;
import plagued.util.CardStats;

public class Gyroscopic extends BaseCard {
    public static final String ID = makeID("Gyroscopic");
    private static final int DRAW_AMOUNT = 4;
    private static final int UPGRADE_DRAW_AMOUNT = 2;

    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    public Gyroscopic() {
        super(ID, info);
        this.setMagic(DRAW_AMOUNT, UPGRADE_DRAW_AMOUNT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(
                new ApplyPowerAction(
                    p,
                    p,
                    new GyroscopicPower(p, this.magicNumber)
                )
            );
        }
    }
}
