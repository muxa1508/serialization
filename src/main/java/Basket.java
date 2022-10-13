import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Basket {

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

    public void saveTxt(File textFile) throws IOException {
        try (PrintWriter out = new PrintWriter(textFile)) {
            for (String name : productName) {
                out.print(name + " ");
            }
            out.print("\n");
            for (long price : productPrice) {
                out.print(price + " ");
            }
            out.print("\n");
            for (int count : productCount) {
                out.print(count + " ");
            }
        }
    }

    static Basket loadFromTxtFile(File textFile) throws IOException {

        try (FileReader in = new FileReader(textFile);
             Scanner scanner = new Scanner(in)) {
            String[] name = scanner.nextLine().split(" ");
            long[] price = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToLong(value -> (long) Integer.parseInt(value))
                    .toArray();
            int[] count = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(value -> Integer.parseInt(value))
                    .toArray();
            return new Basket(price, name, count);
        }
    }
}

