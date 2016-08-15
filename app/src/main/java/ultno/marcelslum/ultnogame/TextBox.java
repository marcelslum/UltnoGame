package ultno.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 07/08/2016.
 */
public class TextBox extends Entity{

    boolean displayArrow;
    Image background;
    ArrayList<Text> texts;
    float width;
    float height;
    float size;
    String text;
    float maxWidth;
    float padding = 0.2f;
    boolean isHaveArrow;
    Button arrowContinuar;
    float arrowStartX;
    float arrowStartY;
    float arrowFinishX;
    float arrowFinishY;
    Image frame;
    Color textColor = new Color(0f, 0f, 0f, 0.9f);
    Line arrow;

    TextBox(String name, Game game, float x, float y, float width, float size, String text) {
        super(name, game, x, y);
        this.width = width;
        this.size = size;
        this.text = text;
        this.texts = new ArrayList<>();

        Text textForMeasure = new Text("text", game, 0f, 0f, size, text, game.font, textColor);
        float widthOfText = textForMeasure.calculateWidth();

        // subdivide o texto em partes, conforme width máximo
        if (widthOfText > this.width) {
            String [] splitedString = text.split(" ");
            String stringToTest = splitedString[0];

            //Log.e("textBox", "splitedString lenght"+ splitedString.length);

            int elementToAdd = 1;
            Text lastText = new Text("text", game, 0f, 0f, size, ".", game.font, textColor);

            int limite = 100;
            int contador = 0;


            do {
                do {
                    contador += 1;
                    textForMeasure = new Text("text", game, 0f, 0f, size, stringToTest, game.font, textColor);

                    //Log.e("textBox", "testando string "+ stringToTest);

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

                //Log.e("textBox", "adicionando texto: "+lastText.text);
                texts.add(lastText);
                //Log.e("textBox", "elementToAdd "+elementToAdd);

            } while ((splitedString.length+1) > elementToAdd && contador < limite);

        } else {
            texts.add(textForMeasure);
        }

        float textPadding = size * padding;
        float textY = this.y + (textPadding * 4);
        float textX = this.x + (textPadding * 4);


        for (int i = 0; i < this.texts.size(); i++){
            this.texts.get(i).x = textX;
            this.texts.get(i).y = textY;
            textY += size + textPadding;
            addChild(this.texts.get(i));
        }

        frame = new Image("frame", game, x, y, width + (textPadding*6), textY - y + (textPadding*6), 7, 0f, 1f, 0f, 550f/1024f);
        addChild(frame);

        arrow = new Line("lina", game, 50f, 50f, 300f, 300f, textColor);
        addChild(arrow);

    }

    public void render(float[] matrixView, float[] matrixProjection){

        if (arrow != null){
            arrow.prepareRender(matrixView, matrixProjection);
        }

        frame.prepareRender(matrixView, matrixProjection);

        for (int i = 0; i < this.texts.size();i++){
            this.texts.get(i).alpha = alpha;
            this.texts.get(i).prepareRender(matrixView, matrixProjection);
        }
    }


}
