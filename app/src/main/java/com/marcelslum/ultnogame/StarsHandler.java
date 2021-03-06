package com.marcelslum.ultnogame;

import java.text.NumberFormat;

/**
 * Created by marcel on 26/01/2017.
 */

public class StarsHandler {

    static int conqueredStarsTotal;
    static int newStars;
    static int previousStars;

    public static int updateConqueredStars(){


        int numberOfStars = 0;
        for (int i = 0; i < Level.NUMBER_OF_LEVELS; i++){
            numberOfStars += SaveGame.saveGame.levelsStars[i];
        }

        conqueredStarsTotal = numberOfStars;
        if (MessagesHandler.messageConqueredStarsTotal != null) {
            MessagesHandler.messageConqueredStarsTotal.setText(Game.getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                    "\u0020" + NumberFormat.getInstance().format(conqueredStarsTotal));
        }
        return numberOfStars;
    }
}
