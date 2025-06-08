package plagued.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DamageLastTakenField {

    @SpirePatch(
            clz= AbstractPlayer.class,
            method=SpirePatch.CLASS
    )
    public static class damageLastTaken
    {
        public static SpireField<DamageInfo> damage = new SpireField<>(() -> new DamageInfo(null, 0));
        public static SpireField<Integer> multiAttack = new SpireField<>(() -> 0);
    }

    @SpirePatch(
            clz= AbstractMonster.class,
            method=SpirePatch.CLASS
    )
    public static class multiAttack
    {
        public static SpireField<Integer> monsterMultiAttack = new SpireField<>(() -> 0);
    }
}
