package operacoes_regulares;

public class Automato {
    private String id="", nome="", simboloI="",simboloII="",lendoI,lendoII, x,y;
    private boolean estadoInicial,estadoFinal;
    
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSimboloI() {
		return simboloI;
	}
	public void setSimboloI(String simboloI) {
		this.simboloI = simboloI;
	}
	public String getSimboloII() {
		return simboloII;
	}
	public void setSimboloII(String simboloII) {
		this.simboloII = simboloII;
	}
	public String getLendoI() {
		return lendoI;
	}
	public void setLendoI(String lendoI) {
		this.lendoI = lendoI;
	}
	public String getLendoII() {
		return lendoII;
	}
	public void setLendoII(String lendoII) {
		this.lendoII = lendoII;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	
	
	public boolean isEstadoInicial() {
		return estadoInicial;
	}
	public void setEstadoInicial(boolean estadoInicial) {
		this.estadoInicial = estadoInicial;
	}
	
	
	public boolean isEstadoFinal() {
		return estadoFinal;
	}
	public void setEstadoFinal(boolean estadoFinal) {
		this.estadoFinal = estadoFinal;
	}
	
    
	

}
