import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {

    public static void main(String[] args) throws UnirestException, IOException, ParserConfigurationException {
        while (true) {
            BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

            String filepath = "/Users/anastasiaanufrieva/IdeaProjects/Project5/src/main/java/currencies";
            System.out.println("Enter the date in the format dd/mm/yyyy: ");
            System.out.println("To exit the program, enter \"exit\"");

            String date = read.readLine();
            if (isValidDate(date)) {
                String res = Unirest.get("https://www.cbr.ru/scripts/XML_daily.asp?date_req={date}").routeParam("date", date).asString().getBody();
                res = res.replace("windows-1251", "utf-8");

                FileWriter writer = new FileWriter("/Users/anastasiaanufrieva/IdeaProjects/Project5/src/main/java/currencies");
                writer.write(res);
                writer.flush();
                writer.close();

                Reader reader = new Reader();
                List<Currency> currencies = reader.readFile(filepath);

                System.out.println("Enter the currency code: ");
                System.out.println("To exit the program, enter \"exit\"");
                String charCode = read.readLine().toUpperCase(Locale.ROOT);

                if (Currency.getCharCodes().contains(charCode)) {
                    for (Currency currency : currencies) {
                        if (currency.getCharCode().equals(charCode)) {
                            System.out.println(currency);
                        }
                    }
                } else if (charCode.equals("exit")) {
                    break;
                } else  {
                    System.out.println("INCORRECT CURRENCY CODE ENTERED");
                }

            } else if (date.equals("exit")) {
                break;
            } else  {
                System.out.println("INCORRECT DATE FORMAT ENTERED");
                System.out.println("The date must be entered in the format dd/mm/yyyy");
            }


        }
    }

    public static boolean isValidDate(String inputValue) {
        boolean result = true;

        if (inputValue == null) {
            result = false;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            try {

                Date date = sdf.parse(inputValue);
                System.out.println(date);

            } catch (ParseException e) {

                e.printStackTrace();
                result = false;
            }
        }

        return result;
    }




}









