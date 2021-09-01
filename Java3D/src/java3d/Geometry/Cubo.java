/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java3d.Geometry;

/**
 *
 * @author luis
 */

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.*;
import com.sun.j3d.utils.applet.MainFrame; 
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.ColorCube;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.*;


public class Cubo extends Applet {

    /////////////////////////////////////////////////
    //
    // create scene graph branch group
    //
    public BranchGroup createSceneGraph() {

       BranchGroup objRoot = new BranchGroup();
       objRoot.addChild(new Axis());

      // Create the transform group node and initialize it to the
      // identity.  Enable the TRANSFORM_WRITE capability so that
      // our behavior code can modify it at runtime.  Add it to the
      // root of the subgraph.
      TransformGroup objSpin = new TransformGroup();
      objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

      // Create a new Behavior object that will perform the desired
      // operation on the specified transform object and add it into
      // the scene graph.
      Transform3D yAxis = new Transform3D();
      Alpha rotationAlpha = new Alpha(-1, 4000);

      RotationInterpolator rotator = new RotationInterpolator(rotationAlpha, objSpin);
      BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
      rotator.setSchedulingBounds(bounds);

      Transform3D trans = new Transform3D();
      trans.set(new Vector3f(0.5f, 0.0f, 0.0f));
      TransformGroup objTrans = new TransformGroup(trans);
      objRoot.addChild(objSpin);
      objSpin.addChild(objTrans); 
      objSpin.addChild(rotator);
      objTrans.addChild(new ColorCube(0.1));
      
      
      // create target Transparency with Capabilities
        TransparencyAttributes objTransp = new TransparencyAttributes();
        objTransp.setCapability(TransparencyAttributes.ALLOW_VALUE_WRITE);
        objTransp.setTransparencyMode(TransparencyAttributes.BLENDED);
        // create Alpha 
        Alpha alpha = new Alpha (-1, Alpha.INCREASING_ENABLE + Alpha.DECREASING_ENABLE, 0, 0, 50, 0, 1000, 2000, 0, 1000);

        // create transparency interpolator
        TransparencyInterpolator traInt = new TransparencyInterpolator (alpha, objTransp);
        traInt.setSchedulingBounds(bounds);
        
        Transform3D t3d = new Transform3D();
        t3d.set(new Vector3f(-0.1f, -0.5f, -0.5f));
        TransformGroup objTranspPos = new TransformGroup(t3d);
        objRoot.addChild(objTranspPos);
        ColorCube b=new ColorCube();
        Appearance transpAppear = b.getAppearance();
        transpAppear.setTransparencyAttributes(objTransp);
        objTranspPos.addChild(b);
        objRoot.addChild(traInt);
        
        
        
        
	// Let Java 3D perform optimizations on this scene graph.
        objRoot.compile();

	return objRoot;
    } // end of CreateSceneGraph method of AxisClassDemoApp

    // Create a simple scene and attach it to the virtual universe

    public Cubo() {
        setLayout(new BorderLayout());
        GraphicsConfiguration config= SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);

        BranchGroup scene = createSceneGraph();

        // SimpleUniverse is a Convenience Utility class
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

	// This will move the ViewPlatform back a bit so the
	// objects in the scene can be viewed.
        simpleU.getViewingPlatform().setNominalViewingTransform();

        simpleU.addBranchGraph(scene);
    } // end of AxisClassDemoApp constructor

    //  The following allows this to be run as an application
    //  as well as an applet

    public static void main(String[] args) {
        Frame frame = new MainFrame(new AxisClassDemoApp(), 256, 256);
    } // end of main method of AxisClassDemoApp

} // end of class AxisClassDemoApp
