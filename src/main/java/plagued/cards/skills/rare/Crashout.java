package plagued.cards.skills.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.actions.DuplicateAction;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.DiseasePower;
import plagued.util.CardStats;

public class Crashout extends BaseCard {
    public static final String ID = makeID("Crashout");

    private static final CardStats info = new CardStats(
        ThePlaguedCharacter.Meta.CARD_COLOR,
        CardType.SKILL,
        CardRarity.RARE,
        CardTarget.SELF,
        2
    );

    public Crashout() {
        super(ID, info);
        this.exhaust = true;
        this.setCostUpgrade(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard card : p.drawPile.group) {
            if(card.type == CardType.ATTACK || card.type == CardType.SKILL)
                this.addToBot(
                    new AbstractGameAction() {
                        public void update() {
                            AbstractDungeon.player.drawPile.addToRandomSpot(card.makeStatEquivalentCopy());
                            this.isDone = true;
                        }
                    }
                );

        }
    }

}
