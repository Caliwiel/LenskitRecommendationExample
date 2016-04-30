package org.grouplens.lenskit.hello;

import org.lenskit.LenskitRecommenderEngine;
import org.lenskit.data.dao.EventDAO;

import java.io.IOException;

/**
 * Created by anais on 30/04/16.
 */
public abstract class RecommenderEngine {
    public abstract LenskitRecommenderEngine getRecommender() throws IOException;
}
