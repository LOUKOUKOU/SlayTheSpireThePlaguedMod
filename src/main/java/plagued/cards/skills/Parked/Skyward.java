package plagued.cards.skills.Parked;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.actions.ApplyCrippleAction;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.services.ApplyPowerService;
import plagued.util.CardStats;

public class Skyward extends BaseCard {
    public static final String ID = makeID("Skyward");
    private static final int BLOCK = 10;
    private static final int BLOCK_UPGRADE = 4;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_MAGIC_NUMBER = 2;
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.SPECIAL, //            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    public Skyward() {
        super(ID, info);
        this.setBlock(BLOCK, BLOCK_UPGRADE);
        this.setMagic(MAGIC_NUMBER, UPGRADE_MAGIC_NUMBER);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new ApplyCrippleAction(ApplyPowerService.POWER_TYPE.VULNERABLE, p, "Crash", 1));
    }
}
