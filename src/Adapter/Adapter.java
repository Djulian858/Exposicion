package Adapter;


public class Adapter {

  
    // Esta es la interfaz que el cliente espera usar.
    interface EnchufeAmericano {
        void conectarEnchufeAmericano();
        double obtenerVoltaje();
    }

    
    // Esta clase representa el enchufe europeo,
    // que no es compatible con el sistema americano.
    class EnchufeEuropeo {
        public void conectarEnchufeEuropeo() {
            System.out.println("🔌 Conectando enchufe europeo (220V)");
        }

        public int getVoltajeEuropeo() {
            return 220;
        }
    }

    // Esta clase adapta el enchufe europeo para que funcione
    // como un enchufe americano.
    class AdaptadorEnchufe implements EnchufeAmericano {
        private EnchufeEuropeo enchufeEuropeo;

        // Constructor: recibe un enchufe europeo a adaptar
        public AdaptadorEnchufe(EnchufeEuropeo enchufeEuropeo) {
            this.enchufeEuropeo = enchufeEuropeo;
        }

        // Implementación del método americano,
        // que internamente llama al método europeo
        @Override
        public void conectarEnchufeAmericano() {
            System.out.println("🔧 Adaptador en acción: convirtiendo 220V a 120V...");
            enchufeEuropeo.conectarEnchufeEuropeo();
        }

        // Devuelve el voltaje "convertido" para simular compatibilidad
        @Override
        public double obtenerVoltaje() {
            int voltajeEuropeo = enchufeEuropeo.getVoltajeEuropeo();
            double voltajeConvertido = voltajeEuropeo * 0.545; // 220V → 120V aprox
            return voltajeConvertido;
        }
    }

   
    // El cliente trabaja solo con la interfaz americana.
    public static void main(String[] args) {
        Adapter demo = new Adapter();

        // Creamos un enchufe europeo
        EnchufeEuropeo enchufeEuropeo = demo.new EnchufeEuropeo();

        // Creamos un adaptador que lo hace compatible con el sistema americano
        EnchufeAmericano adaptador = demo.new AdaptadorEnchufe(enchufeEuropeo);

        // Usamos el adaptador como si fuera un enchufe americano
        adaptador.conectarEnchufeAmericano();

        // Mostramos el voltaje convertido
        double voltaje = adaptador.obtenerVoltaje();
        System.out.println("Voltaje convertido: " + voltaje + "V");

        // Fin de la demostración
        System.out.println("Adaptador funcionando correctamente.");
    }
}
