package plagued.patches;

import com.badlogic.gdx.utils.Array;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;

public class LastAttackPatch {
    @SpirePatch(
            clz= AbstractPlayer.class,
            method="damage",
            paramtypez={
                    DamageInfo.class
            }
    )
    public static class damage
    {
        @SpirePrefixPatch
        public static void Prefix(AbstractPlayer __instance, DamageInfo info)
        {
            if(info.type == DamageInfo.DamageType.NORMAL && info.owner != null) {
                DamageLastTakenField.damageLastTaken.damage.set(__instance, info);
                DamageLastTakenField.multiAttack.monsterMultiAttack.set(info.owner, DamageLastTakenField.multiAttack.monsterMultiAttack.get(info.owner) + 1);
                DamageLastTakenField.damageLastTaken.multiAttack.set(__instance, DamageLastTakenField.multiAttack.monsterMultiAttack.get(info.owner));
            }
        }
    }

    @SpirePatch(
            clz= AbstractPlayer.class,
            method="onVictory",
            paramtypez={
            }
    )
    public static class victory
    {
        @SpirePrefixPatch
        public static void Prefix(AbstractPlayer __instance)
        {
            DamageLastTakenField.damageLastTaken.damage.set(__instance, new DamageInfo(null, 0));
            DamageLastTakenField.damageLastTaken.multiAttack.set(__instance, 0);
        }
    }

    @SpirePatch(
            clz= AbstractPlayer.class,
            method="updateInput",
            paramtypez={
            }
    )
    public static class endTurn
    {
        @SpirePostfixPatch
        public static void Postfix(AbstractPlayer __instance)
        {
            if(__instance.isEndingTurn) {
                MonsterGroup monsters = AbstractDungeon.getMonsters();
                for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                    DamageLastTakenField.multiAttack.monsterMultiAttack.set(monster, 0);
                }
            }
        }
    }
}
