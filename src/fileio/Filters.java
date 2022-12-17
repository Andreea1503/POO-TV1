package fileio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class that contains the types of the filters that can be applied to the
 * movies
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Filters {
    private Sort sort;
    private Contains contains;
    public Filters() {
    }

    /**
     * Method that returns the sort type
     * @return sort
     */
    public Sort getSort() {
        return sort;
    }

    /**
     * Method that sets the sort type
     * @param sort
     */
    public void setSort(final Sort sort) {
        this.sort = sort;
    }

    /**
     * Method that returns the contains type
     * @return contains
     */
    public Contains getContains() {
        return contains;
    }

    /**
     * Method that sets the contains type
     * @param contains
     */
    public void setContains(final Contains contains) {
        this.contains = contains;
    }
}
