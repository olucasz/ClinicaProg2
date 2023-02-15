package controle;

import dao.DaoUsuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Usuario;
import util.JsfUtil;

/**
 *
 * @author José Processa as requisições e respostas da página index.xhtml
 */
@Named
@ViewScoped
public class IndexControle implements Serializable {

    private Usuario usuario;
    private DaoUsuario dao;

    @PostConstruct
    public void iniciar() {
        usuario = new Usuario();
        dao = new DaoUsuario();
    }

    public String autenticar() {
        Usuario temp = dao.autenticar(usuario);
        if (temp == null) {
            JsfUtil.mostrarErro("Usuário ou senha inválidos");
            return null;
        }
        return "menu";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}