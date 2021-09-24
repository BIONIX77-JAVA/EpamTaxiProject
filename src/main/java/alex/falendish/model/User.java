package alex.falendish.model;

import alex.falendish.utils.Identifiable;
import alex.falendish.utils.Role;
import alex.falendish.utils.UserStatus;

import java.time.LocalDateTime;
import java.util.Set;

public class User implements Identifiable<Long> {

    private Long id;
    private String username;
    private String password;

    private String firstName;
    private String lastName;

    private Set<Role> roles;

    private UserStatus status;

    private boolean locked;

    private LocalDateTime created;

    public User() {
    }

    public User(Long id, String username, String password, String firstName, String lastName,
                Set<Role> roles, UserStatus status, boolean locked, LocalDateTime created) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.status = status;
        this.locked = locked;
        this.created = created;
    }

    public User(Long id, String username, String password, String firstName, String lastName, Set<Role> roles, LocalDateTime created) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.created = created;
    }

    public User(String s) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roles=" + roles +
                ", status=" + status +
                ", locked=" + locked +
                ", created=" + created +
                '}';
    }
}