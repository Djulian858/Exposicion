package Adapter;
public class Adapter {
    // Interfaz esperada

    public interface EnchufeAmericano {
        void conectarEnchufeAmericano();

    }

    // Clase incompatible
    public class EnchufeEuropeo {
        public void conectarEnchufeEuropeo() {
            System.out.println("Conectando enchufe europeo");
        }
    }

    // Adaptador
    public class AdaptadorEnchufe implements EnchufeAmericano {
        private EnchufeEuropeo enchufeEuropeo;

        public AdaptadorEnchufe(EnchufeEuropeo enchufeEuropeo) {
            this.enchufeEuropeo = enchufeEuropeo;
        }

        @Override
        public void conectarEnchufeAmericano() {
            enchufeEuropeo.conectarEnchufeEuropeo();
        }
    }    public static void main(String[] args) {
        EnchufeEuropeo enchufeEuropeo = new Adapter().new EnchufeEuropeo();
        EnchufeAmericano adaptador = new Adapter().new AdaptadorEnchufe(enchufeEuropeo);

        adaptador.conectarEnchufeAmericano();
    } 
    
}