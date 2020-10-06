import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.google.gson.*;

public class Main {


    public static Scanner input = new Scanner(System.in);
    public static LinkedList<Usuario> usuarios = new LinkedList<>();
    public static LinkedList<EmpresaDeBasura> empresasDeBasuras = new LinkedList<>();
    public static LinkedList<Area> areas = new LinkedList<>();
    public static LinkedList<Personal> personas = new LinkedList<>();
    public static LinkedList<Recolector> recolectores = new LinkedList<>();
    public static LinkedList<Ruta> rutas = new LinkedList<>();
    public static LinkedList<Taller> talleres = new LinkedList<>();
    public static LinkedList<Sede> sedes = new LinkedList<>();
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

        try(FileReader reader = new FileReader("empresasDeBasuras.json")) {
            Object obj = parser.parse(reader);
            JsonArray empresasList = (JsonArray) obj;

            for (Object object : empresasList) {
                String jsonString = gson.toJson(object);
                EmpresaDeBasura empresa = gson.fromJson(jsonString, EmpresaDeBasura.class);
                empresasDeBasuras.add(empresa);
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

        Usuario usuario = new Usuario(documento_identidad, nombre, apellido, correo, clave);
        usuarios.add(usuario);

        System.out.println("¡Usted se ha registrado exitosamente!");
        GuardarUsuarios();
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


    public static void GuardarObjetos() {
        String jsonString = gson.toJson(empresasDeBasuras);

        try (FileWriter file = new FileWriter("empresasDeBasuras.json")) {

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
            System.out.println("1. Adminstración");
            System.out.println("2. Búsqueda");
            System.out.println("3. Dignóstico de inconsistencias");
            System.out.println("4. Guardar");
            System.out.println("0. Salir y cancelar");
            option = input.next();
            if (option.equals("1")) {
                MenuAdministracion();
            } else if (option.equals("2")) {
                MenuBusqueda();
            } else if (option.equals("3")) {
                //DiagnosticoInconsistencias();
            } else if (option.equals("4")) {
                GuardarObjetos();
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


    public static void MenuAdministracion() {
        String option = "";
        while(true) {
            System.out.println("MENU ADMINISTRACION");
            System.out.println("Elija la opción para administrar la información");
            System.out.println("1. Empresas de Basura");
            System.out.println("2. Sedes");
            System.out.println("3. Áreas");
            System.out.println("4. Rutas");
            System.out.println("5. Talleres");
            System.out.println("6. Personal");
            System.out.println("7. Recolector");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                AdministrarEmpresasBasura();
            } else if (option.equals("2")) {
                AdministrarSedes();
            } else if (option.equals("3")) {
                AdministrarAreas();
            } else if (option.equals("4")) {
                AdministrarRutas();
            } else if (option.equals("5")) {
                AdministrarTalleres();
            } else if (option.equals("6")) {
                AdministrarPersonal();
            } else if (option.equals("7")) {
                AdministrarRecolectores();
            } else if (option.equals("0")) {
                break;
            }
        }
    }


    public static void AdministrarEmpresasBasura() {
        String option = "";
        while(true) {
            System.out.println("Elija una opción");
            System.out.println("1. Ver Empresas de basura");
            System.out.println("2. Crear empresa de basura");
            System.out.println("3. Editar empresa de basura");
            System.out.println("4. Eliminar empresa de basura");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                for (EmpresaDeBasura empresaDeBasura : empresasDeBasuras) {
                    System.out.println("************************************");
                    System.out.println("Nombre: "+empresaDeBasura.nombre);
                    System.out.println("Ciudad: "+empresaDeBasura.ciudad);
                    System.out.println("Gerente: "+empresaDeBasura.gerente);
                    System.out.println("************************************");
                }
            } else if (option.equals("2")) {
                CrearEmpresasBasura();
            } else if (option.equals("3")) {
                EditarEmpresasBasura();
            } else if (option.equals("4")) {
                EliminarEmpresasBasura();
            } else if (option.equals("0")) {
                break;
            }
        }
    }


    public static void CrearEmpresasBasura() {
        System.out.println("CREANDO EMPRESA DE BASURA");
        System.out.println("Ingrese el nombre que desea darle a la empresa");
        String nombre = input.next();
        String ciudad = "";
        String gerente = "";
        for (EmpresaDeBasura empresa : empresasDeBasuras) {
            if (empresa.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Ya existe una empresa con ese nombre");
                return;
            }
        }
        System.out.println("Ingrese la ciudad donde se encuentra la empresa");
        ciudad = input.next();
        System.out.println("Ingrese el nombre del gerente de la empresa");
        gerente = input.next();
        empresasDeBasuras.add(new EmpresaDeBasura(nombre,ciudad,gerente));
        System.out.println("Empresa creada satisfactoriamente");
    }


    public static void EditarEmpresasBasura() {
        System.out.println("Se seleccionará por nombre de empresa");
        System.out.println("Ingrese el nombre de la empresa");
        String nombre = input.next();
        String nuevo_nombre = "";
        String nuevo_gerente = "";
        String nueva_ciudad = "";
        boolean empresaEncontrada = false;
        for (EmpresaDeBasura empresa : empresasDeBasuras) {
            if (empresa.getNombre().equalsIgnoreCase(nombre)) {
                empresaEncontrada = true;
                System.out.println("Nombre: "+ empresa.getNombre());
                System.out.println("Si desea cambiar el nombre, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_nombre = input.next();
                System.out.println("Ciudad: "+ empresa.getCiudad());
                System.out.println("Si desea cambiar la ciudad, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nueva_ciudad = input.next();
                System.out.println("Gerente: "+ empresa.getGerente());
                System.out.println("Si desea cambiar el gerente, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_gerente = input.next();
                System.out.println("¿Desea guardar los cambios?");
                System.out.println("Y");
                System.out.println("N");
                String option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    if (nuevo_nombre.equalsIgnoreCase("N")) {

                    } else {
                        empresa.setNombre(nuevo_nombre);
                    }
                    if (nueva_ciudad.equalsIgnoreCase("N")) {

                    } else {
                        empresa.setCiudad(nueva_ciudad);
                    }
                    if (nuevo_gerente.equalsIgnoreCase("N")) {

                    } else {
                        empresa.setGerente(nuevo_gerente);
                    }
                } else {

                }
            }
        }
        if (!empresaEncontrada) {
            System.out.println("No se encntró la empresa");
        }
    }


    public static void EliminarEmpresasBasura() {
        System.out.println("Se seleccionará por nombre de empresa");
        System.out.println("Ingrese el nombre de la empresa a eliminar");
        String nombre = input.next();
        System.out.println("¿Seguro que desea eliminar esta empresa");
        System.out.println("Y");
        System.out.println("N");
        String option = input.next();
        boolean empresaEncontrada = false;
        if (option.equalsIgnoreCase("Y")) {
            Iterator<EmpresaDeBasura> iterator = empresasDeBasuras.iterator();
            while(iterator.hasNext()) {
                EmpresaDeBasura empresa = iterator.next();
                if (empresa.getNombre().equals(nombre)) {
                    empresaEncontrada = true;
                    iterator.remove();
                }
            }

            if (!empresaEncontrada) {
                System.out.println("No se encontró la empresa");
            }
        }

    }


    public static void AdministrarSedes() {
        String option = "";
        while(true) {
            System.out.println("Elija una opción");
            System.out.println("1. Ver Sedes");
            System.out.println("2. Crear Sedes");
            System.out.println("3. Editar Sedes");
            System.out.println("4. Eliminar Sedes");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                for (Sede sede : sedes) {
                    System.out.println("************************************");
                    System.out.println("Telefono: "+sede.telefono);
                    System.out.println("Direccion: "+sede.direccion);
                    System.out.println("Persona a cargo: "+sede.persona_a_cargo);
                    System.out.println("************************************");
                }
            } else if (option.equals("2")) {
                //CrearSedes();
            } else if (option.equals("3")) {
                //EditarSedes();
            } else if (option.equals("4")) {
                //EliminarSedes();
            } else if (option.equals("0")) {
                break;
            }
        }
    }


    public static void AdministrarAreas() {
        String option = "";
        while(true) {
            System.out.println("Elija una opción");
            System.out.println("1. Ver Areas");
            System.out.println("2. Crear Areas");
            System.out.println("3. Editar Areas");
            System.out.println("4. Eliminar Areas");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                for (Area area : areas) {
                    System.out.println("************************************");
                    System.out.println("Tipo: "+area.tipo);
                    System.out.println("Horario: "+area.horario);
                    System.out.println("Persona a cargo: "+area.persona_a_cargo);
                    System.out.println("Telefono persona a cargo: "+area.telefono_persona_a_cargo);
                    System.out.println("************************************");
                }
            } else if (option.equals("2")) {
                //CrearAreas();
            } else if (option.equals("3")) {
                //EditarAreas();
            } else if (option.equals("4")) {
                //EliminarAreas();
            } else if (option.equals("0")) {
                break;
            }
        }
    }


    public static void AdministrarRutas() {
        String option = "";
        while(true) {
            System.out.println("Elija una opción");
            System.out.println("1. Ver Rutas");
            System.out.println("2. Crear Rutas");
            System.out.println("3. Editar Rutas");
            System.out.println("4. Eliminar Rutas");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                for (Ruta ruta : rutas) {
                    System.out.println("************************************");
                    System.out.println("id: "+ ruta.id);
                    System.out.println("Horario: "+ruta.horario);
                    System.out.println("dia: "+ruta.dia);
                    System.out.println("Id recolercor: "+ruta.recolector_id);
                    System.out.println("************************************");
                }
            } else if (option.equals("2")) {
                //CrearRutas();
            } else if (option.equals("3")) {
                //EditarRutas();
            } else if (option.equals("4")) {
                //EliminarRutas();
            } else if (option.equals("0")) {
                break;
            }
        }
    }


    public static void AdministrarTalleres() {
        String option = "";
        while(true) {
            System.out.println("Elija una opción");
            System.out.println("1. Ver Talleres");
            System.out.println("2. Crear Talleres");
            System.out.println("3. Editar Talleres");
            System.out.println("4. Eliminar Talleres");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                for (Taller taller : talleres) {
                    System.out.println("************************************");
                    System.out.println("Nombre: "+ taller.nombre);
                    System.out.println("Sistema asociado: "+taller.sistema_asociado);
                    System.out.println("Interno sede: "+taller.interno_sede);
                    System.out.println("Dinero fallas menores: "+taller.dinero_fallas_menores);
                    System.out.println("************************************");
                }
            } else if (option.equals("2")) {
                //CrearTalleres();
            } else if (option.equals("3")) {
                //EditarTalleres();
            } else if (option.equals("4")) {
                //EliminarTalleres();
            } else if (option.equals("0")) {
                break;
            }
        }
    }


    public static void AdministrarPersonal() {
        String option = "";
        while(true) {
            System.out.println("Elija una opción");
            System.out.println("1. Ver Personal");
            System.out.println("2. Crear Personal");
            System.out.println("3. Editar Personal");
            System.out.println("4. Eliminar Personal");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                for (Personal persona : personas) {
                    System.out.println("************************************");
                    System.out.println("Nombre: "+ persona.nombre);
                    System.out.println("Perfil: "+persona.perfil);
                    System.out.println("Horario: "+persona.horario);
                    System.out.println("Cedula: "+persona.cedula);
                    System.out.println("Sueldo: "+persona.sueldo);
                    System.out.println("************************************");
                }
            } else if (option.equals("2")) {
                //CrearPersonal();
            } else if (option.equals("3")) {
                //EditarPersonal();
            } else if (option.equals("4")) {
                //EliminarPersonal();
            } else if (option.equals("0")) {
                break;
            }
        }
    }


    public static void AdministrarRecolectores() {
        String option = "";
        while(true) {
            System.out.println("Elija una opción");
            System.out.println("1. Ver Recolector");
            System.out.println("2. Crear Recolector");
            System.out.println("3. Editar Recolector");
            System.out.println("4. Eliminar Recolector");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                for (Recolector recolector : recolectores) {
                    System.out.println("************************************");
                    System.out.println("Marca: "+recolector.marca);
                    System.out.println("Id: "+recolector.id);
                    System.out.println("Ruta Id: "+recolector.rutaid);
                    System.out.println("************************************");
                }
            } else if (option.equals("2")) {
                //CrearRecolector();
            } else if (option.equals("3")) {
                //EditarRecolector();
            } else if (option.equals("4")) {
                //EliminarRecolector();
            } else if (option.equals("0")) {
                break;
            }
        }
    }


    public static void MenuBusqueda() {
        String option = "";
        while(true) {
            System.out.println("MENU BÚSUQEDA");
            System.out.println("Elija la opción para buscar la información");
            System.out.println("1. Empresas de Basura");
            System.out.println("2. Sedes");
            System.out.println("3. Áreas");
            System.out.println("4. Rutas");
            System.out.println("5. Talleres");
            System.out.println("6. Personal");
            System.out.println("7. Recolector");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                BuscarEmpresasBasura();
            } else if (option.equals("2")) {
                BuscarSedes();
            } else if (option.equals("3")) {
                //BuscarAreas();
            } else if (option.equals("4")) {
                //BuscarRutas();
            } else if (option.equals("5")) {
                //BuscarTalleres();
            } else if (option.equals("6")) {
                //BuscarPersonal();
            } else if (option.equals("7")) {
                //BuscarRecolectores();
            } else if (option.equals("0")) {
                break;
            }
        }
    }

    public static void BuscarSedes() {
        LinkedList<Sede> sedes_copia= new LinkedList<>();
        sedes_copia= (LinkedList<Sede>) sedes.clone();
        LinkedList<Sede> sedesEncontradas= new LinkedList<>();
        int enumerador=1;
        boolean mostar_todos=false;

        if(sedes.size()!=0){
            System.out.println("Seleccione el atributo por el cual desea buscar la sede:");
            System.out.println("1. Telefono");
            System.out.println("2. Direccion");
            System.out.println("3. Persona a cargo");
            System.out.println("4. Mostrar todas las sedes");
            String seleccion= input.next();

            if(seleccion.equals("1")){

                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese el telefono de la sede: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Sede sede : sedes) {

                        if(sede.telefono.equals(seleccion)){
                            sedesEncontradas.add(sede);
                        }
                    }

                    if(sedesEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Telefono");
                        System.out.println("2. Direccion");
                        System.out.println("3. Persona a cargo");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar los telefonos de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_telefonoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_telefonoD());
                            }

                            enumerador=1;
                            for (Sede sede : sedesEncontradas) {
                                System.out.println(enumerador +". "+ sede);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar las direcciones de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_direccionA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_direccionD());
                            }             

                            enumerador=1;

                            for (Sede sede : sedesEncontradas) { 
                                System.out.println(enumerador+". "+ sede);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_personaA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_personaD());
                            }

                            enumerador=1;
                            for (Sede sede : sedesEncontradas) {
                                System.out.println(enumerador +". "+ sede);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                            return;
                        }
                    }else{
                        System.out.println("No se encontraron empresas en la cuidad seleccionada");
                        return;
                    }

                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese el telefono de la sede: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Sede sede : sedes) {

                        if(sede.telefono.toLowerCase().equals(seleccion)){
                            sedesEncontradas.add(sede);
                        }
                    }

                    if(sedesEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Telefono");
                        System.out.println("2. Direccion");
                        System.out.println("3. Persona a cargo");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar los telefonos de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_telefonoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_telefonoD());
                            }

                            enumerador=1;
                            for (Sede sede : sedesEncontradas) {
                                System.out.println(enumerador +". "+ sede);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar las direcciones de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_direccionA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_direccionD());
                            }             

                            enumerador=1;

                            for (Sede sede : sedesEncontradas) { 
                                System.out.println(enumerador+". "+ sede);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_personaA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_personaD());
                            }

                            enumerador=1;
                            for (Sede sede : sedesEncontradas) {
                                System.out.println(enumerador +". "+ sede);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                        }
                    }else{
                        System.out.println("No se encontraron empresas en la cuidad seleccionada");
                        return;
                    }
                }else{
                    System.out.println("Opción inválida");
                    return;
                }



            }else if(seleccion.equals("2")){
                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese la direccion: ");
                    input.nextLine();
                    seleccion= input.nextLine();

                    for (Sede sede : sedes) {

                        if(sede.direccion.equals(seleccion)){
                            sedesEncontradas.add(sede);
                        }
                    }

                    if(sedesEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Telefono");
                        System.out.println("2. Direccion");
                        System.out.println("3. Persona a cargo");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar los telefonos de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_telefonoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_telefonoD());
                            }

                            enumerador=1;
                            for (Sede sede : sedesEncontradas) {
                                System.out.println(enumerador +". "+ sede);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar las direcciones de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();
                            if(seleccion.equals("1")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_direccionA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_direccionD());
                            }            

                            enumerador=1;

                            for (Sede sede : sedesEncontradas) { 
                                System.out.println(enumerador+". "+ sede);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_personaA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_personaD());
                            }

                            enumerador=1;
                            for (Sede sede : sedesEncontradas) {
                                System.out.println(enumerador +". "+ sede);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                            return;
                        }
                    }else{
                        System.out.println("No se encontraron empresas en la cuidad seleccionada");
                        return;
                    }

                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese la direccion: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Sede sede : sedes) {

                        if(sede.direccion.toLowerCase().equals(seleccion)){
                            sedesEncontradas.add(sede);
                        }
                    }

                    if(sedesEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Telefono");
                        System.out.println("2. Direccion");
                        System.out.println("3. Persona a cargo");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar los telefonos de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_telefonoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_telefonoD());
                            }

                            enumerador=1;
                            for (Sede sede : sedesEncontradas) {
                                System.out.println(enumerador +". "+ sede);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar las direcciones de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next(); 

                            if(seleccion.equals("1")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_direccionA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_direccionD());
                            }            

                            enumerador=1;

                            for (Sede sede : sedesEncontradas) { 
                                System.out.println(enumerador+". "+ sede);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_personaA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_personaD());
                            }

                            enumerador=1;
                            for (Sede sede : sedesEncontradas) {
                                System.out.println(enumerador +". "+ sede);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                        }
                    }else{
                        System.out.println("No se encontraron empresas en la cuidad seleccionada");
                        return;
                    }
                }else{
                    System.out.println("Opción inválida");
                    return;
                }

            }else if(seleccion.equals("3")){

                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese el nombre de la persona a cargo: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Sede sede : sedes) {

                        if(sede.persona_a_cargo.equals(seleccion)){
                            sedesEncontradas.add(sede);
                        }
                    }

                    if(sedesEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Telefono");
                        System.out.println("2. Direccion");
                        System.out.println("3. Persona a cargo");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar los telefonos de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_telefonoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_telefonoD());
                            }

                            enumerador=1;
                            for (Sede sede : sedesEncontradas) {
                                System.out.println(enumerador +". "+ sede);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar las direcciones de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();            
                            if(seleccion.equals("1")){

                                Collections.sort(sedesEncontradas, new ComparadorSede_direccionA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_direccionD());
                            }
                            enumerador=1;

                            for (Sede sede : sedesEncontradas) { 
                                System.out.println(enumerador+". "+ sede);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();              

                            if(seleccion.equals("1")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_personaA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_personaD());
                            }

                            enumerador=1;
                            for (Sede sede : sedesEncontradas) {
                                System.out.println(enumerador +". "+ sede);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                            return;
                        }
                    }else{
                        System.out.println("No se encontraron empresas en la cuidad seleccionada");
                        return;
                    }

                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese el nombre de la persona a cargo: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Sede sede : sedes) {

                        if(sede.persona_a_cargo.toLowerCase().equals(seleccion)){
                            sedesEncontradas.add(sede);
                        }
                    }

                    if(sedesEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Telefono");
                        System.out.println("2. Direccion");
                        System.out.println("3. Persona a cargo");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar los telefonos de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_telefonoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_telefonoD());
                            }

                            enumerador=1;
                            for (Sede sede : sedesEncontradas) {
                                System.out.println(enumerador +". "+ sede);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar las direcciones de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();     
                            if(seleccion.equals("1")){

                                Collections.sort(sedesEncontradas, new ComparadorSede_direccionA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_direccionD());
                            }       

                            enumerador=1;

                            for (Sede sede : sedesEncontradas) { 
                                System.out.println(enumerador+". "+ sede);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_personaA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(sedesEncontradas, new ComparadorSede_personaD());
                            }

                            enumerador=1;
                            for (Sede sede : sedesEncontradas) {
                                System.out.println(enumerador +". "+ sede);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                            return;
                        }
                    }else{
                        System.out.println("No se encontraron empresas en la cuidad seleccionada");
                        return;
                    }
                }else{
                    System.out.println("Opción inválida");
                    return;
                }


            }else if(seleccion.equals("4")){
                mostar_todos=true;
                System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                System.out.println("1. Telefono");
                System.out.println("2. Direccion");
                System.out.println("3. Persona a cargo");
                seleccion= input.next();



                if(seleccion.equals("1")){
                    System.out.println("Desea organizar los telefonos de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(sedes_copia, new ComparadorSede_telefonoA());
                    } else if (seleccion.equals("2")) {
                        Collections.sort(sedes_copia, new ComparadorSede_telefonoD());
                    }

                    enumerador=1;
                    for (Sede sede : sedes_copia) {
                        System.out.println(enumerador +". "+ sede);
                        enumerador+=1;
                    }
                }else if(seleccion.equals("2")){
                    System.out.println("Desea organizar las direcciones de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();     
                    if(seleccion.equals("1")){

                        Collections.sort(sedes_copia, new ComparadorSede_direccionA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(sedes_copia, new ComparadorSede_direccionD());
                    }       

                    enumerador=1;

                    for (Sede sede : sedes_copia) { 
                        System.out.println(enumerador+". "+ sede);
                        enumerador+=1;
                    }

                }else if(seleccion.equals("3")){
                    System.out.println("Desea organizar los nombres de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(sedes_copia, new ComparadorSede_personaA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(sedes_copia,new ComparadorSede_personaD());
                    }

                    enumerador=1;
                    for (Sede sede : sedes_copia) {
                        System.out.println(enumerador +". "+ sede);
                        enumerador+=1;
                    }
                }else{

                    System.out.println("Opción inválida");
                    return;
                }
            }

        }else{
            System.out.println("No hay empresas de basura registradas en el sistema");
            return;
        }

        // Aquí se establece la búsqueda sobre las sedes 
        // Tener presente que se utilizará la función de Valentín de Editar y eliminar
        System.out.println("**********************************************");
        System.out.println("En las sedes encontradas desea:");
        System.out.println("1. Editar");
        System.out.println("2. Eliminar");
        System.out.println("3. Salir");
        String seleccion= input.next();

        if(seleccion.equals("1")){
            System.out.print("Ingrese el número de la lista correspondiente a la sede: ");
            int option= input.nextInt();
            Sede sede;
            if(!mostar_todos){
                if(sedesEncontradas.size()>=option && option>=1){
                    sede= sedesEncontradas.get(option-1);  // Aquí obtienes la sede a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }else{

                if(sedes_copia.size()>=option && option>=1){
                    sede= sedes_copia.get(option-1);  // Aquí obtienes la sede a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }

            
            BuscarEditarSede(sede);    
            

        }else if(seleccion.equals("2")){
            System.out.print("Ingrese el número de la lista correspondiente a la sede: ");
            int option= input.nextInt();
            Sede sede;
            if(!mostar_todos){
                if(sedesEncontradas.size()>=option && option>=1){
                    sede= sedesEncontradas.get(option-1);  // Aquí obtienes la sede a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }else{

                if(sedes_copia.size()>=option && option>=1){
                    sede= sedes_copia.get(option-1);  // Aquí obtienes la sede a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }
            // Aquí vas a implementar eliminar********************
            BuscarEliminarSede(sede);
        }
    }

    public static void BuscarEliminarSede(Sede place) {
        System.out.println("¿Seguro que desea eliminar esta sede?");
        System.out.println("Y");
        System.out.println("N");
        String option = input.next();
        boolean sedeEncontrada = false;
        if (option.equalsIgnoreCase("Y")) {
            Iterator<Sede> iterator = sedes.iterator();
            while(iterator.hasNext()) {
                Sede sede = iterator.next();
                if (sede.getTelefono().equals(place.getTelefono())) {
                    sedeEncontrada = true;
                    iterator.remove();
                }
            }

            if (!sedeEncontrada) {
                System.out.println("No se encontró la sede");
                return;
            }else{
                System.out.println("La sede se ha removido satisfactoriamente");
            }
        }

        

    }

    public static void BuscarEditarSede(Sede place) {
        for (Sede sede : sedes) {
            if(sede.telefono.equals(place.telefono)){

                String nuevo_telefono = "";
                String nuevo_direccion = "";
                String nuevo_persona = "";

                System.out.println("Telefono: "+ sede.getTelefono());
                System.out.println("Si desea cambiar el telefono, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_telefono = input.next();
                System.out.println("Direccion: "+ sede.getDireccion());
                System.out.println("Si desea cambiar la direccion, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_direccion = input.next();
                System.out.println("Persona a cargo: "+ sede.getDireccion());
                System.out.println("Si desea cambiar la persona a cargo, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_persona = input.next();
                System.out.println("¿Desea guardar los cambios?");
                System.out.println("Y");
                System.out.println("N");
                String option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    if (nuevo_telefono.equalsIgnoreCase("N")) {

                    }else {
                        sede.setTelefono(nuevo_telefono);
                    }
                    if (nuevo_direccion.equalsIgnoreCase("N")) {

                    } else {
                        sede.setDireccion(nuevo_direccion);
                    }
                    if (nuevo_persona.equalsIgnoreCase("N")) {

                    } else {
                    sede.setPersona_a_cargo(nuevo_persona);
                    }

                }
                System.out.println("Se han realizado los cambios satisfactoriamente");
                break;
        
            }
        }
        
    }


    public static void BuscarEmpresasBasura() {
        LinkedList<EmpresaDeBasura> empresaDeBasuras_copia= new LinkedList<>();
        empresaDeBasuras_copia= (LinkedList<EmpresaDeBasura>) empresasDeBasuras.clone();
        LinkedList<EmpresaDeBasura> empresasEncontradas= new LinkedList<>();
        int enumerador=1;
        boolean mostar_todos=false;

        if(empresasDeBasuras.size()!=0){
            System.out.println("Selecciones el atributo por el cual desea buscar la empresa de basura:");
            System.out.println("1. Nombre");
            System.out.println("2. Ciudad");
            System.out.println("3. Gerente");
            System.out.println("4. Mostrar todas las empresas de basura");
            String seleccion= input.next();

            if(seleccion.equals("1")){

                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese el nombre de la empresa: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (EmpresaDeBasura empresa : empresasDeBasuras) {

                        if(empresa.ciudad.equals(seleccion)){
                            empresasEncontradas.add(empresa);
                        }
                    }

                    if(empresasEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Nombre");
                        System.out.println("2. Ciudad");
                        System.out.println("3. Gerente");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_nombreA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_nombreD());
                            }

                            enumerador=1;
                            for (EmpresaDeBasura empresa : empresasEncontradas) {
                                System.out.println(enumerador +". "+ empresa);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar las cuidades de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_cuidadA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_cuidadD());
                            }             

                            enumerador=1;

                            for (EmpresaDeBasura empresa : empresasEncontradas) { 
                                System.out.println(enumerador+". "+ empresa);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_gerenteA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_gerenteD());
                            }

                            enumerador=1;
                            for (EmpresaDeBasura empresa : empresasEncontradas) {
                                System.out.println(enumerador +". "+ empresa);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                            return;
                        }
                    }else{
                        System.out.println("No se encontraron empresas en la cuidad seleccionada");
                        return;
                    }

                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese la cuidad: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (EmpresaDeBasura empresa : empresasDeBasuras) {

                        if(empresa.ciudad.toLowerCase().equals(seleccion)){
                            empresasEncontradas.add(empresa);
                        }
                    }

                    if(empresasEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Nombre");
                        System.out.println("2. Ciudad");
                        System.out.println("3. Gerente");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_nombreA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_nombreD());
                            }

                            enumerador=1;
                            for (EmpresaDeBasura empresa : empresasEncontradas) {
                                System.out.println(enumerador +". "+ empresa);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar las cuidades de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_cuidadA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_cuidadD());
                            }             

                            enumerador=1;

                            for (EmpresaDeBasura empresa : empresasEncontradas) { 
                                System.out.println(enumerador+". "+ empresa);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_gerenteA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_gerenteD());
                            }

                            enumerador=1;
                            for (EmpresaDeBasura empresa : empresasEncontradas) {
                                System.out.println(enumerador +". "+ empresa);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                        }
                    }else{
                        System.out.println("No se encontraron empresas en la cuidad seleccionada");
                        return;
                    }
                }else{
                    System.out.println("Opción inválida");
                    return;
                }



            }else if(seleccion.equals("2")){
                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese la cuidad: ");
                    input.nextLine();
                    seleccion= input.nextLine();

                    for (EmpresaDeBasura empresa : empresasDeBasuras) {

                        if(empresa.ciudad.equals(seleccion)){
                            empresasEncontradas.add(empresa);
                        }
                    }

                    if(empresasEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Nombre");
                        System.out.println("2. Ciudad");
                        System.out.println("3. Gerente");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_nombreA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_nombreD());
                            }

                            enumerador=1;
                            for (EmpresaDeBasura empresa : empresasEncontradas) {
                                System.out.println(enumerador +". "+ empresa);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar las cuidades de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();
                            if(seleccion.equals("1")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_cuidadA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_cuidadD());
                            }            

                            enumerador=1;

                            for (EmpresaDeBasura empresa : empresasEncontradas) { 
                                System.out.println(enumerador+". "+ empresa);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_gerenteA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_gerenteD());
                            }

                            enumerador=1;
                            for (EmpresaDeBasura empresa : empresasEncontradas) {
                                System.out.println(enumerador +". "+ empresa);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                            return;
                        }
                    }else{
                        System.out.println("No se encontraron empresas en la cuidad seleccionada");
                        return;
                    }

                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese la cuidad: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (EmpresaDeBasura empresa : empresasDeBasuras) {

                        if(empresa.ciudad.toLowerCase().equals(seleccion)){
                            empresasEncontradas.add(empresa);
                        }
                    }

                    if(empresasEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Nombre");
                        System.out.println("2. Ciudad");
                        System.out.println("3. Gerente");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_nombreA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_nombreD());
                            }

                            enumerador=1;
                            for (EmpresaDeBasura empresa : empresasEncontradas) {
                                System.out.println(enumerador +". "+ empresa);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar las cuidades de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next(); 

                            if(seleccion.equals("1")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_cuidadA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_cuidadD());
                            }            

                            enumerador=1;

                            for (EmpresaDeBasura empresa : empresasEncontradas) { 
                                System.out.println(enumerador+". "+ empresa);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_gerenteA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_gerenteD());
                            }

                            enumerador=1;
                            for (EmpresaDeBasura empresa : empresasEncontradas) {
                                System.out.println(enumerador +". "+ empresa);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                        }
                    }else{
                        System.out.println("No se encontraron empresas en la cuidad seleccionada");
                        return;
                    }
                }else{
                    System.out.println("Opción inválida");
                    return;
                }

            }else if(seleccion.equals("3")){

                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese el nombre del gerente: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (EmpresaDeBasura empresa : empresasDeBasuras) {

                        if(empresa.gerente.equals(seleccion)){
                            empresasEncontradas.add(empresa);
                        }
                    }

                    if(empresasEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Nombre");
                        System.out.println("2. Ciudad");
                        System.out.println("3. Gerente");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_nombreA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_nombreD());
                            }

                            enumerador=1;
                            for (EmpresaDeBasura empresa : empresasEncontradas) {
                                System.out.println(enumerador +". "+ empresa);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar las cuidades de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();            
                            if(seleccion.equals("1")){

                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_cuidadA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_cuidadD());
                            }
                            enumerador=1;

                            for (EmpresaDeBasura empresa : empresasEncontradas) { 
                                System.out.println(enumerador+". "+ empresa);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();              

                            if(seleccion.equals("1")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_gerenteA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_gerenteD());
                            }

                            enumerador=1;
                            for (EmpresaDeBasura empresa : empresasEncontradas) {
                                System.out.println(enumerador +". "+ empresa);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                            return;
                        }
                    }else{
                        System.out.println("No se encontraron empresas en la cuidad seleccionada");
                        return;
                    }

                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese el nombre del gerente: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (EmpresaDeBasura empresa : empresasDeBasuras) {

                        if(empresa.gerente.toLowerCase().equals(seleccion)){
                            empresasEncontradas.add(empresa);
                        }
                    }

                    if(empresasEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Nombre");
                        System.out.println("2. Ciudad");
                        System.out.println("3. Gerente");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_nombreA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_nombreD());
                            }

                            enumerador=1;
                            for (EmpresaDeBasura empresa : empresasEncontradas) {
                                System.out.println(enumerador +". "+ empresa);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar las cuidades de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();     
                            if(seleccion.equals("1")){

                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_cuidadA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_cuidadD());
                            }       

                            enumerador=1;

                            for (EmpresaDeBasura empresa : empresasEncontradas) { 
                                System.out.println(enumerador+". "+ empresa);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_gerenteA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_gerenteD());
                            }

                            enumerador=1;
                            for (EmpresaDeBasura empresa : empresasEncontradas) {
                                System.out.println(enumerador +". "+ empresa);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                            return;
                        }
                    }else{
                        System.out.println("No se encontraron empresas en la cuidad seleccionada");
                        return;
                    }
                }else{
                    System.out.println("Opción inválida");
                    return;
                }


            }else if(seleccion.equals("4")){
                mostar_todos=true;
                System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                System.out.println("1. Nombre");
                System.out.println("2. Ciudad");
                System.out.println("3. Gerente");
                seleccion= input.next();



                if(seleccion.equals("1")){
                    System.out.println("Desea organizar los nombres de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(empresaDeBasuras_copia, new ComparadorEmpresa_nombreA());
                    } else if (seleccion.equals("2")) {
                        Collections.sort(empresaDeBasuras_copia, new ComparadorEmpresa_nombreD());
                    }

                    enumerador=1;
                    for (EmpresaDeBasura empresa : empresaDeBasuras_copia) {
                        System.out.println(enumerador +". "+ empresa);
                        enumerador+=1;
                    }
                }else if(seleccion.equals("2")){
                    System.out.println("Desea organizar las cuidades de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();     
                    if(seleccion.equals("1")){

                        Collections.sort(empresaDeBasuras_copia, new ComparadorEmpresa_cuidadA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(empresaDeBasuras_copia, new ComparadorEmpresa_cuidadD());
                    }       

                    enumerador=1;

                    for (EmpresaDeBasura empresa : empresaDeBasuras_copia) { 
                        System.out.println(enumerador+". "+ empresa);
                        enumerador+=1;
                    }

                }else if(seleccion.equals("3")){
                    System.out.println("Desea organizar los nombres de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(empresaDeBasuras_copia, new ComparadorEmpresa_gerenteA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(empresaDeBasuras_copia,new ComparadorEmpresa_gerenteD());
                    }

                    enumerador=1;
                    for (EmpresaDeBasura empresa : empresaDeBasuras_copia) {
                        System.out.println(enumerador +". "+ empresa);
                        enumerador+=1;
                    }
                }else{

                    System.out.println("Opción inválida");
                    return;
                }
            }

        }else{
            System.out.println("No hay empresas de basura registradas en el sistema");
            return;
        }

        // Aquí se establece la búsqueda sobre las empresas de basura
        // Tener presente que se utilizará la función de Valentín de Editar y eliminar
        System.out.println("**********************************************");
        System.out.println("En las empresas encontradas desea:");
        System.out.println("1. Editar");
        System.out.println("2. Eliminar");
        System.out.println("3. Salir");
        String seleccion= input.next();

        if(seleccion.equals("1")){
            System.out.print("Ingrese el número de la lista correspondiente a la empresa: ");
            int option= input.nextInt();
            EmpresaDeBasura empresa;

            if(!mostar_todos){
                if(empresasEncontradas.size()>=option && option>=1){
                    empresa= empresasEncontradas.get(option-1);  // Aquí obtienes la empresa a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }else{
                if(empresaDeBasuras_copia.size()>=option && option>=1){
                    empresa= empresaDeBasuras_copia.get(option-1);  // Aquí obtienes la empresa a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }
            
            
            BuscarEditarEmpresasBasura(empresa);    
            

        }else if(seleccion.equals("2")){
            System.out.print("Ingrese el número de la lista correspondiente a la empresa: ");
            int option= input.nextInt();
            EmpresaDeBasura empresa;
            if(!mostar_todos){
                if(empresasEncontradas.size()>=option && option>=1){
                    empresa=empresasEncontradas.get(option-1);
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }else{
                if(empresaDeBasuras_copia.size()>=option && option>=1){
                    empresa=empresaDeBasuras_copia.get(option-1);
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
                
            }
            
            // Aquí vas a implementar eliminar********************
            BuscarEliminarEmpresasBasura(empresa);
        }
        
    }

    public static void BuscarEditarEmpresasBasura(EmpresaDeBasura company) {
        for (EmpresaDeBasura empresa : empresasDeBasuras) {
            if(empresa.nombre.equals(company.nombre)){

                String nuevo_nombre = "";
                String nuevo_gerente = "";
                String nueva_ciudad = "";

                System.out.println("Nombre: "+ empresa.getNombre());
                System.out.println("Si desea cambiar el nombre, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_nombre = input.next();
                System.out.println("Ciudad: "+ empresa.getCiudad());
                System.out.println("Si desea cambiar la ciudad, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nueva_ciudad = input.next();
                System.out.println("Gerente: "+ empresa.getGerente());
                System.out.println("Si desea cambiar el gerente, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_gerente = input.next();
                System.out.println("¿Desea guardar los cambios?");
                System.out.println("Y");
                System.out.println("N");
                String option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    if (nuevo_nombre.equalsIgnoreCase("N")) {

                    } else {
                        empresa.setNombre(nuevo_nombre);
                    }
                    if (nueva_ciudad.equalsIgnoreCase("N")) {

                    } else {
                        empresa.setCiudad(nueva_ciudad);
                    }
                    if (nuevo_gerente.equalsIgnoreCase("N")) {

                    } else {
                    empresa.setGerente(nuevo_gerente);
                    }

                }
                System.out.println("Se han realizado los cambios satisfactoriamente");
                break;
        
            }
        }
        
    }

    public static void BuscarEliminarEmpresasBasura(EmpresaDeBasura company) {
        System.out.println("¿Seguro que desea eliminar esta empresa");
        System.out.println("Y");
        System.out.println("N");
        String option = input.next();
        boolean empresaEncontrada = false;
        if (option.equalsIgnoreCase("Y")) {
            Iterator<EmpresaDeBasura> iterator = empresasDeBasuras.iterator();
            while(iterator.hasNext()) {
                EmpresaDeBasura empresa = iterator.next();
                if (empresa.getNombre().equals(company.nombre)) {
                    empresaEncontrada = true;
                    iterator.remove();
                }
            }

            if (!empresaEncontrada) {
                System.out.println("No se encontró la empresa");
                return;
            }else{
                System.out.println("La empresa se ha removido satisfactoriamente");
            }
        }

        

    }


    public static String atributo_string_buscar() {
        System.out.println("Desea buscar por: ");
        System.out.println("1. Valor exacto");
        System.out.println("2. Valor sin considerar mayúsculas");
        String seleccion= input.next();
        return seleccion;
    }


}
