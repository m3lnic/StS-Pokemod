package pokemodCore.cards.move;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import pokemodCore.patches.AbstractCardEnum;

public class TailWhip extends PokeMove {
    public static final String ID = "pokemod:tail_whip";
    private static final int MAX_UPGRADES = 3;
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "image/cards/tail_whip/tail_whip.png";
    private static final int COST = 1;
    private static final int VULNERABLE_AMOUNT = 1;
    private static final int VULNERABLE_AMOUNT_GAIN = 1;

    public TailWhip() {
        this(0);
    }

    public TailWhip(int upgrades) {
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
        this.magicNumber = this.baseMagicNumber = VULNERABLE_AMOUNT;
        this.timesUpgraded = upgrades;
    }

    @Override
    public void upgrade() {
        if (this.timesUpgraded < MAX_UPGRADES) {
            upgradeName();
            upgradeMagicNumber(VULNERABLE_AMOUNT_GAIN);
            ++this.timesUpgraded;
            this.upgraded = true;
        }
    }

    @Override
    public boolean canUpgrade() {
        return timesUpgraded < MAX_UPGRADES;
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(
                        abstractMonster,
                        abstractPlayer,
                        new VulnerablePower(abstractPlayer, this.magicNumber, false)
                )
        );
        AbstractDungeon.actionManager.addToBottom(
                new AddTemporaryHPAction(abstractPlayer, abstractPlayer, this.magicNumber)
        );
    }
}
