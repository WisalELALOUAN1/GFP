package com.example.gfp.viewmodel;

import java.util.List;

/**
 * Représente une catégorie avec son emoji et la liste de ses options à afficher.
 */
public class CategoryDisplay {
    public final int categoryId;
    public final String categoryName;
    public final String emoji;
    public final List<OptionDisplay> options;

    public CategoryDisplay(int categoryId,
                           String categoryName,
                           String emoji,
                           List<OptionDisplay> options) {
        this.categoryId   = categoryId;
        this.categoryName = categoryName;
        this.emoji        = emoji;
        this.options      = options;
    }
}
