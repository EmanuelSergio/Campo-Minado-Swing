package br.com.emanuel.cm.modelo;

import java.util.ArrayList;
import java.util.List;


public class Campo {

	private final int linha; 
	private final int coluna;
	
	private boolean aberto;
	private boolean minado;
	private boolean marcado;
	
	private List<Campo> vizinhos = new ArrayList<Campo>();
	
	
	
	public Campo(int linha, int coluna) {
		super();
		this.linha = linha;
		this.coluna = coluna;
	}
	
	boolean adicionarVizinho(Campo candidatoVizinho) {
		boolean linhaDiferente = linha != candidatoVizinho.linha;
		boolean colunaDiferente = coluna != candidatoVizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int deltaLinha = Math.abs(linha-candidatoVizinho.linha);
		int deltaColuna = Math.abs(coluna-candidatoVizinho.coluna);
		int deltaGeral = deltaColuna + deltaLinha;
		
		if(deltaGeral == 1 && !diagonal) {
			vizinhos.add(candidatoVizinho);
			return true;
		}else if(deltaGeral == 2 && diagonal) {
			vizinhos.add(candidatoVizinho);
			return true;
		}else {
			return false;
		}
		
	}

	void alternarMarcacao() {
		if(!aberto) {
			marcado = !marcado;
		}
	}
	
	boolean abrir () {
		
		if(!aberto && !marcado) {
			aberto = true;
		
		if(minado) {
			//TODO Implementar nova versao
		}
		
		if(vizinhancaSegura()) {
			vizinhos.forEach(v -> v.abrir());
		}
		
		return true;
		
		}else {
			return false;
		}
		
	}
	
	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
	
	public boolean isMinado(){
		return minado;
	}
	
	public boolean isMarcado() {
		return marcado;
	}
	
	void minar() {
			minado = true;
			
	}

	 void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	public boolean isAberto() {
		return aberto;
	}
	
	public boolean isFechado() {
		return !isAberto();
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}

	long minasNaVizinhanca() {
		return vizinhos.stream().filter(v->v.minado).count();
	}
	
	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
