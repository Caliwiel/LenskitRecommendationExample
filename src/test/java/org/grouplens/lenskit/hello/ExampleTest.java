package org.grouplens.lenskit.hello;

import org.lenskit.api.ResultList;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by anais on 30/04/16.
 */
public class ExampleTest {

    private static final int NB_RECS = 51;

    @org.junit.Test
    public void recommendationsIdentiques() throws Exception {
        //Given
        String[] args = new String[2];
        args[0] = "user";
        args[1] = "1";
        Example example = new Example(args);
        //When
        ResultList[] recs = new ResultList[NB_RECS];
        for (int i = 0; i<NB_RECS;i++) {
            example.run();
            recs[i] = example.getRecommandations();
        }
        //Then
        for (int i=0;i<NB_RECS-1;i++) {
            assertTrue(recs[i].equals(recs[i+1]));
        }
    }

}