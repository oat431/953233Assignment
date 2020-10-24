package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import model.Character;
import view.Platform;
import view.Score;

import java.util.ArrayList;

public class GameLoop implements Runnable {

    private Platform platform;
    private int frameRate;
    private float interval;
    private boolean running;

    public GameLoop(Platform platform) {
        this.platform = platform;
        frameRate = 5;
        interval = 1000.0f / frameRate; // 1000 ms = 1 second
        running = true;
    }

    private void update(ArrayList<Character> characterList) {

        for (Character character : characterList ) {
            if (platform.getKeys().isPressed(character.getLeftKey()) || platform.getKeys().isPressed(character.getRightKey())) {
                character.getImageView().tick();
            }

            if (platform.getKeys().isPressed(character.getLeftKey())) {
                character.setScaleX(-1);
                character.moveLeft();
                character.trace();
            }

            if (platform.getKeys().isPressed(character.getRightKey())) {
                character.setScaleX(1);
                character.moveRight();
                character.trace();
            }

            if (!platform.getKeys().isPressed(character.getLeftKey()) && !platform.getKeys().isPressed(character.getRightKey()) ) {
                character.stop();
            }

            if (platform.getKeys().isPressed(character.getUpKey())) {
                character.jump();
            }
        }
    }

    private void updateScore(ArrayList<Score> scoreList, ArrayList<Character> characterList){
        javafx.application.Platform.runLater(()->{
            for(int i=0;i<scoreList.size();i++){
                scoreList.get(i).setPoint(characterList.get(i).getScore());
            }
        });
    }

    @Override
    public void run() {
        while (running) {

            float time = System.currentTimeMillis();

            update(platform.getCharacterList());
            updateScore(platform.getScoreList(),platform.getCharacterList());

            time = System.currentTimeMillis() - time;

            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException ignore) {
                    ignore.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep((long) (interval - (interval % time)));
                } catch (InterruptedException ignore) {
                    ignore.printStackTrace();
                }
            }
        }
    }
}
