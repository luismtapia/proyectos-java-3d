/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java3d.Light;

/**
 *
 * @author luis
 */
/*
 *      LightsNPlanesApp.java 1.0 99/04/12
 *
 * Copyright (c) 1999 Sun Microsystems, Inc. All Rights Reserved.
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
 *
 * This application (or a version of it) generated one or more
 * of the images in Chapter 6 of Getting Started with the Java 3D API.
 * The Java 3D Turtorial.
 *
 * See http://www.sun.com/desktop/java3d/collateral for more information.
 *
 */

import java.applet.Applet;
import java.awt.BorderLayout;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;

/**
 * LightsNPlanesApp creates 
 */
public class LightsNPlanesApp extends Applet {

    TransformGroup createTG(float x, float y, float z){
        Vector3f position = new Vector3f(x, y, z);
        Transform3D translate = new Transform3D();
        translate.set(position);
        TransformGroup trans1 = new TransformGroup(translate);
        return trans1;
    }

    Appearance createMatAppear(Color3f dColor, Color3f sColor, float shine) {

        Appearance appear = new Appearance();
        Material material = new Material();
        material.setDiffuseColor(dColor);
        material.setSpecularColor(sColor);
        material.setShininess(shine);
        appear.setMaterial(material);

        return appear;
    }


  public LightsNPlanesApp (){
    setLayout(new BorderLayout());
    Canvas3D c = new Canvas3D(null);
    add("Center", c);

    BoundingSphere bounds = new BoundingSphere(new Point3d(), 0.1);

    Vector3f direction = new Vector3f(0.0f, -1.0f, 0.0f);
    Point3f position = new Point3f(0.0f, 0.5f, -0.3f);

    Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
    Color3f red    = new Color3f(1.0f, 0.0f, 0.0f);
    Color3f green  = new Color3f(0.0f, 1.0f, 0.0f);
    Color3f blue   = new Color3f(0.0f, 0.0f, 1.0f);
 
    BranchGroup scene = new BranchGroup();

    IndexedQuadArray qa = new IndexedQuadArray(9, QuadArray.COORDINATES | QuadArray.NORMALS,16);
    qa.setCoordinate( 0, new Point3f(-0.3f,  0.3f, -0.3f));
    qa.setCoordinate( 1, new Point3f( 0.0f,  0.3f, -0.3f));
    qa.setCoordinate( 2, new Point3f( 0.3f,  0.3f, -0.3f));
    qa.setCoordinate( 3, new Point3f(-0.3f,  0.0f,  0.0f));
    qa.setCoordinate( 4, new Point3f( 0.0f,  0.0f,  0.0f));
    qa.setCoordinate( 5, new Point3f( 0.3f,  0.0f,  0.0f));
    qa.setCoordinate( 6, new Point3f(-0.3f, -0.3f,  0.3f));
    qa.setCoordinate( 7, new Point3f( 0.0f, -0.3f,  0.3f));
    qa.setCoordinate( 8, new Point3f( 0.3f, -0.3f,  0.3f));
    Vector3f n = new Vector3f(0.0f, 0.6f, 0.8f);
    n.normalize();
    qa.setNormal( 0, n);
    qa.setNormal( 1, n);
    qa.setNormal( 2, n);
    qa.setNormal( 3, n);
    qa.setNormal( 4, n);
    qa.setNormal( 5, n);
    qa.setNormal( 6, n);
    qa.setNormal( 7, n);
    qa.setNormal( 8, n);

    qa.setCoordinateIndex( 0, 0);
    qa.setCoordinateIndex( 1, 3);
    qa.setCoordinateIndex( 2, 4);
    qa.setCoordinateIndex( 3, 1);
    qa.setCoordinateIndex( 4, 1);
    qa.setCoordinateIndex( 5, 4);
    qa.setCoordinateIndex( 6, 5);
    qa.setCoordinateIndex( 7, 2);
    qa.setCoordinateIndex( 8, 3);
    qa.setCoordinateIndex( 9, 6);
    qa.setCoordinateIndex(10, 7);
    qa.setCoordinateIndex(11, 4);
    qa.setCoordinateIndex(12, 4);
    qa.setCoordinateIndex(13, 7);
    qa.setCoordinateIndex(14, 8);
    qa.setCoordinateIndex(15, 5);

    TransformGroup trans1 = createTG(-0.7f, 0.0f, -0.5f);
    scene.addChild(trans1);

    TransformGroup trans2 = createTG( 0.0f, 0.0f, -0.5f);
    scene.addChild(trans2);

    TransformGroup trans3 = createTG( 0.7f, 0.0f, -0.5f);
    scene.addChild(trans3);

    Appearance qAppear = createMatAppear(white, white, 5.0f);
    Shape3D p1 = new Shape3D(qa, qAppear);
//    p1.setBoundsAutoCompute(false);
    p1.setBounds(bounds);
p1.setCapability(Node.ALLOW_BOUNDS_READ);
    trans1.addChild(p1);

    Shape3D p2 = new Shape3D(qa, qAppear);
    p2.setBounds(bounds);
p2.setCapability(Node.ALLOW_BOUNDS_READ);
    trans2.addChild(p2);

    Shape3D p3 = new Shape3D(qa, qAppear);
    p3.setBounds(bounds);
p3.setCapability(Node.ALLOW_BOUNDS_READ);
    trans3.addChild(p3);


    AmbientLight lightA = new AmbientLight();
    lightA.setInfluencingBounds(new BoundingSphere());
lightA.setCapability(Light.ALLOW_INFLUENCING_BOUNDS_READ);
    scene.addChild(lightA);

    DirectionalLight lightD = new DirectionalLight();
    lightD.setInfluencingBounds(bounds);
    lightD.setBoundsAutoCompute(false);
lightD.setCapability(Light.ALLOW_INFLUENCING_BOUNDS_READ);
    lightD.setDirection(direction);
    lightD.setColor(red);
    trans1.addChild(lightD);

    PointLight lightP = new PointLight();
    lightP.setInfluencingBounds(bounds);
lightP.setCapability(Light.ALLOW_INFLUENCING_BOUNDS_READ);
    lightP.setPosition(position);
    lightP.setColor(green);
    trans2.addChild(lightP);

    SpotLight lightS = new SpotLight();
    lightS.setInfluencingBounds(bounds);
lightS.setCapability(Light.ALLOW_INFLUENCING_BOUNDS_READ);
    lightS.setPosition(position);
    lightS.setDirection(direction);
    lightS.setSpreadAngle(0.3f);
    lightS.setConcentration(1.0f);
    lightS.setColor(blue);
    trans3.addChild(lightS);

    Background background = new Background();
    background.setApplicationBounds(new BoundingSphere());
    background.setColor(1.0f, 1.0f, 1.0f);
    scene.addChild(background);

    scene.compile();
System.out.print("bounds object: ");
System.out.println(bounds);

System.out.print("influencing bounds for lightA: ");
System.out.println(lightA.getInfluencingBounds());

System.out.print("influencing bounds for lightD: ");
System.out.println(lightD.getInfluencingBounds());

System.out.print("influencing bounds for lightP: ");
System.out.println(lightP.getInfluencingBounds());

System.out.print("influencing bounds for lightS: ");
System.out.println(lightS.getInfluencingBounds());

System.out.print("bounds for plane1: ");
System.out.println(p1.getBounds());

System.out.print("bounds for plane2: ");
System.out.println(p2.getBounds());

System.out.print("bounds for plane3: ");
System.out.println(p3.getBounds());


BoundingSphere bs0 = new BoundingSphere(new Point3d( 0.0, 0.0, 0.0), 0.2);
BoundingSphere bs1 = new BoundingSphere(new Point3d(-0.1, 0.0, 0.0), 0.2);
BoundingSphere bs2 = new BoundingSphere(new Point3d(-0.2, 0.0, 0.0), 0.2);
BoundingSphere bs3 = new BoundingSphere(new Point3d(-0.3, 0.0, 0.0), 0.2);
BoundingSphere bs4 = new BoundingSphere(new Point3d(-0.4, 0.0, 0.0), 0.2);
BoundingSphere bs5 = new BoundingSphere(new Point3d(-0.5, 0.0, 0.0), 0.2);
BoundingSphere bs6 = new BoundingSphere(new Point3d(-0.6, 0.0, 0.0), 0.2);
BoundingSphere bs7 = new BoundingSphere(new Point3d(-0.7, 0.0, 0.0), 0.2);
BoundingSphere bs8 = new BoundingSphere(new Point3d(-0.8, 0.0, 0.0), 0.2);
BoundingBox bb1 = new BoundingBox(bs1);
BoundingBox bb2 = new BoundingBox(bs2);
BoundingBox bb3 = new BoundingBox(bs3);
BoundingBox bb4 = new BoundingBox(bs4);
BoundingBox bb5 = new BoundingBox(bs5);
BoundingBox bb6 = new BoundingBox(bs6);
BoundingBox bb7 = new BoundingBox(bs7);
BoundingBox bb8 = new BoundingBox(bs8);

if(bs0.intersect(bs1)) System.out.println("bs0 intersects bs1");
if(bs0.intersect(bs2)) System.out.println("bs0 intersects bs2");
if(bs0.intersect(bs3)) System.out.println("bs0 intersects bs3");
if(bs0.intersect(bs4)) System.out.println("bs0 intersects bs4");
if(bs0.intersect(bs5)) System.out.println("bs0 intersects bs5");
if(bs0.intersect(bs6)) System.out.println("bs0 intersects bs6");
if(bs0.intersect(bs7)) System.out.println("bs0 intersects bs7");
if(bs0.intersect(bs8)) System.out.println("bs0 intersects bs8");

if(bs0.intersect(bb1)) System.out.println("bs0 intersects bb1");
if(bs0.intersect(bb2)) System.out.println("bs0 intersects bb2");
if(bs0.intersect(bb3)) System.out.println("bs0 intersects bb3");
if(bs0.intersect(bb4)) System.out.println("bs0 intersects bb4");
if(bs0.intersect(bb5)) System.out.println("bs0 intersects bb5");
if(bs0.intersect(bb6)) System.out.println("bs0 intersects bb6");
if(bs0.intersect(bb7)) System.out.println("bs0 intersects bb7");
if(bs0.intersect(bb8)) System.out.println("bs0 intersects bb8");

    SimpleUniverse u = new SimpleUniverse(c);

    // This will move the ViewPlatform back a bit so the
    // objects in the scene can be viewed.
    u.getViewingPlatform().setNominalViewingTransform();

    u.addBranchGraph(scene);
  }
  
  public static void main(String argv[])
  {
    new MainFrame(new LightsNPlanesApp(), 256,128);
  }
}
