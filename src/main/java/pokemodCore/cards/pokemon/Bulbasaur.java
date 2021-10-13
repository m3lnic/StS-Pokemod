package pokemodCore.cards.pokemon;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import pokemodCore.cards.move.*;
import pokemodCore.patches.AbstractCardEnum;

import java.util.ArrayList;
import java.util.Arrays;

@AbstractCardModifier.SaveIgnore
public class Bulbasaur extends PokeCard {
    public static final String ID = "pokemod:bulbasaur";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "image/cards/bulbasaur/bulbasaur.png";
    private static final int COST = 0;

    public Bulbasaur () {
        this(new ArrayList<PokeMove>(
                Arrays.asList(new Tackle(), new LeechSeed(), new Growl())
        ));
    }

    public Bulbasaur (ArrayList<PokeMove> moves) {
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
        this.generateDescriptionAndModal();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Bulbasaur(new ArrayList<>(this.moves));
    }

    @Override
    public PokeCard evolution() {
        return new Ivysaur(this.moves);
    }
}