import java.awt.Image;

public class Pipe{
	public int x;//left
	public int y;//top
	public int width;
	public int height;
	public int speed= 3;

	private Image image;

	public Pipe(){
		reset();
	}

	public void reset(){
		width= 66;
		height= 400;
		x= App.WIDTH;
		y= height / 2 + 175 -(int)(Math.random() * 120);//175:constant space pair
	}

	public void update(){
		x -= speed;
	}

	public boolean collides( int _x, int _y, int _width, int _height ){
		int margin= 2;

		if( _x + _width - margin > x && _x + margin < x + width ){
			if( _y +_height > y ){
				return true;
			}
		}

		return false;
	}

	public Render getRender(){
		Render r= new Render();
		r.x= x;
		r.y= y;

		if( image == null ){
			image= Util.loadImage( "lib/pipe.png" );
		}
		r.image= image;

		return r;
	}
}
