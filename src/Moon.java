import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;


public class Moon extends SpaceObject{

	private Sphere s;

	private ShaderProgram moonShader;

	//the planet this moon is rotating around
    private Planet centerPlanet;

    /**
     * Constructorul lunii (basic: raza lunii, raza orbitei si inclinarea pe axa).
     * @param radius
     * @param orbitRadius
     * @param axisTilt
     */
	public Moon(float radius,float orbitRadius, float axisTilt, float speed){
		this.radius = radius;
		this.orbitRadius = orbitRadius;
		this.axisTilt = axisTilt;
		this.speed = speed;
		this.rotationAngleDelta = 0.0f;
		this.revolutionAngleDelta = -5.0f;
		
		s = new Sphere();
	}

	public Sphere getSphere(){
		return s;
	}
	
	public void setCenter(Planet centerPlanet){
	    this.centerPlanet = centerPlanet;
	}
	
	public Planet getParent(){
	    return this.centerPlanet;
	}
	
	public void setShader(ShaderProgram p){
	    this.moonShader = p;
	}

	/**
	 * Desenează luna având în vedere textura, caracteristicile și umbrirea acesteia.
	 */
	public void draw(){
		
			s.setDrawStyle(GLU.GLU_FILL);
			s.setTextureFlag(true);
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			this.getTexture().bind();
			
			super.draw();

			float[] spec = material.getSpecular();
			float shi = material.getShininess();

			moonShader.setUniform4f("mat.specular", spec[0], spec[1], spec[2], spec[3]);
			moonShader.setUniform1f("mat.shininess", shi);
			moonShader.setUniform1i("mat.texture", 0);

			s.draw(radius, 64, 64);
			
			GL11.glRotatef(-this.centerPlanet.getRotationAngle(), 0, 1, 0);
	}  
}
