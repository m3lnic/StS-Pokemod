package pokemodCore.characters;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import pokemodCore.cards.energy.*;
import pokemodCore.cards.pokemon.Bulbasaur;
import pokemodCore.cards.pokemon.Charmander;
import pokemodCore.cards.pokemon.Squirtle;
import pokemodCore.cards.skill.RareCandy;
import pokemodCore.patches.AbstractCardEnum;
import pokemodCore.patches.PokemodEnum;
import pokemodCore.relics.Starterball;

import java.util.ArrayList;

public class TrainerOne extends CustomPlayer {
    public static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("pokemod:TrainerOne");
    public static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
    public static final int STARTING_HP = 80;
    public static final int MAX_HP = 80;
    public static final int ORB_SLOTS = 0;
    public static final int STARTING_GOLD = 99;
    public static final int HAND_SIZE = 8;
    public static final int ASCENSION_MAX_HP_LOSS = 5;
    public static final String TRAINER_1_SHOULDER_2 = "image/character/trainer_1/shoulder.png"; // campfire pose
    public static final String TRAINER_1_SHOULDER_1 = "image/character/trainer_1/shoulder.png"; // another campfire pose
    public static final String TRAINER_1_CORPSE = "image/character/trainer_1/corpse.png"; // dead corpse
    public static final String TRAINER_1_SPRITER = "image/character/trainer_1/Trainer_1.scml";

    public TrainerOne (String name) {
        super (name, PokemodEnum.POKEMOD_PLAYER_CLASS, null, null, null, new SpriterAnimation(TRAINER_1_SPRITER));
        initializeClass(
                null,
                TRAINER_1_SHOULDER_2,
                TRAINER_1_SHOULDER_1,
                TRAINER_1_CORPSE,
                getLoadout(),
                20.0f,
                -10.0f,
                220.0f,
                290.0f,
                new EnergyManager(ENERGY_PER_TURN)
        );
        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Bulbasaur.ID);
        retVal.add(Charmander.ID);
        retVal.add(Squirtle.ID);
        retVal.add(RareCandy.ID);
        retVal.add(RareCandy.ID);
        retVal.add(RareCandy.ID);
        retVal.add(RareCandy.ID);
        retVal.add(RareCandy.ID);
        retVal.add(RareCandy.ID);
        retVal.add(RareCandy.ID);
        retVal.add(RareCandy.ID);
        retVal.add(RareCandy.ID);
        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Starterball.ID);
        UnlockTracker.markRelicAsSeen(Starterball.ID);
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(
                "Trainer One",
                "Be the best around and catch them all!",
                STARTING_HP,
                MAX_HP,
                ORB_SLOTS,
                STARTING_GOLD,
                HAND_SIZE,
                this,
                getStartingRelics(),
                getStartingDeck(),
                false
        );
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return characterStrings.TEXT[1];
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.POKEMOD_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        return CardHelper.getColor(100.0f, 50.0f, 50.0f);
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Bulbasaur();
    }

    @Override
    public Color getCardTrailColor() {
        return CardHelper.getColor(100.0f, 50.0f, 50.0f);
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return ASCENSION_MAX_HP_LOSS;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontPurple;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playV("AUTOMATON_ORB_SPAWN", 1.75f);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "AUTOMATON_ORB_SPAWN";
    }

    @Override
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TrainerOne(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return characterStrings.TEXT[2];
    }

    @Override
    public Color getSlashAttackColor() {
        return CardHelper.getColor(100.0f, 50.0f, 50.0f);
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL,
                AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL
        };
    }

    @Override
    public String getVampireText() {
        return characterStrings.TEXT[3];
    }
}
