package Flyweight;

import java.util.*;

class Flyweight {
    
    // Clase Flyweight para caracteres
    static class Caracter {
        private char simbolo; // Estado INTRINSECO (compartido)
        
        public Caracter(char simbolo) {
            this.simbolo = simbolo;
        }
        
        public void mostrar(int posicion, String color, String fuente) {
            // Estado EXTRINSECO (se pasa como parámetro)
            System.out.println("Caracter: '" + simbolo + 
                             "' | Posicion: " + posicion + 
                             " | Color: " + color + 
                             " | Fuente: " + fuente);
        }
    }
    
    // Factory para gestionar los flyweights
    static class FabricaCaracteres {
        private static Map<Character, Caracter> caracteres = new HashMap<>();
        
        public static Caracter getCaracter(char c) {
            if (!caracteres.containsKey(c)) {
                System.out.println("CREANDO caracter: '" + c + "'");
                caracteres.put(c, new Caracter(c));
            } else {
                System.out.println("REUTILIZANDO caracter: '" + c + "'");
            }
            return caracteres.get(c);
        }
        
        public static int getTotalCaracteres() {
            return caracteres.size();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("EJEMPLO FLYWEIGHT - PROCESADOR DE TEXTO\n");
        
        // Texto que queremos mostrar
        String texto = "HOLA MUNDO";
        
        System.out.println("Creando texto: \"" + texto + "\"");
        System.out.println("=".repeat(40));
        
        List<Object[]> documento = new ArrayList<>();
        Random random = new Random();
        
        // Crear cada caracter del documento
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            
            // Obtener el flyweight (caracter compartido)
            Caracter caracter = FabricaCaracteres.getCaracter(c);
            
            // Estado extrinseco unico para cada instancia
            String[] colores = {"Rojo", "Azul", "Verde", "Negro"};
            String[] fuentes = {"Arial", "Times", "Calibri"};
            
            String color = colores[random.nextInt(colores.length)];
            String fuente = fuentes[random.nextInt(fuentes.length)];
            int posicion = i * 10; // Simula posicion en pantalla
            
            // Guardar para mostrar despues
            documento.add(new Object[]{caracter, posicion, color, fuente});
        }
        
        System.out.println("\nMOSTRANDO DOCUMENTO:");
        System.out.println("=".repeat(40));
        
        // Mostrar el documento completo
        for (Object[] linea : documento) {
            Caracter caracter = (Caracter) linea[0];
            int posicion = (int) linea[1];
            String color = (String) linea[2];
            String fuente = (String) linea[3];
            
            caracter.mostrar(posicion, color, fuente);
        }
        
        // ESTADISTICAS
        System.out.println("\n" + "=".repeat(40));
        System.out.println("RESUMEN DEL AHORRO DE MEMORIA:");
        System.out.println("\n- Caracteres unicos en el texto: " + FabricaCaracteres.getTotalCaracteres());
        System.out.println("\n- Total de posiciones en el documento: " + texto.length());
        System.out.println("\n- MEMORIA AHORRADA: En lugar de " + texto.length() + " objetos,"+
        " solo tenemos " + FabricaCaracteres.getTotalCaracteres() + " objetos en memoria!");
      
        
        // EJEMPLO VISUAL
        System.out.println("\nEJEMPLO VISUAL PARA LA EXPOSICION:");
        System.out.println("\nTexto: H O L A   M U N D O");
        System.out.println("\nFlyweights: H O L A   M U N D O");
        System.out.println("            1 2 3 4   5 6 7 8 2 -> Posiciones");
        System.out.println("           Mismos objetos reutilizados");
    }
}