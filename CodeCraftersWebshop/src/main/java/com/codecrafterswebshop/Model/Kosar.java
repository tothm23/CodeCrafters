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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tothm23
 */
@Entity
@Table(name = "kosar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kosar.findAll", query = "SELECT k FROM Kosar k"),
    @NamedQuery(name = "Kosar.findById", query = "SELECT k FROM Kosar k WHERE k.id = :id"),
    @NamedQuery(name = "Kosar.findByFelhasznaloId", query = "SELECT k FROM Kosar k WHERE k.felhasznaloId = :felhasznaloId"),
    @NamedQuery(name = "Kosar.findByJatekId", query = "SELECT k FROM Kosar k WHERE k.jatekId = :jatekId"),
    @NamedQuery(name = "Kosar.findByVegosszeg", query = "SELECT k FROM Kosar k WHERE k.vegosszeg = :vegosszeg")})
public class Kosar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "felhasznaloId")
    private int felhasznaloId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jatekId")
    private int jatekId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vegosszeg")
    private int vegosszeg;

    public Kosar() {
    }

    public Kosar(Integer id) {
        this.id = id;
    }

    public Kosar(Integer id, int felhasznaloId, int jatekId, int vegosszeg) {
        this.id = id;
        this.felhasznaloId = felhasznaloId;
        this.jatekId = jatekId;
        this.vegosszeg = vegosszeg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getFelhasznaloId() {
        return felhasznaloId;
    }

    public void setFelhasznaloId(int felhasznaloId) {
        this.felhasznaloId = felhasznaloId;
    }

    public int getJatekId() {
        return jatekId;
    }

    public void setJatekId(int jatekId) {
        this.jatekId = jatekId;
    }

    public int getVegosszeg() {
        return vegosszeg;
    }

    public void setVegosszeg(int vegosszeg) {
        this.vegosszeg = vegosszeg;
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
        if (!(object instanceof Kosar)) {
            return false;
        }
        Kosar other = (Kosar) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.codecrafterswebshop.Model.Kosar[ id=" + id + " ]";
    }

}
