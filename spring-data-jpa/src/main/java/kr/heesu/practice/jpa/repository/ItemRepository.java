package kr.heesu.practice.jpa.repository;

import kr.heesu.practice.jpa.entitiy.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            // ID 값이 없는 것이라면 새로 생성하는 것
            em.persist(item);
        } else {
            // ID 값이 있다면 이미 존재하는 것 업데이트 개념
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
