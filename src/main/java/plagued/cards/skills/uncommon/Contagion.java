package plagued.cards.skills.uncommon;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.cards.targeting.SelfOrEnemyTargeting;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.HomeostasisPower;
import plagued.util.CardStats;

public class Contagion extends BaseCard {
    public static final String ID = makeID("Contagion");
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    public Contagion() {
        super(ID, info);
    }

    @Override
    public void upgrade() {
        if(!upgraded) {
            this.target = CardTarget.ALL_ENEMY;
        }
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractPower power : p.powers) {
            AbstractPower copy = power;
            if (this.upgraded) {
                for (AbstractMonster monsterTemp : AbstractDungeon.getMonsters().monsters) {
                    this.addToBot(
                            new ApplyPowerAction(
                                    monsterTemp,
                                    p,
                                    power
                            )
                    );
                }
            } else {
                this.addToBot(
                    new ApplyPowerAction(
                        m,
                        p,
                        power
                    )
                );
            }
        }
    }
}
