import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class HomeSolarSystem extends SolarSystem {

	// incarcarea texturilor pentru planete si satelitii naturali ai acestora
	private Texture mercury, venus, earth, mars, jupiter, saturn, uranus,
			neptune, pluto;
	private Texture moon, phobos, deimos, io, callisto, ganymedes, europa,
			charon;

	public HomeSolarSystem(Sun sun, Texture sunTexture) throws IOException {
		super(sun, sunTexture);

		// citeste texturile planetelor
		mercury = TextureLoader.getTexture("PNG",
				ResourceLoader.getResourceAsStream("res/mercury.png"));
		venus = TextureLoader.getTexture("PNG",
				ResourceLoader.getResourceAsStream("res/venus.png"));
		earth = TextureLoader.getTexture("PNG",
				ResourceLoader.getResourceAsStream("res/earth.png"));
		mars = TextureLoader.getTexture("PNG",
				ResourceLoader.getResourceAsStream("res/mars.png"));
		jupiter = TextureLoader.getTexture("PNG",
				ResourceLoader.getResourceAsStream("res/jupiter.png"));
		saturn = TextureLoader.getTexture("PNG",
				ResourceLoader.getResourceAsStream("res/saturn.png"));
		uranus = TextureLoader.getTexture("PNG",
				ResourceLoader.getResourceAsStream("res/uranus.png"));
		neptune = TextureLoader.getTexture("PNG",
				ResourceLoader.getResourceAsStream("res/neptune.png"));
		pluto = TextureLoader.getTexture("PNG",
				ResourceLoader.getResourceAsStream("res/pluto.png"));

		// citeste texturile pentru satelitii naturali
		moon = TextureLoader.getTexture("PNG",
				ResourceLoader.getResourceAsStream("res/moon.png"));
		phobos = TextureLoader.getTexture("PNG",
				ResourceLoader.getResourceAsStream("res/phobos.png"));
		deimos = TextureLoader.getTexture("PNG",
				ResourceLoader.getResourceAsStream("res/deimos.png"));
		europa = TextureLoader.getTexture("PNG",
				ResourceLoader.getResourceAsStream("res/europa.png"));
		io = TextureLoader.getTexture("PNG",
				ResourceLoader.getResourceAsStream("res/io.png"));
		ganymedes = TextureLoader.getTexture("PNG",
				ResourceLoader.getResourceAsStream("res/ganymede.png"));
		callisto = TextureLoader.getTexture("PNG",
				ResourceLoader.getResourceAsStream("res/callisto.png"));
		charon = TextureLoader.getTexture("PNG",
				ResourceLoader.getResourceAsStream("res/charon.png"));
	}

	

	public void create() {
		// create mercury
		drawMercury(mercury);
		// create venus
		drawVenus(venus);
		// create earth and moon
		drawEarth(earth, moon);
		// create mars and its moons
		drawMars(mars, phobos, deimos);
		// create Jupiter
		drawJupiter(jupiter, io, ganymedes, europa, callisto);
		// create Saturn
		drawSaturn(saturn);
		// create Uranus
		drawUranus(uranus);
		// create Neptune
		drawNeptune(neptune);
		// create Pluto
		drawPluto(pluto, charon);
	}

	/*
	 * Draw mercury based on planet's radius, orbit and tilt
	 */
	private void drawMercury(Texture mercury) {
		this.createPlanet(4f, 200f, 0.0f, mercury, 0.042f);
	}

	/*
	 * Draw venus based on planet's radius, orbit and tilt
	 */
	private void drawVenus(Texture venus) {
		this.createPlanet(6f, 250f, 177.36f, venus, 0.016f);
	}

	/*
	 * Draw earth and its moon based on planet's and moon's radius, orbit and
	 * tilt
	 */
	private void drawEarth(Texture earth, Texture moon) {
		Moon earth_moon = new Moon(0.5f, 5f, 23.45f, 0.01f);
		earth_moon.setTexture(moon);
		List<Moon> earth_moons = new ArrayList<Moon>();
		earth_moons.add(earth_moon);

		this.createPlanet(10f, 380f, 23.45f, earth, earth_moons, 0.01f);
	}

	/*
	 * Draw mars and its moons, phobos and deimos based on planet's and its
	 * moons radius, orbit and tilt
	 */
	private void drawMars(Texture mars, Texture phobos, Texture deimos) {
		Moon mars_moon_a = new Moon(0.22f, 3f, 25.19f, 0.03f);
		Moon mars_moon_b = new Moon(0.14f, 5f, 25.19f, 0.0077f);
		mars_moon_a.setTexture(phobos);
		mars_moon_b.setTexture(deimos);
		List<Moon> mars_moons = new ArrayList<Moon>();
		mars_moons.add(mars_moon_a);
		mars_moons.add(mars_moon_b);

		this.createPlanet(6f, 480f, 25.19f, mars, mars_moons, 0.0053f);
	}

	/*
	 * Draw jupiters and its moons, io,ganymedes, callisto and europa based on
	 * planet's and its moons radius, orbit and tilt
	 */
	private void drawJupiter(Texture jupiter, Texture io, Texture ganymedes,
			Texture europa, Texture callisto) {
		 Moon jupiter_moon_a = new Moon(1.0f,20f, 3.13f, 0.0028f);
		 Moon jupiter_moon_b = new Moon(1.2f,25f, 3.13f, 0.0056f);
		 Moon jupiter_moon_c = new Moon(1.4f,28f, 3.13f, 0.0014f);
		 Moon jupiter_moon_d = new Moon(1.8f,40f, 3.13f, 0.00059f);
		 jupiter_moon_a.setTexture(europa);
		 jupiter_moon_b.setTexture(io);
		 jupiter_moon_c.setTexture(ganymedes);
		 jupiter_moon_d.setTexture(callisto);
		List<Moon> jupiter_moons = new ArrayList<Moon>();
		 jupiter_moons.add(jupiter_moon_a);
		 jupiter_moons.add(jupiter_moon_b);
		 jupiter_moons.add(jupiter_moon_c);
		 jupiter_moons.add(jupiter_moon_d);

		this.createPlanet(14.29f, 600f, 3.13f, jupiter, jupiter_moons, 0.00084f);
	}

	/*
	 * Draw saturn and its rings based on planet's radius, orbit and tilt
	 */
	private void drawSaturn(Texture saturn) {
		List<Moon> saturn_moons = new ArrayList<Moon>();

		List<Float[]> ringsSpecs = new ArrayList<Float[]>();
		List<Float[]> colorSpecs = new ArrayList<Float[]>();

		Float[] firstRing = new Float[2];
		firstRing[0] = 16f;
		firstRing[1] = 17f;
		Float[] firstColor = new Float[3];
		firstColor[0] = 139f;
		firstColor[1] = 131f;
		firstColor[2] = 120f;
		ringsSpecs.add(firstRing);
		colorSpecs.add(firstColor);

		Float[] secondRing = new Float[2];
		secondRing[0] = 17f;
		secondRing[1] = 19f;
		Float[] secondColor = new Float[3];
		secondColor[0] = 255f;
		secondColor[1] = 239f;
		secondColor[2] = 219f;
		ringsSpecs.add(secondRing);
		colorSpecs.add(secondColor);

		Float[] thirdRing = new Float[2];
		thirdRing[0] = 20f;
		thirdRing[1] = 22f;
		Float[] thirdColor = new Float[3];
		thirdColor[0] = 205f;
		thirdColor[1] = 192f;
		thirdColor[2] = 176f;
		ringsSpecs.add(thirdRing);
		colorSpecs.add(thirdColor);

		this.createPlanet(12f, 700f, 26.73f, saturn, saturn_moons, ringsSpecs,
				colorSpecs, 0.00034f);
	}

	/*
	 * Draw uranus and its rings based on planet's radius, orbit and tilt
	 */
	private void drawUranus(Texture uranus) {
		this.createPlanet(5.1f, 850f, 97.77f, uranus, 0.00012f);
	}

	/*
	 * Draw neptune and its rings based on planet's radius, orbit and tilt
	 */
	private void drawNeptune(Texture neptune) {
		this.createPlanet(4.9f, 950f, 28.32f, neptune, 0.000061f);
	}

	/*
	 * Draw pluto and its rings based on planet's radius, orbit and tilt
	 */
	private void drawPluto(Texture pluto, Texture charon) {
		Moon pluto_moon = new Moon(0.15f, 5f, 122.53f, 0.0016f);
		pluto_moon.setTexture(charon);
		List<Moon> pluto_moons = new ArrayList<Moon>();
		pluto_moons.add(pluto_moon);

		this.createPlanet(0.25f, 1000f, 122.53f, pluto, pluto_moons, 0.000040f);
	}


}
