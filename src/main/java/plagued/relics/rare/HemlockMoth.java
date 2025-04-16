package plagued.relics.rare;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import plagued.character.ThePlaguedCharacter;
import plagued.relics.BaseRelic;

import static plagued.ThePlagued.makeID;

public class HemlockMoth extends BaseRelic {
    private static final String NAME = "HemlockMoth";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.SOLID;
    private boolean usedThisTurn = false;

    public HemlockMoth() {
        super(ID, NAME, ThePlaguedCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return String.format(DESCRIPTIONS[0]);
    }
}
