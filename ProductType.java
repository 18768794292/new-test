package two.domain;

public class ProductType {
    private int id;
    private String typeName;

    public ProductType() {
    }

    public ProductType(int id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
