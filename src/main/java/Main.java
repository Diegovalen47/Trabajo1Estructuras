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

        try (FileReader reader = new FileReader("C:\\Users\\user\\IdeaProjects\\Trabajo1\\Trabajo1Estructuras\\src\\main\\java\\META-INF\\database\\usuarios.json")) {
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

        try (FileReader reader = new FileReader("C:\\Users\\user\\IdeaProjects\\Trabajo1\\Trabajo1Estructuras\\src\\main\\java\\META-INF\\database\\empresasDeBasuras.json")) {
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

        try (FileReader reader = new FileReader("C:\\Users\\user\\IdeaProjects\\Trabajo1\\Trabajo1Estructuras\\src\\main\\java\\META-INF\\database\\sedes.json")) {
            Object obj = parser.parse(reader);
            JsonArray sedesList = (JsonArray) obj;

            for (Object object : sedesList) {
                String jsonString = gson.toJson(object);
                Sede sede = gson.fromJson(jsonString, Sede.class);
                sedes.add(sede);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        try(FileReader reader = new FileReader("C:\\Users\\user\\IdeaProjects\\Trabajo1\\Trabajo1Estructuras\\src\\main\\java\\META-INF\\database\\areas.json")) {
            Object obj = parser.parse(reader);
            JsonArray areasList = (JsonArray) obj;

            for (Object object : areasList) {
                String jsonString = gson.toJson(object);
                Area area = gson.fromJson(jsonString, Area.class);
                areas.add(area);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        try(FileReader reader = new FileReader("C:\\Users\\user\\IdeaProjects\\Trabajo1\\Trabajo1Estructuras\\src\\main\\java\\META-INF\\database\\talleres.json")) {
            Object obj = parser.parse(reader);
            JsonArray talleresList = (JsonArray) obj;

            for (Object object : talleresList) {
                String jsonString = gson.toJson(object);
                Taller taller = gson.fromJson(jsonString, Taller.class);
                talleres.add(taller);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



        try(FileReader reader = new FileReader("C:\\Users\\user\\IdeaProjects\\Trabajo1\\Trabajo1Estructuras\\src\\main\\java\\META-INF\\database\\rutas.json")) {
            Object obj = parser.parse(reader);
            JsonArray rutasList = (JsonArray) obj;

            for (Object object : rutasList) {
                String jsonString = gson.toJson(object);
                Ruta ruta = gson.fromJson(jsonString, Ruta.class);
                rutas.add(ruta);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        try(FileReader reader = new FileReader("C:\\Users\\user\\IdeaProjects\\Trabajo1\\Trabajo1Estructuras\\src\\main\\java\\META-INF\\database\\personas.json")) {
            Object obj = parser.parse(reader);
            JsonArray personasList = (JsonArray) obj;

            for (Object object : personasList) {
                String jsonString = gson.toJson(object);
                Personal persona = gson.fromJson(jsonString, Personal.class);
                personas.add(persona);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try(FileReader reader = new FileReader("C:\\Users\\user\\IdeaProjects\\Trabajo1\\Trabajo1Estructuras\\src\\main\\java\\META-INF\\database\\recolectores.json")) {
            Object obj = parser.parse(reader);
            JsonArray recolectoresList = (JsonArray) obj;

            for (Object object : recolectoresList) {
                String jsonString = gson.toJson(object);
                Recolector recolector = gson.fromJson(jsonString, Recolector.class);
                recolectores.add(recolector);
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

                }
            }
        } else {       // Se busca por documento de identidad

            int documento_identidad = Integer.parseInt(quitarPuntosDocumentos(option));
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
        String documento_identidad = input.next();
        int documento = Integer.parseInt(quitarPuntosDocumentos(documento_identidad));

        boolean documento_existente = true;
        while (documento_existente) {         // Verificacion que el documento de identidad no está registrado
            documento_existente = false;
            for (Usuario usuario : usuarios) {

                if (usuario.cedula == documento) {
                    documento_existente = true;
                    System.out.println("Ya existe un usuario registrado con ese documento");
                    System.out.println("Intente nuevamente");
                    System.out.print("Documento de identidad : ");
                    documento_identidad = input.next();
                    documento = Integer.parseInt(quitarPuntosDocumentos(documento_identidad));
                    break;
                }
            }

        }

        System.out.print("Contraseña : ");
        String clave = input.next();

        Usuario usuario = new Usuario(documento, nombre, apellido, correo, clave);
        usuarios.add(usuario);

        System.out.println("¡Usted se ha registrado exitosamente!");
        GuardarUsuarios();
        IniciarSesion();
    }


    public static void GuardarUsuarios() {
        String jsonString = gson.toJson(usuarios);

        try (FileWriter file = new FileWriter("C:\\Users\\user\\IdeaProjects\\Trabajo1\\Trabajo1Estructuras\\src\\main\\java\\META-INF\\database\\usuarios.json")) {

            file.write(jsonString);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void GuardarObjetos() {
        String jsonStringEmpresasDeBasura = gson.toJson(empresasDeBasuras);

        try (FileWriter file = new FileWriter("C:\\Users\\user\\IdeaProjects\\Trabajo1\\Trabajo1Estructuras\\src\\main\\java\\META-INF\\database\\empresasDeBasuras.json")) {

            file.write(jsonStringEmpresasDeBasura);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


        String jsonStringSedes = gson.toJson(sedes);

        try (FileWriter file = new FileWriter("C:\\Users\\user\\IdeaProjects\\Trabajo1\\Trabajo1Estructuras\\src\\main\\java\\META-INF\\database\\sedes.json")) {

            file.write(jsonStringSedes);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


        String jsonStringAreas = gson.toJson(areas);

        try (FileWriter file = new FileWriter("C:\\Users\\user\\IdeaProjects\\Trabajo1\\Trabajo1Estructuras\\src\\main\\java\\META-INF\\database\\areas.json")) {

            file.write(jsonStringAreas);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


        String jsonStringTalleres = gson.toJson(talleres);

        try (FileWriter file = new FileWriter("C:\\Users\\user\\IdeaProjects\\Trabajo1\\Trabajo1Estructuras\\src\\main\\java\\META-INF\\database\\talleres.json")) {

            file.write(jsonStringTalleres);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


        String jsonStringRutas = gson.toJson(rutas);

        try (FileWriter file = new FileWriter("C:\\Users\\user\\IdeaProjects\\Trabajo1\\Trabajo1Estructuras\\src\\main\\java\\META-INF\\database\\rutas.json")) {

            file.write(jsonStringRutas);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }



        String jsonStringPersonal= gson.toJson(personas);

        try (FileWriter file = new FileWriter("C:\\Users\\user\\IdeaProjects\\Trabajo1\\Trabajo1Estructuras\\src\\main\\java\\META-INF\\database\\personas.json")) {

            file.write(jsonStringPersonal);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }



        String jsonStringRecolectores= gson.toJson(recolectores);

        try (FileWriter file = new FileWriter("C:\\Users\\user\\IdeaProjects\\Trabajo1\\Trabajo1Estructuras\\src\\main\\java\\META-INF\\database\\recolectores.json")) {

            file.write(jsonStringRecolectores);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Se han guardado los datos de manera exitosa");
    }


    public static String quitarPuntosDocumentos(String documento) {
        LinkedList<Character> caracteres = new LinkedList<>();
        for (int i = 0; i < documento.length(); i++) {
            char x = documento.charAt(i);
            caracteres.add(x);
        }

        Iterator<Character> iterator = caracteres.iterator();
        while (iterator.hasNext()) {
            char car = iterator.next();
            if (car == '.') {
                iterator.remove();
            }
        }

        String nuevo_documento = "";
        for(char car : caracteres) {
            nuevo_documento+= car;
        }

        return nuevo_documento;
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
                DiagnosticoInconsistencias();
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


    public static void DiagnosticoInconsistencias() {
        System.out.println("********************************************");
        System.out.println("DIAGNÓSTICO  DE INCONSISTENCIAS");
        System.out.println("*********************************************");
        for (EmpresaDeBasura empresa : empresasDeBasuras) {
            if (empresa.sedes.isEmpty()) {
                System.out.println("La empresa "+ empresa.getNombre()+ " no tiene sedes asociadas");
            }
        }
        System.out.println("*********************************************");
        for (Sede sede : sedes) {
            if (sede.areas.isEmpty()) {
                System.out.println("La sede con persona a cargo "+ sede.getPersona_a_cargo()+" y numero telefónico "+ sede.getTelefono()+ " no tiene areas registradas");
            }
        }
        System.out.println("*********************************************");
        for (Area area : areas) {
            if (area.personas.isEmpty()) {
                System.out.println("El area de tipo " + area.getTipo()+" no tiene personal asociado");
            }
        }
        System.out.println("*********************************************");
        for (Taller taller : talleres) {
            if (taller.personas.isEmpty()) {
                System.out.println("El taller "+ taller.getNombre()+" no tiene personal asociado");
            }
        }
        System.out.println("*********************************************");
        for (Taller taller : talleres) {
            if (!taller.recolectoresVarados.isEmpty()) {
                System.out.println(taller);
                System.out.println("Este taller tiene recolectores varados esperando a ser reparados");
                System.out.println("¿Desea atenderlos?");
                System.out.println("Y");
                System.out.println("N");
                String option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    while (!taller.recolectoresVarados.isEmpty()) {
                        taller.AtenderRecolector();
                    }
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
            System.out.println("4. Talleres");
            System.out.println("5. Personal");
            System.out.println("6. Recolectores");
            System.out.println("7. Rutas");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                AdministrarEmpresasBasura();
            } else if (option.equals("2")) {
                AdministrarSedes();
            } else if (option.equals("3")) {
                AdministrarAreas();
            } else if (option.equals("4")) {
                AdministrarTalleres();
            } else if (option.equals("5")) {
                AdministrarPersonal();
            } else if (option.equals("6")) {
                AdministrarRecolectores();
            } else if (option.equals("7")) {
                AdministrarRutas();
            } else if (option.equals("0")) {
                break;
            }
        }
    }


    public static void AdministrarEmpresasBasura() {
        String option = "";
        while(true) {
            System.out.println("Elija una opción");
            System.out.println("1. Ver empresas de basura");
            System.out.println("2. Crear empresa de basura");
            System.out.println("3. Editar empresa de basura");
            System.out.println("4. Eliminar empresa de basura");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                if (empresasDeBasuras.isEmpty()) {
                    System.out.println("No hay empresas de basura registradas");
                } else {
                    for (EmpresaDeBasura empresaDeBasura : empresasDeBasuras) {
                        System.out.println("************************************");
                        System.out.println("Nombre: " + empresaDeBasura.nombre);
                        System.out.println("Ciudad: " + empresaDeBasura.ciudad);
                        System.out.println("Gerente: " + empresaDeBasura.gerente);
                        System.out.println("************************************");
                    }
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
        if (empresasDeBasuras.isEmpty()) {
            System.out.println("No hay empresas de basura registradas");
            return;
        }
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
                for (EmpresaDeBasura empresa1 : empresasDeBasuras) {
                    if (empresa1.getNombre().equalsIgnoreCase(nuevo_nombre)) {
                        System.out.println("Ya existe una empresa con ese nombre");
                        return;
                    }
                }
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
        if (empresasDeBasuras.isEmpty()) {
            System.out.println("No hay empresas de basura registradas");
            return;
        }
        System.out.println("Se seleccionará por nombre de empresa");
        System.out.println("Ingrese el nombre de la empresa a eliminar");
        String nombre = input.next();
        System.out.println("¿Seguro que desea eliminar esta empresa?");
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
            System.out.println("2. Crear Sede");
            System.out.println("3. Editar Sede");
            System.out.println("4. Eliminar Sede");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                if (sedes.isEmpty()) {
                    System.out.println("No hay sedes registradas");
                } else {
                    for (Sede sede : sedes) {
                        System.out.println("************************************");
                        System.out.println("Telefono: " + sede.telefono);
                        System.out.println("Direccion: " + sede.direccion);
                        System.out.println("Persona a cargo: " + sede.persona_a_cargo);
                        System.out.println("************************************");
                    }
                }
            } else if (option.equals("2")) {
                CrearSedes();
            } else if (option.equals("3")) {
                EditarSedes();
            } else if (option.equals("4")) {
                EliminarSedes();
            } else if (option.equals("0")) {
                break;
            }
        }
    }


    public static void CrearSedes() {
        if (empresasDeBasuras.isEmpty()) {
            System.out.println("No hay empresas de basura registradas, por lo tanto no puede crear sedes");
            return;
        }
        System.out.println("CREANDO SEDE");
        System.out.println("Ingrese el telefono de la sede");
        String telefono = input.next();

        for (Sede sede : sedes) {
            if (sede.getTelefono().equalsIgnoreCase(telefono)) {
                System.out.println("Esa sede ya existe");
                return;
            }
        }
        System.out.println("Ingrese la direccion de la sede");
        String direccion = input.next();
        for (Sede sede : sedes) {
            if (sede.getDireccion().equalsIgnoreCase(direccion)) {
                System.out.println("Esa sede ya existe");
                return;
            }
        }
        System.out.println("Ingrese el nombre de la persona a cargo de la sede");
        String personaAcargo = input.next();
        System.out.println("Ingrese el nombre de empresa de basura a la cual va a asociar la sede");
        String nombreEmpresa = input.next();
        Sede sede = new Sede(telefono,direccion,personaAcargo);
        boolean EmpresaEncontrada = false;
        for (EmpresaDeBasura empresa : empresasDeBasuras) {
            if (empresa.getNombre().equals(nombreEmpresa)) {
                EmpresaEncontrada = true;
                empresa.setSedes(sede);
                break;
            }
        }
        if (!EmpresaEncontrada) {
            System.out.println("No exite empresa de basura tal para asociar sede");
            return;
        }
        sedes.add(sede);
        System.out.println("Sede creada satisfactoriamente");

    }


    public static void EditarSedes() {
        if (sedes.isEmpty()) {
            System.out.println("No hay sedes registradas");
            return;
        }
        String option = "";
        while(true) {
            System.out.println("Elija una opción");
            System.out.println("1. Seleciionar por dirección");
            System.out.println("2. Seleccionar por telefono");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                System.out.println("Se seleccionará por direccion de la sede");
                System.out.println("Ingrese la direccion de la sede");
                String direccion = input.next();
                String nuevo_telefono = "";
                String nueva_direccion = "";
                String nueva_persona_a_cargo = "";
                boolean SedeEncontrada = false;
                for (Sede sede : sedes) {
                    if (sede.getDireccion().equalsIgnoreCase(direccion)) {
                        SedeEncontrada = true;
                        System.out.println("Direccion: "+ sede.getDireccion());
                        System.out.println("Si desea cambiar la direccion, ingrese su nuevo valor");
                        System.out.println("en otro caso, digite: N");
                        nueva_direccion = input.next();
                        for (Sede sede1 : sedes) {
                            if (sede1.getDireccion().equalsIgnoreCase(nueva_direccion)) {
                                System.out.println("Esa sede ya existe");
                                return;
                            }
                        }
                        System.out.println("Persona a cargo: "+ sede.getPersona_a_cargo());
                        System.out.println("Si desea cambiar la persona a cargo, ingrese su nuevo valor");
                        System.out.println("en otro caso, digite: N");
                        nueva_persona_a_cargo = input.next();
                        System.out.println("Teléfono: "+ sede.getTelefono());
                        System.out.println("Si desea cambiar el teléfono, ingrese su nuevo valor");
                        System.out.println("en otro caso, digite: N");
                        nuevo_telefono = input.next();
                        for (Sede sede1 : sedes) {
                            if (sede1.getTelefono().equalsIgnoreCase(nuevo_telefono)) {
                                System.out.println("Esa sede ya existe");
                                return;
                            }
                        }
                        System.out.println("¿Desea guardar los cambios?");
                        System.out.println("Y");
                        System.out.println("N");
                        option = input.next();
                        if (option.equalsIgnoreCase("Y")) {
                            if (nueva_direccion.equalsIgnoreCase("N")) {

                            } else {
                                sede.setDireccion(nueva_direccion);
                            }
                            if (nueva_persona_a_cargo.equalsIgnoreCase("N")) {

                            } else {
                                sede.setPersona_a_cargo(nueva_persona_a_cargo);
                            }
                            if (nuevo_telefono.equalsIgnoreCase("N")) {

                            } else {
                                sede.setTelefono(nuevo_telefono);
                            }
                        } else {

                        }
                    }
                }
                if (!SedeEncontrada) {
                    System.out.println("No se encntró la sede");
                }
            } else if (option.equals("2")) {
                System.out.println("Se seleccionará por telefono de la sede");
                System.out.println("Ingrese el télefono");
                String telefono = input.next();
                String nuevo_telefono = "";
                String nueva_direccion = "";
                String nueva_persona_a_cargo = "";
                boolean SedeEncontrada = false;
                for (Sede sede : sedes) {
                    if (sede.getTelefono().equalsIgnoreCase(telefono)) {
                        SedeEncontrada = true;
                        System.out.println("Direccion: "+ sede.getDireccion());
                        System.out.println("Si desea cambiar la direccion, ingrese su nuevo valor");
                        System.out.println("en otro caso, digite: N");
                        nueva_direccion = input.next();
                        System.out.println("Persona a cargo: "+ sede.getPersona_a_cargo());
                        System.out.println("Si desea cambiar la persona a cargo, ingrese su nuevo valor");
                        System.out.println("en otro caso, digite: N");
                        nueva_persona_a_cargo = input.next();
                        System.out.println("Teléfono: "+ sede.getTelefono());
                        System.out.println("Si desea cambiar el teléfono, ingrese su nuevo valor");
                        System.out.println("en otro caso, digite: N");
                        nuevo_telefono = input.next();
                        System.out.println("¿Desea guardar los cambios?");
                        System.out.println("Y");
                        System.out.println("N");
                        option = input.next();
                        if (option.equalsIgnoreCase("Y")) {
                            if (nueva_direccion.equalsIgnoreCase("N")) {

                            } else {
                                sede.setDireccion(nueva_direccion);
                            }
                            if (nueva_persona_a_cargo.equalsIgnoreCase("N")) {

                            } else {
                                sede.setPersona_a_cargo(nueva_persona_a_cargo);
                            }
                            if (nuevo_telefono.equalsIgnoreCase("N")) {

                            } else {
                                sede.setTelefono(nuevo_telefono);
                            }
                        } else {
                            //No guarda los cambios
                        }
                    }
                }
                if (!SedeEncontrada) {
                    System.out.println("No se encntró la sede");
                }
            } else if (option.equals("0")) {
                break;
            }
        }
    }


    public static void EliminarSedes() {
        if (sedes.isEmpty()) {
            System.out.println("No hay sedes registradas");
            return;
        }
        String option = "";
        while(true) {
            System.out.println("Elija una opción");
            System.out.println("1. Seleciionar por dirección");
            System.out.println("2. Seleccionar por telefono");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                System.out.println("Se seleccionará por dirección de la sede");
                System.out.println("Ingrese la dirección de la sede a eliminar");
                String direccion = input.next();
                System.out.println("¿Seguro que desea eliminar esta sede?");
                System.out.println("Y");
                System.out.println("N");
                option = input.next();
                boolean sedeEncontrada = false;
                if (option.equalsIgnoreCase("Y")) {
                    Iterator<Sede> iterator = sedes.iterator();
                    while(iterator.hasNext()) {
                        Sede sede = iterator.next();
                        if (sede.getDireccion().equals(direccion)) {
                            sedeEncontrada = true;
                            iterator.remove();
                        }
                    }

                    if (!sedeEncontrada) {
                        System.out.println("No se encontró la sede");
                    }
                }

            } else if (option.equals("2")) {
                System.out.println("Se seleccionará por telefono de la sede");
                System.out.println("Ingrese el telefono de la sede a eliminar");
                String telefono = input.next();
                System.out.println("¿Seguro que desea eliminar esta sede?");
                System.out.println("Y");
                System.out.println("N");
                option = input.next();
                boolean sedeEncontrada = false;
                if (option.equalsIgnoreCase("Y")) {
                    Iterator<Sede> iterator = sedes.iterator();
                    while(iterator.hasNext()) {
                        Sede sede = iterator.next();
                        if (sede.getTelefono().equalsIgnoreCase(telefono)) {
                            sedeEncontrada = true;
                            iterator.remove();
                        }
                    }

                    if (!sedeEncontrada) {
                        System.out.println("No se encontró la sede");
                    }
                }
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
            System.out.println("2. Crear Area");
            System.out.println("3. Editar Area");
            System.out.println("4. Eliminar Area");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                if (areas.isEmpty()) {
                    System.out.println("No hay areas registradas");
                } else {
                    for (Area area : areas) {
                        System.out.println("************************************");
                        System.out.println("Tipo: " + area.tipo);
                        System.out.println("Horario: " + area.horario);
                        System.out.println("Persona a cargo: " + area.persona_a_cargo);
                        System.out.println("Telefono persona a cargo: " + area.telefono_persona_a_cargo);
                        System.out.println("************************************");
                    }
                }
            } else if (option.equals("2")) {
                CrearAreas();
            } else if (option.equals("3")) {
                EditarAreas();
            } else if (option.equals("4")) {
                EliminarAreas();
            } else if (option.equals("0")) {
                break;
            }
        }
    }


    public static void CrearAreas() {
        if (sedes.isEmpty()) {
            System.out.println("No hay sedes registradas, por lo tanto no puede crear el area");
            return;
        }
        System.out.println("CREANDO ÁREA");
        System.out.println("Ingrese el tipo de area");
        String tipo = input.next();
        System.out.println("¿El Área tiene contratista");
        System.out.println("Y");
        System.out.println("N");
        String option = input.next();
        boolean contratista;
        if (option.equalsIgnoreCase("Y")) {
            contratista = true;

        } else {
            contratista = false;
        }
        System.out.println("Ingrese el horario de la empresa");
        String horario = input.next();
        System.out.println("Ingrese el nombre de la persona a cargo");
        String persona_a_cargo = input.next();
        System.out.println("Ingrese el teléfono de a persona a cargo");
        String telefono_persona_a_cargo = input.next();
        for (Area area : areas) {
            if (area.getTelefono_persona_a_cargo().equalsIgnoreCase(telefono_persona_a_cargo)) {
                System.out.println("El area ya existe, intente de nuevo");
                return;
            }
        }

        System.out.println("Ingrese el teléfono de la sede a la cual desea asociar el area");
        String telefonoSede = input.next();
        Area area = new Area(tipo,contratista,horario,persona_a_cargo,telefono_persona_a_cargo);
        boolean SedeEncontrada = false;
        for (Sede sede : sedes) {
            if (sede.getTelefono().equalsIgnoreCase(telefonoSede)) {
                SedeEncontrada = true;
                sede.setAreas(area);
                break;
            }
        }
        if (!SedeEncontrada) {
            System.out.println("No exite tal sede para asociar el area");
            return;
        }
        areas.add(area);
        System.out.println("Área creada satisfactoriamente");
    }


    public static void EditarAreas() {
        if (areas.isEmpty()) {
            System.out.println("No hay areas registradas");
            return;
        }
        System.out.println("Se seleccionará por teléfono de persona a cargo de la sede");
        System.out.println("Ingrese el teléfono");
        String telefono_persona_a_cargo = input.next();
        String nuevo_tipo = "";
        String nuevo_contratista = "";
        String nuevo_horario = "";
        String nueva_persona_a_cargo = "";
        String option = "";
        String nuevo_telefono_persona_a_cargo = "";
        boolean AreaEncontrada = false;
        for (Area area : areas) {
            if (area.getTelefono_persona_a_cargo().equalsIgnoreCase(telefono_persona_a_cargo)) {
                AreaEncontrada = true;
                System.out.println("Tipo: "+ area.getTipo());
                System.out.println("Si desea cambiar el tipo, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_tipo = input.next();
                System.out.println("Contratista: "+ area.isContratista());
                System.out.println("¡Desea cambiar el estado de contratista?");
                System.out.println("Y");
                System.out.println("N");
                nuevo_contratista = input.next();
                System.out.println("Horario: "+ area.getHorario());
                System.out.println("Si desea cambiar el horario, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_horario = input.next();
                System.out.println("Persona a cargo: "+ area.getPersona_a_cargo());
                System.out.println("Si desea cambiar la persona a cargo, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nueva_persona_a_cargo = input.next();
                System.out.println("Telefono persona a cargo: "+ area.getPersona_a_cargo());
                System.out.println("Si desea cambiar el telefono de la persona a cargo, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_telefono_persona_a_cargo = input.next();
                for (Area area1 : areas) {
                    if (area1.getTelefono_persona_a_cargo().equalsIgnoreCase(nuevo_telefono_persona_a_cargo)) {
                        System.out.println("El area ya existe, intente de nuevo");
                        return;
                    }
                }
                System.out.println("¿Desea guardar los cambios?");
                System.out.println("Y");
                System.out.println("N");
                option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    if (nuevo_tipo.equalsIgnoreCase("N")) {

                    } else {
                        area.setTipo(nuevo_tipo);
                    }
                    if (nuevo_contratista.equalsIgnoreCase("N")) {

                    } else {
                        area.setContratista(!area.contratista);
                    }
                    if (nuevo_horario.equalsIgnoreCase("N")) {

                    } else {
                        area.setHorario(nuevo_horario);
                    }
                    if (nueva_persona_a_cargo.equalsIgnoreCase("N")) {

                    } else {
                        area.setPersona_a_cargo(nueva_persona_a_cargo);
                    }
                    if (nuevo_telefono_persona_a_cargo.equalsIgnoreCase("N")) {

                    } else {
                        area.setTelefono_persona_a_cargo(nuevo_telefono_persona_a_cargo);
                    }
                } else {

                }
            }
        }
        if (!AreaEncontrada) {
            System.out.println("No se encntró esa área");
        }
    }


    public static void EliminarAreas() {
        if (areas.isEmpty()) {
            System.out.println("No hay areas registradas");
            return;
        }
        System.out.println("Se seleccionará por telefono de la persona a cargo");
        System.out.println("Ingrese el telefono de la persona a cargo del area a eliminar");
        String telefono_persona_a_cargo = input.next();
        System.out.println("¿Seguro que desea eliminar esta área?");
        System.out.println("Y");
        System.out.println("N");
        String option = input.next();
        boolean AreaEncontrada = false;
        if (option.equalsIgnoreCase("Y")) {
            Iterator<Area> iterator = areas.iterator();
            while(iterator.hasNext()) {
                Area area = iterator.next();
                if (area.getTelefono_persona_a_cargo().equalsIgnoreCase(telefono_persona_a_cargo)) {
                    AreaEncontrada = true;
                    iterator.remove();
                }
            }

            if (!AreaEncontrada) {
                System.out.println("No se encontró esa área");
            }
        }
    }


    public static void AdministrarTalleres() {
        String option = "";
        while(true) {
            System.out.println("Elija una opción");
            System.out.println("1. Ver Talleres");
            System.out.println("2. Crear Taller");
            System.out.println("3. Editar Taller");
            System.out.println("4. Eliminar Taller");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                if (talleres.isEmpty()) {
                    System.out.println("No hay talleres registrados");
                } else {
                    for (Taller taller : talleres) {
                        System.out.println("************************************");
                        System.out.println("Nombre: " + taller.nombre);
                        System.out.println("Sistema asociado: " + taller.sistema_asociado);
                        System.out.println("Tipo: " + taller.tipo);
                        System.out.println("Dinero fallas menores: " + taller.dinero_fallas_menores);
                        System.out.println("************************************");
                    }
                }
            } else if (option.equals("2")) {
                CrearTalleres();
            } else if (option.equals("3")) {
                EditarTalleres();
            } else if (option.equals("4")) {
                EliminarTalleres();
            } else if (option.equals("0")) {
                break;
            }
        }
    }


    public static void CrearTalleres() {
        if (areas.isEmpty()) {
            System.out.println("No hay areas registradas, por lo tanto no puede crear el taller");
            return;
        }
        System.out.println("CREANDO TALLER");
        System.out.println("Ingrese el nombre del taller");
        String nombre = input.next();
        for (Taller taller : talleres) {
            if (taller.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("El taller ya existe, intente de nuevo");
                return;
            }
        }

        System.out.println("Ingrese el sistema asociado al taller");
        String sistema_asociado = input.next();
        System.out.println("Ingrese el tipo");
        String tipo = input.next();
        System.out.println("¿El taller repara en ruta?");
        System.out.println("Y");
        System.out.println("N");
        String option = input.next();
        boolean reparar_en_ruta;
        if (option.equalsIgnoreCase("Y")) {
            reparar_en_ruta = true;

        } else {
            reparar_en_ruta = false;
        }
        System.out.println("Ingrese la cantidad de dinero en fallas menores");
        int dinero_fallas_menores = input.nextInt();
        System.out.println("Ingrese el telefono de la persona a cargo del area a asociar este taller");
        String telefono_persona_a_cargo_area = input.next();
        Taller taller = new Taller(nombre,sistema_asociado,tipo,reparar_en_ruta,dinero_fallas_menores);
        boolean AreaEncontrada = false;
        for (Area area : areas) {
            if (area.getTelefono_persona_a_cargo().equalsIgnoreCase(telefono_persona_a_cargo_area)) {
                AreaEncontrada = true;
                area.setTalleres(taller);
                break;
            }
        }
        if (!AreaEncontrada) {
            System.out.println("No exite tal area para asociar el taller");
            return;
        }

/*
        while(true) {
            System.out.println("¿Este taller repara algún recolector");
            System.out.println("Y");
            System.out.println("N");
            option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                System.out.println("Ingrese el id del recolector que repara");
                int id_recolector = input.nextInt();
                boolean RecolectorEncontrado = false;
                for (Recolector recolector : recolectores) {
                    if (recolector.getId() == id_recolector) {
                        RecolectorEncontrado = true;
                        recolector.setTalleres(taller);
                        break;
                    }
                }
                if (!RecolectorEncontrado) {
                    System.out.println("No existe ese recolector, intente de nuevo");
                } else {
                    break;
                }
            } else if (option.equalsIgnoreCase("N")) {
                break;
            }
        }
*/


        talleres.add(taller);
        System.out.println("Taller creado satisfactoriamente");
    }


    public static void EditarTalleres() {
        if (talleres.isEmpty()) {
            System.out.println("No hay talleres registrados");
            return;
        }
        System.out.println("Se seleccionará por nombre del taller");
        System.out.println("Ingrese el nombre");
        String nombre= input.next();
        String nuevo_nombre = "";
        String nuevo_sistema_asociado = "";
        String nuevo_tipo = "";
        String nuevo_reparar_en_ruta = "";
        String option = "";
        int nuevo_dinero_fallas_menores = 0;
        boolean TallerEncontrado = false;
        for (Taller taller : talleres) {
            if (taller.getNombre().equalsIgnoreCase(nombre)) {
                TallerEncontrado = true;
                System.out.println("Nombre: "+ taller.getNombre());
                System.out.println("Si desea cambiar el nombre, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_nombre = input.next();
                for (Taller taller1 : talleres) {
                    if (taller1.getNombre().equalsIgnoreCase(nuevo_nombre)) {
                        System.out.println("El taller ya existe, intente de nuevo");
                        return;
                    }
                }
                System.out.println("Sistema asociado: "+ taller.getSistema_asociado());
                System.out.println("Si desea cambiar el sistema asociado, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_sistema_asociado = input.next();
                System.out.println("Tipo: "+ taller.getTipo());
                System.out.println("Si desea cambiar el tipo, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_tipo = input.next();
                System.out.println("Reparar en ruta: "+ taller.isReparar_en_ruta());
                System.out.println("¡Desea cambiar el estado de reparar en ruta?");
                System.out.println("Y");
                System.out.println("N");
                nuevo_reparar_en_ruta = input.next();
                System.out.println("Dinero fallas menores: "+ taller.getDinero_fallas_menores());
                System.out.println("Si desea cambiar esa cantidad de dinero, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: -1");
                nuevo_dinero_fallas_menores = input.nextInt();
                System.out.println("¿Desea guardar los cambios?");
                System.out.println("Y");
                System.out.println("N");
                option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    if (nuevo_nombre.equalsIgnoreCase("N")) {

                    } else {
                        taller.setNombre(nuevo_nombre);
                    }
                    if (nuevo_reparar_en_ruta.equalsIgnoreCase("N")) {

                    } else {
                        taller.setReparar_en_ruta(!taller.reparar_en_ruta);
                    }
                    if (nuevo_sistema_asociado.equalsIgnoreCase("N")) {

                    } else {
                        taller.setSistema_asociado(nuevo_sistema_asociado);
                    }
                    if (nuevo_tipo.equalsIgnoreCase("N")) {

                    } else {
                        taller.setTipo(nuevo_tipo);
                    }
                    if (nuevo_dinero_fallas_menores < 0) {

                    } else {
                        taller.setDinero_fallas_menores(nuevo_dinero_fallas_menores);
                    }
                } else {

                }
            }
        }
        if (!TallerEncontrado) {
            System.out.println("No se encntró ese taller");
        }
    }


    public static void EliminarTalleres() {
        if (talleres.isEmpty()) {
            System.out.println("No hay talleres registrados");
            return;
        }
        System.out.println("Se seleccionará por nombre del taller");
        System.out.println("Ingrese el nombre");
        String nombre= input.next();
        System.out.println("¿Seguro que desea eliminar este taller?");
        System.out.println("Y");
        System.out.println("N");
        String option = input.next();
        boolean TallerEncontrado = false;
        if (option.equalsIgnoreCase("Y")) {
            Iterator<Taller> iterator = talleres.iterator();
            while(iterator.hasNext()) {
                Taller taller = iterator.next();
                if (taller.getNombre().equalsIgnoreCase(nombre)) {
                    TallerEncontrado = true;
                    iterator.remove();
                }
            }

            if (!TallerEncontrado) {
                System.out.println("No se encontró ese taller");
            }
        }
    }


    public static void AdministrarRutas() {
        String option = "";
        while(true) {
            System.out.println("Elija una opción");
            System.out.println("1. Ver Rutas");
            System.out.println("2. Crear Ruta");
            System.out.println("3. Editar Ruta");
            System.out.println("4. Eliminar Ruta");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                if (rutas.isEmpty()) {
                    System.out.println("No hay rutas registradas");
                } else {
                    for (Ruta ruta : rutas) {
                        System.out.println("************************************");
                        System.out.println("id: " + ruta.id);
                        System.out.println("Horario: " + ruta.horario);
                        System.out.println("dia: " + ruta.dia);
                        System.out.println("Id recolercor: " + ruta.recolector_id);
                        System.out.println("************************************");
                    }
                }
            } else if (option.equals("2")) {
                CrearRutas();
            } else if (option.equals("3")) {
                EditarRutas();
            } else if (option.equals("4")) {
                EliminarRutas();
            } else if (option.equals("0")) {
                break;
            }
        }
    }


    public static void CrearRutas() {
        if (areas.isEmpty()){
            System.out.println("No hay areas registradas para asignar ruta");
            return;
        }
        if (recolectores.isEmpty()) {
            System.out.println("No hay recolectores registrados para asignar ruta");
            return;
        }
        System.out.println("CREANDO RUTA");
        System.out.println("Ingrese el id de la ruta");
        int id_ruta = input.nextInt();
        for (Ruta ruta :rutas) {
            if (ruta.getId() == id_ruta) {
                System.out.println("Ya existe una ruta con ese id");
                return;
            }
        }
        System.out.println("Ingrese el horario de la ruta");
        String horario = input.next();
        System.out.println("Ingrese el dia de la ruta");
        String dia = input.next();
        System.out.println("¿El recolector de esa ruta está varado?");
        System.out.println("Y");
        System.out.println("N");
        String option = input.next();
        boolean recolector_varado;
        if (option.equalsIgnoreCase("Y")) {
            recolector_varado = true;

        } else {
            recolector_varado = false;
        }
        System.out.println("Ingrese el id del recolector asociado");
        int id_recolector = input.nextInt();
        Ruta ruta = new Ruta(id_ruta,horario,dia,id_recolector, recolector_varado);
        boolean RecolectorEncontrado = false;
        for (Recolector recolector : recolectores) {
            if (recolector.getId() == id_recolector) {
                RecolectorEncontrado = true;
                recolector.setRuta(ruta);
                break;
            }
        }
        if (!RecolectorEncontrado) {
            System.out.println("No exite tal recolector para asociar la ruta");
            return;
        }
        System.out.println("Ingrese el telefono de la persona a cargo del area a asociar esta ruta");
        String telefono_persona_a_cargo_area = input.next();
        boolean AreaEncontrada = false;
        for (Area area : areas) {
            if (area.getTelefono_persona_a_cargo().equalsIgnoreCase(telefono_persona_a_cargo_area)) {
                AreaEncontrada = true;
                area.setRutas(ruta);
                break;
            }
        }
        if (!AreaEncontrada) {
            System.out.println("No exite tal area para asociar la ruta");
            return;
        }

        // Si el recolector está varado, entonces debe ser enviado a un taller para repararlo
        // se añade a una cola en ese taller, y en taller añadir método de atender recolector
        // qué irá atendiendo en orden  de llegada
        if (recolector_varado) {
            //Buscamos en recolectores el recolector que tenga el id de esta ruta
            for (Recolector recolector : recolectores) {
                if (recolector.getRutaid() == id_ruta) {
                    //Preguntar el nombre del taller al que desea llevarlo
                    for (Taller taller : talleres) {
                        System.out.println(taller.toString());
                    }
                    System.out.println("¿A que taller desea llevar el recolector varado?");
                    String nombreTaller = input.next();
                    for (Taller taller : talleres) {
                        if (taller.getNombre().equalsIgnoreCase(nombreTaller)) {
                            taller.setRecolectoresVarados(recolector);
                            break;
                        }
                    }
                    break;
                }
            }
        }

        rutas.add(ruta);
        System.out.println("Ruta creada satisfactoriamente");
    }


    public static void EditarRutas() {
        if (rutas.isEmpty()) {
            System.out.println("No hay rutas registradas");
            return;
        }
        System.out.println("Se seleccionará por id de ruta");
        System.out.println("Ingrese el id de la ruta");
        int id_ruta = input.nextInt();
        int nuevo_id_ruta = 0;
        String nuevo_horario = "";
        String nuevo_dia = "";
        String nuevo_estado_recolector = "";
        String option = "";
        boolean RutaEncontrada = false;
        for (Ruta ruta : rutas) {
            if (ruta.getId() == id_ruta) {
                RutaEncontrada = true;
                System.out.println("Id: "+ ruta.getId());
                System.out.println("Si desea cambiar el id, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: -1");
                nuevo_id_ruta = input.nextInt();
                for (Ruta ruta1 :rutas) {
                    if (ruta1.getId() == nuevo_id_ruta) {
                        System.out.println("Ya existe una ruta con ese id");
                        return;
                    }
                }
                System.out.println("Horario: "+ ruta.getHorario());
                System.out.println("Si desea cambiar el horario, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_horario = input.next();
                System.out.println("Dia: "+ ruta.getDia());
                System.out.println("Si desea cambiar el dia, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_dia = input.next();
                System.out.println("Estado recolector: "+ ruta.isEstado_recolector());
                System.out.println("¡Desea cambiar el estado del recolector?");
                System.out.println("Y");
                System.out.println("N");
                nuevo_estado_recolector = input.next();
                System.out.println("¿Desea guardar los cambios?");
                System.out.println("Y");
                System.out.println("N");
                option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    if (nuevo_id_ruta < 0) {

                    } else {
                        ruta.setId(nuevo_id_ruta);
                    }
                    if (nuevo_horario.equalsIgnoreCase("N")) {

                    } else {
                        ruta.setHorario(nuevo_horario);
                    }
                    if (nuevo_dia.equalsIgnoreCase("N")) {

                    } else {
                        ruta.setDia(nuevo_dia);
                    }
                    if (nuevo_estado_recolector.equalsIgnoreCase("N")) {

                    } else {
                        ruta.setEstado_recolector(!ruta.estado_recolector);
                    }
                } else {

                }
            }
        }
        if (!RutaEncontrada) {
            System.out.println("No se encntró la ruta");
        }
    }


    public static void EliminarRutas() {
        if (rutas.isEmpty()) {
            System.out.println("No hay rutas registrados");
            return;
        }
        System.out.println("Se seleccionará por id de la rutas");
        System.out.println("Ingrese el id de la ruta");
        int id_ruta = input.nextInt();
        System.out.println("¿Seguro que desea eliminar esta ruta?");
        System.out.println("Y");
        System.out.println("N");
        String option = input.next();
        boolean RutaEncontrada = false;
        if (option.equalsIgnoreCase("Y")) {
            Iterator<Ruta> iterator = rutas.iterator();
            while(iterator.hasNext()) {
                Ruta ruta = iterator.next();
                if (ruta.getId() == id_ruta) {
                    RutaEncontrada = true;
                    iterator.remove();
                }
            }

            if (!RutaEncontrada) {
                System.out.println("No se encontró esa ruta");
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
                if (personas.isEmpty()) {
                    System.out.println("No hay personal registrado");
                } else {
                    for (Personal persona : personas) {
                        System.out.println("************************************");
                        System.out.println("Nombre: " + persona.nombre);
                        System.out.println("Perfil: " + persona.perfil);
                        System.out.println("Horario: " + persona.horario);
                        System.out.println("Cedula: " + persona.cedula);
                        System.out.println("Sueldo: " + persona.sueldo);
                        System.out.println("************************************");
                    }
                }
            } else if (option.equals("2")) {
                CrearPersonal();
            } else if (option.equals("3")) {
                EditarPersonal();
            } else if (option.equals("4")) {
                EliminarPersonal();
            } else if (option.equals("0")) {
                break;
            }
        }
    }


    public static void CrearPersonal() {
        if (areas.isEmpty()) {
            System.out.println("No hay areas registradas, por lo tanto no puede asignar personal");
            return;
        }
        System.out.println("CREANDO PERSONA");
        System.out.println("Ingrese la cedula de persona");
        String cedula1 = input.next();
        int cedula = Integer.parseInt(quitarPuntosDocumentos(cedula1));      // quitar puntos de cedula
        for (Personal persona : personas) {
            if (persona.getCedula() == cedula) {
                System.out.println("Esta persona ya existe, intente nuevamente");
                return;
            }
        }
        System.out.println("Ingrese el nombre de la persona");
        String nombre =input.next();
        System.out.println("Ingrese el perfil de la persona");
        String perfil = input.next();
        System.out.println("Ingrese el horario");
        String horario = input.next();
        System.out.println("Ingrese el sueldo de la persona");
        int sueldo = input.nextInt();

        Personal persona = new Personal(perfil,horario,cedula,nombre,sueldo);
        System.out.println("Ingrese el teléfono de la persona a cargo del area a asociar esta persona");
        String telefono_persona_a_cargo_area = input.next();

        boolean AreaEncontrada = false;
        for (Area area : areas) {
            if (area.getTelefono_persona_a_cargo().equalsIgnoreCase(telefono_persona_a_cargo_area)) {
                AreaEncontrada = true;
                area.setPersonas(persona);
                break;
            }
        }
        if (!AreaEncontrada) {
            System.out.println("No exite tal area para asociar la persona");
            return;
        }
        while(true) {
            System.out.println("¿Esta persona trabaja en algún taller?");
            System.out.println("Y");
            System.out.println("N");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                System.out.println("Ingrese el nombre del taller en el que trabaja");
                String nombre_taller = input.next();
                boolean TallerEncontrado = false;
                for (Taller taller : talleres) {
                    if (taller.getNombre().equalsIgnoreCase(nombre_taller)) {
                        TallerEncontrado = true;
                        taller.setPersonas(persona);
                        break;
                    }
                }
                if (!TallerEncontrado) {
                    System.out.println("No existe ese taller, intente de nuevo");
                } else {
                    break;
                }
            } else if (option.equalsIgnoreCase("N")) {
                break;
            }
        }


       /* while(true) {
            System.out.println("¿Esta persona trabaja en algún recolector?");
            System.out.println("Y");
            System.out.println("N");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                System.out.println("Ingrese el id del recolector donde trabaja");
                int id_recolector = input.nextInt();
                boolean RecolectorEncontrado = false;
                for (Recolector recolector : recolectores) {
                    if (recolector.getId() == id_recolector) {
                        RecolectorEncontrado = true;
                        recolector.setPersonas(persona);
                        break;
                    }
                }
                if (!RecolectorEncontrado) {
                    System.out.println("No existe ese recolector, intente de nuevo");
                } else {
                    break;
                }
            } else if (option.equalsIgnoreCase("N")) {
                break;
            }
        }*/

        personas.add(persona);
        System.out.println("Persona creada satisfactoriamente");
    }


    public static void EditarPersonal() {
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas");
            return;
        }
        System.out.println("Se seleccionará por cédula");
        System.out.println("Ingrese la cédula de la persona");
        String cedula1 = input.next();
        int cedula = Integer.parseInt(quitarPuntosDocumentos(cedula1));
        int nueva_cedula = 0;
        String nuevo_perfil = "";
        String nuevo_horario = "";
        String nuevo_nombre = "";
        int nuevo_sueldo = 0;
        boolean personaEncontrada = false;
        for (Personal persona : personas) {
            if (persona.getCedula() == cedula) {
                personaEncontrada = true;
                System.out.println("Nombre: "+ persona.getNombre());
                System.out.println("Si desea cambiar el nombre, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_nombre = input.next();
                System.out.println("Cédula: "+ persona.getCedula());
                System.out.println("Si desea cambiar la cédula, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: 0");
                nueva_cedula = input.nextInt();
                for (Personal persona1 : personas) {
                    if (persona1.getCedula() == nueva_cedula) {
                        System.out.println("Esta persona ya existe, intente nuevamente");
                        return;
                    }
                }
                System.out.println("Perfil: "+ persona.getPerfil());
                System.out.println("Si desea cambiar el perfil, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_perfil = input.next();
                System.out.println("Horaio: "+ persona.getHorario());
                System.out.println("Si desea cambiar el horario, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_horario = input.next();
                System.out.println("Sueldo: "+ persona.getSueldo());
                System.out.println("Si desea cambiar el sueldo, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: 0");
                nuevo_sueldo = input.nextInt();
                System.out.println("¿Desea guardar los cambios?");
                System.out.println("Y");
                System.out.println("N");
                String option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    if (nuevo_nombre.equalsIgnoreCase("N")) {

                    } else {
                        persona.setNombre(nuevo_nombre);
                    }
                    if (nueva_cedula == 0) {

                    } else {
                        persona.setCedula(nueva_cedula);
                    }
                    if (nuevo_perfil.equalsIgnoreCase("N")) {

                    } else {
                        persona.setPerfil(nuevo_perfil);
                    }
                    if (nuevo_horario.equalsIgnoreCase("N")) {

                    } else {
                        persona.setHorario(nuevo_horario);
                    }
                    if (nuevo_sueldo == 0) {

                    } else {
                        persona.setSueldo(nuevo_sueldo);
                    }
                } else {

                }
            }
        }
        if (!personaEncontrada) {
            System.out.println("No se encontró la persona");
        }
    }


    public static void EliminarPersonal() {
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas");
            return;
        }
        System.out.println("Se seleccionará por cédula");
        System.out.println("Ingrese la cédula de la persona");
        int cedula = input.nextInt();
        System.out.println("¿Seguro que desea eliminar esta persona?");
        System.out.println("Y");
        System.out.println("N");
        String option = input.next();
        boolean personaEncontrada = false;
        if (option.equalsIgnoreCase("Y")) {
            Iterator<Personal> iterator = personas.iterator();
            while(iterator.hasNext()) {
                Personal persona = iterator.next();
                if (persona.getCedula() == cedula) {
                    personaEncontrada = true;
                    iterator.remove();
                }
            }

            if (!personaEncontrada) {
                System.out.println("No se encontró la persona");
            }
        }
    }


    public static void AdministrarRecolectores() {
        String option = "";
        while(true) {
            System.out.println("Elija una opción");
            System.out.println("1. Ver Recolectores");
            System.out.println("2. Crear Recolector");
            System.out.println("3. Editar Recolector");
            System.out.println("4. Eliminar Recolector");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                if (recolectores.isEmpty()) {
                    System.out.println("No hay recolectores registrados");
                } else {
                    for (Recolector recolector : recolectores) {
                        System.out.println("************************************");
                        System.out.println("Marca: " + recolector.marca);
                        System.out.println("Id: " + recolector.id);
                        System.out.println("Ruta Id: " + recolector.rutaid);
                        System.out.println("************************************");
                    }
                }
            } else if (option.equals("2")) {
                CrearRecolector();
            } else if (option.equals("3")) {
                EditarRecolector();
            } else if (option.equals("4")) {
                EliminarRecolector();
            } else if (option.equals("0")) {
                break;
            }
        }
    }


    public static void CrearRecolector() {
        if (areas.isEmpty()) {
            System.out.println("No hay areas registradas, por lo tanto no puede asignar recolectores");
            return;
        }
        if (personas.isEmpty()) {
            System.out.println("No hay personal registrado, por lo tanto no puede asignar recolectores");
            return;
        }
        System.out.println("CREANDO RECOLECTOR");
        System.out.println("Ingrese el Id del recolector");
        int id_recolector = input.nextInt();
        for (Recolector recolector : recolectores) {
            if (recolector.getId() == id_recolector) {
                System.out.println("Este recolector ya existe, intente nuevamente");
                return;
            }
        }
        System.out.println("Ingrese la marca del recolector");
        String marca =input.next();
        System.out.println("El recolector es doble troque?");
        System.out.println("Y");
        System.out.println("N");
        String option = input.next();
        boolean doble_troque;
        if (option.equalsIgnoreCase("Y")) {
            doble_troque = true;
        } else {
            doble_troque = false;
        }
        System.out.println("El recolector está disponible?");
        System.out.println("Y");
        System.out.println("N");
        option = input.next();
        boolean disponible;
        if (option.equalsIgnoreCase("Y")) {
            disponible = true;
        } else {
            disponible = false;
        }
        System.out.println("Ingrese el id de la ruta asociada");
        int id_ruta = input.nextInt();
        Recolector recolector = new Recolector(marca,doble_troque,id_recolector,disponible,id_ruta);

/*        boolean RutaEncontrada = false;
        for (Ruta ruta :rutas) {
            if (ruta.getId() == id_ruta) {
                RutaEncontrada = true;
                ruta.setRecolector(recolector);
                break;
            }
        }
        if (!RutaEncontrada) {
            System.out.println("No exite tal ruta para asociar al recolector");
        }*/
/*        System.out.println("Ingrese el nombre del taller a asociar este recolector");
        String nombre_taller = input.next();
        boolean TallerEncontrada = false;
        for (Taller taller : talleres) {
            if (taller.getNombre().equalsIgnoreCase(nombre_taller)) {
                TallerEncontrada = true;
                taller.setRecolectores(recolector);
                break;
            }
        }
        if (!TallerEncontrada) {
            System.out.println("No exite tal taller para asociar al recolector");
            return;
        }*/

        while(true) {
            System.out.println("¿El recolector tiene personal asociado?");
            System.out.println("Y");
            System.out.println("N");
            option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                System.out.println("Ingrese la cédula de la persona");
                String cedula1 = input.next();
                int cedula_persona = Integer.parseInt(quitarPuntosDocumentos(cedula1));
                boolean PersonalEncontrado = false;
                for (Personal persona : personas) {
                    if (persona.getCedula() == cedula_persona) {
                        PersonalEncontrado = true;
                        persona.setRecolectores(recolector);
                        break;
                    }
                }
                if (!PersonalEncontrado) {
                    System.out.println("No existe esa persona, intente de nuevo");
                } else {
                    break;
                }
            } else if (option.equalsIgnoreCase("N")) {
                break;
            }
        }

        recolectores.add(recolector);
        System.out.println("Recolector creado satisfactoriamente");
    }


    public static void EditarRecolector() {
        if (recolectores.isEmpty()) {
            System.out.println("No hay recolectores registrados");
            return;
        }
        System.out.println("Se seleccionará por id de recolector");
        System.out.println("Ingrese el id del recolector");
        int id_recolector = input.nextInt();
        int nuevo_id_recolector = 0;
        String nuevo_doble_troque = "";
        String nueva_marca = "";
        String nuevo_disponible = "";
        String option = "";
        boolean RecolectorEncontrado = false;
        for (Recolector recolector : recolectores) {
            if (recolector.getId() == id_recolector) {
                RecolectorEncontrado = true;
                System.out.println("Id: "+ recolector.getId());
                System.out.println("Si desea cambiar el id, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: -1");
                nuevo_id_recolector = input.nextInt();
                for (Recolector recolector1 : recolectores) {
                    if (recolector1.getId() == nuevo_id_recolector) {
                        System.out.println("Este recolector ya existe, intente nuevamente");
                        return;
                    }
                }
                System.out.println("Marca: "+ recolector.getMarca());
                System.out.println("Si desea cambiar la marca, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nueva_marca = input.next();
                System.out.println("Recolector doble troque: "+ recolector.isDoble_troque());
                System.out.println("¡Desea cambiar el estado del recolector?");
                System.out.println("Y");
                System.out.println("N");
                nuevo_doble_troque = input.next();
                System.out.println("Recolector disponible: "+ recolector.isDisponible());
                System.out.println("¡Desea cambiar el estado del recolector?");
                System.out.println("Y");
                System.out.println("N");
                nuevo_disponible = input.next();
                System.out.println("¿Desea guardar los cambios?");
                System.out.println("Y");
                System.out.println("N");
                option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    if (nuevo_id_recolector < 0) {

                    } else {
                        recolector.setId(nuevo_id_recolector);
                    }
                    if (nueva_marca.equalsIgnoreCase("N")) {

                    } else {
                       recolector.setMarca(nueva_marca);
                    }
                    if (nuevo_doble_troque.equalsIgnoreCase("N")) {

                    } else {
                       recolector.setDoble_troque(!recolector.doble_troque);
                    }
                    if (nuevo_disponible.equalsIgnoreCase("N")) {

                    } else {
                        recolector.setDisponible(!recolector.disponible);
                    }
                } else {

                }
            }
        }
        if (!RecolectorEncontrado) {
            System.out.println("No se encntró el recolector");
        }

    }


    public static void EliminarRecolector() {
        if (recolectores.isEmpty()) {
            System.out.println("No hay recolectores registrados");
            return;
        }
        System.out.println("Se seleccionará por id del recolector");
        System.out.println("Ingrese el id");
        int id_recolector = input.nextInt();
        System.out.println("¿Seguro que desea eliminar este recolector?");
        System.out.println("Y");
        System.out.println("N");
        String option = input.next();
        boolean RecolectorEncontrado = false;
        if (option.equalsIgnoreCase("Y")) {
            Iterator<Recolector> iterator =     recolectores.iterator();
            while(iterator.hasNext()) {
                Recolector recolector = iterator.next();
                if (recolector.getId() == id_recolector) {
                    RecolectorEncontrado = true;
                    iterator.remove();
                }
            }

            if (!RecolectorEncontrado) {
                System.out.println("No se encontró el recolector");
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
            System.out.println("4. Talleres");
            System.out.println("5. Personal");
            System.out.println("6. Recolectores");
            System.out.println("7. Rutas");
            System.out.println("0. Salir");
            option = input.next();
            if (option.equals("1")) {
                BuscarEmpresasBasura();
            } else if (option.equals("2")) {
                BuscarSedes();
            } else if (option.equals("3")) {
                BuscarAreas();
            } else if (option.equals("4")) {
                BuscarTalleres();
            } else if (option.equals("5")) {
                BuscarPersonal();
            } else if (option.equals("6")) {
                BuscarRecolectores();
            } else if (option.equals("7")) {
                BuscarRutas();
            } else if (option.equals("0")) {
                break;
            }
        }
    }

    public static String atributo_integer_buscar() {
        System.out.println("Desea buscar por: ");
        System.out.println("1. Valor exacto");
        System.out.println("2. Valor minimo");
        System.out.println("3. Valor maximo");
        System.out.println("4. Valor rango");
        String seleccion= input.next();
        return seleccion;
    }

    public static void BuscarRecolectores() {
        LinkedList<Recolector> recolectores_copia= new LinkedList<>();
        recolectores_copia= (LinkedList<Recolector>) recolectores.clone();
        LinkedList<Recolector> recolectoresEncontrados= new LinkedList<>();
        int enumerador=1;
        boolean mostrar_todos=false;

        if(recolectores.size()!=0){
            System.out.println("Seleccione el atributo por el cual desea buscar los recolectores:");
            System.out.println("1. Marca");
            System.out.println("2. Identificacion");
            System.out.println("3. Identificacion de ruta");
            System.out.println("4. Mostrar todos los talleres");
            String seleccion= input.next();
            int seleccion_int;
            if(seleccion.equals("1")){

                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese la marca del recolector: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Recolector recolector : recolectores) {

                        if(recolector.marca.equals(seleccion)){
                            recolectoresEncontrados.add(recolector);
                        }
                    }

                }else if(seleccion.equals("2")){

                    System.out.print("Ingrese la marca del recolector: ");
                    input.nextLine();
                    seleccion= input.nextLine();

                    for (Recolector recolector : recolectores) {
                        
                        if(recolector.marca.toLowerCase().equals(seleccion)){
                            recolectoresEncontrados.add(recolector);
                        }
                    }

                

                }else{
                    System.out.println("Opción inválida");
                    return;
                }

               
            }else if(seleccion.equals("2")){

                seleccion=atributo_integer_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese la identificacion del recolector: ");
                    seleccion_int= input.nextInt();
                    

                    for (Recolector recolector : recolectores) {

                        if(recolector.id==seleccion_int){
                            recolectoresEncontrados.add(recolector);
                        }
                    }

                }else if(seleccion.equals("2")){

                    System.out.print("Ingrese la identificacion del recolector: ");
                    seleccion_int= input.nextInt();

                    for (Recolector recolector : recolectores) {
                        
                        if(recolector.id>=seleccion_int){
                            recolectoresEncontrados.add(recolector);
                        }
                    }

                }else if(seleccion.equals("3")){
                    System.out.print("Ingrese la identificacion del recolector: ");
                    seleccion_int= input.nextInt();

                    for (Recolector recolector : recolectores) {
                        
                        if(recolector.id<=seleccion_int){
                            recolectoresEncontrados.add(recolector);
                        }
                    }
                }else if(seleccion.equals("4")){
                    System.out.print("Ingrese el limite inferior de la identificacion: ");
                    seleccion_int= input.nextInt();
                    System.out.print("Ingrese el limite superior de la identificacion: ");
                    int seleccion_int2= input.nextInt();

                    int aux;

                    if(seleccion_int>seleccion_int2){
                        aux=seleccion_int;
                        seleccion_int=seleccion_int2;
                        seleccion_int2= aux;
                    }
                    
                    for (Recolector recolector: recolectores) {
                        
                        if(recolector.id>=seleccion_int && recolector.id<=seleccion_int2){
                            recolectoresEncontrados.add(recolector);
                        }
                    }

                    
                }else{
                    System.out.println("Opción inválida");
                    return;
                }

            }else if(seleccion.equals("3")){

                seleccion=atributo_integer_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese la identificacion de la ruta: ");
                    seleccion_int= input.nextInt();
                    

                    for (Recolector recolector : recolectores) {

                        if(recolector.rutaid==seleccion_int){
                            recolectoresEncontrados.add(recolector);
                        }
                    }

                }else if(seleccion.equals("2")){

                    System.out.print("Ingrese la identificacion de la ruta: ");
                    seleccion_int= input.nextInt();

                    for (Recolector recolector : recolectores) {
                        
                        if(recolector.rutaid>=seleccion_int){
                            recolectoresEncontrados.add(recolector);
                        }
                    }

                }else if(seleccion.equals("3")){
                    System.out.print("Ingrese la identificacion de la ruta: ");
                    seleccion_int= input.nextInt();

                    for (Recolector recolector : recolectores) {
                        
                        if(recolector.rutaid<=seleccion_int){
                            recolectoresEncontrados.add(recolector);
                        }
                    }
                }else if(seleccion.equals("4")){
                    System.out.print("Ingrese el limite inferior de la identificacion de la ruta: ");
                    seleccion_int= input.nextInt();
                    System.out.print("Ingrese el limite superior de la identificacion de la ruta: ");
                    int seleccion_int2= input.nextInt();

                    int aux;

                    if(seleccion_int>seleccion_int2){
                        aux=seleccion_int;
                        seleccion_int=seleccion_int2;
                        seleccion_int2= aux;
                    }
                    
                    for (Recolector recolector : recolectores) {
                        
                        if(recolector.rutaid>=seleccion_int && recolector.rutaid<=seleccion_int2){
                            recolectoresEncontrados.add(recolector);
                        }
                    }

                    
                }else{
                    System.out.println("Opción inválida");
                    return;
                }



            }else if(seleccion.equals("4")){
                mostrar_todos=true;
                System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                System.out.println("1. Marca");
                System.out.println("2. Identificacion");
                System.out.println("3. Identificacion de la ruta");
                seleccion= input.next();



                if(seleccion.equals("1")){
                    System.out.println("Desea organizar la marca de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(recolectores_copia, new ComparadorRecolector_marcaA());
                    } else if (seleccion.equals("2")) {
                        Collections.sort(recolectores_copia, new ComparadorRecolector_marcaD());
                    }

                    enumerador=1;
                    for (Recolector recolector : recolectores_copia) {
                        System.out.println(enumerador +". "+ recolector);
                        enumerador+=1;
                    }
                }else if(seleccion.equals("2")){
                    System.out.println("Desea organizar las identificaciones de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();     
                    if(seleccion.equals("1")){

                        Collections.sort(recolectores_copia, new ComparadorRecolector_idA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(recolectores_copia, new ComparadorRecolector_idD());
                    }       

                    enumerador=1;

                    for (Recolector recolector : recolectores_copia) { 
                        System.out.println(enumerador+". "+ recolector);
                        enumerador+=1;
                    }

                }else if(seleccion.equals("3")){
                    System.out.println("Desea organizar la identificacion de las rutas de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(recolectores_copia, new ComparadorRecolector_idRutaA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(recolectores_copia,new ComparadorRecolector_idRutaD());
                    }

                    enumerador=1;
                    for (Recolector recolector : recolectores_copia) {
                        System.out.println(enumerador +". "+ recolector);
                        enumerador+=1;
                    }
                


                }else{

                    System.out.println("Opción inválida");
                    return;
                }
            }

        }else{
            System.out.println("No hay recolectores registrados en el sistema");
            return;
        }


        if(recolectoresEncontrados.size()!=0 && !mostrar_todos) {
            System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
            System.out.println("1. Marca");
            System.out.println("2. Identificacion");
            System.out.println("3. Identificacion de la ruta");
            String seleccion= input.next();

            if(seleccion.equals("1")){
                System.out.println("Desea organizar la marca de forma : ");
                System.out.println("1. Ascendente");
                System.out.println("2. Descendente");
                seleccion= input.next();

                if(seleccion.equals("1")){
                    Collections.sort(recolectoresEncontrados, new ComparadorRecolector_marcaA());
                }else if(seleccion.equals("2")){
                    Collections.sort(recolectoresEncontrados, new ComparadorRecolector_marcaD());
                }

                enumerador=1;
                for (Recolector recolector : recolectoresEncontrados) {
                    System.out.println(enumerador +". "+ recolector);
                    enumerador+=1;
                }
            }else if(seleccion.equals("2")){
                System.out.println("Desea organizar las identificaciones de forma : ");
                System.out.println("1. Ascendente");
                System.out.println("2. Descendente");
                seleccion= input.next();

                if(seleccion.equals("1")){
                    Collections.sort(recolectoresEncontrados, new ComparadorRecolector_idA());
                }else if(seleccion.equals("2")){
                    Collections.sort(recolectoresEncontrados, new ComparadorRecolector_idD());
                }             

                enumerador=1;

                for (Recolector recolector : recolectoresEncontrados) { 
                    System.out.println(enumerador+". "+ recolector);
                    enumerador+=1;
                }

            }else if(seleccion.equals("3")){
                System.out.println("Desea organizar las identificaciones de las rutas de forma : ");
                System.out.println("1. Ascendente");
                System.out.println("2. Descendente");
                seleccion= input.next();

                if(seleccion.equals("1")){
                    Collections.sort(recolectoresEncontrados, new ComparadorRecolector_idRutaA());
                }else if(seleccion.equals("2")){
                    Collections.sort(recolectoresEncontrados, new ComparadorRecolector_idRutaD());
                }

                enumerador=1;
                for (Recolector recolector : recolectoresEncontrados) {
                    System.out.println(enumerador +". "+ recolector);
                    enumerador+=1;
                }

           
            }else{

                System.out.println("Opción inválida");
                return;
            }
        }else{
            System.out.println("No se encontraron recolectores");
            return;
        }

        // Aquí se establece la búsqueda sobre las areas
        // Tener presente que se utilizará la función de Valentín de Editar y eliminar
        System.out.println("**********************************************");
        System.out.println("En los recolectores encontrados desea:");
        System.out.println("1. Editar");
        System.out.println("2. Eliminar");
        System.out.println("3. Salir");
        String seleccion= input.next();

        if(seleccion.equals("1")){
            System.out.print("Ingrese el número de la lista correspondiente al recolector: ");
            int option= input.nextInt();
            Recolector recolector;
            if(!mostrar_todos){
                if(recolectoresEncontrados.size()>=option && option>=1){
                    recolector= recolectoresEncontrados.get(option-1);  // Aquí obtienes el recolector a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }else{

                if(recolectores_copia.size()>=option && option>=1){
                    recolector= recolectores_copia.get(option-1);  // Aquí obtienes el recolector a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }

            
            BuscarEditarRecolector(recolector);    
            

        }else if(seleccion.equals("2")){
            System.out.print("Ingrese el número de la lista correspondiente al recolector: ");
            int option= input.nextInt();
            Recolector recolector;
            if(!mostrar_todos){
                if(recolectoresEncontrados.size()>=option && option>=1){
                    recolector= recolectoresEncontrados.get(option-1);  // Aquí obtienes la persona a eliminar 
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }else{

                if(recolectores_copia.size()>=option && option>=1){
                    recolector= recolectores_copia.get(option-1);  // Aquí obtienes la persona a eliminar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }
            // Aquí vas a implementar eliminar********************
            BuscarEliminarRecolector(recolector);
        }
    }

    public static void BuscarEliminarRecolector(Recolector place) {
        System.out.println("¿Seguro que desea eliminar el recolector?");
        System.out.println("Y");
        System.out.println("N");
        String option = input.next();
        boolean recolectorEncontrado = false;
        if (option.equalsIgnoreCase("Y")) {
            Iterator<Recolector> iterator = recolectores.iterator();
            while(iterator.hasNext()) {
                Recolector recolector = iterator.next();
                if (recolector.getId()==place.getId()) {
                    recolectorEncontrado = true;
                    iterator.remove();
                }
            }

            if (!recolectorEncontrado) {
                System.out.println("No se encontró el recolector");
                return;
            }else{
                System.out.println("El recolector se ha removido satisfactoriamente");
            }
        }
    }


    public static void BuscarEditarRecolector(Recolector place) {
        for (Recolector recolector: recolectores) {
            if(recolector.id==place.id){

                String nuevo_marca ="";
                String nuevo_id = "";
                String nuevo_rutaid = "";

                System.out.println("Marca del recolector: "+ recolector.getMarca());
                System.out.println("Si desea cambiar la marca, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_marca = input.next();
                System.out.println("Identificacion: "+ recolector.getId());
                System.out.println("Si desea cambiar la identificacion, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_id = input.next();
                System.out.println("Identificacion de la ruta: "+ recolector.getRutaid());
                System.out.println("Si desea cambiar la identificacion de la ruta, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_rutaid = input.next();
                
                System.out.println("¿Desea guardar los cambios?");
                System.out.println("Y");
                System.out.println("N");
                String option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    if (nuevo_marca.equalsIgnoreCase("N")) {
                        
                    }else {
                        
                        recolector.setMarca(nuevo_marca);
                    }

                   
                    if (nuevo_id.equalsIgnoreCase("N")) {

                    } else {
                        int foo;
                        try {
                            foo = Integer.parseInt(nuevo_id);
                         }
                         catch (NumberFormatException e)
                         {
                            foo = 0;
                         }
                        recolector.setId(foo);
                    }
                    if (nuevo_rutaid.equalsIgnoreCase("N")) {

                    } else {
                        int foo;
                        try {
                            foo = Integer.parseInt(nuevo_rutaid);
                         }
                         catch (NumberFormatException e)
                         {
                            foo = 0;
                         }
                        recolector.setRutaid(foo);
                    }



                }
                System.out.println("Se han realizado los cambios satisfactoriamente");
                break;
        
            }
        }
        
    }

    public static void BuscarPersonal() {
        LinkedList<Personal> personas_copia= new LinkedList<>();
        personas_copia= (LinkedList<Personal>) personas.clone();
        LinkedList<Personal> personasEncontradas= new LinkedList<>();
        int enumerador=1;
        boolean mostrar_todos=false;

        if(personas.size()!=0){
            System.out.println("Seleccione el atributo por el cual desea buscar al personal:");
            System.out.println("1. Perfil");
            System.out.println("2. Horario");
            System.out.println("3. Nombre");
            System.out.println("4. Cedula");
            System.out.println("5. Sueldo");
            System.out.println("6. Mostrar todos los talleres");
            String seleccion= input.next();
            int seleccion_int;
            if(seleccion.equals("1")){

                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese el perfil del personal: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Personal persona : personas) {

                        if(persona.perfil.equals(seleccion)){
                            personasEncontradas.add(persona);
                        }
                    }

                }else if(seleccion.equals("2")){

                    System.out.print("Ingrese el perfil del personal: ");
                    input.nextLine();
                    seleccion= input.nextLine();

                    for (Personal persona : personas) {
                        
                        if(persona.perfil.toLowerCase().equals(seleccion)){
                            personasEncontradas.add(persona);
                        }
                    }

                

                }else{
                    System.out.println("Opción inválida");
                    return;
                }

               

            }else if(seleccion.equals("2")){
                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese el horario del personal: ");
                    input.nextLine();
                    seleccion= input.nextLine();

                    for (Personal persona : personas) {

                        if(persona.horario.equals(seleccion)){
                            personasEncontradas.add(persona);
                        }
                    }


                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese el horario del personal: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Personal persona : personas) {

                        if(persona.horario.toLowerCase().equals(seleccion)){
                            personasEncontradas.add(persona);
                        }
                    }

                }else{
                    System.out.println("Opción inválida");
                    return;
                }

               


            }else if(seleccion.equals("3")){

                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese el nombre de la persona: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Personal persona : personas) {

                        if(persona.nombre.equals(seleccion)){
                            personasEncontradas.add(persona);
                        }
                    }

                    
                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese el nombre de la persona: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Personal persona : personas) {

                        if(persona.nombre.toLowerCase().equals(seleccion)){
                            personasEncontradas.add(persona);
                        }
                    }

                    
                }else{
                    System.out.println("Opción inválida");
                    return;
                }

            }else if(seleccion.equals("4")){

                seleccion=atributo_integer_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese la cedula de la persona: ");
                    seleccion_int= input.nextInt();
                    

                    for (Personal persona : personas) {

                        if(persona.cedula==seleccion_int){
                            personasEncontradas.add(persona);
                        }
                    }

                }else if(seleccion.equals("2")){

                    System.out.print("Ingrese la cedula de la persona: ");
                    seleccion_int= input.nextInt();

                    for (Personal persona : personas) {
                        
                        if(persona.cedula>=seleccion_int){
                            personasEncontradas.add(persona);
                        }
                    }

                }else if(seleccion.equals("3")){
                    System.out.print("Ingrese la cedula de la persona: ");
                    seleccion_int= input.nextInt();

                    for (Personal persona : personas) {
                        
                        if(persona.cedula<=seleccion_int){
                            personasEncontradas.add(persona);
                        }
                    }
                }else if(seleccion.equals("4")){
                    System.out.print("Ingrese el limite inferior de la cedula: ");
                    seleccion_int= input.nextInt();
                    System.out.print("Ingrese el limite superior de la cedula: ");
                    int seleccion_int2= input.nextInt();

                    int aux;

                    if(seleccion_int>seleccion_int2){
                        aux=seleccion_int;
                        seleccion_int=seleccion_int2;
                        seleccion_int2= aux;
                    }
                    
                    for (Personal persona : personas) {
                        
                        if(persona.cedula>=seleccion_int && persona.cedula<=seleccion_int2){
                            personasEncontradas.add(persona);
                        }
                    }

                    
                }else{
                    System.out.println("Opción inválida");
                    return;
                }

            }else if(seleccion.equals("5")){

                seleccion=atributo_integer_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese el sueldo del personal: ");
                    seleccion_int= input.nextInt();
                    

                    for (Personal persona : personas) {

                        if(persona.sueldo==seleccion_int){
                            personasEncontradas.add(persona);
                        }
                    }

                }else if(seleccion.equals("2")){

                    System.out.print("Ingrese el sueldo del personal: ");
                    seleccion_int= input.nextInt();

                    for (Personal persona : personas) {
                        
                        if(persona.sueldo>=seleccion_int){
                            personasEncontradas.add(persona);
                        }
                    }

                }else if(seleccion.equals("3")){
                    System.out.print("Ingrese el sueldo del personal: ");
                    seleccion_int= input.nextInt();

                    for (Personal persona : personas) {
                        
                        if(persona.sueldo<=seleccion_int){
                            personasEncontradas.add(persona);
                        }
                    }
                }else if(seleccion.equals("4")){
                    System.out.print("Ingrese el limite inferior del sueldo del personal: ");
                    seleccion_int= input.nextInt();
                    System.out.print("Ingrese el limite superior del sueldo del personal: ");
                    int seleccion_int2= input.nextInt();

                    int aux;

                    if(seleccion_int>seleccion_int2){
                        aux=seleccion_int;
                        seleccion_int=seleccion_int2;
                        seleccion_int2= aux;
                    }
                    
                    for (Personal persona : personas) {
                        
                        if(persona.sueldo>=seleccion_int && persona.sueldo<=seleccion_int2){
                            personasEncontradas.add(persona);
                        }
                    }

                    
                }else{
                    System.out.println("Opción inválida");
                    return;
                }



            }else if(seleccion.equals("6")){
                mostrar_todos=true;
                System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                System.out.println("1. Perfil");
                System.out.println("2. Horario");
                System.out.println("3. Nombre");
                System.out.println("4. Cedula");
                System.out.println("5. Sueldo");
                seleccion= input.next();



                if(seleccion.equals("1")){
                    System.out.println("Desea organizar el perfil de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(personas_copia, new ComparadorPersona_perfilA());
                    } else if (seleccion.equals("2")) {
                        Collections.sort(personas_copia, new ComparadorPersona_perfilD());
                    }

                    enumerador=1;
                    for (Personal persona : personas_copia) {
                        System.out.println(enumerador +". "+ persona);
                        enumerador+=1;
                    }
                }else if(seleccion.equals("2")){
                    System.out.println("Desea organizar los horarios de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();     
                    if(seleccion.equals("1")){

                        Collections.sort(personas_copia, new ComparadorPersona_horarioA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(personas_copia, new ComparadorPersona_horarioD());
                    }       

                    enumerador=1;

                    for (Personal persona : personas_copia) { 
                        System.out.println(enumerador+". "+ persona);
                        enumerador+=1;
                    }

                }else if(seleccion.equals("3")){
                    System.out.println("Desea organizar los nombres del personal de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(personas_copia, new ComparadorPersona_nombreA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(personas_copia,new ComparadorPersona_nombreD());
                    }

                    enumerador=1;
                    for (Personal persona : personas_copia) {
                        System.out.println(enumerador +". "+ persona);
                        enumerador+=1;
                    }
                
                }else if(seleccion.equals("4")){

                    System.out.println("Desea organizar las cedulas de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(personas_copia, new ComparadorPersona_cedulaA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(personas_copia, new ComparadorPersona_cedulaD());
                    }

                    enumerador=1;
                    for (Personal persona : personas_copia) {
                        System.out.println(enumerador +". "+ persona);
                        enumerador+=1;
                    }


                }else if(seleccion.equals("5")){

                    System.out.println("Desea organizar los sueldos de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(personas_copia, new ComparadorPersona_sueldoA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(personas_copia, new ComparadorPersona_sueldoD());
                    }

                    enumerador=1;
                    for (Personal persona : personas_copia) {
                        System.out.println(enumerador +". "+ persona);
                        enumerador+=1;
                    }

                }else{

                    System.out.println("Opción inválida");
                    return;
                }
            }

        }else{
            System.out.println("No hay talleres registrados en el sistema");
            return;
        }


        if(personasEncontradas.size()!=0 && !mostrar_todos) {
            System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
            System.out.println("1. Perfil");
            System.out.println("2. Horario");
            System.out.println("3. Nombre");
            System.out.println("4. Cedula");
            System.out.println("5. Sueldo");
            String seleccion= input.next();

            if(seleccion.equals("1")){
                System.out.println("Desea organizar los perfiles de forma : ");
                System.out.println("1. Ascendente");
                System.out.println("2. Descendente");
                seleccion= input.next();

                if(seleccion.equals("1")){
                    Collections.sort(personasEncontradas, new ComparadorPersona_perfilA());
                }else if(seleccion.equals("2")){
                    Collections.sort(personasEncontradas, new ComparadorPersona_perfilD());
                }

                enumerador=1;
                for (Personal persona : personasEncontradas) {
                    System.out.println(enumerador +". "+ persona);
                    enumerador+=1;
                }
            }else if(seleccion.equals("2")){
                System.out.println("Desea organizar los horarios de forma : ");
                System.out.println("1. Ascendente");
                System.out.println("2. Descendente");
                seleccion= input.next();

                if(seleccion.equals("1")){
                    Collections.sort(personasEncontradas, new ComparadorPersona_horarioA());
                }else if(seleccion.equals("2")){
                    Collections.sort(personasEncontradas, new ComparadorPersona_horarioD());
                }             

                enumerador=1;

                for (Personal persona : personasEncontradas) { 
                    System.out.println(enumerador+". "+ persona);
                    enumerador+=1;
                }

            }else if(seleccion.equals("3")){
                System.out.println("Desea organizar los nombres de forma : ");
                System.out.println("1. Ascendente");
                System.out.println("2. Descendente");
                seleccion= input.next();

                if(seleccion.equals("1")){
                    Collections.sort(personasEncontradas, new ComparadorPersona_nombreA());
                }else if(seleccion.equals("2")){
                    Collections.sort(personasEncontradas, new ComparadorPersona_nombreD());
                }

                enumerador=1;
                for (Personal persona : personasEncontradas) {
                    System.out.println(enumerador +". "+ persona);
                    enumerador+=1;
                }

            }else if(seleccion.equals("4")){

                System.out.println("Desea organizar las cedulas de forma : ");
                System.out.println("1. Ascendente");
                System.out.println("2. Descendente");
                seleccion= input.next();

                if(seleccion.equals("1")){
                    Collections.sort(personasEncontradas, new ComparadorPersona_cedulaA());
                }else if(seleccion.equals("2")){
                    Collections.sort(personasEncontradas, new ComparadorPersona_cedulaD());
                }

                enumerador=1;
                for (Personal persona: personasEncontradas) {
                    System.out.println(enumerador +". "+ persona);
                    enumerador+=1;
                }

            }else if(seleccion.equals("5")){

                System.out.println("Desea organizar los sueldos de forma : ");
                System.out.println("1. Ascendente");
                System.out.println("2. Descendente");
                seleccion= input.next();

                if(seleccion.equals("1")){
                    Collections.sort(personasEncontradas, new ComparadorPersona_sueldoA());
                }else if(seleccion.equals("2")){
                    Collections.sort(personasEncontradas, new ComparadorPersona_sueldoD());
                }

                enumerador=1;
                for (Personal persona: personasEncontradas) {
                    System.out.println(enumerador +". "+ persona);
                    enumerador+=1;
                }
            
            }else{

                System.out.println("Opción inválida");
                return;
            }
        }else{
            System.out.println("No se encontraron rutas");
            return;
        }

        // Aquí se establece la búsqueda sobre las areas
        // Tener presente que se utilizará la función de Valentín de Editar y eliminar
        System.out.println("**********************************************");
        System.out.println("En el personal encontrado desea:");
        System.out.println("1. Editar");
        System.out.println("2. Eliminar");
        System.out.println("3. Salir");
        String seleccion= input.next();

        if(seleccion.equals("1")){
            System.out.print("Ingrese el número de la lista correspondiente al personal: ");
            int option= input.nextInt();
            Personal persona;
            if(!mostrar_todos){
                if(personasEncontradas.size()>=option && option>=1){
                    persona= personasEncontradas.get(option-1);  // Aquí obtienes el personal a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }else{

                if(personas_copia.size()>=option && option>=1){
                    persona= personas_copia.get(option-1);  // Aquí obtienes el personal a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }

            
            BuscarEditarPersona(persona);    
            

        }else if(seleccion.equals("2")){
            System.out.print("Ingrese el número de la lista correspondiente al personal: ");
            int option= input.nextInt();
            Personal persona;
            if(!mostrar_todos){
                if(personasEncontradas.size()>=option && option>=1){
                    persona= personasEncontradas.get(option-1);  // Aquí obtienes la persona a eliminar 
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }else{

                if(personas_copia.size()>=option && option>=1){
                    persona= personas_copia.get(option-1);  // Aquí obtienes la persona a eliminar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }
            // Aquí vas a implementar eliminar********************
            BuscarEliminarPersona(persona);
        }
    }

    public static void BuscarEliminarPersona(Personal place) {
        System.out.println("¿Seguro que desea eliminar la persona?");
        System.out.println("Y");
        System.out.println("N");
        String option = input.next();
        boolean personaEncontrado = false;
        if (option.equalsIgnoreCase("Y")) {
            Iterator<Personal> iterator = personas.iterator();
            while(iterator.hasNext()) {
                Personal persona = iterator.next();
                if (persona.getCedula()==place.getCedula()) {
                    personaEncontrado = true;
                    iterator.remove();
                }
            }

            if (!personaEncontrado) {
                System.out.println("No se encontró la persona");
                return;
            }else{
                System.out.println("La persona se ha removido satisfactoriamente");
            }
        }
    }


    public static void BuscarEditarPersona(Personal place) {
        for (Personal persona : personas) {
            if(persona.cedula==place.cedula){

                String nuevo_perfil ="";
                String nuevo_horario = "";
                String nuevo_nombre = "";
                String nuevo_cedula="";
                String nuevo_sueldo="";

                System.out.println("Perfil de la persona: "+ persona.getPerfil());
                System.out.println("Si desea cambiar el perfil, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_perfil = input.next();
                System.out.println("Horario: "+ persona.getHorario());
                System.out.println("Si desea cambiar el horario, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_horario = input.next();
                System.out.println("Nombre: "+ persona.getNombre());
                System.out.println("Si desea cambiar el nombre, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_nombre = input.next();
                System.out.println("Cedula: "+ persona.getCedula());
                System.out.println("Si desea cambiar la cedula, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_cedula = input.next();
                System.out.println("Sueldo: "+ persona.getSueldo());
                System.out.println("Si desea cambiar el sueldo, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_sueldo = input.next();
                System.out.println("¿Desea guardar los cambios?");
                System.out.println("Y");
                System.out.println("N");
                String option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    if (nuevo_perfil.equalsIgnoreCase("N")) {
                        
                    }else {
                        
                        persona.setPerfil(nuevo_perfil);
                    }

                    if (nuevo_horario.equalsIgnoreCase("N")) {

                    } else {
                        persona.setHorario(nuevo_horario);
                    }
                    if (nuevo_nombre.equalsIgnoreCase("N")) {

                    } else {
                        persona.setNombre(nuevo_nombre);
                    }
                    if (nuevo_cedula.equalsIgnoreCase("N")) {

                    } else {
                        int foo;
                        try {
                            foo = Integer.parseInt(nuevo_cedula);
                         }
                         catch (NumberFormatException e)
                         {
                            foo = 0;
                         }
                        persona.setCedula(foo);
                    }
                    if (nuevo_sueldo.equalsIgnoreCase("N")) {

                    } else {
                        int foo;
                        try {
                            foo = Integer.parseInt(nuevo_sueldo);
                         }
                         catch (NumberFormatException e)
                         {
                            foo = 0;
                         }
                        persona.setSueldo(foo);
                    }



                }
                System.out.println("Se han realizado los cambios satisfactoriamente");
                break;
        
            }
        }
        
    }



    public static void BuscarTalleres() {
        LinkedList<Taller> talleres_copia= new LinkedList<>();
        talleres_copia= (LinkedList<Taller>) talleres.clone();
        LinkedList<Taller> talleresEncontrados= new LinkedList<>();
        int enumerador=1;
        boolean mostrar_todos=false;

        if(talleres.size()!=0){
            System.out.println("Seleccione el atributo por el cual desea buscar el taller:");
            System.out.println("1. Nombre");
            System.out.println("2. Sistema mecanico asociado");
            System.out.println("3. Tipo de taller");
            System.out.println("4. Presupuesto fallas menores");
            System.out.println("5. Mostrar todos los talleres");
            String seleccion= input.next();
            int seleccion_int;
            if(seleccion.equals("1")){

                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese el nombre del taller: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Taller taller : talleres) {

                        if(taller.nombre.equals(seleccion)){
                            talleresEncontrados.add(taller);
                        }
                    }

                }else if(seleccion.equals("2")){

                    System.out.print("Ingrese el nombre del taller: ");
                    input.nextLine();
                    seleccion= input.nextLine();

                    for (Taller taller : talleres) {
                        
                        if(taller.nombre.toLowerCase().equals(seleccion)){
                            talleresEncontrados.add(taller);
                        }
                    }

                

                }else{
                    System.out.println("Opción inválida");
                    return;
                }

               

            }else if(seleccion.equals("2")){
                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese el sistema asociado: ");
                    input.nextLine();
                    seleccion= input.nextLine();

                    for (Taller taller : talleres) {

                        if(taller.sistema_asociado.equals(seleccion)){
                            talleresEncontrados.add(taller);
                        }
                    }


                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese el sistema asociado: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Taller taller : talleres) {

                        if(taller.sistema_asociado.toLowerCase().equals(seleccion)){
                            talleresEncontrados.add(taller);
                        }
                    }

                }else{
                    System.out.println("Opción inválida");
                    return;
                }

               


            }else if(seleccion.equals("3")){

                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese el tipo de taller: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Taller taller : talleres) {

                        if(taller.tipo.equals(seleccion)){
                            talleresEncontrados.add(taller);
                        }
                    }

                    
                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese el tipo de taller: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Taller taller : talleres) {

                        if(taller.tipo.toLowerCase().equals(seleccion)){
                            talleresEncontrados.add(taller);
                        }
                    }

                    
                }else{
                    System.out.println("Opción inválida");
                    return;
                }

            }else if(seleccion.equals("4")){

                seleccion=atributo_integer_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese el dinero asociado a fallas menores: ");
                    seleccion_int= input.nextInt();
                    

                    for (Taller taller : talleres) {

                        if(taller.dinero_fallas_menores==seleccion_int){
                            talleresEncontrados.add(taller);
                        }
                    }

                }else if(seleccion.equals("2")){

                    System.out.print("Ingrese el dinero asociado a fallas menores: ");
                    seleccion_int= input.nextInt();

                    for (Taller taller : talleres) {
                        
                        if(taller.dinero_fallas_menores>=seleccion_int){
                            talleresEncontrados.add(taller);
                        }
                    }

                }else if(seleccion.equals("3")){
                    System.out.print("Ingrese el dinero asociado a fallas menores: ");
                    seleccion_int= input.nextInt();

                    for (Taller taller : talleres) {
                        
                        if(taller.dinero_fallas_menores<=seleccion_int){
                            talleresEncontrados.add(taller);
                        }
                    }
                }else if(seleccion.equals("4")){
                    System.out.print("Ingrese el limite inferior del dinero asociado a fallas menores: ");
                    seleccion_int= input.nextInt();
                    System.out.print("Ingrese el limite superior del dinero asociado a fallas menores: ");
                    int seleccion_int2= input.nextInt();

                    int aux;

                    if(seleccion_int>seleccion_int2){
                        aux=seleccion_int;
                        seleccion_int=seleccion_int2;
                        seleccion_int2= aux;
                    }
                    
                    for (Taller taller : talleres) {
                        
                        if(taller.dinero_fallas_menores>=seleccion_int && taller.dinero_fallas_menores<=seleccion_int2){
                            talleresEncontrados.add(taller);
                        }
                    }

                    
                }else{
                    System.out.println("Opción inválida");
                    return;
                }



            }else if(seleccion.equals("5")){
                mostrar_todos=true;
                System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                System.out.println("1. Nombre");
                System.out.println("2. Sistema asociado");
                System.out.println("3. Tipo de taller");
                System.out.println("4. Dinero asociado a fallas menores");
                seleccion= input.next();



                if(seleccion.equals("1")){
                    System.out.println("Desea organizar el nombre de los talleres de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(talleres_copia, new ComparadorTaller_nombreA());
                    } else if (seleccion.equals("2")) {
                        Collections.sort(talleres_copia, new ComparadorTaller_nombreD());
                    }

                    enumerador=1;
                    for (Taller taller : talleres_copia) {
                        System.out.println(enumerador +". "+ taller);
                        enumerador+=1;
                    }
                }else if(seleccion.equals("2")){
                    System.out.println("Desea organizar los sistemas asociados de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();     
                    if(seleccion.equals("1")){

                        Collections.sort(talleres_copia, new ComparadorTaller_sistemaA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(talleres_copia, new ComparadorTaller_sistemaD());
                    }       

                    enumerador=1;

                    for (Taller taller : talleres_copia) { 
                        System.out.println(enumerador+". "+ taller);
                        enumerador+=1;
                    }

                }else if(seleccion.equals("3")){
                    System.out.println("Desea organizar el tipo de taller : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(talleres_copia, new ComparadorTaller_tipoA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(talleres_copia,new ComparadorTaller_tipoD());
                    }

                    enumerador=1;
                    for (Taller taller : talleres_copia) {
                        System.out.println(enumerador +". "+ taller);
                        enumerador+=1;
                    }
                
                }else if(seleccion.equals("4")){

                    System.out.println("Desea organizar el dinero asociado a fallas menores de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(talleres_copia, new ComparadorTaller_dineroA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(talleres_copia, new ComparadorTaller_dineroD());
                    }

                    enumerador=1;
                    for (Taller taller : talleres_copia) {
                        System.out.println(enumerador +". "+ taller);
                        enumerador+=1;
                    }

                }else{

                    System.out.println("Opción inválida");
                    return;
                }
            }

        }else{
            System.out.println("No hay talleres registrados en el sistema");
            return;
        }


        if(talleresEncontrados.size()!=0 && !mostrar_todos) {
            System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
            System.out.println("1. Nombre");
            System.out.println("2. Sistema asociado");
            System.out.println("3. Tipo de taller");
            System.out.println("4. Dinero asociado a fallas menores");
            String seleccion= input.next();

            if(seleccion.equals("1")){
                System.out.println("Desea organizar el nombre del taller : ");
                System.out.println("1. Ascendente");
                System.out.println("2. Descendente");
                seleccion= input.next();

                if(seleccion.equals("1")){
                    Collections.sort(talleresEncontrados, new ComparadorTaller_nombreA());
                }else if(seleccion.equals("2")){
                    Collections.sort(talleresEncontrados, new ComparadorTaller_nombreD());
                }

                enumerador=1;
                for (Taller taller : talleresEncontrados) {
                    System.out.println(enumerador +". "+ taller);
                    enumerador+=1;
                }
            }else if(seleccion.equals("2")){
                System.out.println("Desea organizar el sistema asociado de forma : ");
                System.out.println("1. Ascendente");
                System.out.println("2. Descendente");
                seleccion= input.next();

                if(seleccion.equals("1")){
                    Collections.sort(talleresEncontrados, new ComparadorTaller_sistemaA());
                }else if(seleccion.equals("2")){
                    Collections.sort(talleresEncontrados, new ComparadorTaller_sistemaD());
                }             

                enumerador=1;

                for (Taller taller : talleresEncontrados) { 
                    System.out.println(enumerador+". "+ taller);
                    enumerador+=1;
                }

            }else if(seleccion.equals("3")){
                System.out.println("Desea organizar el tipo de taller de forma : ");
                System.out.println("1. Ascendente");
                System.out.println("2. Descendente");
                seleccion= input.next();

                if(seleccion.equals("1")){
                    Collections.sort(talleresEncontrados, new ComparadorTaller_tipoA());
                }else if(seleccion.equals("2")){
                    Collections.sort(talleresEncontrados, new ComparadorTaller_tipoD());
                }

                enumerador=1;
                for (Taller taller : talleresEncontrados) {
                    System.out.println(enumerador +". "+ taller);
                    enumerador+=1;
                }

            }else if(seleccion.equals("4")){

                System.out.println("Desea organizar el dinero asociado a fallas menores de forma : ");
                System.out.println("1. Ascendente");
                System.out.println("2. Descendente");
                seleccion= input.next();

                if(seleccion.equals("1")){
                    Collections.sort(talleresEncontrados, new ComparadorTaller_dineroA());
                }else if(seleccion.equals("2")){
                    Collections.sort(talleresEncontrados, new ComparadorTaller_dineroD());
                }

                enumerador=1;
                for (Taller taller : talleresEncontrados) {
                    System.out.println(enumerador +". "+ taller);
                    enumerador+=1;
                }

            }else{

                System.out.println("Opción inválida");
                return;
            }
        }else{
            System.out.println("No se encontraron rutas");
            return;
        }

        // Aquí se establece la búsqueda sobre las areas
        // Tener presente que se utilizará la función de Valentín de Editar y eliminar
        System.out.println("**********************************************");
        System.out.println("En los talleres encontradas desea:");
        System.out.println("1. Editar");
        System.out.println("2. Eliminar");
        System.out.println("3. Salir");
        String seleccion= input.next();

        if(seleccion.equals("1")){
            System.out.print("Ingrese el número de la lista correspondiente al taller: ");
            int option= input.nextInt();
            Taller taller;
            if(!mostrar_todos){
                if(talleresEncontrados.size()>=option && option>=1){
                    taller= talleresEncontrados.get(option-1);  // Aquí obtienes el taller a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }else{

                if(talleres_copia.size()>=option && option>=1){
                    taller= talleres_copia.get(option-1);  // Aquí obtienes el taller a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }

            
            BuscarEditarTaller(taller);    
            

        }else if(seleccion.equals("2")){
            System.out.print("Ingrese el número de la lista correspondiente al taller: ");
            int option= input.nextInt();
            Taller taller;
            if(!mostrar_todos){
                if(talleresEncontrados.size()>=option && option>=1){
                    taller= talleresEncontrados.get(option-1);  // Aquí obtienes la sede a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }else{

                if(talleres_copia.size()>=option && option>=1){
                    taller= talleres_copia.get(option-1);  // Aquí obtienes la sede a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }
            // Aquí vas a implementar eliminar********************
            BuscarEliminarTaller(taller);
        }
    }

    public static void BuscarEliminarTaller(Taller place) {
        System.out.println("¿Seguro que desea eliminar el taller?");
        System.out.println("Y");
        System.out.println("N");
        String option = input.next();
        boolean tallerEncontrado = false;
        if (option.equalsIgnoreCase("Y")) {
            Iterator<Taller> iterator = talleres.iterator();
            while(iterator.hasNext()) {
                Taller taller = iterator.next();
                if (taller.getNombre().equals(place.getNombre())) {
                    tallerEncontrado = true;
                    iterator.remove();
                }
            }

            if (!tallerEncontrado) {
                System.out.println("No se encontró el taller");
                return;
            }else{
                System.out.println("El taller se ha removido satisfactoriamente");
            }
        }
    }

    public static void BuscarEditarTaller(Taller place) {
        for (Taller taller : talleres) {
            if(taller.nombre.equals(place.nombre)){

                String nuevo_nombre ="";
                String nuevo_sistema = "";
                String nuevo_tipo = "";
                String nuevo_dinero="";

                System.out.println("Nombre taller: "+ taller.getNombre());
                System.out.println("Si desea cambiar el nombre, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_nombre = input.next();
                System.out.println("Sistema asociado: "+ taller.getSistema_asociado());
                System.out.println("Si desea cambiar el sistema asociado, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_sistema = input.next();
                System.out.println("Tipo de taller: "+ taller.getTipo());
                System.out.println("Si desea cambiar el tipo, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_tipo = input.next();
                System.out.println("Dinero asociado a fallas menores: "+ taller.getDinero_fallas_menores());
                System.out.println("Si desea cambiar el dinero, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_dinero = input.next();
                System.out.println("¿Desea guardar los cambios?");
                System.out.println("Y");
                System.out.println("N");
                String option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    if (nuevo_nombre.equalsIgnoreCase("N")) {
                        
                    }else {
                        
                        taller.setNombre(nuevo_nombre);
                    }

                    if (nuevo_sistema.equalsIgnoreCase("N")) {

                    } else {
                        taller.setSistema_asociado(nuevo_sistema);
                    }
                    if (nuevo_tipo.equalsIgnoreCase("N")) {

                    } else {
                        taller.setTipo(nuevo_tipo);
                    }
                    if (nuevo_dinero.equalsIgnoreCase("N")) {

                    } else {
                        int foo;
                        try {
                            foo = Integer.parseInt(nuevo_dinero);
                         }
                         catch (NumberFormatException e)
                         {
                            foo = 0;
                         }
                        taller.setDinero_fallas_menores(foo);
                    }


                }
                System.out.println("Se han realizado los cambios satisfactoriamente");
                break;
        
            }
        }
        
    }

    
    
    


    public static void BuscarRutas() {
        LinkedList<Ruta> rutas_copia= new LinkedList<>();
        rutas_copia= (LinkedList<Ruta>) rutas.clone();
        LinkedList<Ruta> rutasEncontradas= new LinkedList<>();
        int enumerador=1;
        boolean mostrar_todos=false;

        if(rutas.size()!=0){
            System.out.println("Seleccione el atributo por el cual desea buscar la ruta:");
            System.out.println("1. Identificacion");
            System.out.println("2. Horario");
            System.out.println("3. Día de limpieza");
            System.out.println("4. Identificacion recolector asociado");
            System.out.println("5. Mostrar todas las rutas");
            String seleccion= input.next();
            int seleccion_int;
            if(seleccion.equals("1")){

                seleccion=atributo_integer_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese la identificacion de la ruta: ");
                    seleccion_int= input.nextInt();
                    

                    for (Ruta ruta : rutas) {

                        if(ruta.id==seleccion_int){
                            rutasEncontradas.add(ruta);
                        }
                    }

                }else if(seleccion.equals("2")){

                    System.out.print("Ingrese la identificacion de la ruta: ");
                    seleccion_int= input.nextInt();

                    for (Ruta ruta : rutasEncontradas) {
                        
                        if(ruta.id>=seleccion_int){
                            rutasEncontradas.add(ruta);
                        }
                    }

                }else if(seleccion.equals("3")){
                    System.out.print("Ingrese la identificacion de la ruta: ");
                    seleccion_int= input.nextInt();

                    for (Ruta ruta : rutasEncontradas) {
                        
                        if(ruta.id<=seleccion_int){
                            rutasEncontradas.add(ruta);
                        }
                    }
                }else if(seleccion.equals("4")){
                    System.out.print("Ingrese el limite inferior de la identificacion de la ruta: ");
                    seleccion_int= input.nextInt();
                    System.out.print("Ingrese el limite superior de la identificacion de la ruta: ");
                    int seleccion_int2= input.nextInt();

                    int aux;

                    if(seleccion_int>seleccion_int2){
                        aux=seleccion_int;
                        seleccion_int=seleccion_int2;
                        seleccion_int2= aux;
                    }
                    
                    for (Ruta ruta : rutasEncontradas) {
                        
                        if(ruta.id>=seleccion_int && ruta.id<=seleccion_int2){
                            rutasEncontradas.add(ruta);
                        }
                    }

                }else{
                    System.out.println("Opción inválida");
                    return;
                }

               

            }else if(seleccion.equals("2")){
                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese el horario: ");
                    input.nextLine();
                    seleccion= input.nextLine();

                    for (Ruta ruta : rutas) {

                        if(ruta.horario.equals(seleccion)){
                            rutasEncontradas.add(ruta);
                        }
                    }


                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese el horario: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Ruta ruta : rutas) {

                        if(ruta.horario.toLowerCase().equals(seleccion)){
                            rutasEncontradas.add(ruta);
                        }
                    }

                }else{
                    System.out.println("Opción inválida");
                    return;
                }

               


            }else if(seleccion.equals("3")){

                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese el dia de la ruta: ");
                    seleccion= input.next();
                    

                    for (Ruta ruta : rutas) {

                        if(ruta.dia.equals(seleccion)){
                            rutasEncontradas.add(ruta);
                        }
                    }

                    
                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese el dia de la ruta: ");
                    seleccion= input.next();
                    

                    for (Ruta ruta : rutas) {

                        if(ruta.dia.toLowerCase().equals(seleccion)){
                            rutasEncontradas.add(ruta);
                        }
                    }

                    
                }else{
                    System.out.println("Opción inválida");
                    return;
                }

            }else if(seleccion.equals("4")){

                seleccion=atributo_integer_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese la identificacion del recolector: ");
                    seleccion_int= input.nextInt();
                    

                    for (Ruta ruta : rutas) {

                        if(ruta.recolector_id==seleccion_int){
                            rutasEncontradas.add(ruta);
                        }
                    }

                }else if(seleccion.equals("2")){

                    System.out.print("Ingrese la identificacion del recolector: ");
                    seleccion_int= input.nextInt();

                    for (Ruta ruta : rutasEncontradas) {
                        
                        if(ruta.recolector_id>=seleccion_int){
                            rutasEncontradas.add(ruta);
                        }
                    }

                }else if(seleccion.equals("3")){
                    System.out.print("Ingrese la identificacion del recolector: ");
                    seleccion_int= input.nextInt();

                    for (Ruta ruta : rutasEncontradas) {
                        
                        if(ruta.recolector_id<=seleccion_int){
                            rutasEncontradas.add(ruta);
                        }
                    }
                }else if(seleccion.equals("4")){
                    System.out.print("Ingrese el limite inferior de la identificacion del recolector: ");
                    seleccion_int= input.nextInt();
                    System.out.print("Ingrese el limite superior de la identificacion del recolector: ");
                    int seleccion_int2= input.nextInt();

                    int aux;

                    if(seleccion_int>seleccion_int2){
                        aux=seleccion_int;
                        seleccion_int=seleccion_int2;
                        seleccion_int2= aux;
                    }
                    
                    for (Ruta ruta : rutasEncontradas) {
                        
                        if(ruta.recolector_id>=seleccion_int && ruta.recolector_id<=seleccion_int2){
                            rutasEncontradas.add(ruta);
                        }
                    }

                    
                }else{
                    System.out.println("Opción inválida");
                    return;
                }



            }else if(seleccion.equals("5")){
                mostrar_todos=true;
                System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                System.out.println("1. Identificacion de la ruta");
                System.out.println("2. Horario");
                System.out.println("3. Dia");
                System.out.println("4. Identificacion recolector");
                seleccion= input.next();



                if(seleccion.equals("1")){
                    System.out.println("Desea organizar la identificaciones de las rutas de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(rutas_copia, new ComparadorRuta_idA());
                    } else if (seleccion.equals("2")) {
                        Collections.sort(rutas_copia, new ComparadorRuta_idD());
                    }

                    enumerador=1;
                    for (Ruta ruta : rutas_copia) {
                        System.out.println(enumerador +". "+ ruta);
                        enumerador+=1;
                    }
                }else if(seleccion.equals("2")){
                    System.out.println("Desea organizar los horarios de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();     
                    if(seleccion.equals("1")){

                        Collections.sort(rutas_copia, new ComparadorRuta_horarioA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(rutas_copia, new ComparadorRuta_horarioD());
                    }       

                    enumerador=1;

                    for (Ruta ruta : rutas_copia) { 
                        System.out.println(enumerador+". "+ ruta);
                        enumerador+=1;
                    }

                }else if(seleccion.equals("3")){
                    System.out.println("Desea organizar los dias de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(rutas_copia, new ComparadorRuta_diaA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(rutas_copia,new ComparadorRuta_diaD());
                    }

                    enumerador=1;
                    for (Ruta ruta : rutas_copia) {
                        System.out.println(enumerador +". "+ ruta);
                        enumerador+=1;
                    }
                
                }else if(seleccion.equals("4")){

                    System.out.println("Desea organizar las identificaciones de los recolectores de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(rutas_copia, new ComparadorRuta_idRecolectorA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(rutas_copia, new ComparadorRuta_idRecolectorD());
                    }

                    enumerador=1;
                    for (Ruta ruta : rutas_copia) {
                        System.out.println(enumerador +". "+ ruta);
                        enumerador+=1;
                    }

                }else{

                    System.out.println("Opción inválida");
                    return;
                }
            }

        }else{
            System.out.println("No hay rutas registradas en el sistema");
            return;
        }


        if(rutasEncontradas.size()!=0 && !mostrar_todos) {
            System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
            System.out.println("1. Identificación ruta");
            System.out.println("2. Horario");
            System.out.println("3. Dia");
            System.out.println("4. Identificacion recolector");
            String seleccion= input.next();

            if(seleccion.equals("1")){
                System.out.println("Desea organizar la identificacion de la ruta : ");
                System.out.println("1. Ascendente");
                System.out.println("2. Descendente");
                seleccion= input.next();

                if(seleccion.equals("1")){
                    Collections.sort(rutasEncontradas, new ComparadorRuta_idA());
                }else if(seleccion.equals("2")){
                    Collections.sort(rutasEncontradas, new ComparadorRuta_idD());
                }

                enumerador=1;
                for (Ruta ruta : rutasEncontradas) {
                    System.out.println(enumerador +". "+ ruta);
                    enumerador+=1;
                }
            }else if(seleccion.equals("2")){
                System.out.println("Desea organizar el horario de forma : ");
                System.out.println("1. Ascendente");
                System.out.println("2. Descendente");
                seleccion= input.next();

                if(seleccion.equals("1")){
                    Collections.sort(rutasEncontradas, new ComparadorRuta_horarioA());
                }else if(seleccion.equals("2")){
                    Collections.sort(rutasEncontradas, new ComparadorRuta_horarioD());
                }             

                enumerador=1;

                for (Ruta ruta : rutasEncontradas) { 
                    System.out.println(enumerador+". "+ ruta);
                    enumerador+=1;
                }

            }else if(seleccion.equals("3")){
                System.out.println("Desea organizar los dias de forma : ");
                System.out.println("1. Ascendente");
                System.out.println("2. Descendente");
                seleccion= input.next();

                if(seleccion.equals("1")){
                    Collections.sort(rutasEncontradas, new ComparadorRuta_diaA());
                }else if(seleccion.equals("2")){
                    Collections.sort(rutasEncontradas, new ComparadorRuta_diaD());
                }

                enumerador=1;
                for (Ruta ruta : rutasEncontradas) {
                    System.out.println(enumerador +". "+ ruta);
                    enumerador+=1;
                }

            }else if(seleccion.equals("4")){

                System.out.println("Desea organizar las identificaciones de los recolectores de forma : ");
                System.out.println("1. Ascendente");
                System.out.println("2. Descendente");
                seleccion= input.next();

                if(seleccion.equals("1")){
                    Collections.sort(rutasEncontradas, new ComparadorRuta_idRecolectorA());
                }else if(seleccion.equals("2")){
                    Collections.sort(rutasEncontradas, new ComparadorRuta_idRecolectorD());
                }

                enumerador=1;
                for (Ruta ruta : rutasEncontradas) {
                    System.out.println(enumerador +". "+ ruta);
                    enumerador+=1;
                }

            }else{

                System.out.println("Opción inválida");
                return;
            }
        }else{
            System.out.println("No se encontraron rutas");
            return;
        }

        // Aquí se establece la búsqueda sobre las areas
        // Tener presente que se utilizará la función de Valentín de Editar y eliminar
        System.out.println("**********************************************");
        System.out.println("En las rutas encontradas desea:");
        System.out.println("1. Editar");
        System.out.println("2. Eliminar");
        System.out.println("3. Salir");
        String seleccion= input.next();

        if(seleccion.equals("1")){
            System.out.print("Ingrese el número de la lista correspondiente a la ruta: ");
            int option= input.nextInt();
            Ruta ruta;
            if(!mostrar_todos){
                if(rutasEncontradas.size()>=option && option>=1){
                    ruta= rutasEncontradas.get(option-1);  // Aquí obtienes la ruta a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }else{

                if(rutas_copia.size()>=option && option>=1){
                    ruta= rutas_copia.get(option-1);  // Aquí obtienes la ruta a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }

            
            BuscarEditarRuta(ruta);    
            

        }else if(seleccion.equals("2")){
            System.out.print("Ingrese el número de la lista correspondiente a la ruta: ");
            int option= input.nextInt();
            Ruta ruta;
            if(!mostrar_todos){
                if(rutasEncontradas.size()>=option && option>=1){
                    ruta= rutasEncontradas.get(option-1);  // Aquí obtienes la sede a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }else{

                if(rutas_copia.size()>=option && option>=1){
                    ruta= rutas_copia.get(option-1);  // Aquí obtienes la sede a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }
            // Aquí vas a implementar eliminar********************
            BuscarEliminarRuta(ruta);
        }
    }

    public static void BuscarEliminarRuta(Ruta place) {
        System.out.println("¿Seguro que desea eliminar ruta?");
        System.out.println("Y");
        System.out.println("N");
        String option = input.next();
        boolean rutaEncontrada = false;
        if (option.equalsIgnoreCase("Y")) {
            Iterator<Ruta> iterator = rutas.iterator();
            while(iterator.hasNext()) {
                Ruta ruta = iterator.next();
                if (ruta.getId()==place.getId()) {
                    rutaEncontrada = true;
                    iterator.remove();
                }
            }

            if (!rutaEncontrada) {
                System.out.println("No se encontró la ruta");
                return;
            }else{
                System.out.println("La ruta se ha removido satisfactoriamente");
            }
        }
    }

    public static void BuscarEditarRuta(Ruta place) {
        for (Ruta ruta : rutas) {
            if(ruta.id==place.id){

                String nuevo_id ="";
                String nuevo_horario = "";
                String nuevo_dia = "";
                String nuevo_idRecolector="";

                System.out.println("Identificacion ruta: "+ ruta.getId());
                System.out.println("Si desea cambiar el tipo, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_id = input.next();
                System.out.println("Horario: "+ ruta.getHorario());
                System.out.println("Si desea cambiar el horario, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_horario = input.next();
                System.out.println("Dia de la ruta: "+ ruta.getDia());
                System.out.println("Si desea cambiar el dia, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_dia = input.next();
                System.out.println("Identificacion recolector: "+ ruta.getRecolector_id());
                System.out.println("Si desea cambiar el tipo, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_idRecolector = input.next();
                System.out.println("¿Desea guardar los cambios?");
                System.out.println("Y");
                System.out.println("N");
                String option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    if (nuevo_id.equalsIgnoreCase("N")) {
                        
                    }else {
                        int foo;
                        try {
                            foo = Integer.parseInt(nuevo_id);
                         }
                         catch (NumberFormatException e)
                         {
                            foo = 0;
                         }
                        ruta.setId(foo);
                    }

                    if (nuevo_horario.equalsIgnoreCase("N")) {

                    } else {
                        ruta.setHorario(nuevo_horario);
                    }
                    if (nuevo_dia.equalsIgnoreCase("N")) {

                    } else {
                        ruta.setDia(nuevo_dia);
                    }
                    if (nuevo_idRecolector.equalsIgnoreCase("N")) {

                    } else {
                        int foo;
                        try {
                            foo = Integer.parseInt(nuevo_idRecolector);
                         }
                         catch (NumberFormatException e)
                         {
                            foo = 0;
                         }
                        ruta.setRecolector_id(foo);
                    }


                }
                System.out.println("Se han realizado los cambios satisfactoriamente");
                break;
        
            }
        }
        
    }

    public static void BuscarAreas() {
        LinkedList<Area> areas_copia= new LinkedList<>();
        areas_copia= (LinkedList<Area>) areas.clone();
        LinkedList<Area> areasEncontradas= new LinkedList<>();
        int enumerador=1;
        boolean mostar_todos=false;

        if(areas.size()!=0){
            System.out.println("Seleccione el atributo por el cual desea buscar el area:");
            System.out.println("1. Tipo de area");
            System.out.println("2. Horario");
            System.out.println("3. Persona a cargo");
            System.out.println("4. Telefono persona a cargo");
            System.out.println("5. Mostrar todas las sedes");
            String seleccion= input.next();

            if(seleccion.equals("1")){

                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese el tipo de area: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Area area : areas) {

                        if(area.tipo.equals(seleccion)){
                            areasEncontradas.add(area);
                        }
                    }

                    if(areasEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Tipo de area");
                        System.out.println("2. Horario");
                        System.out.println("3. Persona a cargo");
                        System.out.println("4. Telefono persona a cargo");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar el tipo de area de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_tipoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_tipoD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar el horario de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_horarioA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_horarioD());
                            }             

                            enumerador=1;

                            for (Area area : areasEncontradas) { 
                                System.out.println(enumerador+". "+ area);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_personaA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_personaD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("4")){

                            System.out.println("Desea organizar los telefonos de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_telefonoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_telefonoD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }

                        }else{

                            System.out.println("Opción inválida");
                            return;
                        }
                    }else{
                        System.out.println("No se encontraron areas");
                        return;
                    }

                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese el tipo de area: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Area area : areas) {

                        if(area.tipo.toLowerCase().equals(seleccion)){
                            areasEncontradas.add(area);
                        }
                    }

                    if(areasEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Tipo de area");
                        System.out.println("2. Horario");
                        System.out.println("3. Persona a cargo");
                        System.out.println("4. Telefono persona a cargo");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar el tipo de area de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_tipoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_tipoD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar los horarios de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_horarioA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_horarioD());
                            }             

                            enumerador=1;

                            for (Area area : areasEncontradas) { 
                                System.out.println(enumerador+". "+ area);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_personaA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_personaD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("4")){

                            System.out.println("Desea organizar los telefonos de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_telefonoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_telefonoD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                        }
                    }else{
                        System.out.println("No se encontraron areas");
                        return;
                    }
                }else{
                    System.out.println("Opción inválida");
                    return;
                }



            }else if(seleccion.equals("2")){
                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese el horario: ");
                    input.nextLine();
                    seleccion= input.nextLine();

                    for (Area area : areas) {

                        if(area.horario.equals(seleccion)){
                            areasEncontradas.add(area);
                        }
                    }

                    if(areasEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Tipo de area");
                        System.out.println("2. Horario");
                        System.out.println("3. Persona a cargo");
                        System.out.println("Telefono persona a cargo");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar el tipo de area de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_tipoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_tipoD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar los horarios de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();
                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_horarioA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_horarioD());
                            }            

                            enumerador=1;

                            for (Area area : areasEncontradas) { 
                                System.out.println(enumerador+". "+ area);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_personaA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_personaD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("4")){

                            System.out.println("Desea organizar los telefonos de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_telefonoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_telefonoD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }

                        }else{

                            System.out.println("Opción inválida");
                            return;
                        }
                    }else{
                        System.out.println("No se encontraron areas");
                        return;
                    }

                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese el horario: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Area area : areas) {

                        if(area.horario.toLowerCase().equals(seleccion)){
                            areasEncontradas.add(area);
                        }
                    }

                    if(areasEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Tipo de area");
                        System.out.println("2. Horario");
                        System.out.println("3. Persona a cargo");
                        System.out.println("4. Telefono persona a cargo");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar el tipo de area de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_tipoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_tipoD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar los horarios de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next(); 

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_horarioA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_horarioD());
                            }            

                            enumerador=1;

                            for (Area area : areasEncontradas) { 
                                System.out.println(enumerador+". "+ area);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_personaA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_personaD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("4")){

                            System.out.println("Desea organizar los telefonos de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_telefonoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_telefonoD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }

                        }else{

                            System.out.println("Opción inválida");
                        }
                    }else{
                        System.out.println("No se encontraron areas");
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
                    

                    for (Area area : areas) {

                        if(area.persona_a_cargo.equals(seleccion)){
                            areasEncontradas.add(area);
                        }
                    }

                    if(areasEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Tipo de area");
                        System.out.println("2. Horario");
                        System.out.println("3. Persona a cargo");
                        System.out.println("4. Telefono de persona a cargo");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar el tipo de area de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_tipoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_tipoD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar los horarios de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();            
                            if(seleccion.equals("1")){

                                Collections.sort(areasEncontradas, new ComparadorArea_horarioA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_horarioD());
                            }
                            enumerador=1;

                            for (Area area : areasEncontradas) { 
                                System.out.println(enumerador+". "+ area);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();              

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_personaA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_personaD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("4")){

                            System.out.println("Desea organizar los telefonos de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_telefonoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_telefonoD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }

                        }else{

                            System.out.println("Opción inválida");
                            return;
                        }
                    }else{
                        System.out.println("No se encontraron areas");
                        return;
                    }

                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese el nombre de la persona a cargo: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Area area : areas) {

                        if(area.persona_a_cargo.toLowerCase().equals(seleccion)){
                            areasEncontradas.add(area);
                        }
                    }

                    if(areasEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Tipo de area");
                        System.out.println("2. Horario");
                        System.out.println("3. Persona a cargo");
                        System.out.println("4. Telefono persona a cargo");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar el tipo de area de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_tipoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_tipoD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar los horarios de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();     
                            if(seleccion.equals("1")){

                                Collections.sort(areasEncontradas, new ComparadorArea_horarioA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_horarioD());
                            }       

                            enumerador=1;

                            for (Area area : areasEncontradas) { 
                                System.out.println(enumerador+". "+ area);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_personaA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_personaD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("4")){

                            System.out.println("Desea organizar los telefonos de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_telefonoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_telefonoD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }

                        }else{

                            System.out.println("Opción inválida");
                            return;
                        }
                    }else{
                        System.out.println("No se encontraron areas");
                        return;
                    }
                }else{
                    System.out.println("Opción inválida");
                    return;
                }

            }else if(seleccion.equals("4")){

                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese el telefono de la persona a cargo: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Area area : areas) {

                        if(area.telefono_persona_a_cargo.equals(seleccion)){
                            areasEncontradas.add(area);
                        }
                    }

                    if(areasEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Tipo de area");
                        System.out.println("2. Horario");
                        System.out.println("3. Persona a cargo");
                        System.out.println("4. Telefono persona a cargo");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar el tipo de area de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_tipoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_tipoD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar el horario de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_horarioA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_horarioD());
                            }             

                            enumerador=1;

                            for (Area area : areasEncontradas) { 
                                System.out.println(enumerador+". "+ area);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_personaA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_personaD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("4")){

                            System.out.println("Desea organizar los telefonos de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_telefonoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_telefonoD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }

                        }else{

                            System.out.println("Opción inválida");
                            return;
                        }
                    }else{
                        System.out.println("No se encontraron areas");
                        return;
                    }

                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese el telefono de la persona a cargo: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    

                    for (Area area : areas) {

                        if(area.telefono_persona_a_cargo.toLowerCase().equals(seleccion)){
                            areasEncontradas.add(area);
                        }
                    }

                    if(areasEncontradas.size()!=0){
                        System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                        System.out.println("1. Tipo de area");
                        System.out.println("2. Horario");
                        System.out.println("3. Persona a cargo");
                        System.out.println("4. Telefono persona a cargo");
                        seleccion= input.next();

                        if(seleccion.equals("1")){
                            System.out.println("Desea organizar el tipo de area de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_tipoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_tipoD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }
                        }else if(seleccion.equals("2")){
                            System.out.println("Desea organizar los horarios de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_horarioA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_horarioD());
                            }             

                            enumerador=1;

                            for (Area area : areasEncontradas) { 
                                System.out.println(enumerador+". "+ area);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_personaA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_personaD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("4")){

                            System.out.println("Desea organizar los telefonos de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();

                            if(seleccion.equals("1")){
                                Collections.sort(areasEncontradas, new ComparadorArea_telefonoA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(areasEncontradas, new ComparadorArea_telefonoD());
                            }

                            enumerador=1;
                            for (Area area : areasEncontradas) {
                                System.out.println(enumerador +". "+ area);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                        }
                    }else{
                        System.out.println("No se encontraron areas");
                        return;
                    }
                }else{
                    System.out.println("Opción inválida");
                    return;
                }



            }else if(seleccion.equals("5")){
                mostar_todos=true;
                System.out.println("Seleccione el atributo por al cual desea organizar los resultados: ");
                System.out.println("1. Tipo de area");
                System.out.println("2. Horario");
                System.out.println("3. Persona a cargo");
                System.out.println("4. Telefono persona a cargo");
                seleccion= input.next();



                if(seleccion.equals("1")){
                    System.out.println("Desea organizar los telefonos de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(areas_copia, new ComparadorArea_tipoA());
                    } else if (seleccion.equals("2")) {
                        Collections.sort(areas_copia, new ComparadorArea_tipoD());
                    }

                    enumerador=1;
                    for (Area area : areas_copia) {
                        System.out.println(enumerador +". "+ area);
                        enumerador+=1;
                    }
                }else if(seleccion.equals("2")){
                    System.out.println("Desea organizar los horarios de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();     
                    if(seleccion.equals("1")){

                        Collections.sort(areas_copia, new ComparadorArea_horarioA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(areas_copia, new ComparadorArea_horarioD());
                    }       

                    enumerador=1;

                    for (Area area : areas_copia) { 
                        System.out.println(enumerador+". "+ area);
                        enumerador+=1;
                    }

                }else if(seleccion.equals("3")){
                    System.out.println("Desea organizar los nombres de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(areas_copia, new ComparadorArea_personaA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(areas_copia,new ComparadorArea_personaD());
                    }

                    enumerador=1;
                    for (Area area : areas_copia) {
                        System.out.println(enumerador +". "+ area);
                        enumerador+=1;
                    }
                
                }else if(seleccion.equals("4")){

                    System.out.println("Desea organizar los telefonos de forma : ");
                    System.out.println("1. Ascendente");
                    System.out.println("2. Descendente");
                    seleccion= input.next();

                    if(seleccion.equals("1")){
                        Collections.sort(areasEncontradas, new ComparadorArea_telefonoA());
                    }else if(seleccion.equals("2")){
                        Collections.sort(areasEncontradas, new ComparadorArea_telefonoD());
                    }

                    enumerador=1;
                    for (Area area : areasEncontradas) {
                        System.out.println(enumerador +". "+ area);
                        enumerador+=1;
                    }

                }else{

                    System.out.println("Opción inválida");
                    return;
                }
            }

        }else{
            System.out.println("No hay areas registradas en el sistema");
            return;
        }

        // Aquí se establece la búsqueda sobre las areas
        // Tener presente que se utilizará la función de Valentín de Editar y eliminar
        System.out.println("**********************************************");
        System.out.println("En las areas encontradas desea:");
        System.out.println("1. Editar");
        System.out.println("2. Eliminar");
        System.out.println("3. Salir");
        String seleccion= input.next();

        if(seleccion.equals("1")){
            System.out.print("Ingrese el número de la lista correspondiente a al area: ");
            int option= input.nextInt();
            Area area;
            if(!mostar_todos){
                if(areasEncontradas.size()>=option && option>=1){
                    area= areasEncontradas.get(option-1);  // Aquí obtienes el area a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }else{

                if(areas_copia.size()>=option && option>=1){
                    area= areas_copia.get(option-1);  // Aquí obtienes el area a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }

            
            BuscarEditarArea(area);    
            

        }else if(seleccion.equals("2")){
            System.out.print("Ingrese el número de la lista correspondiente al area: ");
            int option= input.nextInt();
            Area area;
            if(!mostar_todos){
                if(areasEncontradas.size()>=option && option>=1){
                    area= areasEncontradas.get(option-1);  // Aquí obtienes la sede a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }else{

                if(areas_copia.size()>=option && option>=1){
                    area= areas_copia.get(option-1);  // Aquí obtienes la sede a editar de la copia
                }else{
                    System.out.println("Opción inválida");
                    return;
                }
            }
            // Aquí vas a implementar eliminar********************
            BuscarEliminarArea(area);
        }
    }

    public static void BuscarEliminarArea(Area place) {
        System.out.println("¿Seguro que desea eliminar esta area?");
        System.out.println("Y");
        System.out.println("N");
        String option = input.next();
        boolean areaEncontrada = false;
        if (option.equalsIgnoreCase("Y")) {
            Iterator<Area> iterator = areas.iterator();
            while(iterator.hasNext()) {
                Area area = iterator.next();
                if (area.getTipo().equals(place.getTipo())) {
                    areaEncontrada = true;
                    iterator.remove();
                }
            }

            if (!areaEncontrada) {
                System.out.println("No se encontró el area");
                return;
            }else{
                System.out.println("EL area se ha removido satisfactoriamente");
            }
        }
    }

    public static void BuscarEditarArea(Area place) {
        for (Area area : areas) {
            if(area.tipo.equals(place.tipo)){

                String nuevo_tipo = "";
                String nuevo_horario = "";
                String nuevo_persona = "";
                String nuevo_telefono="";

                System.out.println("Tipo de area: "+ area.getTipo());
                System.out.println("Si desea cambiar el tipo, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_tipo = input.next();
                System.out.println("Horario: "+ area.getHorario());
                System.out.println("Si desea cambiar el horario, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_horario = input.next();
                System.out.println("Persona a cargo: "+ area.getPersona_a_cargo());
                System.out.println("Si desea cambiar la persona a cargo, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_persona = input.next();
                System.out.println("Telefono persona a cargo: "+ area.getTelefono_persona_a_cargo());
                System.out.println("Si desea cambiar el tipo, ingrese su nuevo valor");
                System.out.println("en otro caso, digite: N");
                nuevo_telefono = input.next();
                System.out.println("¿Desea guardar los cambios?");
                System.out.println("Y");
                System.out.println("N");
                String option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    if (nuevo_telefono.equalsIgnoreCase("N")) {

                    }else {
                        area.setTipo(nuevo_tipo);
                    }
                    if (nuevo_horario.equalsIgnoreCase("N")) {

                    } else {
                        area.setHorario(nuevo_horario);
                    }
                    if (nuevo_persona.equalsIgnoreCase("N")) {

                    } else {
                    area.setPersona_a_cargo(nuevo_persona);
                    }
                    if (nuevo_telefono.equalsIgnoreCase("N")) {

                    } else {
                    area.setTelefono_persona_a_cargo(nuevo_telefono);
                    }


                }
                System.out.println("Se han realizado los cambios satisfactoriamente");
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
                System.out.println("Persona a cargo: "+ sede.getPersona_a_cargo());
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
        LinkedList<EmpresaDeBasura> empresaDeBasuras_copia;
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

                        if(empresa.nombre.equals(seleccion)){
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
