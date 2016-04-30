package org.grouplens.lenskit.hello;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by anais on 30/04/16.
 */
public class TestDonneesEntrees {
    @org.junit.Test
    public void testDonneesEntrees (){
        try{
            InputStream fluxTest = new FileInputStream("/home/anais/IdeaProjects/LenskitRecommendationExample/src/test/java/org/grouplens/lenskit/hello/ressources/ratingsTests.csv");
            InputStreamReader ipsrTest = new InputStreamReader(fluxTest);
            BufferedReader brTest = new BufferedReader(ipsrTest);

            InputStream flux = new FileInputStream("/home/anais/IdeaProjects/LenskitRecommendationExample/data/langage/ratings.csv");
            InputStreamReader ipsr = new InputStreamReader(flux);
            BufferedReader br = new BufferedReader(ipsr);

            String ligneTest = brTest.readLine();
            String ligne = br.readLine();
            while ( ligneTest != null || ligne != null ){
                if (ligneTest == null || ligne == null)
                    assertTrue(false);
                else
                    assertEquals(ligne,ligneTest);
                ligneTest = brTest.readLine();
                ligne = br.readLine();
            }
            brTest.close();
            br.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

    }
}
