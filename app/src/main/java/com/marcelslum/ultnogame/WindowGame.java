package com.marcelslum.ultnogame;

public class WindowGame extends PhysicalObject{
    
    int quantityOfLines;
    float distance;
    float height;
    float size;
    float [] linesY;
    int quantityX;

    private float maxDistance;
    SubWindowGameM swgM;
    SubWindowGameF swgF;

    boolean isActive;
    
    public WindowGame(String name, float y, int quantityOfLines, float height, float distance, float velocity) {
        super(name, 0f, y, Entity.TYPE_WINDOW_GAME, 0);
        this.quantityOfLines = quantityOfLines;
        this.distance = distance;
        program = Game.imageProgram;
        textureId = Texture.TEXTURES;
        isActive = false;
        swgM = new SubWindowGameM(this, 0f, y);
        swgF = new SubWindowGameF(this, 0f, y);

        addChild(swgM);
        addChild(swgF);
        
        if (distance > 0.49f){
            distance = 0.49f;
        }
        
        if (distance < 0f){
            distance = 0f;
        }
        
        this.height = height;
        this.dvx = velocity;
        this.vx = velocity;
        isCollidable = false;
        isSolid = false;
        isMovable = true;
        
        
        size = height/(quantityOfLines - (distance * quantityOfLines) + distance);
        linesY = new float[quantityOfLines];
        
        quantityX = (int)Math.floor((Game.gameAreaResolutionX*2f) / (size * (1f - distance)));
        
        for (int i = 0; i < quantityOfLines; i++){
            linesY[i] = i * (size * (1 - distance)); 
        }
        
        maxDistance = (size * (1f-distance))*8f;
        
        if (velocity >= 0){
            x = -maxDistance;
        } else {
            x = 0f;
        }
        
        setDrawInfo();
    }
    
    public void setDrawInfo(){
        int quantityOfSquares = quantityX * (int)(Math.floor((quantityOfLines/3) + 1));
        initializeData(12*quantityOfSquares, 6*quantityOfSquares, 8*quantityOfSquares, 0);
        
        float xPosition;
        float yPosition;

        int insertedSquare = 0;

        float distanceToDraw = size * (1-distance);

        for (int iY = 0; iY < quantityOfLines; iY++){
            for (int iX = 0; iX < quantityX; iX++){
                xPosition = iX * distanceToDraw;
                yPosition = iY * distanceToDraw;
                if (iY%2 == 0){ // se é par
                    if (iY%4 == 0){
                        if (iX%4 == 3){
                            Utils.insertRectangleVerticesData(this.verticesData, (insertedSquare * 12), xPosition, xPosition+size, yPosition, yPosition + size, 0f);
                            insertedSquare += 1;
                        }
                    } else {
                        if (iX%4 == 1){
                            Utils.insertRectangleVerticesData(this.verticesData, (insertedSquare * 12), xPosition, xPosition+size, yPosition, yPosition + size, 0f);
                            insertedSquare += 1;
                        }

                    }
                }
            }
        }
        
        for (int i = 0; i < quantityOfSquares; i++){
            Utils.insertRectangleIndicesData(this.indicesData, i * 6, i * 4);
            Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_WINDOW_ID));
        }
        
        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);
        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);

        swgM.setDrawInfo(distanceToDraw, quantityOfLines);
        swgF.setDrawInfo(distanceToDraw, quantityOfLines);
    }

    public void initMoving(){
        isActive = true;

    }

    public void stopMoving(){
        isActive = false;
    }

    
    public void move(){
        if (!isActive){
            return;
        }
        if (isMovable){
            x += vx;
            if (vx > 0){
                if (x > -(maxDistance/2f)){
                    x = x - (maxDistance/2f);
                }
            } else {
                if (x < (-maxDistance/2f)){
                    x = x + (maxDistance/2f);
                }
            }

            swgM.move(vx/2, maxDistance);
            swgF.move(vx, maxDistance);
        }
    }

    @Override
    public void render(float[] matrixView, float[] matrixProjection) {
        super.render(matrixView, matrixProjection);
        swgM.render(matrixView, matrixProjection);
        swgF.render(matrixView, matrixProjection);
    }

    public class SubWindowGameM extends PhysicalObject {

        WindowGame wg;

        public SubWindowGameM(WindowGame wg, float x, float y) {
            super(wg.name+"m", x, y, Entity.TYPE_WINDOW_GAME, 0);
            program = wg.program;
            textureId = wg.textureId;
            this.wg = wg;
        }

        public void setDrawInfo(float distanceToDraw, int quantityOfLines) {

            int quantityOfSquares = quantityX * (int) Math.floor(quantityOfLines / 2);
            initializeData(12 * quantityOfSquares, 6 * quantityOfSquares, 8 * quantityOfSquares, 0);

            int insertedSquare = 0;

            float xPosition;
            float yPosition;
            for (int iY = 0; iY < wg.quantityOfLines; iY++) {
                for (int iX = 0; iX < wg.quantityX; iX++) {
                    xPosition = (iX - 1) * distanceToDraw;
                    yPosition = iY * distanceToDraw;
                    if (iY % 2 != 0) {
                        if (iX % 2 != 0) {
                            Utils.insertRectangleVerticesData(this.verticesData, insertedSquare * 12, xPosition, xPosition + size, yPosition, yPosition + size, 0f);
                            insertedSquare += 1;
                        }
                    }
                }
            }

            for (int i = 0; i < quantityOfSquares; i++) {
                Utils.insertRectangleIndicesData(indicesData, i * 6, i * 4);
                Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_WINDOW_ID));
            }

            verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);
            indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);
            uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);
        }

        public void move(float vx, float maxDistance) {
                x += vx;
                if (vx > 0) {
                    if (x > -(maxDistance / 2f)) {
                        x = x - (maxDistance / 2f);
                    }
                } else {
                    if (x < (-maxDistance / 2f)) {
                        x = x + (maxDistance / 2f);
                    }
                }
            }
    }


    public class SubWindowGameF extends PhysicalObject {
        WindowGame wg;

        public SubWindowGameF(WindowGame wg, float x, float y) {
            super(wg.name+"f", x, y, Entity.TYPE_WINDOW_GAME, 0);
            program = wg.program;
            textureId = wg.textureId;
            this.wg = wg;
        }

        public void setDrawInfo(float distanceToDraw, int quantityOfLines) {

            int quantityOfSquares = quantityX * (int)(Math.floor((quantityOfLines/3) + 1));
            initializeData(12 * quantityOfSquares, 6 * quantityOfSquares, 8 * quantityOfSquares, 0);

            int insertedSquare = 0;

            float xPosition;
            float yPosition;
            for (int iY = 0; iY < quantityOfLines; iY++){
                for (int iX = 0; iX < quantityX; iX++){
                    xPosition = iX * distanceToDraw;
                    yPosition = iY * distanceToDraw;
                    if (iY%2 == 0){ // se é par
                        if (iY%4 == 0){
                            if (iX%4 == 1){
                                Utils.insertRectangleVerticesData(this.verticesData, insertedSquare * 12, xPosition, xPosition+size, yPosition, yPosition + size, 0f);
                                insertedSquare += 1;
                            }
                        } else {
                            if (iX%4 == 3){
                                Utils.insertRectangleVerticesData(this.verticesData, insertedSquare * 12, xPosition, xPosition+size, yPosition, yPosition + size, 0f);
                                insertedSquare += 1;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < quantityOfSquares; i++) {
                Utils.insertRectangleIndicesData(this.indicesData, i * 6, i * 4);
                Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_WINDOW_ID));
            }

            verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);
            indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);
            uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);
        }

        public void move(float vx, float maxDistance) {
            x += vx;
            if (vx > 0) {
                if (x > -(maxDistance / 2f)) {
                    x = x - (maxDistance / 2f);
                }
            } else {
                if (x < (-maxDistance / 2f)) {
                    x = x + (maxDistance / 2f);
                }
            }
        }
    }

}
