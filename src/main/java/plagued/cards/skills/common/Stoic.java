package plagued.cards.skills.common;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import plagued.actions.ApplyCrippleAction;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.services.ApplyPowerService;
import plagued.util.CardStats;

public class Stoic extends BaseCard {
    public static final String ID = makeID("Stoic");
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_MAGIC_NUMBER = 1;
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    public Stoic() {
        super(ID, info);
        this.setMagic(MAGIC_NUMBER, UPGRADE_MAGIC_NUMBER);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
            for (int i = 0; i < AbstractDungeon.player.powers.size(); i++) {
                AbstractDungeon.player.powers.get(i).stackPower(this.magicNumber);
            }
    }
}
