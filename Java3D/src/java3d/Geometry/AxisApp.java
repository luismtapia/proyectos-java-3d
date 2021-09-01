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
 *	axisapp.java 1.0 98/11/25
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
 * This program demonstrates:
 *   1. writing a visual object class
 *      In this program, Axis class defines a visual object
 *      This particular class does not extend another class.
 *      See other the text for a discussion and a differnt approach.
 *   2. Using LineArray to draw 3D lines.
 *      Three LineArray geometries are contained in an instance of Axis.
 *      There are better ways of doing this.  This is a simple example.
 */

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.*;
import com.sun.j3d.utils.applet.MainFrame; 
import com.sun.j3d.utils.universe.*;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.*;


public class AxisApp extends Applet {

    /////////////////////////////////////////////////
    //
    // create graph branch group
    //
    public class Axis{

        private BranchGroup axisBG;

	////////////////////////////////////////////
	//
	// create axis subgraph
	//
	public Axis() {
	
	    axisBG = new BranchGroup();

	    // create line for X axis
	    LineArray axisXLines = new LineArray(2, LineArray.COORDINATES  );
	    axisBG.addChild(new Shape3D(axisXLines));

	    axisXLines.setCoordinate(0, new Point3f(-1.0f, 0.0f, 0.0f));
	    axisXLines.setCoordinate(1, new Point3f( 1.0f, 0.0f, 0.0f));

	    Color3f red   = new Color3f(1.0f, 0.0f, 0.0f);
	    Color3f green = new Color3f(0.0f, 1.0f, 0.0f);
	    Color3f blue  = new Color3f(0.0f, 0.0f, 1.0f);

	    // create line for Y axis
	    LineArray axisYLines = new LineArray(2, 
		LineArray.COORDINATES | LineArray.COLOR_3 );
	    axisBG.addChild(new Shape3D(axisYLines));

	    axisYLines.setCoordinate(0, new Point3f( 0.0f,-1.0f, 0.0f));
	    axisYLines.setCoordinate(1, new Point3f( 0.0f, 1.0f, 0.0f));

	    axisYLines.setColor(0, green);
	    axisYLines.setColor(1, blue);

	    // create line for Z axis
	    Point3f z1 = new Point3f( 0.0f, 0.0f,-1.0f);
	    Point3f z2 = new Point3f( 0.0f, 0.0f, 1.0f);

	    LineArray axisZLines = new LineArray(10, 
			LineArray.COORDINATES  | LineArray.COLOR_3
		);
	    axisBG.addChild(new Shape3D(axisZLines));

	    axisZLines.setCoordinate(0, z1);
	    axisZLines.setCoordinate(1, z2);
	    axisZLines.setCoordinate(2, z2);
	    axisZLines.setCoordinate(3, new Point3f( 0.1f, 0.1f, 0.9f));
	    axisZLines.setCoordinate(4, z2);
	    axisZLines.setCoordinate(5, new Point3f(-0.1f, 0.1f, 0.9f));
	    axisZLines.setCoordinate(6, z2);
	    axisZLines.setCoordinate(7, new Point3f( 0.1f,-0.1f, 0.9f));
	    axisZLines.setCoordinate(8, z2);
	    axisZLines.setCoordinate(9, new Point3f(-0.1f,-0.1f, 0.9f));

	    Color3f colors[] = new Color3f[9];

	    colors[0] = new Color3f(0.0f, 1.0f, 1.0f);
	    for(int v = 0; v < 9; v++){
		colors[v] = red;
	    }

	    axisZLines.setColors(1, colors);
	    	    
	} // end of axis constructor	

	public BranchGroup getBG(){
	    return axisBG;
	}

    } // end of class Axis

    /////////////////////////////////////////////////
    //
    // create scene graph branch group
    //
    public BranchGroup createSceneGraph() {

	BranchGroup objRoot = new Axis().getBG();

	// Let Java 3D perform optimizations on this scene graph.
        objRoot.compile();

	return objRoot;
    } // end of CreateSceneGraph method of yoyo1

    // Create a simple scene and attach it to the virtual universe

    public AxisApp() {
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
    } // end of coneyoyo constructor

    //  The following allows this to be run as an application
    //  as well as an applet

    public static void main(String[] args) {
        Frame frame = new MainFrame(new AxisApp(), 256, 256);
    } // end of main method of axisapp

} // end of class axisap