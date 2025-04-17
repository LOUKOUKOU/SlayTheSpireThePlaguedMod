package plagued.relics.boss;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import plagued.character.ThePlaguedCharacter;
import plagued.relics.BaseRelic;
import plagued.relics.starter.ArtificialWing;

import static plagued.ThePlagued.makeID;

public class MechanicalWing extends BaseRelic {
    private static final String NAME = "MechanicalWing";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.HEAVY;

    public MechanicalWing() {
        super(ID, NAME, ThePlaguedCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(ArtificialWing.ID);
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(ArtificialWing.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(ArtificialWing.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public String getUpdatedDescription() {
        return String.format(DESCRIPTIONS[0]);
    }
}
