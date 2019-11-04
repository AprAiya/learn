package edu.hubu.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import edu.hubu.learn.dao.FlowerDao;
import edu.hubu.learn.entity.Flower;

@Service
public class FlowerService {

    @Autowired
    private FlowerDao flowerDao;

    public Flower getFlower(Long id) {
        return flowerDao.findById(id).get();
    }

    public List<Flower> getFlowers() {
        return flowerDao.findAll(new Sort(Direction.DESC, "id"));
    }

    public List<Flower> searchFlowers(String keyword) {
        Flower flower = new Flower();
        flower.setName(keyword);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", match->match.contains());
        Example<Flower> example = Example.of(flower, matcher);
        Sort sort = new Sort(Direction.DESC, "id");
        return flowerDao.findAll(example, sort);
    }

    public Flower addFlower(Flower flower) {
        return flowerDao.save(flower);
    }

    public void deleteFlower(Long id) {
        flowerDao.deleteById(id);
    }

    public void modifyFlower(Flower flower) {
        flowerDao.save(flower);
    }
}