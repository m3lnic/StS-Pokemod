package pokemodCore.cards.move;

import basemod.abstracts.CustomCard;

import javax.smartcardio.Card;

public abstract class PokeMove extends CustomCard {
    public PokeMove(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, CardRarity.SPECIAL, target);
        this.setDisplayRarity(rarity);
    }
}
