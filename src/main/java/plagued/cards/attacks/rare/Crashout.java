package plagued.cards.attacks.rare;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.util.CardStats;

public class Crashout extends BaseCard {
    public static final String ID = makeID("Crashout");
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            0
    );
    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 5;
    public Crashout() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToBot(new VFXAction(new ThrowDaggerEffect(m.hb.cX, m.hb.cY)));
        }
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));

        if(EnergyPanel.getCurrentEnergy() > 0) {
            int dupeAmount = EnergyPanel.getCurrentEnergy();
            if (p.hasRelic(ChemicalX.ID)) {
                dupeAmount += 2;
            }

            p.energy.use(EnergyPanel.getCurrentEnergy());
            AbstractCard card = this;
            this.addToBot(new MakeTempCardInDrawPileAction(card, dupeAmount, true, true));
        }
    }
}
