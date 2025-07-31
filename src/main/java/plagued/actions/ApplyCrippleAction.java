package plagued.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import plagued.powers.CrippledPower;
import plagued.powers.services.ApplyPowerService;
import plagued.relics.boss.MechanicalWing;

public class ApplyCrippleAction extends AbstractGameAction {
    private int amount = 1;
    private String name = "";
    private final AbstractPlayer p;
    private final ApplyPowerService.POWER_TYPE debuffType;
    private boolean applyThisTurn = false;
    private String description = null;

    public ApplyCrippleAction(ApplyPowerService.POWER_TYPE debuffType, AbstractPlayer player, String debuffName, int amount) {
        this.amount = amount;
        this.name = debuffName;
        this.p = player;
        this.debuffType = debuffType;
    }

    public ApplyCrippleAction(ApplyPowerService.POWER_TYPE debuffType, AbstractPlayer player, String debuffName, String description, int amount) {
        this.amount = amount;
        this.name = debuffName;
        this.p = player;
        this.debuffType = debuffType;
        this.description = description;
    }

    public ApplyCrippleAction(ApplyPowerService.POWER_TYPE debuffType, AbstractPlayer player, String debuffName) {
        this.name = debuffName;
        this.p = player;
        this.debuffType = debuffType;
    }

    public ApplyCrippleAction(ApplyPowerService.POWER_TYPE debuffType, AbstractPlayer player, String debuffName, int amount, boolean thisTurn) {
        this.amount = amount;
        this.name = debuffName;
        this.p = player;
        this.debuffType = debuffType;
        this.applyThisTurn = thisTurn;
    }

    public ApplyCrippleAction(ApplyPowerService.POWER_TYPE debuffType, AbstractPlayer player, String debuffName, boolean thisTurn) {
        this.name = debuffName;
        this.p = player;
        this.debuffType = debuffType;
        this.applyThisTurn = thisTurn;
    }



    @Override
    public void update() {
        if (!AbstractDungeon.player.hasRelic(MechanicalWing.ID)) {
            if (this.applyThisTurn) {
                this.addToBot(CrippledPower.getPower(this.debuffType, p, this.amount));
            } else {
                if (this.description == null)
                    this.addToBot(new ApplyPowerAction(p, p, new CrippledPower(p, this.name, this.debuffType, this.amount)));
                else
                    this.addToBot(new ApplyPowerAction(p, p, new CrippledPower(p, this.name, this.description, this.debuffType, this.amount)));
            }
        }
        this.isDone = true;
    }
}
