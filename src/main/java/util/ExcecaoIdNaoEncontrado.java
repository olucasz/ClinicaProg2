package util;

public class ExcecaoIdNaoEncontrado extends Exception {
    
    
    public ExcecaoIdNaoEncontrado(Integer id){
        super("Não existe objeto com o id: " + id); 
    }
    
}