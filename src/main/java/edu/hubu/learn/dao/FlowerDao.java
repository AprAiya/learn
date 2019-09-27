package edu.hubu.learn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.hubu.learn.entity.Flower;;

public interface FlowerDao extends JpaRepository<Flower, Long> {

}