package agentpicmerge;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
// to find the right pixel pixelarrayNr = x+(y*width);
import toxi.geom.Vec2D;

public class AgentPicMerge extends PApplet {

	
	
	PImage canvas, pic1, pic2;
	PVector agent;
	Flock flock;
	int boidAmount = 1;
	
	public void setup() {
		size(500,500);
		
	    
		  canvas = createImage(500, 500, RGB);
		  canvas.resize(width, height);
		  pic1 = loadImage("red.jpg");
		  pic1.resize(width, height);
		  pic2 = loadImage("green.jpg");
		  
		  agent = new PVector(width/2, height/2);
		  
		  flock = new Flock(this);
		  // Add an initial set of boids into the system
		  for (int i = 0; i < boidAmount; i++) {
		    flock.addBoid(new Boid(this, new Vec2D(width/2f,height/2f),3.0f, 0.05f));
		  }
		  
		  
	}

	public void draw() {
		background(0);
		
		canvas.loadPixels();
		pic1.loadPixels();
//		pic2.loadPixels();
		
		
		
//		if(key == 'w' && keyPressed){
//			agent.y -= 1;
//			if(agent.y >= height) agent.y = 0;
//		}
//		if(key == 's' && keyPressed == true){
//			agent.y += 1;
//			if(agent.y >= height) agent.y = 0;
//		}
//		if(key == 'a' && keyPressed == true){
//			agent.x -= 1;
//			if(agent.x < 0) agent.x = width;
//		}
//		if(key == 'd' && keyPressed == true){
//			agent.x += 1;
//			if(agent.x > width) agent.x = 0;
//		}
		
//		int pixelLoc = getPixLoc(agent.x, agent.y);
		
//		//get color of source pixel and apply it to the canvas
//		float r = red(pic1.pixels[pixelLoc]);
//		float g = green(pic1.pixels[pixelLoc]);
//		float b = blue(pic1.pixels[pixelLoc]);
//		canvas.pixels[pixelLoc] = color(r,g,b);
		
		
//		for(int i = 0; i < flock.boids.size(); i++)
//		{
			Boid tempBoid = (Boid) flock.boids.get(0);
			
//			canvas.pixels[getPixLoc(tempBoid.loc.x, tempBoid.loc.y)] = color(pic1.pixels[getPixLoc(tempBoid.loc.x, tempBoid.loc.y)]);
			
			int pixLoc = getPixLoc(round(tempBoid.loc.x), round(tempBoid.loc.y));
			
			if(mousePressed) canvas.pixels[pixLoc] = color(pic1.pixels[pixLoc]);

//		}
		
		
		
		
		//Update canvas pixel
		canvas.updatePixels();
		//display canvas
		image(canvas, 0, 0);
		
		flock.run();
		
	}
	
	int getPixLoc(float x_, float y_){
		
		int location = (int)(x_ + y_ * width);
		
		if(location < 0 || location > (width*height))
		{
			location = 0;
		}
		return location;
	}
	
	public void keyPressed(){
		
		
		
	}
	
//	public static void main(String _args[]) {
//		PApplet.main(new String[] { agentpicmerge.AgentPicMerge.class.getName() });
//	}
}
