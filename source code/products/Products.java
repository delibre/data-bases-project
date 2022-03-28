package products;

import javafx.beans.property.*;

public class Products {

    private int id_product;
    private final StringProperty product = new SimpleStringProperty();
    private int id_category;
    private final StringProperty category = new SimpleStringProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final IntegerProperty quantity = new SimpleIntegerProperty();

    /**
     * Konstruuje obiekt Products
     * @param id_product [int]
     * @param product [String]
     * @param id_category [int]
     * @param category [String]
     * @param price [double]
     */

    public Products(int id_product, String product, int id_category, String category, double price) {
        this.id_product = id_product;
        this.product.set(product);
        this.id_category = id_category;
        this.category.set(category);
        this.price.set(price);
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public int getId_category() {
        return id_category;
    }

    public StringProperty getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product.set(product);
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public int getId_product() {
        return id_product;
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public DoubleProperty getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }


}
