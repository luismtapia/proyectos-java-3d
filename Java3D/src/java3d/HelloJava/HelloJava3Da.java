/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java3d.HelloJava;

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

//   HelloJava3Da renders a single, rotating cube.  

public class HelloJava3Da extends Applet {
    public HelloJava3Da() {
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
    } // end of HelloJava3Da (constructor)

     public BranchGroup createSceneGraph() {
	// Create the root of the branch graph
	BranchGroup objRoot = new BranchGroup();
        Shape3D cubo= new ColorCube(0.4);
	objRoot.addChild(cubo);

	return objRoot;
    } // end of CreateSceneGraph method of HelloJava3Da

    //  The following allows this to be run as an application
    //  as well as an applet

    public static void main(String[] args) {
	Frame frame = new MainFrame(new HelloJava3Da(), 256, 256);
    } // end of main (method of HelloJava3Da)

} // end of class HelloJava3Da