package com.marcelslum.ultnogame;
import android.util.Log;
import java.util.ArrayList;

public class TextView extends Entity{
  
	public ArrayList<Text> texts;
	public float width;
	public float height;
	public float size;
	public Font font;
	public float padding = 0.2f;
	public float currentTranslateY;
	
	boolean desacelerationActivated = false;
    float lastMovement;
	private boolean cancelNextPress;

	private final String TAG = "TextView";

	float translateY = 0;
	int alignment;
  
	public TextView(String name, float x, float y, float width, float height, float size, Font font, Color color, int alignment, float padding){
		
		super(name, x, y, Entity.TYPE_TEXT_VIEW);
		this.width = width;
		this.height = height;
		this.size = size;
		this.font = font;
		this.color = color;
		this.alignment = alignment;
		this.padding = padding;
		texts = new ArrayList<>();

		
		final TextView innerTextView = this;
		setListener(new InteractionListener(this.name, x, y, width, height, 5000, this));
		
		
		listener.setPressListener(new InteractionListener.PressListener() {
			@Override
			public void onPress() {Log.e(TAG, "press");}
			@Override
			public void onUnpress() {Log.e(TAG, "unpress");}
		});
		
		listener.setMoveListener(new InteractionListener.MoveListener() {
			@Override
			public void onMoveDown() {

				Log.e(TAG, "moveDown");

			    if (innerTextView.desacelerationActivated){
				innerTextView.desacelerationActivated = false;
				innerTextView.cancelNextPress = true;
			    }
			}

			@Override
			public void onMove(TouchEvent touch, long startTime) {

				Log.e(TAG, "move");
			    if (!isBlocked){
				innerTextView.move(touch.y - touch.previousY, true);
				innerTextView.lastMovement = touch.y - touch.previousY;
			    }
			}

			@Override
			public void onMoveUp(TouchEvent touch, long startTime) {
				Log.e(TAG, "moveUp");
			    innerTextView.desacelerationActivated = true;
			}
		});
	}

	public void checkTransformations(boolean updatePrevious){
		super.checkTransformations(updatePrevious);
		desacelerate();
	}
	
	public void addText(String text, Color newTextColor){



		ArrayList<Text> newTexts = Text.splitStringAtMaxWidth("novo text", text, font, newTextColor, size, width, alignment);

        texts.addAll(newTexts);
		Text.doLinesWithStringCollection(texts, y, size, size * padding, false);

		for (int i = 0; i < texts.size(); i++){
			texts.get(i).setX(x);
		}
		
		if (childs != null){
            		childs.clear();
		}
		
		for (int i = 0; i < texts.size(); i++){
			addChild(texts.get(i));
		}
	}
	
	public void render(float[] matrixView, float[] matrixProjection){
		for (int i = 0; i < texts.size(); i++){
			texts.get(i).render(matrixView, matrixProjection);
		}
	}
	
	private void desacelerate() {
		if (lastMovement < 0) {
		    if (lastMovement + 0.8f < 0) {
			move(lastMovement + 0.8f, true);
			lastMovement += 0.8f;
		    } else {
			desacelerationActivated = false;
		    }
		} else if (lastMovement > 0) {
		    if (lastMovement - 0.8f > 0) {
			move(lastMovement - 0.8f, true);
			lastMovement -= 0.8f;
		    } else {
			desacelerationActivated = false;
		    }
		}
	}
	

	public void move(float iconTranslateY, boolean updateCurrentTranslateY) {

		float padd = size * 0.5f;

		Text lastText = texts.get(texts.size()-1);
		if (lastText.positionY + size + iconTranslateY < Game.resolutionY - padd){
		    iconTranslateY = (Game.resolutionY - padd) - (lastText.positionY + size);
		    if (desacelerationActivated){
			desacelerationActivated = false;
		    }
		}

		Text firstText = texts.get(0);
		if (firstText.positionY - iconTranslateY > y){
		    iconTranslateY = padd - firstText.positionY;
		    if (desacelerationActivated){
			desacelerationActivated = false;
		    }
		}

		for (int i = 0; i < texts.size(); i++){
		    texts.get(i).translate(0f, iconTranslateY);
		    if (texts.get(i).shadowText != null){
			texts.get(i).shadowText.translate(0f, iconTranslateY);
		    }
		}

		if (updateCurrentTranslateY) {
		    currentTranslateY += iconTranslateY;
		}
	}
}

