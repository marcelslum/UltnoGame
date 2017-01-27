package com.marcelslum.ultnogame;

import java.text.NumberFormat;

/**
 * Created by marcel on 26/01/2017.
 */

public class StarsHandle {


    public static int conqueredStarsTotal;
    static int newStars;
    static int previousStars;

    public static int updateConqueredStars(){
        int numberOfStars = 0;
        for (int i = 0; i < SaveGame.saveGame.maxNumberOfLevels; i++){
            numberOfStars += SaveGame.saveGame.starsLevels[i];
        }
        conqueredStarsTotal = numberOfStars;
        if (MessageHandle.messageConqueredStarsTotal != null) {
            MessageHandle.messageConqueredStarsTotal.setText(Game.getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                    "\u0020" + NumberFormat.getInstance().format(conqueredStarsTotal));
        }
        return numberOfStars;
    }
}
