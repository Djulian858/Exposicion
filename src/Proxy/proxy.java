package Proxy;

// clase
public class proxy {
    
    // Interfaz común para Proxy y Objeto Real
    interface BaseDeDatos {
        void acceder(String usuario);
    }
    
    // Objeto Real - La base de datos sensible
    static class BaseDeDatosReal implements BaseDeDatos {
        private String datosSensibles;
        
        public BaseDeDatosReal() {
            this.datosSensibles = " \nDATOS SUPER SECRETOS: 123-456-789";
            System.out.println(" \nCARGANDO base de datos real ... (Operación Exitosa!!!)");
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
                System.out.println(" \nACCESO DENEGADO: " + usuario + " no tiene permisos");
                return;
            }
            
            // 2️⃣ CARGA PEREZOSA (Lazy Loading)
            if (baseDeDatosReal == null) {
                System.out.println(" \nProxy: Cargando base de datos por primera vez...");
                baseDeDatosReal = new BaseDeDatosReal();
            }
            
            // 3️⃣ REGISTRO/LOGGING
            System.out.println(" \nLOG: Acceso autorizado para " + usuario);
            
            // 4️⃣ Delegar al objeto real
            baseDeDatosReal.acceder(usuario);
        }
    }
    
    public static void main(String[] args) {
        System.out.println(" \nEJEMPLO PROXY - SISTEMA DE SEGURIDAD\n");
        
        // El cliente usa el proxy sin saber que hay un objeto real detrás
        BaseDeDatos sistema = new ProxySeguridad();
        
        System.out.println(" \nSIMULANDO INTENTOS DE ACCESO:\n");
        
        // Intento 1: Usuario sin permisos
        sistema.acceder("\nusuario");
        
        // Intento 2: Otro usuario sin permisos  
        sistema.acceder("\ninvitado");
        
        // Intento 3: Admin (debería funcionar)
        sistema.acceder("\nAdmin");
        
        // Intento 4: Admin nuevamente (ya está cargado)
        sistema.acceder("\nAdmin");
        
        // RESUMEN
        System.out.println("\n" + "=" .repeat(50));
        System.out.println(" \nRESUMEN DEL PATRÓN PROXY:");
        System.out.println("\n•  CONTROL DE ACCESO: Solo 'admin' puede entrar");
        System.out.println("\n•  CARGA PEREZOSA: Base de datos se carga solo cuando es necesario");
        System.out.println("\n•  REGISTRO/LOGGING: Se registran todos los intentos de acceso");
        System.out.println("\n•  TRANSPARENCIA: El cliente usa la misma interfaz");
    }
}
