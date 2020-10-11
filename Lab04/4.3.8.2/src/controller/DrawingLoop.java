package controller;

import model.Character;
import view.Platform;

public class DrawingLoop implements Runnable{
    private Platform platform;
    private int frameRate;
    private float interval;
    private boolean running;
    public DrawingLoop(Platform platform){
        this.platform = platform;
        frameRate = 60;
        interval = 1000.0f / frameRate;
        running = true;
    }

    private void checkCollisions(Character character){
        character.checkReachGameWall();
        character.checkReachHighest();
        character.checkReachFloor();
    }

    private void paint(Character character){
        character.repaint();
    }

    @Override
    public void run(){
        while(running){
            float time = System.currentTimeMillis();
            checkCollisions(platform.getCharacter());
            checkCollisions(platform.getCharacter2());
            paint(platform.getCharacter());
            paint(platform.getCharacter2());
            time = System.currentTimeMillis() - time;
            if(time < interval){
                try{
                    Thread.sleep((long) (interval - time));
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            } else {
                try{
                    Thread.sleep((long)(interval - (interval % time)));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
