package net.springBootApp.apiLibrary.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detail_users")
@Data
@NoArgsConstructor
public class DetailUser {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(length = 50 )
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(length = 50)
    private String phoneNumber;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @OneToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User userEmail;

    public DetailUser(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    


}
