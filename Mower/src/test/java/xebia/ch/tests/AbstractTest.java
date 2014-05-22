package xebia.ch.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import xebia.ch.model.Lawn;
import xebia.ch.model.Mower;

public abstract class AbstractTest extends TestCase {

	protected List<Mower> mowers = new ArrayList<Mower>();
	protected static final Lawn lawn = new Lawn(5, 5);

	@Override
	protected void setUp() throws Exception {
		mowers.clear();
	}

	@Override
	protected void tearDown() throws Exception {
		mowers = null;
	}

}
