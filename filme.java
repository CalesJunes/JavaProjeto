package couto;


	import javax.swing.*;
	import javax.swing.table.*;
	import javax.swing.border.TitledBorder;
	import java.awt.*;
	import java.awt.event.*;
	import java.text.DecimalFormat;

	public class filme extends JPanel {
		private JPanel pnPrincipal, pnTable,pnButton;
		private JButton btRemover, btAdicionar,btver;
		private JScrollPane scrollTable;
		private JRadioButton rbSim, rbNao;
		private JComboBox genero;
		private ButtonGroup btGroup;
		private JTable table; // declaração da tabela
		private JLabel lbNumero, lbTotal, lbProduto, lbgenero, lbano,lbdisponibilidade,lbgenero2,lbano2,lbnome2;
		private JTextField tfNumero, tfTotal, tfProduto, tfano;
		DecimalFormat df = new DecimalFormat("#,###.00"); //classe para formatar um campo da tabela;

		public filme() {
			inicializarComponentes();
			definirEventos();
		}

		private void inicializarComponentes() {
			setLayout(null);
			String [] vetor = {"Terror","Luta"};
			rbSim = new JRadioButton("Sim");
			rbNao = new JRadioButton("Nao");
			btGroup = new ButtonGroup();
			btver = new JButton("Ver");
			genero = new JComboBox(vetor);
			btGroup.add(rbSim);
			btGroup.add(rbNao);
			lbProduto = new JLabel("Nome");
			lbgenero = new JLabel("Genero");
			lbano = new JLabel("Ano");
			lbdisponibilidade = new JLabel ("Disponibilidade");
			lbNumero = new JLabel("Código:");
			lbTotal = new JLabel("Total de Pedidos: ");
			tfProduto = new JTextField();
			tfano = new JTextField();
			tfNumero = new JTextField();
			tfTotal = new JTextField();
			tfTotal.setEnabled(false);// textfield editavel ou não 
			tfTotal.setHorizontalAlignment(JTextField.CENTER);// alinhamento do texto no textfield (esquerda, direita, centro)
			btAdicionar = new JButton("Adicionar");
			btAdicionar.setToolTipText("Adiciona um item ao pedido");
			btRemover = new JButton("Remover");
			btRemover.setToolTipText("Remove os itens selecionados");
			lbProduto.setBounds(15, 45, 100, 25);
			//lbQtde.setBounds(225, 40, 100, 25);
			lbano.setBounds(15, 90, 100, 25);
			lbNumero.setBounds(15, 10, 120, 25);
			genero.setBounds(160,65,120,25);
			lbTotal.setBounds(278, 360, 100, 25);
			tfProduto.setBounds(15, 65, 130, 25);
			//tfQtde.setBounds(225, 65, 50, 25);
			tfano.setBounds(15, 110, 80, 25);
			lbdisponibilidade.setBounds(160,90,100,25);
			tfNumero.setBounds(65, 10, 60, 25);
			btAdicionar.setBounds(15, 150, 100, 22);
			btver.setBounds(150,150,100,22);
			btRemover.setBounds(125, 150, 100, 22);
			rbSim.setBounds(160,115,50,25);
			rbNao.setBounds(210,115,50,25);
			pnPrincipal = new JPanel();
			pnPrincipal.setLayout(null);
			pnPrincipal.setBounds(0, 0, 500, 400);
			pnPrincipal.add(lbNumero);

			pnPrincipal.add(tfNumero);
			pnPrincipal.add(lbProduto);
			pnPrincipal.add(tfProduto);
			pnPrincipal.add(lbdisponibilidade);
			pnPrincipal.add(genero);
			pnPrincipal.add(lbgenero);
			pnPrincipal.add(lbano);
			pnPrincipal.add(tfano);
			pnPrincipal.add(btver);
			pnPrincipal.add(rbSim);
			pnPrincipal.add(rbNao);
			pnTable = new JPanel(new BorderLayout());
			pnButton = new JPanel(new BorderLayout());
			pnTable.setBorder(new TitledBorder("Itens do Pedido"));//linhas 64 e 65 definem a borda do painel , com o nome dele (nesse caso itens do pedido)
			scrollTable = new JScrollPane();
			df.setMinimumFractionDigits(2);//minimo de numeros depois da virgula
			df.setMaximumFractionDigits(2);// minimo de núemros depois da virgula
			pnPrincipal.add(btAdicionar);
			pnButton.add(btRemover);
			rbSim.setSelected(true);
			
			
			DefaultTableModel tableModel = new DefaultTableModel(
					new String[] {"Nome", "Ano", "Genero" },0) { //cria o layout da tabela , os itens do vetor são os nomes da coluna, o 0 indica que a tabela saíra sem nenhuma linha
				public boolean isCellEditable(int row, int col) { //método para dizer se a celula será editavel ou não
					if (col == 3  ) {
						return false;
					}
					return true;
				}
			};
			table = new JTable(tableModel);//instancia a tabela e adiciona o modelo de tabela criado anteriomente a ela
			DefaultTableCellRenderer alinhaDireita = new DefaultTableCellRenderer(); 
			alinhaDireita.setHorizontalAlignment(SwingConstants.RIGHT);//a variável alinhaDireita alinha o conteudo da tabela a direita 
			table.getColumnModel().getColumn(0).setPreferredWidth(150); //define o tamanho da coluna
			table.getColumnModel().getColumn(0).setResizable(true);//define se a coluna é editavel ou não ;
			table.getColumnModel().getColumn(1).setPreferredWidth(50);
			table.getColumnModel().getColumn(1).setResizable(false);
			table.getColumnModel().getColumn(1).setCellRenderer(alinhaDireita);//alinha o conteúdo da ceula a direita;
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setResizable(false);
			table.getColumnModel().getColumn(2).setCellRenderer(alinhaDireita);

		
			table.getTableHeader().setReorderingAllowed(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// linhas 97 e 98 define que as colunas não podem ser reorganizadas com arraste e solte
			scrollTable.setViewportView(table);
			pnTable.add(scrollTable);
			pnTable.setBounds(10, 200, 470, 230);
			pnPrincipal.add(pnTable);
			add(pnPrincipal);

		}

		private void definirEventos(){
			btAdicionar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(tfProduto.getText().equals("") || tfano.getText().equals("") ){
					JOptionPane.showMessageDialog(pnTable, "Preencha todos os campos");
					return;
				}
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.addRow(new Object[] {tfProduto.getText(),tfano.getText(),genero.getSelectedItem()
			});
			btRemover.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					int[] linhas = table.getSelectedRows();
					DefaultTableModel dtm = (DefaultTableModel) table.getModel();
					for(int i = (linhas.length -1 ); i>=0; --i){ //esse for percorre o vetor de linhas , verifica qual  linha é selecionada e exclui
						dtm.removeRow(linhas[i]);
					}
					calcularTotal();
				}
			});
			
		}
		
		private void calcularTotal(){
			double total = 0;
			for(int linha = 0; linha<table.getRowCount(); linha++){// for para tirar os pontoos, e virguglas do numero para calcular total
				String valor = " "+table.getValueAt(linha,3);
				valor = valor.replace(".","");
				valor = valor.replace(",",".");
				total += Double.parseDouble(valor);
			}
			tfTotal.setText(""+df.format(total));
		}

		private void limparCampos(){
			tfProduto.setText("");
			tfano.setText("");
			//tfPrecoUnitario.setText("");
			tfProduto.requestFocus();
		}
		
			}); }
			
		
		public static void main(String args[]){
	 		JFrame frame = new JFrame("Area de Texto");
			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 frame.getContentPane().add(new filme());
			 frame.setBounds(300,300,500,500);
			 frame.setVisible(true);
			
			
		}
	}
	

	

