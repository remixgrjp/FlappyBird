import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;

public class Util{
	private static HashMap<String, Image> cache= new HashMap<String, Image>();

	public static Image loadImage( Class c, String path ){
		Image image= null;

		if( cache.containsKey( path ) ){
			return cache.get( path );
		}

		Toolkit toolkit= Toolkit.getDefaultToolkit();
		image= toolkit.getImage( c.getResource( path ) );

		if( ! cache.containsKey( path ) ){
			cache.put( path, image );
		}

		return image;
	}
}
