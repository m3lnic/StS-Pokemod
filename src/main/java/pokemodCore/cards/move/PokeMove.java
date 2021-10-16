package pokemodCore.cards.move;

import basemod.abstracts.CustomCard;
import pokemodCore.patches.PokemodCardTags;

import javax.smartcardio.Card;

public abstract class PokeMove extends CustomCard {
    private CardTags primaryType;

    public PokeMove(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, CardTags primaryType) {
        super(id, name, img, cost, rawDescription, type, color, CardRarity.SPECIAL, target);
        this.setDisplayRarity(rarity);
        this.primaryType = primaryType;
        this.tags.add(PokemodCardTags.MOVE);
        this.tags.add(primaryType);
    }

    public CardTags getPrimaryType() {
        return primaryType;
    }
}
