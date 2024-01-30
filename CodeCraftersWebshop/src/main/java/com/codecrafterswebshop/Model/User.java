package com.codecrafterswebshop.Model;

import com.codecrafterswebshop.Config.Database;
import com.codecrafterswebshop.Exception.UserException;
import java.io.Serializable;
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
@Table(name = "felhasznalo")
@NamedQueries({
    @NamedQuery(name = "Felhasznalo.findAll", query = "SELECT f FROM Felhasznalo f"),
    @NamedQuery(name = "Felhasznalo.findById", query = "SELECT f FROM Felhasznalo f WHERE f.id = :id"),
    @NamedQuery(name = "Felhasznalo.findByFelhasznaloNev", query = "SELECT f FROM Felhasznalo f WHERE f.userName = :userName"),
    @NamedQuery(name = "Felhasznalo.findByVezetekNev", query = "SELECT f FROM Felhasznalo f WHERE f.lastName = :lastName"),
    @NamedQuery(name = "Felhasznalo.findByKeresztNev", query = "SELECT f FROM Felhasznalo f WHERE f.firstName = :firstName"),
    @NamedQuery(name = "Felhasznalo.findByEmail", query = "SELECT f FROM Felhasznalo f WHERE f.email = :email"),
    @NamedQuery(name = "Felhasznalo.findByLetrehozva", query = "SELECT f FROM Felhasznalo f WHERE f.letrehozva = :letrehozva"),
    @NamedQuery(name = "Felhasznalo.findByFrissitve", query = "SELECT f FROM Felhasznalo f WHERE f.frissitve = :frissitve")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "userName")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lastName")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "firstName")
    private String firstName;
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
    @Column(name = "letrehozva")
    @Temporal(TemporalType.TIMESTAMP)
    private Date letrehozva;
    @Column(name = "frissitve")
    @Temporal(TemporalType.TIMESTAMP)
    private Date frissitve;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String userName, String lastName, String firstName, String email, String jelszo) {
        this.id = id;
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.jelszo = jelszo;
    }

    public User(Integer id, String userName, String lastName, String firstName, String email) {
        this.id = id;
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFelhasznaloNev() {
        return userName;
    }

    public void setFelhasznaloNev(String userName) {
        this.userName = userName;
    }

    public String getVezetekNev() {
        return lastName;
    }

    public void setVezetekNev(String lastName) {
        this.lastName = lastName;
    }

    public String getKeresztNev() {
        return firstName;
    }

    public void setKeresztNev(String firstName) {
        this.firstName = firstName;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.codecrafterswebshop.Model.Felhasznalo[ id=" + id + " ]";
    }

    public static Map<String, Object> user(Integer idIN) {

        Map<String, Object> user = new LinkedHashMap<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPersistenceUnitName());
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("user");

            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("idIN", idIN);

            List<Object[]> result = spq.getResultList();

            if (!result.isEmpty()) {
                Object[] line = result.get(0);
                user.put("id", line[0]);
                user.put("userName", line[1]);
                user.put("lastName", line[2]);
                user.put("firstName", line[3]);
                user.put("email", line[4]);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        return user;
    }

    public static User login(String userNameIN, String passwordIN) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPersistenceUnitName());
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("login");

            spq.registerStoredProcedureParameter("userNameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("userIdOUT", Integer.class, ParameterMode.OUT);
            spq.registerStoredProcedureParameter("userNameOUT", String.class, ParameterMode.OUT);
            spq.registerStoredProcedureParameter("lastNameOUT", String.class, ParameterMode.OUT);
            spq.registerStoredProcedureParameter("firstNameOUT", String.class, ParameterMode.OUT);
            spq.registerStoredProcedureParameter("emailOUT", String.class, ParameterMode.OUT);

            spq.setParameter("userNameIN", userNameIN);
            spq.setParameter("passwordIN", passwordIN);

            Integer id = (Integer) spq.getOutputParameterValue("userIdOUT");
            String userName = (String) spq.getOutputParameterValue("userNameOUT");
            String lastName = (String) spq.getOutputParameterValue("lastNameOUT");
            String firstName = (String) spq.getOutputParameterValue("firstNameOUT");
            String email = (String) spq.getOutputParameterValue("emailOUT");

            User f = new User(id, userName, lastName, firstName, email);
            return f;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new User();
        } finally {
            em.clear();
            em.close();
            emf.close();
        }

    }

    public static boolean registration(String userNameIN, String lastNameIN, String firstNameIN,
            String emailIN, String passwordIN) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPersistenceUnitName());
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("registration");

            spq.registerStoredProcedureParameter("userNameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("lastNameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("firstNameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIN", String.class, ParameterMode.IN);

            spq.setParameter("userNameIN", userNameIN);
            spq.setParameter("lastNameIN", lastNameIN);
            spq.setParameter("firstNameIN", firstNameIN);
            spq.setParameter("emailIN", emailIN);
            spq.setParameter("passwordIN", passwordIN);

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

    public static boolean updateUser(Integer idIN, String userNameIN, String lastNameIN, String firstNameIN, String passwordIN) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPersistenceUnitName());
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateUser");

            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("userNameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("lastNameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("firstNameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIN", String.class, ParameterMode.IN);

            spq.setParameter("idIN", idIN);
            spq.setParameter("userNameIN", userNameIN);
            spq.setParameter("lastNameIN", lastNameIN);
            spq.setParameter("firstNameIN", firstNameIN);
            spq.setParameter("passwordIN", passwordIN);

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

    public static boolean deleteUser(Integer userIdIN) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPersistenceUnitName());
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteUser");

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

    public static boolean checkUsernameUnique(String userName) throws UserException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPersistenceUnitName());
        EntityManager em = emf.createEntityManager();

        int piece = 0;
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("checkUsername");

            spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("pieceOUT", Integer.class, ParameterMode.OUT);

            spq.setParameter("nameIN", userName);
            piece = (Integer) spq.getOutputParameterValue("pieceOUT");

        } catch (Exception e) {
            System.err.println(e.getMessage());

        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        if (piece > 0) {
            throw new UserException("A felhasználó neve már létezik!");
        } else {
            return true;
        }
    }

    public static boolean checkUsername(String userName) throws UserException {
        if (userName.equals("")) {
            throw new UserException("A felhasználó neve nem lehet üres!");
        } else if (userName.length() > 100) {
            throw new UserException("A felhasználó neve nem lehet 100 karakternél hosszabb!");
        } else {
            return true;
        }
    }

    public static boolean checkLastName(String lastName) throws UserException {

        boolean includeSpecialCharacter = false;

        for (char c : lastName.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                includeSpecialCharacter = true;
            }
        }

        if (lastName.equals("")) {
            throw new UserException("A felhasználó vezetékneve nem lehet üres!");
        } else if (lastName.length() > 100) {
            throw new UserException("A felhasználó vezetékneve nem lehet 100 karakternél hosszabb!");
        } else if (includeSpecialCharacter) {
            throw new UserException("A felhasználó vezetékneve nem tartalmazhat speciális karaktert!");
        } else {
            return true;
        }
    }

    public static boolean checkFirstName(String firstName) throws UserException {

        boolean includeSpecialCharacter = false;

        for (char c : firstName.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                includeSpecialCharacter = true;
            }
        }

        if (firstName.equals("")) {
            throw new UserException("A felhasználó keresztneve nem lehet üres!");
        } else if (firstName.length() > 100) {
            throw new UserException("A felhasználó keresztneve nem lehet 100 karakternél hosszabb!");
        } else if (includeSpecialCharacter) {
            throw new UserException("A felhasználó keresztneve nem tartalmazhat speciális karaktert!");
        } else {
            return true;
        }
    }

    public static boolean checkEmailUnique(String email) throws UserException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPersistenceUnitName());
        EntityManager em = emf.createEntityManager();

        int piece = 0;
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("checkEmail");

            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("pieceOUT", Integer.class, ParameterMode.OUT);

            spq.setParameter("emailIN", email);
            piece = (Integer) spq.getOutputParameterValue("pieceOUT");

        } catch (Exception e) {
            System.err.println(e.getMessage());

        } finally {
            em.clear();
            em.close();
            emf.close();
        }

        if (piece > 0) {
            throw new UserException("A felhasználó emailje már létezik!");
        } else {
            return true;
        }
    }

    public static boolean checkEmail(String email) throws UserException {
        if (email.equals("")) {
            throw new UserException("A felhasználó emailje nem lehet üres!");
        } else if (email.length() > 100) {
            throw new UserException("A felhasználó emailje nem lehet 100 karakternél hosszabb!");
        } else if (!email.contains("@")) {
            throw new UserException("A felhasználó emailjének tartalmaznia kell a @ karaktert!");
        } else {
            return true;
        }
    }

    public static boolean checkPassword(String password) throws UserException {

        boolean includeNumber = false;
        boolean includeLetter = false;
        boolean includeSpecialCharacter = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                includeNumber = true;
            } else if (Character.isLetter(c)) {
                includeLetter = true;
            } else if (!Character.isLetterOrDigit(c)) {
                includeSpecialCharacter = true;
            }
        }

        if (password.equals("")) {
            throw new UserException("A felhasználó jelszava nem lehet üres!");
        } else if (password.length() > 100) {
            throw new UserException("A felhasználó jelszava nem lehet 100 karakternél hosszabb!");
        } else if (password.length() < 8) {
            throw new UserException("A felhasználó jelszava nem lehet 8 karakternél rövidebb!");
        } else if (!includeNumber) {
            throw new UserException("A felhasználó jelszavának tartalmaznia kell számot!");
        } else if (!includeLetter) {
            throw new UserException("A felhasználó jelszavának tartalmaznia kell betűt!");
        } else if (!includeSpecialCharacter) {
            throw new UserException("A felhasználó jelszavának tartalmaznia kell speciális karaktert!");
        } else {
            return true;
        }
    }
}
