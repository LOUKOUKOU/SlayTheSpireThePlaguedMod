package plagued.cards.attacks.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.util.CardStats;

import java.sql.Array;
import java.util.Objects;
import java.util.Random;

public class Dive extends BaseCard {
    public static final String ID = makeID("Dive");
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            2
    );
    private static final int DAMAGE = 8;
    private static final int DAMAGE_PER_DIVE = 3;
    private static final int DAMAGE_PER_DIVE_UPGRADE = 2;

    public Dive() {
        super(ID, info);
        setDamage(DAMAGE);
        setMagic(DAMAGE_PER_DIVE, DAMAGE_PER_DIVE_UPGRADE);
    }

    private static boolean isDive(AbstractCard card) {
        return Objects.equals(card.cardID, ID);
    }

    private static int countCards() {
        int count = 0;

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (isDive(c)) {
                ++count;
            }
        }

        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (isDive(c)) {
                ++count;
            }
        }

        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (isDive(c)) {
                ++count;
            }
        }

        return count;
    }

    private static AbstractGameAction.AttackEffect getRandomSlash() {
        AbstractGameAction.AttackEffect[] slashes = {
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL,
            AbstractGameAction.AttackEffect.SLASH_VERTICAL,
            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
        };

        Random rn = new Random();
        return slashes[rn.nextInt(2)];
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * countCards();
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * countCards();
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), getRandomSlash()));
    }
}
