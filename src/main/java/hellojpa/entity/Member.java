package hellojpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	// primitive type이 아니라 wrapper class 쓰는 것이 권장. 기본 타입의 경우 값이 없을 때 0값이 세팅되는데 혼동되지 않게 하기 위해 wrapper class 권장 
	// int 보단 long 권장. 21억은 생각보다 금방 넘게 됨
	private Long id;
	
	@Column(name="USERNAME")
	private String name;
	
	//ordinal의 경우 순서대로 숫자가 매겨진다.(default) 현업에서는 절대 사용 금지. enum에 다른 값을 끼워넣을 경우 기존 값들과 순서가 꼬이게 됨
//	@Enumerated(EnumType.ORDINAL)
	@Enumerated(EnumType.STRING)
	private MemberType memberType;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date regDate;

//	@Column(name="TEAM_ID")
//	private Long teamId;
	
	// 일단 모두 lazy가 권장. 꼭 필요한 부분에서 eager
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TEAM_ID")
	private Team team;
	
	@Override
	public String toString() {
		return id + " / " + name + " / " + memberType + " / " + team.getId() + " / " + team.getName()+ " / ";
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
	public MemberType getMemberType() {
		return memberType;
	}
	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}
//	public Date getRegDate() {
//		return regDate;
//	}
//	public void setRegDate(Date regDate) {
//		this.regDate = regDate;
//	}
	
//	public Long getTeamId() {
//		return teamId;
//	}
//	public void setTeamId(Long teamId) {
//		this.teamId = teamId;
//	}
	
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
}
