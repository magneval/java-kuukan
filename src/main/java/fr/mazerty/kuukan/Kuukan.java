package fr.mazerty.kuukan;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;

public class Kuukan extends SimpleApplication {

    public static void main(String[] args) {
        new Kuukan().start();
    }

    @Override
    public void simpleInitApp() {
        Sphere sphere = new Sphere(20, 20, 1);

        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.White);
        material.setColor("GlowColor", ColorRGBA.White);

        Geometry geometry = new Geometry("sphere", sphere);
        geometry.setMaterial(material);

        rootNode.attachChild(geometry);

        BitmapText text = new BitmapText(guiFont);
        text.setText("Glowing star");
        text.setLocalTranslation(0, settings.getHeight(), 0);

        guiNode.attachChild(text);

        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        fpp.addFilter(new BloomFilter(BloomFilter.GlowMode.Objects));
        viewPort.addProcessor(fpp);
    }

}
