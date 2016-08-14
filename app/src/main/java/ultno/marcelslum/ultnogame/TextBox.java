package ultno.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 07/08/2016.
 */
public class TextBox extends Entity{

    boolean displayArrow;
    TextImage background;
    ArrayList<Text> texts;
    float width;
    float height;
    float size;
    String text;
    float maxWidth;
    float padding;
    boolean isHaveArrow;
    Button arrowContinuar;
    float arrowStartX;
    float arrowStartY;
    float arrowFinishX;
    float arrowFinishY;

    TextBox(String name, Game game, float x, float y, float width, float size, String text) {
        super(name, game, x, y);
        this.width = width;
        this.size = size;
        this.text = text;
        this.texts = new ArrayList<>();

        Text textForMeasure = new Text("text", game, 0f, 0f, size, text, game.font);
        float widthOfText = textForMeasure.calculateWidth();

        if (widthOfText > this.width) {
            String [] splitedString = text.split(" ");
            String stringToTest = splitedString[0];

            Log.e("textBox", "splitedString lenght"+ splitedString.length);

            int elementToAdd = 1;
            Text lastText = new Text("text", game, 0f, 0f, size, ".", game.font);


            int limite = 500;
            int contador = 0;

            do {
                do {
                    contador += 1;
                    textForMeasure = new Text("text", game, 0f, 0f, size, stringToTest, game.font);

                    Log.e("textBox", "testando string "+ stringToTest);

                    widthOfText = textForMeasure.calculateWidth();

                    if (widthOfText > width) {
                        elementToAdd -= 1;
                        stringToTest = splitedString[elementToAdd];
                        elementToAdd += 1;
                        break;
                    }

                    lastText = textForMeasure;

                    if (elementToAdd > (splitedString.length-1)){
                        elementToAdd += 1;
                        break;
                    }


                    stringToTest = stringToTest + " " + splitedString[elementToAdd];
                    elementToAdd += 1;
                } while (widthOfText < width && (splitedString.length+1) > elementToAdd && contador < limite);

                Log.e("textBox", "adicionando texto: "+lastText.text);
                texts.add(lastText);
                Log.e("textBox", "elementToAdd "+elementToAdd);

            } while ((splitedString.length+1) > elementToAdd && contador < limite);

            Log.e("textbox", "contador "+contador);

            for (int i = 0; i < texts.size(); i++){
                Log.e("textbox", texts.get(i).text);
            }

        }
    }
}
