package plagued.cards.attacks.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import plagued.actions.DuplicateAction;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.util.CardStats;

public class Assault extends BaseCard {
    public static final String ID = makeID("Assault");
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            0
    );
    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 5;
    public Assault() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToBot(new VFXAction(new ThrowDaggerEffect(m.hb.cX, m.hb.cY)));
        }
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        // TODO Energy left
        if(p.energy.energyMaster > 0) {
            int dupeAmount = p.energy.energy;
            if (p.hasRelic(ChemicalX.ID)) {
                dupeAmount += 2;
            }

            p.energy.use(p.energy.energy);
            AbstractCard card = this;
            for (int i = 0; i < dupeAmount; i++) {
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
}
