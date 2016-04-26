import org.grouplens.lenskit.transform.normalize.BaselineSubtractingUserVectorNormalizer
import org.grouplens.lenskit.transform.normalize.UserVectorNormalizer
import org.lenskit.api.ItemScorer
import org.lenskit.baseline.BaselineScorer
import org.lenskit.baseline.ItemMeanRatingItemScorer
import org.lenskit.baseline.UserMeanBaseline
import org.lenskit.baseline.UserMeanItemScorer
import org.lenskit.knn.MinNeighbors
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.item.ItemItemScorer
/**
 * Created by Responsable on 26/04/2016.
 */

// configure the item scorer
bind ItemScorer to ItemItemScorer.class
// set up a baseline predictor
bind (BaselineScorer,ItemScorer) to ItemMeanRatingItemScorer
// use the baseline for normalizing user ratings
bind UserVectorNormalizer to BaselineSubtractingUserVectorNormalizer
// the default neighborhood size is 20, so the next line isn't technically needed
set NeighborhoodSize to 2