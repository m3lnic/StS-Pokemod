package pokemodCore.cards.move;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pokemodCore.patches.AbstractCardEnum;
import pokemodCore.patches.PokemodCardTags;

public class Scratch extends PokeMove {
    public static final String ID = "pokemod:scratch";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "image/cards/scratch/scratch.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 5;
    private static final int ATTACK_DMG_INCREASE = 3;
    private static final int MAX_UPGRADES = 3;

    public Scratch() {
        this(0);
    }

    public Scratch(int upgrades) {
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
        this.damage = this.baseDamage = ATTACK_DMG;
        this.timesUpgraded = upgrades;
        this.tags.add(PokemodCardTags.TYPE_NORMAL);
    }

    @Override
    public boolean canUpgrade() {
        return timesUpgraded < MAX_UPGRADES;
    }


    @Override
    public void upgrade() {
        if (this.timesUpgraded < MAX_UPGRADES) {
            upgradeName();
            upgradeDamage(ATTACK_DMG_INCREASE);
            ++this.timesUpgraded;
            this.upgraded = true;
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(
                new com.megacrit.cardcrawl.actions.common.DamageAction(
                        abstractMonster,
                        new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_DIAGONAL
                )
        );
    }
}
