package plagued.cards.skills.uncommon;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.actions.ApplyCrippleAction;
import plagued.actions.ApplyGeneralPowerAction;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.CrippledPower;
import plagued.powers.services.ApplyPowerService;
import plagued.util.CardStats;

public class IronWingTechnique extends BaseCard {
    public static final String ID = makeID("IronWingTechnique");
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    private static final int BASE_BLOCK = 15;
    private static final int BASE_DEBUFF = 1;
    private static final int BASE_BLOCK_UPGRADE = 5;
    private static final int BASE_DEBUFF_UPGRADE = 1;
    public IronWingTechnique() {
        super(ID, info);
        this.setBlock(BASE_BLOCK, BASE_BLOCK_UPGRADE);
        this.setMagic(BASE_DEBUFF, BASE_DEBUFF_UPGRADE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new ApplyCrippleAction(ApplyPowerService.getRandomDebuffType(), p, "PTSD", this.magicNumber));
    }
}
