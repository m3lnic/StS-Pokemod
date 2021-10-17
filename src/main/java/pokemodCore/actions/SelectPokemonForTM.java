package pokemodCore.actions;

import basemod.helpers.ModalChoice;
import basemod.helpers.ModalChoiceBuilder;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pokemodCore.Utils;
import pokemodCore.cards.move.PokeMove;
import pokemodCore.cards.pokemon.PokeCard;
import pokemodCore.cards.tm.PokeTM;

import java.util.ArrayList;

public class SelectPokemonForTM extends AbstractGameAction implements ModalChoice.Callback {
    private ModalChoice modalChoice;
    private PokeTM newCard;
    private PokeCard cardReplacingSkillsOf;
    ArrayList<AbstractCard> pokemonArray;

    public SelectPokemonForTM(PokeTM newCard, ArrayList<AbstractCard> pokemonArray) {
        this.newCard = newCard;
        this.pokemonArray = pokemonArray;

        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        pokemonArray.forEach(item -> {
            PokeCard pokeCast = (PokeCard) item;
            if (pokeCast.canEvolve()) {
                item.initializeDescription();
                tmp.addToTop(item);
            }
        });
        AbstractDungeon.gridSelectScreen.open(
                tmp,
                tmp.size(),
                false,
                "Select a pokemon to learn " + this.newCard.getMoveName()
        );
    }

    public void gridSelectHandler() {
        cardReplacingSkillsOf = (PokeCard) AbstractDungeon.gridSelectScreen.selectedCards.get(0);
        AbstractDungeon.gridSelectScreen.selectedCards.clear();

        if (cardReplacingSkillsOf.moves.size() < 4) {
            cardReplacingSkillsOf.addNewMove(this.newCard.getMoveCard());
        } else {
            ModalChoiceBuilder choiceBuilder = new ModalChoiceBuilder().setCallback(this);
            cardReplacingSkillsOf.moves.forEach(item -> choiceBuilder.addOption(item));
            choiceBuilder.addOption(newCard);
            this.modalChoice = choiceBuilder.create();
            this.modalChoice.generateTooltips();
        }
        Utils.updateCardInCombat(cardReplacingSkillsOf, AbstractDungeon.player.drawPile);
    }

    @Override
    public void optionSelected(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster, int i) {
        if (i <= this.pokemonArray.size() - 1) {
            cardReplacingSkillsOf.addNewMove(this.newCard.getMoveCard(), i);
        }
    }

    @Override
    public void update() {
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            this.gridSelectHandler();
        }
        tickDuration();
    }
}
