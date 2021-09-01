package texture;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Label;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.*;

/**
 *
 * @author luis
 */
public class SimpleTextureApp extends Applet {
    BranchGroup createScene() { 
      BranchGroup objRoot = new BranchGroup();

      Transform3D transform = new Transform3D();

      QuadArray plane = new QuadArray(4, GeometryArray.COORDINATES | GeometryArray.TEXTURE_COORDINATE_2);

      Point3f p = new Point3f(-1.0f,  1.0f,  0.0f);
      plane.setCoordinate(0, p);
      p.set(-1.0f, -1.0f,  0.0f);
      plane.setCoordinate(1, p);
      p.set(1.0f, -1.0f,  0.0f);
      plane.setCoordinate(2, p);
      p.set(1.0f,  1.0f,  0.0f);
      plane.setCoordinate(3, p);

      Point2f q = new Point2f( 0.0f,  1.0f);
      plane.setTextureCoordinate(0, q);
      q.set(0.0f, 0.0f);
      plane.setTextureCoordinate(1, q);
      q.set(1.0f, 0.0f);
      plane.setTextureCoordinate(2, q);
      q.set(1.0f, 1.0f);
      plane.setTextureCoordinate(3, q);

      Appearance appear = new Appearance();

      String filename = "textura.png";  
      TextureLoader loader = new TextureLoader(filename, null);
      ImageComponent2D image = loader.getImage();

      if(image == null) {
            System.out.println("load failed for texture: "+filename);
      }

      // can't use parameterless constuctor
      Texture2D texture = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, image.getWidth(), image.getHeight());
      texture.setImage(0, image);
      //texture.setEnable(false);

      appear.setTexture(texture);

      appear.setTransparencyAttributes(new TransparencyAttributes(TransparencyAttributes.FASTEST, 0.1f));

      Shape3D planeObj = new Shape3D(plane, appear);
      objRoot.addChild(planeObj);

      Background background = new Background();
      background.setColor(1.0f, 1.0f, 1.0f);
      background.setApplicationBounds(new BoundingSphere());
      objRoot.addChild(background);

      return objRoot;
  }

  public SimpleTextureApp (){
    setLayout(new BorderLayout());
    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    Canvas3D c = new Canvas3D(config); 
    add("Center", c);

    c.setStereoEnable(false);
    SimpleUniverse u = new SimpleUniverse(c);
    u.getViewingPlatform().setNominalViewingTransform();
    u.addBranchGraph(createScene());
  }
  
  public static void main(String argv[]){
    new MainFrame(new SimpleTextureApp(), 256, 256);
  }
}
