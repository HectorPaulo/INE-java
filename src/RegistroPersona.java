import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class RegistroPersona extends JFrame {
    private JTextField nombreField;
    private JTextField apellidoPaternoField;
    private JTextField apellidoMaternoField;
    private JTextField direccionField;
    private JTextField edadField;
    private JTextField curpField;
    private JCheckBox ineCheckBox;

    public RegistroPersona() {
        setTitle("Registro de Persona");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        nombreField = new JTextField(20);
        apellidoPaternoField = new JTextField(20);
        apellidoMaternoField = new JTextField(20);
        direccionField = new JTextField(20);
        edadField = new JTextField(20);
        curpField = new JTextField(20);
        ineCheckBox = new JCheckBox("¿Cuenta con INE?");

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });

        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Apellido Paterno:"));
        panel.add(apellidoPaternoField);
        panel.add(new JLabel("Apellido Materno:"));
        panel.add(apellidoMaternoField);
        panel.add(new JLabel("Dirección:"));
        panel.add(direccionField);
        panel.add(new JLabel("Edad:"));
        panel.add(edadField);
        panel.add(new JLabel("CURP:"));
        panel.add(curpField);
        panel.add(ineCheckBox);
        panel.add(guardarButton);

        add(panel);
        setVisible(true);
    }

    private void guardarDatos() {
        String nombre = nombreField.getText().trim();
        String apellidoPaterno = apellidoPaternoField.getText().trim();
        String apellidoMaterno = apellidoMaternoField.getText().trim();
        String direccion = direccionField.getText().trim();
        String edadStr = edadField.getText().trim();
        String curp = curpField.getText().trim();
        boolean tieneIne = ineCheckBox.isSelected();

        // Validar que la edad sea un número
        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una edad válida.");
            return;
        }

        // Validar que el CURP tenga 18 caracteres
        if (curp.length() != 18) {
            JOptionPane.showMessageDialog(this, "El CURP debe tener exactamente 18 caracteres.");
            return;
        }

        // Verificar si la persona es mayor de edad y tiene INE
        if (edad >= 18 && tieneIne) {
            // Exportar los datos a un archivo .txt
            try (FileWriter writer = new FileWriter("datos_persona.txt")) {
                writer.write("Nombre: " + nombre + "\n");
                writer.write("Apellido Paterno: " + apellidoPaterno + "\n");
                writer.write("Apellido Materno: " + apellidoMaterno + "\n");
                writer.write("Dirección: " + direccion + "\n");
                writer.write("Edad: " + edad + "\n");
                writer.write("CURP: " + curp + "\n");
                writer.write("Cuenta con INE: Sí\n");
                JOptionPane.showMessageDialog(this, "Datos guardados correctamente.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar los datos: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "La persona no es mayor de edad o no cuenta con INE.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegistroPersona();
            }
        });
    }
}
