//import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;


public class Santa implements Runnable {

	enum SantaState {SLEEPING, READY_FOR_CHRISTMAS, WOKEN_UP_BY_ELVES, WOKEN_UP_BY_REINDEER};
	private SantaState state;
	private SantaScenario scenario;
	
	public Santa(SantaScenario scenario) {
		this.state = SantaState.SLEEPING;
		this.scenario = scenario;
	}

	public void setState(SantaState state) {
		this.state = state;
	}

	public SantaState getState() {
		return state;
	}
	
	
	@Override
	public void run() {
		while(true) {
			// wait a day...
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch(state) {
			case SLEEPING: // if sleeping, continue to sleep
				break;
			case WOKEN_UP_BY_ELVES: 
				// FIXME: help the elves who are at the door and go back to sleep
				for (Elf elf: scenario.troubledElves) {
					elf.setState(Elf.ElfState.WORKING);
				} 

				scenario.troubledElves.clear();
				break;
			case WOKEN_UP_BY_REINDEER: 
				// FIXME: assemble the reindeer to the sleigh then change state to ready
				for (Reindeer reindeer: scenario.reindeers) {
					reindeer.setState(Reindeer.ReindeerState.AT_THE_SLEIGH);
				}
				setState(SantaState.READY_FOR_CHRISTMAS); 
				break;
			case READY_FOR_CHRISTMAS: // nothing more to be done
				break;
			}
		}
	}

	
	/**
	 * Report about my state
	 */
	public void report() {
		System.out.println("Santa : " + state);
	}
	
	
}
