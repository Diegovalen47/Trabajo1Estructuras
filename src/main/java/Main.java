import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.google.gson.*;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

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

        try(FileReader reader = new FileReader("sedes.json")) {
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
        String jsonStringEmpresasDeBasura = gson.toJson(empresasDeBasuras);

        try (FileWriter file = new FileWriter("empresasDeBasuras.json")) {

            file.write(jsonStringEmpresasDeBasura);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


        String jsonStringSedes = gson.toJson(sedes);

        try (FileWriter file = new FileWriter("sedes.json")) {

            file.write(jsonStringSedes);
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
        int telefono = input.nextInt();

        for (Sede sede : sedes) {
            if (sede.getTelefono() == telefono) {
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
        sedes.add(new Sede(telefono,direccion,personaAcargo));
        System.out.println("Sede creada satisfactoriamente");

    }


    public static void EditarSedes() {
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
                int nuevo_telefono = 0;
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
                        System.out.println("Persona a cargo: "+ sede.getPersona_a_cargo());
                        System.out.println("Si desea cambiar la persona a cargo, ingrese su nuevo valor");
                        System.out.println("en otro caso, digite: N");
                        nueva_persona_a_cargo = input.next();
                        System.out.println("Teléfono: "+ sede.getTelefono());
                        System.out.println("Si desea cambiar el teléfono, ingrese su nuevo valor");
                        System.out.println("en otro caso, digite: 0");
                        nuevo_telefono = input.nextInt();
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
                            if (nuevo_telefono == 0) {

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
                int telefono = input.nextInt();
                int nuevo_telefono = 0;
                String nueva_direccion = "";
                String nueva_persona_a_cargo = "";
                boolean SedeEncontrada = false;
                for (Sede sede : sedes) {
                    if (sede.getTelefono() == telefono) {
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
                        System.out.println("en otro caso, digite: 0");
                        nuevo_telefono = input.nextInt();
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
                            if (nuevo_telefono == 0) {

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
                System.out.println("¿Seguro que desea eliminar esta sede");
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
                int telefono = input.nextInt();
                System.out.println("¿Seguro que desea eliminar esta sede");
                System.out.println("Y");
                System.out.println("N");
                option = input.next();
                boolean sedeEncontrada = false;
                if (option.equalsIgnoreCase("Y")) {
                    Iterator<Sede> iterator = sedes.iterator();
                    while(iterator.hasNext()) {
                        Sede sede = iterator.next();
                        if (sede.getTelefono() == telefono) {
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
                //BuscarSedes();
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

    public static void BuscarEmpresasBasura() {
        LinkedList<EmpresaDeBasura> empresaDeBasuras_copia= new LinkedList<>();
        empresaDeBasuras_copia= (LinkedList<EmpresaDeBasura>) empresasDeBasuras.clone();

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
                    LinkedList<EmpresaDeBasura> empresasEncontradas= new LinkedList<>();

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

                            int enumerador=1;
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

                            int enumerador=1;

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

                            int enumerador=1;
                            for (EmpresaDeBasura empresa : empresasEncontradas) {
                                System.out.println(enumerador +". "+ empresa);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                        }
                    }else{
                        System.out.println("No se encontraron empresas en la cuidad seleccionada");
                    }

                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese la cuidad: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    LinkedList<EmpresaDeBasura> empresasEncontradas= new LinkedList<>();

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

                            int enumerador=1;
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

                            int enumerador=1;

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

                            int enumerador=1;
                            for (EmpresaDeBasura empresa : empresasEncontradas) {
                                System.out.println(enumerador +". "+ empresa);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                        }
                    }else{
                        System.out.println("No se encontraron empresas en la cuidad seleccionada");
                    }
                }else{
                    System.out.println("Opción inválida");
                }



            }else if(seleccion.equals("2")){
                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese la cuidad: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    LinkedList<EmpresaDeBasura> empresasEncontradas= new LinkedList<>();

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

                            int enumerador=1;
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

                            int enumerador=1;

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

                            int enumerador=1;
                            for (EmpresaDeBasura empresa : empresasEncontradas) {
                                System.out.println(enumerador +". "+ empresa);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                        }
                    }else{
                        System.out.println("No se encontraron empresas en la cuidad seleccionada");
                    }

                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese la cuidad: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    LinkedList<EmpresaDeBasura> empresasEncontradas= new LinkedList<>();

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

                            int enumerador=1;
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

                            int enumerador=1;

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

                            int enumerador=1;
                            for (EmpresaDeBasura empresa : empresasEncontradas) {
                                System.out.println(enumerador +". "+ empresa);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                        }
                    }else{
                        System.out.println("No se encontraron empresas en la cuidad seleccionada");
                    }
                }else{
                    System.out.println("Opción inválida");
                }

            }else if(seleccion.equals("3")){

                seleccion=atributo_string_buscar();

                if(seleccion.equals("1")){
                    System.out.print("Ingrese el nombre del gerente: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    LinkedList<EmpresaDeBasura> empresasEncontradas= new LinkedList<>();

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

                            int enumerador=1;
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
                            int enumerador=1;

                            for (EmpresaDeBasura empresa : empresasEncontradas) { 
                                System.out.println(enumerador+". "+ empresa);
                                enumerador+=1;
                            }

                        }else if(seleccion.equals("3")){
                            System.out.println("Desea organizar los nombres de forma : ");
                            System.out.println("1. Ascendente");
                            System.out.println("2. Descendente");
                            seleccion= input.next();              // No se organizan porque se buscó por gerente

                            if(seleccion.equals("1")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_gerenteA());
                            }else if(seleccion.equals("2")){
                                Collections.sort(empresasEncontradas, new ComparadorEmpresa_gerenteD());
                            }

                            int enumerador=1;
                            for (EmpresaDeBasura empresa : empresasEncontradas) {
                                System.out.println(enumerador +". "+ empresa);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                        }
                    }else{
                        System.out.println("No se encontraron empresas en la cuidad seleccionada");
                    }

                }else if(seleccion.equals("2")){
                    System.out.print("Ingrese el nombre del gerente: ");
                    input.nextLine();
                    seleccion= input.nextLine();
                    LinkedList<EmpresaDeBasura> empresasEncontradas= new LinkedList<>();

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

                            int enumerador=1;
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

                            int enumerador=1;

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

                            int enumerador=1;
                            for (EmpresaDeBasura empresa : empresasEncontradas) {
                                System.out.println(enumerador +". "+ empresa);
                                enumerador+=1;
                            }
                        }else{

                            System.out.println("Opción inválida");
                        }
                    }else{
                        System.out.println("No se encontraron empresas en la cuidad seleccionada");
                    }
                }else{
                    System.out.println("Opción inválida");
                }


            }else if(seleccion.equals("4")){
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

                    int enumerador=1;
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

                    int enumerador=1;

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

                    int enumerador=1;
                    for (EmpresaDeBasura empresa : empresaDeBasuras_copia) {
                        System.out.println(enumerador +". "+ empresa);
                        enumerador+=1;
                    }
                }else{

                    System.out.println("Opción inválida");
                }
            }

        }else{
            System.out.println("No hay empresas de basura registradas en el sistema");
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
