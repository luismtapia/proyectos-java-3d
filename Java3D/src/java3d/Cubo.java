/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java3d;

/**
 *
 * @author luis
 */

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import javax.media.j3d.*;
import javax.vecmath.*;

import java.awt.event.*;
import java.util.Enumeration;

//   MouseRotate2App renders a single, interactively rotatable cube.

public class Cubo extends Applet {
    public BranchGroup createSceneGraph() {
	BranchGroup objRoot = new BranchGroup();

        TransformGroup objRotate;
        MouseRotate myMouseRotate = null;
        Transform3D transform = new Transform3D();
        BoundingSphere bounds = new BoundingSphere();

        // create target Transparency with Capabilities
        TransparencyAttributes objTransp = new TransparencyAttributes();
        objTransp.setCapability(TransparencyAttributes.ALLOW_VALUE_WRITE);
        objTransp.setTransparencyMode(TransparencyAttributes.BLENDED);
        
        // create Alpha 
        Alpha alpha = new Alpha (-1, Alpha.INCREASING_ENABLE + Alpha.DECREASING_ENABLE, 0, 0, 50, 0, 1000, 2000, 0, 1000);

        // create transparency interpolator
        TransparencyInterpolator traInt = new TransparencyInterpolator (alpha, objTransp);
        traInt.setSchedulingBounds(bounds);
        
        // create ColorCube and MouseRotate behvaior objects
        transform.setTranslation(new Vector3f(-0.4f, 0.0f, -0.6f));
        objRotate = new TransformGroup(transform);
        objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        objRoot.addChild(objRotate);
        objRotate.addChild(new ColorCube(0.4));
        
        myMouseRotate = new MouseRotate();
        myMouseRotate.setTransformGroup(objRotate);
        myMouseRotate.setSchedulingBounds(new BoundingSphere());
        objRoot.addChild(myMouseRotate);
        
        /*
        //*******************************
        //Shape3D transpCar = cubo(0.4f, 0.4f, false, true);
        //Box transpCar = cubo(0.4f, 0.4f, 0.4f, true );
        Appearance transpAppear = transpCar.getAppearance();
        transpAppear.setTransparencyAttributes(objTransp);
        objTranspPos.addChild(transpCar);*/
        objRoot.addChild(traInt);
        
        
        
        Background background = new Background();
        background.setColor(0.2f, 0.2f, 0.2f);
        background.setApplicationBounds(new BoundingSphere());
        objRoot.addChild(background);
	// Let Java 3D perform optimizations on this scene graph.
        objRoot.compile();

	return objRoot;
    } // end of CreateSceneGraph method of MouseRotate2App

    public Cubo() {
        setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);

        BranchGroup scene = createSceneGraph();
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(scene);
    }

    public static void main(String[] args) {
        System.out.print("MouseRotate2App.java \n- a demonstration of using the MouseRotate ");
        Frame frame = new MainFrame(new Cubo(), 256, 256);
    } // end of main (method of MouseRotate2App)

} // end of class MouseRotate2App
