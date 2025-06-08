package plagued.relics.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.DiseasePower;
import plagued.relics.BaseRelic;

import static plagued.ThePlagued.makeID;

public class FeatherCrown extends BaseRelic {
    private static final String NAME = "FeatherCrown";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.SOLID;
    private static final int DEBUFF_NUMBER = 3;

    public FeatherCrown() {
        super(ID, NAME, ThePlaguedCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return String.format(DESCRIPTIONS[0] + DEBUFF_NUMBER + DESCRIPTIONS[1] + DEBUFF_NUMBER + DESCRIPTIONS[2]);
    }

    @Override
    public void onMonsterDeath(AbstractMonster monster) {
        if (monster.currentHealth == 0 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            AbstractPlayer p = AbstractDungeon.player;

            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (!m.isDead && !m.isDying) {
                    this.addToBot(new ApplyPowerAction(m, p, new DiseasePower(m, DEBUFF_NUMBER), DEBUFF_NUMBER, true, AbstractGameAction.AttackEffect.POISON));
                    this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new VulnerablePower(m, DEBUFF_NUMBER, false), DEBUFF_NUMBER, true));
                    this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new WeakPower(m, DEBUFF_NUMBER, false), DEBUFF_NUMBER, true));
                }
            }
        }
    }
}
