package org.zhumagulova.chatgptlow.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User follower;    // User who is following

    @ManyToOne
    private User following;   // User who is being followed

    public UserFollow(long myId, Long authorId) {
        myId = follower.getId();
        authorId = following.getId();
    }
}
