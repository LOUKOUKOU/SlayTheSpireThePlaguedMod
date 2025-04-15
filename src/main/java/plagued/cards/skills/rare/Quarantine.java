package plagued.cards.skills.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.DiseasePower;
import plagued.util.CardStats;

public class Quarantine extends BaseCard {
    public static final String ID = makeID("Quarantine");
    private static final int MAGIC_NUMBER = 7;
    private static final int UPGRADE_MAGIC_NUMBER = 3;

    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            2
    );

    public Quarantine() {
        super(ID, info);
        this.setMagic(this.MAGIC_NUMBER, this.UPGRADE_MAGIC_NUMBER);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new ApplyPowerAction(mo, p, new DiseasePower(mo, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.POISON));
        }
    }
}
