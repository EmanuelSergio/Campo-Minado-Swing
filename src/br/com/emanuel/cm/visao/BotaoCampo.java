package br.com.emanuel.cm.visao;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import br.com.emanuel.cm.modelo.Campo;
import br.com.emanuel.cm.modelo.CampoEvento;
import br.com.emanuel.cm.modelo.CampoObservador;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton 
	implements CampoObservador, MouseListener{

	private final Color BG_PADRAO = new Color(184,184,184);
	private final Color BG_MARCADO = new Color(8,179,247);
	private final Color BG_EXPLODIR = new Color(189,66,68);
	private final Color BG_VERDE = new Color(0,100,0);
	
	private Campo campo;

	ImageIcon iconClick = new ImageIcon("\"C:\\Users\\User\\Desktop\\Java\\bombear.png\"");
	
	JButton bomb = new JButton(iconClick);

	
	public BotaoCampo(Campo campo) {
		this.campo=campo;
		setBackground(BG_PADRAO);
		setOpaque(true);
		setBorder(BorderFactory.createBevelBorder(0));
		
		addMouseListener(this);
		campo.registrarObservador(this);
	}

	@Override
	public void eventoOcorreu(Campo campo, CampoEvento evento) {
		switch(evento) {
		case ABRIR:
			aplicarEstiloAbrir();
			break;
		case MARCAR:
			aplicarEstiloMarcar();
			break;
		case EXPLODIR:
			aplicarEstiloExplodir();
			break;
			default:
			aplicarEstiloPadrao();
		}
		
		SwingUtilities.invokeLater(()->{
			repaint();
			validate();
		});
		
		
	}

	private void aplicarEstiloPadrao() {
		setBorder(BorderFactory.createBevelBorder(0));
		setBackground(BG_PADRAO);
		setText("");
	}

	private void aplicarEstiloExplodir() {
		setBackground(BG_EXPLODIR);
		setForeground(Color.WHITE);
		setText("💣");
	}

	private void aplicarEstiloMarcar() {
		setBackground(BG_MARCADO);
		setForeground(Color.black);
		setText("🚩");
		
		
	}

	private void aplicarEstiloAbrir() {
	
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if(campo.isMinado()) {
			setBackground(BG_EXPLODIR);
			return;
		}
		
		setBackground(BG_PADRAO);
		
		
		switch(campo.minasNaVizinhanca()) {
		case 1:
			setForeground(BG_VERDE);
			break;
		case 2:
			setForeground(Color.BLUE);
			break;
		case 3:
			setForeground(Color.YELLOW);
			break;
		case 4:
		case 5:
		case 6:
			setForeground(Color.RED);
			break;
			default:
				setForeground(Color.PINK);
		}

		String valor = !campo.vizinhancaSegura() ? 
				campo.minasNaVizinhanca() +"":"";
		setText(valor);
	
	}
	
	//interface dos eventos do Mouse
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==1) {
			campo.abrir();
		}else {
			campo.alternarMarcacao();
		}
	}
	
	public void mouseClicked(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
	
	public void mouseReleased(MouseEvent e) {}
	
	
	
}
