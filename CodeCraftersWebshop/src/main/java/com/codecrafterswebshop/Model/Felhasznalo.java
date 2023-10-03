package com.codecrafterswebshop.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tothm23
 */
@Entity
@Table(name = "felhasznalo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Felhasznalo.findAll", query = "SELECT f FROM Felhasznalo f"),
    @NamedQuery(name = "Felhasznalo.findById", query = "SELECT f FROM Felhasznalo f WHERE f.id = :id"),
    @NamedQuery(name = "Felhasznalo.findByFelhasznaloNev", query = "SELECT f FROM Felhasznalo f WHERE f.felhasznaloNev = :felhasznaloNev"),
    @NamedQuery(name = "Felhasznalo.findByVezetekNev", query = "SELECT f FROM Felhasznalo f WHERE f.vezetekNev = :vezetekNev"),
    @NamedQuery(name = "Felhasznalo.findByKeresztNev", query = "SELECT f FROM Felhasznalo f WHERE f.keresztNev = :keresztNev"),
    @NamedQuery(name = "Felhasznalo.findBySzuletesiDatum", query = "SELECT f FROM Felhasznalo f WHERE f.szuletesiDatum = :szuletesiDatum"),
    @NamedQuery(name = "Felhasznalo.findByEmail", query = "SELECT f FROM Felhasznalo f WHERE f.email = :email"),
    @NamedQuery(name = "Felhasznalo.findByOrszag", query = "SELECT f FROM Felhasznalo f WHERE f.orszag = :orszag"),
    @NamedQuery(name = "Felhasznalo.findByTelefon", query = "SELECT f FROM Felhasznalo f WHERE f.telefon = :telefon"),
    @NamedQuery(name = "Felhasznalo.findByAktiv", query = "SELECT f FROM Felhasznalo f WHERE f.aktiv = :aktiv"),
    @NamedQuery(name = "Felhasznalo.findByProfilkep", query = "SELECT f FROM Felhasznalo f WHERE f.profilkep = :profilkep"),
    @NamedQuery(name = "Felhasznalo.findByJogosultsagId", query = "SELECT f FROM Felhasznalo f WHERE f.jogosultsagId = :jogosultsagId"),
    @NamedQuery(name = "Felhasznalo.findByLetrehozva", query = "SELECT f FROM Felhasznalo f WHERE f.letrehozva = :letrehozva"),
    @NamedQuery(name = "Felhasznalo.findByFrissitve", query = "SELECT f FROM Felhasznalo f WHERE f.frissitve = :frissitve"),
    @NamedQuery(name = "Felhasznalo.findByTorolve", query = "SELECT f FROM Felhasznalo f WHERE f.torolve = :torolve")})
public class Felhasznalo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "felhasznaloNev")
    private String felhasznaloNev;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "vezetekNev")
    private String vezetekNev;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "keresztNev")
    private String keresztNev;
    @Basic(optional = false)
    @NotNull
    @Column(name = "szuletesiDatum")
    private String szuletesiDatum;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "jelszo")
    private String jelszo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "orszag")
    private String orszag;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "telefon")
    private String telefon;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aktiv")
    private boolean aktiv;
    @Size(max = 100)
    @Column(name = "profilkep")
    private String profilkep;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jogosultsagId")
    private int jogosultsagId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "letrehozva")
    @Temporal(TemporalType.TIMESTAMP)
    private Date letrehozva;
    @Column(name = "frissitve")
    @Temporal(TemporalType.TIMESTAMP)
    private Date frissitve;
    @Column(name = "torolve")
    @Temporal(TemporalType.TIMESTAMP)
    private Date torolve;

    public Felhasznalo() {
    }

    public Felhasznalo(Integer id) {
        this.id = id;
    }

    public Felhasznalo(Integer id, String felhasznaloNev, String vezetekNev, String keresztNev, String szuletesiDatum, String email, String jelszo, String orszag, String telefon, boolean aktiv, int jogosultsagId, Date letrehozva) {
        this.id = id;
        this.felhasznaloNev = felhasznaloNev;
        this.vezetekNev = vezetekNev;
        this.keresztNev = keresztNev;
        this.szuletesiDatum = szuletesiDatum;
        this.email = email;
        this.jelszo = jelszo;
        this.orszag = orszag;
        this.telefon = telefon;
        this.aktiv = aktiv;
        this.jogosultsagId = jogosultsagId;
        this.letrehozva = letrehozva;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFelhasznaloNev() {
        return felhasznaloNev;
    }

    public void setFelhasznaloNev(String felhasznaloNev) {
        this.felhasznaloNev = felhasznaloNev;
    }

    public String getVezetekNev() {
        return vezetekNev;
    }

    public void setVezetekNev(String vezetekNev) {
        this.vezetekNev = vezetekNev;
    }

    public String getKeresztNev() {
        return keresztNev;
    }

    public void setKeresztNev(String keresztNev) {
        this.keresztNev = keresztNev;
    }

    public String getSzuletesiDatum() {
        return szuletesiDatum;
    }

    public void setSzuletesiDatum(String szuletesiDatum) {
        this.szuletesiDatum = szuletesiDatum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

    public String getOrszag() {
        return orszag;
    }

    public void setOrszag(String orszag) {
        this.orszag = orszag;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public boolean getAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

    public String getProfilkep() {
        return profilkep;
    }

    public void setProfilkep(String profilkep) {
        this.profilkep = profilkep;
    }

    public int getJogosultsagId() {
        return jogosultsagId;
    }

    public void setJogosultsagId(int jogosultsagId) {
        this.jogosultsagId = jogosultsagId;
    }

    public Date getLetrehozva() {
        return letrehozva;
    }

    public void setLetrehozva(Date letrehozva) {
        this.letrehozva = letrehozva;
    }

    public Date getFrissitve() {
        return frissitve;
    }

    public void setFrissitve(Date frissitve) {
        this.frissitve = frissitve;
    }

    public Date getTorolve() {
        return torolve;
    }

    public void setTorolve(Date torolve) {
        this.torolve = torolve;
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
        if (!(object instanceof Felhasznalo)) {
            return false;
        }
        Felhasznalo other = (Felhasznalo) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.codecrafterswebshop.Model.Felhasznalo[ id=" + id + " ]";
    }

    public static List<Felhasznalo> felhasznalok() {

        List<Felhasznalo> felhasznalok = new ArrayList<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com_CodeCraftersWebshop_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("felhasznalok");
            felhasznalok = spq.getResultList();

        } catch (Exception e) {

            System.err.println(e.getMessage());

        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        return felhasznalok;
    }

    public static boolean ujFelhasznalo(String felhasznaloNevBE, String vezetekNevBE, String keresztNev,
            String szuletesiDatumBE, String emailBE, String jelszoBE, String orszagBE, String telefon) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com_CodeCraftersWebshop_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("ujFelhasznalo");

            spq.registerStoredProcedureParameter("felhasznaloNevBE", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("vezetekNevBE", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("keresztNev", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("szuletesiDatumBE", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("emailBE", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("jelszoBE", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("orszagBE", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("telefon", String.class, ParameterMode.IN);

            spq.setParameter("felhasznaloNevBE", felhasznaloNevBE);
            spq.setParameter("vezetekNevBE", vezetekNevBE);
            spq.setParameter("keresztNev", keresztNev);
            spq.setParameter("szuletesiDatumBE", szuletesiDatumBE);
            spq.setParameter("emailBE", emailBE);
            spq.setParameter("jelszoBE", jelszoBE);
            spq.setParameter("orszagBE", orszagBE);
            spq.setParameter("telefon", telefon);

            spq.execute();
            return true;

        } catch (Exception e) {

            System.err.println(e.getMessage());
            return false;

        } finally {
            em.clear();
            em.close();
            emf.close();
        }
    }

}
