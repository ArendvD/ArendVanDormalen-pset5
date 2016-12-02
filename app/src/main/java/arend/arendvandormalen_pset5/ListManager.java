package arend.arendvandormalen_pset5;

/**
 * Created by Arend on 2016-11-28.
 * Managing Lists
 */
public class ListManager {
    private static ListManager ourInstance = new ListManager();

    public static ListManager getInstance() {
        return ourInstance;
    }

    private ListManager() {
    }
}
