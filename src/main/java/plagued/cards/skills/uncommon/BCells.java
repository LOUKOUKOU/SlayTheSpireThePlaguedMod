package plagued.cards.skills.uncommon;

import com.evacipated.cardcrawl.mod.stslib.cards.targeting.SelfOrEnemyTargeting;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.util.CardStats;

public class BCells extends BaseCard {
    public static final String ID = makeID("BCells");
    public static final int BLOCK_PER_POWER = 4;
    public static final int BLOCK_PER_POWER_UPGRADE = 1;
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            SelfOrEnemyTargeting.SELF_OR_ENEMY,
            1
    );

    public BCells() {
        super(ID, info);
        setBlock(BLOCK_PER_POWER, BLOCK_PER_POWER_UPGRADE);
    }

    public void use(AbstractCreature target) {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractCreature target = SelfOrEnemyTargeting.getTarget(this);

        if (target == null)
            target = AbstractDungeon.player;

        for (int i = 0; i < target.powers.size(); i++) {
            this.addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
        }
    }
}
