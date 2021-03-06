package pokemodCore.cards.pokemon;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import pokemodCore.cards.move.*;
import pokemodCore.patches.AbstractCardEnum;
import pokemodCore.patches.PokemodCardTags;

import java.util.ArrayList;
import java.util.Arrays;

@AbstractCardModifier.SaveIgnore
public class Ivysaur extends PokeCard {
    public static final String ID = "pokemod:ivysaur";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "image/cards/ivysaur/ivysaur.png";
    private static final int COST = 0;

    public Ivysaur () {
        this(new ArrayList<PokeMove>());
    }

    public Ivysaur (ArrayList<PokeMove> moves) {
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
        return new Ivysaur(cardCopies);
    }

    @Override
    public PokeCard evolution() {
        return new Venusaur(this.moves);
    }
}
