package pokemodCore.cards.pokemon;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.relics.WingBoots;
import pokemodCore.cards.move.*;
import pokemodCore.patches.AbstractCardEnum;
import pokemodCore.patches.PokemodCardTags;

import java.util.ArrayList;
import java.util.Arrays;

@AbstractCardModifier.SaveIgnore
public class Venusaur extends PokeCard {
    public static final String ID = "pokemod:venusaur";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "image/cards/venusaur/venusaur.png";
    private static final int COST = 0;

    public Venusaur () {
        this(new ArrayList<PokeMove>());
    }

    public Venusaur (ArrayList<PokeMove> moves) {
        super (
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.POKEMOD_COLOR,
                CardRarity.SPECIAL,
                PokemodCardTags.TYPE_GRASS,
                PokemodCardTags.TYPE_POISON
        );
        this.moves = new ArrayList<PokeMove>(moves);
        this.generateDescriptionAndModal();
    }

    @Override
    public AbstractCard makeCopy() {
        ArrayList<PokeMove> cardCopies = new ArrayList<>();
        this.moves.forEach(item -> cardCopies.add((PokeMove) item.makeStatEquivalentCopy()));
        return new Venusaur(cardCopies);
    }

    @Override
    public PokeCard evolution() {
        return null;
    }
}
