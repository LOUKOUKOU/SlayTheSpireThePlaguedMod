package plagued.cards.skills.common;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.actions.ApplyCrippleAction;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.services.ApplyPowerService;
import plagued.util.CardStats;

public class Swoop extends BaseCard {
    public static final String ID = makeID("Swoop");
    private static final int ENERGY_GAIN = 2;
    private static final int UPGRADE_ENERGY_GAIN = 1;
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );

    public Swoop() {
        super(ID, info);
        this.exhaust = true;
        this.setMagic(ENERGY_GAIN, UPGRADE_ENERGY_GAIN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        this.addToBot(new GainEnergyAction(this.magicNumber));
        this.addToBot(new ApplyCrippleAction(ApplyPowerService.POWER_TYPE.DRAW_DOWN, p, "",  true));
    }
}
