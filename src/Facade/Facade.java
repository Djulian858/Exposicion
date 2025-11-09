package Facade;

public class Facade {

    // Subsistemas
    class CPU {
        void iniciar() { 
            System.out.println("CPU iniciada"); 
        }
    }

    class Memoria {
        void cargar() { 
            System.out.println("Memoria cargada"); 
        }
    }

    class DiscoDuro {
        void leer() { 
            System.out.println("Leyendo disco duro"); 
        }
    }

    // Fachada
    class ComputadoraFacade {
        private CPU cpu;
        private Memoria memoria;
        private DiscoDuro disco;

        public ComputadoraFacade() {
            cpu = new CPU();
            memoria = new Memoria();
            disco = new DiscoDuro();
        }

        void encender() {
            cpu.iniciar();
            memoria.cargar();
            disco.leer();
            System.out.println("Computadora lista");
        }
    }

    // Clase principal
    public static void main(String[] args) {
        Facade ejemplo = new Facade();
        ComputadoraFacade pc = ejemplo.new ComputadoraFacade();
        pc.encender();
    }
}

