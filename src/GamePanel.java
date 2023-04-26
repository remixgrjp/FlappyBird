import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class GamePanel extends Panel implements Runnable{
	private Game game;
	Image imageOff= null;
	Graphics gOff= null;

	public GamePanel(){
		game= new Game();
		new Thread( this ).start();
	}

	public void update(){
		game.update();

		if( null == imageOff ) return;

		Graphics2D g2D= (Graphics2D)gOff;
		for( Render r : game.getRenders() )
			if( r.transform != null )
				g2D.drawImage( r.image, r.transform, null );
			else
				gOff.drawImage( r.image, r.x, r.y, null );

		g2D.setColor( Color.BLACK );

		if( ! game.started ){
			g2D.setFont( new Font( "TimesRoman", Font.PLAIN, 20 ) );
			g2D.drawString( "Press SPACE to start", 150, 240 );
		}else{
			g2D.setFont(new Font( "TimesRoman", Font.PLAIN, 24 ) );
			g2D.drawString( Integer.toString( game.score ), 10, 465 );
		}
		g2D.drawString( String.format( "x:%+d, y:%+d, yvel:%+3.1f", game.getBirdX(), game.getBirdY(), game.getBirdV() ), 150, 465 );//bird position

		if( game.gameover ){
			g2D.setFont(new Font( "TimesRoman", Font.PLAIN, 20 ) );
			g2D.drawString( "Press R to restart", 150, 240 );
		}
		gOff.drawImage( imageOff, 0, 0, this );

		repaint();
	}

	public void update( Graphics g ){
		paint( g );
	}

	public void paint( Graphics g ){
		if( null == imageOff ){
			imageOff= createImage( getSize().width, getSize().height );//not call at constructor
			gOff= imageOff.getGraphics();
		}
		g.drawImage( imageOff, 0, 0, this );
	}

	public void run(){
		try{
			while( true ){
				update();
				Thread.sleep( 25 );
			}
		}catch( Exception e ){
			e.printStackTrace();
		}
	}
}
