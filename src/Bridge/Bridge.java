package Bridge;

public class Bridge {
    // Implementación
    interface Dispositivo {
        void encender();

        void apagar();
    }

    class Televisor implements Dispositivo {
        public void encender() {
            System.out.println("Televisor encendido");
        }

        public void apagar() {
            System.out.println("Televisor apagado");
        }
    }

    class Radio implements Dispositivo {
        public void encender() {
            System.out.println("Radio encendida");
        }

        public void apagar() {
            System.out.println("Radio apagada");
        }
    }

    // Abstracción
    abstract class ControlRemoto {
        protected Dispositivo dispositivo;

        public ControlRemoto(Dispositivo d) {
            dispositivo = d;
        }

        abstract void encender();

        abstract void apagar();
    }

    class ControlBasico extends ControlRemoto {
        public ControlBasico(Dispositivo d) {
            super(d);
        }

        void encender() {
            dispositivo.encender();
        }

        void apagar() {
            dispositivo.apagar();
        }
    }

//uso
    public static void main(String[] args) {
        Dispositivo tv = new Bridge().new Televisor();
        ControlRemoto controlTv = new Bridge().new ControlBasico(tv);

        controlTv.encender();
        controlTv.apagar();

        Dispositivo radio = new Bridge().new Radio();
        ControlRemoto controlRadio = new Bridge().new ControlBasico(radio);

        controlRadio.encender();
        controlRadio.apagar();
    }

}
