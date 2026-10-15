package vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelos.TipoEnvio;

public class LogisticaVista extends JFrame {

    private static final int TAMANO_ICONO = 86;

    private JTable tblEnvios;
    private JPanel pnlEditarEnvio;
    private JTextField txtCodigo, txtCliente, txtPeso, txtDistancia;
    private JComboBox<TipoEnvio> cmbTipoEnvio;
    private JButton btnAgregarEnvio, btnRetirarEnvio, btnGuardarEnvio;

    public LogisticaVista() {
        setSize(600, 400);
        setTitle("Operador Logístico");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JToolBar tbLogistica = new JToolBar();
        btnAgregarEnvio = crearBoton("/iconos/AgregarEnvio.png", "Agregar envío");
        btnAgregarEnvio.addActionListener(evt -> btnAgregarEnvioClick());
        tbLogistica.add(btnAgregarEnvio);

        btnRetirarEnvio = crearBoton("/iconos/RetirarEnvio.png", "Retirar envío");
        tbLogistica.add(btnRetirarEnvio);

        pnlEditarEnvio = new JPanel();
        pnlEditarEnvio.setPreferredSize(new Dimension(pnlEditarEnvio.getWidth(), 100));
        pnlEditarEnvio.setLayout(null);

        JLabel lblCodigo = new JLabel("Número");
        lblCodigo.setBounds(10, 10, 100, 25);
        pnlEditarEnvio.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(110, 10, 100, 25);
        pnlEditarEnvio.add(txtCodigo);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, 40, 100, 25);
        pnlEditarEnvio.add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(110, 40, 100, 25);
        pnlEditarEnvio.add(txtCliente);

        JLabel lblPeso = new JLabel("Peso");
        lblPeso.setBounds(10, 70, 100, 25);
        pnlEditarEnvio.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(110, 70, 100, 25);
        pnlEditarEnvio.add(txtPeso);

        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(220, 10, 100, 25);
        pnlEditarEnvio.add(lblTipo);

        cmbTipoEnvio = new JComboBox<>(TipoEnvio.values());
        cmbTipoEnvio.setBounds(320, 10, 100, 25);
        pnlEditarEnvio.add(cmbTipoEnvio);

        JLabel lblDistancia = new JLabel("Distancia en Km");
        lblDistancia.setBounds(220, 40, 100, 25);
        pnlEditarEnvio.add(lblDistancia);

        txtDistancia = new JTextField();
        txtDistancia.setBounds(320, 40, 100, 25);
        pnlEditarEnvio.add(txtDistancia);

        btnGuardarEnvio = new JButton("Guardar");
        btnGuardarEnvio.setBounds(220, 70, 100, 25);
        pnlEditarEnvio.add(btnGuardarEnvio);

        JButton btnCancelarEnvio = new JButton("Cancelar");
        btnCancelarEnvio.setBounds(320, 70, 100, 25);
        btnCancelarEnvio.addActionListener(evt -> ocultarEdicionEnvio());
        pnlEditarEnvio.add(btnCancelarEnvio);

        pnlEditarEnvio.setVisible(false);

        tblEnvios = new JTable();
        JScrollPane spListaEnvios = new JScrollPane(tblEnvios);

        JPanel pnlEnvios = new JPanel(new BorderLayout());
        pnlEnvios.add(pnlEditarEnvio, BorderLayout.NORTH);
        pnlEnvios.add(spListaEnvios, BorderLayout.CENTER);

        add(tbLogistica, BorderLayout.NORTH);
        add(pnlEnvios, BorderLayout.CENTER);
    }


    private JButton crearBoton(String rutaIcono, String texto) {
        JButton boton = new JButton();
        URL url = getClass().getResource(rutaIcono);
        if (url != null) {
            Image imagen = new ImageIcon(url).getImage()
                    .getScaledInstance(TAMANO_ICONO, TAMANO_ICONO, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(imagen));
        } else {
            boton.setText(texto);
        }
        boton.setToolTipText(texto);
        return boton;
    }


    public String getCodigo() {
        return txtCodigo.getText().trim();
    }

    public String getCliente() {
        return txtCliente.getText().trim();
    }

    public double getPeso() {
        return leerNumero(txtPeso);
    }

    public double getDistancia() {
        return leerNumero(txtDistancia);
    }

    public TipoEnvio getTipoSeleccionado() {
        return (TipoEnvio) cmbTipoEnvio.getSelectedItem();
    }

    private double leerNumero(JTextField campo) {
        try {
            return Double.parseDouble(campo.getText().trim().replace(',', '.'));
        } catch (NumberFormatException ex) {
            return 0;
        }
    }


    public void setGuardarEnvioClick(ActionListener escuchadorEvento) {
        btnGuardarEnvio.addActionListener(escuchadorEvento);
    }

    public void setRetirarEnvioClick(ActionListener escuchadorEvento) {
        btnRetirarEnvio.addActionListener(escuchadorEvento);
    }


    public void mostrarEnvios(String[][] datos, String[] encabezados) {
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tblEnvios.setModel(dtm);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }


    public String solicitarCodigo() {
        String codigo = JOptionPane.showInputDialog(this, "Código del envío a retirar:");
        return codigo == null ? null : codigo.trim();
    }

    public void ocultarEdicionEnvio() {
        pnlEditarEnvio.setVisible(false);
    }

    public void limpiarFormulario() {
        txtCodigo.setText("");
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
        cmbTipoEnvio.setSelectedIndex(0);
    }


    private void btnAgregarEnvioClick() {
        pnlEditarEnvio.setVisible(true);
    }
}