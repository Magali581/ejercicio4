import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import java.io.*;

public class AddressBook {
    public static void main(String[] args){

        //Creacion objeto tipo hashMap
        HashMap<String, String> mapa = new HashMap<String, String> ();
        Scanner resp = new Scanner(System.in);

        int opcion, ban = 0;
        String telefono, nombre;

        do {
            try {
                System.out.println("-----------------------------------------");
                System.out.println("Menu");
                System.out.println("Seleccione una opcion");
                System.out.println("1.- cargar contactos");
                System.out.println("2.- Guardar cambios");
                System.out.println("3.- mostrar contactos de la agenda");
                System.out.println("4.- crear un nuevo contacto");
                System.out.println("5.- borrar un contacto");
                System.out.println("6.- salir");
                System.out.println("----------------------------------------");
                opcion = resp.nextInt();

                //metodos que se llamaran segun la opcion
                switch (opcion) {

                    case 1: load(mapa);
                    break;

                    case 2: save(mapa);
                    break;

                    case 3: list(mapa);
                    break;

                    case 4:
                        System.out.println("Inserte el nuevo telefono: ");
                        telefono = resp.next();
                        System.out.println("Inserte el nombre del contacto: ");
                        nombre = resp.next();
                        create(mapa,telefono,nombre);
                        break;

                    case 5:
                        System.out.println("Inserte el telefono a eliminar: ");
                        telefono = resp.next();
                        delete(mapa, telefono);
                        break;

                    case 6:
                        System.out.println("Fin de el programa");
                        ban = 1;
                        break;

                    default:
                        System.out.println("Opción incorrecta\n");
                        break;

                }

                }

            catch (Exception e){
                System.out.println("Error, vuelva a iniciar el programa");
                break;
            }

        }
        while (ban == 0);

    }
    // metodos
// metodo list
    public static void list(HashMap<String, String>mapa) {
        Iterator<String> iterator = mapa.keySet().iterator();

        System.out.println("Contactos: ");
        System.out.println("Telefono \t|\tNombre  ");

        while (iterator.hasNext())
        {
            String llave = iterator.next();
            System.out.println("  "+llave+"\t|\t"+mapa.get(llave)); // se muestra el contenido del hashmap

        }
    }
       // metodo create
    public static void create(HashMap<String, String> mapa, String tel, String nom){
        if (mapa.containsKey(tel)) {
            System.out.println("No se puede registrar dos veces el mismo numero");
        }
        else {
            mapa.put(tel, nom);
            System.out.println("Contacto creado correctamente");
        }
    }
    //metodo delete

    public static void delete(HashMap<String, String> mapa, String tel)
    {
        if (mapa.containsKey(tel)) {
            System.out.println("El contacto "  +mapa.get(tel) + " ha sido eliminado.");
            mapa.remove(tel);
        }
        else
            System.out.println("El telefono no existe");
    }
    // metodo de cargar datos desde archivo csv
    public static void load(HashMap<String, String> m)
    {
        String inputFilename = "C:\\Users\\Magali\\IdeaProjects\\Actividad4\\src\\contactos.csv";
        String a [];

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(inputFilename));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                a = line.split(",");
                m.put(a[0],a[1]);
            }
        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                    System.out.println(" Contactos cargados  ");
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }

    }

    //metodo de guardar datos en archivo csv
    public static void save(HashMap<String, String> m)
    {
        Iterator<String> iterator = m.keySet().iterator();
        String inputFilename = "C:\\Users\\Magali\\IdeaProjects\\Actividad4\\src\\contactos.csv";
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(inputFilename));

            while(iterator.hasNext())
            {
                String llave = iterator.next();


                bufferedWriter.write(llave+","+m.get(llave)+"\n");
            }

        }
        catch(IOException e) {
            System.out.println("IOException catched while writing: " + e.getMessage());
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                    System.out.println(" \nCambios guardados  ");
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }
}

