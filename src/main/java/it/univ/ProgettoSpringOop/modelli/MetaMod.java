package it.univ.ProgettoSpringOop.modelli;

public class MetaMod {
	//questa classe contiene attributi per la lista di oggetti che conterrà i metadati degli attributi della lista orignale
	private String nomeCampo;
	private String alias;
	private String tipo;
	public String getNomeCampo() {
		return nomeCampo;
	}
	public String getAlias() {
		return alias;
	}
	public String getTipo() {
		return tipo;
	}
	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "MetaMod [nomeCampo=" + nomeCampo + ", alias=" + alias + ", tipo=" + tipo + "]";
	}
	public MetaMod(String nomeCampo, String alias, String tipo) {
		this.nomeCampo = nomeCampo;
		this.alias = alias;
		this.tipo = tipo;
	}
	
	
	
	
}
