import java.awt.Frame;

public class App{
	public static int WIDTH= 500;
	public static int HEIGHT= 520;

	public static void main( String[] args ){
		Frame frame= new Frame();
		frame.setVisible( true );
		frame.addWindowListener( new java.awt.event.WindowAdapter(){
			public void windowClosing( java.awt.event.WindowEvent e ){
				System.exit( 0 );
			}
		} );
		frame.setLocationRelativeTo( null );//center

		Keyboard keyboard= Keyboard.getInstance();
		frame.addKeyListener( keyboard );

		GamePanel panel= new GamePanel();
		frame.add( panel );
		frame.setResizable( false );
		frame.setSize( WIDTH, HEIGHT );
	}
}
