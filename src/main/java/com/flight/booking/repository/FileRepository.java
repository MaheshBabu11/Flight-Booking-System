package com.flight.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.booking.repository.entity.FilesEntity;

public interface FileRepository extends JpaRepository<FilesEntity, Integer> {
}
