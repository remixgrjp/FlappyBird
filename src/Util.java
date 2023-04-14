import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;

public class Util{
	static Class classBase;
	static Toolkit toolkit= Toolkit.getDefaultToolkit();
	private static HashMap<String, Image> cache= new HashMap<String, Image>();

	public static Image loadImage( String path ){
		Image image= null;

		if( cache.containsKey( path ) ){
			return cache.get( path );
		}

		image= toolkit.getImage( classBase.getResource( path ) );
		if( ! cache.containsKey( path ) ){
			cache.put( path, image );
		}

		return image;
	}
}
