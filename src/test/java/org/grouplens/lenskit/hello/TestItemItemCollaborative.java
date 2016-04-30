package org.grouplens.lenskit.hello;

import org.grouplens.lenskit.data.text.Formats;
import org.grouplens.lenskit.data.text.TextEventDAO;
import org.lenskit.LenskitConfiguration;
import org.lenskit.LenskitRecommender;
import org.lenskit.LenskitRecommenderEngine;
import org.lenskit.api.ItemRecommender;
import org.lenskit.api.Result;
import org.lenskit.api.ResultList;
import org.lenskit.config.ConfigHelpers;
import org.lenskit.data.dao.EventDAO;

import java.io.File;
import java.io.IOException;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by anais on 30/04/16.
 */
public class TestItemItemCollaborative {
    @org.junit.Test
    public void testItemItem () throws IOException {

        String[] args = new String[2];
        args[0] = "item";
        Example exTest;
        for (int i = 1; i < 6; i++) {
            args[1] = String.valueOf(i);
            exTest = new Example(args);
            exTest.run();
            assertNotSame(exTest.getRecommandations().size(),0);
        }
    }
}
