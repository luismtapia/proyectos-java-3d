/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java3d;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import com.sun.j3d.utils.applet.MainFrame; 
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.*;
import java.awt.GraphicsConfiguration;
import com.sun.j3d.utils.geometry.ColorCube;

import javax.media.j3d.*;
import javax.vecmath.*;

/**
 *
 * @author luis
 */
public class CuboTransparencyInterpolator extends Applet{

    Shape3D createCar(float xScale, float yScale, boolean createNormals, boolean assignColoring) {
        Shape3D figura = new Shape3D();
        QuadArray carGeom = null;
        int  array[] = {4, 4}; 
        Point3f[] coordenadas = new Point3f[4+4];
        Color3f[] colores = new Color3f[4+4];
        Color3f verde = new Color3f(1.0f, 1.f, 0.0f);
        Color3f rojo = new Color3f(0.0f, 1.f, 1.0f);
        Color3f azul = new Color3f(1.0f, 0.f, 1.0f);
        

        if (createNormals)
            carGeom = new QuadArray(8, GeometryArray.COORDINATES | GeometryArray.NORMALS);
        else
            carGeom = new QuadArray(8, GeometryArray.COORDINATES);

        //0.0 es el centro
        //********************************Lado 1 cara frontal
        coordenadas[0] =  new Point3f(xScale*-1.0f, yScale*-1.0f, 0.0f);
        coordenadas[1] = new Point3f(xScale* 0.0f, yScale*-2.0f, 0.0f);  
        coordenadas[2] = new Point3f(xScale* 2.0f, yScale*-2.0f, 0.0f);  
        coordenadas[3] = new Point3f(xScale* 1.0f, yScale*-1.0f, 0.0f);
        
        
//        carGeom.setCoordinate( 4, new Point3f(xScale* 1.0f, yScale*1.0f, 0.0f));
//        carGeom.setCoordinate( 5, new Point3f(xScale*-1.0f, yScale*1.0f, 0.0f));
        /*
        carGeom.setCoordinate( 6, new Point3f(xScale* 0.45f, yScale*0.20f, 0.0f));
        carGeom.setCoordinate( 7, new Point3f(xScale*-0.48f, yScale*0.20f, 0.0f));
        carGeom.setCoordinate( 8, new Point3f(xScale*-0.26f, yScale*0.00f, 0.0f));
        carGeom.setCoordinate( 9, new Point3f(xScale*-0.18f, yScale*0.00f, 0.0f));
        carGeom.setCoordinate(10, new Point3f(xScale*-0.16f, yScale*0.12f, 0.0f));
        carGeom.setCoordinate(11, new Point3f(xScale*-0.28f, yScale*0.12f, 0.0f));
        carGeom.setCoordinate(12, new Point3f(xScale* 0.25f, yScale*0.00f, 0.0f));
        carGeom.setCoordinate(13, new Point3f(xScale* 0.33f, yScale*0.00f, 0.0f));
        carGeom.setCoordinate(14, new Point3f(xScale* 0.35f, yScale*0.12f, 0.0f));
        carGeom.setCoordinate(15, new Point3f(xScale* 0.23f, yScale*0.12f, 0.0f));*/

        if (createNormals){
            int i;
            Vector3f normal = new Vector3f(0.6f, 0.6f, 0.8f);
            for(i = 0; i < 8; i++)
                carGeom.setNormal(i, normal);
            normal.set(new Vector3f(0.5f, 0.5f, 0.5f));
            for(i = 8; i <16; i++)
                carGeom.setNormal(i, normal);
        }

        if (assignColoring){
            ColoringAttributes colorAttrib = new ColoringAttributes(0.0f, 0.0f, 1.0f, ColoringAttributes.NICEST);
            Appearance carAppear = new Appearance();
            carAppear.setColoringAttributes(colorAttrib);
            figura.setAppearance(carAppear);
        }

        figura.setGeometry(carGeom);
        
        return figura;
    }

    Box cubo(float xScale, float yScale, float zScale, boolean assignColoring) {
        Box caja =new Box(xScale, yScale, zScale, new Appearance() );

        if (assignColoring){
            ColoringAttributes colorAttrib = new ColoringAttributes(0.0f, 0.0f, 1.0f, ColoringAttributes.NICEST);
            Appearance carAppear = new Appearance();
            carAppear.setColoringAttributes(colorAttrib);
            
            caja.setAppearance(carAppear);
        }

        return caja;
    }
    
    public BranchGroup createSceneGraph() {
	// Create the root of the branch graph
	BranchGroup objRoot = new BranchGroup();
        Transform3D t3d = new Transform3D();
        BoundingSphere bounds = new BoundingSphere();

        // create target Transparency with Capabilities
        TransparencyAttributes objTransp = new TransparencyAttributes();
        objTransp.setCapability(TransparencyAttributes.ALLOW_VALUE_WRITE);
        objTransp.setTransparencyMode(TransparencyAttributes.BLENDED);

        // create Alpha 
        Alpha alpha = new Alpha (-1, Alpha.INCREASING_ENABLE + Alpha.DECREASING_ENABLE,
                                    0, 0, 50, 0, 1000, 2000, 0, 1000);

        // create transparency interpolator
        TransparencyInterpolator traInt = new TransparencyInterpolator (alpha, objTransp);
        traInt.setSchedulingBounds(bounds);

        t3d.setTranslation(new Vector3f(-0.1f, -0.5f, -0.5f));
        
        TransformGroup objTranspPos = new TransformGroup(t3d);
        objRoot.addChild(objTranspPos);
        Shape3D figura3d = new Shape3D();
        figura3d.addGeometry(new ColorCube(0.4).getGeometry());
        
        Appearance transpAppear = figura3d.getAppearance();
        transpAppear.setTransparencyAttributes(objTransp);
        objTranspPos.addChild(figura3d);
        objRoot.addChild(traInt);
        
        
        Background background = new Background();
        background.setColor(0.0f, 0.0f, 0.0f);
        background.setApplicationBounds(new BoundingSphere());
        objRoot.addChild(background);

	// Let Java 3D perform optimizations on this scene graph.
        objRoot.compile();

	return objRoot;
    } // end of CreateSceneGraph method of InterpolatorApp

    // Create a simple scene and attach it to the virtual universe

    public CuboTransparencyInterpolator() {
        setLayout(new BorderLayout());
        GraphicsConfiguration config= SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);

        BranchGroup scene = createSceneGraph();
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();

        simpleU.addBranchGraph(scene);
    } // end of InterpolatorApp (constructor)

    public static void main(String[] args) {
        System.out.print("InterpolatorApp.java \n- a demonstration of using Interpolator ");
        System.out.println("objects to provide animation in a Java 3D scene.");
        System.out.println("This is a simple example progam from The Java 3D API Tutorial.");
        System.out.println("The Java 3D Tutorial is available on the web at:");
        System.out.println("http://java.sun.com/products/java-media/3D/collateral");
        Frame frame = new MainFrame(new CuboTransparencyInterpolator(), 256, 256);
    }
    
}
