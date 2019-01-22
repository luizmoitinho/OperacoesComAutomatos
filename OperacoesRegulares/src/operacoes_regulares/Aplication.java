package operacoes_regulares;
import java.io.BufferedReader;
import javax.swing.JOptionPane; 
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class Aplication extends JFrame {

	private JPanel lblM1;
	private JTextField txtCaminho2;
	private JTextField txtM2;
	private JTextField txtM1;
	private JTextField txtCaminho1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplication frame = new Aplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Aplication() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		lblM1 = new JPanel();
		lblM1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(lblM1);
		lblM1.setLayout(null);
		
		txtCaminho2 = new JTextField();
		txtCaminho2.setColumns(10);
		txtCaminho2.setBounds(212, 208, 174, 20);
		lblM1.add(txtCaminho2);
		
		JLabel lblUnioEInterseco = new JLabel("Uni\u00E3o e Intersec\u00E7\u00E3o de Aut\u00F4matos");
		lblUnioEInterseco.setFont(new Font("Times New Roman", Font.BOLD, 21));
		lblUnioEInterseco.setBounds(70, 11, 316, 31);
		lblM1.add(lblUnioEInterseco);
		
		JLabel lblCaminho2 = new JLabel("Caminho do Arquivo");
		lblCaminho2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblCaminho2.setBounds(10, 209, 156, 14);
		lblM1.add(lblCaminho2);
		
		JLabel lblNomeM2 = new JLabel("Nome do Arquivo");
		lblNomeM2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNomeM2.setBounds(20, 243, 156, 14);
		lblM1.add(lblNomeM2);
		
		txtM2 = new JTextField();
		txtM2.setColumns(10);
		txtM2.setBounds(212, 242, 174, 20);
		lblM1.add(txtM2);
		
		JLabel lbCaminho2 = new JLabel("Caminho do Arquivo");
		lbCaminho2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lbCaminho2.setBounds(10, 107, 156, 14);
		lblM1.add(lbCaminho2);
		
		JLabel lblNomeM1 = new JLabel("Nome do Arquivo");
		lblNomeM1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNomeM1.setBounds(10, 132, 156, 14);
		lblM1.add(lblNomeM1);
		
		txtM1 = new JTextField();
		txtM1.setColumns(10);
		txtM1.setBounds(212, 135, 174, 20);
		lblM1.add(txtM1);
		
		txtCaminho1 = new JTextField();
		txtCaminho1.setColumns(10);
		txtCaminho1.setBounds(212, 104, 174, 20);
		lblM1.add(txtCaminho1);
		
		JLabel lblSegundoArquivo = new JLabel("Segundo arquivo");
		lblSegundoArquivo.setForeground(SystemColor.textHighlight);
		lblSegundoArquivo.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		lblSegundoArquivo.setBounds(142, 174, 149, 23);
		lblM1.add(lblSegundoArquivo);
		
		JLabel lblPrimeiroArquivo = new JLabel("Primeiro arquivo");
		lblPrimeiroArquivo.setForeground(SystemColor.textHighlight);
		lblPrimeiroArquivo.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		lblPrimeiroArquivo.setBounds(142, 71, 160, 25);
		lblM1.add(lblPrimeiroArquivo);
		
		JButton btnClick = new JButton("Realizar Opera\u00E7\u00F5es");
		btnClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerenciaArquivos automato = new GerenciaArquivos();			
				automato.setNomeArqI(txtM1.getText());
				automato.setNomeArqII(txtM2.getText());
				automato.setCaminhoI(txtCaminho1.getText());
				automato.setCaminhoII(txtCaminho2.getText());
				
				if(automato.recebeArquivos()) 
					JOptionPane.showMessageDialog(null, " Caminho encontrado!");
				
				else
					JOptionPane.showMessageDialog(null, " Um dos caminhos não foi encontrado!");
		
			}
		});
		btnClick.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnClick.setForeground(SystemColor.text);
		btnClick.setBackground(SystemColor.textHighlight);
		btnClick.setBounds(129, 304, 190, 34);
		lblM1.add(btnClick);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(432, 175, -435, 2);
		lblM1.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBackground(SystemColor.activeCaptionBorder);
		separator_1.setBounds(7, 166, 417, 20);
		lblM1.add(separator_1);
		
		JLabel lblAlerta = new JLabel("");
		lblAlerta.setBounds(189, 304, 46, 14);
		lblM1.add(lblAlerta);
	}
}
