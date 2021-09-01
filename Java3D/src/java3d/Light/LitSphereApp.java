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
 *      LitSphereApp.java 1.0 99/04/12
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
import java.awt.Label;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class LitSphereApp extends Applet {
  

  Appearance createAppearance() {

      Appearance appear = new Appearance();
      Material material = new Material();
//      material.setDiffuseColor(0.0f, 0.0f, 1.0f);
      material.setShininess(50.0f);
      // make modifications to default material properties
      appear.setMaterial(material);

//      ColoringAttributes colorAtt = new ColoringAttributes();
//      colorAtt.setShadeModel(ColoringAttributes.SHADE_FLAT);
//      appear.setColoringAttributes(colorAtt);

      return appear;
  }

  BranchGroup createScene(){
    BranchGroup scene = new BranchGroup();

    scene.addChild(new Sphere(0.9f,
                              Sphere.GENERATE_NORMALS,
                              createAppearance()));


    AmbientLight lightA = new AmbientLight();
    lightA.setInfluencingBounds(new BoundingSphere());
    scene.addChild(lightA);

    DirectionalLight lightD1 = new DirectionalLight();
    lightD1.setInfluencingBounds(new BoundingSphere());
    Vector3f direction1 = new Vector3f(-1.0f, -1.0f, -0.5f);
    direction1.normalize();
    lightD1.setDirection(direction1);
    lightD1.setColor(new Color3f(0.0f, 0.0f, 1.0f));
    scene.addChild(lightD1);

    DirectionalLight lightD2 = new DirectionalLight();
    lightD2.setInfluencingBounds(new BoundingSphere());
    Vector3f direction2 = new Vector3f(1.0f, -1.0f, -0.5f);
    direction2.normalize();
    lightD2.setDirection(direction2);
    lightD2.setColor(new Color3f(1.0f, 0.0f, 0.0f));
    scene.addChild(lightD2);


    Background bg = new Background();
    bg.setColor(1.0f, 1.0f, 1.0f);
    bg.setApplicationBounds(new BoundingSphere());
    scene.addChild(bg);

    return scene;
  }

  public LitSphereApp (){
    setLayout(new BorderLayout());
    Canvas3D c = new Canvas3D(null);
    add("Center", c);
    
    BranchGroup scene = createScene();
    scene.compile();

    SimpleUniverse u = new SimpleUniverse(c);

    // This will move the ViewPlatform back a bit so the
    // objects in the scene can be viewed.
    u.getViewingPlatform().setNominalViewingTransform();

    u.addBranchGraph(scene);
  }
  
  public static void main(String argv[])
  {
    new MainFrame(new LitSphereApp(), 256, 256);
  }
}
