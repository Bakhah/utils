package db;

/**
 * Created by bakhah on 24/03/17.
 */

public enum ItemEnum {
    COL_ID("ID", 0), COL_NAME("NAME", 1);

    private String name;
    private int columnNumber;



    ItemEnum(String id, int i) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }
}
