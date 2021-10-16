package pokemodCore.cards.energy;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pokemodCore.PokemodCore;
import pokemodCore.enums.ENERGY_TYPE_ENUM;
import pokemodCore.patches.PokemodCardTags;

public abstract class EnergyBase extends CustomCard {
    private ENERGY_TYPE_ENUM energy_type;
    private int resource_on_use;

    public EnergyBase(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, ENERGY_TYPE_ENUM energy_typeENUM, int energy_modify_amount) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.energy_type = energy_typeENUM;
        this.resource_on_use = energy_modify_amount;
        this.tags.add(PokemodCardTags.ENERGY);
        this.tags.add(PokemodCardTags.PRODUCES_ENERGY);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        PokemodCore.addResouce(energy_type, this.magicNumber);
    }
}
