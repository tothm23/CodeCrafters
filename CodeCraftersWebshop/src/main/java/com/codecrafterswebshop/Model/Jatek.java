package com.codecrafterswebshop.Model;

import com.codecrafterswebshop.Config.Database;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
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

/**
 *
 * @author tothm23
 */
@Entity
@Table(name = "jatek")
@NamedQueries({
    @NamedQuery(name = "Jatek.findAll", query = "SELECT j FROM Jatek j"),
    @NamedQuery(name = "Jatek.findById", query = "SELECT j FROM Jatek j WHERE j.id = :id"),
    @NamedQuery(name = "Jatek.findByNev", query = "SELECT j FROM Jatek j WHERE j.nev = :nev"),
    @NamedQuery(name = "Jatek.findByAr", query = "SELECT j FROM Jatek j WHERE j.ar = :ar"),
    @NamedQuery(name = "Jatek.findByKep", query = "SELECT j FROM Jatek j WHERE j.kep = :kep"),
    @NamedQuery(name = "Jatek.findByKorhatar", query = "SELECT j FROM Jatek j WHERE j.korhatar = :korhatar"),
    @NamedQuery(name = "Jatek.findByAkcio", query = "SELECT j FROM Jatek j WHERE j.akcio = :akcio"),
    @NamedQuery(name = "Jatek.findByMennyisegraktaron", query = "SELECT j FROM Jatek j WHERE j.mennyisegraktaron = :mennyisegraktaron"),
    @NamedQuery(name = "Jatek.findByEszkozId", query = "SELECT j FROM Jatek j WHERE j.eszkozId = :eszkozId"),
    @NamedQuery(name = "Jatek.findByPlatformId", query = "SELECT j FROM Jatek j WHERE j.platformId = :platformId"),
    @NamedQuery(name = "Jatek.findByLetrehozva", query = "SELECT j FROM Jatek j WHERE j.letrehozva = :letrehozva"),
    @NamedQuery(name = "Jatek.findByFrissitve", query = "SELECT j FROM Jatek j WHERE j.frissitve = :frissitve")})
public class Jatek implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nev")
    private String nev;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ar")
    private int ar;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "leiras")
    private String leiras;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "kep")
    private String kep;
    @Basic(optional = false)
    @NotNull
    @Column(name = "korhatar")
    private int korhatar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "akcio")
    private int akcio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mennyisegraktaron")
    private int mennyisegraktaron;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eszkozId")
    private int eszkozId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "platformId")
    private int platformId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "letrehozva")
    @Temporal(TemporalType.TIMESTAMP)
    private Date letrehozva;
    @Column(name = "frissitve")
    @Temporal(TemporalType.TIMESTAMP)
    private Date frissitve;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eladva")
    private int eladva;

    public Jatek() {
    }

    public Jatek(Integer id) {
        this.id = id;
    }

    public Jatek(Integer id, String nev, int ar, String leiras, String kep, int korhatar, int akcio, int mennyisegraktaron, int eszkozId,
            int platformId, Date letrehozva, Integer eladva) {
        this.id = id;
        this.nev = nev;
        this.ar = ar;
        this.leiras = leiras;
        this.kep = kep;
        this.korhatar = korhatar;
        this.akcio = akcio;
        this.mennyisegraktaron = mennyisegraktaron;
        this.eszkozId = eszkozId;
        this.platformId = platformId;
        this.letrehozva = letrehozva;
        this.eladva = eladva;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public String getKep() {
        return kep;
    }

    public void setKep(String kep) {
        this.kep = kep;
    }

    public int getKorhatar() {
        return korhatar;
    }

    public void setKorhatar(int korhatar) {
        this.korhatar = korhatar;
    }

    public int getAkcio() {
        return akcio;
    }

    public void setAkcio(int akcio) {
        this.akcio = akcio;
    }

    public int getMennyisegraktaron() {
        return mennyisegraktaron;
    }

    public void setMennyisegraktaron(int mennyisegraktaron) {
        this.mennyisegraktaron = mennyisegraktaron;
    }

    public int getEszkozId() {
        return eszkozId;
    }

    public void setEszkozId(int eszkozId) {
        this.eszkozId = eszkozId;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
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

    public int getEladva() {
        return eladva;
    }

    public void setEladva(int eladva) {
        this.eladva = eladva;
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
        if (!(object instanceof Jatek)) {
            return false;
        }
        Jatek other = (Jatek) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.codecrafterswebshop.Model.Jatek[ id=" + id + " ]";
    }

    public static List<Map<String, Object>> jatekok() {
        List<Map<String, Object>> jatekok = new ArrayList<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPersistenceUnitNev());
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("jatekok");

            List<Object[]> eredmeny = spq.getResultList();

            if (!eredmeny.isEmpty()) {

                for (Object[] sor : eredmeny) {
                    LinkedHashMap<String, Object> jatek = new LinkedHashMap<>();

                    jatek.put("id", (Integer) sor[0]);
                    jatek.put("nev", (String) sor[1]);
                    jatek.put("ar", (Integer) sor[2]);
                    jatek.put("leiras", (String) sor[3]);
                    jatek.put("kep", (String) sor[4]);
                    jatek.put("korhatar", (Integer) sor[5]);
                    jatek.put("akcio", (Integer) sor[6]);
                    jatek.put("mennyisegraktaron", (Integer) sor[7]);
                    jatek.put("eszkoz", (String) sor[8]);
                    jatek.put("platform", (String) sor[9]);

                    jatekok.add(jatek);
                }

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        return jatekok;

    }

    public static List<Map<String, Object>> _3veletlenjatek() {
        List<Map<String, Object>> jatekok = new ArrayList<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPersistenceUnitNev());
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("3veletlenjatek");

            List<Object[]> eredmeny = spq.getResultList();

            if (!eredmeny.isEmpty()) {

                for (Object[] sor : eredmeny) {
                    LinkedHashMap<String, Object> jatek = new LinkedHashMap<>();

                    jatek.put("id", (Integer) sor[0]);
                    jatek.put("nev", (String) sor[1]);
                    jatek.put("ar", (Integer) sor[2]);
                    jatek.put("leiras", (String) sor[3]);
                    jatek.put("kep", (String) sor[4]);
                    jatek.put("korhatar", (Integer) sor[5]);
                    jatek.put("akcio", (Integer) sor[6]);
                    jatek.put("mennyisegraktaron", (Integer) sor[7]);
                    jatek.put("eszkoz", (String) sor[8]);
                    jatek.put("platform", (String) sor[9]);

                    jatekok.add(jatek);
                }

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        return jatekok;

    }

    public static Map<String, Object> jatek(Integer idBe) {

        Map<String, Object> jatek = new LinkedHashMap<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPersistenceUnitNev());
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("jatek");

            spq.registerStoredProcedureParameter("idBe", Integer.class, ParameterMode.IN);
            spq.setParameter("idBe", idBe);

            List<Object[]> eredmeny = spq.getResultList();

            if (!eredmeny.isEmpty()) {
                Object[] sor = eredmeny.get(0);
                jatek.put("id", sor[0]);
                jatek.put("nev", sor[1]);
                jatek.put("ar", sor[2]);
                jatek.put("leiras", sor[3]);
                jatek.put("kep", sor[4]);
                jatek.put("korhatar", sor[5]);
                jatek.put("akcio", sor[6]);
                jatek.put("mennyisegraktaron", sor[7]);
                jatek.put("eszkoz", sor[8]);
                jatek.put("platform", sor[9]);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        return jatek;
    }

}
