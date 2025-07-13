package plagued.cards.attacks.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.RaptorBoostPower;
import plagued.util.CardStats;

public class FalconPunch extends BaseCard {
    public static final String ID = makeID("FalconPunch");
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );
    private static final int DAMAGE = 14;
    private static final int RAPTOR_BOOST = 2;
    private static final int RAPTOR_BOOST_UPGRADE = 1;
    private static final int RAPTOR_BOOST_DAMAGE = 4;
    public FalconPunch() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setDamage(DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        setMagic(RAPTOR_BOOST, RAPTOR_BOOST_UPGRADE); //Sets the card's damage and how much it changes when upgraded.
        setCustomVar("raptorDamage", VariableType.DAMAGE, RAPTOR_BOOST_DAMAGE * RAPTOR_BOOST, RAPTOR_BOOST_DAMAGE * (RAPTOR_BOOST + RAPTOR_BOOST_UPGRADE));
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.addToBot(
            new ApplyPowerAction(
                p,
                p,
                new RaptorBoostPower(p, magicNumber)
            )
        );
    }
}
