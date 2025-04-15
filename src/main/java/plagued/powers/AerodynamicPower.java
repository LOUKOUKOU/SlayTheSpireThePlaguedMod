package plagued.powers;

import com.badlogic.gdx.utils.Array;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import jdk.internal.classfile.impl.AbstractDirectBuilder;
import plagued.actions.DiscountAction;

import static plagued.ThePlagued.makeID;

public class AerodynamicPower extends BasePower {
    public static final String POWER_ID = makeID("AerodynamicPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private int attackPlayedAmount = 0;
    private boolean isDoneThisTurn = false;

    public AerodynamicPower(AbstractCreature owner, int copiesPlayed) {
        super(POWER_ID, TYPE, TURN_BASED, owner, copiesPlayed);
        this.attackPlayedAmount += copiesPlayed;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (amount > 1 ? amount + " copies" : "a copy") + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();
        attackPlayedAmount = amount;
        isDoneThisTurn = false;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.attackPlayedAmount += stackAmount;
    }

    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if(!this.isDoneThisTurn && card.type == AbstractCard.CardType.ATTACK) {
            CardGroup tempAttacks = AbstractDungeon.player.drawPile.getAttacks();

            for (int i = 0; i < tempAttacks.size() && attackPlayedAmount != 0; i++) {
                AbstractCard tempCard = tempAttacks.group.get(i);
                if (tempCard.cardID.equals(card.cardID)) {
                    this.addToBot(
                        new AbstractGameAction() {
                            public void update() {
                                AbstractDungeon.player.drawPile.removeCard(tempCard);
                                AbstractDungeon.player.drawPile.addToTop(tempCard);
                                isDone = true;
                            }
                        }
                    );
                    this.addToBot(
                            new AbstractGameAction() {
                                public void update() {
                                    this.addToBot(new PlayTopCardAction(m, false));
                                    isDone = true;
                                }
                            }
                    );
                    attackPlayedAmount--;
                }
            }
            this.isDoneThisTurn = true;
        }
    }
}
