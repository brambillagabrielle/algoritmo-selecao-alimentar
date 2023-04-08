package AlgoritmoSelecaoAlimentar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Classe do Algoritmo Genético, contendo os métodos para execução da solução através das gerações
 */
public class AlgoritmoGenetico {

    private List<Individuo> populacao = new ArrayList();
    private int tamanhoPopulacao;
    private Individuo melhorSolucao;

    /**
     * Método construtor que atribui o tamanho da população (indivíduos em uma geração)
     * @param tamanhoPopulacao
     */
    public AlgoritmoGenetico(int tamanhoPopulacao) {
        this.tamanhoPopulacao = tamanhoPopulacao;
    }

    /**
     * Método que inicia a população, criando uma quantidade de indivíduos igual ao tamanho da
     * população
     * @param valoresCaloricos
     * @param vitaminas
     * @param gruposAlimentares
     * @param limiteCalorico
     */
    public void inicializaPopulacao(List valoresCaloricos, List vitaminas, List gruposAlimentares, Double limiteCalorico) {

        for (int i = 0; i < tamanhoPopulacao; i++)
            this.populacao.add(new Individuo(valoresCaloricos, vitaminas, gruposAlimentares, limiteCalorico));

        this.melhorSolucao = this.populacao.get(0);

    }

    /**
     * Método que seleciona o melhor indivíduo, comparando a avaliação de um indivíduo passado
     * como parâmetro com a melhor solução da geração anterior
     * @param individuo
     */
    public void melhorIndividuo(Individuo individuo) {

        if (individuo.getNotaAvaliacao() > this.melhorSolucao.getNotaAvaliacao())
            this.melhorSolucao = individuo;

    }

    /**
     * Método que soma a avaliação entre todos os indivíduos de uma população
     * @return Double
     */
    public Double somaAvaliacao() {

        Double soma = 0.0;
        
        for (Individuo i : this.populacao)
            soma += i.getNotaAvaliacao();

        return soma;

    }

    /**
     * Método que seleciona um pai para participar do crossover, aleatoriamente
     * @param somaAvaliacao
     * @return int
     */
    public int selecionaPai(Double somaAvaliacao) {

        int pai = -1;
        Double soma = 0.0;
        int i = 0;
        Double valorSorteado = Math.random() * somaAvaliacao;

        while (i < this.populacao.size() && soma < valorSorteado) {
            soma += this.populacao.get(i).getNotaAvaliacao();
            pai += 1;
            i += 1;
        }

        return pai;

    }

    /**
     * Método que mostra o melhor indivíduo da geração atual
     */
    public void visualizaGeracao() {

        Individuo melhorIndividuo = this.populacao.get(0);

        System.out.println("\nMELHOR INDIVÍDUO DA GERAÇÃO: ");
        System.out.println(
            "Geração: " + melhorIndividuo.getGeracao()
            + "\nTotal calórico: " + melhorIndividuo.getTotalCalorico()
            + "\nCromossomo: " + melhorIndividuo.getCromossomo()
        );

    }
	
	/**
	 * Método que ordena a população, ordenando os indivíduos de uma geração, fazendo
     * com que o melhor indivíduo seja o indivíduo da primeira posição (0)
	 */
	public void ordenaPopulacao() {
		Collections.sort(this.populacao);
	}

    /**
     * Método que executa o Algoritmo Genético, iterando entre todas as gerações, coordenando
     * o crossover e selecionando o melhor indivíduo de cada geração, até que se obtenha
     * uma melhor solução
     * @param taxaMutacao
     * @param numeroGeracoes
     * @param valoresCaloricos
     * @param vitaminas
     * @param gruposAlimentares
     * @param limiteCalorico
     * @return List
     */
    public List executaAlgoritmo(Double taxaMutacao, int numeroGeracoes, List valoresCaloricos, List vitaminas, List gruposAlimentares, Double limiteCalorico) {

        this.inicializaPopulacao(valoresCaloricos, vitaminas, gruposAlimentares, limiteCalorico);

        for (Individuo i : this.populacao)
            i.avaliacao();

        this.ordenaPopulacao();
        this.visualizaGeracao();

        for (int gen = 0; gen < numeroGeracoes; gen++) {
            
            Double somaAvaliacao = this.somaAvaliacao();
            List<Individuo> novaPopulacao = new ArrayList();

            for (int j = 0; j < this.populacao.size() / 2; j++) {

                int pai1 = this.selecionaPai(somaAvaliacao);
                int pai2 = this.selecionaPai(somaAvaliacao);

                List<Individuo> filhos = this.getPopulacao().get(pai1).crossover(this.getPopulacao().get(pai2));

                novaPopulacao.add(filhos.get(0).mutacao(taxaMutacao));
                novaPopulacao.add(filhos.get(1).mutacao(taxaMutacao));

            }

            this.setPopulacao(novaPopulacao);
            for (Individuo in : this.populacao)
                in.avaliacao();

            this.ordenaPopulacao();
            this.visualizaGeracao();
            
            Individuo melhorIndividuo = this.populacao.get(0);
            this.melhorIndividuo(melhorIndividuo);

        }

        System.out.println("\nMELHOR SOLUÇÃO: ");
        System.out.println(
            "Geração: " + melhorSolucao.getGeracao()
            + "\nTotal calórico: " + melhorSolucao.getTotalCalorico()
            + "\nCromossomo: " + melhorSolucao.getCromossomo()
        );
        
        return this.melhorSolucao.getCromossomo();

    }

    /**
     * Método que retorna os indivíduos de uma população
     * @return List<Individuo>
     */
    public List<Individuo> getPopulacao() {
        return populacao;
    }

    /**
     * Método que atribui indivíduos para uma população
     * @param populacao
     */
    public void setPopulacao(List<Individuo> populacao) {
        this.populacao = populacao;
    }

    /**
     * Método que retorna o tamanho de uma população
     * @return int
     */
    public int getTamanhoPopulacao() {
        return tamanhoPopulacao;
    }

    /**
     * Método que atribui um tamanho uma população
     * @param tamanhoPopulacao
     */
    public void setTamanhoPopulacao(int tamanhoPopulacao) {
        this.tamanhoPopulacao = tamanhoPopulacao;
    }

    /**
     * Método que retorna a melhor solucão
     * @return Individuo
     */
    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }
    
    /**
     * Método que atribui um indivíduo como a melhor solução
     * @param melhorSolucao
     */
    public void setMelhorSolucao(Individuo melhorSolucao) {
        this.melhorSolucao = melhorSolucao;
    }
    
}
