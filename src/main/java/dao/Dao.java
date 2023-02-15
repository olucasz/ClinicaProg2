package dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import util.ExcecaoIdNaoEncontrado;
import util.JpaUtil;

public class Dao<T> implements Serializable {

    private final Class<T> classe;
    private EntityManager manager;

    public Dao(Class<T> classe) {
        this.classe = classe;
    }

    public T alterar(T objeto) {
        manager = JpaUtil.getEntityManager();
        manager.getTransaction().begin();
        objeto = manager.merge(objeto);
        manager.getTransaction().commit();
        manager.close();
        return objeto;
    }

    public T buscarPorId(Integer id) throws ExcecaoIdNaoEncontrado {
        T objeto;
        manager = JpaUtil.getEntityManager();
        objeto = manager.find(classe, id);
        if (objeto == null){
            manager.close();
            throw new ExcecaoIdNaoEncontrado(id);             
        }
        manager.close();
        return objeto;
    }
    
    public T buscarPorNome(String nome) {
        T temp = null;
        manager = JpaUtil.getEntityManager();
        String sql = "SELECT o FROM "+ classe.getName() +" o WHERE o.nome = :nome";
        TypedQuery<T> query = manager.createQuery(sql, classe);
        query.setParameter("nome", nome);
        try {
            temp = query.getSingleResult();
        } catch (Exception e) {  

        } finally {
            manager.close();
        }
        return temp;
    }

    public void excluir(Integer id) {
        manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        T temp = manager.find(classe, id);
        manager.remove(temp);
        tx.commit();
        manager.close();
    }

    public void inserir(T objeto) {
        manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(objeto);
        tx.commit();
        manager.close();
    }

    public List<T> listarTodos() {
        manager = JpaUtil.getEntityManager();
        CriteriaQuery<T> query
                = manager.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));
        List<T> lista = manager.createQuery(query).getResultList();
        manager.close();
        return lista;
    }

}