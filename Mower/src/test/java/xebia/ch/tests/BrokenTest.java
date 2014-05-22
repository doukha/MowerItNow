package xebia.ch.tests;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.util.Assert;

import xebia.ch.controller.MowerController;
import xebia.ch.model.Direction;
import xebia.ch.model.Instruction;
import xebia.ch.model.Mower;
import xebia.ch.model.Position;

public class BrokenTest extends AbstractTest {

	private static final Logger LOGGER = Logger.getLogger(BrokenTest.class.getName());
	// init mower 1
	private List<Instruction> instructions1 = Arrays.asList(Instruction.G, Instruction.A, Instruction.G, Instruction.A, Instruction.G,
			Instruction.A, Instruction.G, Instruction.A, Instruction.A);
	private Mower mower1 = new Mower("Mower 1", lawn, new Position(1, 2, Direction.NORTH), instructions1);

	// init mower 2
	private List<Instruction> instruction2 = Arrays.asList(Instruction.A, Instruction.A, Instruction.D, Instruction.A, Instruction.A,
			Instruction.D, Instruction.A, Instruction.D, Instruction.D, Instruction.A);
	private Mower mower2 = new Mower("Mower 2", lawn, new Position(3, 3, Direction.EAST), instruction2);

	// init mower 3
	private Mower mower3 = new Mower("Mower 3", lawn, new Position(5, 1, Direction.EAST), null);

	// init mower 4 : The fifth instruction will not be executed cause the
	// position is occupied by Mower1
	private List<Instruction> instructions4 = Arrays.asList(Instruction.A, Instruction.A, Instruction.A, Instruction.D, Instruction.A,
			Instruction.D, Instruction.A, Instruction.G, Instruction.A);
	private Mower mower4 = new Mower("Mower 4", lawn, new Position(0, 0, Direction.NORTH), instructions4);

	// init mower 5 :
	// position is occupied by Mower1
	private List<Instruction> instructions5 = Arrays.asList(Instruction.A, Instruction.G, Instruction.A, Instruction.D, Instruction.A,
			Instruction.A, Instruction.G, Instruction.A, Instruction.A);
	private Mower mower5 = new Mower("Mower 5", lawn, new Position(0, 0, Direction.SOUTH), instructions5);

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testCollisionMower() {
		LOGGER.info("*************************************************");
		LOGGER.info("************* Start Collision Case **************");
		LOGGER.info("*************************************************");

		super.mowers.add(mower1);
		super.mowers.add(mower4);
		MowerController mowerController = new MowerController(super.mowers);
		mowerController.run();

		Assert.isTrue(mower1.getIncidents().isEmpty());
		Assert.notEmpty(mower4.getIncidents());
		Assert.isTrue(mowerController.hasNoIncident());
		LOGGER.info("*************************************************");
		LOGGER.info("*****End Collision Case *****");
		LOGGER.info("*************************************************");

	}

	@Test
	public void testOccupiedStartPosition() {
		LOGGER.info("*************************************************");
		LOGGER.info("*****Start Out Occupied Start Position Case *****");
		LOGGER.info("*************************************************");

		super.mowers.add(mower1);
		super.mowers.add(mower2);
		super.mowers.add(mower3); // it start position is occupied
		MowerController controller = new MowerController(super.mowers);
		controller.run();
		Assert.isTrue(!controller.hasNoIncident());
		Assert.isTrue(mower1.getIncidents().isEmpty());
		Assert.isTrue(mower2.getIncidents().isEmpty());
		// Assert
		LOGGER.info("*************************************************");
		LOGGER.info("****** End Occupied Start Position Case *********");
		LOGGER.info("*************************************************");
	}
	
	/**
	 * TODO
	 */
	@Test
	public void testOutOfLawn() {
		LOGGER.info("*************************************************");
		LOGGER.info("*********** Start Out Of Lawn Case **************");
		LOGGER.info("*************************************************");

		super.mowers.add(mower5);
		MowerController mowerController = new MowerController(super.mowers);
		mowerController.run();
		Assert.isTrue(!mower5.getIncidents().isEmpty());
		assertEquals(mower5.getIncidents().size(), 3);
		// Assert
		LOGGER.info("*************************************************");
		LOGGER.info("*********** End Out OF Lawn Case ****************");
		LOGGER.info("*************************************************");
	}
}
