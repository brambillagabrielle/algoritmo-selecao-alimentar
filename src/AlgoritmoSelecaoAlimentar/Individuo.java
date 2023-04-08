package AlgoritmoSelecaoAlimentar;

import java.util.ArrayList;
import java.util.List;

/*
 * Classe que implementa os indivíduos do Algoritmo Genético
 */
public class Individuo implements Comparable<Individuo> {

    private List cromossomo = new ArrayList();
    private int geracao;
    private Double notaAvaliacao;
    private Double limiteCalorico;

    private List valoresCaloricos;
    private List vitaminas;
    private List gruposAlimentares;
    private Double totalCalorico;

    /**
     * Método construtor que atribui valores para os dados comuns entre os indivíduos 
     * (informações dos alimentos e o limite calórico) e gera os valores de cada gene do cromossomo
     * Cada gene corresponde à um alimento, sendo que:
     * 0 = alimento não está na seleção alimentar
     * 1 = alimento está na seleção alimentar
     * @param valoresCaloricos
     * @param vitaminas
     * @param gruposAlimentares
     * @param limiteCalorico
     */
    public Individuo(List valoresCaloricos, List vitaminas, List gruposAlimentares, Double limiteCalorico) {

        super();
        this.valoresCaloricos = valoresCaloricos;
        this.vitaminas = vitaminas;
        this.gruposAlimentares = gruposAlimentares;
        this.limiteCalorico = limiteCalorico;
        this.geracao = 0;
        this.notaAvaliacao = 0.0;

        for (int i = 0; i < this.valoresCaloricos.size(); i++) {

            if (java.lang.Math.random() < 0.5)
                this.cromossomo.add(1);
            else
                this.cromossomo.add(0);

        }

    }

    /**
     * Método que calcula a nota de avaliação para um indivíduo, levando em consideração o total
     * de calorias somadas entre todos os alimentos que estão na seleção, além das vitaminas e 
     * grupos alimentares selecionados
     */
    public void avaliacao() {

        Double totalCalorico = 0.0;
        List vitaminas = new ArrayList();
        List gruposAlimentares = new ArrayList();
        List quantidades = new ArrayList();

        for (int i = 0; i < cromossomo.size(); i++) {

            if (!this.cromossomo.get(i).equals(0)) {

                totalCalorico += (Integer) this.cromossomo.get(i) * (Double) this.valoresCaloricos.get(i);

                if (!vitaminas.contains(this.vitaminas.get(i)))
                    vitaminas.add(this.vitaminas.get(i));

                if(!gruposAlimentares.contains(this.gruposAlimentares.get(i)))
                    gruposAlimentares.add(this.gruposAlimentares.get(i));

            }

        }

        Double nota = 0.0;

        if (totalCalorico > this.limiteCalorico)
            nota = 1.0;
        else
            nota += totalCalorico;
        
        nota += vitaminas.size() + gruposAlimentares.size();

        this.notaAvaliacao = nota;
        this.totalCalorico = totalCalorico;

    }

    /**
     * Método que faz o crossover entre o indivíduo atual e um outro indivíduo selecionado, usando
     * um ponto de corte para misturar os cromossomos de ambos para gerar filhos para a nova geração
     * @param outroIndividuo
     * @return List
     */
    public List crossover(Individuo outroIndividuo) {

        int corte = (int) Math.round(Math.random() * this.cromossomo.size());

        List filho1 = new ArrayList();
        filho1.addAll(outroIndividuo.getCromossomo().subList(0, corte));
	    filho1.addAll(this.cromossomo.subList(corte, this.cromossomo.size()));

        List filho2 = new ArrayList();
        filho2.addAll(this.cromossomo.subList(0, corte));
	    filho2.addAll(outroIndividuo.getCromossomo().subList(corte, this.cromossomo.size()));

        List<Individuo> filhos = new ArrayList();
        filhos.add(new Individuo(this.valoresCaloricos, this.vitaminas, this.gruposAlimentares, this.limiteCalorico));
        filhos.add(new Individuo(this.valoresCaloricos, this.vitaminas, this.gruposAlimentares, this.limiteCalorico));

        filhos.get(0).setCromossomo(filho1);
        filhos.get(0).setGeracao(this.geracao + 1);
        filhos.get(1).setCromossomo(filho2);
        filhos.get(1).setGeracao(this.geracao + 1);

        return filhos;

    }

    /**
     * Método para realizar a mutação de um novo indivíduo da população, sendo que para cada gene
     * do seu cromossomo, será testando se alcança a taxa muito pequena para mutação
     * Caso o gene alcance a taxa de mutação, sera invertido (1 vira 0 ou 0 vira 1)
     * @param taxaMutacao
     * @return Individuo
     */
    public Individuo mutacao(Double taxaMutacao) {

        for (int i = 0; i < this.cromossomo.size(); i++) {

            if (Math.random() < taxaMutacao) {

                if (this.cromossomo.get(i).equals(0))
                    this.cromossomo.set(i, 1);
                else
                    this.cromossomo.set(i, 0);

            }

        }

        return this;

    }

    /**
     * Método herdado do Comparable para comparar dois indivíduos quanto à nota de avaliação
     * @param o
     * @return int
     */
    @Override
    public int compareTo(Individuo o) {

        if (this.notaAvaliacao > o.getNotaAvaliacao())
            return -1;
        
        if (this.notaAvaliacao < o.getNotaAvaliacao())
            return 1;
        
        return 0;

    }

    /**
     * Método que retorna o cromossomo de um indivíduo
     * @return List
     */
    public List getCromossomo() {
        return cromossomo;
    }

    /**
     * Método que atribui um cromossomo para um indivíduo
     * @param cromossomo
     */
    public void setCromossomo(List cromossomo) {
        this.cromossomo = cromossomo;
    }

    /**
     * Método que retorna a geração de um indivíduo
     * @return int
     */
    public int getGeracao() {
        return geracao;
    }

    /**
     * Método que atribui uma geração para um indivíduo
     * @param geracao
     */
    public void setGeracao(int geracao) {
        this.geracao = geracao;
    }

    /**
     * Método que retorna a nota de avaliação de um indivíduo
     * @return Double
     */
    public Double getNotaAvaliacao() {
        return notaAvaliacao;
    }

    /**
     * Método que atribui uma nota de avaliação para um indivíduo
     * @param notaAvaliacao
     */
    public void setNotaAvaliacao(Double notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
    }

    /**
     * Método que retorna o limite calórico para a seleção
     * @return Double
     */
    public Double getLimiteCalorico() {
        return limiteCalorico;
    }

    /**
     * Método que atribui um limite calórico para a seleção
     * @param limiteCalorico
     */
    public void setLimiteCalorico(Double limiteCalorico) {
        this.limiteCalorico = limiteCalorico;
    }

    /**
     * Método que retorna os valores calóricos de todos os alimentos
     * @return List
     */
    public List getValoresCaloricos() {
        return valoresCaloricos;
    }

    /**
     * Método que atribui valores calóricos para todos os alimentos
     * @param valoresCaloricos
     */
    public void setValoresCaloricos(List valoresCaloricos) {
        this.valoresCaloricos = valoresCaloricos;
    }

    /**
     * Método que retorna as vitaminas de todos os alimentos
     * @return List
     */
    public List getVitaminas() {
        return vitaminas;
    }

    /**
     * Método que atribui vitaminas para todos os alimentos
     * @param vitaminas
     */
    public void setVitaminas(List vitaminas) {
        this.vitaminas = vitaminas;
    }

    /**
     * Método que retorna os grupos alimentares de todos os alimentos
     * @return List
     */
    public List getGruposAlimentares() {
        return gruposAlimentares;
    }

    /**
     * Método que atribui grupos alimentares para todos os alimentos
     * @param gruposAlimentares
     */
    public void setGruposAlimentares(List gruposAlimentares) {
        this.gruposAlimentares = gruposAlimentares;
    }

    /**
     * Método que retorna o total calórico de todos os alimentos selecionados
     * @return Double
     */
    public Double getTotalCalorico() {
        return totalCalorico;
    }

    /**
     * Método que atribui um total calórico para todos os alimentos selecionados
     * @param totalCalorico
     */
    public void setTotalCalorico(Double totalCalorico) {
        this.totalCalorico = totalCalorico;
    }
    
}
