package xebia.ch.aop;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Timer {

	private static final Logger LOGGER = Logger.getLogger(Timer.class.getCanonicalName());

	/**
	 * Insert a timer after each instruction.
	 * 
	 * @param jointPoint
	 */
	@Before("execution (* xebia.ch.model.Mower.performsInstruction(..))")
	public void timerBefore(JoinPoint jointPoint) {
		LOGGER.info("Next step ...");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			LOGGER.log(Level.FATAL, "Timer Exception !");
			e.printStackTrace();
		}
	}
}
