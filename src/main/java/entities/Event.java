package entities;



import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private LocalDate date;
    private String location;
    @Column(name = "max_participants")
    private int maxParticipants;
    @ManyToMany
    @JoinTable(name = "events_users",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @ToString.Exclude
    @JsonIgnore
    private List<User> users;
}
