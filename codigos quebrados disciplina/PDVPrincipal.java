import javax.swing.*; 
import javax.swing.table.DefaultTableModel; 
import java.awt.*; 
import java.util.ArrayList; 
 
// --- MODELO E BANCO FAKE --- 
class Produto { 
    int id; String nome; double preco; int estoque; 
    public Produto(int id, String nome, double preco, int estoque) { 
        this.id = id; this.nome = nome; this.preco = preco; this.estoque = estoque; 
    } 
} 
 
class Banco { 
    public static java.util.List<Produto> produtos = new ArrayList<>(); 
    static { 
        produtos.add(new Produto(1, "Monitor Gamer", 800.0, 10)); 
        produtos.add(new Produto(2, "Teclado Mecânico", 250.0, 5)); 
        produtos.add(new Produto(3, "Mouse Wireless", 120.0, 15)); 
 produtos.add(new Produto(4, "Headset Cloud Alpha", 450.0, 8));  
    produtos.add(new Produto(5, "Cadeira Ergonômica", 1200.0, 3));    
    produtos.add(new Produto(6, "Webcam Full HD", 320.0, 12));        
    produtos.add(new Produto(7, "Pad Mouse XL", 80.0, 25));           
    produtos.add(new Produto(8, "SSD 1TB NVMe", 580.0, 2));           
    } 
} 
 
public class PDVPrincipal extends JFrame { 
    private DefaultTableModel modeloTabela; 
    private JLabel lblTotal; 
    private JTextField txtId, txtQtd; 
    private double totalVenda = 0; 
 
    public PDVPrincipal() { 
        setTitle("Senac TDS - Sistema de Ponto de Venda"); 
        setSize(800, 500); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setLayout(new BorderLayout(10, 10)); 
 
       
        JPanel pnlEntrada = new JPanel(new GridLayout(6, 1, 5, 5)); 
        pnlEntrada.setBorder(BorderFactory.createTitledBorder("Nova Venda")); 
        txtId = new JTextField(); txtQtd = new JTextField(); 
        JButton btnAdd = new JButton("Adicionar Item (F1)"); 
        pnlEntrada.add(new JLabel("ID do Produto:")); pnlEntrada.add(txtId); 
        pnlEntrada.add(new JLabel("Quantidade:")); pnlEntrada.add(txtQtd); 
        pnlEntrada.add(new JLabel("")); pnlEntrada.add(btnAdd); 
 
       
        String[] colunas = {"ID", "Produto", "Qtd", "Unitário", "Subtotal"}; 
        modeloTabela = new DefaultTableModel(colunas, 0); 
        JTable tabela = new JTable(modeloTabela); 
         
       
        lblTotal = new JLabel("TOTAL: R$ 0,00", SwingConstants.RIGHT); 
        lblTotal.setFont(new Font("Arial", Font.BOLD, 30)); 
        lblTotal.setForeground(Color.BLUE); 
 
        btnAdd.addActionListener(e -> adicionarItem()); 
 
        add(pnlEntrada, BorderLayout.WEST); 
        add(new JScrollPane(tabela), BorderLayout.CENTER); 
        add(lblTotal, BorderLayout.SOUTH); 
    } 
 
    private void adicionarItem() { 
        try { 
            int id = Integer.parseInt(txtId.getText()); 
            int qtd = Integer.parseInt(txtQtd.getText()); 
             
            Produto p = null; 
            for(Produto item : Banco.produtos) if(item.id == id) p = item; 
 
            if (p != null) { 
                double subtotal = p.preco * qtd; //ok
                modeloTabela.addRow(new Object[]{p.id, p.nome, qtd, p.preco, subtotal}); 
                 
                totalVenda += subtotal; 

                if(totalVenda>500){
                    double precoFinal = totalVenda*0.9;
                    lblTotal.setText(String.format("DESCONTO: R$ %.2f",totalVenda-precoFinal));

                }
                
                lblTotal.setText(String.format("TOTAL: R$ %.2f", totalVenda)); 
            } else { 
                JOptionPane.showMessageDialog(this, "Produto não encontrado!"); 
            } 
        } catch (Exception ex) { 
            
            JOptionPane.showMessageDialog(this, "Erro no sistema!"); 
        } 
    } 
 
    public static void main(String[] args) { 
        SwingUtilities.invokeLater(() -> new PDVPrincipal().setVisible(true)); 
    } 
} 
 