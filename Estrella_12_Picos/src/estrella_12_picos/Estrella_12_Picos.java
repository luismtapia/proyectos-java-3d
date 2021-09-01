/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrella_12_picos;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import com.sun.j3d.utils.applet.MainFrame; 
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Stripifier;
import com.sun.j3d.utils.universe.*;
import javafx.scene.shape.Sphere;
import javax.media.j3d.*;
import javax.vecmath.*;

/**
 *
 * @author luis
 */
public class Estrella_12_Picos extends Applet{
    GeometryInfo geometryInfo;
    public static void main(String[] args) {
        Frame ventana = new MainFrame(new Estrella_12_Picos(), 500, 500);
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
        figura3d.addGeometry(lados().getGeometryArray());
        figura3d.setAppearance(crearApariencia());
        transfor.addChild(figura3d);
        rotacion.setSchedulingBounds(limites);
        transfor.addChild(rotacion);
        
        
        AmbientLight lightA = new AmbientLight();
        lightA.setInfluencingBounds(new BoundingSphere());
        transfor.addChild(lightA);

        DirectionalLight lightD1 = new DirectionalLight();
        lightD1.setInfluencingBounds(new BoundingSphere());
        Vector3f direction1 = new Vector3f(-1.0f, -1.0f, -0.5f);
        direction1.normalize();
        lightD1.setDirection(direction1);
        lightD1.setColor(new Color3f(0.0f, 0.0f, 1.0f));
        transfor.addChild(lightD1);

        DirectionalLight lightD2 = new DirectionalLight();
        lightD2.setInfluencingBounds(new BoundingSphere());
        Vector3f direction2 = new Vector3f(1.0f, -1.0f, -1.5f);
        direction2.normalize();
        lightD2.setDirection(direction2);
        lightD2.setColor(new Color3f(1.0f, 0.0f, 0.0f));
        transfor.addChild(lightD2);
        
        
        root.compile();
        return root;
    }
    
    private GeometryInfo lados(){
        int  array[] = {7,7}; 
        Point3f[] coordenadas = new Point3f[7+7];
        Color3f[] colores = new Color3f[7+7];
        Color3f verde = new Color3f(1.0f, 1.f, 0.0f);
    
        //**************************************LADO 1**************************
        coordenadas[0] =  new Point3f(0f/2, -1.5f/2, 0f/2);
        coordenadas[1] = new Point3f(0f/2, -0.68f/2, 0.5f/2);  
        coordenadas[2] = new Point3f(0.48f/2, -0.68f/2, 0.13f/2);  
        coordenadas[3] = new Point3f(0.27f/2, -0.67f/2, -0.44f/2);  
        coordenadas[4] = new Point3f(-0.33f/2, -0.67f/2, -0.42f/2); 
        coordenadas[5] = new Point3f(-0.5f/2, -0.67f/2, 0.16f/2); 
        coordenadas[6] = new Point3f(0f/2, -0.68f/2, 0.5f/2);
        
        //**************************************LADO 2**************************
        coordenadas[7] = new Point3f(-0.9f/2, -0.9f/2, 1.27f/2); 
        coordenadas[8] = new Point3f(0f/2, -0.68f/2, 0.5f/2);  
        coordenadas[9] = new Point3f(-0.5f/2, -0.67f/2, 0.16f/2);  
        coordenadas[10] = new Point3f(-0.78f/2, -0.14f/2, 0.29f/2);
        coordenadas[11] = new Point3f(-0.46f/2, 0.16f/2, 0.7f/2); 
        coordenadas[12] = new Point3f(0.02f/2, -0.18f/2, 0.83f/2);
        coordenadas[13] = new Point3f(0f/2, -0.68f/2, 0.5f/2); 
/*
        //**************************************LADO 3**************************
        coordenadas[14] = new Point3f(-1.3f/2, -0.9f/2, -0.38f/2);  
        coordenadas[15] = new Point3f(-0.5f/2, -0.67f/2, 0.16f/2);  
        coordenadas[16] = new Point3f(-0.33f/2, -0.67f/2, -0.42f/2);
        coordenadas[17] = new Point3f(-0.51f/2, -0.12f/2, -0.65f/2);
        coordenadas[18] = new Point3f(-0.79f/2, 0.19f/2, -0.22f/2); 
        coordenadas[19] = new Point3f(-0.78f/2, -0.14f/2, 0.29f/2); 
        coordenadas[20] = new Point3f(-0.5f/2, -0.67f/2, 0.16f/2);  

        //**************************************LADO 4**************************
        coordenadas[21] = new Point3f(0.01f/2, -0.9f/2, -1.3f/2);
        coordenadas[22] = new Point3f(-0.33f/2, -0.67f/2, -0.42f/2);
        coordenadas[23] = new Point3f(0.27f/2, -0.67f/2, -0.44f/2); 
        coordenadas[24] = new Point3f(0.46f/2, -0.14f/2, -0.68f/2); 
        coordenadas[25] = new Point3f(-0.02f/2, 0.19f/2, -0.82f/2); 
        coordenadas[26] = new Point3f(-0.51f/2, -0.12f/2, -0.65f/2);
        coordenadas[27] = new Point3f(-0.33f/2, -0.67f/2, -0.42f/2);

        //**************************************LADO 5**************************
        coordenadas[28] = new Point3f(1.22f/2, -0.9f/2, -0.63f/2);
        coordenadas[29] = new Point3f(0.27f/2, -0.67f/2, -0.44f/2);
        coordenadas[30] = new Point3f(0.48f/2, -0.68f/2, 0.13f/2); 
        coordenadas[31] = new Point3f(0.79f/2, -0.18f/2, 0.23f/2); 
        coordenadas[32] = new Point3f(0.78f/2, 0.16f/2, -0.27f/2); 
        coordenadas[33] = new Point3f(0.46f/2, -0.14f/2, -0.68f/2);
        coordenadas[34] = new Point3f(0.27f/2, -0.67f/2, -0.44f/2);

        //**************************************LADO 6**************************
        coordenadas[35] = new Point3f(0.86f/2, -0.9f/2, 1.15f/2);  
        coordenadas[36] = new Point3f(0.48f/2, -0.68f/2, 0.13f/2); 
        coordenadas[37] = new Point3f(0f/2, -0.68f/2, 0.5f/2);  
        coordenadas[38] = new Point3f(0.02f/2, -0.18f/2, 0.83f/2);
        coordenadas[39] = new Point3f(0.51f/2, 0.13f/2, 0.67f/2); 
        coordenadas[40] = new Point3f(0.79f/2, -0.18f/2, 0.23f/2); 
        coordenadas[41] = new Point3f(0.48f/2, -0.68f/2, 0.13f/2);  

        //**************************************LADO 7**************************
        coordenadas[42] = new Point3f(0.03f/2, 0.9f/2, 1.3f/2); 
        coordenadas[43] = new Point3f(0.51f/2, 0.13f/2, 0.67f/2); 
        coordenadas[44] = new Point3f(0.02f/2, -0.18f/2, 0.83f/2);
        coordenadas[45] = new Point3f(-0.46f/2, 0.16f/2, 0.7f/2); 
        coordenadas[46] = new Point3f(-0.27f/2, 0.67f/2, 0.45f/2);
        coordenadas[47] = new Point3f(0.33f/2, 0.66f/2, 0.43f/2); 
        coordenadas[48] = new Point3f(0.51f/2, 0.13f/2, 0.67f/2); 
       
        //**************************************LADO 8**************************
        coordenadas[49] = new Point3f(-1.3f/2, .9f/2, 0.73f/2); 
        coordenadas[50] = new Point3f(-0.46f/2, 0.16f/2, 0.7f/2); 
        coordenadas[51] = new Point3f(-0.78f/2, -0.14f/2, 0.29f/2); 
        coordenadas[52] = new Point3f(-0.79f/2, 0.19f/2, -0.22f/2); 
        coordenadas[53] = new Point3f(-0.48f/2, 0.69f/2, -0.11f/2);
        coordenadas[54] = new Point3f(-0.27f/2, 0.67f/2, 0.45f/2); 
        coordenadas[55] = new Point3f(-0.46f/2, 0.16f/2, 0.7f/2); 

        //**************************************LADO 9**************************
        coordenadas[56] = new Point3f(-0.36f, 0.6f, -0.46f);
        coordenadas[57] = new Point3f(-0.79f/2, 0.19f/2, -0.22f/2);
        coordenadas[58] = new Point3f(-0.51f/2, -0.12f/2, -0.65f/2); 
        coordenadas[59] = new Point3f(-0.02f/2, 0.19f/2, -0.82f/2); 
        coordenadas[60] = new Point3f(0f/2, 0.69f/2, -0.48f/2); 
        coordenadas[61] = new Point3f(-0.48f/2, 0.69f/2, -0.11f/2); 
        coordenadas[62] = new Point3f(-0.79f/2, 0.19f/2, -0.22f/2); 

        //**************************************LADO 10**************************
        coordenadas[63] = new Point3f(0.81f/2, 0.9f/2, -1.31f/2);
        coordenadas[64] = new Point3f(-0.02f/2, 0.19f/2, -0.82f/2);
        coordenadas[65] = new Point3f(0.46f/2, -0.14f/2, -0.68f/2);
        coordenadas[66] = new Point3f(0.78f/2, 0.16f/2, -0.27f/2); 
        coordenadas[67] = new Point3f(0.5f/2, 0.67f/2, -0.15f/2); 
        coordenadas[68] = new Point3f(0f/2, 0.69f/2, -0.48f/2); 
        coordenadas[69] = new Point3f(-0.02f/2, 0.19f/2, -0.82f/2); 

        //**************************************LADO 11**************************
        coordenadas[70] = new Point3f(1.5f/2, 0.9f/2, 1f/2);
        coordenadas[71] = new Point3f(0.78f/2, 0.16f/2, -0.32f/2); 
        coordenadas[72] = new Point3f(0.79f/2, -0.18f/2, 0.23f/2);
        coordenadas[73] = new Point3f(0.51f/2, 0.13f/2, 0.67f/2); 
        coordenadas[74] = new Point3f(0.33f/2, 0.66f/2, 0.43f/2); 
        coordenadas[75] = new Point3f(0.5f/2, 0.67f/2, -0.15f/2); 
        coordenadas[76] = new Point3f(0.78f/2, 0.16f/2, -0.32f/2);

        //**************************************LADO 12**************************
        coordenadas[77] = new Point3f(0f/2, 1.5f/2, 0f/2); 
        coordenadas[78] = new Point3f(0.5f/2, 0.67f/2, -0.15f/2);
        coordenadas[79] = new Point3f(0.33f/2, 0.66f/2, 0.43f/2);
        coordenadas[80] = new Point3f(-0.27f/2, 0.67f/2, 0.45f/2);
        coordenadas[81] = new Point3f(-0.48f/2, 0.69f/2, -0.11f/2);
        coordenadas[82] = new Point3f(0f/2, 0.69f/2, -0.48f/2);  
        coordenadas[83] = new Point3f(0.5f/2, 0.67f/2, -0.15f/2);*/

        for(int i=0;i<colores.length;i++){
            colores[i] = verde;
        }
        
        
        
        geometryInfo = new GeometryInfo(GeometryInfo.TRIANGLE_FAN_ARRAY);
        geometryInfo.setColors(colores);
        geometryInfo.setCoordinates(coordenadas);
        geometryInfo.setStripCounts(array);
        NormalGenerator generador = new NormalGenerator();
        generador.generateNormals(geometryInfo);
        Stripifier stripi = new Stripifier();
        stripi.stripify(geometryInfo);
        return geometryInfo;
        
    }
    
    public Estrella_12_Picos(){
        setLayout(new BorderLayout());
        GraphicsConfiguration configuracion = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(configuracion);
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        
        add("Center", canvas3D);
        BranchGroup escena = createSceneGraph();
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(escena);
    }
    
    Appearance crearApariencia(){

        Appearance materialAppear = new Appearance();
        PolygonAttributes polyAttrib = new PolygonAttributes();
        polyAttrib.setCullFace(PolygonAttributes.CULL_NONE);
        materialAppear.setPolygonAttributes(polyAttrib);

        Material material = new Material();

        material.setDiffuseColor(new Color3f(0.2f, .6f, 6f));
        materialAppear.setMaterial(material);

        return materialAppear;
    }
    
}   
    
    