package com.logandhillon.dungeongame.entity.model;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

/**
 * Builds a hollow room using six rectangles (floor, ceiling, walls).
 * Call getModelInstance() to place it in the world.
 */
public class RoomModel extends Model3D {
    private final Model model;
    private final ModelInstance instance;

    /**
     * a hollow room with 6 rectangles (walls, floor, ceiling)
     * @param pos position of room
     * @param s size of room
     * @param color color of all walls
     */
    public RoomModel(Vector3 pos, Vector3 s, Color color) {
        ModelBuilder builder = new ModelBuilder();
        builder.begin();

        Material mat = new Material(ColorAttribute.createDiffuse(color));
        long attrs = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal;

        builder.part("floor", GL20.GL_TRIANGLES, attrs, mat)
               .rect(
                   0, 0f, 0,
                   s.x, 0f, 0,
                   s.x, 0f, -s.z,
                   0, 0f, -s.z,
                   0f, 1f, 0f
               );
        builder.part("floor_back", GL20.GL_TRIANGLES, attrs, mat)
               .rect(
                   0, 0f, -s.z,
                   s.x, 0f, -s.z,
                   s.x, 0f, 0,
                   0, 0f, 0,
                   0f, -1f, 0f
               );

        builder.part("ceiling", GL20.GL_TRIANGLES, attrs, mat)
               .rect(
                   0, s.y, -s.z,
                   s.x, s.y, -s.z,
                   s.x, s.y, 0,
                   0, s.y, 0,
                   0f, -1f, 0f
               );
        builder.part("ceiling_back", GL20.GL_TRIANGLES, attrs, mat)
               .rect(
                   0, s.y, 0,
                   s.x, s.y, 0,
                   s.x, s.y, -s.z,
                   0, s.y, -s.z,
                   0f, 1f, 0f
               );

        builder.part("back", GL20.GL_TRIANGLES, attrs, mat)
               .rect(
                   0, 0f, 0,
                   0, s.y, 0,
                   s.x, s.y, 0,
                   s.x, 0f, 0,
                   0f, 0f, 1f
               );
        builder.part("back_back", GL20.GL_TRIANGLES, attrs, mat)
               .rect(
                   s.x, 0f, 0,
                   s.x, s.y, 0,
                   0, s.y, 0,
                   0, 0f, 0,
                   0f, 0f, -1f
               );

        builder.part("front", GL20.GL_TRIANGLES, attrs, mat)
               .rect(
                   s.x, 0f, -s.z,
                   s.x, s.y, -s.z,
                   0, s.y, -s.z,
                   0, 0f, -s.z,
                   0f, 0f, -1f
               );
        builder.part("front_back", GL20.GL_TRIANGLES, attrs, mat)
               .rect(
                   0, 0f, -s.z,
                   0, s.y, -s.z,
                   s.x, s.y, -s.z,
                   s.x, 0f, -s.z,
                   0f, 0f, 1f
               );

        builder.part("left", GL20.GL_TRIANGLES, attrs, mat)
               .rect(
                   0, 0f, -s.z,
                   0, s.y, -s.z,
                   0, s.y, 0,
                   0, 0f, 0,
                   1f, 0f, 0f
               );
        builder.part("left_back", GL20.GL_TRIANGLES, attrs, mat)
               .rect(
                   0, 0f, 0,
                   0, s.y, 0,
                   0, s.y, -s.z,
                   0, 0f, -s.z,
                   -1f, 0f, 0f
               );

        builder.part("right", GL20.GL_TRIANGLES, attrs, mat)
               .rect(
                   s.x, 0f, 0,
                   s.x, s.y, 0,
                   s.x, s.y, -s.z,
                   s.x, 0f, -s.z,
                   -1f, 0f, 0f
               );
        builder.part("right_back", GL20.GL_TRIANGLES, attrs, mat)
               .rect(
                   s.x, 0f, -s.z,
                   s.x, s.y, -s.z,
                   s.x, s.y, 0,
                   s.x, 0f, 0,
                   1f, 0f, 0f
               );

        model = builder.end();
        instance = new ModelInstance(model);
        this.moveTo(pos);
    }

    public Model getModel() {
        return model;
    }

    public ModelInstance getModelInstance() {
        return instance;
    }
}
