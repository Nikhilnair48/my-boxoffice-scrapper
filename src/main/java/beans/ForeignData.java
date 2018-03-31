package beans;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ForeignData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ForeignDataKey FDKey;
	//private String name;
	private String distributor;
	private Long release_date;
	private Long closing_date;;
	private String opening_weekend;
	private String total_gross;
	
	public ForeignData() {
		FDKey = new ForeignDataKey();
		//this.name = "";
		this.distributor = "";
		//this.release_date = "";
		//this.closing_date = "";
		this.opening_weekend = "";
		this.total_gross = "";
	}

	public ForeignDataKey getFDKey() {
		return FDKey;
	}

	public void setFDKey(ForeignDataKey fDKey) {
		FDKey = fDKey;
	}

	/*public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}*/

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public Long getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Long release_date) {
		this.release_date = release_date;
	}

	public Long getClosing_date() {
		return closing_date;
	}

	public void setClosing_date(Long closing_date) {
		this.closing_date = closing_date;
	}

	public String getOpening_weekend() {
		return opening_weekend;
	}

	public void setOpening_weekend(String opening_weekend) {
		this.opening_weekend = opening_weekend;
	}

	public String getTotal_gross() {
		return total_gross;
	}

	public void setTotal_gross(String total_gross) {
		this.total_gross = total_gross;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ForeignData [FDKey=" + FDKey + ", distributor=" + distributor
				+ ", release_date=" + release_date + ", closing_date="
				+ closing_date + ", opening_weekend=" + opening_weekend
				+ ", total_gross=" + total_gross + "]";
	}
}
