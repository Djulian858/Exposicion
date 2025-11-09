package Composite;

import java.util.*;

public class Composite {

    interface Componente {
        void mostrar();
    }

    // ---------- Clase hoja ----------
    static class Archivo implements Componente {
        private String nombre;

        public Archivo(String n) { nombre = n; }

        public void mostrar() {
            System.out.println("Archivo: " + nombre);
        }
    }

    // ---------- Clase compuesta ----------
    static class Carpeta implements Componente {
        private String nombre;
        private List<Componente> elementos = new ArrayList<>();

        public Carpeta(String n) { nombre = n; }

        public void agregar(Componente c) { elementos.add(c); }

        public void mostrar() {
            System.out.println("Carpeta: " + nombre);
            for (Componente c : elementos) {
                c.mostrar();
            }
        }
    }

    // ---------- Clase principal ----------
    public static class CompositeEjemplo {
        public static void main(String[] args) {
            Carpeta root = new Carpeta("Documentos");
            root.agregar(new Archivo("CV.pdf"));

            Carpeta fotos = new Carpeta("Fotos");
            fotos.agregar(new Archivo("vacaciones.jpg"));

            root.agregar(fotos);
            root.mostrar();
        }
    }
}
