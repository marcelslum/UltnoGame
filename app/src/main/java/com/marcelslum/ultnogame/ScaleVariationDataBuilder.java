package com.marcelslum.ultnogame;

public class ScaleVariationDataBuilder {
    public boolean isActive = false;
    public boolean increaseWidth = false;
    public boolean increaseHeight = false;
    public float minWidth_BI = 0.5f;
    public float maxWidth_BI = 2f;
    public float minHeight_BI = 0.5f;
    public float maxHeight_BI = 2f;
    public float widthVelocity = 0f;
    public float heightVelocity = 0f;

    public ScaleVariationDataBuilder(){

    }

    public ScaleVariationDataBuilder setIsActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public ScaleVariationDataBuilder setIncreaseWidth(boolean increaseWidth) {
        this.increaseWidth = increaseWidth;
        return this;
    }

    public ScaleVariationDataBuilder setIncreaseHeight(boolean incraseHeight) {
        this.increaseHeight = incraseHeight;
        return this;
    }

    public ScaleVariationDataBuilder setMinWidth_BI(float minWidth_bi) {
        this.minWidth_BI = minWidth_bi;
        return this;
    }

    public ScaleVariationDataBuilder setMaxWidth_BI(float maxWidth_bi) {
        this.maxWidth_BI = maxWidth_bi;
        return this;
    }

    public ScaleVariationDataBuilder setMinHeight_BI(float minHeight_bi) {
        this.minHeight_BI = minHeight_bi;
        return this;
    }

    public ScaleVariationDataBuilder setMaxHeight_BI(float maxHeight_bi) {
        this.maxHeight_BI = maxHeight_bi;
        return this;
    }

    public ScaleVariationDataBuilder setWidthVelocity(float widthVelocity) {
        this.widthVelocity = widthVelocity;
        return this;
    }

    public ScaleVariationDataBuilder setHeightVelocity(float heightVelocity) {
        this.heightVelocity = heightVelocity;
        return this;
    }

    public ScaleVariationData build() {
        return new ScaleVariationData(this);
    }
}
