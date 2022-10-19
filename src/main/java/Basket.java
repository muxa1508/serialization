import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Basket {

    private double[] productPrice;
    private String[] productName;
    private long[] productCount;

    public Basket(double[] productPrice, String[] productName) {
        this.productPrice = productPrice;
        this.productName = productName;
        this.productCount = new long[productName.length];
    }

    public Basket(double[] productPrice, String[] productName, long[] productCount) {
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
            for (double price : productPrice) {
                out.print(price + " ");
            }
            out.print("\n");
            for (long count : productCount) {
                out.print(count + " ");
            }
        }
    }

    static Basket loadFromTxtFile(File textFile) throws IOException {

        try (FileReader in = new FileReader(textFile);
             Scanner scanner = new Scanner(in)) {
            String[] name = scanner.nextLine().split(" ");
            double[] price = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToDouble(value -> (double) Integer.parseInt(value))
                    .toArray();
            long[] count = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToLong(value -> (long) Integer.parseInt(value))
                    .toArray();
            return new Basket(price, name, count);
        }
    }

    public void saveJson(File textFile) {

        JSONObject obj = new JSONObject();

        JSONArray productNames = new JSONArray();
        for (String name : productName) {
            productNames.add(name);
        }
        obj.put("productNames", productNames);
        JSONArray productPrices = new JSONArray();
        for (double price : productPrice) {
            productPrices.add(price);
        }
        obj.put("productPrices", productPrices);
        JSONArray productCounts = new JSONArray();
        for (long count : productCount) {
            productCounts.add(count);
        }
        obj.put("productCounts", productCounts);

        try (FileWriter file = new FileWriter(textFile)) {
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Basket loadFromJsonFile(File textFile) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(textFile));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray productNames = (JSONArray) jsonObject.get("productNames");
            String[] productName = new String[productNames.size()];
            for (int i = 0; i < productNames.size(); i++) {
                productName[i] = (String) productNames.get(i);
            }
            JSONArray productPrices = (JSONArray) jsonObject.get("productPrices");
            double[] productPrice = new double[productPrices.size()];
            for (int i = 0; i < productPrices.size(); i++) {
                productPrice[i] = (long) productPrices.get(i);
            }
            JSONArray productCounts = (JSONArray) jsonObject.get("productCounts");
            long[] productCount = new long[productCounts.size()];
            for (int i = 0; i < productCounts.size(); i++) {
                productCount[i] = (long) productCounts.get(i);
            }
            return new Basket(productPrice, productName, productCount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}