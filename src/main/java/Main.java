import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, ParseException {

        SettingsChecker settings = new SettingsChecker();
        settings.settingscheck();
        Basket basket;

        if (settings.loadEnabled.equals("true")) {
            if (settings.loadFormat.equals("json")) {
                basket = Basket.loadFromJsonFile(new File(settings.loadFilename));
            } else {
                basket = Basket.loadFromTxtFile(new File(settings.loadFilename));
            }
        } else {
            String[] products = {"Хлеб", "Яблоки", "Молоко"};
            double[] prices = {100, 200, 300};
            basket = new Basket(prices, products);
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

            if (settings.saveEnabled.equals("true")) {
                if (settings.saveFormat.equals("json")) {
                    basket.saveJson(new File(settings.saveFilename));
                } else {
                    basket.saveTxt(new File(settings.saveFilename));
                }
            }

            basket.printCart();
            log.printlog();

        }

        if (settings.logEnabled.equals("true")) {
            log.exportAsCSV(new File(settings.logFilename));
        }
    }
}