import java.util.ArrayList;
import java.util.List;

public class Currency {
    private static List<String> charCodes = new ArrayList<>();
    private String numCode;
    private String charCode;
    private int nominal;
    private String name;
    private double value;

    public Currency(String numCode, String charCode, int nominal, String name, double value) {
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;

    }

    public Currency() {

    }


    @Override
    public String toString() {
        String result = "";
        result += this.nominal + " " + this.name + " = " + this.value + " Russian rubles." + "\n";
        return result;
    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public static List<String> getCharCodes() {
        return charCodes;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
        charCodes.add(charCode);

    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value =  value;
    }
}