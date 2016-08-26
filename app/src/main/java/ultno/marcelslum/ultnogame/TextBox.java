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
    float padding = 0.2f;
    boolean isHaveArrow = false;
    Button arrowContinuar;
    Image frame;
    Color textColor = new Color(0f, 0f, 0f, 0.9f);
    Line arrow;
    
    private TextBox(TextBoxBuilder builder) {
        super(builder.name, builder.game, builder.x, builder.y);
        this.width = builder.width;
        this.size = builder.size;
        this.text = builder.text;
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

        frame = new Image("frame", game, x, y, width + (textPadding*6), textY - y + (textPadding*6), Game.TEXTURE_TITTLE, 0f, 1f, 0f, 550f/1024f);
        addChild(frame);

        Log.e("texbox", x + " " + y + " " + (width + (textPadding*6)) + " " + (textY - y + (textPadding*6)));


        arrowContinuar = new Button("arrowContinuar", this.game, x + width - size, y + textY - size - (textPadding*8), size, size, Game.TEXTURE_BUTTONS_AND_BALLS);
        arrowContinuar.setTextureMap(14);
        arrowContinuar.textureMapUnpressed = 14;
        arrowContinuar.textureMapPressed = 6;
            final Game innerGame = game;
        arrowContinuar.setOnPress(new Button.OnPress2() {
            @Override
            public void onPress2() {
                innerGame.levelObject.nextTutorial();
            }
        });
        addChild(arrowContinuar);
    }

    public void appendArrow(float arrowX, float arrowY){
        isHaveArrow = true;

        float initialX;
        float initialY;

        Log.e("textbox appendArrow", " " + frame.x + " " + frame.y + " " + frame.width + " " + frame.height);

        if (arrowY > (frame.y + frame.height)){
            initialY = frame.y + frame.height;
            initialX = frame.x + (frame.width/2f);
        } else if (arrowY < frame.y){
            initialY = frame.y;
            initialX = frame.x + (frame.width/2f);
        } else {
            initialY = frame.y + (frame.height/2f);
            if (arrowX < frame.x){
                initialX = frame.x;
            } else if (arrowX > (frame.x+frame.width)){
                initialX = frame.x + frame.width;
            } else {
                initialX = frame.x;
            }
        }

        Log.e("textbox appendArrow", " initialX " + initialX + " initialY " + initialY);

        arrow = new Line("line", game, initialX, initialY, arrowX, arrowY, textColor);
        addChild(arrow);

    }

    public void render(float[] matrixView, float[] matrixProjection){

        if (isHaveArrow && arrow != null){
            arrow.prepareRender(matrixView, matrixProjection);
        }

        frame.prepareRender(matrixView, matrixProjection);

        arrowContinuar.alpha = alpha * 0.6f;

        arrowContinuar.prepareRender(matrixView, matrixProjection);

        for (int i = 0; i < this.texts.size();i++){
            this.texts.get(i).alpha = alpha;
            this.texts.get(i).prepareRender(matrixView, matrixProjection);
        }
    }
    
    public static class TextBoxBuilder {

        private static float width;
        private static float size;
        private static String text;
        private static x;
        private static y;
        private static String name;
        private static Game game;
        
        public TextBoxBuilder() {
            this.name = name;
            this.game = game;
        }
        
        public TextBoxBuilder name(String name){
            this.name = name;
            return this;
        }
        
        public TextBoxBuilder game(Game game){
            this.game = game;
            return this;
        }
        
        public TextBoxBuilder position(float x, float y){
            this.x = x;
            this.y = y;
            return this;
        }
        
        public TextBoxBuilder position(float x, float y){
            this.x = x;
            this.y = y;
            return this;
        }
        
        public TextBoxBuilder size(float size){
            this.size = size;
            return this;
        }
        
        public TextBoxBuilder text(float text){
            this.text = text;
            return this;
        }
        
        public TextBoxBuilder width(float width){
            this.width = width;
            return this;
        }
        
        public TextBoxBuilder build(){
            return new TextBox(this);
        }
    }
    
}
