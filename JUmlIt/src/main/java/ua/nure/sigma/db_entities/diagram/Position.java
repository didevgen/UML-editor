package ua.nure.sigma.db_entities.diagram;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "position")
public class Position {

	private long classId;

	private long x;

	private long y;
	
	@JsonIgnore 
	private Clazz clazz;

	public Position() {
	}

	public Position(long x, long y) {
		this.x = x;
		this.y = y;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", 
			parameters = @Parameter(name = "property", value = "clazz") )
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "class_id", unique = true, nullable = false)
	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	@Column(name = "x")
	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}

	@Column(name = "y")
	public long getY() {
		return y;
	}

	public void setY(long y) {
		this.y = y;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

}
