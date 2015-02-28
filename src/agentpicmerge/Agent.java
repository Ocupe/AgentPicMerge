package agentpicmerge;
import processing.core.PApplet;
import processing.core.PVector;

public class Agent {
	
	PApplet p; // The parent PApplet that we will render ourselves onto

	PVector location;
	PVector speed;
	PVector acceleration;
	
	  Agent(PApplet p_) {
	    p = p_;
	    location = new PVector(p.width/2, p.height/2);
	    speed = new PVector(0, 0);
	    acceleration = new PVector(0,0);
	   
	  }
	  
	  void update() {
		    speed.add(acceleration);
		    location.add(speed);    
		    acceleration.mult((float) 0.0);
		  }

		  void display() {
		    p.ellipse(location.x, location.y, 20, 20);
		  }
		  
		  void applyForce(PVector f){
		   acceleration.add(f); 
		  }

		  void edgeTest() {
		    if (location.x > p.width){
		      location.x = p.width;
		      speed.x = speed.x * -1;      
		    } else if(location.x <= 0){
		      location.x = 0;
		      speed.x = speed.x * -1; 
		    }
		    if (location.y >= p.height){
		      location.y = p.height;
		      speed.y = speed.y * -1;
		    }else if(location.y <= 0){
		       location.y = 0;
		      speed.y = speed.y * -1;
		    }
		  }
	  
	  

}
