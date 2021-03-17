package com.example.demoUni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoUni.entity.Doc;

public interface DocRepository  extends JpaRepository<Doc,Integer>{

}
