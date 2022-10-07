
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Datos extends JFrame {

    private static File archivo = null;
    private static FileWriter fichero = null;
    private static FileReader fr = null;
    private static BufferedReader br = null;
    private static PrintWriter escribe = null;
    int verificacionContrasena = 0;

    TextField text = null, text2 = null;
    JPanel panel;
    JButton boton1, boton2, boton3, boton4, boton5;
    int eliminar = 0;
    String Usuario = "", Contrasena = "";
    // File archivo;//manipular archivo
    FileWriter escribir;//escribir enm el archivo
    PrintWriter linea;// esvribir en el archivo

    public Datos() {

        setLayout(new GridLayout(14, 7, 14, 7));

        iniciarComponentes();
        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void iniciarComponentes() {
        creararchivo();
        ColocarPanel();
        TextField();
        TextField2();
        ColocarBoton();
        Validar();

    }

    private void ColocarPanel() {

        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);

    }

    private void TextField() {
// usuario
        add(new JLabel("Usuario"));
        add(text = new TextField(10));
        //panel.add(text);

    }

    private void TextField2() {

        add(new JLabel("Contrasena"));
        add(text2 = new TextField(10));
        //   panel.add(text2);

    }

    private void creararchivo() {
        archivo = new File("Base_de_datos.txt");//preparar archivo
        if (!archivo.exists()) {

            try {

                //no existe  archivo
                archivo.createNewFile();

            } catch (IOException ex) {

                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void Comprobar() {

        String usuario = text.getText();
        int paso = 0;
        FileReader leer;
        BufferedReader almacenar;
        String cadena, nombre = "", email = "";

        archivo = new File("Base_de_datos.txt");

        try {

            leer = new FileReader(archivo);
            almacenar = new BufferedReader(leer);
            cadena = "";
            while (cadena != null) {

                try {
                    cadena = almacenar.readLine();
                    nombre = cadena;
                    cadena = almacenar.readLine();
                    // email=cadena;

                    if (usuario.equals(nombre)) {

                        JOptionPane.showMessageDialog(null, "Este usuario ya existe");

                        paso = 1;// señalador
                    }

                } catch (IOException ex) {
                    Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            try {
                almacenar.close();
                leer.close();
            } catch (IOException ex) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {

            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (paso == 0) {

            Almacenar1();

        }

    }

    private void Almacenar1() {

        archivo = new File("Base_de_datos.txt");//preparar archivo

        //existe el archivo
        try {

            Usuario = text.getText();
            Contrasena = text2.getText();

            escribir = new FileWriter(archivo, true);
            linea = new PrintWriter(escribir);

            // esvribimos en el archivo
            linea.println(Usuario);
            linea.println(Contrasena);
            linea.close();
            escribir.close();
            JOptionPane.showMessageDialog(null, "Usuario registrado");
            JOptionPane.showMessageDialog(null, "Su usuario es: " + Usuario + "\nSu contrasena es: " + Contrasena);

        } catch (IOException ex) {

            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void Almacenar2() {

        //  Comprobar();
        archivo = new File("Base_de_datos.txt");//preparar archivo

        //existe el archivo
        try {

            Usuario = text.getText();
            Contrasena = text2.getText();

            escribir = new FileWriter(archivo, true);
            linea = new PrintWriter(escribir);

            // esvribimos en el archivo
            linea.println(Usuario);
            linea.println(Contrasena);
            linea.close();
            escribir.close();
            JOptionPane.showMessageDialog(null, "Tu usuario se ha modificado");
            JOptionPane.showMessageDialog(null, "Tu nuevo usuario es: " + Usuario + "\nTu nueva contrasena es: " + Contrasena);

        } catch (IOException ex) {

            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void Registro() {

        String usuario = text.getText();
        String clave = text2.getText();
        FileReader leer;
        BufferedReader almacenar;
        String cadena, nombre, email;
        int verificacion = 0;

        archivo = new File("Base_de_datos.txt");

        try {

            int registro = 1;
            leer = new FileReader(archivo);
            almacenar = new BufferedReader(leer);
            cadena = "";
            while (cadena != null) {

                try {
                    cadena = almacenar.readLine();
                    nombre = cadena;
                    cadena = almacenar.readLine();
                    email = cadena;

                    if (usuario.equals(nombre)) {

                        verificacion = 1;

                        if (clave.equals(email)) {

                            JOptionPane.showMessageDialog(null, "Bienvenido al sistema");

                            eliminar = registro;

                            verificacionContrasena = 1;

                        } else {

                            verificacionContrasena = 2;

                        }

                    }

                    registro++;

                } catch (IOException ex) {
                    Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            try {
                almacenar.close();
                leer.close();
            } catch (IOException ex) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {

            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }

        ////////////////// error al entrar
        if (verificacion == 0) {

            JOptionPane.showMessageDialog(null, "Este usuario no se encuentra registrado\nPor favor registrese");

        }

        if (verificacion == 1 && verificacionContrasena == 2) {

            JOptionPane.showMessageDialog(null, "Contrasena incorrecta");

        }

    }

    private void EliminarUsuario() {

        if (verificacionContrasena == 0) {

            JOptionPane.showMessageDialog(null, "ERROR, Ingresa tu usuario y entra\n"
                    + "al sistema para ejecutar esta accion");
        } else {

            LeerArchivo();

            int n = eliminar;

            n = n + (n - 1);

            BorrarLinea(n);
            GuardarArchivo();
            LeerArchivo();
        }

        verificacionContrasena = 0;

    }

    public static void GuardarArchivo() {
        try {
            fichero = new FileWriter("Base_de_datos.txt");
            escribe = new PrintWriter(fichero);
            for (int i = 0; i < lineas.size(); i++) {
                escribe.println(lineas.elementAt(i));
            }
            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static Vector lineas = new Vector();

    public static void LeerArchivo() {

        try {
            archivo = new File("Base_de_datos.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void BorrarLinea(int n) {
        try {
            archivo = new File("Base_de_datos.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            int cont = 0;
            String completo = "";
            while ((linea = br.readLine()) != null) {
                cont++;
                if (cont != n) {

                    if (cont != n + 1) {
                        lineas.addElement(linea);//AGREGAR LINEASS A UN VECTOR
                    }
                }

            }

            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        JOptionPane.showMessageDialog(null, "El Usuario Ha Sido Eliminado");
    }

    public static void BorrarLinea2(int n) {
        try {
            archivo = new File("Base_de_datos.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            int cont = 0;
            String completo = "";
            while ((linea = br.readLine()) != null) {
                cont++;
                if (cont != n) {

                    if (cont != n + 1) {
                        lineas.addElement(linea);//AGREGAR LINEASS A UN VECTOR
                    }
                }

            }

            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void BorrarLinea3(int n) {
        try {
            archivo = new File("Base_de_datos.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            int cont = 0;
            String completo = "";
            while ((linea = br.readLine()) != null) {
                cont++;
                if (cont != n) {

                    if (cont != n + 1) {
                        lineas.addElement(linea);//AGREGAR LINEASS A UN VECTOR
                    }
                }

            }

            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        JOptionPane.showMessageDialog(null, "Ingresa tu nuevo usuario\n Ingresa tu nueva contrasena"
                + "\n Y al final acepte la modificacion");
    }

    private void Nuevousuario() {

        if (verificacionContrasena == 0) {

            JOptionPane.showMessageDialog(null, "ERROR, Ingresa tu usuario y entra\n"
                    + "al sistema para ejecutar esta accion");

        } else {
            //dijfhffhgegnweungnwegwoq

            LeerArchivo();

            int n = eliminar;

            n = n + (n - 1);

            BorrarLinea3(n);
            GuardarArchivo();
            LeerArchivo();

        }

    }

    private void Modifusuario() {

        if (verificacionContrasena == 0) {

            JOptionPane.showMessageDialog(null, "ERROR,Primero modifica tu usuaruio\n"
                    + "para ejecutar esta accion");

        } else {

            String usuario = text.getText();
            int paso = 0;
            FileReader leer;
            BufferedReader almacenar;
            String cadena, nombre, email;

            archivo = new File("Base_de_datos.txt");

            try {

                leer = new FileReader(archivo);
                almacenar = new BufferedReader(leer);
                cadena = "";
                while (cadena != null) {

                    try {
                        cadena = almacenar.readLine();
                        nombre = cadena;
                        cadena = almacenar.readLine();
                        // email=cadena;

                        if (usuario.equals(nombre)) {

                            JOptionPane.showMessageDialog(null, "Este usuario ya existe intenta con \n otro nombre");

                            paso = 1;// señalador
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                try {
                    almacenar.close();
                    leer.close();
                } catch (IOException ex) {
                    Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (FileNotFoundException ex) {

                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (paso == 0) {

                Almacenar2();

            }
        }
    }

    private void EliminarUsuario2() {

        if (verificacionContrasena == 0) {

            JOptionPane.showMessageDialog(null, "ERROR, Ingresa tu usuario y entra\n"
                    + "al sistema para ejecutar esta accion");

        } else {

            LeerArchivo();

            int n = eliminar;

            n = n + (n - 1);

            BorrarLinea2(n);
            GuardarArchivo();
            LeerArchivo();
        }

    }

    private void ColocarBoton() {

        boton1 = new JButton("Validar Usuario");
        add(boton1);
        // panel.add(boton1);
        boton2 = new JButton("Registrarme");
        add(boton2);
        boton3 = new JButton("Eliminar Usuario");
        add(boton3);
        boton4 = new JButton("Modificar usuario");
        add(boton4);
        boton5 = new JButton("Aceptar modificacion");
        add(boton5);

    }

    private void Validar() {

        ActionListener oyente = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Registro();

            }
        };

        boton1.addActionListener(oyente);

        ActionListener oyente1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Comprobar();

            }
        };

        boton2.addActionListener(oyente1);

        ActionListener oyente2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                EliminarUsuario();

            }
        };

        boton3.addActionListener(oyente2);

        ActionListener oyente3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Nuevousuario();

            }
        };

        boton4.addActionListener(oyente3);

        ActionListener oyente4 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Modifusuario();

            }
        };

        boton5.addActionListener(oyente4);

    }

    public static void main(String[] args) {

        Datos v1 = new Datos();

        Datos frame = new Datos();
        frame.setTitle("Login");
        frame.setSize(300, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}



