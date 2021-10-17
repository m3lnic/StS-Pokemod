package pokemodCore.cards.pokemon;

import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.abstracts.CustomCard;
import basemod.helpers.ModalChoice;
import basemod.helpers.ModalChoiceBuilder;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import com.megacrit.cardcrawl.shop.ShopScreen;
import pokemodCore.cards.move.PokeMove;
import pokemodCore.patches.PokemodCardTags;

import java.util.ArrayList;
import java.util.List;

@AbstractCardModifier.SaveIgnore
public abstract class PokeCard extends CustomCard implements ModalChoice.Callback {
    public ArrayList<PokeMove> moves = new ArrayList<>();
    private CardTags primaryType;
    private CardTags secondaryType;
    private ModalChoice modal;

    public CardTags getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(CardTags primaryType) {
        this.primaryType = primaryType;
    }

    public CardTags getSecondaryType() {
        return secondaryType;
    }

    public void setSecondaryType(CardTags secondaryType) {
        this.secondaryType = secondaryType;
    }

    public PokeCard(String id, String name, String imgUrl, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTags primaryType, CardTags secondaryType) {
        super(
                id,
                name,
                imgUrl,
                cost,
                rawDescription,
                type,
                color,
                rarity,
                CardTarget.NONE
        );
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
        this.tags.add(PokemodCardTags.POKEMON);
        this.tags.add(primaryType);
        this.tags.add(secondaryType);
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        return modal.generateTooltips();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        BaseMod.logger.info(moves.size());
        if (moves.size() > 0) {
            modal.open();
        }
    }

    @Override
    public void optionSelected(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster, int i) {
        PokeMove move = moves.get(i);
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(move, true));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            BaseMod.logger.info("PokeCard adding upgrades");
            upgradeName();
            this.upgraded = true;
            ++this.timesUpgraded;
            this.moves.forEach(item -> {
                BaseMod.logger.info("PokeCard adding upgrade to: " + item.name);
                item.upgrade();
            });
            AbstractDungeon.player.masterDeck.refreshHandLayout();
        }
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        return this.makeCopy();
    }

    public boolean canEvolve () {
        return this.evolution() != null;
    }

    public abstract PokeCard evolution();

    public void generateDescriptionAndModal() {
        ModalChoiceBuilder choiceBuilder = new ModalChoiceBuilder().setCallback(this);
        moves.forEach(item -> choiceBuilder.addOption(item));
        this.modal = choiceBuilder.create();
        this.modal.generateTooltips();
        this.initializeDescription();
    }

    public void addNewMove(PokeMove moveToLearn) {
        this.moves.add(moveToLearn);
        this.generateDescriptionAndModal();
    }

    public void addNewMove(PokeMove moveToLearn, int cardIndexToSwap) {
        this.moves.set(cardIndexToSwap, moveToLearn);
        this.generateDescriptionAndModal();
    }

    public boolean canLearnSkill(PokeMove card) {
        return card.getPrimaryType() == this.primaryType || card.getPrimaryType() == this.secondaryType || card.getPrimaryType() == PokemodCardTags.TYPE_NORMAL;
    }
}
