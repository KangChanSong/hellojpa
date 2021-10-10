package relationship;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TeamR {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<MemberR> members = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MemberR> getMembers() {
        return members;
    }

    public void setMembers(List<MemberR> members) {
        this.members = members;
    }
}
