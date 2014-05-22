package xebia.ch.tests;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import xebia.ch.controller.MowerController;
import xebia.ch.model.Direction;
import xebia.ch.model.Instruction;
import xebia.ch.model.Mower;
import xebia.ch.model.Position;

/**
 * 
 * @author samyboukhris
 * 
 *         Test if the next displacement is authorized.
 */

public class MowerTest extends AbstractTest {

	private static final Logger LOGGER = Logger.getLogger(MowerTest.class.getName());
	// init mower 1
	private List<Instruction> instructions1 = Arrays.asList(Instruction.G, Instruction.A, Instruction.G, Instruction.A, Instruction.G,
			Instruction.A, Instruction.G, Instruction.A, Instruction.A);
	private Mower mower1 = new Mower("Mower 1", lawn, new Position(1, 2, Direction.NORTH), instructions1);

	// init mower 2
	private List<Instruction> instruction2 = Arrays.asList(Instruction.A, Instruction.A, Instruction.D, Instruction.A, Instruction.A,
			Instruction.D, Instruction.A, Instruction.D, Instruction.D, Instruction.A);
	private Mower mower2 = new Mower("Mower 2", lawn, new Position(3, 3, Direction.EAST), instruction2);

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testNominalCase() {
		LOGGER.info("*************************************************");
		LOGGER.info("*********** Start nominal Case ******************");
		LOGGER.info("*************************************************");
		
		super.mowers.add(mower1);
		super.mowers.add(mower2);

	
		MowerController mowerController = new MowerController(super.mowers);
		mowerController.run();
		Position mower1Position = mower1.getPosition();
		Assert.assertEquals(mower1Position.getX(), 1);
		Assert.assertEquals(mower1Position.getY(), 3);
		Assert.assertEquals(mower1Position.getDirection(), Direction.NORTH);

		Position mower2Position = mower2.getPosition();
		Assert.assertEquals(mower2Position.getX(), 5);
		Assert.assertEquals(mower2Position.getY(), 1);
		Assert.assertEquals(mower2Position.getDirection(), Direction.EAST);

		Assert.assertTrue(mower1.getIncidents().isEmpty());
		Assert.assertTrue(mower2.getIncidents().isEmpty());
		Assert.assertTrue(mowerController.hasNoIncident());
		
		LOGGER.info("*************************************************");
		LOGGER.info("*********** End nominal Case ******************");
		LOGGER.info("*************************************************");

	}
}
