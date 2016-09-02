package ultno.marcelslum.ultnogame;

/**
 * Created by marcel on 02/09/2016.
 */
public class ScaleVariationData{
    public boolean isActive;
    public boolean increaseWidth;
    public boolean increaseHeight;
    public float minWidth_BI;
    public float maxWidth_BI;
    public float minHeight_BI;
    public float maxHeight_BI;
    public float widthVelocity;
    public float heightVelocity;

    public ScaleVariationData(boolean isActive, boolean increaseWidth, boolean increaseHeight, float minWidth_BI, float maxWidth_BI, float minHeight_BI, float maxHeight_BI, float widthVelocity, float heightVelocity) {
        this.isActive = isActive;
        this.increaseWidth = increaseWidth;
        this.increaseHeight = increaseHeight;
        this.minWidth_BI = minWidth_BI;
        this.maxWidth_BI = maxWidth_BI;
        this.minHeight_BI = minHeight_BI;
        this.maxHeight_BI = maxHeight_BI;
        this.widthVelocity = widthVelocity;
        this.heightVelocity = heightVelocity;
    }
}

