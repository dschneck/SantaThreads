import java.util.ArrayList;
import java.util.List;



public class SantaScenario {

	public Santa santa;
	public List<Elf> elves;
	public List<Reindeer> reindeers;

	public List<Elf> troubledElves;
	public boolean isDecember;
	public int day;
	
	public static void main(String args[]) {
		SantaScenario scenario = new SantaScenario();
		scenario.isDecember = false;
		// create the participants
		// Santa
		scenario.santa = new Santa(scenario);
		Thread th = new Thread(scenario.santa);
		th.start();
		// The elves: in this case: 10
		scenario.elves = new ArrayList<>();
		scenario.troubledElves = new ArrayList<>();
		for(int i = 0; i != 10; i++) {
			Elf elf = new Elf(i+1, scenario);
			scenario.elves.add(elf);
			th = new Thread(elf);
			th.start();
		}
		// The reindeer: in this case: 9
		scenario.reindeers = new ArrayList<>();
		for(int i=0; i != 9; i++) {
			Reindeer reindeer = new Reindeer(i+1, scenario);
			scenario.reindeers.add(reindeer);
			th = new Thread(reindeer);
			th.start();
		}
		// now, start the passing of time
		for(scenario.day = 350; scenario.day < 500; scenario.day++) {
			// wait a day
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//Thread.currentThread().stop();
			}
			// turn on December
			if (scenario.day > (365 - 31)) {
				scenario.isDecember = true;
			}

			// print out the state:
			System.out.println("***********  Day " + scenario.day + " *************************");
			if (scenario.day > 370) {
				try {
					th.interrupt();
				} catch(Exception e) {

				}
			}

			else{

			scenario.santa.report();
			for(Elf elf: scenario.elves) {
				elf.report();
			}
			for(Reindeer reindeer: scenario.reindeers) {
				reindeer.report();
			}

		}
		}
	}
	
	
	
}
