package it.univ.ProgettoSpringOop.modelli;
public class Metadato {
	private String alias;
	private String sourceField;
	private String type;
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getSourceField() {
		return sourceField;
	}
	public void setSourceField(String sourceField) {
		this.sourceField = sourceField;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Metadato(String alias, String sourceField, String type) {
		this.alias = alias;
		this.sourceField = sourceField;
		this.type = type;
	}
	public Metadato() {
	}
	@Override
	public String toString() {
		return "Metadato [alias=" + alias + ", sourceField=" + sourceField + ", type=" + type + "]";
	}
	
}
