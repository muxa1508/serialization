import java.io.*;

public class Basket implements Serializable {

    private long[] productPrice;
    private String[] productName;
    private int[] productCount;

    public Basket(long[] productPrice, String[] productName) {
        this.productPrice = productPrice;
        this.productName = productName;
        this.productCount = new int[productName.length];
    }

    public Basket(long[] productPrice, String[] productName, int[] productCount) {
        this.productPrice = productPrice;
        this.productName = productName;
        this.productCount = productCount;
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

    public void saveBin(File file) throws IOException {

        try (FileOutputStream out = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(out)) {
            for (String name : productName) {
                oos.writeObject(this);
            }
        }
    }

    static Basket loadFromBinFile(File file) throws IOException, ClassNotFoundException {

        try (FileInputStream in = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(in)) {
            Basket basket = (Basket) ois.readObject();
            return basket;
        }
    }
}

