package yellow.sausages.com.exam;

import android.provider.BaseColumns;

/**
 * Created by Christoffer on 21/01/2018.
 */

public final class NameContract {

    private NameContract() {}

    public static final class NameEntry implements BaseColumns {

        public static final String TABLE_NAME = "names";

        //should, but not needed
        public static final String ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
    }
}
