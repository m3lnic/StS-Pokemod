package pokemodCore.cards.tm;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pokemodCore.Utils;
import pokemodCore.actions.SelectPokemonForTM;
import pokemodCore.cards.move.PokeMove;
import pokemodCore.cards.pokemon.PokeCard;
import pokemodCore.patches.PokemodCardTags;

import java.util.ArrayList;

public abstract class PokeTM extends AbstractCard {
    private PokeMove move;

    public PokeTM(String id, String name, String imgUrl, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, PokeMove move) {
        super(id, name, imgUrl, cost, rawDescription, type, color, rarity, target);
        this.move = move;
        this.tags.add(PokemodCardTags.TM);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> masterDeckPokemon = Utils.getCardsByTag(AbstractDungeon.player.masterDeck.group, PokemodCardTags.POKEMON);
        if (masterDeckPokemon.size() > 0) {
            ArrayList<AbstractCard> pokemonThatCanLearnMove = Utils.getPokemonThatCanLearnMove(masterDeckPokemon, this.move);
            if (pokemonThatCanLearnMove.size() > 0) {
                AbstractDungeon.actionManager.addToBottom(new SelectPokemonForTM(this, pokemonThatCanLearnMove));
            }
        }
    }

    public String getMoveName () {
        return this.move.name;
    }

    public PokeMove getMoveCard() {
        return (PokeMove) this.move.makeCopy();
    }
}
