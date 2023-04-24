import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Bird{
	public int x;//left
	public int y;//top
	public int width;
	public int height;

	public boolean flapping;//or landed
	public boolean dead;

	public double yvel;//-10.0, -9.5, -9.0, -8.5,,, (accelerate, animate)
	public double gravity;//0.5

	private int jumpDelay;
	private double rotation;

	private Image image;
	private Keyboard keyboard;

	public Bird(){
		x= 100;
		y= 150;
		yvel= 0;
		width= 45;
		height= 32;
		gravity= 0.5;
		jumpDelay= 0;
		rotation= 0.0;
		flapping= true;
		dead= false;

		keyboard= Keyboard.getInstance();
	}

	public void update(){
		if( flapping ) yvel += gravity;//+=0.5

		if( jumpDelay > 0 )
			jumpDelay--;

		if( ! dead && keyboard.isDown(KeyEvent.VK_SPACE) && jumpDelay <= 0 ){
			yvel= -10;// -> calc rotation
			jumpDelay= 10;
		}

		if( flapping ) y += (int)yvel;
	}

	public Render getRender(){
		Render r= new Render();
		r.x= x;
		r.y= y;

		if( image == null ){
			image= Util.loadImage( "lib/bird.png" );
		}
		r.image= image;

		double d= 4.5 * yvel;//[DEG] 4.5*0.5=2.25 refine[= (90 * (yvel + 20) / 20) - 90;]
		if( d > 90.0 )//limit 90[DEG]
			d= 90.0;
		rotation= d * Math.PI / 180;//0<>180[DEG] -> 0<>pi[RAD]

		r.transform= new AffineTransform();
		r.transform.translate( x + width / 2, y + height / 2 );
		r.transform.rotate( rotation );//[RAD]
		r.transform.translate( -width / 2, -height / 2 );

		return r;
	}
}
