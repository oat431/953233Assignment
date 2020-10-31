package test;
import controller.GameLoop;
import model.Snake;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({SnakeTest.class, GameLoopTest.class})
public class JunitTestSuite { }