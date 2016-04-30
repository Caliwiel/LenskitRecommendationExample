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
public class RecommenderEngineUserUser extends RecommenderEngine {
    private EventDAO dao;
    public RecommenderEngineUserUser(EventDAO dao)
    {
        this.dao = dao;
    }
    @Override
    public LenskitRecommenderEngine getRecommender() {
        LenskitRecommenderEngine engine = null;
        try {

            LenskitConfiguration config = ConfigHelpers.load(new File("etc/user-user-collaborative.groovy"));
            config.addComponent(dao);
            engine = LenskitRecommenderEngine.build(config);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return engine;
    }
}
