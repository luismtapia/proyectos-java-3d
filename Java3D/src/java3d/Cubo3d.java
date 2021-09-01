/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java3d;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import com.sun.j3d.utils.applet.MainFrame; 
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;

/**
 *
 * @author luis
 */
public class Cubo3d extends Applet{
    public static void main(String[] args) {
        Frame ventana = new MainFrame(new Cubo3d(), 500, 500);
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
        figura3d.setAppearance(new Appearance());
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
        
        Appearance transpAppear = figura3d.getAppearance();
        transpAppear.setTransparencyAttributes(objTransp);
        root.addChild(traInt);
        
        
        root.compile();
        return root;
    }
    
    public Cubo3d(){
        setLayout(new BorderLayout());
        GraphicsConfiguration configuracion = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(configuracion);
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        
        add("Center", canvas3D);
        BranchGroup escena = createSceneGraph();
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(escena);
    }
    

}
