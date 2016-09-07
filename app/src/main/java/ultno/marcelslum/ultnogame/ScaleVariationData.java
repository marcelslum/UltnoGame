package ultno.marcelslum.ultnogame;

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

    public ScaleVariationData(ScaleVariationDataBuilder builder) {
        this.isActive = builder.isActive;
        this.increaseWidth = builder.increaseWidth;
        this.increaseHeight = builder.increaseHeight;
        this.minWidth_BI = builder.minWidth_BI;
        this.maxWidth_BI = builder.maxWidth_BI;
        this.minHeight_BI = builder.minHeight_BI;
        this.maxHeight_BI = builder.maxHeight_BI;
        this.widthVelocity = builder.widthVelocity;
        this.heightVelocity = builder.heightVelocity;
    }
}

