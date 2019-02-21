
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
public class ProcessionPosition extends DomainEntity {

	//Atributos de clase
	private int	rowPosition;
	private int	columnPosition;


	@Min(1)
	public int getRowPosition() {
		return this.rowPosition;
	}

	public void setRowPosition(final int rowPosition) {
		this.rowPosition = rowPosition;
	}

	@Min(1)
	public int getColumnPosition() {
		return this.columnPosition;
	}

	public void setColumnPosition(final int columnPosition) {
		this.columnPosition = columnPosition;
	}

}
