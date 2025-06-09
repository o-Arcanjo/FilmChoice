import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.regex.Pattern;


public class ValidarDados <EntidadeValidacao, EntidadeValidada>{
    private EntidadeValidacao entidadeValidacao;
    private EntidadeValidada entidadeValidada;
    private final Map<String, Map<String, String>> propriedades = new HashMap<>();


    public ValidarDados (EntidadeValidacao entidadeValidacao, EntidadeValidada entidadeValidada){
        this.entidadeValidacao = entidadeValidacao;
        this.entidadeValidada = entidadeValidada;
    }

    private Field[] retornarCampoClasse(Class<?> clazz){
        return clazz.getDeclaredFields();
    }

    private boolean validarCadaPropriedade (String variavel, String valor){
        if(!propriedades.containsKey(variavel)){
            return false;
        }
        String propriedade = propriedades.get(variavel);
        return propriedade == null || Pattern.matches(propriedade, valor);
    } 

    private <Classe> Class<?> retornarTipoClasse(Classe classe){
        return classe.getClass();
    }

    private <Classe> String retornarNomeClasse(Classe classe){
        return retornarTipoClasse(classe).getSimpleName();
    }

    private boolean ehCampoInexistente(String nomeClasse, String variavel){
        return !propriedades.get(nomeClasse).containsKey(variavel);
    }

    private void adicionarCampoValor(String nomeClasse, String variavel, String valor){
        if(ehCampoInexistente(nomeClasse, variavel)){
            propriedades.put(variavel,valor);
        }
    }

    private <Entidade> boolean iterarCampos(Entidade entidade, BiFunction<String, String, Boolean> processadorCampo){
        Field[] campos = retornarCampoClasse(retornarTipoClasse(entidade));
        for(Field campo : campos){
            campo.setAccessible(true);
            try{
                Object valor = campo.get(entidade);
                if(!(valor instanceof String)){
                    throw new IllegalArgumentException("valor" + campo.getName() + "deve ser uma String");
                }
                String variavel = campo.getName();
                if(!processadorCampo.apply(variavel, valor)){
                    return false;
                }
            }catch(IllegalAccessException | IllegalArgumentException e){
                e.printStackTrace();
            }
        }
    }

    public boolean validarCampos(){
        String nomeClasse = retornarNomeClasse(entidadeValidada);
        if(!propriedades.containsKey(nomeClasse)){
            return false;  
        }
        return iterarCampos(entidadeValidada, this::validarCadaPropriedade);
    }

    public void adicionarCamposParaValidacao (){
        String nomeClasse = retornarNomeClasse(entidadeValidacao);
        propriedades.putIfAbsent(nomeClasse, new HashMap<>());
        iterarCampos(entidadeValidacao, (nomeCampo, valor) -> {
            adicionarCampoValor(nomeClasse, nomeCampo, valor);
        });
        
    }
    
}
      