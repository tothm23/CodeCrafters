package com.codecrafterswebshop.Model;

import com.codecrafterswebshop.Config.Database;
import com.codecrafterswebshop.Exception.BasketException;
import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tothm23
 */
@Entity
@Table(name = "basket")
@NamedQueries({
    @NamedQuery(name = "Basket.findAll", query = "SELECT b FROM Basket b"),
    @NamedQuery(name = "Basket.findById", query = "SELECT b FROM Basket b WHERE b.id = :id"),
    @NamedQuery(name = "Basket.findByUserId", query = "SELECT b FROM Basket b WHERE b.userId = :userId"),
    @NamedQuery(name = "Basket.findByGameId", query = "SELECT b FROM Basket b WHERE b.gameId = :gameId"),
    @NamedQuery(name = "Basket.findByAmount", query = "SELECT b FROM Basket b WHERE b.amount = :amount")})
public class Basket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "userId")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gameId")
    private int gameId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private int amount;

    public Basket() {
    }

    public Basket(Integer id) {
        this.id = id;
    }

    public Basket(Integer id, int userId, int gameId, int amount) {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Basket)) {
            return false;
        }
        Basket other = (Basket) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.codecrafterswebshop.Model.Basket[ id=" + id + " ]";
    }

    public static List<Map<String, Object>> userBasket(Integer userIdIN) {
        List<Map<String, Object>> baskets = new ArrayList<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.PERSISTENCEUNITNAME);
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("userBasket");

            spq.registerStoredProcedureParameter("userIdIN", Integer.class, ParameterMode.IN);
            spq.setParameter("userIdIN", userIdIN);

            List<Object[]> result = spq.getResultList();

            if (!result.isEmpty()) {

                for (Object[] line : result) {
                    LinkedHashMap<String, Object> basket = new LinkedHashMap<>();

                    basket.put("id", (Integer) line[1]);
                    basket.put("name", (String) line[2]);
                    basket.put("amount", (Integer) line[3]);
                    basket.put("image", (String) line[4]);

                    baskets.add(basket);
                }

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        return baskets;

    }

    public static boolean basket(Integer gameIdIN, Integer userIdIN) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.PERSISTENCEUNITNAME);
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("basket");

            spq.registerStoredProcedureParameter("gameIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("userIdIN", Integer.class, ParameterMode.IN);

            spq.setParameter("gameIdIN", gameIdIN);
            spq.setParameter("userIdIN", userIdIN);

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

    public static boolean deleteGameFromBasket(Integer userIdIN, Integer gameIdIN) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.PERSISTENCEUNITNAME);
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteGameFromBasket");

            spq.registerStoredProcedureParameter("userIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("gameIdIN", Integer.class, ParameterMode.IN);

            spq.setParameter("userIdIN", userIdIN);
            spq.setParameter("gameIdIN", gameIdIN);

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

    public static boolean order(Integer userIdIN) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.PERSISTENCEUNITNAME);
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("order_");

            spq.registerStoredProcedureParameter("userIdIN", Integer.class, ParameterMode.IN);
            spq.setParameter("userIdIN", userIdIN);

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

    public static List<Map<String, Object>> productKeys(Integer userIdIN) {

        List<Map<String, Object>> productKeys = new ArrayList<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.PERSISTENCEUNITNAME);
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("productKeys");

            spq.registerStoredProcedureParameter("userIdIN", Integer.class, ParameterMode.IN);
            spq.setParameter("userIdIN", userIdIN);

            List<Object[]> result = spq.getResultList();

            if (!result.isEmpty()) {

                for (Object[] line : result) {
                    LinkedHashMap<String, Object> productKey = new LinkedHashMap<>();

                    productKey.put("email", (String) line[1]);
                    productKey.put("userName", (String) line[2]);
                    productKey.put("name", (String) line[3]);
                    productKey.put("amount", (Integer) line[4]);
                    productKey.put("key", (String) line[5]);

                    productKeys.add(productKey);
                }

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        return productKeys;
    }

    public static boolean checkGameId(Integer gameIdIN) throws BasketException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.PERSISTENCEUNITNAME);
        EntityManager em = emf.createEntityManager();

        int count = 0;
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("checkGameId");

            spq.registerStoredProcedureParameter("gameIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("countOUT", Integer.class, ParameterMode.OUT);

            spq.setParameter("gameIdIN", gameIdIN);
            count = (Integer) spq.getOutputParameterValue("countOUT");

        } catch (Exception e) {
            System.err.println(e.getMessage());

        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        if (count < 1) {
            throw new BasketException("A játék nem létezik!");
        } else {
            return true;
        }
    }

    public static boolean checkUserId(Integer userIdIN) throws BasketException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.PERSISTENCEUNITNAME);
        EntityManager em = emf.createEntityManager();

        int count = 0;
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("checkUserId");

            spq.registerStoredProcedureParameter("userIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("countOUT", Integer.class, ParameterMode.OUT);

            spq.setParameter("userIdIN", userIdIN);
            count = (Integer) spq.getOutputParameterValue("countOUT");

        } catch (Exception e) {
            System.err.println(e.getMessage());

        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        if (count < 1) {
            throw new BasketException("A felhasználó nem létezik!");
        } else {
            return true;
        }
    }

    public static boolean checkUserIdBasket(Integer userIdIN) throws BasketException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.PERSISTENCEUNITNAME);
        EntityManager em = emf.createEntityManager();

        int count = 0;
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("checkUserIdBasket");

            spq.registerStoredProcedureParameter("userIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("countOUT", Integer.class, ParameterMode.OUT);

            spq.setParameter("userIdIN", userIdIN);
            count = (Integer) spq.getOutputParameterValue("countOUT");

        } catch (Exception e) {
            System.err.println(e.getMessage());

        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        if (count < 1) {
            throw new BasketException("A felhasználó nem tett semmit sem a kosárba!");
        } else {
            return true;
        }
    }

    public static boolean checkGameIdBasket(Integer gameIdIN) throws BasketException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.PERSISTENCEUNITNAME);
        EntityManager em = emf.createEntityManager();

        int count = 0;
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("checkGameIdBasket");

            spq.registerStoredProcedureParameter("gameIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("countOUT", Integer.class, ParameterMode.OUT);

            spq.setParameter("gameIdIN", gameIdIN);
            count = (Integer) spq.getOutputParameterValue("countOUT");

        } catch (Exception e) {
            System.err.println(e.getMessage());

        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        if (count < 1) {
            throw new BasketException("A felhasználó nem tett ilyen játékot a kosárba!");
        } else {
            return true;
        }
    }

}
