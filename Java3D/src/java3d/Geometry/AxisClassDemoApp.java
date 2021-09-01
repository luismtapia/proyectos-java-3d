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
/*
 *      AxisClassDemoApp.java 1.0 98/11/25
 *
 * Copyright (c) 1998 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

/*
 * Getting Started with the Java 3D API
 * written in Java 3D
 *
 * This is a program to demonstrate the Axis class
 * defined in the file Axis.java distributed with the tutorial.
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


public class AxisClassDemoApp extends Applet {

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

      RotationInterpolator rotator =
          new RotationInterpolator(rotationAlpha, objSpin);
      BoundingSphere bounds =
          new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
      rotator.setSchedulingBounds(bounds);

      Transform3D trans = new Transform3D();
      trans.set(new Vector3f(0.5f, 0.0f, 0.0f));
      TransformGroup objTrans = new TransformGroup(trans);
      objRoot.addChild(objSpin);
      objSpin.addChild(objTrans); 
      objSpin.addChild(rotator);
      objTrans.addChild(new ColorCube(0.1));

	// Let Java 3D perform optimizations on this scene graph.
        objRoot.compile();

	return objRoot;
    } // end of CreateSceneGraph method of AxisClassDemoApp

    // Create a simple scene and attach it to the virtual universe

    public AxisClassDemoApp() {
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
