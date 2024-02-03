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
@Table(name = "game")
@NamedQueries({
    @NamedQuery(name = "Game.findAll", query = "SELECT g FROM Game g"),
    @NamedQuery(name = "Game.findById", query = "SELECT g FROM Game g WHERE g.id = :id"),
    @NamedQuery(name = "Game.findByName", query = "SELECT g FROM Game g WHERE g.name = :name"),
    @NamedQuery(name = "Game.findByPrice", query = "SELECT g FROM Game g WHERE g.price = :price"),
    @NamedQuery(name = "Game.findByImage", query = "SELECT g FROM Game g WHERE g.image = :image"),
    @NamedQuery(name = "Game.findByAgeLimit", query = "SELECT g FROM Game g WHERE g.ageLimit = :ageLimit"),
    @NamedQuery(name = "Game.findByDiscount", query = "SELECT g FROM Game g WHERE g.discount = :discount"),
    @NamedQuery(name = "Game.findByInStock", query = "SELECT g FROM Game g WHERE g.inStock = :inStock"),
    @NamedQuery(name = "Game.findByDeviceId", query = "SELECT g FROM Game g WHERE g.deviceId = :deviceId"),
    @NamedQuery(name = "Game.findByPlatformId", query = "SELECT g FROM Game g WHERE g.platformId = :platformId"),
    @NamedQuery(name = "Game.findByCreatedAt", query = "SELECT g FROM Game g WHERE g.createdAt = :createdAt"),
    @NamedQuery(name = "Game.findByAmountSold", query = "SELECT g FROM Game g WHERE g.amountSold = :amountSold")})
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ageLimit")
    private int ageLimit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "discount")
    private int discount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inStock")
    private int inStock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deviceId")
    private int deviceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "platformId")
    private int platformId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amountSold")
    private int amountSold;

    public Game() {
    }

    public Game(Integer id) {
        this.id = id;
    }

    public Game(Integer id, String name, int price, String description, String image, int ageLimit, int discount, int inStock, int deviceId, int platformId, Date createdAt, int amountSold) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.ageLimit = ageLimit;
        this.discount = discount;
        this.inStock = inStock;
        this.deviceId = deviceId;
        this.platformId = platformId;
        this.createdAt = createdAt;
        this.amountSold = amountSold;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getAmountSold() {
        return amountSold;
    }

    public void setAmountSold(int amountSold) {
        this.amountSold = amountSold;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Game)) {
            return false;
        }
        Game other = (Game) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.codecrafterswebshop.Model.Game[ id=" + id + " ]";
    }

    public static List<Map<String, Object>> games() {
        List<Map<String, Object>> games = new ArrayList<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPersistenceUnitName());
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("games");

            List<Object[]> result = spq.getResultList();

            if (!result.isEmpty()) {

                for (Object[] line : result) {
                    LinkedHashMap<String, Object> game = new LinkedHashMap<>();

                    game.put("id", (Integer) line[0]);
                    game.put("gameName", (String) line[1]);
                    game.put("price", (Integer) line[2]);
                    game.put("image", (String) line[3]);
                    game.put("ageLimit", (Integer) line[4]);
                    game.put("discount", (Integer) line[5]);
                    game.put("deviceName", (String) line[6]);
                    game.put("platformName", (String) line[7]);

                    games.add(game);
                }

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        return games;

    }

    public static List<Map<String, Object>> carouselGames() {
        List<Map<String, Object>> games = new ArrayList<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPersistenceUnitName());
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("carouselGames");

            List<Object[]> result = spq.getResultList();

            if (!result.isEmpty()) {

                for (Object[] line : result) {
                    LinkedHashMap<String, Object> game = new LinkedHashMap<>();

                    game.put("id", (Integer) line[0]);
                    game.put("name", (String) line[1]);
                    game.put("price", (Integer) line[2]);
                    game.put("image", (String) line[3]);
                    game.put("discount", (Integer) line[4]);

                    games.add(game);
                }

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        return games;

    }

    public static List<Map<String, Object>> bestsellers() {
        List<Map<String, Object>> bestsellers = new ArrayList<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPersistenceUnitName());
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("bestsellers");

            List<Object[]> result = spq.getResultList();

            if (!result.isEmpty()) {

                for (Object[] line : result) {
                    LinkedHashMap<String, Object> bestseller = new LinkedHashMap<>();

                    bestseller.put("id", (Integer) line[0]);
                    bestseller.put("name", (String) line[1]);
                    bestseller.put("price", (Integer) line[2]);
                    bestseller.put("image", (String) line[3]);
                    bestseller.put("discount", (Integer) line[4]);

                    bestsellers.add(bestseller);
                }

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        return bestsellers;

    }

    public static List<Map<String, Object>> newGames() {
        List<Map<String, Object>> games = new ArrayList<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPersistenceUnitName());
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("newGames");

            List<Object[]> result = spq.getResultList();

            if (!result.isEmpty()) {

                for (Object[] line : result) {
                    LinkedHashMap<String, Object> game = new LinkedHashMap<>();

                    game.put("id", (Integer) line[0]);
                    game.put("name", (String) line[1]);
                    game.put("price", (Integer) line[2]);
                    game.put("image", (String) line[3]);
                    game.put("discount", (Integer) line[4]);

                    games.add(game);
                }

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        return games;

    }

    public static Map<String, Object> game(Integer idBe) {

        Map<String, Object> game = new LinkedHashMap<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPersistenceUnitName());
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("game");

            spq.registerStoredProcedureParameter("idBe", Integer.class, ParameterMode.IN);
            spq.setParameter("idBe", idBe);

            List<Object[]> result = spq.getResultList();

            if (!result.isEmpty()) {
                Object[] line = result.get(0);

                game.put("id", (Integer) line[0]);
                game.put("gameName", (String) line[1]);
                game.put("price", (Integer) line[2]);
                game.put("description", (String) line[3]);
                game.put("image", (String) line[3]);
                game.put("ageLimit", (Integer) line[4]);
                game.put("discount", (Integer) line[5]);
                game.put("inStock", (Integer) line[5]);
                game.put("deviceName", (String) line[6]);
                game.put("platformName", (String) line[7]);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        return game;
    }

}
