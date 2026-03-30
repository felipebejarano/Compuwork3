package actividad3compuwork;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VentanaGestion extends JFrame {
    private ArrayList<Empleado> empleados = new ArrayList<>();
    private ArrayList<Departamento> departamentos = new ArrayList<>();
    
    private JTable tablaEmpleados;
    private DefaultTableModel modeloTabla;
    private JComboBox<Departamento> comboDeptos;
    private JTextArea areaSalida;

    public VentanaGestion() {
        setTitle("Sistema Pro de Gestión de Empleados");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Gestión General", crearPanelPrincipal());
        
        add(pestanas, BorderLayout.CENTER);
        
        areaSalida = new JTextArea(6, 20);
        areaSalida.setEditable(false);
        areaSalida.setBackground(new Color(240, 240, 240));
        add(new JScrollPane(areaSalida), BorderLayout.SOUTH);
    }

    private JPanel crearPanelPrincipal() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel controles = new JPanel();
        controles.setLayout(new BoxLayout(controles, BoxLayout.Y_AXIS));
        controles.setBorder(BorderFactory.createTitledBorder("Acciones"));

        
        JTextField txtId = new JTextField(10);
        JTextField txtNombre = new JTextField(10);
        JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Permanente", "Temporal"});
        JTextField txtExtra1 = new JTextField(10); 
        JTextField txtExtra2 = new JTextField(10);

        controles.add(new JLabel("ID:")); controles.add(txtId);
        controles.add(new JLabel("Nombre:")); controles.add(txtNombre);
        controles.add(new JLabel("Tipo:")); controles.add(cbTipo);
        controles.add(new JLabel("Salario/Horas:")); controles.add(txtExtra1);
        controles.add(new JLabel("Bono/Tarifa:")); controles.add(txtExtra2);
        
        
        JButton btnAgregar = new JButton("Crear Empleado");
        JButton btnEliminarEmp = new JButton("Eliminar Empleado");
        JButton btnConsultarEmp = new JButton("Consultar Empleados");

        controles.add(Box.createRigidArea(new Dimension(0, 10)));
        controles.add(btnAgregar);
        controles.add(btnEliminarEmp);
        controles.add(btnConsultarEmp);

        
        JPanel panelDepto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField txtNombreDepto = new JTextField(10);
        JButton btnCrearDepto = new JButton("Nuevo Depto");
        JButton btnEliminarDepto = new JButton("Eliminar Depto");
        JButton btnConsultarDepto = new JButton("Consultar Deptos");
        comboDeptos = new JComboBox<>();
        JButton btnAsignar = new JButton("Asignar a Depto");

        panelDepto.add(new JLabel("Depto:")); panelDepto.add(txtNombreDepto);
        panelDepto.add(btnCrearDepto);
        panelDepto.add(btnEliminarDepto);
        panelDepto.add(btnConsultarDepto);
        panelDepto.add(new JSeparator(SwingConstants.VERTICAL));
        panelDepto.add(comboDeptos);
        panelDepto.add(btnAsignar);

        
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Nombre", "Tipo", "Salario", "Departamento"}, 0);
        tablaEmpleados = new JTable(modeloTabla);

        

        btnAgregar.addActionListener(e -> {
    try {
        String id = txtId.getText();
        String nom = txtNombre.getText();
        
        // Validar que los campos no estén vacíos
        if(id.isEmpty() || nom.isEmpty() || txtExtra1.getText().isEmpty() || txtExtra2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, llena todos los campos.");
            return;
        }

        if(cbTipo.getSelectedItem().equals("Permanente")) {
            double salBase = Double.parseDouble(txtExtra1.getText());
            double bono = Double.parseDouble(txtExtra2.getText());
            // Coincide con: EmpleadoPermanente(id, nombre, salarioBase, bonoAnual)
            empleados.add(new EmpleadoPermanente(id, nom, salBase, bono));
        } else {
            int horas = Integer.parseInt(txtExtra1.getText());
            double tarifa = Double.parseDouble(txtExtra2.getText());
            // Coincide con: EmpleadoTemporal(id, nombre, horasMensuales, tarifaHora)
            empleados.add(new EmpleadoTemporal(id, nom, horas, tarifa));
        }
        
        actualizarTabla();
        areaSalida.append("✓ Empleado creado: " + nom + "\n");
        
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Error: En 'Salario/Horas' y 'Bono/Tarifa' solo se permiten números.");
    }
});
        btnEliminarEmp.addActionListener(e -> {
            int fila = tablaEmpleados.getSelectedRow();
            if (fila != -1) {
                Empleado emp = empleados.remove(fila);
                for(Departamento d : departamentos) d.eliminarEmpleado(emp);
                actualizarTabla();
                areaSalida.append("× Empleado eliminado: " + emp.getNombre() + "\n");
            }
        });

        btnConsultarEmp.addActionListener(e -> {
            areaSalida.append("\n=== LISTA DE EMPLEADOS ===\n");
            for(Empleado emp : empleados) areaSalida.append(emp.toString() + "\n");
        });

        btnCrearDepto.addActionListener(e -> {
            String nom = txtNombreDepto.getText();
            if(!nom.isEmpty()){
                Departamento d = new Departamento(nom);
                departamentos.add(d);
                comboDeptos.addItem(d);
                areaSalida.append("⊕ Depto creado: " + nom + "\n");
            }
        });

        btnEliminarDepto.addActionListener(e -> {
            Departamento d = (Departamento) comboDeptos.getSelectedItem();
            if(d != null) {
                departamentos.remove(d);
                comboDeptos.removeItem(d);
                for(Empleado emp : empleados) if(emp.getDepartamento() == d) /* Limpiar ref */; 
                areaSalida.append("⊗ Depto eliminado: " + d.getNombre() + "\n");
                actualizarTabla();
            }
        });

        btnConsultarDepto.addActionListener(e -> {
            areaSalida.append("\n=== ESTRUCTURA POR DEPARTAMENTOS ===\n");
            for(Departamento d : departamentos) {
                areaSalida.append("- " + d.getNombre() + " (" + d.getEmpleados().size() + " personas)\n");
            }
        });

        btnAsignar.addActionListener(e -> {
            int fila = tablaEmpleados.getSelectedRow();
            Departamento d = (Departamento) comboDeptos.getSelectedItem();
            if(fila != -1 && d != null) {
                empleados.get(fila).asignarDepartamento(d);
                actualizarTabla();
                areaSalida.append("→ Asignado correctamente\n");
            }
        });

        panel.add(controles, BorderLayout.WEST);
        panel.add(panelDepto, BorderLayout.NORTH);
        panel.add(new JScrollPane(tablaEmpleados), BorderLayout.CENTER);

        return panel;
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Empleado emp : empleados) {
            modeloTabla.addRow(new Object[]{emp.getId(), emp.getNombre(), emp.getTipo(), 
                String.format("$%.2f", emp.calcularSalario()), 
                (emp.getDepartamento() != null ? emp.getDepartamento().getNombre() : "Sin asignar")});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaGestion().setVisible(true));
    }
}