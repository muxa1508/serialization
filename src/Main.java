import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        File file = new File("src/basket.bin");
        Basket basket;

        if (!file.exists()) {
            String[] products = {"Хлеб", "Яблоки", "Молоко"};
            long[] prices = {100, 200, 300};
            basket = new Basket(prices, products);

        } else {
            basket = Basket.loadFromBinFile(file);
        }

        basket.printCart();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите товар и количество или введите 'end' ");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }
            String[] selectedProduct = input.split(" ");
            int productNumber = (Integer.parseInt(selectedProduct[0]) - 1);
            int productCount = Integer.parseInt(selectedProduct[1]);
            basket.addToCart(productNumber, productCount);

            basket.saveBin(file);

            basket.printCart();
        }
    }
}