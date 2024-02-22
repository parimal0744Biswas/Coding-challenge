package com.parimal.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MyErrorDetails
{

	private LocalDateTime timestamp;

	private String name;

	private String description;

	public MyErrorDetails(LocalDateTime timestamp, String name, String description)
	{
		super();
		this.timestamp = timestamp;
		this.name = name;
		this.description = description;
	}

	public MyErrorDetails()
	{
		// TODO Auto-generated constructor stub
	}

	public LocalDateTime getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp)
	{
		this.timestamp = timestamp;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

}
