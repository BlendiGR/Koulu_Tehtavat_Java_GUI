package SevenPointTwo.entity;

public class Currency {
    private final String code;
    private final String name;
    private final double usdPerUnit;

    public Currency(String code, String name, double usdPerUnit) {
        this.code = code;
        this.name = name;
        this.usdPerUnit = usdPerUnit;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getUsdPerUnit() { return usdPerUnit; }

    @Override
    public String toString() {
        return code + " — " + name;
    }
}
