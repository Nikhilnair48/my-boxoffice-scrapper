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
	//private String foreignKeyMovieID;
	//private String country;
	//private String name;
	private String distributor;
	private Long release_date;
	private Long closing_date;;
	private String opening_weekend;
	private String total_gross;
	
	public ForeignData() {
		super();
		this.distributor = "";
		this.release_date = 0L;
		this.closing_date = 0L;
		this.opening_weekend = "";
		this.total_gross = "";
	}
	
	public ForeignData(ForeignDataKey fDKey, String distributor,
			Long release_date, Long closing_date, String opening_weekend,
			String total_gross) {
		super();
		FDKey = fDKey;
		this.distributor = distributor;
		this.release_date = release_date;
		this.closing_date = closing_date;
		this.opening_weekend = opening_weekend;
		this.total_gross = total_gross;
	}

	public ForeignDataKey getFDKey() {
		return FDKey;
	}

	public void setFDKey(ForeignDataKey fDKey) {
		FDKey = fDKey;
	}

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
		
}
