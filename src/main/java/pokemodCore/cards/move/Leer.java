package pokemodCore.cards.move;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.PowerDebuffEffect;
import pokemodCore.patches.AbstractCardEnum;
import pokemodCore.patches.PokemodCardTags;

public class Leer extends PokeMove {
    public static final String ID = "pokemod:leer";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "image/cards/leer/leer.png";
    private static final int COST = 1;
    private static final int VULNERABLE_AMOUNT = 1;
    private static final int VULNERABLE_AMOUNT_UPGRADE = 1;
    private static final int MAX_UPGRADES = 3;

    public Leer() {
        this (0);
    }

    public Leer(int upgrades) {
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
        this.tags.add(PokemodCardTags.TYPE_NORMAL);
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
            upgradeMagicNumber(VULNERABLE_AMOUNT_UPGRADE);
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
                        new VulnerablePower(abstractPlayer, this.magicNumber, false)
                )
        );
    }
}
