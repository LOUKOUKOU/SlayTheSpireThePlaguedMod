package plagued.relics.mutation;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.HomeostasisPower;
import plagued.powers.PaleBloodPower;
import plagued.powers.services.ApplyPowerService;
import plagued.relics.BaseRelic;
import plagued.relics.starter.ArtificialWing;

import static plagued.ThePlagued.makeID;

public class PaleBlood extends Stackable {
    private static final String NAME = "PaleBlood";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.HEAVY;
    private static final Logger log = LoggerFactory.getLogger(PaleBlood.class);

    public PaleBlood() {
        super(ID, NAME, RARITY, SOUND);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public String getUpdatedDescription() {
        return String.format(DESCRIPTIONS[0]);
    }

    @Override
    public void atBattleStart() {
        this.addToBot(
            new ApplyPowerAction(
                AbstractDungeon.player,
                AbstractDungeon.player,
                new PaleBloodPower(AbstractDungeon.player)
            )
        );
        super.atBattleStart();
    }
}

