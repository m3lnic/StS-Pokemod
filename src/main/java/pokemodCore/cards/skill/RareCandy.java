package pokemodCore.cards.skill;

import basemod.abstracts.CustomCard;
import basemod.helpers.ModalChoice;
import basemod.helpers.ModalChoiceBuilder;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pokemodCore.PokemodCore;
import pokemodCore.actions.EvolveScreenAction;
import pokemodCore.cards.move.PokeMove;
import pokemodCore.cards.pokemon.PokeCard;
import pokemodCore.patches.AbstractCardEnum;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RareCandy extends CustomCard implements ModalChoice.Callback {
    public static final String ID = "pokemod:rare_candy";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "image/cards/rare_candy/rare_candy.png";
    private static final int COST = 0;
    private ArrayList<PokeCard> pokemons;
    private ModalChoice modal;

    public RareCandy () {
        super (
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.POKEMOD_COLOR,
                CardRarity.RARE,
                CardTarget.NONE
        );
        pokemons = new ArrayList<>();
        FleetingField.fleeting.set(this, true);
    }

    @Override
    public void optionSelected(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster, int i) {
        PokeCard pokemon = pokemons.get(i);
        AbstractDungeon.player.masterDeck.removeCard(pokemon);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.player.masterDeck.group.forEach(item -> {
            if (item instanceof PokeCard) {
                if (((PokeCard) item).canEvolve())
                    pokemons.add((PokeCard) item);
            }
        });

        if (pokemons.size() > 0) {
            AbstractDungeon.actionManager.addToBottom(new EvolveScreenAction(pokemons));
        }

        AbstractDungeon.gridSelectScreen.selectedCards.clear();
    }
}
