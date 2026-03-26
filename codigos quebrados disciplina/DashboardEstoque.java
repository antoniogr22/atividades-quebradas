import javax.swing.*; 
import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
public class DashboardEstoque extends JFrame { 
    // Simulação de banco de dados em memória  
    private int estoqueAtual = 50;  
    private JLabel lblStatus; 
    private JTextField txtQuantidade; 
    public DashboardEstoque() { 
        setTitle("Senac TDS - Dashboard de Vendas (BETA)"); 
        setSize(400, 250); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLayout(new GridLayout(4, 1, 10, 10)); 
        lblStatus = new JLabel("Estoque Disponível: " + estoqueAtual, SwingConstants.CENTER); 
        lblStatus.setFont(new Font("Arial", Font.BOLD, 16)); 
        txtQuantidade = new JTextField(); 
        txtQuantidade.setBorder(BorderFactory.createTitledBorder("Quantidade para Venda")); 
        JButton btnVender = new JButton("Confirmar Venda"); 
        btnVender.addActionListener(new ActionListener() { 
            @Override 
            public void actionPerformed(ActionEvent e) { 
                executarVenda(); 
            } 
        }); 
 
        add(lblStatus); 
        add(txtQuantidade); 
        add(btnVender); 
         
        setVisible(true); 
    } 
 
    private void executarVenda() { 
           
         try{
        int qtd = Integer.parseInt(txtQuantidade.getText()); //Integer analisa uma string e converte em valor Int
        if(estoqueAtual<qtd){
             JOptionPane.showMessageDialog(this, "em falta no estoque!");//usei a estrutura de condição if else para corrigir os bugs de: num negativo, quantia de produtos maior que a do estoque, e letras entrando ao invés de números
        }
       else if (qtd<=0){
            JOptionPane.showMessageDialog(this, "por favor, digite um número positivo!");
        }
     else{
        JOptionPane.showMessageDialog(this, "processado com sucesso.");
        estoqueAtual -= qtd;
      }
    }catch(NumberFormatException e){
        JOptionPane.showMessageDialog(this, "digite um número!");//tratamento do erro de campo vazio
    }
    
     txtQuantidade.setText("");//esse trecho faz o campo de quantidade "esvaziar" após o fim de qualquer operação sendo ela bem sucedida ou não
      
      lblStatus.setText("Estoque Disponível: " + estoqueAtual);
    }
      

    public static void main(String[] args) { 
        SwingUtilities.invokeLater(() -> new DashboardEstoque()); 
    } 
}