package rsh.spring;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.util.Assert;

@Entity
public class Project extends AbstractEntity {


	@Column(unique = true)
	private String name;

<<<<<<< HEAD
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProjectStaff> projectStaff; 
=======
  @OneToMany(mappedBy="project", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProjectStaff> projectStaff; // = new ArrayList<ProjectStaff>();
>>>>>>> trial

	public Project(String name) {
		Assert.hasText(name);
    this.setName(name);
	}

	protected Project() {
	}

<<<<<<< HEAD
  public List<ProjectStaff> getProjectStaff() {
    return Collections.unmodifiableList(projectStaff);
  }
  public void setProjectStaff(List<ProjectStaff> projectStaff) {
    this.projectStaff = projectStaff;
  }
=======
//  public void addProjectStaff(ProjectStaff projectStaff) {
//    Assert.notNull(projectStaff);
//    this.projectStaff.add(projectStaff);
//    projectStaff.setProject(this);
//  }
//  public List<ProjectStaff> getProjectStaff() {
//    return Collections.unmodifiableList(projectStaff);
//  }
//  public void setProjectStaff(List<ProjectStaff> projectStaff) {
//    this.projectStaff = projectStaff;
//  }
>>>>>>> trial

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
