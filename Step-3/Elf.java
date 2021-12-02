import java.util.Random;

public class Elf implements Runnable {

	enum ElfState {
		WORKING, TROUBLE, AT_SANTAS_DOOR
	};

	private ElfState state;
	/**
	 * The number associated with the Elf
	 */
	private int number;
	private Random rand = new Random();
	private SantaScenario scenario;

	public Elf(int number, SantaScenario scenario) {
		this.number = number;
		this.scenario = scenario;
		this.state = ElfState.WORKING;
	}


	public ElfState getState() {
		return state;
	}

	/**
	 * Santa might call this function to fix the trouble
	 * @param state
	 */
	public void setState(ElfState state) {
		this.state = state;
	}


	@Override
	public void run() {
		while (true) {

      // wait a day
  		try {
  			Thread.sleep(100);
  		} catch (InterruptedException e) {
  			// TODO Auto-generated catch block
  			//e.printStackTrace();
  		}
			switch (state) {
			case WORKING: {
				// at each day, there is a 1% chance that an elf runs into
				// trouble.
				if (rand.nextDouble() < 0.1) {
					state = ElfState.TROUBLE;
				}
				break;
			}
			case TROUBLE:
				// FIXME: if possible, move to Santa's door
				if (!scenario.troubledElves.contains(this) &&  scenario.troubledElves.size() < 3) {
					scenario.troubledElves.add(this);
					if (scenario.troubledElves.size() == 3) {
						for (Elf elf: scenario.troubledElves) {
							elf.setState(ElfState.AT_SANTAS_DOOR);
						}
					}
				}
				break;
			case AT_SANTAS_DOOR:
				// FIXME: if feasible, wake up Santa
				int count = 0;
				for (Elf elf: scenario.troubledElves) {
					if (elf.getState() == Elf.ElfState.AT_SANTAS_DOOR) {
						count++;
					}
				}

				if (count == 3) scenario.santa.setState(Santa.SantaState.WOKEN_UP_BY_ELVES);
				break;
			}
		}
	}

	/**
	 * Report about my state
	 */
	public void report() {
		System.out.println("Elf " + number + " : " + state);
	}

}
