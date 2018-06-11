package com.devopsbuddy.backend.persistence.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.devopsbuddy.backend.persistence.converters.LocalDateTimeAttributeConverter;

@Entity
public class PasswordResetToken implements Serializable{
	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_TOKEN_LENTH_IN_MINS = 0;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(unique=true)
	private String token;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")	
	private User user;
	
	@Convert(converter=LocalDateTimeAttributeConverter.class)
	private LocalDateTime expiryDate;
	
	public PasswordResetToken() {
		
	}
	
	public PasswordResetToken(String token, User user, LocalDateTime creationDatetime, int expInMinutes) {
		if(null == token || null == creationDatetime || null == user) {
			throw new IllegalArgumentException("Token, user, creationtime cannot be null");
		}
		
		if(expInMinutes == 0) {
			expInMinutes=DEFAULT_TOKEN_LENTH_IN_MINS;
		}
		
		this.token= token;
		this.user=user;
		this.expiryDate=creationDatetime.plusMinutes(expInMinutes);
				
		
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasswordResetToken that = (PasswordResetToken) o;

        return id == that.id;

    }
	
	@Override
	public int hashCode() {
        return (int) (id ^ (id >>> 32));
        }

}
