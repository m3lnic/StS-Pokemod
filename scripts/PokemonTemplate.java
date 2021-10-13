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
public class {{CLASS_NAME}} extends PokeCard {
    public static final String ID = "{{MOD_ID}}";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "image/cards/{{PATH}}/{{PATH}}.png";
    private static final int COST = 0;
    private static final ArrayList<PokeMove> STARTER_MOVES = new ArrayList<PokeMove>();

    public {{CLASS_NAME}} () {
        super (
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.POKEMOD_COLOR,
                CardRarity.SPECIAL,
                STARTER_MOVES
        );
    }

    public {{CLASS_NAME}} (ArrayList<PokeMove> moves) {
        super (
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.POKEMOD_COLOR,
                CardRarity.SPECIAL,
                moves
        );
    }

    @Override
    public AbstractCard makeCopy() {
        return new {{CLASS_NAME}}(this.moves);
    }

    @Override
    public PokeCard evolution() {
        return {{EVOLUTION}};
    }
}
