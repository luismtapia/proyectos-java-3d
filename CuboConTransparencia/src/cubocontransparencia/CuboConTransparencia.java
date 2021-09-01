/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cubocontransparencia;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import com.sun.j3d.utils.applet.MainFrame; 
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;

/**
 *
 * @author luis
 */
public class CuboConTransparencia extends Applet{

    public static void main(String[] args) {
        Frame ventana = new MainFrame(new CuboConTransparencia(), 500, 500);
    }
    
    public BranchGroup createSceneGraph(){
        BranchGroup root = new BranchGroup();
        TransformGroup transfor = new TransformGroup();
        Shape3D figura3d = new Shape3D();
        Alpha rotacionAlpha = new Alpha(400, 5000);
        RotationInterpolator rotacion =new RotationInterpolator(rotacionAlpha, transfor);
        BoundingSphere limites =new BoundingSphere(new Point3d(1,1,1), 200);
        
        transfor.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(transfor);
        figura3d.addGeometry(new ColorCube(0.4).getGeometry());
        //figura3d.setAppearance(crearApariencia());
        
        
        Appearance appear = new Appearance();
        String filename = "textura.png";  
        TextureLoader loader = new TextureLoader(filename, null);
        ImageComponent2D image = loader.getImage();
        if(image == null) {
              System.out.println("load failed for texture: "+filename);
        }
        Texture2D texture = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, image.getWidth(), image.getHeight());
        texture.setImage(0, image);
        appear.setTexture(texture);
        appear.setTransparencyAttributes(new TransparencyAttributes(TransparencyAttributes.FASTEST, 0.1f));
        
        
        figura3d.setAppearance(appear);
        
        transfor.addChild(figura3d);
        rotacion.setSchedulingBounds(limites);
        transfor.addChild(rotacion);
        
        
        // create target Transparency with Capabilities
        TransparencyAttributes objTransp = new TransparencyAttributes();
        objTransp.setCapability(TransparencyAttributes.ALLOW_VALUE_WRITE);
        objTransp.setTransparencyMode(TransparencyAttributes.BLENDED);
        // create Alpha 
        Alpha alpha = new Alpha (-1, Alpha.INCREASING_ENABLE + Alpha.DECREASING_ENABLE, 0,
                200, 10, 1000, 10, 2000, 100, 500);
        // create transparency interpolator
        TransparencyInterpolator traInt = new TransparencyInterpolator (alpha, objTransp);
        BoundingSphere bounds = new BoundingSphere();
        traInt.setSchedulingBounds(bounds);
        
        /*Appearance apariencia = crearApariencia();
        figura3d.setAppearance(apariencia);
        apariencia.setTransparencyAttributes(objTransp);*/
        Appearance transpAppear = figura3d.getAppearance();
        transpAppear.setTransparencyAttributes(objTransp);
        root.addChild(traInt);
        
        
        
        
        /*
        Background background = new Background(); 
        background.setColor(1.0f, 1.0f, 1.0f); 
        background.setApplicationBounds(new BoundingSphere()); 
        root.addChild(background);*/
          
        
        root.compile();
        return root;
    }
    
    
    
    public CuboConTransparencia(){
        setLayout(new BorderLayout());
        GraphicsConfiguration configuracion = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(configuracion);
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        
        add("Center", canvas3D);
        BranchGroup escena = createSceneGraph();
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(escena);
    }
    

    Appearance crearApariencia(){

        Appearance materialAppear = new Appearance();

        Material material = new Material();

        material.setAmbientColor(new Color3f(0.2f, .6f, 6f));
        //material.setDiffuseColor(new Color3f(0.2f, .6f, 6f));
        materialAppear.setMaterial(material);

        return materialAppear;
    }
}
