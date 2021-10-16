package pokemodCore.actions;

import basemod.helpers.ModalChoice;
import basemod.helpers.ModalChoiceBuilder;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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

    public SelectPokemonForTM(PokeTM newCard) {
        this.newCard = newCard;
    }

    public void gridSelectHandler() {
        cardReplacingSkillsOf = (PokeCard) AbstractDungeon.gridSelectScreen.selectedCards.get(0);
        AbstractDungeon.gridSelectScreen.selectedCards.clear();

        ModalChoiceBuilder choiceBuilder = new ModalChoiceBuilder().setCallback(this);
        cardReplacingSkillsOf.moves.forEach(item -> choiceBuilder.addOption(item));
        choiceBuilder.addOption(newCard);
        this.modalChoice = choiceBuilder.create();
        this.modalChoice.generateTooltips();
    }

    @Override
    public void optionSelected(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster, int i) {

    }

    @Override
    public void update() {
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            this.gridSelectHandler();
        }
        tickDuration();
    }
}
