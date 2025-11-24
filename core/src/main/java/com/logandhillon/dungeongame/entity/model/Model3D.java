package com.logandhillon.dungeongame.entity.model;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.logandhillon.dungeongame.entity.Entity;

/**
 * abstract class for all 3D models. extends {@link Entity}
 * @implNote remember to FLIP the Z-axis, as given vector are relative to camera, not absolute to world
 * @author logan
 */
public abstract class Model3D extends Entity {
    /**
     * @return the static {@link Model} of this model. this should not be used to render to the scene.
     * @see Model3D#getModelInstance()
     */
    public abstract Model getModel();

    /**
     * @return a dynamic {@link ModelInstance} of this model, that can have transformations and be rendered to the scene
     */
    public abstract ModelInstance getModelInstance();

    /**
     * translates this model instance by relative units
     * @param d relative coordinates to translate by
     */
    public void translate(Vector3 d) {
        this.getModelInstance().transform.translate(d.x, d.y, -d.z);
    }

    /**
     * moves this model instance to absolute coordinates
     * @param pos absolute coordinates to move to
     */
    public void moveTo(Vector3 pos) {
        this.getModelInstance().transform.setToTranslation(pos.x, pos.y, -pos.z);
    }

    /**
     * Disposes of the model then destroys the entity.
     */
    @Override
    public void destroy() {
        this.getModel().dispose();
        super.destroy();
    }
}
