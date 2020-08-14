package com.lieying.lydemo3.bean;

import java.util.ArrayList;

public class Data {
    SpellProducts spellProducts;
    ArrayList<BaseBean> spellGroups;

    public SpellProducts getProducts() {
        return spellProducts;
    }

    public void setProducts(SpellProducts spellProducts) {
        this.spellProducts = spellProducts;
    }

    public ArrayList<BaseBean> getSpellGroups() {
        return spellGroups;
    }

    public void setSpellGroups(ArrayList<BaseBean> spellGroups) {
        this.spellGroups = spellGroups;
    }

    @Override
    public String toString() {
        return "Data{" +
                "products=" + spellProducts +
                ", spellGroups=" + spellGroups +
                '}';
    }
}
