import org.grouplens.lenskit.iterative.IterationCount
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
import org.lenskit.mf.funksvd.FeatureCount
import org.lenskit.mf.funksvd.FunkSVDItemScorer

/**
 * Created by Responsable on 26/04/2016.
 */

bind ItemScorer to FunkSVDItemScorer.class
bind (BaselineScorer, ItemScorer) to UserMeanItemScorer
bind (UserMeanBaseline, ItemScorer) to ItemMeanRatingItemScorer
set FeatureCount to 10
set IterationCount to 125