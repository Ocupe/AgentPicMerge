package agentpicmerge;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
// to find the right pixel pixelarrayNr = x+(y*width);
import toxi.geom.Vec2D;

public class AgentPicMerge extends PApplet {
	
	PImage canvas, pic1, pic2;
	PVector agent;
	Flock flock01, flock02;
	int boidAmount = 10;
	
	public void setup() 
	{
		size(500,500);

		canvas = createImage(500, 500, RGB);
		pic1 = loadImage("pic01.jpg");
		pic2 = loadImage("pic02.jpg");
		pic1.resize(width, height);
		pic2.resize(width, height);
		pic1.loadPixels();
		pic2.loadPixels();
		
		flock01 = new Flock(this);
		// Add an initial set of boids into the system
		for (int i = 0; i < boidAmount; i++) {
			flock01.addBoid(new Boid(this, new Vec2D(width/2f,height/2f),3.0f, 0.05f));
		}
		
		flock02 = new Flock(this);
		// Add an initial set of boids into the system
		for (int i = 0; i < boidAmount; i++) {
			flock02.addBoid(new Boid(this, new Vec2D(width/2f,height/2f),3.0f, 0.05f));
		}
		  
		  
	}

	public void draw() 
	{
		background(0);

		if( pic1.isLoaded() && pic2.isLoaded() )
		{
			canvas.loadPixels();
			if( canvas.isLoaded() )
			{

				for( int i = 0; i < flock01.boids.size(); i++ )
				{
					Boid tempBoid = (Boid) flock01.boids.get(i);
					int pixLoc = getPixLoc(round(tempBoid.loc.x), round(tempBoid.loc.y));
					canvas.pixels[pixLoc] = pic1.pixels[pixLoc];
					
//					int pixLoc1 = getPixLoc(round(tempBoid.loc.x), round(tempBoid.loc.y -1 ));
//					canvas.pixels[pixLoc1] = color(pic1.pixels[pixLoc1], 10);
//					
//					int pixLoc2 = getPixLoc(round(tempBoid.loc.x + 1), round(tempBoid.loc.y));
//					canvas.pixels[pixLoc2] = color(pic1.pixels[pixLoc2], 10);
//					
//					int pixLoc3 = getPixLoc(round(tempBoid.loc.x), round(tempBoid.loc.y + 1));
//					canvas.pixels[pixLoc3] = color(pic1.pixels[pixLoc3], 10);
//					
//					int pixLoc4 = getPixLoc(round(tempBoid.loc.x - 1), round(tempBoid.loc.y));
//					canvas.pixels[pixLoc4] = color(pic1.pixels[pixLoc4], 10);

				}
				for( int i = 0; i < flock02.boids.size(); i++ )
				{
					Boid tempBoid = (Boid) flock02.boids.get(i);
					int pixLoc = getPixLoc(round(tempBoid.loc.x), round(tempBoid.loc.y));
					canvas.pixels[pixLoc] = color(pic2.pixels[pixLoc]);
				}

				//Update canvas pixel
				canvas.updatePixels();

				//display canvas
				image(canvas, 0, 0);
				
				flock01.run();
				flock02.run();
			}
		}
		
	}
	
	int getPixLoc( float x_, float y_ ){
		
		int location = (int)(x_ + y_ * width);
		
		if(location < 0 || location > ((width*height)-1))
		{
			location = 0;
		}
		return location;
	}
	
	public void keyPressed(){
		
		
		
	}
	
	public static void main(String _args[]) {
		PApplet.main(new String[] {  agentpicmerge.AgentPicMerge.class.getName() });
	}
}
