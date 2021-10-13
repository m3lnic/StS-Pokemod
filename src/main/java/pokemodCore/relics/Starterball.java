package pokemodCore.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import pokemodCore.cards.pokemon.Bulbasaur;

public class Starterball extends CustomRelic {
    public static final String ID = "pokemod:starterball";
    public static final String IMG = "image/relics/pokeball.png";
    public static final String OUTLINE_IMG = "image/relics/outline/pokeball.png";

    public Starterball () {
        super (
                ID,
                ImageMaster.loadImage(IMG),
                ImageMaster.loadImage(OUTLINE_IMG),
                RelicTier.STARTER,
                LandingSound.MAGICAL
        );
    }

    public String getUpdatedDescription () {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Starterball();
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.masterDeck.addToBottom(new Bulbasaur());
    }
}
