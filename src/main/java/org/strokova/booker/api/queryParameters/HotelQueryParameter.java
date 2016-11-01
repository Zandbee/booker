package org.strokova.booker.api.queryParameters;

/**
 * 01.11.2016.
 */
public enum HotelQueryParameter {
    ID("id", "id", "id"),
    NAME("name", "name", "name"),
    HAS_POOL("hasPool", "hasPool", "hasPool"),
    HAS_WATERPARK("hasWaterpark", "hasWaterpark", "hasWaterpark"),
    HAS_TENNIS_COURT("hasTennisCourt", "hasTennisCourt", "hasTennisCourt");

    // exact column name
    private String columnName;
    // for clients to use in their sorting requests as parameter value
    private String sortColumn;
    // for clients to use in their query strings as parameter name
    private String queryParameterName;

    // private
    HotelQueryParameter(String columnName, String sortColumn, String queryParameter) {
        this.columnName = columnName;
        this.sortColumn = sortColumn;
        this.queryParameterName = queryParameter;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getQueryParameterName() {
        return queryParameterName;
    }
}
