package hellojpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	// 양방향 맵핑
	// mappedBy를 썼다는 건 주인이 아니라는 뜻. 단순 조회만 가능. 수정해도 반영 안됨
	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<Member>();

	
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
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
}
