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

	float translateY = 0;
  
	public TextView(String name, float x, float y, float width, float height, Font font){
		super(name, x, y, Entity.TYPE_TEXT_VIEW);
		this.width = width;
		this.height = height;
		this.size = size;
		this.font = font;
		texts = new ArrayList<>();
	}
	
	public void addText(String text){
		ArrayList<Text> newTexts = Text.splitStringAtMaxWidth(text.name, text, font, text.color, size, width);
		texts.addAll(newTexts);
		Text.doLinesWithStringCollection(texts, y, size, size * padding, false);
		
		if (childs != null){
            childs.clear();
		}
		
		for (int i = 0; i < texts.size(); i++){
			addChild(texts.get(i));
		}
	}
	
	public void render(float[] matrixView, float[] matrixProjection){
		for (int i = 0; i < texts.size(); i++){
			texts.get(i).render(float[] matrixView, float[] matrixProjection);
		}
	}
	
	
}

