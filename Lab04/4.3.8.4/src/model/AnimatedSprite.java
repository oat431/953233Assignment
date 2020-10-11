package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimatedSprite extends ImageView {
    int count, columns,rows,offsetX,offsetY,width,height,curIndex,curColumnIndex=0,curRowIndex=0;
    public AnimatedSprite(Image image, int count, int columns,int rows,int offsetX, int offsetY, int width, int height){
        this.setImage(image);
        this.count = count;
        this.columns = columns;
        this.rows = rows;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        this.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
    }

    public void tick(){
        curColumnIndex = curIndex % columns;
        curRowIndex = curIndex / rows;
        curIndex = (curIndex + 1) % (columns * rows);
        interpolate();
    }

    public void tock(){
        curColumnIndex = curIndex % columns;
        curRowIndex = curIndex / rows;
        curIndex = (curIndex + 1) % (columns * rows);
        interpolate2();
    }

    public void interpolate(){
        int x = curColumnIndex * width + offsetX;
        int y = curRowIndex * height + offsetY;
        this.setViewport(new Rectangle2D(x,0,width,height));
    }

    public void interpolate2(){
        int x = curColumnIndex * width + offsetX;
        int y = curRowIndex * height + offsetY;
        this.setViewport(new Rectangle2D(x,48,width,height));
    }
}
