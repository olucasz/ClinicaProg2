package controle;
import dao.Dao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Animal;
import modelo.Tutor;
import modelo.Consulta;
import util.JsfUtil;

@Named
@ViewScoped
public class ConsultaControle implements Serializable{
    private Dao<Consulta> daoConsulta;
    private Dao<Animal> daoAnimal;
    private List<Animal> listaAnimal;
    private List<Consulta> listaConsulta;
    private Consulta consulta; 
    private List<Consulta> consultaNaoPago;
    
@PostConstruct
    public void iniciar() {
        daoConsulta = new Dao(Consulta.class);
        daoAnimal = new Dao(Animal.class);
        listaConsulta = daoConsulta.listarTodos();
        listaAnimal = daoAnimal.listarTodos();
        consulta = new Consulta();
        consultaNaoPago = new ArrayList();
        isPago();
    }

    public List<Consulta> getConsultaNaoPago() {
        return consultaNaoPago;
    }

    public void setConsultaNaoPago(List<Consulta> consultaNaoPago) {
        this.consultaNaoPago = consultaNaoPago;
    }

    public String salvar(){
        consulta.getAnimal().getConsultas().add(consulta);
        daoAnimal.alterar(consulta.getAnimal());
        consulta = new Consulta(); // limpa os campos 
        JsfUtil.mostrarSucesso("Consulta cadastrada");
        listaConsulta = daoConsulta.listarTodos(); // atualiza tabela 
        return null;
        
    }
    public void isPago(){
        for(Consulta c :listaConsulta){
            if(c.getPago() == false){
                consultaNaoPago.add(c);
            }
        }
    }
    public Dao<Animal> getDaoAnimal() {
        return daoAnimal;
    }

    public void setDaoAnimal(Dao<Animal> daoAnimal) {
        this.daoAnimal = daoAnimal;
    }

    public List<Animal> getListaAnimal() {
        return listaAnimal;
    }

    public void setListaAnimal(List<Animal> listaAnimal) {
        this.listaAnimal = listaAnimal;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
    
    public List<Consulta> getListaConsulta() {
        return listaConsulta;
    }

    public void setListaConsulta(List<Consulta> listaConsulta) {
        this.listaConsulta = listaConsulta;
    }

    
   
}