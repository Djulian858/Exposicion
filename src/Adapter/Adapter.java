package Adapter;
public class Adapter {
    // Interfaz esperada

    public interface EnchufeEuropeo {
        void conectarEnchufeEuropeo();

    }

    // Clase incompatible
    public class EnchufeAmericano {
        public void conectarEnchufeAmericano() {
            System.out.println("Conectando enchufe americano");
        }
    }

    // Adaptador
    public class EnchufeAdapter implements EnchufeEuropeo {
        private EnchufeAmericano enchufeAmericano;

        public EnchufeAdapter(EnchufeAmericano enchufeAmericano) {
            this.enchufeAmericano = enchufeAmericano;
        }

        @Override
        public void conectarEnchufeEuropeo() {
            enchufeAmericano.conectarEnchufeAmericano();
        }
    }

     public static void main(String[] args) {
        // Crear una instancia del enchufe americano
        Adapter.EnchufeAmericano enchufeAmericano = new Adapter().new EnchufeAmericano();

        // Crear el adaptador para que el enchufe americano pueda ser usado como europeo
        Adapter.EnchufeEuropeo enchufeEuropeo = new Adapter().new EnchufeAdapter(enchufeAmericano);

        // Usar el enchufe europeo (que en realidad es un enchufe americano adaptado)
        enchufeEuropeo.conectarEnchufeEuropeo();
    }
}




