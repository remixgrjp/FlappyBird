import java.awt.Image;

public class Pipe{
	public int x;//left
	public int y;//top
	public int width;
	public int height;
	public int speed= 3;

	private Image image;

	public int x2;//left
	public int y2;//top
	private Image image2;

	public Pipe(){
		reset();
	}

	public void reset(){
		width= 66;
		height= 400;
		x= App.WIDTH;
		y= height / 2 + 175 -(int)(Math.random() * 120);//175:constant space pair

		x2= App.WIDTH;
		y2= y - height - 175;
	}

	public void update(){
		x -= speed;
		x2 -= speed;
	}

	public boolean collides( int _x, int _y, int _width, int _height ){
		int margin= 2;

		if( _x + _width - margin > x && _x + margin < x + width ){
//			if(       orientation.equals( "south" ) && _y < y + height ){
//				return true;
//			}else if( orientation.equals( "north" ) && _y +_height > y ){
			if( _y +_height > y ){
				return true;
			}
			if( _y < y2 + height ){
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

	public Render getRender2(){
		Render r= new Render();
		r.x= x2;
		r.y= y2;

		if( image2 == null ){
			image2= Util.loadImage( "lib/pipe.png" );

			//Flip Upside Down
			java.awt.geom.AffineTransform tx= java.awt.geom.AffineTransform.getScaleInstance( 1, -1 );
			tx.translate( 0, -image2.getHeight( null ) );
			java.awt.image.AffineTransformOp op = new java.awt.image.AffineTransformOp( tx, java.awt.image.AffineTransformOp.TYPE_NEAREST_NEIGHBOR );
			image2= op.filter( (java.awt.image.BufferedImage)image2, null );

//			BufferedImage bImg =new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_ARGB);  
//			Graphics g = bImg.getGraphics();  
//			g.drawImage(img,0,0,null);  

//			//上下反転原始的方法 実態複製する必要
//			int w= image2.getWidth( null );//最初-1
//			int h= image2.getHeight( null );//最初-1
//	System.out.println( String.format( "[w:%d h:%d]", w, h ) );
//			for( int x= 0; w > x; x++ ){
//				for( int ys= 0, ye= h-1; ys < ye; ys++, ye-- ){
//					int s= ((java.awt.image.BufferedImage)image2).getRGB( x, ys );
//					int d= ((java.awt.image.BufferedImage)image2).getRGB( x, ye );
//					((java.awt.image.BufferedImage)image2).setRGB( x, ys, d );
//					((java.awt.image.BufferedImage)image2).setRGB( x, ye, s );
//				}
//			}

		}
		r.image= image2;

		return r;
	}
}
