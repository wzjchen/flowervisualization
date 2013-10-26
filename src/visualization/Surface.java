package visualization;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Surface extends JPanel implements ActionListener {
	
//	Flower flower1 = new Flower(Color.BLUE, 50, 300, 250, 10, );
//	Flower flower2 = new Flower(Color.RED, 25, 500, 350, 10);
//	Flower[] flowers  = {flower1, flower2};
//	
	int currentFrame = 0;
	
	int frameRate = 10;
	int currentTFrame = 0;
	
	Repository repo;
	
	private Timer timer;
	
	public Surface () {
		Initialize();
	}
	
	private void Initialize () {
	       timer = new Timer(400, this);
	       timer.start(); 
	       
	       repo = new Repository("src/visualization/sampleXMLFile.xml");
	}
	
    @Override
    public void actionPerformed (ActionEvent e) {
    	
    	Flower[] flowers = repo.frames[currentFrame].flowers;
    	
    	for(int i=0;i<flowers.length;i++){
			Flower flower = flowers[i];
			
				
			flower.makeDarker();
		    
		    //System.out.println("Darker");
		    
		    repaint();
		    
    	}
    	
    	if(currentTFrame > frameRate){
    		currentTFrame = 0;
    		
    		if(currentFrame < repo.frames.length-1)
    		currentFrame++;
    	}
    	
    	currentTFrame++;
    	
    }
    
    
	
	private void doDrawing(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;	
		
		Frame frame = repo.frames[currentFrame];
		
		drawFlowers(g2, frame.flowers);		
			
    }
	
	private void drawFlowers(Graphics2D g, Flower[] flowers) {
		
		for(int i=0;i<flowers.length;i++){
			Flower flower = flowers[i];
			
			g.setColor(flower.color);
			g.fillOval(flower.x-flower.size/2,flower.y-flower.size/2,flower.size,flower.size);
			drawPedals(g, flower);
		}
		
	}
	
	private void drawPedals(Graphics2D g, Flower flower){			
		
		int pedalSize = flower.size/2;
		
		for(int i=0;i<flower.numMethods;i++){
			g.fillOval(flower.x-pedalSize/2,flower.y + flower.size/2,pedalSize,pedalSize*2);
//			g.draw(new Ellipse2D.Double(flower.x-pedalSize/2,flower.y + flower.size/2,pedalSize,pedalSize*2));
			g.rotate(Math.toRadians(360/flower.numMethods),flower.x,flower.y);
		}
	}

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}
