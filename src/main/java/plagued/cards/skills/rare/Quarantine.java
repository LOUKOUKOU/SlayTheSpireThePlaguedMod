package plagued.cards.skills.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.actions.ApplyGeneralPowerAction;
import plagued.actions.DiscountAction;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.DiseasePower;
import plagued.powers.FeatherFormPower;
import plagued.util.CardStats;

public class Quarantine extends BaseCard {
    public static final String ID = makeID("Quarantine");
    private static final int DISEASE_AMOUNT = 2;
    private static final int UPGRADE_DISEASE_AMOUNT = 1;

    private static final CardStats info = new CardStats(
        ThePlaguedCharacter.Meta.CARD_COLOR,
        CardType.SKILL,
        CardRarity.RARE,
        CardTarget.ALL_ENEMY,
        2
    );

    public Quarantine() {
        super(ID, info);
        this.setMagic(DISEASE_AMOUNT, UPGRADE_DISEASE_AMOUNT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(
                new ApplyPowerAction(
                    mo,
                    p,
                    new DiseasePower(mo, this.magicNumber)
                )
            );
        }

        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(
                new AbstractGameAction() {
                    public void update() {
                        if(mo.getPower(DiseasePower.POWER_ID) != null)
                            mo.getPower(DiseasePower.POWER_ID).stackPower(mo.getPower(DiseasePower.POWER_ID).amount);
                        this.isDone = true;
                    }
                }
            );
        }
    }
}
