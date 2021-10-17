package pokemodCore;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;

import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import pokemodCore.cards.pokemon.Charmander;
import pokemodCore.characters.TrainerOne;
import pokemodCore.enums.ENERGY_TYPE_ENUM;
import pokemodCore.patches.*;
import pokemodCore.relics.Starterball;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

@SpireInitializer
public class PokemodCore implements EditCardsSubscriber, EditStringsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber, StartGameSubscriber {
    private static final String MOD_ID = "pokemodCore";
    private static final Color EXAMPLE_COLOR_BG = CardHelper.getColor(100.0f, 50.0f, 50.0f);
    private static final String ATTACK_EXAMPLE = "image/cards/bg_attack_default_gray_512.png";
    private static final String SKILL_EXAMPLE = "image/cards/bg_skill_default_gray_512.png";
    private static final String POWER_EXAMPLE = "image/cards/bg_power_default_gray_512.png";
    private static final String ENERGY_ORB_EXAMPLE = "image/cards/card_default_gray_orb_512.png";
    private static final String ATTACK_PORT_EXAMPLE = "image/cards/bg_attack_default_gray_1024.png";
    private static final String SKILL_PORT_EXAMPLE = "image/cards/bg_skill_default_gray_1024.png";
    private static final String POWER_PORT_EXAMPLE = "image/cards/bg_power_default_gray_1024.png";
    private static final String ENERGY_ORB_PORT_EXAMPLE = "image/cards/card_default_gray_orb_1024.png";
    private static final String ENERGY_ORB_CARD_EXAMPLE = "image/cards/card_ui_orb.png";

    private static HashMap<ENERGY_TYPE_ENUM, Integer> resources;

    public PokemodCore() {
        BaseMod.subscribe(this);
        BaseMod.addColor(
                // > It is a color addition part. Just enter the variables set above.
                AbstractCardEnum.POKEMOD_COLOR // > color
                , EXAMPLE_COLOR_BG // > bgColor
                , EXAMPLE_COLOR_BG // > backColor
                , EXAMPLE_COLOR_BG // > frameColor
                , EXAMPLE_COLOR_BG // > frameOutlineColor
                , EXAMPLE_COLOR_BG // > descBoxColor
                , EXAMPLE_COLOR_BG  // > trailVfColor
                , EXAMPLE_COLOR_BG // > glowColor
                , ATTACK_EXAMPLE // > attackBg
                , SKILL_EXAMPLE // > skillBg
                , POWER_EXAMPLE // > powerBG
                , ENERGY_ORB_EXAMPLE // > energyOrb
                , ATTACK_PORT_EXAMPLE // > attackBgPortrait
                , SKILL_PORT_EXAMPLE // > skillBgPortrait
                , POWER_PORT_EXAMPLE // > powerBgPortrait
                , ENERGY_ORB_PORT_EXAMPLE // > energyOrbPortrait
                , ENERGY_ORB_CARD_EXAMPLE // > CardEnergyOrb
        );
        BaseMod.logger.info("Pokemod is now loading");
        resources = new HashMap<ENERGY_TYPE_ENUM, Integer>() {{
            put(ENERGY_TYPE_ENUM.WATER, 0);
            put(ENERGY_TYPE_ENUM.GRASS, 0);
            put(ENERGY_TYPE_ENUM.ELECTRIC, 0);
            put(ENERGY_TYPE_ENUM.PSYCHIC, 0);
            put(ENERGY_TYPE_ENUM.FIGHTING, 0);
            put(ENERGY_TYPE_ENUM.FAIRY, 0);
            put(ENERGY_TYPE_ENUM.STEEL, 0);
            put(ENERGY_TYPE_ENUM.FIRE, 0);
            put(ENERGY_TYPE_ENUM.DARK, 0);
            put(ENERGY_TYPE_ENUM.NORMAL, 3);
        }};
    }

    public static void initialize () {
        PokemodCore pokemodCore = new PokemodCore();
    }

    @Override
    public void receiveEditCards() {
        BaseMod.logger.info("Pokemod is now adding cards");
        // > Pokemons
        BaseMod.addCard(new pokemodCore.cards.pokemon.Blastoise());
        BaseMod.addCard(new pokemodCore.cards.pokemon.Bulbasaur());
        BaseMod.addCard(new pokemodCore.cards.pokemon.Charizard());
        BaseMod.addCard(new pokemodCore.cards.pokemon.Charmander());
        BaseMod.addCard(new pokemodCore.cards.pokemon.Charmeleon());
        BaseMod.addCard(new pokemodCore.cards.pokemon.Ivysaur());
        BaseMod.addCard(new pokemodCore.cards.pokemon.Squirtle());
        BaseMod.addCard(new pokemodCore.cards.pokemon.Venusaur());
        BaseMod.addCard(new pokemodCore.cards.pokemon.Wartortle());

        // > Energies
        BaseMod.addCard(new pokemodCore.cards.energy.DarkEnergy());
        BaseMod.addCard(new pokemodCore.cards.energy.ElectricEnergy());
        BaseMod.addCard(new pokemodCore.cards.energy.FairyEnergy());
        BaseMod.addCard(new pokemodCore.cards.energy.FightingEnergy());
        BaseMod.addCard(new pokemodCore.cards.energy.FireEnergy());
        BaseMod.addCard(new pokemodCore.cards.energy.GrassEnergy());
        BaseMod.addCard(new pokemodCore.cards.energy.PsychicEnergy());
        BaseMod.addCard(new pokemodCore.cards.energy.SteelEnergy());
        BaseMod.addCard(new pokemodCore.cards.energy.WaterEnergy());

        // > Moves
        BaseMod.addCard(new pokemodCore.cards.move.Growl());
        BaseMod.addCard(new pokemodCore.cards.move.LeechSeed());
        BaseMod.addCard(new pokemodCore.cards.move.Leer());
        BaseMod.addCard(new pokemodCore.cards.move.Peck());
        BaseMod.addCard(new pokemodCore.cards.move.Tackle());
        BaseMod.addCard(new pokemodCore.cards.move.Scratch());
        BaseMod.addCard(new pokemodCore.cards.move.TailWhip());
        BaseMod.addCard(new pokemodCore.cards.move.ThrowRock());

        // > Skills
        BaseMod.addCard(new pokemodCore.cards.skill.RareCandy());

        // > TMs
        BaseMod.addCard(new pokemodCore.cards.tm.GrowlTM());
        BaseMod.addCard(new pokemodCore.cards.tm.LeechSeedTM());
        BaseMod.addCard(new pokemodCore.cards.tm.LeerTM());
        BaseMod.addCard(new pokemodCore.cards.tm.PeckTM());
        BaseMod.addCard(new pokemodCore.cards.tm.ScratchTM());
        BaseMod.addCard(new pokemodCore.cards.tm.TackleTM());
        BaseMod.addCard(new pokemodCore.cards.tm.TailWhipTM());
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.logger.info("--- Pokemod is now loading language packs ---");
        BaseMod.logger.info("localization/cards-" + Settings.language + ".json");
        BaseMod.loadCustomStringsFile(CardStrings.class, "localization/cards-" + Settings.language + ".json");

        BaseMod.logger.info("localization/relics-" + Settings.language + ".json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "localization/relics-" + Settings.language + ".json");

        BaseMod.logger.info("localization/characters-" + Settings.language + ".json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "localization/characters-" + Settings.language + ".json");
    }

    @Override
    public void receiveEditRelics () {
        BaseMod.logger.info("Pokemod is now adding relics");
        BaseMod.addRelicToCustomPool(
                new Starterball(),
                AbstractCardEnum.POKEMOD_COLOR
        );
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.logger.info("Pokemod is now adding characters");
        BaseMod.addCharacter(
                new TrainerOne("TrainerOne"),
                "image/character/trainer_1/select_button.png",
                "image/character/trainer_1/menu_background.png",
                PokemodEnum.POKEMOD_PLAYER_CLASS
        );
    }

    @Override
    public void receiveStartGame() {
        BaseMod.logger.info("Starting now");
    }

    public static String createModId(String itemName) {
        return MOD_ID + ":" + itemName;
    }

    public static void addResouce(ENERGY_TYPE_ENUM type, int amount) {
        int curResourceOfType = resources.get(type);
        resources.put(type, curResourceOfType + amount);
        BaseMod.logger.info(type.toString() + " has the new value: " + resources.get(type));
    }

    public static <newType, oldType> ArrayList<newType> castArrayList(ArrayList<oldType> list){
        ArrayList<newType> newlyCastedArrayList = new ArrayList<newType>();
        for(oldType listObject : list){
            newlyCastedArrayList.add((newType)listObject);
        }
        return newlyCastedArrayList;
    }
}
