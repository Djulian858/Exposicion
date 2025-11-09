package Decorator;

public class Decorator {

    // Interfaz base
    interface Cafe {
        String descripcion();
        double costo();
    }

    // Clase concreta
    class CafeSimple implements Cafe {
        public String descripcion() {
            return "Café simple";
        }

        public double costo() {
            return 5.0;
        }
    }

    // Decorador base (abstracto)
    abstract class CafeDecorador implements Cafe {
        protected Cafe cafe;

        public CafeDecorador(Cafe c) {
            cafe = c;
        }
    }

    // Decorador concreto: leche
    class ConLeche extends CafeDecorador {
        public ConLeche(Cafe c) {
            super(c);
        }

        public String descripcion() {
            return cafe.descripcion() + ", con leche";
        }

        public double costo() {
            return cafe.costo() + 1.5;
        }
    }

    // Decorador concreto: chocolate
    class ConChocolate extends CafeDecorador {
        public ConChocolate(Cafe c) {
            super(c);
        }

        public String descripcion() {
            return cafe.descripcion() + ", con chocolate";
        }

        public double costo() {
            return cafe.costo() + 2.0;
        }
    }

    // Método principal
    public static void main(String[] args) {
        Decorator ejemplo = new Decorator();

        Cafe cafe = ejemplo.new ConChocolate(
                        ejemplo.new ConLeche(
                            ejemplo.new CafeSimple()
                        )
                    );

        System.out.println(cafe.descripcion() + " cuesta: $" + cafe.costo());
    }
}
