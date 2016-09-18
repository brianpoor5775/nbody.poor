import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class NBody {
	public static double readRadius(String fname){
		double result = 0;
		try{
		File file = new File("./data/planets.txt");
		Scanner scan = new Scanner(file);
		scan.nextDouble();
		result = scan.nextDouble();
		scan.close();
		} catch (Exception ex) {
            ex.printStackTrace();
        }
		return result;
	}
	public static Planet[] readPlanets(String fname){
		Planet[] planetArray = new Planet[5];
		int arraycount = 0;
		try{
			File file = new File(fname);
			Scanner scan = new Scanner(file);
			scan.nextInt();
			scan.nextDouble();
			
			while (//scan.hasNext() == true
					arraycount <= 5){
				double xp, yp, xv, yv, m;
				String name;
				xp = scan.nextDouble();
				yp = scan.nextDouble();
				xv = scan.nextDouble();
				yv = scan.nextDouble();
				m = scan.nextDouble();
				name = scan.next();
				
				planetArray[arraycount] = new Planet(xp, yp, xv, yv ,m, name);
				arraycount++;
			}
		
		
		}catch (Exception ex) {
	        ex.printStackTrace();
	    }
	return planetArray;
	}

	public static void main(String[] args){
		double T = 157788000.0;
		double dt = 25000.0;
		String pfile = "input/planets.txt";
		if (args.length > 2) {
			T = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		}	
		//Planet[] planets = null;
		Planet[] planets = readPlanets("./data/planets.txt");
		double radius = readRadius("./data/planets.txt");
		
		double[]xForce = new double[planets.length];
		double[]yForce = new double[planets.length];
		
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "./images/starfield.jpg");
		for (int i = 0; i < planets.length; i++) {
		   planets[i].draw();	
		}
		for (int t = 0; t <= T; t+= dt){
			for(int n = 0; n < planets.length; n++){
				xForce[n] = planets[n].calcNetForceExertedByX(planets);
				yForce[n] = planets[n].calcNetForceExertedByY(planets);
			}
			for(int n = 0; n < planets.length; n++){
				planets[n].update(dt, xForce[n], yForce[n]);
				
			}
			
			StdDraw.setScale(-radius, radius);
			StdDraw.picture(0, 0, "./images/starfield.jpg");
			
			for (int i = 0; i < planets.length; i++) {
			   planets[i].draw();	
			}
			StdDraw.show(10);
		}
		
		//Print Final State of Universe
		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              planets[i].myXPos, planets[i].myYPos, 
		                      planets[i].myXVel, planets[i].myYVel, 
		                      planets[i].myMass, planets[i].myFileName);	
		    
		}
	}
}
