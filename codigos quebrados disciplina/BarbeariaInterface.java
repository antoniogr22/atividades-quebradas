import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


class Agendamento {
    String barbeiro, cliente, data, hora;
    public Agendamento(String b, String c, String d, String h) {
        this.barbeiro = b; this.cliente = c; this.data = d; this.hora = h;
    }
}


class BancoBarbearia {
    public static List<Agendamento> agenda = new ArrayList<>();
    static {
        agenda.add(new Agendamento("Marcio", "Alan Motta", "25/03/2026", "10:00"));
    }
}


public class BarbeariaInterface extends JFrame {
    private DefaultTableModel modelo;
    private JTextField txtCliente, txtData;
    private JComboBox<String> cbBarbeiro, cbHora;

    public BarbeariaInterface() {
        setTitle("Senac TDS - Gestão de Barbearia");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        // Painel de Agendamento (Esquerda)
        JPanel pnlCadastro = new JPanel(new GridLayout(10, 1, 5, 5));
        pnlCadastro.setBorder(BorderFactory.createTitledBorder("Marcar Horário"));

        cbBarbeiro = new JComboBox<>(new String[]{"Marcio", "Augusto", "Rafael"});
        txtCliente = new JTextField();
        txtData = new JTextField("25/03/2026");
        cbHora = new JComboBox<>(new String[]{"09:00", "10:00", "11:00", "14:00", "15:00", "16:00"});
        JButton btnAgendar = new JButton("Agendar (F1)");

        pnlCadastro.add(new JLabel("Escolha o Barbeiro:")); pnlCadastro.add(cbBarbeiro);
        pnlCadastro.add(new JLabel("Nome do Cliente:")); pnlCadastro.add(txtCliente);
        pnlCadastro.add(new JLabel("Data (DD/MM/AAAA):")); pnlCadastro.add(txtData);
        pnlCadastro.add(new JLabel("Horário:")); pnlCadastro.add(cbHora);
        pnlCadastro.add(new JLabel("")); pnlCadastro.add(btnAgendar);

        // Tabela de Agendamentos (Direita)
        modelo = new DefaultTableModel(new String[]{"Barbeiro", "Cliente", "Data", "Hora"}, 0);
        JTable tabela = new JTable(modelo);
        atualizarTabela();

        btnAgendar.addActionListener(e -> processarAgendamento());

        add(pnlCadastro, BorderLayout.WEST);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
    }

    private void processarAgendamento() {
        String barbeiro = (String) cbBarbeiro.getSelectedItem();
        String cliente = txtCliente.getText();
        String data = txtData.getText();
        String hora = (String) cbHora.getSelectedItem();

        for(Agendamento a : BancoBarbearia.agenda){

            if (a.barbeiro.equals(barbeiro) && a.data.equals(data) && a.hora.equals(hora)){
                JOptionPane.showMessageDialog(this, "horário já está agendado");
                return;
                
            }
        }

        Agendamento novo = new Agendamento(barbeiro, cliente, data, hora);
        BancoBarbearia.agenda.add(novo);

        atualizarTabela();

   
        JOptionPane.showMessageDialog(this, "Agendamento realizado!");
    }

    private void atualizarTabela() {
        // Dica:modelo.setRowCount(0) resolve um BUG.
        for (Agendamento a : BancoBarbearia.agenda) {
            modelo.addRow(new Object[]{a.barbeiro, a.cliente, a.data, a.hora});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BarbeariaInterface().setVisible(true));
    }
}
