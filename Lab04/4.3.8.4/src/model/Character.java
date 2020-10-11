package model;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import view.Platform;

public class Character extends Pane {
    public static final Logger logger = LogManager.getLogger(Character.class.getName());
    public static final int CHARACTER_WIDTH = 32;
    public static final int CHARACTER_HEIGHT = 64;
    private Image characterImg;
    private AnimatedSprite imageView;
    private int x;
    private int y;
    private KeyCode leftKey;
    private KeyCode rightKey;
    private KeyCode upKey;
    private int xVelocity = 0;
    private int yVelocity = 0;
    private int xAcceleration = 1;
    private int yAcceleration = 1;
    private int xMaxVelocity = 7;
    private int yMaxVelocity = 17;
    private boolean isMoveLeft = false;
    private boolean isMoveRight = false;
    private boolean isFalling = true;
    private boolean canJump = false;
    private boolean isJumping = false;
    private int highestJump = 100;

    public Character(int x,int y,int offsetX,int offsetY, KeyCode leftKey,KeyCode rightKey, KeyCode upKey,String text){
        this.x = x;
        this.y = y;
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.characterImg = new Image(getClass().getResourceAsStream(text));
        this.imageView = new AnimatedSprite(characterImg,1,4,4,offsetX,offsetY,16,32);
        this.imageView.setFitHeight(CHARACTER_HEIGHT);
        this.imageView.setFitWidth(CHARACTER_WIDTH);
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.getChildren().addAll(this.imageView);
    }

    public void moveY(){
        setTranslateY(y);
        if(isFalling){
            yVelocity = yVelocity >= yMaxVelocity ? yMaxVelocity : yVelocity + yAcceleration;
            y = y + yVelocity;
        }else if(isJumping){
            yVelocity = yVelocity <= 0 ? 0 : yVelocity - yAcceleration;
            y = y - yVelocity;
        }
    }

    public void checkReachFloor(){
        if(isFalling && y > Platform.GROUND - CHARACTER_HEIGHT){
            isFalling = false;
            canJump = true;
            yVelocity = 0;
        }
    }

    public void repaint(){
        moveX();
        moveY();
    }

    public KeyCode getLeftKey() {
        return leftKey;
    }

    public KeyCode getRightKey() {
        return rightKey;
    }

    public KeyCode getUpKey() {
        return upKey;
    }

    public void moveLeft(){
        isMoveLeft = true;
        isMoveRight = false;
    }

    public void moveRight(){
        isMoveLeft = false;
        isMoveRight = true;
    }

    public void stop() {
        isMoveLeft = false;
        isMoveRight = false;
    }

    public void runAgain(){
        xVelocity = 5;
    }

    public void moveX(){
        setTranslateX(x);
        if(isMoveLeft){
            xVelocity = xVelocity >= xMaxVelocity ? xMaxVelocity : xVelocity + xAcceleration;
            x = x - xVelocity;
        }
        if(isMoveRight){
            xVelocity = xVelocity >= xMaxVelocity ? xMaxVelocity : xVelocity + xAcceleration;
            x = x + xVelocity;
        }
    }

    public void jump(){
        if(canJump){
            yVelocity = yMaxVelocity;
            canJump = false;
            isJumping = true;
            isFalling = false;
        }
    }

    public void checkReachHighest(){
        if(isJumping && yVelocity <= 0){
            isJumping = false;
            isFalling = true;
            yVelocity = 0;
        }
    }

    public void checkReachGameWall() {
        if (x <= 0) {
            x = 0;
        } else if (x + getWidth() >= Platform.WIDTH) {
            x = Platform.WIDTH - (int) getWidth();
        }
    }

    public AnimatedSprite getImageView() {
        return imageView;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void setxAcceleration(int xAcceleration) {
        this.xAcceleration = xAcceleration;
    }

    public void setyAcceleration(int yAcceleration) {
        this.yAcceleration = yAcceleration;
    }

    public void setxMaxVelocity(int xMaxVelocity) {
        this.xMaxVelocity = xMaxVelocity;
    }

    public void setCharacterImg(String text) {
        this.characterImg = new Image(getClass().getResourceAsStream(text));
    }

    public void setyMaxVelocity(int yMaxVelocity) {
        this.yMaxVelocity = yMaxVelocity;
    }

    public void trace(){
        logger.info("x:{} y:{} vx:{} vy:{}",x,y,xVelocity,yVelocity);
    }
}
