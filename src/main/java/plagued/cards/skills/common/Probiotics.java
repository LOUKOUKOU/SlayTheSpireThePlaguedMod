package plagued.cards.skills.common;

import com.evacipated.cardcrawl.mod.stslib.cards.targeting.SelfOrEnemyTargeting;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.util.CardStats;

public class Probiotics extends BaseCard {
    public static final String ID = makeID("Probiotics");
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_MAGIC_NUMBER = 1;
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            SelfOrEnemyTargeting.SELF_OR_ENEMY,
            1
    );

    public Probiotics() {
        super(ID, info);
        this.setMagic(MAGIC_NUMBER, UPGRADE_MAGIC_NUMBER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCreature target = SelfOrEnemyTargeting.getTarget(this);
        if (target == null)
            target = AbstractDungeon.player;
        for (int i = 0; i < target.powers.size(); i++) {
            target.powers.get(i).stackPower(this.magicNumber);
        }
    }
}
