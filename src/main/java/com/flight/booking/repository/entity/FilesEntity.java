package com.flight.booking.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "files")
public class FilesEntity implements Serializable {

	private static final long serialVersionUID = -1022630155740677093L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "file_id")
	private Integer fileId;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_type")
	private String fileType;

	@Lob
	@Column(name = "file_content")
	private byte[] fileContent;

}
