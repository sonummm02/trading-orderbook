package com.paritytrading.parity.ticker.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Entity
@Table("trading")
public class TickerDTO implements Serializable
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@PrimaryKey
	private UUID id;
	
	private String timestamp;

	private String instrument;

	private String bidPrice;
	
	private String bidSize;
	
	private String askPrice;
	
	private String askSize;
	
	private String lastPrice;
	
	private String lastSize;

	 public UUID getId() {
	        return id;
	    }

	    public void setId(UUID id) {
	        this.id = id;
	    }

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public String getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(String bidPrice) {
		this.bidPrice = bidPrice;
	}

	public String getBidSize() {
		return bidSize;
	}

	public void setBidSize(String bidSize) {
		this.bidSize = bidSize;
	}

	public String getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(String askPrice) {
		this.askPrice = askPrice;
	}

	public String getAskSize() {
		return askSize;
	}

	public void setAskSize(String askSize) {
		this.askSize = askSize;
	}

	public String getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(String lastPrice) {
		this.lastPrice = lastPrice;
	}

	public String getLastSize() {
		return lastSize;
	}

	public void setLastSize(String lastSize) {
		this.lastSize = lastSize;
	}
/*
	@Override
	public String toString() {
		return "TickerDTO [id=" + id + ", timestamp=" + timestamp + ", instrument=" + instrument + ", bidPrice="
				+ bidPrice + ", bidSize=" + bidSize + ", askPrice=" + askPrice + ", askSize=" + askSize + ", lastPrice="
				+ lastPrice + ", lastSize=" + lastSize + "]";
	}
	*/
	

}
