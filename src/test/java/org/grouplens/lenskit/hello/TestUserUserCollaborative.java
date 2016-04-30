package org.grouplens.lenskit.hello;

import java.io.IOException;

import static junit.framework.Assert.assertNotSame;

/**
 * Created by anais on 30/04/16.
 */
public class TestUserUserCollaborative {
    @org.junit.Test
    public void testUserUser () throws IOException {

        String[] args = new String[2];
        args[0] = "user";
        Example exTest;
        for (int i = 1; i < 6; i++) {
            args[1] = String.valueOf(i);
            exTest = new Example(args);
            exTest.run();
            assertNotSame(exTest.getRecommandations().size(),0);
        }
    }
}
