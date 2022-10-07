import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] products = {"Хлеб", "Яблоки", "Молоко"};
        int[] prices = {100, 200, 300};
        Basket basket = new Basket(prices, products);

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

            basket.printCart();

            basket.saveTxt();
        }

    }
}