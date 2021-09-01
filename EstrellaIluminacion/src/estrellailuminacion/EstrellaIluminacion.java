/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrellailuminacion;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Stripifier;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

/**
 *
 * @author luis
 */
public class EstrellaIluminacion extends Applet {
    
    public static void main(String[] args) {
        Frame ventana = new MainFrame(new EstrellaIluminacion(), 500, 500);
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
        figura3d.addGeometry(crear_cubo().getGeometryArray());
        figura3d.addGeometry(crear_puntas().getGeometryArray());
        figura3d.setAppearance(crearApariencia());
        
        //figura3d.setAppearance(Apariencia());
        
        transfor.addChild(figura3d);
        rotacion.setSchedulingBounds(limites);
        transfor.addChild(rotacion);
        
        
        AmbientLight lightA = new AmbientLight();
        lightA.setInfluencingBounds(new BoundingSphere());
        transfor.addChild(lightA);

        DirectionalLight lightD1 = new DirectionalLight();
        lightD1.setInfluencingBounds(new BoundingSphere());
        Vector3f direction1 = new Vector3f(-1.0f, 1.0f, -1.0f);//abajo
        //Vector3f direction1 = new Vector3f(-1.0f, -1.0f, -0.5f);//arriba
        //Vector3f direction1 = new Vector3f(-1.0f, 0.0f, 0.0f);
        direction1.normalize();
        lightD1.setDirection(direction1);
        lightD1.setColor(new Color3f(1.0f, 0.0f, 0.0f));
        root.addChild(lightD1);

        
        DirectionalLight lightD2 = new DirectionalLight();
        lightD2.setInfluencingBounds(new BoundingSphere());
        //Vector3f direction2 = new Vector3f(1.0f, -1.0f, -1.5f);
        Vector3f direction2 = new Vector3f(-1.0f, -1.0f, -0.5f);
        direction2.normalize();
        lightD2.setDirection(direction2);
        lightD2.setColor(new Color3f(1.0f, 0.0f, 0.0f));
        root.addChild(lightD2);
        
        
        Background background = new Background(); 
        background.setColor(0.2f, 0.6f, 6.0f); 
        background.setApplicationBounds(new BoundingSphere()); 
        root.addChild(background);
          
        
        root.compile();
        return root;
    }
    
    private GeometryInfo crear_cubo(){
        GeometryInfo geometryInfo;
        int  array[] = {4, 4, 4, 4, 4, 4};
        Point3f[] coordenadas = new Point3f[4+4+4+4+4+4];
        Color3f[] colores = new Color3f[4+4+4+4+4+4];
        Color3f verde = new Color3f(1.0f, 1.0f, 0.0f);
        
        Point3f uno=new Point3f(-0.2f, 0.2f, -0.2f);
        Point3f dos=new Point3f(-0.2f, -0.2f, -0.2f);
        Point3f tres=new Point3f(0.2f, -0.2f, -0.2f);
        Point3f cuatro=new Point3f(0.2f, 0.2f, -0.2f);
        Point3f cinco=new Point3f(0.2f, 0.2f, 0.2f);
        Point3f seis=new Point3f(-0.2f, 0.2f, 0.2f);
        Point3f siete=new Point3f(0.2f, -0.2f, 0.2f);
        Point3f ocho=new Point3f(-0.2f, -0.2f, 0.2f);
    
        //**************************************CARA FRONTAL**************************
        coordenadas[0] =  uno;
        coordenadas[1] = dos;  
        coordenadas[2] = tres; 
        coordenadas[3] = cuatro;
        
        //**************************************CARA SUPERIOR**************************
        coordenadas[4] =  uno;
        coordenadas[5] = cuatro;
        coordenadas[6] = cinco;
        coordenadas[7] = seis;
        
        //**************************************CARA DERECHA**************************
        coordenadas[8] = cuatro;
        coordenadas[9] = cinco;
        coordenadas[10] = siete;
        coordenadas[11] = tres;
        
        //**************************************CARA TRASERA**************************
        coordenadas[12] = cinco;
        coordenadas[13] = seis;
        coordenadas[14] = ocho;
        coordenadas[15] =  siete;
        
        //**************************************CARA IZQUIERDA**************************
        coordenadas[16] = uno;
        coordenadas[17] = dos;
        coordenadas[18] = ocho;
        coordenadas[19] = seis;
        
        //**************************************CARA ABAJO**************************
        coordenadas[20] = ocho;
        coordenadas[21] = dos;
        coordenadas[22] = tres; 
        coordenadas[23] = siete;
        
        for(int i=0;i<colores.length;i++){
            colores[i] = verde;
        }

        geometryInfo = new GeometryInfo(GeometryInfo.POLYGON_ARRAY);
        geometryInfo.setColors(colores);
        geometryInfo.setCoordinates(coordenadas);
        geometryInfo.setStripCounts(array);
        NormalGenerator generador = new NormalGenerator();
        generador.generateNormals(geometryInfo);
        Stripifier stripi = new Stripifier();
        stripi.stripify(geometryInfo);
        return geometryInfo;
        
    }
    
    private GeometryInfo crear_puntas(){
        GeometryInfo geometryInfo;
        int numero=7;
        int  array[] = {numero, numero, numero, numero, numero, numero};
        Point3f[] coordenadas = new Point3f[numero*6];
        Color3f[] colores = new Color3f[numero*6];
        Color3f rojo = new Color3f(1.0f, 0.0f, 0.0f);//rgb
        Color3f verde = new Color3f(0.0f, 1.0f, 0.0f);
        Color3f azul = new Color3f(0.0f, 0.0f, 1.0f);
        Color3f el_otro = new Color3f(0.4f, 1.0f, 0.1f);
        
        Point3f punta_arriba=new Point3f(0f/2, 1.5f/2, 0f/2);//en y
        Point3f punta_frontal=new Point3f(0f/2, 0f/2, -1.5f/2);//en -z
        Point3f punta_izquierda=new Point3f(-1.5f/2, 0f/2, 0f/2);//en -x
        Point3f punta_abajo=new Point3f(0f/2, -1.5f/2, 0f/2);//en -y
        Point3f punta_derecha=new Point3f(1.5f/2, 0f/2, 0f/2);//en x
        Point3f punta_trasera=new Point3f(0f/2, 0f/2, 1.5f/2);//en z
        
        Point3f uno=new Point3f(-0.2f, 0.2f, -0.2f);
        Point3f dos=new Point3f(-0.2f, -0.2f, -0.2f);
        Point3f tres=new Point3f(0.2f, -0.2f, -0.2f);
        Point3f cuatro=new Point3f(0.2f, 0.2f, -0.2f);
        Point3f cinco=new Point3f(0.2f, 0.2f, 0.2f);
        Point3f seis=new Point3f(-0.2f, 0.2f, 0.2f);
        Point3f siete=new Point3f(0.2f, -0.2f, 0.2f);
        Point3f ocho=new Point3f(-0.2f, -0.2f, 0.2f);
    
        //**************************************CARA SUPERIOR**************************PICO 1
        coordenadas[0] =  uno;
        coordenadas[1] = seis;
        coordenadas[2] = punta_arriba;
        coordenadas[3] = cinco;
        coordenadas[4] = cuatro;
        coordenadas[5] = punta_arriba;
        coordenadas[6] = uno;
        
        //**************************************CARA INFERIOR**************************pico 2
        coordenadas[7] =  uno;
        coordenadas[8] = dos;
        coordenadas[9] = punta_frontal;
        coordenadas[10] = tres;
        coordenadas[11] = cuatro;
        coordenadas[12] = punta_frontal;
        coordenadas[13] = uno;
        
        //**************************************CARA IZQUIERDA**************************pico 2
        coordenadas[14] =  uno;
        coordenadas[15] = dos;
        coordenadas[16] = punta_izquierda;
        coordenadas[17] = ocho;
        coordenadas[18] = seis;
        coordenadas[19] = punta_izquierda;
        coordenadas[20] = uno;
        
        //**************************************CARA ABAJO**************************pico 2
        coordenadas[21] =  dos;
        coordenadas[22] = tres;
        coordenadas[23] = punta_abajo;
        coordenadas[24] = siete;
        coordenadas[25] = ocho;
        coordenadas[26] = punta_abajo;
        coordenadas[27] = dos;
        
        //**************************************CARA DERECHA**************************pico 2
        coordenadas[28] =  cuatro;
        coordenadas[29] = tres;
        coordenadas[30] = punta_derecha;
        coordenadas[31] = siete;
        coordenadas[32] = cinco;
        coordenadas[33] = punta_derecha;
        coordenadas[34] = cuatro;
        
        //**************************************CARA ATRAS**************************pico 2
        coordenadas[35] =  ocho;
        coordenadas[36] = seis;
        coordenadas[37] = punta_trasera;
        coordenadas[38] = cinco;
        coordenadas[39] = siete;
        coordenadas[40] = punta_trasera;
        coordenadas[41] = ocho;
        
        
        for(int i=0;i<colores.length;i++){
            colores[i] = rojo;
        }
        /*int tam=colores.length;
        int por=tam/4;
        int u=por,d=por*2,t=por*3,c=tam;
        for(int i=0;i<u;i++){
            colores[i] = rojo;
        }
        for(int i=u;i<d;i++){
            colores[i] = verde;
        }
        for(int i=d;i<t;i++){
            colores[i] = azul;
        }
        for(int i=t;i<c;i++){
            colores[i] = el_otro;
        }*/
            
        geometryInfo = new GeometryInfo(GeometryInfo.TRIANGLE_STRIP_ARRAY);
        geometryInfo.setColors(colores);
        geometryInfo.setCoordinates(coordenadas);
        geometryInfo.setStripCounts(array);
        NormalGenerator generador = new NormalGenerator();
        generador.generateNormals(geometryInfo);
        Stripifier stripi = new Stripifier();
        stripi.stripify(geometryInfo);
        return geometryInfo;
        
    }
    
    Appearance crearApariencia(){
        Appearance materialAppear = new Appearance();
        PolygonAttributes polyAttrib = new PolygonAttributes();
        //materialAppear.setTransparencyAttributes(new TransparencyAttributes(TransparencyAttributes.BLENDED, 0.1f));
        polyAttrib.setCullFace(PolygonAttributes.CULL_NONE);
        polyAttrib.setBackFaceNormalFlip(true);
        materialAppear.setPolygonAttributes(polyAttrib);
        
        Material material = new Material();
        material.setDiffuseColor(new Color3f(0.2f, 0.6f, 0.6f));
        materialAppear.setMaterial(material);
        
        return materialAppear;
    }
    
    Appearance Apariencia(){
        Appearance materialAppear = new Appearance();
        PolygonAttributes polyAttrib = new PolygonAttributes();
        //materialAppear.setTransparencyAttributes(new TransparencyAttributes(TransparencyAttributes.BLENDED, 0.1f));
        polyAttrib.setCullFace(PolygonAttributes.CULL_NONE);
        materialAppear.setPolygonAttributes(polyAttrib);
        
        
        return materialAppear;
    }
    
    public EstrellaIluminacion(){
        setLayout(new BorderLayout());
        GraphicsConfiguration configuracion = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(configuracion);
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        
        add("Center", canvas3D);
        BranchGroup escena = createSceneGraph();
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(escena);
    }
}