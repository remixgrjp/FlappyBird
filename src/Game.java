import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game{
	public static final int PIPE_DELAY= 100;

	private Boolean paused;

	private int pauseDelay;
	private int restartDelay;
	private int pipeDelay;

	private Bird bird;
	int getBirdX(){ return bird.x; };//for indicate
	int getBirdY(){ return bird.y; };//for indicate
	double getBirdV(){ return bird.yvel; };//for indicate
	private ArrayList<Pipe> pipes;
	private Keyboard keyboard;

	public int score;
	public Boolean gameover;
	public Boolean started;

	public Game(){
		Util.classBase= this.getClass();
		keyboard= Keyboard.getInstance();
		restart();
	}

	public void restart(){
		paused= false;
		started= false;
		gameover= false;

		score= 0;
		pauseDelay= 0;
		restartDelay= 0;
		pipeDelay= 0;

		bird= new Bird();
		pipes= new ArrayList<Pipe>();
	}

	public void update(){
		watchForStart();

		if( ! started ) return;

		watchForPause();
		watchForReset();

		if( paused ) return;

		bird.update();
		checkForLimit();
		if( gameover ) return;

		movePipes();
		checkForCollisions();
	}

	public ArrayList<Render> getRenders(){
		ArrayList<Render> renders= new ArrayList<Render>();
		renders.add( new Render( 0, 0, "lib/background.png" ) );
		for( Pipe p: pipes ){
			renders.add( p.getRender() );
			renders.add( p.getRender2() );
		}
		renders.add( new Render( 0, 0, "lib/foreground.png" ) );
		renders.add( bird.getRender() );
		return renders;
	}

	private void watchForStart(){
		if( ! started && keyboard.isDown( KeyEvent.VK_SPACE ) ){
			started= true;
		}
	}

	private void watchForPause(){
		if( pauseDelay > 0 )
			pauseDelay--;

		if( keyboard.isDown(KeyEvent.VK_P) && pauseDelay <= 0 ){
			paused= !paused;
			pauseDelay= 10;
		}
	}

	private void watchForReset(){
		if( restartDelay > 0 )
			restartDelay--;

		if( keyboard.isDown(KeyEvent.VK_R) && restartDelay <= 0 ){
			restart();
			restartDelay= 10;
			return;
		}
	}

	private void movePipes(){
		pipeDelay--;

		if( pipeDelay < 0 ){
			pipeDelay= PIPE_DELAY;
			Pipe pipe= null;

			// Look for pipes off the screen
			for( Pipe p: pipes ){
				if( p.x - p.width < 0 ){//out of left
					if( pipe == null ){
						pipe= p;
					}
				}
			}

			if( pipe == null ){
				Pipe p= new Pipe();
				pipes.add( p );
				pipe= p;
			}else{
				pipe.reset();
			}
		}

		for( Pipe p: pipes ){
			p.update();
		}
	}

	private void checkForCollisions(){
		for( Pipe p: pipes ){
			if( p.collides( bird.x, bird.y, bird.width, bird.height ) ){
				gameover= true;
				bird.dead= true;
			}else if( (bird.x > p.x + p.width) && (bird.x <= p.x + p.width + p.speed) ){
				score++;
			}
		}
	}

	private void checkForLimit(){
		if( bird.y + bird.height > App.HEIGHT - 80 ){
			gameover= true;
			if( 0 >= bird.yvel )
				bird.flapping= true;
			else
				bird.flapping= false;
		}
	}
}
