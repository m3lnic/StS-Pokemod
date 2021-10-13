package pokemodCore.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PokemodEnum {
    @SpireEnum
    public static AbstractPlayer.PlayerClass POKEMOD_PLAYER_CLASS;

    @SpireEnum
    public static AbstractDungeon.CurrentScreen POKEMOD_STARTER_SELECT_SCREEN;
}
