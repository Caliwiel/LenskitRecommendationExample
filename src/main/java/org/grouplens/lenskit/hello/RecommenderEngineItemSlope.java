package org.grouplens.lenskit.hello;

import org.lenskit.LenskitConfiguration;
import org.lenskit.LenskitRecommenderEngine;
import org.lenskit.config.ConfigHelpers;
import org.lenskit.data.dao.EventDAO;

import java.io.File;
import java.io.IOException;

/**
 * Created by anais on 30/04/16.
 */
public class RecommenderEngineItemSlope extends RecommenderEngine {
    private EventDAO dao;
    public RecommenderEngineItemSlope(EventDAO dao)
    {
        this.dao = dao;
    }
    @Override
    public LenskitRecommenderEngine getRecommender() {
        LenskitRecommenderEngine engine = null;
        try {

            LenskitConfiguration config2 = ConfigHelpers.load(new File("etc/slope-one.groovy"));
            LenskitConfiguration config = ConfigHelpers.load(new File("etc/item-item-collaborative.groovy"));
            config.addComponent(dao);
            engine = LenskitRecommenderEngine.newBuilder().addConfiguration(config).addConfiguration(config2).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return engine;
    }
}
