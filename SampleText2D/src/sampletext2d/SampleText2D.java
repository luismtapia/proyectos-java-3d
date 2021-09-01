/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampletext2d;

 import java.applet.Applet; 
import java.awt.BorderLayout; 
import java.awt.Frame; 
import java.awt.event.*; 
import java.awt.Font; 
import com.sun.j3d.utils.applet.MainFrame; 
import com.sun.j3d.utils.geometry.Text2D; 
import com.sun.j3d.utils.universe.*; 
import javax.media.j3d.*; 
import javax.vecmath.*; 
 //   Text2DApp renders a single Text2D object. 
 public class SampleText2D extends Applet { 
     
   public BranchGroup createSceneGraph() { 
     // Create the root of the branch graph 
                 BranchGroup objRoot = new BranchGroup(); 
                 // Create a Text2D leaf node, add it to the scene graph. 
                 Text2D text2D = new Text2D("2D text is a textured polygon", 
                                                    new Color3f(0.9f, 1.0f, 1.0f), 
                                                    "Helvetica", 18, Font.ITALIC); 
                         objRoot.addChild(text2D);
                    Appearance textAppear = text2D.getAppearance(); 
                      // The following 4 lines of code make the Text2D object 2-sided. 
                      PolygonAttributes polyAttrib = new PolygonAttributes(); 
                      polyAttrib.setCullFace(PolygonAttributes.CULL_NONE); 
                      polyAttrib.setBackFaceNormalFlip(true); 
                      textAppear.setPolygonAttributes(polyAttrib);
        return objRoot;
   }
 }