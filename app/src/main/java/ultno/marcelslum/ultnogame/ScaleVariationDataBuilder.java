package ultno.marcelslum.ultnogame;

/**
 * Created by marcel on 02/09/2016.
 */
public class ScaleVariationDataBuilder {
    private boolean isActive = false;
    private boolean increaseWidth = false;
    private boolean incraseHeight = false;
    private float minWidth_bi = 0.5f;
    private float maxWidth_bi = 2f;
    private float minHeight_bi = 0.5f;
    private float maxHeight_bi = 2f;
    private float widthVelocity = 0f;
    private float heightVelocity = 0f;

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
        this.incraseHeight = incraseHeight;
        return this;
    }

    public ScaleVariationDataBuilder setMinWidth_BI(float minWidth_bi) {
        this.minWidth_bi = minWidth_bi;
        return this;
    }

    public ScaleVariationDataBuilder setMaxWidth_BI(float maxWidth_bi) {
        this.maxWidth_bi = maxWidth_bi;
        return this;
    }

    public ScaleVariationDataBuilder setMinHeight_BI(float minHeight_bi) {
        this.minHeight_bi = minHeight_bi;
        return this;
    }

    public ScaleVariationDataBuilder setMaxHeight_BI(float maxHeight_bi) {
        this.maxHeight_bi = maxHeight_bi;
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
        return new ScaleVariationData(isActive, increaseWidth, incraseHeight, minWidth_bi, maxWidth_bi, minHeight_bi, maxHeight_bi, widthVelocity, heightVelocity);
    }
}
