package pokemodCore;

import basemod.BaseMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pokemodCore.cards.move.PokeMove;
import pokemodCore.cards.pokemon.PokeCard;

import java.util.ArrayList;

public class Utils {
    public static boolean removeCardFromHand(AbstractCard cardToRemove) {
        ArrayList<AbstractCard> cardsToRemove = getCardsByUUID(AbstractDungeon.player.hand.group, cardToRemove);
        if (cardsToRemove.size() > 0) {
            AbstractDungeon.player.hand.removeCard(cardsToRemove.get(0));
            AbstractDungeon.player.hand.refreshHandLayout();
            return true;
        }
        return false;
    }

    public static boolean removeCardFromDiscardPile(AbstractCard cardToRemove) {
        ArrayList<AbstractCard> cardsToRemove = getCardsByUUID(AbstractDungeon.player.discardPile.group, cardToRemove);
        if (cardsToRemove.size() > 0) {
            AbstractDungeon.player.discardPile.removeCard(cardsToRemove.get(0));
            AbstractDungeon.player.discardPile.refreshHandLayout();
            return true;
        }
        return false;
    }

    public static boolean removeCardFromDrawPile(AbstractCard cardToRemove) {
        ArrayList<AbstractCard> cardsToRemove = getCardsByUUID(AbstractDungeon.player.drawPile.group, cardToRemove);
        if (cardsToRemove.size() > 0) {
            AbstractDungeon.player.drawPile.removeCard(cardsToRemove.get(0));
            AbstractDungeon.player.drawPile.refreshHandLayout();
            return true;
        }
        return false;
    }

    public static boolean removeCardFromMasterDeck(AbstractCard cardToRemove) {
        ArrayList<AbstractCard> cardsToRemove = getCardsByUUID(AbstractDungeon.player.masterDeck.group, cardToRemove);
        if (cardsToRemove.size() > 0) {
            AbstractDungeon.player.masterDeck.removeCard(cardsToRemove.get(0));
            AbstractDungeon.player.masterDeck.refreshHandLayout();
            return true;
        }
        return false;
    }

    public static boolean removeCardFromExhaustPile(AbstractCard cardToRemove) {
        ArrayList<AbstractCard> cardsToRemove = getCardsByUUID(AbstractDungeon.player.exhaustPile.group, cardToRemove);
        if (cardsToRemove.size() > 0) {
            AbstractDungeon.player.exhaustPile.removeCard(cardsToRemove.get(0));
            AbstractDungeon.player.exhaustPile.refreshHandLayout();
            return true;
        }
        return false;
    }

    public static void updateCardInCombat(AbstractCard cardToUpdate, CardGroup locationToAdd) {
        removeCardFromDiscardPile(cardToUpdate);
        removeCardFromHand(cardToUpdate);
        removeCardFromDrawPile(cardToUpdate);
        removeCardFromExhaustPile(cardToUpdate);
        locationToAdd.addToBottom(cardToUpdate);
        locationToAdd.refreshHandLayout();
    }

    public static ArrayList<AbstractCard> getCardsByUUID(ArrayList<AbstractCard> arrayToSearch, AbstractCard cardToGet) {
        ArrayList<AbstractCard> cardsToGet = new ArrayList<>();
        arrayToSearch.forEach(item -> {
            if (item.uuid.equals(cardToGet.uuid)) {
                cardsToGet.add(item);
            }
        });
        return cardsToGet;
    }

    public static ArrayList<AbstractCard> getCardsByTag(ArrayList<AbstractCard> arrayToSearch, AbstractCard.CardTags tagToSearchFor) {
        ArrayList<AbstractCard> cardsToGet = new ArrayList<>();
        arrayToSearch.forEach(item -> {
            if (item.hasTag(tagToSearchFor)) {
                cardsToGet.add(item);
            }
        });
        return cardsToGet;
    }

    public static ArrayList<AbstractCard> getPokemonThatCanLearnMove(ArrayList<AbstractCard> arrayToSearch, PokeMove cardToLearn) {
        ArrayList<AbstractCard> cardsToGet = new ArrayList<>();
        arrayToSearch.forEach(item -> {
            PokeCard cardInstance = (PokeCard) item;
            if (cardInstance.canLearnSkill(cardToLearn)) {
                cardsToGet.add(item);
            }
        });
        return cardsToGet;
    }

    public static void refreshHandOfAll() {
        AbstractDungeon.player.masterDeck.refreshHandLayout();
        AbstractDungeon.player.hand.refreshHandLayout();
        AbstractDungeon.player.discardPile.refreshHandLayout();
        AbstractDungeon.player.exhaustPile.refreshHandLayout();
        AbstractDungeon.player.drawPile.refreshHandLayout();
    }
}
