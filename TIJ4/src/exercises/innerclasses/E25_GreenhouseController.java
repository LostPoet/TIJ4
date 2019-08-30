package exercises.innerclasses;

import innerclasses.GreenhouseControls;
import innerclasses.controller.Event;

class GreenhouseControlsWithWMG extends GreenhouseControls {
    @SuppressWarnings("unused")
    private boolean generator = false;

    public class WatermistGeneratorOn extends Event {
	public WatermistGeneratorOn(long delayTime) {
	    super(delayTime);
	}

	public void action() {
	    generator = true;
	}

	public String toString() {
	    return "Water mist generator is on";
	}
    }

    public class WatermistGeneratorOff extends Event {
	public WatermistGeneratorOff(long delayTime) {
	    super(delayTime);
	}

	public void action() {
	    generator = false;
	}

	public String toString() {
	    return "Water mist generator is off";
	}
    }
}

public class E25_GreenhouseController {
    public static void main(String[] args) {
	GreenhouseControlsWithWMG gc = new GreenhouseControlsWithWMG();
	// Instead of hard-wiring, you could parse
	// configuration information from a text file here:
	gc.addEvent(gc.new Bell(900));
	Event[] eventList = { gc.new ThermostatNight(0), gc.new LightOn(200), gc.new LightOff(400), gc.new WaterOn(600),
		gc.new WaterOff(800), gc.new ThermostatDay(1400), gc.new WatermistGeneratorOn(1600),
		gc.new WatermistGeneratorOff(1800) };
	gc.addEvent(gc.new Restart(2000, eventList));
	if (args.length == 1)
	    // use valueOf() in place of new Interger()
	    gc.addEvent(new GreenhouseControlsWithWMG.Terminate(Integer.valueOf(args[0])));
	gc.run();
    }
}
