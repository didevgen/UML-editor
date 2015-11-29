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

@Entity
@Table(name = "position")
public class Position {

	private long positionId;

	private long x;

	private long y;
	
	private Clazz clazz;

	public Position() {
	}

	public Position(long x, long y) {
		this.x = x;
		this.y = y;
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

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "position_id")
	public long getPositionId() {
		return positionId;
	}

	public void setPositionId(long positionId) {
		this.positionId = positionId;
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
