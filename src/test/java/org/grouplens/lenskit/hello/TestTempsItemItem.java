package org.grouplens.lenskit.hello;

import static junit.framework.Assert.assertTrue;

/**
 * Created by anais on 30/04/16.
 */
public class TestTempsItemItem {

    @org.junit.Test
    public void testTemps (){

        String[] args = new String[2];
        args[0] = "item";
        Example exTest;
        for (int i = 1; i < 6; i++) {
            args[1] = String.valueOf(2);
            long startTime = System.nanoTime();
            exTest = new Example(args);
            exTest.run();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            //Attention temps en nanosecondes pour plus de précisions!
            assertTrue(duration < 2000000000);
        }
    }
}
