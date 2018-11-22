package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import hellojpa.entity.Member;
import hellojpa.entity.MemberType;
import hellojpa.entity.Team;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx= em.getTransaction();
		tx.begin();
		
		try {
			/*Team team = new Team();
			team.setName("teamA");
			em.persist(team);
			
			Member member = new Member();
//			member.setId(100L);
			member.setName("안녕하세요");
			member.setMemberType(MemberType.USER);
//			member.setTeamId(team.getId());
			member.setTeam(team);
			em.persist(member);*/
			
			// ----------------

			
			/*
			// lazy의 경우에는 아래 라인에서 member만 select 함
			// lazy를 사용하기 위해서는 엔티티가 영속상태여야 한다. 즉, em.clear(), em.close() 한 이후에는 lazy loading 되지 않음
			Member findMember = em.find(Member.class, member.getId());
			// 현재 findTeam에는 가짜 객체(프록시 객체)가 들어있음
			Team findTeam = findMember.getTeam();
			
			// 아래에서 실제로 팀을 사용할 때 team을 select 해 옴
			System.out.println("find team name : " + findTeam.getName());*/
			
			
			// -----------------
			
			
			/*
			// 스냅샷 비교해서 db 반영(쿼리 나감)
			em.flush();
			// 영속성 컨텍스트(em) 비우기(1차 캐시 날리기)
			em.clear();
			
			Team findTeam2 = em.find(Team.class, team.getId());
			System.out.println(findTeam2.getMembers().size());
			*/
			
			
			// -----------------
			
			Team team = new Team();
			team.setName("teamA");
			em.persist(team);
			
			Member member = new Member();
			member.setName("안녕하세요");
			member.setMemberType(MemberType.USER);
			em.persist(member);
			
			// fk 주인이 아니므로 추가하더라도 적용되지 않음 
			team.getMembers().add(member);
			// fk 주인이므로 team_id 컬럼에 반영됨
			// 양쪽 다 반영해주는게 권장사항
			member.setTeam(team);
			
			
			
			tx.commit();
		}catch(Exception e) {
			// 오류가 생겼을 경우 롤백해야 됨
			tx.rollback();
			e.printStackTrace();
		}finally {
			// 리소스 반환
			em.close();
		}
		
		emf.close();
	}
}
