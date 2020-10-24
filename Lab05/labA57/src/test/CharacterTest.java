package test;

import controller.DrawingLoop;
import controller.GameLoop;
import de.saxsys.mvvmfx.testingutils.jfxrunner.JfxRunner;
import javafx.scene.input.KeyCode;
import junit.framework.TestCase;
import model.Character;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import view.Platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(JfxRunner.class)
public class CharacterTest {
    private Character floatingCharacter;
    private Character floatingCharacter2;
    private ArrayList<Character> characterListUnderTest;
    private Platform platformUnderTest;
    private GameLoop gameLoopUnderTest;
    private DrawingLoop drawingLoopUnderTest;
    private Method updateMethod;
    private Method redrawMethod;
    @Before
    public void setup(){
        floatingCharacter = new Character(30,30,0,0, KeyCode.A, KeyCode.D,KeyCode.W);
        floatingCharacter2 = new Character(Platform.WIDTH-60, 30,0,96, KeyCode.LEFT,KeyCode.RIGHT,KeyCode.UP);
        characterListUnderTest = new ArrayList<>();
        characterListUnderTest.add(floatingCharacter);
        characterListUnderTest.add(floatingCharacter2);
        platformUnderTest = new Platform();
        gameLoopUnderTest = new GameLoop(platformUnderTest);
        drawingLoopUnderTest = new DrawingLoop(platformUnderTest);

        try{
            updateMethod = GameLoop.class.getDeclaredMethod("update" , ArrayList.class);
            redrawMethod = DrawingLoop.class.getDeclaredMethod("paint",ArrayList.class);
            updateMethod.setAccessible(true);
            redrawMethod.setAccessible(true);
        }catch (NoSuchElementException | NoSuchMethodException e){
            e.printStackTrace();
            updateMethod = null;

        }
    }
    @Test
    public void characterInitialValuesShouldMatchConstructorArguments(){
        assertEquals("Initial x", 30, floatingCharacter.getX(), 0);
        assertEquals("Initial y", 30, floatingCharacter.getY(), 0);
        assertEquals("Offset x", 0, floatingCharacter.getOffsetX(), 0.0);
        assertEquals("Offset y", 0, floatingCharacter.getOffsetY(), 0.0);
        assertEquals("Left key", KeyCode.A, floatingCharacter.getLeftKey());
        assertEquals("Right key", KeyCode.D, floatingCharacter.getRightKey());
        assertEquals("Up key", KeyCode.W, floatingCharacter.getUpKey());
    }

    @Test
    public void characterShouldMoveToTheLeftAfterTheLeftKeyIsPressed() throws IllegalAccessException, InvocationTargetException {
        platformUnderTest.getKeys().add(KeyCode.A);
        updateMethod.invoke(gameLoopUnderTest,characterListUnderTest);
        redrawMethod.invoke(drawingLoopUnderTest,characterListUnderTest);
        assertTrue("Controller: Left key pressing is acknowledged",platformUnderTest.getKeys().isPressed(KeyCode.A));
        assertTrue("Model: Character moving left state is set", characterListUnderTest.get(0).isMoveLeft());
        assertTrue("View: Character is moving left", characterListUnderTest.get(0).getX() < characterListUnderTest.get(0).getStartX());
    }


    @Test
    public void characterShouldMoveToTheRightAfterTheRightKeyIsPress() throws IllegalAccessException, InvocationTargetException {
        platformUnderTest.getKeys().add(KeyCode.D);
        updateMethod.invoke(gameLoopUnderTest,characterListUnderTest);
        redrawMethod.invoke(drawingLoopUnderTest,characterListUnderTest);
        assertTrue("Controller: Left key pressing is acknowledged",platformUnderTest.getKeys().isPressed(KeyCode.D));
        assertTrue("Model: Character moving left state is set", characterListUnderTest.get(0).isMoveRight());
        assertTrue("View: Character is moving left", characterListUnderTest.get(0).getX() > characterListUnderTest.get(0).getStartX());
    }

    @Test
    public void characterCanBeJumpIfCharacterIsOnTheGroundAndPressUpperKey() throws IllegalAccessException, InvocationTargetException {
        platformUnderTest.getKeys().add(KeyCode.W);
        updateMethod.invoke(gameLoopUnderTest,characterListUnderTest);
        redrawMethod.invoke(drawingLoopUnderTest,characterListUnderTest);
        floatingCharacter.onFloor();
        assertTrue("Model: Character is ReachFloor", characterListUnderTest.get(0).checkReachFloorTest());
        assertEquals("View: Character is really reach floor", characterListUnderTest.get(0).getY() , Platform.GROUND);
        assertTrue("Controller: Left key pressing is acknowledged",platformUnderTest.getKeys().isPressed(KeyCode.W));
        assertTrue("Model: Character Can be jump", characterListUnderTest.get(0).isCanJump());
    }

    @Test
    public void characterCannotBeJumpIfCharacterIsOnTheAirIMeanWhenThePressedDuringCharacterJump() throws IllegalAccessException, InvocationTargetException {
        platformUnderTest.getKeys().add(KeyCode.W);
        updateMethod.invoke(gameLoopUnderTest,characterListUnderTest);
        redrawMethod.invoke(drawingLoopUnderTest,characterListUnderTest);
        assertFalse("Model: Character is jumping but not reach the floor yet", characterListUnderTest.get(0).checkReachFloorTest());
        assertTrue("View: Character is Not reach floor", characterListUnderTest.get(0).getY() < Platform.GROUND);
        assertTrue("Controller: Left key pressing is acknowledged",platformUnderTest.getKeys().isPressed(KeyCode.W));
        assertFalse("Model: Character Can not be jump", characterListUnderTest.get(0).isCanJump());
    }

    @Test
    public void characterCannotPassedTheInvisibleWallThatCreatorJustCreateThatMeanCharacterInTheCageSorryMario() throws IllegalAccessException, InvocationTargetException {
        platformUnderTest.getKeys().add(KeyCode.D);
        updateMethod.invoke(gameLoopUnderTest,characterListUnderTest);
        redrawMethod.invoke(drawingLoopUnderTest,characterListUnderTest);
        floatingCharacter.ReachGameWallTest();
        assertTrue("View: Character is Reach The game border", floatingCharacter.checkReachGameWallTest());
    }

    @Test
    public void characterCollisionOnEachOtherAndItNeedToBeBack() throws IllegalAccessException, InvocationTargetException, InterruptedException {
        updateMethod.invoke(gameLoopUnderTest,characterListUnderTest);
        redrawMethod.invoke(drawingLoopUnderTest,characterListUnderTest);
        characterListUnderTest.get(0).setX(Platform.WIDTH / 2);
        characterListUnderTest.get(1).setX(Platform.WIDTH / 2);
        assertTrue("View: Is collision of each other", characterListUnderTest.get(0).getBoundsInParent().intersects(characterListUnderTest.get(0).getBoundsInParent()));
        characterListUnderTest.get(0).collidedTest(characterListUnderTest.get(1));
        assertEquals("Model: Character need to be back a bit", characterListUnderTest.get(0).getX()-(characterListUnderTest.get(1).getX()), 5);
    }

    @Test
    public void characterStompedToAnotherCharacterAndGetScoreSoIMadeTheCharacterToKillEachOtherForScore() throws IllegalAccessException, InvocationTargetException, InterruptedException {
        updateMethod.invoke(gameLoopUnderTest,characterListUnderTest);
        redrawMethod.invoke(drawingLoopUnderTest,characterListUnderTest);
        characterListUnderTest.get(0).setY(Platform.HEIGHT / 2 + 1);
        characterListUnderTest.get(1).setY(Platform.HEIGHT / 2 + Character.CHARACTER_HEIGHT);
        characterListUnderTest.get(0).setX(Platform.WIDTH / 2);
        characterListUnderTest.get(1).setX(Platform.WIDTH / 2);
        assertTrue("Model: Character Stomped To each other" , characterListUnderTest.get(0).stompedTest(characterListUnderTest.get(1)));
        assertEquals("Initial x", Platform.WIDTH-60, characterListUnderTest.get(1).getX(), 0);
        assertEquals("Initial y", 30, characterListUnderTest.get(1).getY(), 0);
    }

    @Test
    public void characterGetStompedByAnotherCharacterAndGetToOriginalPlaceOfTheirRespawnPointSoSad() throws IllegalAccessException, InvocationTargetException, InterruptedException {
        updateMethod.invoke(gameLoopUnderTest,characterListUnderTest);
        redrawMethod.invoke(drawingLoopUnderTest,characterListUnderTest);
        characterListUnderTest.get(1).setY(Platform.HEIGHT / 2 + 1);
        characterListUnderTest.get(0).setY(Platform.HEIGHT / 2 + Character.CHARACTER_HEIGHT);
        characterListUnderTest.get(1).setX(Platform.WIDTH / 2);
        characterListUnderTest.get(0).setX(Platform.WIDTH / 2);
        assertTrue("Model: Character Stomped To each other" , characterListUnderTest.get(1).stompedTest(characterListUnderTest.get(0)));
        assertEquals("Initial x", 30, characterListUnderTest.get(0).getX(), 0);
        assertEquals("Initial y", 30, characterListUnderTest.get(0).getY(), 0);
    }

}


