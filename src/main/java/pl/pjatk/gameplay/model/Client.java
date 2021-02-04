package pl.pjatk.gameplay.model;

import net.bytebuddy.implementation.bind.annotation.Default;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.regex.Pattern;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private int age;
    private Long ownedBook;
    private LocalDateTime borrowDate;

    public Client(String name, String surname, String phoneNumber, int age) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public Client(Long id, String name, String surname, String phoneNumber, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getOwnedBook() {
        return ownedBook;
    }

    public void setOwnedBook(Long ownedBook) {
        this.ownedBook = ownedBook;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public boolean isEmpty() {
        return (this.name == null) &&
                (this.surname == null) &&
                (this.phoneNumber == null) &&
                (this.age == 0);
    }

    public boolean isNumberLegit() {
        Pattern pattern = Pattern.compile("^[1-9]\\d{2}\\d{3}\\d{4}");
        return pattern.equals(this.phoneNumber);
    }
}
