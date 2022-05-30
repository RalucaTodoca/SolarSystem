import java.util.List;
import java.util.Random;

import org.ejml.factory.SingularMatrixException;
import org.ejml.simple.SimpleMatrix;

import org.lwjgl.util.glu.Sphere;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.glu.Disk;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;
import org.lwjgl.util.vector.Vector3f;

import org.newdawn.slick.opengl.Texture;

public class Asteroid{

  //aceasta clasa "descrie" un asteroid care va aparea random in sistemul solar
  //directia este un vector
  //theta descrie unde asteroidul va aparea in functie de raza cercului care este egala cu valoarea variabilei spawnDistance
  // aceasta implementare face ca asteroizii sa zboare spre soare mereu


  private float[] direction; //currently unused
  private float theta;
  private float[] worldCoordinates;
  private float spawnDistance;
  private float currentDistance;
  private float velocity = 4.0f;
  private float radius;
  private Sphere s;
  private Material m;

  private ShaderProgram sp;
  private ParticleSource partSource;

  public Asteroid(float spawn, float r){
    theta = (float) Math.random()*360;
    spawnDistance = spawn;
    currentDistance = spawnDistance;
    worldCoordinates = new float[]{0.0f, 0.0f, 0.0f};
    updateWorldCoordinates();
    radius = r;
    s = new Sphere();
    m = new Material();

    partSource = createParticleSource();
    
  }
  
	/**
   * Creați o sursă de particule. Îl inițiază și îl colorează.
     * Setează numărul de particule generate de la sursă, durata de viață a particulelor
     * și forța gravitațională care le mișcă în sau în afara sursei.

   */
  private ParticleSource createParticleSource(){
  
  	//create particle source initial position vector & velocity vector
      Vector3f inpos = new Vector3f(currentDistance,0,0);
      Vector3f invel = new Vector3f(-1f,0f,0f);
      
      ParticleSource s = new ParticleSource(inpos,invel);
      
      //choose random color for the particles of the source
      Random randomGenerator = new Random();
      
      float colorx = (float) (randomGenerator.nextDouble() - 1);
      float colory = (float) (randomGenerator.nextDouble() - 1);
      float colorz = (float) (randomGenerator.nextDouble() - 1);
      
      s.setNumberOfParticles(150);
      s.setLifetime(300);
      s.setGravity(new Vector3f(0,-0.000001f,0));
      s.setColor(new Vector3f((1-colorx),(1-colory),(1-colorz)));
      
      return s;
  }


  public void draw(){
    GL11.glPushMatrix();
      GL11.glRotatef(theta, 0, 1, 0);
      GL11.glTranslatef(currentDistance, 0.0f, 0.0f);
      updateWorldCoordinates();

      float[] spec = m.getSpecular();
	  float shi = m.getShininess();
      sp.setUniform4f("mat.specular", spec[0], spec[1], spec[2], spec[3]);
      sp.setUniform1f("mat.shininess", shi);
      sp.setUniform1i("mat.texture", 0);

      //s.setDrawStyle(GLU.GLU_FILL);
      //s.setTextureFlag(true);
      //GL13.glActiveTexture(GL13.GL_TEXTURE0);
      //this.m.getTexture().bind();

      //s.draw(radius, 64, 64);
      sp.setUniform1f("isSun", 1.0f);
      partSource.drawParticleSource();
      sp.setUniform1f("isSun", 0.0f);
    
      currentDistance -= velocity;
     
    GL11.glPopMatrix();
    
    partSource.setPosition(new Vector3f(currentDistance,0,0));
    updateParticleSource();
  }

  private void updateParticleSource() {
  

  	
  	//update second particle source
  	partSource.updateSource();
  	
  }
  
  public void setShader(ShaderProgram p){
    this.sp = p;
  }

  public void setTexture(Texture t){
    this.m.setTexture(t);
  }

  public void updateWorldCoordinates(){
    double[][] posA = new double[][]{
      {0},
      {0},
      {0},
      {1}
    };
    SimpleMatrix pos = new SimpleMatrix(posA);

    double[][] transA = new double[][]{
      {1, 0, 0, currentDistance},
      {0, 1, 0, 0},
      {0, 0, 1, 0},
      {0, 0, 0, 1}
    };
    SimpleMatrix trans = new SimpleMatrix(transA);

    double thetaRad = Math.toRadians(theta);
		double[][] rotYA = new double[][]{
			{Math.cos(thetaRad), 0, Math.sin(thetaRad), 0},
      {0,1,0,0},
      {-Math.sin(thetaRad), 0, Math.cos(thetaRad), 0},
      {0,0,0,1}
		};
    SimpleMatrix rot = new SimpleMatrix(rotYA);

    SimpleMatrix resultT = rot.mult(trans.mult(pos));

    worldCoordinates = new float[]{ (float) resultT.get(0), (float) resultT.get(1), (float) resultT.get(2)};

  }

  public void setTheta(float t){
    this.theta = t;
  }

  public float getTheta(){
    return this.theta;
  }

  public void setDirection(float[] d){
    this.direction = d;
  }

  public float[] getDirection(){
    return this.direction;
  }

  public void setSpawnDistance(float s){
    this.spawnDistance = s;
  }

  public float getSpawnDistance(){
    return this.spawnDistance;
  }

	public float getDistance() {
		return this.currentDistance;
	}
}
