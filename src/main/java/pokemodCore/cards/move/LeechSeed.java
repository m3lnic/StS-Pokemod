package pokemodCore.cards.move;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import pokemodCore.patches.AbstractCardEnum;
import pokemodCore.patches.PokemodCardTags;

public class LeechSeed extends PokeMove {
    public static final String ID = "pokemod:leech_seed";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "image/cards/leech_seed/leech_seed.png";
    private static final int COST = 1;
    private static final int SIPHON_AMOUNT = 4;
    private static final int SIPHON_AMOUNT_GAIN = 3;
    private static final int MAX_UPGRADES = 3;

    public LeechSeed() {
        this(0);
    }

    public LeechSeed(int upgrades) {
        super (
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.POKEMOD_COLOR,
                CardRarity.RARE,
                CardTarget.ENEMY
        );
        this.magicNumber = this.baseMagicNumber = SIPHON_AMOUNT;
        this.timesUpgraded = upgrades;
        this.tags.add(PokemodCardTags.TYPE_GRASS);
    }

    @Override
    public boolean canUpgrade() {
        return timesUpgraded < MAX_UPGRADES;
    }

    @Override
    public void upgrade() {
        if (this.timesUpgraded < MAX_UPGRADES) {
            upgradeName();
            upgradeMagicNumber(SIPHON_AMOUNT_GAIN);
            ++this.timesUpgraded;
            this.upgraded = true;
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(
                new LoseHPAction(
                        abstractMonster,
                        abstractPlayer,
                        this.magicNumber,
                        AbstractGameAction.AttackEffect.POISON
                )
        );
        AbstractDungeon.actionManager.addToBottom(
                new AddTemporaryHPAction(abstractPlayer, abstractPlayer, this.magicNumber)
        );
    }
}
