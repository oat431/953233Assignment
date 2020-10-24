package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimatedSprite extends ImageView{

    int count, columns, offsetX, offsetY, width, height, curXIndex=0, curYIndex=0;

    public AnimatedSprite(Image image, int count, int columns, int offsetX, int offsetY, int width, int height){
        this.setImage(image);
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        this.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
    }

    public void tick() {
        curXIndex = (curXIndex+1)%columns;
        curYIndex = (curYIndex+1)/columns;
        interpolate();
    }

    protected void interpolate() {
        final int x = curXIndex*width+offsetX;
        final int y = curYIndex*height+offsetY;
        this.setViewport(new Rectangle2D(x, y, width, height));
    }
}
