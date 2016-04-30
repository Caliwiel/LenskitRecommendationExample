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
import org.lenskit.data.dao.ItemNameDAO;
import org.lenskit.data.dao.MapItemNameDAO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Responsable=Elodie (haha!) on 25/04/2016.
 * Démonstration de Lenskit. L'application construit un model de recommendation
 * à partir de fichier CSV, puis génère une recommandation pour un utilisateur
 * <p/>
 */

public class Example implements Runnable {

    /**
     * Main méthode pour lancer la recommandation on
     * 26/04/2016.
     * @param args
     * L'algo choisi et l'id utilisateur pour lequel
     * on fait la recommandation.
     */
    public static void main(String[] args) {
        Example hello = new Example(args);
        try {
            System.out.println("début");
            hello.run();
        } catch (RuntimeException e) {
            System.err.println(e.toString());
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    //private String delimiter = "\t";
    /**
     * Fichier en entrée avec les notes utilisateurs pour les items
     */
    private File inputFile = new File("data/langage/ratings.csv");

    /**
     * Fichier en entrée pour les items et leurs caractérisques
     */
    private File movieFile = new File("data/langage/langages.csv");

    /**
     * L'algorithme choisi
     */
    private String algo;

    /**
     * Les utilisateurs pour lesquels on produit la recommandation
     */
    private List<Long> users;

    /**
     * Constructeur
     * @param args
     * L'algo choisi et l'id utilisateur pour lequel
     * on fait la recommandation.
     */
    public Example(String[] args) {
        users = new ArrayList<Long>(args.length);
        algo = args[0];
        for (String arg : args) {
            if(!arg.equals(algo))
                users.add(Long.parseLong(arg));
        }
    }

    /**
     * Lancer la recommandation
     * Les commentaires explicatifs de la suite sont issus de l'exemple
     * donné par Lenskit : hello lenskit
     */
    public void run() {
        // We first need to configure the data access.
        // We will use a simple delimited file; you can use something else like
        // a database (see JDBCRatingDAO).
        EventDAO dao = TextEventDAO.create(inputFile, Formats.movieLensLatest());
        ItemNameDAO names;
        try {
            names = MapItemNameDAO.fromCSVFile(movieFile, 1);
        } catch (IOException e) {
            throw new RuntimeException("cannot load names", e);
        }

        // Next: load the LensKit algorithm configuration
        LenskitConfiguration config = null;
        try {
            if (algo.equals("item")) {
                config = ConfigHelpers.load(new File("etc/item-item-collaborative.groovy"));
            } else if (algo.equals("user")) {
                config = ConfigHelpers.load(new File("etc/user-user-collaborative.groovy"));
            } else if (algo.equals("matrix")) {
                config = ConfigHelpers.load(new File("etc/matrix-factorization.groovy"));
            } else if (algo.equals("slope")) {
                config = ConfigHelpers.load(new File("etc/slope-one.groovy"));
            } else {
                config = ConfigHelpers.load(new File("etc/item-item.groovy"));
            }
        } catch (IOException e) {
            throw new RuntimeException("could not load configuration", e);
        }
        // Add our data component to the configuration
        config.addComponent(dao);

        // There are more parameters, roles, and components that can be set. See the
        // JavaDoc for each recommender algorithm for more information.

        // Now that we have a configuration, build a recommender engine from the configuration
        // and data source. This will compute the similarity matrix and return a recommender
        // engine that uses it.
        LenskitRecommenderEngine engine = LenskitRecommenderEngine.build(config);

        // Finally, get the recommender and use it.
        try (LenskitRecommender rec = engine.createRecommender()) {
            // we want to recommend items
            ItemRecommender irec = rec.getItemRecommender();
            assert irec != null; // not null because we configured one
            System.out.println("Algo utilisé : "+ algo);
            // for users
            for (long user : users) {
                // get 10 recommendation for the user
                ResultList recs = irec.recommendWithDetails(user, 10, null, null);
                System.out.format("Recommendations for user %d:\n", user);
                for (Result item : recs) {
                    String name = names.getItemName(item.getId());
                    System.out.format("\t%d (%s): %.2f\n", item.getId(), name, item.getScore());
                }
            }
            System.out.println("Fin exécution ");
        }
    }
}
