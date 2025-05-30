package plagued.relics.uncommon;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import plagued.character.ThePlaguedCharacter;
import plagued.relics.BaseRelic;

import static plagued.ThePlagued.makeID;

public class BattleOils extends BaseRelic {
    private static final String NAME = "BattleOils";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.SOLID;
    private static final int BLOCK_BY = 3;

    public BattleOils() {
        super(ID, NAME, ThePlaguedCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return String.format(DESCRIPTIONS[0] + BLOCK_BY + DESCRIPTIONS[1]);
    }

    @Override
    public void onPlayerEndTurn() {
        for (int i = 0; i < AbstractDungeon.player.powers.size(); i++) {
            AbstractPower tempPower = AbstractDungeon.player.powers.get(i);
            if(tempPower.type == AbstractPower.PowerType.DEBUFF) {
                this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, BLOCK_BY, true));
            }
        }
    }
}
