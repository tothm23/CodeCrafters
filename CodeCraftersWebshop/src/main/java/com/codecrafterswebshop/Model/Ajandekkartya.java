package com.codecrafterswebshop.Model;

import com.codecrafterswebshop.Exception.AjandekkartyaException;
import java.io.Serializable;
import java.util.ArrayList;
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

/**
 *
 * @author tothm23
 */
@Entity
@Table(name = "ajandekkartya")
@NamedQueries({
    @NamedQuery(name = "Ajandekkartya.findAll", query = "SELECT a FROM Ajandekkartya a"),
    @NamedQuery(name = "Ajandekkartya.findById", query = "SELECT a FROM Ajandekkartya a WHERE a.id = :id"),
    @NamedQuery(name = "Ajandekkartya.findByNev", query = "SELECT a FROM Ajandekkartya a WHERE a.nev = :nev"),
    @NamedQuery(name = "Ajandekkartya.findByAr", query = "SELECT a FROM Ajandekkartya a WHERE a.ar = :ar"),
    @NamedQuery(name = "Ajandekkartya.findByKep", query = "SELECT a FROM Ajandekkartya a WHERE a.kep = :kep"),
    @NamedQuery(name = "Ajandekkartya.findByAkcio", query = "SELECT a FROM Ajandekkartya a WHERE a.akcio = :akcio"),
    @NamedQuery(name = "Ajandekkartya.findByMennyisegraktaron", query = "SELECT a FROM Ajandekkartya a WHERE a.mennyisegraktaron = :mennyisegraktaron"),
    @NamedQuery(name = "Ajandekkartya.findByEszkozId", query = "SELECT a FROM Ajandekkartya a WHERE a.eszkozId = :eszkozId"),
    @NamedQuery(name = "Ajandekkartya.findByPlatformId", query = "SELECT a FROM Ajandekkartya a WHERE a.platformId = :platformId"),
    @NamedQuery(name = "Ajandekkartya.findByLetrehozva", query = "SELECT a FROM Ajandekkartya a WHERE a.letrehozva = :letrehozva"),
    @NamedQuery(name = "Ajandekkartya.findByFrissitve", query = "SELECT a FROM Ajandekkartya a WHERE a.frissitve = :frissitve")})
public class Ajandekkartya implements Serializable {

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

    public Ajandekkartya() {
    }

    public Ajandekkartya(Integer id) {
        this.id = id;
    }

    public Ajandekkartya(Integer id, String nev, int ar, String leiras, String kep, int akcio, int mennyisegraktaron, int eszkozId, int platformId, Date letrehozva) {
        this.id = id;
        this.nev = nev;
        this.ar = ar;
        this.leiras = leiras;
        this.kep = kep;
        this.akcio = akcio;
        this.mennyisegraktaron = mennyisegraktaron;
        this.eszkozId = eszkozId;
        this.platformId = platformId;
        this.letrehozva = letrehozva;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Ajandekkartya)) {
            return false;
        }
        Ajandekkartya other = (Ajandekkartya) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.codecrafterswebshop.Model.Ajandekkartya[ id=" + id + " ]";
    }

    public static List<Map<String, Object>> ajandekKartyak() {
        List<Map<String, Object>> ajandekKartyak = new ArrayList<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com_CodeCraftersWebshop_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("ajandekKartyak");

            List<Object[]> eredmeny = spq.getResultList();

            if (!eredmeny.isEmpty()) {

                for (Object[] sor : eredmeny) {
                    HashMap<String, Object> ajandekKartya = new HashMap<>();

                    ajandekKartya.put("id", (Integer) sor[0]);
                    ajandekKartya.put("nev", (String) sor[1]);
                    ajandekKartya.put("ar", (Integer) sor[2]);
                    ajandekKartya.put("leiras", (String) sor[3]);
                    ajandekKartya.put("kep", (String) sor[4]);
                    ajandekKartya.put("akcio", (Integer) sor[5]);
                    ajandekKartya.put("mennyisegraktaron", (Integer) sor[6]);

                    ajandekKartyak.add(ajandekKartya);
                }

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        return ajandekKartyak;

    }

    public static Map<String, Object> ajandekKartya(Integer ajandekKartyaIdBE) {

        Map<String, Object> ajandekKartya = new HashMap<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com_CodeCraftersWebshop_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("ajandekKartya");

            spq.registerStoredProcedureParameter("ajandekKartyaIdBE", Integer.class, ParameterMode.IN);
            spq.setParameter("ajandekKartyaIdBE", ajandekKartyaIdBE);

            List<Object[]> eredmeny = spq.getResultList();

            if (!eredmeny.isEmpty()) {
                Object[] sor = eredmeny.get(0);
                ajandekKartya.put("id", sor[0]);
                ajandekKartya.put("nev", sor[1]);
                ajandekKartya.put("ar", sor[2]);
                ajandekKartya.put("leiras", sor[3]);
                ajandekKartya.put("kep", sor[4]);
                ajandekKartya.put("akcio", sor[5]);
                ajandekKartya.put("mennyisegraktaron", sor[6]);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        return ajandekKartya;
    }

    public static boolean ujAjandekKartya(String nevBE, Integer arBE, String leirasBE,
            String kepBE, Integer akcioBE, Integer mennyisegraktaronBE, Integer eszkozIdBE, Integer platformIdBE) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com_CodeCraftersWebshop_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("ujAjandekKartya");

            spq.registerStoredProcedureParameter("nevBE", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("arBE", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("leirasBE", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("kepBE", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("akcioBE", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("mennyisegraktaronBE", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("eszkozIdBE", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("platformIdBE", Integer.class, ParameterMode.IN);

            spq.setParameter("nevBE", nevBE);
            spq.setParameter("arBE", arBE);
            spq.setParameter("leirasBE", leirasBE);
            spq.setParameter("kepBE", kepBE);
            spq.setParameter("akcioBE", akcioBE);
            spq.setParameter("mennyisegraktaronBE", mennyisegraktaronBE);
            spq.setParameter("eszkozIdBE", eszkozIdBE);
            spq.setParameter("platformIdBE", platformIdBE);

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

    public static boolean frissitesAjandekKartya(Integer idBE, String nevBE, Integer arBE, String leirasBE,
            String kepBE, Integer akcioBE, Integer mennyisegraktaronBE, Integer eszkozIdBE, Integer platformIdBE) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com_CodeCraftersWebshop_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("frissitesAjandekKartya");

            spq.registerStoredProcedureParameter("idBE", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("nevBE", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("arBE", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("leirasBE", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("kepBE", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("akcioBE", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("mennyisegraktaronBE", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("eszkozIdBE", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("platformIdBE", Integer.class, ParameterMode.IN);

            spq.setParameter("idBE", idBE);
            spq.setParameter("nevBE", nevBE);
            spq.setParameter("arBE", arBE);
            spq.setParameter("leirasBE", leirasBE);
            spq.setParameter("kepBE", kepBE);
            spq.setParameter("akcioBE", akcioBE);
            spq.setParameter("mennyisegraktaronBE", mennyisegraktaronBE);
            spq.setParameter("eszkozIdBE", eszkozIdBE);
            spq.setParameter("platformIdBE", platformIdBE);

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

    public static boolean torlesAjandekKartya(Integer idBE) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com_CodeCraftersWebshop_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("torlesAjandekKartya");

            spq.registerStoredProcedureParameter("idBE", Integer.class, ParameterMode.IN);
            spq.setParameter("idBE", idBE);

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

    public static boolean nevEllenorzes(String nev) throws AjandekkartyaException {
        if (nev.equals("")) {
            throw new AjandekkartyaException("Az ajándék kártya neve lehet üres!");
        } else if (nev.length() > 100) {
            throw new AjandekkartyaException("Az ajándék kártya neve nem lehet 100 karakternél hosszabb!");
        } else {
            return true;
        }
    }

    public static boolean arEllenorzes(Integer ar) throws AjandekkartyaException {
        if (ar < 0) {
            throw new AjandekkartyaException("Az ajándék kártya ára nem lehet kisebb, mint 0!");
        } else {
            return true;
        }
    }

    public static boolean leirasEllenorzes(String leiras) throws AjandekkartyaException {
        if (leiras.equals("")) {
            throw new AjandekkartyaException("Az ajándék kártya leírása lehet üres!");
        } else {
            return true;
        }
    }

    public static boolean kepEllenorzes(String kep) throws AjandekkartyaException {
        if (kep.equals("")) {
            throw new AjandekkartyaException("Az ajándék kártya képe lehet üres!");
        } else {
            return true;
        }
    }

    public static boolean akcioEllenorzes(Integer akcio) throws AjandekkartyaException {
        if (akcio < 0) {
            throw new AjandekkartyaException("Az ajándék kártya akciója nem lehet kisebb, mint 0!");
        } else if (akcio > 100) {
            throw new AjandekkartyaException("Az ajándék kártya akciója nem lehet nagyobb, mint 100!");
        } else {
            return true;
        }
    }

    public static boolean mennyisegraktaronEllenorzes(Integer mennyisegraktaron) throws AjandekkartyaException {
        if (mennyisegraktaron < 0) {
            throw new AjandekkartyaException("Az ajándék kártya mennyisegraktaronja nem lehet kisebb, mint 0!");
        } else {
            return true;
        }
    }

}
