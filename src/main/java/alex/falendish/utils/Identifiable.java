package alex.falendish.utils;

public interface Identifiable<T> {

    T getId();

    Role getRole();

    void setRole(Role unknown);
}
