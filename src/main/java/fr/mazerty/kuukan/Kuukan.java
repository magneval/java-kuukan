package fr.mazerty.kuukan;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class Kuukan extends SimpleApplication {

    public static void main(String[] args) {
        new Kuukan().start();
    }

    @Override
    public void simpleInitApp() {
        Box box = new Box(1, 1, 1);

        Material material = new Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");

        Geometry geometry = new Geometry("box", box);
        geometry.setMaterial(material);

        rootNode.attachChild(geometry);
    }

}
