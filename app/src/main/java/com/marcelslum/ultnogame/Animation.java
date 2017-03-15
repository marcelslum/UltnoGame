package com.marcelslum.ultnogame;

import java.util.ArrayList;


// teste
/**
 * Created by marcel on 01/08/2016.
 */
public class Animation{
    private Entity targetObject;
    String name;
    private String parameterToAnimate;
    private int duration;
    ArrayList<float[]> values;
    private float offSet;
    private boolean isInfinite;
    private boolean isFluid;
    private long startTime;
    private int positionNotFluid;
    boolean started;
    public float elapsedTime;
    private float percentage;
    private float initialValue;
    private float initialTime;
    private float finalValue;
    private float finalTime;
    private int lastValue;
    private AnimationListener mListener;
    private OnChange onChange;
    private boolean[] isFluidChanged;
    private ArrayList<Entity> attachedEntities;
    boolean applyOnChild = true;

    public Animation(Entity target, String name, String parameter, int duration, ArrayList<float[]> values, boolean isInfinite, boolean isFluid){
        this.name = name;
        targetObject = target;
        parameterToAnimate = parameter;
        this.duration = duration;
        this.values = values;
        this.isInfinite = isInfinite;
        this.isFluid = isFluid;
        startTime = 0;
        positionNotFluid = -1;
        started = false;
        elapsedTime = 0;
        percentage = 0;
        offSet = 0;
        applyOnChild = true;
        targetObject.addAnimation(this);
    }

    public void excludeChild(){
        applyOnChild = false;
    }

    public void start(){
        //Log.e("Animation", "start animation");
        this.startTime = -1;
        this.positionNotFluid = -1;
        this.started = true;
        this.elapsedTime = 0;
        this.percentage = 0;
        this.setStartTime();

        isFluidChanged = new boolean [values.size()];

        if (!isFluid){
            for (int i = 0; i < isFluidChanged.length; i++){
                isFluidChanged[i] = false;
            }
        }
        //Log.e("Animation", "animation started "+this.started);
    }

    void stop(){
        started = false;
    }

    void addAttachedEntities(Entity e){
        if (attachedEntities == null){
            attachedEntities = new ArrayList<>();
        }
        attachedEntities.add(e);
    }

    void stopAndConclude(){
        stop();
        targetObject.applyAnimation(parameterToAnimate, values.get(values.size() - 1)[1], applyOnChild);

        if (attachedEntities != null){
            for (int i = 0; i < attachedEntities.size(); i++){
                attachedEntities.get(i).applyAnimation(parameterToAnimate, values.get(values.size() - 1)[1], applyOnChild);
            }
        }
        fireAnimationEnd();
    }

    void doAnimation(){

        this.elapsedTime = Utils.getTime() - this.startTime;
        this.percentage = this.elapsedTime/(float)this.duration;

        //Log.e("Animation", "this.percentage "+this.percentage);
        if (this.elapsedTime < this.duration && this.started){
            if (this.isFluid){
                if(this.values.size() > 1){
                    int v;
                    for (v = 0; v < this.values.size(); v++){
                        if (v + 1 < this.values.size()){
                            if (this.percentage >= this.values.get(v)[0] && this.percentage <= this.values.get(v+1)[0]){
                                this.finalValue = this.values.get(v+1)[1];
                                this.initialValue = this.values.get(v)[1];
                                this.finalTime = this.values.get(v+1)[0];
                                this.initialTime = this.values.get(v)[0];
                                this.lastValue = v+1;
                            }
                        }
                        else if (v+1 == this.values.size()){
                            break;
                        }
                    }
                } else {
                    this.finalValue = this.values.get(1)[1];
                    this.initialValue = this.values.get(0)[1];
                    this.finalTime = this.values.get(1)[0];
                    this.initialTime = this.values.get(0)[0];
                }

                //Log.e("Animation", " iv "+this.initialValue+" fv "+this.finalValue+" it "+this.initialTime+" ft "+this.finalTime);

                if (this.percentage > this.initialTime){
                    float delta = this.finalTime - this.initialTime;
                    float stepPercentage = (((this.percentage - this.initialTime)*100)/delta)/100;
                    float value = ((this.finalValue - this.initialValue) * stepPercentage)+this.values.get(0)[1];
                    float addValue = 0;
                    if (this.lastValue > 1){
                        for (int i = 1; i< this.lastValue; i++){
                            addValue += this.values.get(i)[1] - this.values.get(i-1)[1];
                        }
                    }

                    //Log.e("Animation", "aplicando animação na entidade "+this.targetObject.name+ " para o valor "+ (value + addValue));
                    this.targetObject.applyAnimation(parameterToAnimate, value + addValue, applyOnChild);

                    if (attachedEntities != null){
                        for (int i = 0; i < attachedEntities.size(); i++){
                            attachedEntities.get(i).applyAnimation(parameterToAnimate, value + addValue, applyOnChild);
                        }
                    }
                }

            } else { // if is not fluid
                this.percentage = this.elapsedTime/(float)this.duration;
                for (int v = 0; v < this.values.size(); v++){
                    if (this.percentage >= this.values.get(v)[0])
                    {
                        if (!isFluidChanged[v]) {

                            isFluidChanged[v] = true;
                            if (v > this.positionNotFluid) {
                                this.positionNotFluid = v;
                            }
                            this.targetObject.applyAnimation(parameterToAnimate, this.values.get(v)[1] + this.offSet, applyOnChild);

                            if (attachedEntities != null) {
                                for (int i = 0; i < attachedEntities.size(); i++) {
                                    attachedEntities.get(i).applyAnimation(parameterToAnimate, this.values.get(v)[1] + this.offSet, applyOnChild);
                                }
                            }
                            if (this.onChange != null) {
                            //Log.e("animation", "animation onChange fired");
                            onChange.onChange();
                            }
                        }
                    }
                }
            }
        } else {
            if (!this.isInfinite){
                this.targetObject.applyAnimation(parameterToAnimate, this.values.get(this.values.size()-1)[1] + this.offSet, applyOnChild);
                this.started = false;
                this.fireAnimationEnd();
            } else {
                if (!isFluid){
                    for (int i = 0; i < isFluidChanged.length; i++){
                        isFluidChanged[i] = false;
                    }
                }
                this.setStartTime();
            }
        }
    }

    private void fireAnimationEnd() {
        if (mListener != null) {
            mListener.onAnimationEnd();
        }
    }

    public void setStartTime(){
        this.startTime = Utils.getTime();
    }

    public void setAnimationListener(AnimationListener listener) {
        mListener = listener;
    }

    public void setOnChangeNotFluid(OnChange onChange) {
        isFluid = false;
        this.onChange = onChange;
    }

    public interface AnimationListener {
        void onAnimationEnd();
    }

    public interface OnChange {
        void onChange();
    }
}
