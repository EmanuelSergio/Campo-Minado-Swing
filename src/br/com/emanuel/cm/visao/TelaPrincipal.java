package br.com.emanuel.cm.visao;

import javax.swing.JFrame;

import br.com.emanuel.cm.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame{

	public TelaPrincipal() {
		
		Tabuleiro tabuleiro = new Tabuleiro(16,30,50);
		add(new PainelTabuleiro(tabuleiro));
		
		setTitle("campo minado");
		setSize(690,438);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		}
	
	
	
	
	public static void main(String[] args) {
		
		new TelaPrincipal();
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
}
