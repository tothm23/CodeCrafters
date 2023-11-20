package com.codecrafterswebshop.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @NamedQuery(name = "Felhasznalo.findByEmail", query = "SELECT f FROM Felhasznalo f WHERE f.email = :email"),
    @NamedQuery(name = "Felhasznalo.findByJogosultsagId", query = "SELECT f FROM Felhasznalo f WHERE f.jogosultsagId = :jogosultsagId"),
    @NamedQuery(name = "Felhasznalo.findByLetrehozva", query = "SELECT f FROM Felhasznalo f WHERE f.letrehozva = :letrehozva"),
    @NamedQuery(name = "Felhasznalo.findByFrissitve", query = "SELECT f FROM Felhasznalo f WHERE f.frissitve = :frissitve")})
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

    public Felhasznalo() {
    }

    public Felhasznalo(Integer id) {
        this.id = id;
    }

    public Felhasznalo(Integer id, String felhasznaloNev, String vezetekNev, String keresztNev, String email, String jelszo, int jogosultsagId) {
        this.id = id;
        this.felhasznaloNev = felhasznaloNev;
        this.vezetekNev = vezetekNev;
        this.keresztNev = keresztNev;
        this.email = email;
        this.jelszo = jelszo;
        this.jogosultsagId = jogosultsagId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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

    public static Map<String, Object> felhasznalo(Integer idBE) {

        Map<String, Object> felhasznalo = new HashMap<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com_CodeCraftersWebshop_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("felhasznalo");

            spq.registerStoredProcedureParameter("idBe", Integer.class, ParameterMode.IN);
            spq.setParameter("idBe", idBE);

            List<Object[]> eredmeny = spq.getResultList();

            if (!eredmeny.isEmpty()) {
                Object[] sor = eredmeny.get(0);
                felhasznalo.put("id", sor[0]);
                felhasznalo.put("felhasznaloNev", sor[1]);
                felhasznalo.put("vezetekNev", sor[2]);
                felhasznalo.put("keresztNev", sor[3]);
                felhasznalo.put("email", sor[4]);
                felhasznalo.put("jelszo", sor[5]);
                felhasznalo.put("jogosultsagId", sor[6]);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        return felhasznalo;
    }

    public static Integer felhasznaloBelepes(String felhasznaloNevBE, String jelszoBE) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com_CodeCraftersWebshop_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("felhasznaloBelepes");

            spq.registerStoredProcedureParameter("felhasznaloNevBE", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("jelszoBE", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("felhasznaloIdKI", Integer.class, ParameterMode.OUT);

            spq.setParameter("felhasznaloNevBE", felhasznaloNevBE);
            spq.setParameter("jelszoBE", jelszoBE);

            System.out.println("Success! ID: " + new Felhasznalo((Integer) spq.getOutputParameterValue("felhasznaloIdKI")).getId());
            return new Felhasznalo((Integer) spq.getOutputParameterValue("felhasznaloIdKI")).getId();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new Felhasznalo().getId();
        } finally {
            em.clear();
            em.close();
            emf.close();
        }

    }

    public static boolean ujFelhasznalo(String felhasznaloNevBE, String vezetekNevBE, String keresztNev,
            String emailBE, String jelszoBE, Integer jogosultsagIdBE) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com_CodeCraftersWebshop_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("ujFelhasznalo");

            spq.registerStoredProcedureParameter("felhasznaloNevBE", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("vezetekNevBE", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("keresztNev", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("emailBE", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("jelszoBE", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("jogosultsagIdBE", Integer.class, ParameterMode.IN);

            spq.setParameter("felhasznaloNevBE", felhasznaloNevBE);
            spq.setParameter("vezetekNevBE", vezetekNevBE);
            spq.setParameter("keresztNev", keresztNev);
            spq.setParameter("emailBE", emailBE);
            spq.setParameter("jelszoBE", jelszoBE);
            spq.setParameter("jogosultsagIdBE", jogosultsagIdBE);

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
