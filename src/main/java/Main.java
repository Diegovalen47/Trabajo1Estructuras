import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import com.google.gson.*;


public class Main {


    public static Scanner input = new Scanner(System.in);
    public static LinkedList<Usuario> usuarios = new LinkedList<>();
    public static LinkedList<EmpresaDeBasura> Empresas= new LinkedList<>();
    public static Gson gson = new Gson();


    public static void CargarUsuarios() {
        JsonParser parser = new JsonParser();

        try(FileReader reader = new FileReader("usuarios.json")) {
            Object obj = parser.parse(reader);
            JsonArray userList = (JsonArray) obj;

            for (Object object : userList) {
                String jsonString = gson.toJson(object);
                Usuario usuario = gson.fromJson(jsonString, Usuario.class);
                usuarios.add(usuario);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        CargarUsuarios();
        /*  El siguiente ciclo while tiene la funcionalidad de mostrar el menú principal
        en donde el usuario tendrá las opciones de iniciar sesión y registrarse   */
        String option = "";
        while(true) {
            System.out.println("***********************************************");
            System.out.println("Bienvenido al sistema de gestión de información");
            System.out.println("de empresas recolectoras de basura.");
            System.out.println("***********************************************");
            System.out.println("Elija una opción :");
            System.out.println("1. Iniciar sesión.");
            System.out.println("2. Registrarse.");
            System.out.println("0. Salir.");
            option = input.next();
            if(option.equals("1")) {
                IniciarSesion();
            } else if (option.equals("2")) {
                Registrarse();
            } else if (option.equals("0")) {
                GuardarUsuarios();
                break;
            }
        }
    }


    public static void IniciarSesion() {

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados al momento");
            return;
        }
        System.out.println("Iniciar sesion");
        System.out.println("******************************************************");
        System.out.print("Ingrese su correo electronico o documento de identidad : ");
        String option = input.next();
        String clave;

        if (option.contains("@")) {   // Se busca por correo electrónico
            boolean encontrado = false;
            for (Usuario usuario : usuarios) {
                if (usuario.correo.equals(option)) {
                    encontrado = true;           //Verificador de usuario encontrado
                    System.out.print("Ingrese su contraseña : ");
                    clave = input.next();
                    while(!usuario.clave.equals(clave)) {
                        System.out.println("Contraseña incorrecta, intente nuevamente.");
                        System.out.print("Ingrese su contraseña : ");
                        clave = input.next();
                    }
                    System.out.println("Inicio de sesión exitoso");
                    MenuPrincipal(usuario);
                }
            }
            if (!encontrado) {           // Si no se encuentra el usuario
                System.out.println("El usuario ingresado no se encuentra en el sistema");
                System.out.println("¿Desea registrarse?");
                System.out.println("Y");
                System.out.println("N");
                String seleccion = input.next();
                if (seleccion.equalsIgnoreCase("Y")) {
                    Registrarse();
                } else if (seleccion.equalsIgnoreCase("N")) {
                    IniciarSesion();
                }
            }
        } else {       // Se busca por documento de identidad
            int documento_identidad = Integer.parseInt(option);
            boolean encontrado = false;
            for (Usuario usuario : usuarios) {
                if (usuario.cedula == documento_identidad) {
                    encontrado = true;               //Verificador de usuario encontrado
                    System.out.print("Ingrese su contraseña : ");
                    clave = input.next();
                    while(!usuario.clave.equals(clave)) {
                        System.out.println("Contraseña incorrecta, intente nuevamente.");
                        System.out.print("Ingrese su contraseña : ");
                        clave = input.next();
                    }
                    System.out.println("Inicio de sesión exitoso");
                    MenuPrincipal(usuario);
                }
            }
            if (!encontrado) {              // Si no se encuentra el usuario
                System.out.println("El usuario ingresado no se encuentra en el sistema");
                System.out.println("¿Desea registrarse?");
                System.out.println("Y");
                System.out.println("N");
                String seleccion = input.next();
                if (seleccion.equalsIgnoreCase("Y")) {
                    Registrarse();
                } else if (seleccion.equalsIgnoreCase("N")) {
                    IniciarSesion();
                }
            }
        }
    }


    public static void Registrarse() {

        System.out.println("Registrarse");
        System.out.println("**********************************");
        System.out.println("Ingrese sus datos personales");
        System.out.print("Nombre : ");
        String nombre = input.next();
        System.out.print("Apellido : ");
        String apellido = input.next();
        System.out.print("Correo electrónico : ");
        String correo = input.next();

        boolean correo_existente = true;
        while (correo_existente) {         // Verificacion que el correo no está registrado
            correo_existente = false;
            for (Usuario usuario : usuarios) {

                if (usuario.correo.equals(correo)) {
                    correo_existente = true;
                    System.out.println("Ya existe un usuario registrado con ese correo");
                    System.out.println("Intente nuevamente");
                    System.out.print("Correo electrónico : ");
                    correo = input.next();
                    break;
                }
            }

        }

        System.out.print("Documento de identidad : ");
        int documento_identidad = input.nextInt();

        boolean documento_existente = true;
        while (documento_existente) {         // Verificacion que el documento de identidad no está registrado
            documento_existente = false;
            for (Usuario usuario : usuarios) {

                if (usuario.cedula == documento_identidad) {
                    documento_existente = true;
                    System.out.println("Ya existe un usuario registrado con ese documento");
                    System.out.println("Intente nuevamente");
                    System.out.print("Documento de identidad : ");
                    documento_identidad = input.nextInt();
                    break;
                }
            }

        }

        System.out.print("Contraseña : ");
        String clave = input.next();

        Usuario usuario = new Usuario(documento_identidad, nombre, apellido, correo, clave, null);
        usuarios.add(usuario);

        System.out.println("¡Usted se ha registrado exitosamente!");
        IniciarSesion();
    }


    public static void GuardarUsuarios() {
        String jsonString = gson.toJson(usuarios);

        try (FileWriter file = new FileWriter("usuarios.json")) {

            file.write(jsonString);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void MenuPrincipal(Usuario usuario) {
        System.out.println("*********************************");
        System.out.println("¡Hola "+ usuario.nombre +"!");
        String option = "";
        while(true) {
            System.out.println("Bienvenido al menú principal");
            System.out.println("Elija una opción");
            System.out.println("1. Administración de información");
            System.out.println("2. Búsqueda de información");
            System.out.println("3. Dignóstico de inconsistencias");
            System.out.println("1. Guardar");
            System.out.println("0. Salir y cancelar");
            option = input.next();
            if (option.equals("1")) {
<<<<<<< HEAD
                AdministracionInformacion();
            } else if (option.equals("2")) {
                BusquedaInformacion();
=======
                CrearEmpresa();
            } else if (option.equals("2")) {
                ConsultarEmpresa();
>>>>>>> 5c8f46fab8002c6602dd3b84c91edaf0e7794e08
            } else if (option.equals("3")) {
                //DiagnosticoInconsistencias();
            } else if (option.equals("4")) {
                //Guardar();
            } else if (option.equals("0")) {
                System.out.println("Se perderán los cambios sin guardar, ¿Desea salir?");
                System.out.println("Y");
                System.out.println("N");
                option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    break;
                }
            }
        }
    }


    public static void AdministracionInformacion() {
        String option = "";
        while(true) {
            System.out.println("*****************************************************************");
            System.out.println("Seleccione el elemento por el cual desea administrar la información");
            System.out.println("1. Empresas de basura");
            System.out.println("2. Sedes");
            System.out.println("3. Áreas");
            System.out.println("4. Rutas");
            System.out.println("5. Talleres");
            System.out.println("6. Personal");
            System.out.println("7. Recolectores");
            System.out.println("0. Cancelar");
            option = input.next();
            if (option.equals("1")) {
                //MenuEmpresasDeBasura();
            } else if (option.equals("2")) {
                //MenúSedes();
            } else if (option.equals("3")) {
                //MenuAreas();
            } else if (option.equals("4")) {
                //MenuRutas();
            } else if (option.equals("5")) {
                //MenuTalleres();
            } else if (option.equals("6")) {
                //MenuPersonal();
            } else if (option.equals("7")) {
                //MenuRecolectores
            } else if (option.equals("0")) {
                break;
            }
        }
    }


    public static void BusquedaInformacion() {
        String option = "";
        while(true) {
            System.out.println("*****************************************************************");
            System.out.println("Seleccione el elemento por el cual desea buscar la información");
            System.out.println("1. Empresas de basura");
            System.out.println("2. Sedes");
            System.out.println("3. Áreas");
            System.out.println("4. Rutas");
            System.out.println("5. Talleres");
            System.out.println("6. Personal");
            System.out.println("7. Recolectores");
            System.out.println("0. Cancelar");
            option = input.next();
            if (option.equals("1")) {
                //MenuEmpresasDeBasura();
            } else if (option.equals("2")) {
                //MenúSedes();
            } else if (option.equals("3")) {
                //MenuAreas();
            } else if (option.equals("4")) {
                //MenuRutas();
            } else if (option.equals("5")) {
                //MenuTalleres();
            } else if (option.equals("6")) {
                //MenuPersonal();
            } else if (option.equals("7")) {
                //MenuRecolectores
            } else if (option.equals("0")) {
                break;
            }
        }
    }



    public static void CrearEmpresa(){
        boolean registrado= false;
        String nombre;
        while(!registrado){         // Aquí se valida si el nombre de la empresa a ingresar ya existe
            registrado=true;
            System.out.print("Ingrese el nombre de la nueva empresa de basura: ");
            input.nextLine();
            nombre= input.nextLine();
            for(EmpresaDeBasura empresa: Empresas){
                if(empresa.nombre.equals(nombre)){
                    registrado=false;
                    System.out.println("El nombre seleccionado ya está registrado");
                    break;
                }
            }
        }

        String ciudad;
        boolean cuidad_regiatrada= false;
        while(!cuidad_regiatrada){         // Aquí se valida si la cuidad ya posee empresa de basura
            cuidad_regiatrada=true;
            System.out.print("Ingrese la cuidad donde se establecerá la empresa: ");
            ciudad = input.nextLine();
            for(EmpresaDeBasura empresa: Empresas){
                if(empresa.ciudad.equals(ciudad)){
                    cuidad_regiatrada  =false;
                    System.out.println("La cuidad seleccionada ya posee empresa de basura");
                    break;
                }
            }
        }

        String gerente;
        boolean gerente_regiatrado= false;
        while(!gerente_regiatrado){         // Aquí se valida si el gerente ya está en otra empresa de basura
            gerente_regiatrado=true;
            System.out.print("Ingrese el nombre del gerente de la nueva empresa de basura: ");
            input.nextLine();
            gerente= input.nextLine();
            for(EmpresaDeBasura empresa: Empresas){
                if(empresa.gerente.equals(gerente)){
                    gerente_regiatrado=false;
                    System.out.println("La cuidad seleccionada ya posee empresa de basura");
                    break;
                }
            }
        }

<<<<<<< HEAD
        /*Empresas.add(new EmpresaDeBasura(nombre,cuidad,gerente));*/
=======
        Empresas.add(new EmpresaDeBasura(nombre,cuidad,gerente));
        System.out.println("Se ha ingresado exitosamente la empresa de basura");
>>>>>>> 5c8f46fab8002c6602dd3b84c91edaf0e7794e08

        System.out.println("¿Desea crear sedes para la empresa de basura?");
        System.out.println("Y");
        System.out.println("N");
        String option= input.next();

        if(option.equals("Y")){
            
            CrearSedes();  // Esta funcion no ha sido creada (tener esto presente)

        }else{
            return;
        }


    }
    
    public static void ConsultarEmpresa() {
        if(Empresas.size()==0){
            System.out.println("No hay empresas registradas");
            System.out.println("¿Desea registrar una empresa de basura?");
            System.out.println("Y");
            System.out.println("N");
            String option= input.next();

            if(option.equals("Y")){
                CrearEmpresa();
            }
            return;

        }else{

            System.out.println("Las empresas registradas son: ");

            for (EmpresaDeBasura empresaDeBasura : Empresas) {
                System.out.println(empresaDeBasura.nombre);
            }

            boolean nombre_correcto= false;

            while(!nombre_correcto){

                System.out.println();
                System.out.print("Ingrese el nombre de la empresa a consultar: ");
                input.nextLine();
                option=input.nextLine();

                for (EmpresaDeBasura empresaDeBasura : Empresas) {
                    if(empresaDeBasura.nombre.equals(option)){
                        MenuPrincipalEmpresa(empresaDeBasura); // En esta función se crean las sedes y se consulta en general a la empresa
                        return;
                    }
                }

                System.out.println("El nombre ingresado es incorrecto");
                System.out.println("¿Desea intertarlo de nuevo?");
                System.out.println("Y");
                System.out.println("N");
                String option_2= input.next();

                if(option_2.equals("N")){
                    return;
                }
            }
            
            

        }



    }

    public static void MenuPrincipalEmpresa(EmpresaDeBasura Empresa) {
        
        System.out.println("*********************************");
        System.out.println("Bienvenido a la empresa:  "+ usuario.nombre);
        String option = "";
        while(true) {
            System.out.println("Bienvenido al menú principal");
            System.out.println("Elija una opción");
            System.out.println("1. Consultar datos generales de la empresa");
            System.out.println("2. Crear una nueva Sede");
            System.out.println("3. Consultar Sede");
            System.out.println("0. Salir y cancelar");
            option = input.next();
            if (option.equals("1")) {
                System.out.println(Empresa);
            } else if (option.equals("2")) {
                //CrearSede();
            } else if (option.equals("3")) {
                //ConsultarSede();
            } else if (option.equals("4")) {
                //Guardar();
            } else if (option.equals("0")) {
                System.out.println("Se perderán los cambios sin guardar, ¿Desea salir?");
                System.out.println("Y");
                System.out.println("N");
                option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    break;
                }
            }
        }
    }


}
