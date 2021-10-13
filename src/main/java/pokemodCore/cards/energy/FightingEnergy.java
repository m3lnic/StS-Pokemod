package pokemodCore.cards.energy;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import pokemodCore.enums.ENERGY_TYPE_ENUM;
import pokemodCore.patches.AbstractCardEnum;

public class FightingEnergy extends EnergyBase {
    public static final String ID = "pokemod:fighting_energy";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "image/cards/fighting_energy/fighting_energy.png";
    private static final int COST = 1;
    private static final ENERGY_TYPE_ENUM ENERGY_TYPE = ENERGY_TYPE_ENUM.FIGHTING;
    private static final int RESOURCE_ON_USE = 1;
    private static final int UPGRADE_RESOURCE_ON_USE_GAIN = 1;

    public FightingEnergy() {
        super (
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.POKEMOD_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.NONE,
                ENERGY_TYPE,
                RESOURCE_ON_USE
        );
        this.magicNumber = this.baseMagicNumber = RESOURCE_ON_USE;
    }

    @Override
    public void upgrade() {
        if (this.timesUpgraded <= 4) {
            upgradeMagicNumber(UPGRADE_RESOURCE_ON_USE_GAIN);
            upgradeName();
        }
    }
}
