package ultno.marcelslum.ultnogame;

public class PositionVariationDataBuilder {
    public boolean isActive = false;
    public boolean increaseX = true;
    public boolean increaseY = true;
    public float minX = 0;
    public float maxX = 1;
    public float minY = 0;
    public float maxY = 1;
    public float xVelocity = 0.01f;
    public float yVelocity = 0.01f;

    public PositionVariationDataBuilder setIsActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public PositionVariationDataBuilder setIncreaseX(boolean increaseX) {
        this.increaseX = increaseX;
        return this;
    }

    public PositionVariationDataBuilder setIncreaseY(boolean increaseY) {
        this.increaseY = increaseY;
        return this;
    }

    public PositionVariationDataBuilder setMinX(float minX) {
        this.minX = minX;
        return this;
    }

    public PositionVariationDataBuilder setMaxX(float maxX) {
        this.maxX = maxX;
        return this;
    }

    public PositionVariationDataBuilder setMinY(float minY) {
        this.minY = minY;
        return this;
    }

    public PositionVariationDataBuilder setMaxY(float maxY) {
        this.maxY = maxY;
        return this;
    }

    public PositionVariationDataBuilder setxVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
        return this;
    }

    public PositionVariationDataBuilder setyVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
        return this;
    }

    public PositionVariationData build() {
        return new PositionVariationData(this);
    }
}