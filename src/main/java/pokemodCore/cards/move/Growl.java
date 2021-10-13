package pokemodCore.cards.move;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.SearingBlow;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import pokemodCore.patches.AbstractCardEnum;

public class Growl extends PokeMove {
    public static final String ID = "pokemod:growl";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "image/cards/growl/growl.png";
    private static final int COST = 1;
    private static final int WEAK_AMOUNT = 1;
    private static final int MAX_UPGRADES = 3;

    public Growl() {
        this(0);
    }

    public Growl(int upgrades) {
        super (
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.POKEMOD_COLOR,
                CardRarity.BASIC,
                CardTarget.ENEMY
        );
        this.magicNumber = this.baseMagicNumber = WEAK_AMOUNT;
        this.timesUpgraded = upgrades;
    }

    @Override
    public boolean canUpgrade() {
        return timesUpgraded < MAX_UPGRADES;
    }

    @Override
    public void upgrade() {
        if (this.timesUpgraded < MAX_UPGRADES) {
            upgradeName();
            BaseMod.logger.info(this.timesUpgraded);
            upgradeMagicNumber(WEAK_AMOUNT);
            ++this.timesUpgraded;
            this.upgraded = true;
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(
                        abstractMonster,
                        abstractPlayer,
                        new WeakPower(abstractPlayer, this.magicNumber, false)
                )
        );
    }
}
