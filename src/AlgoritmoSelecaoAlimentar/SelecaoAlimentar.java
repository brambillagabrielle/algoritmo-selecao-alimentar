package AlgoritmoSelecaoAlimentar;

import java.util.ArrayList;
import java.util.List;

/*
 * Classe responsável por iniciar a execução do Algoritmo Genético
 */
public class SelecaoAlimentar {

    /**
     * Classe que executa a Seleção Alimentar, criando os alimentos da base de dados e os parâmetros
     * que serão passados para o algoritmo, além de mostrar a lista de alimentos selecionados pela melhor
     * solução no final
     * @param args
     */
    public static void main(String[] args) {

        List<Alimento> alimentos = new ArrayList();
        alimentos.add(new Alimento("Arroz", 366.0, "E", "carboidrato"));
        alimentos.add(new Alimento("Pão", 243.0, "E", "carboidrato"));
        alimentos.add(new Alimento("Aveia", 94.0, "B1", "carboidrato"));
        alimentos.add(new Alimento("Batata", 71.0, "C", "carboidrato"));
        alimentos.add(new Alimento("Mandioca", 132.0, "C", "carboidrato"));
        
        alimentos.add(new Alimento("Cenoura", 30.0, "A", "legumes-verduras"));
        alimentos.add(new Alimento("Beterraba", 46.0, "C", "legumes-verduras"));
        alimentos.add(new Alimento("Espinafre", 23.0, "A", "legumes-verduras"));
        alimentos.add(new Alimento("Tomate", 17.0, "A", "legumes-verduras"));
        alimentos.add(new Alimento("Alface", 8.0, "A", "legumes-verduras"));

        alimentos.add(new Alimento("Maçã", 64.0, "A", "frutas"));
        alimentos.add(new Alimento("Banana", 109.0, "A", "frutas"));
        alimentos.add(new Alimento("Manga", 67.0, "A", "frutas"));
        alimentos.add(new Alimento("Abacaxi", 49.0, "C", "frutas"));
        alimentos.add(new Alimento("Laranja", 45.0, "C", "frutas"));
        alimentos.add(new Alimento("Morango", 30.0, "C", "frutas"));

        alimentos.add(new Alimento("Peixe", 93.0, "D", "carnes-ovos"));
        alimentos.add(new Alimento("Ovo", 135.0, "A", "carnes-ovos"));
        alimentos.add(new Alimento("Carne bovina", 149.0, "B3", "carnes-ovos"));
        alimentos.add(new Alimento("Frango", 105.0, "B3", "carnes-ovos"));
        
        alimentos.add(new Alimento("Leite", 55.0, "A", "laticinios"));
        alimentos.add(new Alimento("Queijo", 320.0, "A", "laticinios"));
        alimentos.add(new Alimento("Iogurte", 62.0, "A", "laticinios"));
        alimentos.add(new Alimento("Requeijão", 290.0, "E", "laticinios"));
        alimentos.add(new Alimento("Leite fermentado", 75.0, "E", "laticinios"));

        alimentos.add(new Alimento("Feijão", 249.0, "B3", "feijoes-oleaginosas"));
        alimentos.add(new Alimento("Lentilha", 313.0, "B3", "feijoes-oleaginosas"));
        alimentos.add(new Alimento("Nozes", 649.0, "A", "feijoes-oleaginosas"));
        alimentos.add(new Alimento("Castanha", 581.0, "C", "feijoes-oleaginosas"));
        alimentos.add(new Alimento("Grão de bico", 290.0, "B6", "feijoes-oleaginosas"));

        List valoresCaloricos = new ArrayList();
        List vitaminas = new ArrayList(); 
        List gruposAlimentares = new ArrayList();

        for (Alimento a : alimentos) {
            valoresCaloricos.add(a.getValorCalorico());
            vitaminas.add(a.getVitamina());
            gruposAlimentares.add(a.getGrupoAlimentar());
        }

        Double limiteCalorico = 2000.0;
        int tamanhoPopulacao = 20;
        Double taxaMutacao = 0.01;
        int numeroGeracoes = 10;

        AlgoritmoGenetico ag = new AlgoritmoGenetico(tamanhoPopulacao);
        List resultado = ag.executaAlgoritmo(taxaMutacao, numeroGeracoes, valoresCaloricos, vitaminas, gruposAlimentares, limiteCalorico);

        System.out.println("Alimentos selecionados: ");
        for (int i = 0; i < alimentos.size(); i++) {

    	    if (!resultado.get(i).equals(0))
    		    System.out.println(" - "+ alimentos.get(i).getNome());

        }

    }

}
