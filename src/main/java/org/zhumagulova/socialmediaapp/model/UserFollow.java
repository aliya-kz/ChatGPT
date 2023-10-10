package org.zhumagulova.socialmediaapp.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User follower;    // User who is following

    @ManyToOne
    private User following;   // User who is being followed

    public UserFollow(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }
}
