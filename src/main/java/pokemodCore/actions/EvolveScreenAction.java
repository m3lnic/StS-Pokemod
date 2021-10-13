package pokemodCore.actions;

import basemod.BaseMod;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.blue.Claw;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import pokemodCore.cards.pokemon.PokeCard;

import java.util.ArrayList;

public class EvolveScreenAction extends AbstractGameAction {
    private AbstractPlayer player;
    private ArrayList<PokeCard> cards;

    public EvolveScreenAction (ArrayList<PokeCard> cardsToChooseFrom) {
        this.player = AbstractDungeon.player;
        setValues(this.player, AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.cards = cardsToChooseFrom;

        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        cards.forEach(item -> {
            if (item.canEvolve()) {
                item.initializeDescription();
                tmp.addToTop((AbstractCard) item);
            }
        });

        AbstractDungeon.gridSelectScreen.open(
                tmp,
                tmp.size(),
                false,
                "Choose a pokemon to evolve"
        );
    }

    @Override
    public void update() {
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            PokeCard c = (PokeCard) AbstractDungeon.gridSelectScreen.selectedCards.get(0);

            boolean removedDiscard = removeCardFromDiscardPile(c);
            boolean removedDraw = removeCardFromDrawPile(c);
            boolean removedHand = removeCardFromHand(c);
            BaseMod.logger.info("Discard: " + removedDiscard + "    Draw: " + removedDraw + "    Hand: " + removedHand);

            PokeCard newCard = c.evolution();
            AbstractDungeon.player.masterDeck.removeCard((AbstractCard) c);
            AbstractDungeon.player.masterDeck.addToBottom((AbstractCard) newCard);
            AbstractDungeon.player.drawPile.addToBottom((AbstractCard) newCard);
            AbstractDungeon.player.masterDeck.refreshHandLayout();
            AbstractDungeon.player.drawPile.refreshHandLayout();
            AbstractDungeon.player.discardPile.refreshHandLayout();
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
        tickDuration();
    }

    public boolean removeCardFromHand(AbstractCard cardToRemove) {
        ArrayList<AbstractCard> handCards = new ArrayList<>();
        AbstractDungeon.player.hand.group.forEach(item -> {
            if (cardToRemove.uuid.equals(item.uuid)) {
                handCards.add(item);
            }
        });
        BaseMod.logger.info(handCards.size());
        if (handCards.size() > 0) {
            BaseMod.logger.info("Removing card!");
            AbstractDungeon.player.hand.removeCard(handCards.get(0));
            AbstractDungeon.player.hand.refreshHandLayout();
            return true;
        }
        return false;
    }

    public boolean removeCardFromDiscardPile(AbstractCard cardToRemove) {
        ArrayList<AbstractCard> handCards = new ArrayList<>();
        AbstractDungeon.player.discardPile.group.forEach(item -> {
            if (cardToRemove.uuid.equals(item.uuid)) {
                handCards.add(item);
            }
        });
        BaseMod.logger.info(handCards.size());
        if (handCards.size() > 0) {
            AbstractDungeon.player.discardPile.removeCard(handCards.get(0));
            AbstractDungeon.player.discardPile.refreshHandLayout();
            return true;
        }
        return false;
    }

    public boolean removeCardFromDrawPile(AbstractCard cardToRemove) {
        ArrayList<AbstractCard> handCards = new ArrayList<>();
        AbstractDungeon.player.drawPile.group.forEach(item -> {
            if (cardToRemove.uuid.equals(item.uuid)) {
                handCards.add(item);
            }
        });
        BaseMod.logger.info(handCards.size());
        if (handCards.size() > 0) {
            AbstractDungeon.player.drawPile.removeCard(handCards.get(0));
            AbstractDungeon.player.drawPile.refreshHandLayout();
            return true;
        }
        return false;
    }
}
