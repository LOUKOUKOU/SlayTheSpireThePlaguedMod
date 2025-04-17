package plagued.cards.attacks.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import plagued.actions.ApplyCrippleAction;
import plagued.cards.BaseCard;
import plagued.character.ThePlaguedCharacter;
import plagued.powers.CrippledPower;
import plagued.powers.services.ApplyPowerService;
import plagued.util.CardStats;

public class FlyingKick extends BaseCard {
    public static final String ID = makeID("FlyingKick");
    private static final CardStats info = new CardStats(
            ThePlaguedCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;
    private static final int GAIN_ENERGY = 1;
    public FlyingKick() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        setMagic(GAIN_ENERGY);
        setCustomVar("debuffAmount", 1, 0);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        p.gainEnergy(magicNumber);
        this.addToBot(new ApplyCrippleAction(ApplyPowerService.POWER_TYPE.FRAIL, p, "Off balance"));
    }
}
