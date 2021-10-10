package relationship;

import util.QueryRunner;

public class JpaMain_relationship {
    public static void main(String[] args) {
        QueryRunner.execute(em ->{
            TeamR team = new TeamR();
            team.setName("asd");
            em.persist(team);

            MemberR member = new MemberR();
            member.setUsername("asd");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            TeamR team1 = em.find(TeamR.class, team.getId());

            System.out.println("==============");
            for (MemberR m : team1.getMembers()) {
                System.out.println("m = " + m.getUsername());
            }
            System.out.println("==============");
        });
    }
}
