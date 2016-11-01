package org.strokova.booker.api.queryParameters;

/**
 * 01.11.2016.
 */
public enum HotelQueryParameter {
    NAME("name", "name"),
    HAS_POOL("hasPool", "hasPool"),
    HAS_WATERPARK("hasWaterpark", "hasWaterpark"),
    HAS_TENNIS_COURT("hasTennisCourt", "hasTennisCourt");

    // exact column name
    private String columnName;
    // for clients to use in their sorting requests
    private String sortColumn;

    // private
    HotelQueryParameter(String columnName, String sortColumn) {
        this.columnName = columnName;
        this.sortColumn = sortColumn;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public String getColumnName() {
        return columnName;
    }
}
