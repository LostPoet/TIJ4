package exercises.interfaces.music5;

import static net.mindview.util.Print.print;
import polymorphism.music.Note;

interface Instrument {
    int VALUE = 5;

    void adjust();
}

interface Playable {
    void play(Note n);
}

class Wind implements Instrument, Playable {
    public void play(Note n) {
	print(this + ".play() " + n);
    }

    public String toString() {
	return "Wind";
    }

    public void adjust() {
	print(this + ".adjust()");
    }
}

class Percussion implements Instrument, Playable {
    public void play(Note n) {
	print(this + ".play() " + n);
    }

    public String toString() {
	return "Percussion";
    }

    public void adjust() {
	print(this + ".adjust()");
    }
}

class Stringed implements Instrument, Playable {
    public void play(Note n) {
	print(this + ".play() " + n);
    }

    public String toString() {
	return "Stringed";
    }

    public void adjust() {
	print(this + ".adjust()");
    }
}

class Brass extends Wind {
    public String toString() {
	return "Brass";
    }
}

class Woodwind extends Wind {
    public String toString() {
	return "Woodwind";
    }
}

public class E10_Music5 {
    static void tune(Playable i) {
	i.play(Note.MIDDLE_C);
    }

    static void tuneAll(Playable[] e) {
	for (Playable i : e)
	    tune(i);
    }

    public static void main(String[] args) {
	Playable[] orchestra = { new Wind(), new Percussion(), new Stringed(), new Brass(), new Woodwind() };
	tuneAll(orchestra);
    }
}
