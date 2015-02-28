package agentpicmerge;

import java.util.ArrayList;
import processing.core.PApplet;

public class Flock {
	PApplet p;
	// Flock class
	// Does very little, simply manages the ArrayList of all the boids  
	ArrayList boids; // An arraylist for all the boids

	    Flock(PApplet p_) {
	    	p = p_;
	    boids = new ArrayList(); // Initialize the arraylist
	  }

	  void run() {
	    for (int i = 0; i < boids.size(); i++) {
	      Boid b = (Boid) boids.get(i);  
	      b.run(boids);  // Passing the entire list of boids to each boid individually
	    }
	  }

	  void addBoid(Boid b) {
	    boids.add(b);
	  }


	
}