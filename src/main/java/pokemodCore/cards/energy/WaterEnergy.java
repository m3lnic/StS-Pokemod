package pokemodCore.cards.energy;

import basemod.BaseMod;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import pokemodCore.enums.ENERGY_TYPE_ENUM;
import pokemodCore.patches.AbstractCardEnum;
import pokemodCore.patches.PokemodCardTags;

public class WaterEnergy extends EnergyBase {
    public static final String ID = "pokemod:water_energy";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "image/cards/water_energy/water_energy.png";
    private static final int COST = 1;
    private static final ENERGY_TYPE_ENUM ENERGY_TYPE = ENERGY_TYPE_ENUM.WATER;
    private static final int RESOURCE_ON_USE = 1;
    private static final int UPGRADE_RESOURCE_ON_USE_GAIN = 1;

    public WaterEnergy() {
        super (
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.POKEMOD_COLOR,
                CardRarity.COMMON,
                CardTarget.NONE,
                ENERGY_TYPE,
                RESOURCE_ON_USE
        );
        this.magicNumber = this.baseMagicNumber = RESOURCE_ON_USE;
        this.tags.add(PokemodCardTags.TYPE_WATER);
    }

    @Override
    public void upgrade() {
        if (this.timesUpgraded <= 4) {
            upgradeMagicNumber(UPGRADE_RESOURCE_ON_USE_GAIN);
            upgradeName();
        }
    }
}
