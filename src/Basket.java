import java.io.File;

public class Basket {

    private int[] productPrice;
    private String[] productName;
    private int[] productCount;

    public Basket(int[] productPrice, String[] productName) {
        this.productPrice = productPrice;
        this.productName = productName;
        this.productCount = new int[productName.length];
    }

    public void addToCart(int productNum, int amount) {
        productCount[productNum] += amount;
    }

    public void printCart() {
                for (int i = 0; i < productName.length; i++) {
            if (productCount[i] != 0) {
                System.out.println(productName[i] + " " + productCount[i]);
            }
        }
    }

    public void saveTxt(File textFile) {

    }

    static Basket loadFromTxtFile(File textFile) {

    }

}
