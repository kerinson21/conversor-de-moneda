package intefaces;

import models.Pais;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;

public class Conversor extends javax.swing.JFrame{
    private JPanel principal;
    private JLabel lbTitle;
    private JButton btnConvertir;
    private JComboBox<String> cbCantidad;
    private JLabel lbCantidad;
    private JPanel jpHome;
    private JTextField textCantidad;
    private JLabel lbResultado;
    private JLabel lbCambio;
    private JLabel lbTextCambio;
    private ArrayList<Pais> paises;

    private ActionListener calcular = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] cortarString = new String[2];
            double cantidad = 0;
            cortarString = cbCantidad.getSelectedItem().toString().split(",,");

            if(textCantidad.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Debes de ingresar la cantidad de monedas que deseas convertir a Quetzales");
            }else{
                lbTextCambio.setText("Tipo de Cambio: ");
                cantidad = Double.valueOf(textCantidad.getText());
                obtener(cortarString[1],cantidad);
            }
        }
    };

    public Conversor(){
        this.setSize(1080,720);
        this.setTitle("CONVERSOR");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(principal);
        initComponents();
        btnConvertir.addActionListener(calcular);
        textCantidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int key = e.getKeyChar();
                boolean num = key >=48 && key <=57 || key==8 || key==10;
                if(!num) {
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Utiliza solo Números");
                }
            }
        });
    }
    private void initComponents(){
        this.textCantidad.setColumns(20);
        this.cbCantidad.setSize(25,25);
        this.paises = crearLista();
        for (Pais p : paises) {
            this.cbCantidad.addItem(p.getNombre() + ",," + p.getMoneda());
        }
    }
    private void obtener(String moneda, double cantidad){
        double [] datos = convertir(moneda,cantidad);
        lbResultado.setText("Q. " + String.valueOf(datos[0]));
        lbCambio.setText("Q. "+ String.valueOf(datos[1]));
    }
    private ArrayList<Pais> crearLista(){
        ArrayList<Pais> paises = new ArrayList<Pais>();
        paises.add(new Pais("Estados Unidos", "USD",7.82));
        paises.add(new Pais("España", "EUR",8.29));
        paises.add(new Pais("Inglaterra", "JPY",9.42));
        paises.add(new Pais("Japón", "EUR",0.058));
        paises.add(new Pais("Corea del Sur", "KRW",0.0060));
        return paises;
    }
    private double[] convertir(String moneda, double cantidad){
        double[] datos = new double[2];
        for (Pais p: paises) {
            if(Objects.equals(moneda, p.getMoneda())){
                datos[0]= p.getConversion() * cantidad;
                datos[1] = p.getConversion();
            }
        }
        return datos;
    }
}
