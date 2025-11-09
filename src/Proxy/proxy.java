package Proxy;

// Proxy.java
public class proxy {
    
    // Interfaz común para Proxy y Objeto Real
    interface BaseDeDatos {
        void acceder(String usuario);
    }
    
    // Objeto Real - La base de datos sensible
    static class BaseDeDatosReal implements BaseDeDatos {
        private String datosSensibles;
        
        public BaseDeDatosReal() {
            this.datosSensibles = " DATOS SUPER SECRETOS: 123-456-789";
            System.out.println(" CARGANDO base de datos real... (Operación Exitosa!!!)");
        }
        
        @Override
        public void acceder(String usuario) {
            System.out.println( usuario + " accedió a: " + datosSensibles);
        }
    }
    
    // Proxy - Controla el acceso a la base de datos
    static class ProxySeguridad implements BaseDeDatos {
        private BaseDeDatosReal baseDeDatosReal;
        private int intentosAcceso = 0;
        
        @Override
        public void acceder(String usuario) {
            intentosAcceso++;
            System.out.println("\n--- Intento de acceso #" + intentosAcceso + " ---");
            
            // 1️⃣ CONTROL DE ACCESO
            if (!usuario.equals("admin")) {
                System.out.println(" ACCESO DENEGADO: " + usuario + " no tiene permisos");
                return;
            }
            
            // 2️⃣ CARGA PEREZOSA (Lazy Loading)
            if (baseDeDatosReal == null) {
                System.out.println(" Proxy: Cargando base de datos por primera vez...");
                baseDeDatosReal = new BaseDeDatosReal();
            }
            
            // 3️⃣ REGISTRO/LOGGING
            System.out.println(" LOG: Acceso autorizado para " + usuario);
            
            // 4️⃣ Delegar al objeto real
            baseDeDatosReal.acceder(usuario);
        }
    }
    
    public static void main(String[] args) {
        System.out.println(" EJEMPLO PROXY - SISTEMA DE SEGURIDAD\n");
        
        // El cliente usa el proxy sin saber que hay un objeto real detrás
        BaseDeDatos sistema = new ProxySeguridad();
        
        System.out.println(" SIMULANDO INTENTOS DE ACCESO:\n");
        
        // Intento 1: Usuario sin permisos
        sistema.acceder("usuario");
        
        // Intento 2: Otro usuario sin permisos  
        sistema.acceder("invitado");
        
        // Intento 3: Admin (debería funcionar)
        sistema.acceder("admin");
        
        // Intento 4: Admin nuevamente (ya está cargado)
        sistema.acceder("admin");
        
        // RESUMEN
        System.out.println("\n" + "=" .repeat(50));
        System.out.println(" RESUMEN DEL PATRÓN PROXY:");
        System.out.println("•  CONTROL DE ACCESO: Solo 'admin' puede entrar");
        System.out.println("•  CARGA PEREZOSA: Base de datos se carga solo cuando es necesario");
        System.out.println("•  REGISTRO/LOGGING: Se registran todos los intentos de acceso");
        System.out.println("•  TRANSPARENCIA: El cliente usa la misma interfaz");
    }
}
