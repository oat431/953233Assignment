package test;

import controller.GameLoop;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import model.Direction;
import model.Food;
import model.Snake;
import model.SpecialFood;
import org.junit.Before;
import org.junit.Test;
import view.Platforms;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class GameLoopTest {
    private GameLoop gameLoopUnderTest;
    private Method update;
    private Method collision;
    private Method redraw;
    private Method eatSp;
    @Before
    public void init() throws NoSuchMethodException {
        this.gameLoopUnderTest = new GameLoop(new Platforms(), new Snake(new Point2D(0,0)), new Food());
        this.gameLoopUnderTest.setSp(new SpecialFood());
        this.update = GameLoop.class.getDeclaredMethod("update");
        this.update.setAccessible(true);
        this.collision = GameLoop.class.getDeclaredMethod("checkCollision");
        this.collision.setAccessible(true);
        this.eatSp = GameLoop.class.getDeclaredMethod("eatSpecialFood");
        this.eatSp.setAccessible(true);
        this.redraw = GameLoop.class.getDeclaredMethod("redraw");
        this.redraw.setAccessible(true);
    }

//    private void clockTickHelper() throws InvocationTargetException,IllegalAccessException {
//        update.invoke(gameLoopUnderTest);
//        collision.invoke(gameLoopUnderTest);
//        eatSp.invoke(gameLoopUnderTest);
//        redraw.invoke(gameLoopUnderTest);
//    }
//
//
//    @Test
//    public void testClockTick() throws InvocationTargetException , IllegalAccessException {
//        gameLoopUnderTest = new GameLoop(new Platforms(),new Snake(new Point2D(0,0)),new Food());
//        clockTickHelper();
//        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0,1));
//    }
//
//    @Test
//    public void testNoBack() throws InvocationTargetException , IllegalAccessException {
//        gameLoopUnderTest = new GameLoop(new Platforms(),new Snake(new Point2D(0,0)),new Food());
//
//        gameLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
//        clockTickHelper();
//        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0,1));
//
//        gameLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
//        clockTickHelper();
//        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0,2));
//
//        gameLoopUnderTest.getPlatform().setKey(KeyCode.UP);
//        clockTickHelper();
//        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0,3));
//
//    }

    @Test
    public void ifSnakeEatSomeFoodWeWillGainSomeScore()  {
        gameLoopUnderTest = new GameLoop(new Platforms(),new Snake(new Point2D(0,0)),new Food(new Point2D(1,0)));

        gameLoopUnderTest.getSnake().setCurrentDirection(Direction.RIGHT);
        gameLoopUnderTest.getSnake().update();
        assertTrue(gameLoopUnderTest.getSnake().isCollidingWith(gameLoopUnderTest.getFood()));
        gameLoopUnderTest.checkCollision();
        assertEquals(gameLoopUnderTest.getScore(),1);
    }

    @Test
    public void ifSnakeEatSpecialFoodItwillget5point(){
        gameLoopUnderTest = new GameLoop(new Platforms(),new Snake(new Point2D(0,0)),new Food(new Point2D(3,3)));
        gameLoopUnderTest.getSnake().setCurrentDirection(Direction.RIGHT);
        gameLoopUnderTest.setSp(new SpecialFood(new Point2D(1,0)));
        gameLoopUnderTest.getSnake().update();
        assertTrue(gameLoopUnderTest.getSnake().isCollidingWitSpecial(gameLoopUnderTest.getSp()));
        gameLoopUnderTest.eatSpecialFood();
        assertEquals(gameLoopUnderTest.getScore(),5);
    }
}