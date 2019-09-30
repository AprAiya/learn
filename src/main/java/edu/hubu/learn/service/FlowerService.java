package edu.hubu.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hubu.learn.dao.FlowerDao;
import edu.hubu.learn.entity.Flower;;

@Service
public class FlowerService {

    @Autowired
    private FlowerDao flowerDao;

    public Flower getFlower(Long id) {
        return flowerDao.findById(id).get();
    }
    public List<Flower> getFlowers() {
        return flowerDao.findAll();
    }
}