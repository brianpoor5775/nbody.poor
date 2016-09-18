
public class Planet {
	//Instance Variables
	double myXPos, myYPos, myXVel, myYVel, myMass;
	String myFileName;
	
	//Constants
	final double G = 6.67 * Math.pow(10.0, -11);
	
	public Planet(double xP, double yP, double xV,
            double yV, double m, String img){
		myXPos = xP;
		myYPos = yP;
		myXVel = xV;
		myYVel = yV;
		myMass = m;
		myFileName = img;
	}
	public Planet(Planet P){
		this(P.myXPos, P.myYPos, P.myXVel, P.myYVel, P.myMass, P.myFileName);
	}
	
	//Methods
	public double calcDistance(Planet otherPlanet){
		return Math.pow((myXPos-otherPlanet.myXPos)*(myXPos-otherPlanet.myXPos) + (myYPos-otherPlanet.myYPos)*(myYPos-otherPlanet.myYPos), 0.5);
	}
	
	public double calcForceExertedBy(Planet otherPlanet){
		return G * myMass * otherPlanet.myMass / Math.pow(calcDistance(otherPlanet), 2);
	}
	public double calcForceExertedByX(Planet otherPlanet){
		return calcForceExertedBy(otherPlanet) * (otherPlanet.myXPos - myXPos) / calcDistance(otherPlanet);
		
	}
	public double calcForceExertedByY(Planet otherPlanet){
		return calcForceExertedBy(otherPlanet) * (otherPlanet.myYPos - myYPos) / calcDistance(otherPlanet);
		
	}
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double sum = 0;
		for(int i = 0; i < allPlanets.length; i++){
			if (! allPlanets[i].equals(this)) {
			    sum += calcForceExertedByX(allPlanets[i]);
			}
		}
		return sum;
	}
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double sum = 0;
		for(int i = 0; i < allPlanets.length; i++){
			if (! allPlanets[i].equals(this)) {
			    sum += calcForceExertedByY(allPlanets[i]);
			}
		}
		return sum;
	}
	public void update(double seconds, double xforce, double yforce){
		double ax = xforce/this.myMass;
		double ay = yforce/this.myMass;
		
		myXVel += ax*seconds;
		myYVel += ay*seconds;
		
		myXPos += myXVel * seconds;
		myYPos += myYVel * seconds;
	}
	public void draw(){
		StdDraw.picture(myXPos, myYPos, "./images/" + myFileName);
	}
}
