package pokemodCore.cards.tm;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import pokemodCore.cards.move.Growl;
import pokemodCore.cards.move.Peck;
import pokemodCore.cards.move.PokeMove;
import pokemodCore.patches.AbstractCardEnum;

public class PeckTM extends PokeTM {
    public static final String ID = "pokemod:peck:tm";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "image/cards/peck/peck.png";
    public static final CardRarity CARD_RARITY = CardRarity.COMMON;
    public static final CardType CARD_TYPE = CardType.SKILL;
    public static final CardTarget CARD_TARGET = CardTarget.NONE;
    private static final int COST = 0;
    private static final PokeMove MOVE = new Peck();

    public PeckTM() {
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CARD_TYPE,
                AbstractCardEnum.POKEMOD_COLOR,
                CARD_RARITY,
                CARD_TARGET,
                MOVE
        );
    }

    @Override
    public AbstractCard makeCopy() {
        return new PeckTM();
    }
}
