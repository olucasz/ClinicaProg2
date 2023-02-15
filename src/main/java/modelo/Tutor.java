package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tutor")
@DiscriminatorValue("Tutor")
public class Tutor extends Pessoa implements Serializable {
    
    @Column(length = 20)
    private String cpf; 
    
    @OneToMany (mappedBy = "tutor", fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    private List<Animal> animais; 

    public Tutor() { }

    public Tutor(String cpf, String nome, String endereco, String telefone, Date dataNascimento) {
        super(nome, endereco, telefone, dataNascimento);
        this.cpf = cpf;
    }
    
    

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.cpf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tutor other = (Tutor) obj;
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}