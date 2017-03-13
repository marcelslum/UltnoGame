package com.marcelslum.ultnogame;

import java.text.NumberFormat;

/**
 * Created by marcel on 26/01/2017.
 */

public class StarsHandler {


    public static int conqueredStarsTotal;
    static int newStars;
    static int previousStars;

    public static int updateConqueredStars(){
        int numberOfStars = 0;
        for (int i = 0; i < Level.maxNumberOfLevels; i++){
            numberOfStars += SaveGame.saveGame.starsLevels[i];
        }

        for (int i = 0; i < Level.numberOfSecretLevels; i++){
            numberOfStars += SaveGame.saveGame.starsSecretLevels[i];
        }

        conqueredStarsTotal = numberOfStars;
        if (MessagesHandler.messageConqueredStarsTotal != null) {
            MessagesHandler.messageConqueredStarsTotal.setText(Game.getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                    "\u0020" + NumberFormat.getInstance().format(conqueredStarsTotal));
        }
        return numberOfStars;
    }
}
