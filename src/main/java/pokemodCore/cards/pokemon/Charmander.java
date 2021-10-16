package pokemodCore.cards.pokemon;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import pokemodCore.cards.move.*;
import pokemodCore.patches.AbstractCardEnum;
import pokemodCore.patches.PokemodCardTags;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@AbstractCardModifier.SaveIgnore
public class Charmander extends PokeCard {
    public static final String ID = "pokemod:charmander";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "image/cards/charmander/charmander.png";
    private static final int COST = 0;

    public Charmander () {
        this(new ArrayList<PokeMove>(
                Arrays.asList(new Tackle(), new Leer(), new Growl())
        ));
    }

    public Charmander (ArrayList<PokeMove> moves) {
        super (
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.POKEMOD_COLOR,
                CardRarity.SPECIAL
        );
        this.moves = new ArrayList<PokeMove>(moves);
        this.tags.add(PokemodCardTags.TYPE_FIRE);
        this.generateDescriptionAndModal();
    }

    @Override
    public AbstractCard makeCopy() {
        ArrayList<PokeMove> cardCopies = new ArrayList<>();
        this.moves.forEach(item -> cardCopies.add((PokeMove) item.makeStatEquivalentCopy()));
        return new Charmander(cardCopies);
    }

    @Override
    public PokeCard evolution() {
        return new Charmeleon(this.moves);
    }
}
