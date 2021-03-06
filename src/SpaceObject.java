import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;


public class SpaceObject {

	//space object's radius, orbit radius and tilt
	protected float radius;
	protected float orbitRadius;
	protected float axisTilt; // references an angle for rotational tilt.
	protected float speed; //  references the speed at which the revolution should update
	protected float pX;
	protected float pY;
	protected int id;

	//material used for object's shader
	protected Material material;

	//space object's angles with regards to rotation, revolution and tilt
	protected float rotationAngle = 0.0f;
	protected float revolutionAngle = 0.0f;
	protected float axisOffestTheta = 0.0f; //an offset theta from the y axis

	//delta value for changing rotation and revolution angles
	protected float rotationAngleDelta = 4.0f;
	protected float revolutionAngleDelta = 0.1f;

	public SpaceObject(){
		this.material = new Material();
	}
	

	public void draw(){
		//Update the revolution and rotation angles
		this.revolve();
		this.rotate();
	}
	

	public void revolve(){
		
		float[] coords = revolutionPlanet(0f, 0f,this.getPX(), this.getPY(), this.getRevolutionAngle(), this.getOrbitRadius(), this.getSpeedRate());
		this.setPX(coords[0]);
		this.setPY(coords[1]);
		this.setRevolutionAngle(coords[2]);
		GL11.glTranslatef(this.getPX(),0f, this.getPY());

	}


	public void rotate(){
		this.setRotationAngle( rotatePlanet(this.getRotationAngle(), this.getAxisTilt()));

	}

	public Material getMaterial(){
		return this.material;
	}

	public void setMaterial(Material mat){
		this.material = mat;
	}

	
	public float getSpeedRate(){
		return speed;
	}
	
	
	public Texture getTexture(){
		return this.material.getTexture();
	}

	public void setTexture(Texture texture){
		this.material.setTexture(texture);
	}

	public float getRevolutionAngle(){
		return this.revolutionAngle;
	}

	public void setPX(float x){
		pX = x;
	}
	
	public float getPX(){
		return pX;
	}
	
	public void setPY(float y){
		pY = y;
	}
	
	public float getPY(){
		return pY;
	}

	public void setRevolutionAngle(float revolutionAngle){
		this.revolutionAngle = revolutionAngle;
	}

	public float getRotationAngle(){
		return this.rotationAngle;
	}


	public void setRotationAngle(float rotationAngle){
		this.rotationAngle = rotationAngle;
	}
	
	public float getRevolutionDelta(){
		return this.revolutionAngleDelta;
	}

	public void setRevolutionDelta(float d){
		this.revolutionAngleDelta = d;
	}

	public float getRotationDelta(){
		return this.rotationAngleDelta;
	}

	public void setRotationDelta(float d){
		this.rotationAngleDelta = d;
	}

	public float getRadius(){
		return this.radius;
	}

	public void setRadius(float radius){
		this.radius = radius;
	}

	public float getOrbitRadius(){
		return this.orbitRadius;
	}

	public void setOrbitRadius(float orbitRadius){
		this.orbitRadius = orbitRadius;
	}
	
	public float getAxisTilt(){
		return axisTilt;
	}

	public int getId(){
		return this.id;
	}

	public void setId(int id){
		this.id = id;
	}


	public static float rotatePlanet(float rotationAngle, float axisTilt) {
		rotationAngle = 5f + rotationAngle;
		if (rotationAngle >= 360)
			rotationAngle = 0f;
		GL11.glRotatef(rotationAngle, (float) Math.cos(axisTilt), 1f, 0f); // rotate
																			// around
																			// center
		return rotationAngle;
	}


	public static float[] revolutionPlanet(float centerX, float centerY,
			float xCoord, float yCoord, float angle, float radius, float speed) {
		angle = speed + angle;
		if (angle >= (2 * Math.PI))
			angle = 0;
		xCoord = (float) (centerX + Math.sin(angle) * radius);
		yCoord = (float) (centerY + Math.cos(angle) * radius);
		return new float[] { xCoord, yCoord, angle };
	}
}
