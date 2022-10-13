import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {

        File textFile = new File("src/basket.json");
        File txtFile = new File("src/log.csv");
        Basket basket;

        if (!textFile.exists()) {
            String[] products = {"Хлеб", "Яблоки", "Молоко"};
            long[] prices = {100, 200, 300};
            basket = new Basket(prices, products);

        } else {
            basket = Basket.loadFromTxtFile(textFile);
        }

        ClientLog log = new ClientLog();
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

            log.log((productNumber + 1), productCount);

//            basket.saveTxt(textFile);

            basket.printCart();
        }
//        log.printlog();
        log.exportAsCSV(txtFile);

    }
}