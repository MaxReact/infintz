package uz.zako.infintz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.zako.infintz.entity.Rectangle;

@Repository
public interface RectangleRepository extends JpaRepository<Rectangle,Integer> {
}
