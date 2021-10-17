package pokemodCore.cards.move;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.SearingBlow;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import pokemodCore.patches.AbstractCardEnum;
import pokemodCore.patches.PokemodCardTags;

public class Peck extends PokeMove {
    public static final String ID = "pokemod:peck";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "image/cards/peck/peck.png";
    private static final int COST = 1;
    private static final int REPEAT_AMOUNT = 3;
    private static final int DAMAGE_AMOUNT = 2;
    private static final int DAMAGE_AMOUNT_GAIN = 1;
    private static final int MAX_UPGRADES = 3;

    public Peck() {
        this(0);
    }

    public Peck(int upgrades) {
        super (
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.POKEMOD_COLOR,
                CardRarity.BASIC,
                CardTarget.ENEMY,
                PokemodCardTags.TYPE_FLYING
        );
        this.magicNumber = this.baseMagicNumber = REPEAT_AMOUNT;
        this.damage = this.baseDamage = DAMAGE_AMOUNT;
        this.timesUpgraded = upgrades;
    }

    @Override
    public void upgrade() {
        if (this.timesUpgraded < MAX_UPGRADES) {
            upgradeName();
            upgradeDamage(DAMAGE_AMOUNT_GAIN);
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
        for (int i = 0; i < this.magicNumber; i++) {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(
                            abstractMonster,
                            new DamageInfo(abstractMonster, this.damage, damageTypeForTurn),
                            AbstractGameAction.AttackEffect.SLASH_DIAGONAL
                    )
            );
        }
    }
}
