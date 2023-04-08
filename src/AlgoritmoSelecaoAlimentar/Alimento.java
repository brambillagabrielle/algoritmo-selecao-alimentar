package AlgoritmoSelecaoAlimentar;

/*
 * Classe que implementa os alimentos da base de dados e seus atributos
 */
public class Alimento {

    private String nome;
    private Double valorCalorico;
    private String vitamina;
    private String grupoAlimentar;
    
    /**
     * Método construtor que atribui valores passados como atributos para cada alimento
     * @param nome
     * @param valorCalorico
     * @param vitamina
     * @param grupoAlimentar
     */
    public Alimento(String nome, Double valorCalorico, String vitamina, String grupoAlimentar) {
        super();
        this.nome = nome;
        this.valorCalorico = valorCalorico;
        this.vitamina = vitamina;
        this.grupoAlimentar = grupoAlimentar;
    }

    /**
     * Método que retorna o nome de um alimento
     * @return String
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que atribui um nome para um alimento
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método que retorna o valor calórico de um alimento
     * @return Double
     */
    public Double getValorCalorico() {
        return valorCalorico;
    }

    /**
     * Método que atribui um valor calórico para um alimento
     * @param valorCalorico
     */
    public void setValorCalorico(Double valorCalorico) {
        this.valorCalorico = valorCalorico;
    }

    /**
     * Método que retorna a vitamina de um alimento
     * @return String
     */
    public String getVitamina() {
        return vitamina;
    }

    /**
     * Método que atribui uma vitamina para um alimento
     * @param vitamina
     */
    public void setVitamina(String vitamina) {
        this.vitamina = vitamina;
    }

    /**
     * Método que retorna o grupo alimentar de um alimento
     * @return String
     */
    public String getGrupoAlimentar() {
        return grupoAlimentar;
    }

    /**
     * Método que atribui um grupo alimentar para um alimento
     * @param grupoAlimentar
     */
    public void setGrupoAlimentar(String grupoAlimentar) {
        this.grupoAlimentar = grupoAlimentar;
    }
    
}
