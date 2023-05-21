import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.HashMap;

public class Util{
	static Class classBase;
	private static HashMap<String, Image> cache= new HashMap<String, Image>();

	public static Image loadImage( String path ){
		Image image= null;

		if( cache.containsKey( path ) ){
			return cache.get( path );
		}

		try{
			image= ImageIO.read( classBase.getResourceAsStream( path ) );
			if( ! cache.containsKey( path ) ){
				cache.put( path, image );
			}
		}catch( IOException e ){
			e.printStackTrace();
		}

		return image;
	}
}
