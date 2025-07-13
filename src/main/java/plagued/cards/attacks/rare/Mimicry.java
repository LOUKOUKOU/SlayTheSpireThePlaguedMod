package plagued.cards.attacks.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.defect.ForTheEyesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.patches.DamageLastTakenField;
import plagued.patches.LastAttackPatch;
import plagued.util.CardStats;

public class Mimicry extends BaseCard {
    public static final String ID = makeID("Mimicry");
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2
    );
    private static final int DAMAGE = 0;

    private static final Logger log = LoggerFactory.getLogger(Mimicry.class);

    public Mimicry() {
        super(ID, info);
        this.setCostUpgrade(1);
        setDamage(DAMAGE);
        setMagic(0);
    }

    public void updateMimicry() {
        DamageInfo tempDamage = DamageLastTakenField.damageLastTaken.damage.get(AbstractDungeon.player);
        int numberOfAttacks = DamageLastTakenField.damageLastTaken.multiAttack.get(AbstractDungeon.player);
        setDamage(tempDamage.base);
        setMagic(numberOfAttacks);
        this.updateDescription();
    }

    public void applyPowers() {
        super.applyPowers();
        this.updateMimicry();
    }

    public void updateDescription() {
        if (this.magicNumber > 1)
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[1];
        else
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];;
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
    }
}
