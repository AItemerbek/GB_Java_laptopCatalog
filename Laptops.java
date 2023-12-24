import java.util.Random;

public class Laptops {
    public String brand;
    public int diagonal;
    public int ram;
    public int ssd;
    public double cpuSpeed;
    public int graphics;

    public Laptops() {
        Random random = new Random();
        this.brand = Brands.values()[new Random().nextInt(Brands.values().length - 1)].toString().toUpperCase();
        this.diagonal = 13 + 2 * random.nextInt(0,3);
        this.cpuSpeed = random.nextDouble(2.0,4.0);
        this.graphics = (int)Math.pow(2.0,random.nextInt(3,6));
        this.ram = (int)Math.pow(2.0,random.nextInt(3,7));
        this.ssd = (int)Math.pow(2.0,random.nextInt(6,11));
    }

    @Override
    public String toString() {
        return (brand + " " + diagonal + ": "
                + "Chipset= " + String.format("%.2f",cpuSpeed) + "GHz, "
                + "Graphics= " + graphics + "Gb, "
                + "RAM= " + ram + "Gb, "
                + "ssd= " + ssd + "Gb" + "\n"
                );
    }

    public Object getAtribute(int atribute){
        switch (atribute){
            case 1:
                return this.brand;
            case 2:
                return this.diagonal;
            case 3:
                return this.cpuSpeed;
            case 4:
                return this.graphics;
            case 5:
                return this.ram;
            case 6:
                return this.ssd;
            default: return null;
        }
    }

}
