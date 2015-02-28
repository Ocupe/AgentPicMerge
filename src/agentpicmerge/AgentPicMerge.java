package agentpicmerge;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import punktiert.math.Vec;
import punktiert.physics.BConstantForce;
import punktiert.physics.VBoid;
import punktiert.physics.VParticle;
import punktiert.physics.VPhysics;
// to find the right pixel pixelarrayNr = x+(y*width);

public class AgentPicMerge extends PApplet {

	
	
	PImage canvas, pic1, pic2;
	PVector agent;
	
	VPhysics physics;
	BConstantForce force;
	
	public void setup() {
		size(500,500);
		
	    
		  canvas = createImage(500, 500, RGB);
		  pic1 = loadImage("red.jpg");
		  pic2 = loadImage("green.jpg");
		  
		  agent = new PVector(width/2, height/2);
		  
		  
		  //punktiert code
		  physics = new VPhysics(width, height);
		  physics.setWrappedSpace(true);
//		  force = new BConstantForce(new Vec(-1,0));
//		  physics.addBehavior(force);
		  
		  int amount = 10;

		  for (int i = 0; i < amount; i++) {
		    Vec pos = new Vec(random(width),random(height));
		    float rad = random(3, 6);
		    VBoid p = new VBoid(pos);
//		    p.swarm.setSeperationScale((float) (rad*.7));
		    p.setRadius(rad);
		    physics.addParticle(p);
		  }
		  
	}

	public void draw() {
		background(0);
		
		physics.update();
		

		
		pic1.loadPixels();
		pic2.loadPixels();
		canvas.loadPixels();
		
		for (VParticle p : physics.particles) 
		 {
			//find location of the pixel
			int pixelLoc = getPixLoc(p.x, p.y);
			
			//get color of source pixel and apply it to the canvas
			float r = red(pic1.pixels[pixelLoc]);
			float g = green(pic1.pixels[pixelLoc]);
			float b = blue(pic1.pixels[pixelLoc]);
			canvas.pixels[pixelLoc] = color(r,g,b);
			
			fill(255);
		 }
		
		
		if(key == 'w' && keyPressed){
			agent.y -= 1;
			if(agent.y >= height) agent.y = 0;
		}
		if(key == 's' && keyPressed == true){
			agent.y += 1;
			if(agent.y >= height) agent.y = 0;
		}
		if(key == 'a' && keyPressed == true){
			agent.x -= 1;
			if(agent.x < 0) agent.x = width;
		}
		if(key == 'd' && keyPressed == true){
			agent.x += 1;
			if(agent.x > width) agent.x = 0;
		}
		
		println("A.:"+agent);
		int pixelLoc = getPixLoc(agent.x, agent.y);
		
		//get color of source pixel and apply it to the canvas
		float r = red(pic1.pixels[pixelLoc]);
		float g = green(pic1.pixels[pixelLoc]);
		float b = blue(pic1.pixels[pixelLoc]);
		canvas.pixels[pixelLoc] = color(r,g,b);
		
		
		
		//Update pixels
		canvas.updatePixels();
		
		//display canvas
		image(canvas, 0, 0);
		
		//draw particles
		for(VParticle p : physics.particles)
		{
			ellipse(p.x, p.y, p.getRadius(), p.getRadius());
			println("P.:"+p.x+" "+p.y);
			println("2d:"+p.x());
		}
	}
	
	int getPixLoc(float x_, float y_){
		
		int location = (int)(x_ + (y_ * width));
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
