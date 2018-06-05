package nikhi.a5_minutestressreliever;

import android.provider.BaseColumns;
/**
 * Created by nikhi on 7/28/17.
 */

final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "title";
        public static final String COLUMN1_NAME_TITLE = "date";
        public static final String COLUMN2_NAME_TITLE = "activity";
        public static final String COLUMN3_NAME_TITLE = "category";
        public static final String COLUMN4_NAME_TITLE = "rating";



    }
}