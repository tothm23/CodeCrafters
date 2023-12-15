package com.codecrafterswebshop.Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tothm23
 */
@Entity
@Table(name = "termekkulcs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Termekkulcs.findAll", query = "SELECT t FROM Termekkulcs t"),
    @NamedQuery(name = "Termekkulcs.findById", query = "SELECT t FROM Termekkulcs t WHERE t.id = :id"),
    @NamedQuery(name = "Termekkulcs.findByKulcs", query = "SELECT t FROM Termekkulcs t WHERE t.kulcs = :kulcs"),
    @NamedQuery(name = "Termekkulcs.findByJatekId", query = "SELECT t FROM Termekkulcs t WHERE t.jatekId = :jatekId")})
public class Termekkulcs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "kulcs")
    private String kulcs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jatekId")
    private int jatekId;

    public Termekkulcs() {
    }

    public Termekkulcs(Integer id) {
        this.id = id;
    }

    public Termekkulcs(Integer id, String kulcs, int jatekId) {
        this.id = id;
        this.kulcs = kulcs;
        this.jatekId = jatekId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKulcs() {
        return kulcs;
    }

    public void setKulcs(String kulcs) {
        this.kulcs = kulcs;
    }

    public int getJatekId() {
        return jatekId;
    }

    public void setJatekId(int jatekId) {
        this.jatekId = jatekId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Termekkulcs)) {
            return false;
        }
        Termekkulcs other = (Termekkulcs) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.codecrafterswebshop.Model.Termekkulcs[ id=" + id + " ]";
    }

}
